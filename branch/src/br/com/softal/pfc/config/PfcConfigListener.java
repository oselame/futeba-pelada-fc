package br.com.softal.pfc.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class PfcConfigListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void contextInitialized(ServletContextEvent arg0) {
		PfcConfig.init();
	}
}
