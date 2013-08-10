package br.com.softal.pfc.struts.action;

import br.com.softal.pfc.Configuracao;
 
 
@SuppressWarnings("serial")
public class ConfiguracaoForm extends PfcForm {
	
	public ConfiguracaoForm() {
		setEntidade( new Configuracao() );
	}
 
}
