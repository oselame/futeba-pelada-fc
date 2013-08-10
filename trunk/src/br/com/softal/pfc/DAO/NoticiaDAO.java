package br.com.softal.pfc.DAO;

import java.util.List;

import br.com.softal.pfc.Noticia;
 
public interface NoticiaDAO extends DAO {
 
	List<Noticia> findAllNoticiasEventosatuais(Integer tipo) throws DAOException;
	void removeFlagPopupOutrasNoticias(Noticia noticia) throws DAOException;
	
}
