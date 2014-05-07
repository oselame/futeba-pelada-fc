package br.com.softal.pfc;

@SuppressWarnings("serial")
public class Punicaopartida extends Entidade {

	private PunicaopartidaPK punicaopartidaPK;

	public Punicaopartida() {
		setPunicaopartidaPK(new PunicaopartidaPK());
	}

	@Override
	public EntidadePK<Punicaopartida> getPK() {
		return getPunicaopartidaPK();
	}

	public PunicaopartidaPK getPunicaopartidaPK() {
		return punicaopartidaPK;
	}

	public void setPunicaopartidaPK(PunicaopartidaPK punicaopartidaPK) {
		this.punicaopartidaPK = punicaopartidaPK;
	}

}
