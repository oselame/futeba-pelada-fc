package br.com.softal.pfc;

import java.util.List;

import br.com.softal.pfc.DAO.DAOException;
import br.com.softal.pfc.DAO.DAOFactory;
import br.com.softal.pfc.dto.EmailAtrasoDto;
import br.com.softal.pfc.exception.PartidaException;


public class PfcBusinessDelegate {
	
	public static Boolean existeAtrasadosOuCartoes(Integer cdPartida) {
		return DAOFactory.getSociopartidaDAO().existeAtrasadosOuCartoes(cdPartida);
	}
	
	public static List<EmailAtrasoDto> findAllSociosAtrasadosComCartao(Integer cdPartida) {
		return DAOFactory.getSociopartidaDAO().findAllSociosAtrasadosComCartao(cdPartida);
	}
	
	public static void deleteSociospartida(Integer cdPartida) {
		DAOFactory.getSociopartidaDAO().deleteSociospartida(cdPartida);
	}
	
	public static void encerrarPartida(Partida partida) throws Exception {
		try {
			DAOFactory.getPartidaDAO().encerrarPartida(partida);
		} catch (DAOException e) {
			throw new PartidaException(e.getMessage());
		}
		
	}
	
}
