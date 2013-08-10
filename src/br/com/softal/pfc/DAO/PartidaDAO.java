package br.com.softal.pfc.DAO;

import java.util.List;

import br.com.softal.pfc.Codigodescricao;
import br.com.softal.pfc.Partida;
 
public interface PartidaDAO extends DAO {
	
	public List<Partida> findByAnoQuadrimestre(String ano, String quadrimestre) throws DAOException;
	public List<Codigodescricao> findAnos() throws DAOException;
	public void atualizaResultado(Integer cdPartida, Integer cdTimeVencedor, Integer nuGolsVencedor, Integer nuGolsPerdedor) throws DAOException;
	public Object findPartida(Integer cdPartida) throws DAOException;
	public Object findUltimapartida() throws DAOException;
	public Integer findTotalPartidas(Integer nuAno, Integer cdQuadrimestre) throws DAOException;
	public Boolean partidaAnteriorEncerrada(Integer cdPartida) throws DAOException;
	public Boolean partidaPosteriorEncerrada(Integer cdPartida) throws DAOException;
	public List<Partida> findPartidasquadrimestre(Integer nuAno, Integer cdQuadrimestre) throws DAOException;
	public Partida getPartidaAnterior(Integer cdPartidaAtual) throws DAOException;
	public Boolean isUltimapartidaencerrada() throws DAOException;
	public Partida getUltimapartida() throws DAOException;
	public void excluirPartida(Partida partida) throws DAOException;
	public Partida findPartidaanterior(Integer cdPartida) throws DAOException;
	public void encerrarPartida(Partida partida) throws DAOException;
	
}
