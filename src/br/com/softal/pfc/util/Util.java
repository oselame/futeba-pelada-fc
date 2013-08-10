package br.com.softal.pfc.util;

import java.security.MessageDigest;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import br.com.softal.pfc.Configuracao;
import br.com.softal.pfc.Constantes;
import br.com.softal.pfc.Noticia;
import br.com.softal.pfc.Quadrimestre;
import br.com.softal.pfc.Socio;
import br.com.softal.pfc.Timecamisa;
import br.com.softal.pfc.DAO.DAOFactory;
import br.com.softal.pfc.DAO.QuadrimestreDAO;
import br.com.softal.pfc.DAO.TimecamisaDAO;
import br.com.softal.pfc.exception.QuadrimestreException;

/**
 * Classe utilizada para realiza&ccedil;&atilde;o de tarefas comuns.
 * 
 */
public class Util { // extends Funcoes {

	private static final String DATA_FORMATO_BANCO = "yyyy-MM-dd";
	private static final String DATA_FORMATO_PADRAO = "dd/MM/yyyy";
	public static final String DATA_FORMATO_CURTO = "dd/MM";

	private static final String PATTERN_SEM_CASAS = "#0";
	private static final String PATTERN_2_CASAS = "#,##0.00";
	private static final String PATTERN_3_CASAS = "#,##0.000";

	private static final int NUM_DIGITOS_AGENCIA = 4;
	private static final int NUM_DIGITOS_CONTA = 7;

	public Util() {
	}

	/**
	 * Arredonda o valor passado por parametro, para duas casas decimais.
	 * 
	 * @param value
	 *            o valor a ser arredondado
	 * @return double o resultado do valor arredondado
	 */
	public static double arredonda(double value) throws NumberFormatException {
		return Util.stringTodouble(Util.FormataValor2Casas(value));
	}

	public static Date stringToDate(String data) {
		if (data == null || data.equals("")) {
			return null;
		}
		StringTokenizer st = new StringTokenizer(data, "//");
		if (st.countTokens() == 3) {
			int day = Integer.parseInt(st.nextToken());
			int month = Integer.parseInt(st.nextToken()) - 1;
			int year = Integer.parseInt(st.nextToken());
			Calendar d = Calendar.getInstance();
			d.set(year, month, day);
			return new Date(d.getTimeInMillis());
		} else {
			return null;
		}
	}
	
	public static String dateToString(java.util.Date data) {
		return getDataFormatada(data);
	}
	
	public static String dateToString(Date data) {
		return getDataFormatada( new java.util.Date( data.getTime()) );
	}	
	

	/**
	 * Recebe uma String no formato 000.000,00 e retorna um double. Caso seja
	 * diferente retorna lança a exceção
	 */

	public static double stringTodouble(String value)
			throws NumberFormatException {
		if (value == null || value.equals("")) {
			return 0;
		}
		value = value.replaceAll("\\.", "");
		value = value.replaceAll(",", ".");
		return new Double(value).doubleValue();
	}

	/**
	 * Retorna a data no formato especificado padrao
	 * 
	 * @param data
	 *            a data a ser formatada.
	 * @return String a data no formato especificado.
	 */
	public static String getDataFormatada(java.util.Date data) {
		if (data == null) {
			return "";
		}
		return Util.getDataFormatada(data, DATA_FORMATO_PADRAO);
	}

	/**
	 * Retorna a data no formato especificado por pattern
	 * 
	 * @param data
	 *            a data a ser formatada.
	 * @param pattern
	 *            o formato no qual a data deve ser retornada.
	 * @return String a data no formato especificado.
	 */
	public static String getDataFormatada(java.util.Date data, String pattern) {
		if (data == null) {
			return "";
		}
		return new SimpleDateFormat(pattern).format(data);
	}

	/**
	 * Retorna a data atual no formato especificado por pattern
	 * 
	 * @param pattern
	 *            o formato no qual a data deve ser retornada.
	 * @return String a data no formato especificado.
	 */
	public static String hoje(String pattern) {
		java.util.Date data = Calendar.getInstance().getTime();
		return Util.getDataFormatada(data, pattern);
	}

	public static java.util.Date dataAtual() {
		java.util.Date data = Calendar.getInstance().getTime();
		return data;
	}

	/**
	 * Retorna a data atual no formato brasileiro
	 * 
	 * @return String a data no formato brasileiro.
	 */
	public static String hoje() {
		return Util.hoje(DATA_FORMATO_PADRAO);
	}

	public static String hojeExtenso() {
		return Util.hoje("dd 'de " + getMesAtualExtenso() + " de' yyyy");
	}

	public static String getDataPorExtenso(java.util.Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		return getDataFormatada(data, "dd 'de "
				+ getMesExtenso(calendar.get(Calendar.MONTH)) + " de' yyyy");
	}

	public static String getDiaAtual() {
		return Util.hoje("dd");
	}

	public static String getMesAtual() {
		return Util.hoje("MM");
	}

	public static String getMesAtualExtenso() {
		Calendar calendar = Calendar.getInstance();
		return getMesExtenso(calendar.get(Calendar.MONTH));
	}

	public static String getAnoAtuals() {
		return Util.hoje("yyyy");
	}
	
	public static Integer getAnoQuadrimestreAtual() throws Exception {
		java.util.Date dtAtual = dataAtual();
		QuadrimestreDAO dao = DAOFactory.getQuadrimestreDAO();
		Quadrimestre quadrimestre = (Quadrimestre) dao.findQuadrimestrePorData( dtAtual, 0 );
		if (quadrimestre.getQuadrimestrePK().getCdQuadrimestre() == null) {
			throw new QuadrimestreException("msg.erro.quadrimestre.nao.cadastrado");
		}
		return quadrimestre.getQuadrimestrePK().getNuAno();
	}
	
	
	
	public static String getQuadrimestreAtual( Boolean flAnual) throws Exception {
		java.util.Date dtAtual = dataAtual();
		QuadrimestreDAO dao = DAOFactory.getQuadrimestreDAO();
		Quadrimestre quadrimestre = (Quadrimestre) dao.findQuadrimestrePorData( dtAtual, (flAnual == true ? 1 : 0) );
		if (quadrimestre.getQuadrimestrePK().getCdQuadrimestre() == null) {
			throw new QuadrimestreException("msg.erro.quadrimestre.nao.cadastrado");
		}
		return quadrimestre.getQuadrimestrePK().getCdQuadrimestre().toString();
	}
	
	
	public static int getQuadrimestreAtual_Integer(Boolean flAnual) throws Exception {
		java.util.Date dtAtual = dataAtual();
		QuadrimestreDAO dao = DAOFactory.getQuadrimestreDAO();
		Quadrimestre quadrimestre = (Quadrimestre) dao.findQuadrimestrePorData( dtAtual, (flAnual == true ? 1 : 0) );
		if (quadrimestre.getQuadrimestrePK().getCdQuadrimestre() == null) {
			throw new QuadrimestreException("msg.erro.quadrimestre.nao.cadastrado");
		}
		return quadrimestre.getQuadrimestrePK().getCdQuadrimestre();
	}
	
	public static int getQuadrimestreAtual_Integer(Date data, Boolean flAnual) throws Exception {
		QuadrimestreDAO dao = DAOFactory.getQuadrimestreDAO();
		Quadrimestre quadrimestre = (Quadrimestre) dao.findQuadrimestrePorData( data, (flAnual == true ? 1 : 0) );
		if (quadrimestre.getQuadrimestrePK().getCdQuadrimestre() == null) {
			throw new QuadrimestreException("msg.erro.quadrimestre.nao.cadastrado");
		}
		return quadrimestre.getQuadrimestrePK().getCdQuadrimestre();
	}
	
	public static String getMesExtenso(int codMes) {
		String retorno = "";
		switch (codMes) {
		case (0):
			retorno = "Janeiro";
			break;
		case (1):
			retorno = "Fevereiro";
			break;
		case (2):
			retorno = "Março";
			break;
		case (3):
			retorno = "Abril";
			break;
		case (4):
			retorno = "Maio";
			break;
		case (5):
			retorno = "Junho";
			break;
		case (6):
			retorno = "Julho";
			break;
		case (7):
			retorno = "Agosto";
			break;
		case (8):
			retorno = "Setembro";
			break;
		case (9):
			retorno = "Outubro";
			break;
		case (10):
			retorno = "Novembro";
			break;
		case (11):
			retorno = "Dezembro";
			break;
		}
		return retorno;
	}

	/**
	 * Retorna a data atual no formato SQL
	 * 
	 * @return String a data no formato SQL.
	 */
	public static String hojeDB() {
		return Util.hoje(DATA_FORMATO_BANCO);
	}

	/**
	 * Retorna a data no formato brasileiro.
	 * 
	 * @param dataDoBanco
	 *            a data completa no formato do Banco de Dados (yyyy-MM-dd
	 *            hh:mm:ss.m).
	 * @return String a data no formato brasileiro (dd/MM/yyyy). pode ser vazia
	 *         caso o parametro seja nulo.
	 */
	public static String dataBancoBR(String dataDoBanco)
			throws IllegalArgumentException {
		String dataStr = dataDoBanco;
		// Debug.println("[retornaData] dataDoBanco: '" + dataDoBanco + "', size
		// = " +
		// dataDoBanco.length());
		if ((dataDoBanco != null) && (dataDoBanco.length() > 0)) {
			// Retira os caracteres não legíveis (incluindo espaço) do início e
			// final da string
			dataDoBanco.trim();
			// Verifica se tem o horario juntamente com a data
			if (dataDoBanco.length() > 11)
				dataDoBanco = dataDoBanco
						.substring(0, dataDoBanco.indexOf(' '));
			// Debug.println("[retornaData] dataDoBanco: '"+dataDoBanco+"'");
			java.util.Date data = java.sql.Date.valueOf(dataDoBanco);
			if (data != null)
				dataStr = new SimpleDateFormat(DATA_FORMATO_PADRAO)
						.format(data);
		}
		// Debug.println("[retornaData] leaving...");
		return dataStr;
	}

	/**
	 * Retorna a data no formato do banco de dados (yyy-MM-dd).
	 * 
	 * @param data
	 *            a data completa no formato brasileiro (dd/MM/yyyy).
	 * @return String a data no formato do banco de dados (yyy-MM-dd). Caso o
	 *         parametro esteja incorreto, retorna ele mesmo.
	 */
	public static String dataBRBanco(String _data) {
		// Debug.println("\n------------------------\ndataBRBanco called...");
		if ((_data == null) || (_data.length() != 10))
			return _data;
		// Debug.println("data = '" + _data + "'");
		String str = _data.substring(6, 10) + "-" + _data.substring(3, 5) + "-"
				+ _data.substring(0, 2);
		// Debug.println("str = '" + str + "'");
		// Debug.println("dataBRBanco returning\n------------------------\n");
		return str;
	}

	/**
	 * Retorna o double como String, com precisão de 2 casas.
	 * 
	 * @param num
	 *            o número a ser convertido
	 * @return String o número convertido com precisão de 2 casas.
	 */
	public static String doubleAsString(double num)
			throws IllegalArgumentException {
		return Util.doubleAsString(num, 2);
	}

	/**
	 * Retorna o double como String.
	 * 
	 * @param num
	 *            o número a ser convertido
	 * @param precisao
	 *            a precisão para utilizar na conversão.
	 * @return String o double convertido com precisao de 'precisao' casas
	 *         decimais. Se a precisão for maior do que a contida no número, a
	 *         precisão será a maior possível.
	 */
	public static String doubleAsString(double num, int precisao)
			throws IllegalArgumentException {
		/*
		 * StringBuffer pattern = new StringBuffer("0"); if (precisao > 0)
		 * pattern.append("."); for (int i = 0; i < precisao; i++)
		 * pattern.append("0"); DecimalFormat format = new
		 * DecimalFormat(pattern.toString()); return
		 * format.format(num).replace(',', '.');
		 */
		StringBuffer pattern = new StringBuffer("0");
		if (precisao > 0)
			pattern.append(".0");
		DecimalFormat format = new DecimalFormat(pattern.toString());
		format.setMinimumFractionDigits(precisao);
		format.setMaximumFractionDigits(precisao);
		format.setGroupingUsed(false);
		return format.format(num).replace(',', '.');
	}

	/**
	 * Retorna o double como String, com precisão de 2 casas. Valida se o valor
	 * retornado é igual ao recebido, pois podem haver erros de arredondamento.
	 * Caso isto ocorra, realiza o arredondamento para baixo.
	 * 
	 * @param num
	 *            o número a ser convertido
	 * @return String o número convertido com precisão de 2 casas.
	 */
	public static String doubleAsStringLower(double num)
			throws IllegalArgumentException {
		return Util.doubleAsStringLower(num, 2);
	}

	/**
	 * Retorna o double como String, com precisão de 2 casas. Valida se o valor
	 * retornado é igual ao recebido, pois podem haver erros de arredondamento.
	 * Caso isto ocorra, realiza o arredondamento para baixo.
	 * 
	 * @param num
	 *            o número a ser convertido
	 * @return String o número convertido com precisão de 2 casas.
	 */
	public static String doubleAsStringLower(double num, int precisao)
			throws IllegalArgumentException {
		String retStr = Util.doubleAsString(num, precisao);
		if (precisao <= 0)
			return retStr;
		double retorno = Double.parseDouble(retStr);
		// Valida e garante que o retorno é igual ou menor que o parametro
		// recebido
		if (Double.compare(num, retorno) < 0) {
			StringBuffer diferenca = new StringBuffer("0.");
			for (int i = 0; i < (precisao - 1); i++) {
				diferenca.append("0");
			}
			diferenca.append("1");
			retorno = retorno - Double.parseDouble(diferenca.toString());
		}
		return String.valueOf(retorno);
	}

	/**
	 * Retorna uma Enumeration com todos os anos entre anoInicial e anoFinal,
	 * incluindo os extremos.
	 * 
	 * @param anoInicial
	 *            o ano inicial da lista a ser retornada.
	 * @param anoFinal
	 *            o ano final da lista a ser retornada.
	 * @returns Enumeration a lista com os anos entre anoInicial e anoFinal,
	 *          incluindo os extremos.
	 */
	public static List<Integer> getAnos(int anoInicial, int anoFinal) {
		if (anoFinal < anoInicial) {
			throw new IllegalArgumentException(
					"Ano final menor que ano inicial");
		}
		ArrayList<Integer> anos = new ArrayList<Integer>();
		for (int i = anoInicial; i <= anoFinal; i++) {
			anos.add(i);
		}
		return anos;
	}

	public static List<Integer> getAnos(int anoInicial) {
		return getAnos(anoInicial, Integer.parseInt(getAnoAtuals()));
	}

	/**
	 * Retorna uma string no formato de CPF ou CNPJ.
	 * 
	 * @param int
	 *            o número a ser utilizado para criar o CPF. Deve ter exatos 11
	 *            digitos ou 14 para CNPJ, caso contrario retorna uma string
	 *            representando o proprio numero original.
	 * @returns String a string no formato de CPF (999.999.999-99).
	 */
	public static String colocaCaracteresCPFCNPJ(int num) {
		return Util.colocaCaracteresCPFCNPJ(String.valueOf(num));
	}

	/**
	 * Retorna o CPF/CNPJ sem caracteres separadores.
	 * 
	 * @param ex:
	 *            string o CPF no formato 999.999.999-99.
	 * @returns String o CPF sem separadores, no formato 99999999999.
	 */
	public static String eliminaCaracteresCPFCNPJ(String campo) {
		while (campo.indexOf("/") != -1)
			campo = campo.substring(0, campo.indexOf("/"))
					+ campo.substring(campo.indexOf("/") + 1, campo.length());
		while (campo.indexOf(",") != -1)
			campo = campo.substring(0, campo.indexOf(","))
					+ campo.substring(campo.indexOf(",") + 1, campo.length());
		while (campo.indexOf(".") != -1)
			campo = campo.substring(0, campo.indexOf("."))
					+ campo.substring(campo.indexOf(".") + 1, campo.length());
		while (campo.indexOf("-") != -1)
			campo = campo.substring(0, campo.indexOf("-"))
					+ campo.substring(campo.indexOf("-") + 1, campo.length());
		return campo;
	}

	public static String eliminaMascaraCEP(String cep) {
		cep = cep.replaceAll("\\.", "");
		cep = cep.replaceAll("-", "");
		return cep;
	}

	public static String colocaCaracteresCEP(String campo) {
		if ((campo != null) && (campo.trim().length() == 8)) {
			campo = campo.trim();
			campo = campo.substring(0, 2) + "."
					+ campo.substring(2, campo.length());
			campo = campo.substring(0, 6) + "-"
					+ campo.substring(6, campo.length());
		}
		return campo;
	}

	/**
	 * Retorna uma string no formato de CPF ou CNPJ.
	 * 
	 * @param string
	 *            a string a ser utilizada para criar o CPF. deve ter exatos 11
	 *            caracteres, ou exatos 14 para CNPJ, caso contrario retorna a
	 *            propria string original.
	 * @returns ex: String a string no formato de CPF (999.999.999-99).
	 */
	public static String colocaCaracteresCPFCNPJ(String campo) {
		if (campo.length() == 11)// CPF
		{
			campo = campo.substring(0, 3) + "."
					+ campo.substring(3, campo.length());
			campo = campo.substring(0, 7) + "."
					+ campo.substring(7, campo.length());
			campo = campo.substring(0, 11) + "-"
					+ campo.substring(11, campo.length());
		} else if (campo.length() == 14)// CNPJ
		{
			campo = campo.substring(0, 2) + "."
					+ campo.substring(2, campo.length());
			campo = campo.substring(0, 6) + "."
					+ campo.substring(6, campo.length());
			campo = campo.substring(0, 10) + "/"
					+ campo.substring(10, campo.length());
			campo = campo.substring(0, 15) + "-"
					+ campo.substring(15, campo.length());
		}
		return campo;
	}

	/**
	 * Retorna o numero de protocolo formatado.
	 * 
	 * @param int
	 *            o número a ser utilizado para criar o numero de protocolo
	 *            formatado.
	 * @returns String o numero de protocolo no formato 999/999.
	 */
	public static String formataNumProtocolo(int _num) {
		return Util.formataNumProtocolo(String.valueOf(_num));
	}

	/**
	 * Retorna o numero de protocolo formatado.
	 * 
	 * @param string
	 *            a string a ser utilizada para criar o numero de protocolo
	 *            formatado.
	 * @returns String o numero de protocolo no formato 999/999.
	 */
	public static String formataNumProtocolo(String _string) {
		if (_string == null)
			return _string;
		// Verifica se tem o tamanho necessário
		if (_string.length() < 6) {
			// Adiciona n zeros a esquerda
			while (_string.length() < 6) {
				_string = "0" + _string;
			}
		}
		int len = _string.length();
		int posBarra = len - 3;
		// Coloca o divisor no numero de protocolo
		_string = _string.substring(0, posBarra) + "/"
				+ _string.substring(posBarra, len);
		return _string;
	}

	/**
	 * Retorna o numero de protocolo sem o caracter divisor.
	 * 
	 * @param string
	 *            o numero de protocolo formatado.
	 * @returns String o numero de protocolo no formato 999999. Se o parametro
	 *          nao tiver 7 caracteres, retorna o proprio parametro.
	 */
	public static String limpaNumProtocolo(String _str) {
		if (_str == null)
			return _str;
		if (_str.length() != 7)
			return _str;
		return (_str.substring(0, 3) + _str.substring(4, 7));
	}

	public static String formataTextoPrimeiraLetraMaiuscula(String _text) {
		if ((_text == null) || (_text.length() == 0))
			return _text;

		_text = _text.charAt(0) + _text.substring(1).toLowerCase();
		int pos = 0;
		while ((pos = _text.indexOf(' ', pos)) >= 0) {
			pos++;
			if (_text.charAt(pos) != ' ') {
				_text = _text.substring(0, pos)
						+ _text.substring(pos, pos + 1).toUpperCase()
						+ _text.substring(pos + 1, _text.length());
			}
		}
		return _text;
	}

	public static String formataProtocolo(String prot) {
		if ((prot == null) || (prot.length() != 9)) {
			return null;
		}
		return prot.substring(0, 5) + "-" + prot.substring(5, 9);
	}

	public static String formataPratica(String codigo) {
		if ((codigo == null) || (codigo.length() != 6)) {
			return codigo;
		} else {
			return codigo.substring(0, 2) + "." + codigo.substring(2, 4) + "."
					+ codigo.substring(4, 6);
		}
	}

	public static String eliminaMascaraPratica(String codigo) {
		return codigo.replaceAll("\\.", "");
	}

	public static String formataAgencia(int codAgencia) {
		return Util.formataAgencia(String.valueOf(codAgencia));
	}

	public static String formataAgencia(String codigo) {
		return Util.formataAgenciaConta(codigo, NUM_DIGITOS_AGENCIA);
	}

	public static String formataConta(int codAgencia) {
		return Util.formataConta(String.valueOf(codAgencia));
	}

	public static String formataConta(String codigo) {
		return Util.formataAgenciaConta(codigo, NUM_DIGITOS_CONTA);
	}

	public static String formataAgenciaConta(String codigo, int numDigitos) {
		if ((codigo == null) || (codigo.length() < 1)
				|| (codigo.length() > numDigitos)) {
			return codigo;
		} else {
			while (codigo.length() < numDigitos) {
				codigo = "0" + codigo;
			}
			return codigo.substring(0, numDigitos - 1) + "-"
					+ codigo.substring(numDigitos - 1, numDigitos);
		}
	}

	public static String eliminaMascaraAgencia(String codigo) {
		return codigo.replaceAll("\\-", "");
	}

	public static String eliminaMascaraConta(String codigo) {
		return codigo.replaceAll("\\-", "");
	}

	public static String FormataDouble(double num, String pattern) {
		DecimalFormatSymbols simbolosDecimais = new DecimalFormatSymbols(
				Locale.ITALIAN);
		DecimalFormat formatadorDecimais = new DecimalFormat();
		formatadorDecimais.setDecimalFormatSymbols(simbolosDecimais);
		formatadorDecimais.applyPattern(pattern);
		return formatadorDecimais.format(num, new StringBuffer(""),
				new FieldPosition(1)).toString();
	}

	private static String FormataValorPrivado(double num) {
		return FormataDouble(num, PATTERN_SEM_CASAS);
	}

	public static String FormataValor(int num) {
		return FormataValorPrivado(num);
	}

	public static String FormataValor(Integer num) {
		return FormataValorPrivado(num.doubleValue());
	}

	public static String FormataValor(float num) {
		return FormataValorPrivado(num);
	}

	public static String FormataValor(Float num) {
		return FormataValorPrivado(num.doubleValue());
	}

	public static String FormataValor(double num) {
		return FormataValorPrivado(num);
	}

	public static String FormataValor(Double num) {
		return FormataValorPrivado(num.doubleValue());
	}

	public static String FormataValor(String num) {
		return FormataValorPrivado(new Double(num).doubleValue());
	}

	private static String FormataValor2CasasPrivado(double num) {
		return FormataDouble(num, PATTERN_2_CASAS);
	}

	public static String FormataValor2Casas(int num) {
		return FormataValor2CasasPrivado(num);
	}

	public static String FormataValor2Casas(Integer num) {
		return FormataValor2CasasPrivado(num.doubleValue());
	}

	public static String FormataValor2Casas(float num) {
		return FormataValor2CasasPrivado(num);
	}

	public static String FormataValor2Casas(Float num) {
		return FormataValor2CasasPrivado(num.doubleValue());
	}

	public static String FormataValor2Casas(double num) {
		return FormataValor2CasasPrivado(num);
	}

	public static String FormataValor2Casas(Double num) {
		return FormataValor2CasasPrivado(num.doubleValue());
	}

	public static String FormataValor2Casas(String num) {
		return FormataValor2CasasPrivado(new Double(num).doubleValue());
	}

	private static String FormataValor3CasasPrivado(double num) {
		return FormataDouble(num, PATTERN_3_CASAS);
	}

	public static String FormataValor3Casas(int num) {
		return FormataValor3CasasPrivado(num);
	}

	public static String FormataValor3Casas(Integer num) {
		return FormataValor3CasasPrivado(num.doubleValue());
	}

	public static String FormataValor3Casas(float num) {
		return FormataValor3CasasPrivado(num);
	}

	public static String FormataValor3Casas(Float num) {
		return FormataValor3CasasPrivado(num.doubleValue());
	}

	public static String FormataValor3Casas(double num) {
		return FormataValor3CasasPrivado(num);
	}

	public static String FormataValor3Casas(Double num) {
		return FormataValor3CasasPrivado(num.doubleValue());
	}

	public static String FormataValor3Casas(String num) {
		return FormataValor3CasasPrivado(new Double(num).doubleValue());
	}

	public static String RetornaHash(String string) {
		String hash_md5;

		byte[] texto = string.getBytes();

		try {
			MessageDigest md5 = MessageDigest.getInstance("SHA-1");
			md5.update(texto);
			hash_md5 = toHex(md5.digest());
		} catch (java.security.NoSuchAlgorithmException e) {
			hash_md5 = new String("Erro!");
		}
		return hash_md5;
	}

	public static String toHex(byte[] v) {
		int i, h, l;
		StringBuffer s = new StringBuffer();

		for (i = 0; i < v.length; i++) {
			h = new Byte(v[i]).intValue() & 0xf0;
			h = h >> 4;
			s.append(Integer.toHexString(h));
			l = new Byte(v[i]).intValue() & 0xf;
			s.append(Integer.toHexString(l));
		}
		return s.toString();
	}

	public static boolean isAnoBisexto(int ano) {
		int f4 = 0;
		int f100 = 0;
		int f400 = 0;
		if (ano % 4 == 0) {
			f4 = 1;
		}
		if (ano % 100 == 0) {
			f100 = 1;
		}
		if (ano % 400 == 0) {
			f400 = 1;
		}
		if ((f4 == 1 && f100 == 0) || (f4 == 1 && f400 == 1)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static int getNumeroDias(int nuMes, int nuAno) {
		int retorno = 0;
		switch (nuMes) {
			case 1: retorno = 31; break;
			case 2: if (isAnoBisexto(nuAno)) {
						retorno = 29;
					} else {
						retorno = 28;
					}
					break;
			case 3: retorno = 31; break;
			case 4: retorno = 30; break;
			case 5: retorno = 31; break;
			case 6: retorno = 30; break;
			case 7: retorno = 31; break;
			case 8: retorno = 31; break;
			case 9: retorno = 30; break;
			case 10: retorno = 31; break;
			case 11: retorno = 30; break;
			case 12: retorno = 31; break;
			default: break;
		}
		
		return retorno;
	}
	
	public static String zeroEsquerda(int nuTamanho, String valor) {
		String zeros = valor;
		while (zeros.length() <= nuTamanho) {
			zeros = "0" + zeros;
		}
		return zeros;
	}
	
	public static void carregaTimes(HttpServletRequest request) {
		TimecamisaDAO timecamisaDAO = DAOFactory.getTimecamisaDAO();
		Timecamisa timeA = timecamisaDAO.findTimecamisa( Timecamisa.TIME_CAMISA_A );
		Timecamisa timeB = timecamisaDAO.findTimecamisa( Timecamisa.TIME_CAMISA_B );
		request.getSession().setAttribute(Constantes.SESSION_NMTIMEA, timeA.getNmTime());
		request.getSession().setAttribute(Constantes.SESSION_NMTIMEB, timeB.getNmTime());
	}
	
	public static void carregaUltimasnoticias(HttpServletRequest request) {
		if (request.getSession().getAttribute(Constantes.SESSION_LIST_NOTICIAS) == null) {
			Calendar c = Calendar.getInstance();
			Date data = new Date(c.getTimeInMillis());
			List<Noticia> listNoticias = DAOFactory.getNoticiaDAO().findAllNoticiasEventosatuais( Noticia.TIPO_NOTICIA );
			request.getSession().setAttribute(Constantes.SESSION_MOSTRA_POPUP_NOTICIA, false);
			request.getSession().removeAttribute("nuNoticiapopup");
			for (Noticia n : listNoticias) {
				if (n.getFlPopup() == 1) {
					request.getSession().setAttribute(Constantes.SESSION_MOSTRA_POPUP_NOTICIA, true);
					request.getSession().setAttribute("nuNoticiapopup", n.getNoticiaPK().getCdNoticia() + "");
					n.setStyleClass("negrito");
				}
			}
			request.getSession().setAttribute(Constantes.SESSION_LIST_NOTICIAS, listNoticias);		
		}
	}
	
	public static void carregaUltimoseventos(HttpServletRequest request) {
		if (request.getSession().getAttribute(Constantes.SESSION_LISTA_EVENTOS) == null) {
			Calendar c = Calendar.getInstance();
			Date data = new Date(c.getTimeInMillis());
			List<Noticia> listEventos = DAOFactory.getNoticiaDAO().findAllNoticiasEventosatuais( Noticia.TIPO_EVENTO );
			request.getSession().setAttribute(Constantes.SESSION_MOSTRA_POPUP_EVENTO, false);
			request.getSession().removeAttribute("nuEventopopup");
			for (Noticia n : listEventos) {
				if ((n.getFlPopup() == 1) && (n.getDtFimevento().compareTo(data) > 0)) {
					request.getSession().setAttribute(Constantes.SESSION_MOSTRA_POPUP_EVENTO, true);
					request.getSession().setAttribute("nuEventopopup", n.getNoticiaPK().getCdNoticia() + "");
					n.setStyleClass("negrito");
					
				}
			}
			request.getSession().setAttribute(Constantes.SESSION_LISTA_EVENTOS, listEventos);			
		}
	}

	public static Configuracao carregaConfiguracao(Integer cdConfiguracao) {
		Configuracao config = new Configuracao();
		config.getConfiguracaoPK().setCdConfiguracao( cdConfiguracao );
		config = (Configuracao) DAOFactory.getConfiguracaoDAO().findByPrimaryKey( config.getConfiguracaoPK() );
		if (config.getVlConfiguracao() == null) {
			config.setStatusInsert();
			config.setVlConfiguracao(" ");
		} else {
			config.setStatusUpdate();
		}		
		return config;
	}
	
	public static void carregaDescritivoSite(HttpServletRequest request) {
		if (request.getSession().getAttribute(Constantes.SESSION_DESCRITIVO_SITE) == null) {
			Configuracao conf = carregaConfiguracao(Configuracao.TP_CONFIG_DESCRITIVO_SITE);
			request.getSession().setAttribute(Constantes.SESSION_DESCRITIVO_SITE, conf.getVlConfiguracao());
		}
	}
	
	public static Integer nullToZero(Integer valor) {
		return valor == null ? 0 : valor;
	}
	
	public static void carregaAniversariantes(HttpServletRequest request) {
		if (request.getSession().getAttribute(Constantes.SESSION_LIST_ANIVERSARIANTES) == null) {
			Calendar c = Calendar.getInstance();
			Date data = new Date(c.getTimeInMillis());
			List<Socio> aniverList = DAOFactory.getSocioDAO().findAniversariantes(c.get(Calendar.MONTH) + 1, 
					Constantes.TIPO_SOCIO_PATRIMONIAL, 
					Constantes.TIPO_SOCIO_PREFERENCIAL, 
					Constantes.TIPO_SOCIO_BENEMERITO);
			/*
			Collections.sort(aniverList, new Comparator<Socio>() {
				public int compare(Socio arg0, Socio arg1) {
					return arg1.getDtNascimento().getDate() >= arg0.getDtNascimento().getDate() ? 1 : 0;
				}			
			});
			*/
			for (Socio s : aniverList) {
				if (s.getDtNascimento().getDate() == data.getDate()
					&& s.getDtNascimento().getMonth() == data.getMonth()) {
					s.setClassName("negrito");
				}
			}
			request.getSession().setAttribute(Constantes.SESSION_LIST_ANIVERSARIANTES, aniverList);
		}
	}
	
	public static Boolean isNullOrZero(Integer valor) {
		if (valor == null || valor == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String getRessource(String resource) {
		ResourceBundle banBundle = ResourceBundle.getBundle("ApplicationResources");
		return banBundle.getString(resource);
	}

}