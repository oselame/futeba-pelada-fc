package br.com.softal.pfc.DAO;

import java.util.List;


public interface DAO  {
	
	void insert(Object obj) throws DAOException;
	void delete(Object obj) throws DAOException;
	void update(Object obj) throws DAOException;
		
	Object findByPrimaryKey(Object obj) throws DAOException;
	List<?> findAll() throws DAOException;
	List<?> findAll(Object obj) throws DAOException;
	
}

