package br.com.softal.pfc.struts.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestHandler;

import br.com.softal.pfc.Configuracao;
import br.com.softal.pfc.Constantes;
import br.com.softal.pfc.Fotogaleria;
import br.com.softal.pfc.Galeria;
import br.com.softal.pfc.Miniatura;
import br.com.softal.pfc.DAO.DAOFactory;
import br.com.softal.pfc.service.PeladaService;
import br.com.softal.pfc.service.PfcServiceLocator;
import br.com.softal.pfc.util.Util;
 
public class GaleriaAction extends PfcAction {
 
	@SuppressWarnings("unchecked")
	public ActionForward abrirConFotos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		GaleriaForm galeriaForm = (GaleriaForm) form;
		List<Galeria> lista = (List<Galeria>) DAOFactory.getGaleriaDAO().findAll();
		for (Galeria g : lista) {
			if (existeFotosnagaleria(g.getGaleriaPK().getCdGaleria()).length <= 1) {
				g.setNuFotos( 0 );
			}
		}
		galeriaForm.setRows( lista );
        return mapping.findForward(FWD_CONSULTAR);
    }

	public ActionForward visualizarFotos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		String cdGaleria = request.getParameter("galeriaPK.cdGaleria").toString();
		request.getSession().setAttribute(Constantes.SESSION_CODIGO_GALERIA, cdGaleria);
        return mapping.findForward(FWD_CONSULTAR);
    }
	
	public ActionForward abrirCadFotos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
        @SuppressWarnings("unused")
		GaleriaForm galeriaForm = (GaleriaForm) form;
        galeriaForm.setEntidade( new Galeria() );
        galeriaForm.getEntidade().setStatusInsert();        
        return mapping.findForward(FWD_CADASTRAR);
    }

	@SuppressWarnings("unchecked")
	public ActionForward editarCadFotos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		GaleriaForm galeriaForm = (GaleriaForm) form;
        Galeria galeria = (Galeria) galeriaForm.getEntidade();
        String cdGaleria = request.getParameter("galeriaPK.cdGaleria");
		if (cdGaleria != null && cdGaleria != "") {
			galeria.getGaleriaPK().setCdGaleria( Integer.valueOf(cdGaleria) );
		}		
		galeria = (Galeria) DAOFactory.getGaleriaDAO().findByPrimaryKey( galeria );
		galeria.setStatusUpdate();
		galeriaForm.setEntidade( galeria );
		
		//-- fotos da galeria
		Fotogaleria fotogaleria = new Fotogaleria();
		fotogaleria.setCdGaleria( galeria.getGaleriaPK().getCdGaleria() );
		List<Fotogaleria> lista = (List<Fotogaleria>) DAOFactory.getFotogaleriaDAO().findAll(fotogaleria);
		galeria.setFotogaleria( lista );
		
        return mapping.findForward(FWD_CADASTRAR);
    }
	
	public ActionForward salvarCadFotos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		GaleriaForm galeriaForm = (GaleriaForm) form;
        Galeria galeria = (Galeria) galeriaForm.getEntidade();
        if (request.getParameter("cdGaleria") != null) {
        	galeria.getGaleriaPK().setCdGaleria( Integer.valueOf( request.getParameter("cdGaleria") ) );
        }
        ActionMessages messages = new ActionMessages();
        try {
        	PeladaService service = PfcServiceLocator.getInstance().getPeladaService();
        	service.salvarGaleria(galeria);
	        if (galeria.isStatusInsert()) {
	        	//-- DAOFactory.getGaleriaDAO().insert( galeria );
	        	messages.add(Constantes.TIPO_MENSAGEM_SUCESSO, new ActionMessage("msg.sucesso.salvar.registro"));
		    } else if (galeria.isStatusUpdate()) {
		    	//-- DAOFactory.getGaleriaDAO().update( galeria );
		    	messages.add(Constantes.TIPO_MENSAGEM_SUCESSO, new ActionMessage("msg.sucesso.atualizar.registro"));
		    } else  if (galeria.isStatusDelete()) {
		    	//-- DAOFactory.getGaleriaDAO().delete( galeria );
		    	this.deletaDiretorioFotos(galeriaForm, request);
		    	messages.add(Constantes.TIPO_MENSAGEM_SUCESSO, new ActionMessage("msg.sucesso.excluir.registro"));
		    	return abrirCadFotos(mapping, galeriaForm, request, response);
		    }
        } catch (Exception e) {
			e.printStackTrace();
			messages.add(Constantes.TIPO_MENSAGEM_ERROR, new ActionMessage("msg.erro.registro.encontrado"));
		} finally {
			if (!messages.isEmpty()) {
				saveMessages(request, messages);				
	        }			
		}
        return editarCadFotos(mapping, form, request, response);
    }
	
	public ActionForward salvarFotos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		GaleriaForm galeriaForm = (GaleriaForm) form;
        Galeria galeria = (Galeria) galeriaForm.getEntidade();
        try {
	    	MultipartRequestHandler x = galeriaForm.getMultipartRequestHandler();
			Object fileData = x.getFileElements().get("Filedata");
			Object cdGaleria = x.getAllElements().get("folder");
			if (cdGaleria != null) {
				String[] g = (String[]) cdGaleria;
				String cdGal= g[0];
				galeria.getGaleriaPK().setCdGaleria( Integer.valueOf( cdGal.substring(1) ) );
			}
			FormFile myFile = (FormFile) fileData;
			galeriaForm.setArquivo(myFile);
	    	if (galeriaForm.getArquivo().getFileName() != null && !galeriaForm.getArquivo().getFileName().equalsIgnoreCase("")) {
	    		Fotogaleria fotogaleria = new Fotogaleria();
	    		fotogaleria.setCdGaleria( galeria.getGaleriaPK().getCdGaleria() );
	    		fotogaleria.setNmArqfoto( galeriaForm.getArquivo().getFileName() );
	    		DAOFactory.getFotogaleriaDAO().insert( fotogaleria );
	    		criarAtualizarFotos(form, request);
	    		criarXmlFotos(form, request);
	    	}
        } catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return mapping.findForward("cadastrar");
    }
	
	private void criarAtualizarFotos(ActionForm form, HttpServletRequest request) {
		GaleriaForm galeriaForm = (GaleriaForm) form;
        Galeria galeria = (Galeria) galeriaForm.getEntidade();
        FormFile foto = galeriaForm.getArquivo();
        String sNmGaleria = "galeria_" + galeria.getGaleriaPK().getCdGaleria();        
        String sCaminho = retornaCaminhoFotos() + sNmGaleria;
        try {
	        File caminho = new File( sCaminho );
	        if (!caminho.exists()) {
	        	caminho.mkdir();
	        }
	        
	        File diskFile = new File(caminho, foto.getFileName());
            if (!diskFile.exists()) {
                diskFile.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(diskFile);
            fos.write(foto.getFileData());
            fos.flush();
            fos.close();
            
            Miniatura.converter( sCaminho + "//" , foto.getFileName());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	private void deletaDiretorioFotos(ActionForm form, HttpServletRequest request) {
		GaleriaForm galeriaForm = (GaleriaForm) form;
        Galeria galeria = (Galeria) galeriaForm.getEntidade();
        String sNmGaleria = "galeria_" + galeria.getGaleriaPK().getCdGaleria();        
        String sCaminho = retornaCaminhoFotos() + sNmGaleria;
        File caminho = new File( sCaminho );
        if (caminho.exists()) {
        	File[] listFiles = caminho.listFiles();
        	for (File file : listFiles) {
        		file.delete();
        	}
        	caminho.delete();
        }
	}
	
	private void criarXmlFotos(ActionForm form, HttpServletRequest request) {
		GaleriaForm galeriaForm = (GaleriaForm) form;
        Galeria galeria = (Galeria) galeriaForm.getEntidade();        

        String sNmGaleria = "galeria_" + galeria.getGaleriaPK().getCdGaleria();        
        String sCaminho = retornaCaminhoFotos() + sNmGaleria;        
        
        try {
	        File caminho = new File(sCaminho);
	        String[] files = caminho.list();
	        
	        File xmlGaleria = new File(caminho, "gallery.xml");
            if (!xmlGaleria.exists()) {
            	xmlGaleria.createNewFile();
            }
            
            FileWriter writer = new FileWriter( xmlGaleria );
            StringBuilder buf = new StringBuilder();
            buf.append("  <tiltviewergallery> \n");
            buf.append("  <photos> \n");
            for (int i = 0; i < files.length; i++) {
            	if (!(files[i].indexOf(".xml") > -1)) {
	                buf.append("  	<photo imageurl=\"" + sNmGaleria + "/" + files[i] + "\" linkurl=\"\" > \n");
	                buf.append("  		<title>" + files[i] + "</title> \n");
	                buf.append("  		<description><![CDATA[" + galeria.getDtGaleriastring() + "\n" + galeria.getDeGaleria() + "]]></description> \n");
	                buf.append("  	</photo> \n");
            	}
			}
            buf.append("  </photos> \n");
            buf.append("  </tiltviewergallery> ");            
            writer.write( buf.toString() );
            writer.flush();
            writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public ActionForward excluirFotoselecionada(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		GaleriaForm galeriaForm = (GaleriaForm) form;
        Galeria galeria = (Galeria) galeriaForm.getEntidade();
        String cdGaleria = request.getParameter("cdGaleria");
        String cdFotogaleria = request.getParameter("cdFotogaleria");
        String nmArquivo = "";
		if (cdGaleria != null && cdGaleria != "") {
			galeria.getGaleriaPK().setCdGaleria( Integer.valueOf(cdGaleria) );
		}
		try {
			Fotogaleria fotogaleria = new Fotogaleria();
			fotogaleria.getFotogaleriaPK().setCdFotogaleria( Integer.valueOf(cdFotogaleria) );
			fotogaleria = (Fotogaleria) DAOFactory.getFotogaleriaDAO().findByPrimaryKey( fotogaleria );
			nmArquivo = fotogaleria.getNmArqfoto(); 
			excluirArquivo(form, nmArquivo);
			DAOFactory.getFotogaleriaDAO().delete( fotogaleria );
			
			criarXmlFotos(form, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return editarCadFotos(mapping, form, request, response);
    }
	
	private Boolean excluirArquivo(ActionForm form, String nmArquivo) {
		GaleriaForm galeriaForm = (GaleriaForm) form;
        Galeria galeria = (Galeria) galeriaForm.getEntidade();        

        String sNmGaleria = "galeria_" + galeria.getGaleriaPK().getCdGaleria();        
        String sCaminho = retornaCaminhoFotos() + sNmGaleria + "/" + nmArquivo;        
        File arquivo = new File(sCaminho);
        if (arquivo.exists()) {
        	arquivo.delete();
        	return true;
        }
		return false;
	}
	
	private String retornaCaminhoFotos() {
        String sCaminho = getServlet().getServletContext().getRealPath("");
        sCaminho = sCaminho.replaceAll("\\\\", "/");
        try {
        	sCaminho = sCaminho.substring(0, sCaminho.lastIndexOf("/"));
		} catch (Exception e) {
			sCaminho = sCaminho.substring(0, sCaminho.lastIndexOf("//"));
		}
		return sCaminho += "/fotos/"; 
	}
	
	private String[] existeFotosnagaleria(Integer cdGaleria) {
        try {
            String sNmGaleria = "galeria_" + cdGaleria;        
            String sCaminho = retornaCaminhoFotos() + sNmGaleria;        
            File arquivo = new File(sCaminho);
            if (arquivo.isDirectory()) {
            	return arquivo.list();
            }
		} catch (Exception e) {
			e.printStackTrace();
		}	
        return new String[1];
	}
	
	
	public ActionForward abrirCadArquivoGaleriaAnterior(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
        @SuppressWarnings("unused")
		GaleriaForm galeriaForm = (GaleriaForm) form;
        Configuracao config = Util.carregaConfiguracao( Configuracao.TP_CONFIG_GALERIA_ANTERIOR );
        if (config.getConfiguracaoPK().getCdConfiguracao() != null) {
        	request.getSession().setAttribute(Constantes.SESSION_NOME_ARQUIVO_CAMPEOES_ANTERIORES, config.getVlConfiguracao().trim());
        }
        return mapping.findForward(FWD_CADASTRAR);
    }
	
	public ActionForward salvarCadArquivoGaleriaAnterior(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
        @SuppressWarnings("unused")
		GaleriaForm galeriaForm = (GaleriaForm) form;
        ActionMessages messages = new ActionMessages();
        Configuracao config = Util.carregaConfiguracao( Configuracao.TP_CONFIG_GALERIA_ANTERIOR );
        try {
        	if (galeriaForm.getArquivo().getFileName() != null && !galeriaForm.getArquivo().getFileName().equalsIgnoreCase("")) {
        		if (config.isStatusInsert()) {
        			config.getConfiguracaoPK().setCdConfiguracao( Configuracao.TP_CONFIG_GALERIA_ANTERIOR );
        			config.setVlConfiguracao( galeriaForm.getArquivo().getFileName() );
        			DAOFactory.getConfiguracaoDAO().insert( config );        			
        		} else {
        			config.setVlConfiguracao( galeriaForm.getArquivo().getFileName() );
        			DAOFactory.getConfiguracaoDAO().update( config );
        		}
        		salvaArquivoCampeoesanterior(form, request);
        	}
        } catch (Exception e) {
			e.printStackTrace();
			messages.add(Constantes.TIPO_MENSAGEM_ERROR, new ActionMessage("msg.erro.registro.encontrado"));
		} finally {
			if (!messages.isEmpty()) {
				saveMessages(request, messages);				
	        }			
		}
        return abrirCadArquivoGaleriaAnterior(mapping, form, request, response);
    }
	

	private void salvaArquivoCampeoesanterior(ActionForm form, HttpServletRequest request) {
		GaleriaForm galeriaForm = (GaleriaForm) form;
        FormFile arquivo = galeriaForm.getArquivo();
        String snmPasta = "campeoes";        
        String sCaminho = retornaCaminhoFotos() + snmPasta;
        try {
	        File caminho = new File( sCaminho );
	        if (!caminho.exists()) {
	        	caminho.mkdir();
	        } else {
	        	String[] arqs = caminho.list();
	        	for (int i = 0; i < arqs.length; i++) {
	        		File diskFile = new File(caminho, arqs[i]);
	        		diskFile.delete();
				}
	        }
	        
	        File diskFile = new File(caminho, arquivo.getFileName());
            if (!diskFile.exists()) {
                diskFile.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(diskFile);
            fos.write(arquivo.getFileData());
            fos.flush();
            fos.close();
            
           // Miniatura.converter( sCaminho + "//" , arquivo.getFileName());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}	
	
}
