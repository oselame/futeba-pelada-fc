package br.com.softal.pfc.DAO;

import br.com.softal.pfc.Timecamisa;
 
public interface TimecamisaDAO extends DAO {
	
	public Timecamisa findTimecamisa(Integer cdTimecamisa) throws DAOException;
 
}
