package br.com.softal.pfc.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.softal.pfc.Classificacao;
import br.com.softal.pfc.Codigodescricao;
import br.com.softal.pfc.Constantes;
import br.com.softal.pfc.Partida;
import br.com.softal.pfc.Quadrimestre;
import br.com.softal.pfc.DAO.DAOFactory;
import br.com.softal.pfc.exception.QuadrimestreException;
import br.com.softal.pfc.util.Util;
 
 
public class ClassificacaoAction extends PfcAction {
	
	@SuppressWarnings("unchecked")
	public ActionForward abrirRankingQuadrimestre(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String nuAno = Util.getAnoQuadrimestreAtual().toString();
			String cdQuadrimestre = Util.getQuadrimestreAtual(false);
			ClassificacaoForm classificacaoForm = (ClassificacaoForm) form;
			Classificacao classificacao = new Classificacao();
			classificacao.setCdQuadrimestre( Integer.valueOf( cdQuadrimestre ) );
			classificacao.setNuAno( Integer.valueOf( nuAno ) );
			try {
				request.setAttribute(Constantes.SESSION_TITULO, "Ranking do " + cdQuadrimestre + "&ordm; Quadrimestre de " + nuAno);
				List lista = DAOFactory.getClassificacaoDAO().findRanking( classificacao );
				classificacaoForm.setRows( lista );
				
				Quadrimestre quadrimestre  = (Quadrimestre) DAOFactory.getQuadrimestreDAO().getDadosQuadrimestre(classificacao.getNuAno(), classificacao.getCdQuadrimestre()) ;
				request.setAttribute(Constantes.SESSION_PERIODO_QUADRIMESTRE, "Per&iacute;odo: " + 
						Util.dateToString(quadrimestre.getDtInicio()) + " a " + 
						Util.dateToString(quadrimestre.getDtFim()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (QuadrimestreException e) {
			salvarMensagem(request, e.getMessage(), Tipomensagem.ERRO);
			return mapping.findForward("error");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(FWD_CONSULTAR);
	}
	
	
	@SuppressWarnings("unchecked")
	public ActionForward abrirConRankingAno(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nuAno = Util.getAnoQuadrimestreAtual().toString();
		ClassificacaoForm classificacaoForm = (ClassificacaoForm) form;
		Classificacao classificacao = new Classificacao();
		classificacao.setNuAno( Integer.valueOf( nuAno ) );
		classificacao.setCdQuadrimestre( 4 );
		try {
			request.setAttribute(Constantes.SESSION_TITULO, "Ranking do ano de " + nuAno);
			List lista = DAOFactory.getClassificacaoDAO().findRanking( classificacao );
			classificacaoForm.setRows( lista );		
			
			Quadrimestre quadrimestre  = (Quadrimestre) DAOFactory.getQuadrimestreDAO().getDadosQuadrimestre(classificacao.getNuAno(), classificacao.getCdQuadrimestre()) ;
			request.setAttribute(Constantes.SESSION_PERIODO_QUADRIMESTRE, "Per&iacute;odo: " + 
					Util.dateToString(quadrimestre.getDtInicio()) + " a " + Util.dateToString(quadrimestre.getDtFim()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(FWD_CONSULTAR);
	}

	public ActionForward abrirRankingConsulta(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nuAno = Util.getAnoQuadrimestreAtual().toString();
		String quadrimestre = Util.getQuadrimestreAtual(false);
		ClassificacaoForm classificacaoForm = (ClassificacaoForm) form;
		Classificacao classificacao = (Classificacao) classificacaoForm.getEntidade(); //new Classificacao();
		classificacao.setCdQuadrimestre( Integer.valueOf( quadrimestre ) );
		classificacao.setNuAno( Integer.valueOf( nuAno ) );
		classificacao.setCdPartida(null);
		try {
			List<Codigodescricao> listaAnos = DAOFactory.getQuadrimestreDAO().findAnosQuadrimestres();
			request.getSession().setAttribute(Constantes.SESSION_LISTA_ANOS, listaAnos);
			
			List<Partida> listPartidasQuadrimestre = DAOFactory.getPartidaDAO().findPartidasquadrimestre(
					classificacao.getNuAno(),
					classificacao.getCdQuadrimestre());
			request.getSession().setAttribute(Constantes.SESSION_LISTA_PARTIDAS_QUADRIMESTRE, listPartidasQuadrimestre);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(FWD_CONSULTAR);
	}
	
	
	@SuppressWarnings("unchecked")
	public ActionForward abrirConRanking(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ClassificacaoForm classificacaoForm = (ClassificacaoForm) form;
		Classificacao classificacao = (Classificacao) classificacaoForm.getEntidade(); 
		try {
			request.setAttribute(Constantes.SESSION_TITULO, "Ranking do " + classificacao.getCdQuadrimestre() + "&ordm; Quadrimestre de " + classificacao.getNuAno());
			
			Quadrimestre quadrimestre  = (Quadrimestre) DAOFactory.getQuadrimestreDAO().getDadosQuadrimestre(classificacao.getNuAno(), classificacao.getCdQuadrimestre()) ;
			request.setAttribute(Constantes.SESSION_PERIODO_QUADRIMESTRE, "Per&iacute;odo: " + 
					Util.dateToString(quadrimestre.getDtInicio()) + " a " + Util.dateToString(quadrimestre.getDtFim()));
					
			List lista = DAOFactory.getClassificacaoDAO().findRanking( classificacao );
			classificacaoForm.setRows( lista );		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(FWD_CONSULTAR);
	}
	
}
