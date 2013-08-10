package br.com.softal.pfc.struts.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

@SuppressWarnings("serial")
public class LoginForm extends PfcForm {

	private String usuario;
	private String senha;
	
	public LoginForm() {
		setEntidade(null);
	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		throw new UnsupportedOperationException(
				"Generated method 'validate(...)' not implemented.");
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

	}

	public String getSenha() {
		return senha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setSenha(String string) {
		senha = string;
	}

	public void setUsuario(String string) {
		usuario = string;
	}

}
