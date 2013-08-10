package br.com.softal.pfc.struts.action;

import br.com.softal.pfc.Partida;
 
@SuppressWarnings("serial")
public class PartidaForm extends PfcForm<Partida> {
	
	private Boolean mostraBotaoEnviarEmailAtrasados;

	public PartidaForm() {
		setEntidade( new Partida() );
	}

	public Boolean getMostraBotaoEnviarEmailAtrasados() {
		return mostraBotaoEnviarEmailAtrasados == null ? false : mostraBotaoEnviarEmailAtrasados;
	}

	public void setMostraBotaoEnviarEmailAtrasados(
			Boolean mostraBotaoEnviarEmailAtrasados) {
		this.mostraBotaoEnviarEmailAtrasados = mostraBotaoEnviarEmailAtrasados;
	}
	
	
}
