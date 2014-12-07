package br.com.softal.pfc.struts.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.com.softal.pfc.Classificacao;
import br.com.softal.pfc.Constantes;
import br.com.softal.pfc.Convocacao;
import br.com.softal.pfc.Partida;
import br.com.softal.pfc.Sociopartida;
import br.com.softal.pfc.DAO.DAOFactory;
import br.com.softal.pfc.DAO.PartidaDAO;
import br.com.softal.pfc.DAO.SociopartidaDAO;
import br.com.softal.pfc.util.Banner;
import br.com.softal.pfc.util.Util;

public class HomeAction extends Action {

	@SuppressWarnings("unchecked")
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		ActionMessages messages = new ActionMessages();
		try {
			@SuppressWarnings("unused")
			String path = mapping.getPath();
			
			//banners
			Banner.getBanners( request );	
			Banner.getPatrocinios( request );	
			
			Util.carregaTimes(request);
			
			//if (request.getSession().getAttribute(Constantes.SESSION_LISTA_QUADRIMESTRE_ATUAL) == null) {
				//-- Quadrimestre atual
				List<Classificacao> listaClassQuadriAtual = null;
				List<Classificacao> listaQuadriAtual = null;
				try {
					int nuAno = Util.getAnoQuadrimestreAtual();
					int cdQuadrimestre = Integer.valueOf(Util.getQuadrimestreAtual(false));
					Classificacao classificacao = new Classificacao();
					classificacao.setCdQuadrimestre( cdQuadrimestre );
					classificacao.setNuAno( Integer.valueOf( nuAno ) );
					listaClassQuadriAtual = DAOFactory.getClassificacaoDAO().findRanking( classificacao );
					if (listaClassQuadriAtual.size() == 0) { //-- Nao tem ranking para o quadrimestre atual, mostra o anterior
						if (cdQuadrimestre > 1) {
							cdQuadrimestre--;
						} else {
							cdQuadrimestre = 3;
							nuAno--;							
						}
						classificacao.setCdQuadrimestre( cdQuadrimestre );
						classificacao.setNuAno( nuAno );
						listaClassQuadriAtual = DAOFactory.getClassificacaoDAO().findRanking( classificacao );	
					}
					listaQuadriAtual = listaClassQuadriAtual.subList(0, 5);
					request.getSession().setAttribute(Constantes.SESSION_RANKINGATUAL, listaQuadriAtual); //ranking
					request.getSession().setAttribute(Constantes.SESSION_QUADRIMESTRE, cdQuadrimestre); //quadrimestre
					request.getSession().setAttribute(Constantes.SESSION_LISTA_QUADRIMESTRE_ATUAL, true);
				} catch (Exception e) {
					request.getSession().setAttribute(Constantes.SESSION_RANKINGATUAL, new ArrayList<Classificacao>()); //ranking
					request.getSession().setAttribute(Constantes.SESSION_QUADRIMESTRE, 1); //quadrimestre
				}
			//}
			
			//--Quadrimestre anual
			//if (request.getSession().getAttribute(Constantes.SESSION_LISTA_QUADRIMESTRE_ANUAL) == null) {
				try {
					String nuAno = Util.getAnoQuadrimestreAtual().toString();
					Classificacao classificacao = new Classificacao();
					classificacao.setCdQuadrimestre( Integer.valueOf( 4 ) );
					classificacao.setNuAno( Integer.valueOf( nuAno ) );
					List<Classificacao> listaClass = DAOFactory.getClassificacaoDAO().findRanking( classificacao );
					List<Classificacao> lista = listaClass.subList(0, 5);
					request.getSession().setAttribute(Constantes.SESSION_RANKINGANUAL, lista); //ranking
					request.getSession().setAttribute(Constantes.SESSION_LISTA_QUADRIMESTRE_ANUAL,true);
				} catch (Exception e) {
					e.printStackTrace();
					request.getSession().setAttribute(Constantes.SESSION_RANKINGANUAL, new ArrayList<Classificacao>()); //ranking
				}
			//}
			
			request.setAttribute(Constantes.SESSION_CONVOCACAO, new Convocacao()); //--convocacao
			
			Partida partida = null;
			if (request.getSession().getAttribute(Constantes.SESSION_PARTIDA) == null) {
				PartidaDAO partidaDAO = DAOFactory.getPartidaDAO();
				partida = (Partida) partidaDAO.findUltimapartida();
				request.getSession().setAttribute(Constantes.SESSION_PARTIDA, partida); // jogo
			} 
			partida = (Partida) request.getSession().getAttribute(Constantes.SESSION_PARTIDA);
			
			if (request.getSession().getAttribute(Constantes.SESSION_JOGO) == null) {
				SociopartidaDAO sociopartidaDAO = DAOFactory.getSociopartidaDAO();
				Sociopartida sociopartida = new Sociopartida();
				sociopartida.getSociopartidaPK().setCdPartida( partida.getPartidaPK().getCdPartida() );
				List lista = sociopartidaDAO.findAll( sociopartida );
				request.getSession().setAttribute(Constantes.SESSION_JOGO, lista); // jogo
			}
			
			Util.carregaUltimasnoticias(request);
			Util.carregaUltimoseventos(request);
			Util.carregaDescritivoSite(request);
			Util.carregaAniversariantes(request);
			
			if (request.getSession().getAttribute(Constantes.SESSION_NMAPELIDO) == null) {
				request.getSession().setAttribute(Constantes.SESSION_NMAPELIDO, "Visitante");
			}
			//
			return mapping.findForward("home2");
		} catch (Exception e) {
			e.printStackTrace();
			messages.add(Constantes.TIPO_MENSAGEM_ERROR, new ActionMessage("msg.erro.partida.anterior.nao.encerrada"));
			return mapping.findForward("erro");
		} finally {
			if (!messages.isEmpty()) {
				saveMessages(request, messages);				
	        }
		}
	}

}







