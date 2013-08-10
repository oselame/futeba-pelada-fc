package br.com.softal.pfc.service;

import br.com.softal.pfc.Galeria;
import br.com.softal.pfc.Noticia;
import br.com.softal.pfc.DAO.DAOFactory;

public class PeladaServiceImpl implements PeladaService {
	
	public void salvarGaleria(Galeria galeria) throws Exception {
		if (galeria.isStatusInsert()) {
        	DAOFactory.getGaleriaDAO().insert( galeria );
	    } else if (galeria.isStatusUpdate()) {
	    	DAOFactory.getGaleriaDAO().update( galeria );
	    } else  if (galeria.isStatusDelete()) {
	    	
	    	DAOFactory.getFotogaleriaDAO().deleteAllFotogaleria(galeria);
	    	DAOFactory.getGaleriaDAO().delete( galeria );
	    	
	    }
	}
	
	public void salvarNoticia(Noticia noticia) throws Exception {
		if (noticia.isStatusInsert()) {
			DAOFactory.getNoticiaDAO().insert( noticia );
			DAOFactory.getNoticiaDAO().removeFlagPopupOutrasNoticias( noticia );
		} else if (noticia.isStatusUpdate()) {
			DAOFactory.getNoticiaDAO().update( noticia );
			DAOFactory.getNoticiaDAO().removeFlagPopupOutrasNoticias( noticia );
		} else if (noticia.isStatusDelete()) {
			DAOFactory.getNoticiaDAO().delete( noticia );
		}
		
	}

}
