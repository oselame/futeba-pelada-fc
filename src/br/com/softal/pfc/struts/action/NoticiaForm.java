package br.com.softal.pfc.struts.action;

import br.com.softal.pfc.Noticia;
 
@SuppressWarnings("serial")
public class NoticiaForm extends PfcForm {
 
	public NoticiaForm() {
		setEntidade( new Noticia() );
	}

}
