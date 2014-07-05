package br.com.softal.pfc.struts.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.Calendar;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;

import br.com.softal.pfc.Constantes;
import br.com.softal.pfc.Socio;
import br.com.softal.pfc.DAO.DAOFactory;
import br.com.softal.pfc.DAO.SocioDAO;
import br.com.softal.pfc.util.ImageUtil;

public class SocioAction extends PfcAction {
	
	@SuppressWarnings("unchecked")
	public ActionForward abrirSociosInicio(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		SocioForm socioForm = (SocioForm) form;
		SocioDAO socioDAO = (SocioDAO) DAOFactory.getDAO("SocioDAO");
		try {
			List<Socio> socios = (List<Socio>) socioDAO.findAll();
			for (Socio s : socios) {
				if (s.getFlForauso() == 1) {
					socioForm.getSociosforauso().add(s);
				} else if (s.getTpSocio() == 1) {
					socioForm.getSociospatrimonial().add(s);
				} else if (s.getTpSocio() == 2) {
					socioForm.getSociospreferencial().add(s);
				} else if (s.getTpSocio() == 3) {
					socioForm.getSociosavulso().add(s);
				} else if (s.getTpSocio() == 4) {
					socioForm.getSociosbenemerito().add(s);
				}
			}
			socioForm.setRows(socios);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(FWD_CONSULTAR);
	}

	public ActionForward abrirSociosShowSocio(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		SocioForm socioForm = (SocioForm) form;
		Socio socio = (Socio) socioForm.getEntidade();
		SocioDAO socioDAO = (SocioDAO) DAOFactory.getDAO("SocioDAO");
		try {
			socio = (Socio) socioDAO.findByPrimaryKey(socio);
			socio.setStatusUpdate();
			socioForm.setEntidade(socio);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return mapping.findForward(FWD_CADASTRAR);
	}

	@SuppressWarnings("unchecked")
	public ActionForward abrirSocioAniversariantes(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SocioForm socioForm = (SocioForm) form;
		Socio socio = (Socio) socioForm.getEntidade();
		SocioDAO socioDAO = (SocioDAO) DAOFactory.getDAO("SocioDAO");
		try {
			Integer nuMesaniversario = null;
			if (request.getParameter("entidade.nuMesnascimento") != null && request.getParameter("entidade.nuMesnascimento") != "" ) {
				nuMesaniversario = Integer.valueOf( request.getParameter("entidade.nuMesnascimento").toString() );
			} else {
				if (nuMesaniversario == null) {
					Calendar c = Calendar.getInstance();		
					nuMesaniversario = (c.get(Calendar.MONTH) + 1);
				}
			}
			socio.setNuMesnascimento( nuMesaniversario );
			List lista = socioDAO.findAniversariantes( nuMesaniversario );
			socioForm.setRows(lista);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(FWD_CONSULTAR);
	}

	@SuppressWarnings("unchecked")
	public ActionForward abrirSocioImagemSocio(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SocioForm socioForm = (SocioForm) form;
		Socio socio = (Socio) socioForm.getEntidade();
		int cdSocio = Integer.parseInt(request.getParameter("socioPK.cdSocio"));
		socio.getSocioPK().setCdSocio(cdSocio);
		SocioDAO socioDAO = (SocioDAO) DAOFactory.getDAO("SocioDAO");
		try {
			socio = (Socio) socioDAO.findByPrimaryKey(socio);
			try {
				byte[] data = null;
				if ((socio.getImFoto() != null) && (socio.getImFoto().length != 0)) {
					data = socio.getImFoto();
				} else {
					String imagemDefault = request.getSession().getServletContext().getRealPath("//jsp//socios//fotos//fotoDefault.jpg");
					data = ImageUtil.readImageAsByteArray(imagemDefault);
				}
				response.setContentType("image/gif");
				ServletOutputStream sout = response.getOutputStream();
				sout.write(data);
				sout.flush();
				sout.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;// mapping.findForward(FWD_CONSULTAR);
	}

	@SuppressWarnings("unchecked")
	public ActionForward abrirCadSocio(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			SocioForm socioForm = (SocioForm) form;
			Socio socio = (Socio) socioForm.getEntidade();
			socio.setTpSocio( 3 );
			socio.setSgUf("SC");
			socio.setNuTrabalho( " " );
			socioForm.getEntidade().setStatusInsert();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(FWD_CADASTRAR);
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward salvarCadSocio(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionMessages messages = new ActionMessages();
		SocioForm socioForm = (SocioForm) form;
		SocioDAO socioDAO = (SocioDAO) DAOFactory.getDAO("SocioDAO");
		Socio socio = (Socio) socioForm.getEntidade();
		if (socio.getTpSocio() == 3) {
			socio.setDeSenha("");
		}
		try {
			FormFile arquivo = socioForm.getArquivo();
			if (arquivo != null) {
				byte[] fileData    = arquivo.getFileData();
				socio.setImFoto( fileData );
				if (fileData.length > 0) {
					ByteArrayInputStream ba = new ByteArrayInputStream( socio.getImFoto() );
					BufferedImage imagem = ImageIO.read( ba );
					int imageWidth = imagem.getWidth();
					int imageHeight = imagem.getHeight();
					if (imageWidth > 100 || imageHeight > 100 ) {
						messages.add(Constantes.TIPO_MENSAGEM_ERROR, new ActionMessage("msg.erro.imagem.dimensao"));
						return abrirSociosShowSocio(mapping, form, request, response);
					}
				}
			}
		    if (socio.isStatusInsert()) {
		    	socioDAO.insert( socio );
		    	messages.add(Constantes.TIPO_MENSAGEM_SUCESSO, new ActionMessage("msg.sucesso.salvar.registro"));
		    } else if (socio.isStatusUpdate()) {
		    	socioDAO.update( socio );
		    	messages.add(Constantes.TIPO_MENSAGEM_SUCESSO, new ActionMessage("msg.sucesso.salvar.registro"));
		    } else  if (socio.isStatusDelete()) {
		    	socioDAO.delete( socio );
		    	messages.add(Constantes.TIPO_MENSAGEM_SUCESSO, new ActionMessage("msg.sucesso.excluir.registro"));
		    }
		} catch (Exception e) {
			e.printStackTrace();
			messages.add(Constantes.TIPO_MENSAGEM_ERROR, new ActionMessage("msg.erro.registro.encontrado"));
		} finally {
			if (!messages.isEmpty()) {
				saveMessages(request, messages);				
	        }			
			
		}
		return abrirSociosShowSocio(mapping, socioForm, request, response);
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward excluirCadSocio(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		/*
		SocioForm socioForm = (SocioForm) form;
		SocioDAO socioDAO = (SocioDAO) DAOFactory.getDAO("SocioDAO");
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return mapping.findForward(FWD_CADASTRAR);
	}	
	
	
	
}
