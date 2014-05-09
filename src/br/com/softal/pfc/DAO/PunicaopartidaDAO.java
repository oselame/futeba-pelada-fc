package br.com.softal.pfc.DAO;

import java.util.List;

import br.com.softal.pfc.Punicaopartida;

 
public interface PunicaopartidaDAO extends DAO {
	
	public List<Punicaopartida> findAllJogodoresPartida(Integer cdPartida) throws DAOException;
	public void salvaPunicoes(List<Punicaopartida> punicoes) throws DAOException;
	
}
