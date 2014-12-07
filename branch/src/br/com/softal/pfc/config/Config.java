package br.com.softal.pfc.config;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

/**
 * Classe responsável por carregar arquivos de configurações do tipo .properties.
 *
 * @author diogo
 */
public abstract class Config{

	private static Map<String, Properties> mapCache = new HashMap<String, Properties>();
	private static List<String> looks = new ArrayList<String>();

	/**
	 * Responsavel por iniciar o hash de propriedades colocando-o em cache.<br>
	 *
	 * @param nmPropertSystem
	 *            - Nome da propriedade do sistema com o diretorio do arquivo.
	 * @param nmArquivoConfig
	 *            - Nome do arquivo de propriedades.
	 */
	protected static void init(String nmPropertSystem, String nmArquivoConfig) {
		try {
			synchronized (looks) {
				if (!looks.contains(nmArquivoConfig)) {
					Properties propers = new Properties();
					String systemPropert = System.getProperties().getProperty(nmPropertSystem);
					if (systemPropert != null) {
						URL url = new URL("file:" + File.separator + systemPropert + File.separator + nmArquivoConfig);
						propers.load(url.openStream());
					} else {
						File confDir = new File(System.getProperty("catalina.home")+"//conf");
						File propFile = new File(confDir , nmArquivoConfig);
						propers.load(new FileInputStream(propFile));
					}
					

					mapCache.put(nmArquivoConfig, propers);
					looks.add(nmArquivoConfig);

					System.out.println("## Config ## - Carregadas [" + propers.size() + "] propriedades do arquivo [" + nmArquivoConfig + "], em: "
							+ new Date());
				}
			}
		} catch (Exception e) {
			throw new ConfigExeption("## Config ## [ERRO] - Não foi possivel carregar o arquivo de propiedades: " + nmArquivoConfig);
		}
	}

	/**
	 * Retorna a valor de uma propriedade em cache.<br>
	 *
	 * @param key
	 *            - Chave para a obtenção do valor
	 * @param nmPropertSystem
	 *            - Nome da propriedade do sistema com o diretorio do arquivo.
	 * @param nmArquivoConfig
	 *            - Nome do arquivo de propriedades.
	 * @return
	 */
	protected static String getProperty(String key, String nmArquivoConfig, String nmPropertSystem) {
		if (nmArquivoConfig != null && !nmArquivoConfig.equals("")) {
			if (looks.contains(nmArquivoConfig)) {
				return mapCache.get(nmArquivoConfig).getProperty(key);
			} else {
				throw new ConfigExeption("## Config ## - É nescessario carregar previamento o propriedades: " + nmArquivoConfig);
			}
		} else {
			throw new ConfigExeption("## Config ## - É nescessario informar o nome do arquivo de propriedades.");
		}
	}

	/**
	 * Retorna a valor de uma propriedade em cache de forma criptografada.<br>
	 *
	 * @param key
	 *            - Chave para a obtenção do valor
	 * @param nmPropertSystem
	 *            - Nome da propriedade do sistema com o diretorio do arquivo.
	 * @param nmArquivoConfig
	 *            - Nome do arquivo de propriedades.
	 * @return
	 */
	/*protected static String getPropertyCriptografada(String key, String nmArquivoConfig, String nmPropertSystem) {
		String property = getProperty(key, nmArquivoConfig, nmPropertSystem);
		if(StringUtils.isNotEmpty(property)){
			try {
				return Criptografia.criptografaPalavra(property);
			} catch (BadPaddingException e) {
				e.printStackTrace();
				throw new EpagriConfigExeption("## Config ## - Erro ao criptografar propriedade");
			} catch (IllegalBlockSizeException e) {
				e.printStackTrace();
				throw new EpagriConfigExeption("## Config ## - Erro ao criptografar propriedade");
			}
		}
		return property;
	}*/

	/**
	 * Retorna a valor de uma propriedade que estaja cadastrada como uma lista.<br>
	 *
	 * <b>Obs.:</b><br>
	 * <i>Para propriedades do tipo lista, utilizar o padrão de códigos separados por "," (Virgula).</i><br>
	 *
	 * @param key
	 *            - Chave para a obtenção do valor
	 * @param nmPropertSystem
	 *            - Nome da propriedade do sistema com o diretorio do arquivo.
	 * @param nmArquivoConfig
	 *            - Nome do arquivo de propriedades.
	 * @return
	 */
	protected static List<String> getPropertyList(String key, String nmArquivoConfig, String nmPropertSystem) {
		String property = getProperty(key, nmArquivoConfig, nmPropertSystem);
		if (StringUtils.isNotEmpty(property)) {
			return Arrays.asList(property.split(","));
		}
		return new ArrayList<String>();
	}

	/**
	 * Retorna o arquivo de propriedade em cache.<br>
	 *
	 * @param key
	 *            - Chave para a obtenção do valor
	 * @param nmPropertSystem
	 *            - Nome da propriedade do sistema com o diretorio do arquivo.
	 * @param nmArquivoConfig
	 *            - Nome do arquivo de propriedades.
	 * @return
	 */
	protected static Properties getProperties(String nmArquivoConfig, String nmPropertSystem) {
		if (nmArquivoConfig != null && !nmArquivoConfig.equals("")) {
			if (looks.contains(nmArquivoConfig)) {
				return mapCache.get(nmArquivoConfig);
			} else {
				throw new ConfigExeption("## Config ## - É nescessario carregar previamento o propriedades: " + nmArquivoConfig);
			}
		} else {
			throw new ConfigExeption("## Config ## - É nescessario informar o nome do arquivo de propriedades.");
		}
	}
}
