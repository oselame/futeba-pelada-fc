package br.com.softal.pfc.DAO;

import java.util.List;

import br.com.softal.pfc.Partida;
 
public interface ClassificacaoDAO extends DAO {
	
	public void encerrarPartidaQuadrimestreAtual(Partida partida) throws DAOException;
	public void encerrarPartidaAnual(Partida partida) throws DAOException;
	@SuppressWarnings("unchecked")
	public List findRanking(Object obj) throws DAOException;
	public void excluirClassificacaoPartida(Integer cdPartida) throws DAOException;
	public void excluirClassificacoes() throws DAOException;
 
}
