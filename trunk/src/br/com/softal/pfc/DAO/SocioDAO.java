package br.com.softal.pfc.DAO;

import java.util.List;

import br.com.softal.pfc.Socio;
 
public interface SocioDAO extends DAO {
 
	public List<Socio> findAniversariantes(Integer nuMesaniversario) throws DAOException;
	
	public List<Socio> findAniversariantes(Integer nuMesaniversario, Integer... tiposocio) throws DAOException;
	
	public Socio findLoginUsuario(String nmApelido, String deSenha) throws DAOException;
	
	public Integer getQuantidesocios() throws DAOException;
	
}
