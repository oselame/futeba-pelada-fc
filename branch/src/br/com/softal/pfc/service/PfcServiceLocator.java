package br.com.softal.pfc.service;

public class PfcServiceLocator {
	
	private static PfcServiceLocator instance = null;
	
	private PfcServiceLocator() {
		// TODO Auto-generated constructor stub
	}
	
	public static PfcServiceLocator getInstance() {
		if (instance == null) {
			instance = new PfcServiceLocator();
		}
		return instance;
	}
	
	public PeladaService getPeladaService() {
		return new PeladaServiceImpl();
	}

}
