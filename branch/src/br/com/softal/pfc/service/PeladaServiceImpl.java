package br.com.softal.pfc.service;

import org.apache.struts.action.ActionMessage;

import br.com.softal.pfc.Constantes;
import br.com.softal.pfc.Galeria;
import br.com.softal.pfc.Noticia;
import br.com.softal.pfc.Partida;
import br.com.softal.pfc.DAO.ClassificacaoDAO;
import br.com.softal.pfc.DAO.DAOFactory;
import br.com.softal.pfc.DAO.PartidaDAO;
import br.com.softal.pfc.DAO.SociopartidaDAO;
import br.com.softal.pfc.exception.PartidaException;

public class PeladaServiceImpl implements PeladaService {
	
	public void salvarGaleria(Galeria galeria) throws Exception {
		if (galeria.isStatusInsert()) {
        	DAOFactory.getGaleriaDAO().insert( galeria );
	    } else if (galeria.isStatusUpdate()) {
	    	DAOFactory.getGaleriaDAO().update( galeria );
	    } else  if (galeria.isStatusDelete()) {
	    	
	    	DAOFactory.getFotogaleriaDAO().deleteAllFotogaleria(galeria);
	    	DAOFactory.getGaleriaDAO().delete( galeria );
	    }
	}
	
	public void salvarNoticia(Noticia noticia) throws Exception {
		if (noticia.isStatusInsert()) {
			DAOFactory.getNoticiaDAO().insert( noticia );
			DAOFactory.getNoticiaDAO().removeFlagPopupOutrasNoticias( noticia );
		} else if (noticia.isStatusUpdate()) {
			DAOFactory.getNoticiaDAO().update( noticia );
			DAOFactory.getNoticiaDAO().removeFlagPopupOutrasNoticias( noticia );
		} else if (noticia.isStatusDelete()) {
			DAOFactory.getNoticiaDAO().delete( noticia );
		}
	}
	
	public void reabrirCadPartida(Partida partida) throws Exception {
		partida.setFlEmpate( 0 );
		if (partida.getNuGolperdedor().intValue() == partida.getNuGolvencedor().intValue()) {
			partida.setFlEmpate( 1 );
		}
		PartidaDAO partidaDAO = DAOFactory.getPartidaDAO();
		ClassificacaoDAO classificacaoDAO = DAOFactory.getClassificacaoDAO();
		try {
			if (!partidaDAO.partidaPosteriorEncerrada( partida.getPartidaPK().getCdPartida() )) {
				throw new PartidaException("msg.erro.encerrar.partida.posterior.nao.encerrada");
			}
			//-- Deleta classificacao da partida selecionada
			classificacaoDAO.excluirClassificacaoPartida(partida.getPartidaPK().getCdPartida());
			//-- Encerra a partida		
			partida.setFlConcluida( 0 );
			partidaDAO.update( partida );
		} catch (Exception e) {
			e.printStackTrace();
			throw new PartidaException("msg.erro.reabrir.partida");
		}		
	}
	
	public void encerrarCadPartida(Partida partida) throws Exception {
		partida.setFlEmpate( 0 );
		if (partida.getNuGolperdedor().intValue() == partida.getNuGolvencedor().intValue()) {
			partida.setFlEmpate( 1 );
		}
		PartidaDAO partidaDAO = DAOFactory.getPartidaDAO();
		SociopartidaDAO sociopartidaDAO = DAOFactory.getSociopartidaDAO();
		ClassificacaoDAO classificacaoDAO = DAOFactory.getClassificacaoDAO();
		try {
			if (!sociopartidaDAO.existeParticipantes( partida.getPartidaPK().getCdPartida() )) {
				//messages.add(Constantes.TIPO_MENSAGEM_ERROR, new ActionMessage("msg.erro.encerrar.partida.socios.nao.informado"));
				//return editarCadPartida(mapping, form, request, response);
				throw new PartidaException("msg.erro.encerrar.partida.socios.nao.informado");
			}
			if (!partidaDAO.partidaAnteriorEncerrada( partida.getPartidaPK().getCdPartida() )) {
				//messages.add(Constantes.TIPO_MENSAGEM_ERROR, new ActionMessage("msg.erro.encerrar.partida.anterior.nao.encerrada"));
				//return editarCadPartida(mapping, form, request, response);
				throw new PartidaException("msg.erro.encerrar.partida.anterior.nao.encerrada");
			}
			
			//-- Deleta classificacao da partida selecionada
			classificacaoDAO.excluirClassificacaoPartida(partida.getPartidaPK().getCdPartida());
			
			//-- Gera o Ranking para a Partida do Quadrimestre Atual
			classificacaoDAO.encerrarPartidaQuadrimestreAtual( partida );
			
			//-- Gera o Ranking para a Partida do Quadrimestre Anual
			classificacaoDAO.encerrarPartidaAnual( partida );
						
			//-- Encerra a partida		
			partida.setFlConcluida( 1 );
			partidaDAO.update( partida );
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void reabrirPartidasate(Integer cdPartida) throws Exception {
		Partida ultimaPartida = DAOFactory.getPartidaDAO().getUltimapartida();
		for (int i = ultimaPartida.getPartidaPK().getCdPartida(); i >= cdPartida; i--) {
			Partida partidaAtual = (Partida) DAOFactory.getPartidaDAO().findPartida( i );
			if (partidaAtual != null) {
				this.reabrirCadPartida( partidaAtual );
			}
		}
	}
	
	public void encerrarPartidasate(Integer cdPartida) throws Exception {
		Partida ultimaPartidaEncerrada = (Partida) DAOFactory.getPartidaDAO().findUltimapartida();
		Integer cdUltimaPartidaEncerra = ultimaPartidaEncerrada.getPartidaPK().getCdPartida();
		if (cdPartida > cdUltimaPartidaEncerra) {
			for (int i = (cdUltimaPartidaEncerra + 1); i <= cdPartida; i++) {
				Partida partidaAtual = (Partida) DAOFactory.getPartidaDAO().findPartida( i );
				if (partidaAtual != null) {
					this.encerrarCadPartida( partidaAtual );
				}
			}
		}
		
	}
	
	public void encerrarTodasAsPartidas(Integer cdPartida) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
