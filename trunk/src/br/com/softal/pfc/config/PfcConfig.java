package br.com.softal.pfc.config;

import java.util.List;
import java.util.Properties;

import br.com.softal.pfc.Constantes;

public class PfcConfig extends Config {

	private static final String NOME_ARQUIVO_PROPERTIES = "pfc-config.properties";
	private static final String NOME_PROPERTY_SYSTEM = "pfc-config";

	public static void init() {
		init(NOME_PROPERTY_SYSTEM, NOME_ARQUIVO_PROPERTIES);
	}

	public static String getProperty(String key) {
		return getProperty(key, NOME_ARQUIVO_PROPERTIES, NOME_PROPERTY_SYSTEM);
	}

	/**
	 * Retorna o valor da propriedade como um {@code Long}. Caso valor
	 * parametrizado não seja um número retorna {@code null}.
	 *
	 * @param key Chave da propriedade.
	 * @return Valor parametrizado na chave ou {@code null}.
	 */
	public static Long getPropertyLong(String key) {
		try {
			String property = getProperty(key);
			return Long.parseLong(property);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static Properties getProperties() {
		return getProperties(NOME_ARQUIVO_PROPERTIES, NOME_PROPERTY_SYSTEM);
	}

/*	public static String getPropertyCriptografia(String key) {
		return getPropertyCriptografada(key, NOME_ARQUIVO_PROPERTIES, NOME_PROPERTY_SYSTEM);
	}*/

	public static List<String> getPropertyList(String key) {
		return getPropertyList(key, NOME_ARQUIVO_PROPERTIES, NOME_PROPERTY_SYSTEM);
	}
}
