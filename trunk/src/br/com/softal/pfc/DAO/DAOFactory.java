package br.com.softal.pfc.DAO;

import java.util.HashMap;
import java.util.ResourceBundle;


public class DAOFactory {
	 
	private static ResourceBundle rb = ResourceBundle.getBundle("dao");
	private static HashMap<String, Object> daoCache = new HashMap<String, Object>();
	private static DAOFactory instancia = null;
	
	@SuppressWarnings("unchecked")
	public static DAO getDAO( String name ) {
		DAOFactory daoFactory = getInstance();
		Class estaClasse = daoFactory.getClass();
		ClassLoader loader = estaClasse.getClassLoader();
		try {
			
			if( daoCache.containsKey( name ) ) {
				return (DAO) daoCache.get( name );
				
			} else {
				String className = rb.getString( name );
				Object dao = loader.loadClass( className ).newInstance();
				daoCache.put( name, dao );
				return (DAO) dao;
			}
		} catch(Exception ex) {
			throw new DAOException("DAO não encontrado: " + ex.getMessage());
		}
	}
	
	private static DAOFactory getInstance() {
		if( instancia == null )
			instancia = new DAOFactory();
		return instancia;
	}		
	
	private DAOFactory() {		
	}
	
	public static SocioDAO getSocioDAO() {
		return (SocioDAO) getDAO("SocioDAO");
	}
	
	public static PartidaDAO getPartidaDAO() {
		return (PartidaDAO) getDAO("PartidaDAO");
	}

	public static SociopartidaDAO getSociopartidaDAO() {
		return (SociopartidaDAO) getDAO("SociopartidaDAO");
	}
	
	public static TimecamisaDAO getTimecamisaDAO() {
		return (TimecamisaDAO) getDAO("TimecamisaDAO");
	}

	public static QuadrimestreDAO getQuadrimestreDAO() {
		return (QuadrimestreDAO) getDAO("QuadrimestreDAO");
	}
	
	public static ClassificacaoDAO getClassificacaoDAO() {
		return (ClassificacaoDAO) getDAO("ClassificacaoDAO");
	}
	
	public static NoticiaDAO getNoticiaDAO() {
		return (NoticiaDAO) getDAO("NoticiaDAO");
	}
	
	public static AnexoDAO getAnexoDAO() {
		return (AnexoDAO) getDAO("AnexoDAO");
	}
	
	public static ConfiguracaoDAO getConfiguracaoDAO() {
		return (ConfiguracaoDAO) getDAO("ConfiguracaoDAO");
	}

	public static GaleriaDAO getGaleriaDAO() {
		return (GaleriaDAO) getDAO("GaleriaDAO");
	}
	
	public static FotogaleriaDAO getFotogaleriaDAO() {
		return (FotogaleriaDAO) getDAO("FotogaleriaDAO");
	}
	
}

