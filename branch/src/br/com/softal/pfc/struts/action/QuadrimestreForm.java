package br.com.softal.pfc.struts.action;

import br.com.softal.pfc.Quadrimestre;
 
@SuppressWarnings("serial")
public class QuadrimestreForm extends PfcForm {
	
	private Quadrimestre quadrimestre1;
	private Quadrimestre quadrimestre2;
	private Quadrimestre quadrimestre3;
	private Quadrimestre quadrimestre4;
	private Boolean podeAlterar;
	
	public QuadrimestreForm() {
		setEntidade(new Quadrimestre());
		setQuadrimestre1(new Quadrimestre());
		setQuadrimestre2(new Quadrimestre());
		setQuadrimestre3(new Quadrimestre());
		setQuadrimestre4(new Quadrimestre());
	}

	public Quadrimestre getQuadrimestre1() {
		return quadrimestre1;
	}

	public void setQuadrimestre1(Quadrimestre quadrimestre1) {
		this.quadrimestre1 = quadrimestre1;
	}

	public Quadrimestre getQuadrimestre2() {
		return quadrimestre2;
	}

	public void setQuadrimestre2(Quadrimestre quadrimestre2) {
		this.quadrimestre2 = quadrimestre2;
	}

	public Quadrimestre getQuadrimestre3() {
		return quadrimestre3;
	}

	public void setQuadrimestre3(Quadrimestre quadrimestre3) {
		this.quadrimestre3 = quadrimestre3;
	}

	public Quadrimestre getQuadrimestre4() {
		return quadrimestre4;
	}

	public void setQuadrimestre4(Quadrimestre quadrimestre4) {
		this.quadrimestre4 = quadrimestre4;
	}

	public Boolean getPodeAlterar() {
		return podeAlterar == null ? true : podeAlterar;
	}

	public void setPodeAlterar(Boolean podeAlterar) {
		this.podeAlterar = podeAlterar;
	}
	
}
