package br.com.softal.pfc.struts.action;

import br.com.softal.pfc.Classificacao;
 
@SuppressWarnings("serial")
public class ClassificacaoForm extends PfcForm {
 
	public ClassificacaoForm() {
		setEntidade(new Classificacao());
	}	
}
