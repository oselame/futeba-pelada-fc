package br.com.softal.pfc.DAO;

import java.util.List;
import java.util.Map;

import br.com.softal.pfc.Punicaopartida;

 
public interface PunicaopartidaDAO extends DAO {
	
	public List<Punicaopartida> findAllJogodoresPartida(Integer cdPartida) throws DAOException;
	public void salvaPunicoes(List<Punicaopartida> punicoes) throws DAOException;
	public Map<Integer, Punicaopartida> findAllMapJogodoresPartida(Integer cdPartida) throws DAOException;
	
}
