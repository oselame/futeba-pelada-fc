package br.com.softal.pfc.DAO;

import java.util.Date;
import java.util.List;

import br.com.softal.pfc.Codigodescricao;
import br.com.softal.pfc.Quadrimestre;
 
public interface QuadrimestreDAO extends DAO {

	public Object findQuadrimestrePorData(Date data, Integer flAnual) throws DAOException;
	
	public List<Codigodescricao> findAnosQuadrimestres() throws DAOException;
	
	public Object getDadosQuadrimestre(Integer nuAno, Integer cdQuadrimestre) throws DAOException;
	
	public Integer findUltimoAnoCadastrado() throws DAOException;
	
	public List<Quadrimestre> findDadosCampeoes(Integer nuAno)  throws DAOException;
	
	List<Quadrimestre> findAllMaiorescampeoes() throws DAOException;
	
}
