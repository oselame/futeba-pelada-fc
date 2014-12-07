package br.com.softal.pfc.struts.action;

import java.util.ArrayList;
import java.util.List;

import br.com.softal.pfc.Partida;
import br.com.softal.pfc.Punicaopartida;
 
@SuppressWarnings("serial")
public class PartidaForm extends PfcForm<Partida> {
	
	private Boolean mostraBotaoEnviarEmailAtrasados;
	private List<Punicaopartida> punicoes;

	public PartidaForm() {
		setEntidade( new Partida() );
		setPunicoes(new ArrayList<Punicaopartida>());
	}

	public Boolean getMostraBotaoEnviarEmailAtrasados() {
		return mostraBotaoEnviarEmailAtrasados == null ? false : mostraBotaoEnviarEmailAtrasados;
	}

	public void setMostraBotaoEnviarEmailAtrasados(
			Boolean mostraBotaoEnviarEmailAtrasados) {
		this.mostraBotaoEnviarEmailAtrasados = mostraBotaoEnviarEmailAtrasados;
	}

	public List<Punicaopartida> getPunicoes() {
		return punicoes;
	}

	public void setPunicoes(List<Punicaopartida> punicoes) {
		this.punicoes = punicoes;
	}
	
}
