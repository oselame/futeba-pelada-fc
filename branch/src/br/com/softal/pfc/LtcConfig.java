package br.com.softal.pfc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class LtcConfig  {
	
	private static Properties props;
	private static InputStream is;
	private static ClassLoader classLoader;
	
	public LtcConfig() {
	}
	
	static {
		try {
			classLoader = Thread.currentThread().getContextClassLoader();  
			//classLoader = getClass().getClassLoader().getResourceAsStream("pfc.properties"); 
			is = classLoader.getResourceAsStream("pfc.properties");  
			props = new java.util.Properties();
			props.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String loadProperties(String property) {
		return props.getProperty(property);
	}
}
