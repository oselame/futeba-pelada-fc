package br.com.softal.pfc.struts.action;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.com.softal.pfc.Configuracao;
import br.com.softal.pfc.Constantes;
import br.com.softal.pfc.Partida;
import br.com.softal.pfc.Quadrimestre;
import br.com.softal.pfc.DAO.DAOFactory;
import br.com.softal.pfc.util.Util;
 
public class QuadrimestreAction extends PfcAction {
 
	@SuppressWarnings("unchecked")
	public ActionForward abrirConQuadrimestre(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QuadrimestreForm quadrimestreForm = (QuadrimestreForm) form;
		List lista = DAOFactory.getQuadrimestreDAO().findAll();
		quadrimestreForm.setRows( lista );
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(FWD_CONSULTAR);
	}
	
	public ActionForward abrirCadQuadrimestre(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QuadrimestreForm quadrimestreForm = (QuadrimestreForm) form;
		Quadrimestre quadrimestre = (Quadrimestre) quadrimestreForm.getEntidade();
		try {
			Integer ultimoAnocadastrado = DAOFactory.getQuadrimestreDAO().findUltimoAnoCadastrado();
			quadrimestre.getQuadrimestrePK().setNuAno( ultimoAnocadastrado + 1 );
			populaQuadrimestresNovoRegistro(form, ultimoAnocadastrado);
			quadrimestreForm.getEntidade().setStatusInsert();
			quadrimestreForm.setPodeAlterar( true );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(FWD_CADASTRAR);
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward editarCadQuadrimestre(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QuadrimestreForm quadrimestreForm = (QuadrimestreForm) form;
		Quadrimestre quadrimestre = (Quadrimestre) quadrimestreForm.getEntidade();
		try {
			Integer nuAno = 0;
			if (request.getParameter("nuAno") != null) {
				nuAno = Integer.valueOf(request.getParameter("nuAno").toString());
				quadrimestre.getQuadrimestrePK().setNuAno( nuAno );
			}
			List<Quadrimestre> quadrimestres = (List<Quadrimestre>) DAOFactory.getQuadrimestreDAO().findAll( quadrimestre );
			quadrimestreForm.setQuadrimestre1( quadrimestres.get(0) );
			quadrimestreForm.setQuadrimestre2( quadrimestres.get(1) );
			quadrimestreForm.setQuadrimestre3( quadrimestres.get(2) );
			quadrimestreForm.setQuadrimestre4( quadrimestres.get(3) );
			quadrimestreForm.getEntidade().setStatusUpdate();
			
			List<Partida> partidas = DAOFactory.getPartidaDAO().findPartidasquadrimestre(quadrimestre.getQuadrimestrePK().getNuAno(), null);
			quadrimestreForm.setPodeAlterar( partidas.size() == 0 );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(FWD_CADASTRAR);
	}
	
	public ActionForward salvarCadQuadrimestre(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QuadrimestreForm quadrimestreForm = (QuadrimestreForm) form;
		Quadrimestre quadrimestre = (Quadrimestre) quadrimestreForm.getEntidade();
		ActionMessages messages = new ActionMessages();
		try {
			if (quadrimestre.isStatusInsert()) {
				// Quadrimestre 1
				Quadrimestre quadri = quadrimestreForm.getQuadrimestre1();
				quadri.getQuadrimestrePK().setNuAno( quadrimestre.getQuadrimestrePK().getNuAno() );
				quadri.getQuadrimestrePK().setCdQuadrimestre( 1 );
				quadri.setCdSociocampeao(null);
				quadri.setNuJogos(null);
				quadri.setFlAnual( 0 );
				DAOFactory.getQuadrimestreDAO().insert( quadri );
				// Quadrimestre 2
				quadri = quadrimestreForm.getQuadrimestre2();
				quadri.getQuadrimestrePK().setNuAno( quadrimestre.getQuadrimestrePK().getNuAno() );
				quadri.getQuadrimestrePK().setCdQuadrimestre( 2 );
				quadri.setCdSociocampeao(null);
				quadri.setNuJogos(null);
				quadri.setFlAnual( 0 );
				DAOFactory.getQuadrimestreDAO().insert( quadri );
				// Quadrimestre 3
				quadri = quadrimestreForm.getQuadrimestre3();
				quadri.getQuadrimestrePK().setNuAno( quadrimestre.getQuadrimestrePK().getNuAno() );
				quadri.getQuadrimestrePK().setCdQuadrimestre( 3 );
				quadri.setCdSociocampeao(null);
				quadri.setNuJogos(null);
				quadri.setFlAnual( 0 );
				DAOFactory.getQuadrimestreDAO().insert( quadri );
				// Quadrimestre 4
				quadri = quadrimestreForm.getQuadrimestre4();
				quadri.getQuadrimestrePK().setNuAno( quadrimestre.getQuadrimestrePK().getNuAno() );
				quadri.getQuadrimestrePK().setCdQuadrimestre( 4 );
				quadri.setDtInicio( quadrimestreForm.getQuadrimestre1().getDtInicio() );
				quadri.setDtFim( quadrimestreForm.getQuadrimestre3().getDtFim() );
				quadri.setCdSociocampeao(null);
				quadri.setNuJogos(null);
				quadri.setFlAnual( 1 );
				DAOFactory.getQuadrimestreDAO().insert( quadri );
				messages.add(Constantes.TIPO_MENSAGEM_SUCESSO, new ActionMessage("msg.sucesso.salvar.registro"));
			} else if (quadrimestre.isStatusUpdate()) {
				// Quadrimestre 1
				Quadrimestre quadri = quadrimestreForm.getQuadrimestre1();
				quadri.getQuadrimestrePK().setNuAno( quadrimestre.getQuadrimestrePK().getNuAno() );
				quadri.getQuadrimestrePK().setCdQuadrimestre( 1 );
				quadri.setFlAnual( 0 );
				DAOFactory.getQuadrimestreDAO().update( quadri );
				// Quadrimestre 2
				quadri = quadrimestreForm.getQuadrimestre2();
				quadri.getQuadrimestrePK().setNuAno( quadrimestre.getQuadrimestrePK().getNuAno() );
				quadri.getQuadrimestrePK().setCdQuadrimestre( 2 );
				quadri.setFlAnual( 0 );
				DAOFactory.getQuadrimestreDAO().update( quadri );
				// Quadrimestre 3
				quadri = quadrimestreForm.getQuadrimestre3();
				quadri.getQuadrimestrePK().setNuAno( quadrimestre.getQuadrimestrePK().getNuAno() );
				quadri.getQuadrimestrePK().setCdQuadrimestre( 3 );
				quadri.setFlAnual( 0 );
				DAOFactory.getQuadrimestreDAO().update( quadri );
				// Quadrimestre 3
				quadri = quadrimestreForm.getQuadrimestre4();
				quadri.getQuadrimestrePK().setNuAno( quadrimestre.getQuadrimestrePK().getNuAno() );
				quadri.getQuadrimestrePK().setCdQuadrimestre( 4 );
				quadri.setDtInicio( quadrimestreForm.getQuadrimestre1().getDtInicio() );
				quadri.setDtFim( quadrimestreForm.getQuadrimestre3().getDtFim() );				
				quadri.setFlAnual( 1 );
				DAOFactory.getQuadrimestreDAO().update( quadri );
				messages.add(Constantes.TIPO_MENSAGEM_SUCESSO, new ActionMessage("msg.sucesso.salvar.registro"));
			}		
		} catch (Exception e) {
			e.printStackTrace();
			messages.add(Constantes.TIPO_MENSAGEM_ERROR, new ActionMessage("msg.erro.registro.encontrado"));
		} finally {
			if (!messages.isEmpty()) {
				saveMessages(request, messages);				
	        }			
		}
		return editarCadQuadrimestre(mapping, form, request, response);
	}
	
	private void  populaQuadrimestresNovoRegistro(ActionForm form, Integer ultimoAnocadastrado) {
		QuadrimestreForm quadrimestreForm = (QuadrimestreForm) form;
		for (int i = 1;i < 4;i++) {
			Quadrimestre quad = new Quadrimestre();
			quad.getQuadrimestrePK().setNuAno( ultimoAnocadastrado );
			quad.getQuadrimestrePK().setCdQuadrimestre( i );
			Quadrimestre resultado = (Quadrimestre) DAOFactory.getQuadrimestreDAO().findAll( quad ).get(0);
			Calendar cInicio = Calendar.getInstance();
			cInicio.setTime( resultado.getDtInicio() );
			Calendar cFim = Calendar.getInstance();
			cFim.setTime( resultado.getDtFim() );
			if (i == 1) {
				cInicio.set(Calendar.YEAR, ultimoAnocadastrado);
				quadrimestreForm.getQuadrimestre1().setDtInicio( cInicio.getTime() );
				quadrimestreForm.getQuadrimestre4().setDtInicio( cInicio.getTime() );
				cFim.set(Calendar.YEAR, ultimoAnocadastrado + 1);
				quadrimestreForm.getQuadrimestre1().setDtFim( cFim.getTime() );
			} else if (i == 2) {
				cInicio.set(Calendar.YEAR, ultimoAnocadastrado + 1);
				quadrimestreForm.getQuadrimestre2().setDtInicio( cInicio.getTime() );
				cFim.set(Calendar.YEAR, ultimoAnocadastrado + 1);
				quadrimestreForm.getQuadrimestre2().setDtFim( cFim.getTime() );
			} else if (i == 3) {
				cInicio.set(Calendar.YEAR, ultimoAnocadastrado + 1);
				quadrimestreForm.getQuadrimestre3().setDtInicio( cInicio.getTime() );
				cFim.set(Calendar.YEAR, ultimoAnocadastrado + 1);
				quadrimestreForm.getQuadrimestre3().setDtFim( cFim.getTime() );
				quadrimestreForm.getQuadrimestre4().setDtFim( cFim.getTime() );
			}
		}
		//-- populando dados
	}
	
	public ActionForward abrirConGaleriacampeoes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QuadrimestreForm quadrimestreForm = (QuadrimestreForm) form;
		List<Quadrimestre> lista = DAOFactory.getQuadrimestreDAO().findDadosCampeoes( Util.getAnoQuadrimestreAtual() );
		quadrimestreForm.setRows( lista );		
		
        Configuracao config = Util.carregaConfiguracao( Configuracao.TP_CONFIG_GALERIA_ANTERIOR );
        if (config.getConfiguracaoPK().getCdConfiguracao() != null) {
        	request.getSession().setAttribute(Constantes.SESSION_NOME_ARQUIVO_CAMPEOES_ANTERIORES.toString(), config.getVlConfiguracao().trim());
        }
        return mapping.findForward(FWD_CONSULTAR);
    }
	
	public ActionForward abrirConMaiorescampeoes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QuadrimestreForm quadrimestreForm = (QuadrimestreForm) form;
		List<Quadrimestre> lista = DAOFactory.getQuadrimestreDAO().findAllMaiorescampeoes();
		quadrimestreForm.setRows( lista );	
        return mapping.findForward(FWD_CONSULTAR);
    }
	
}
