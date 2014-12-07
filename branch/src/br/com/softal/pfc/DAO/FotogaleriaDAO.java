package br.com.softal.pfc.DAO;

import br.com.softal.pfc.Galeria;
 
public interface FotogaleriaDAO extends DAO {
	
	void deleteAllFotogaleria(Galeria galeria) throws DAOException;
	
}
