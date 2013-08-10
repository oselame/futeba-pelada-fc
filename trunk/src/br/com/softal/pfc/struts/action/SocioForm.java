package br.com.softal.pfc.struts.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.upload.FormFile;

import br.com.softal.pfc.Socio;

public class SocioForm extends PfcForm {
	
	private static final long serialVersionUID = 1L;
	
	private FormFile arquivo;
	
	private List<Socio> sociospatrimonial;
	private List<Socio> sociospreferencial;
	private List<Socio> sociosavulso;
	private List<Socio> sociosbenemerito;
	private List<Socio> sociosforauso;

	public SocioForm() {
		setEntidade(new Socio());
		setSociosavulso(new ArrayList<Socio>());
		setSociospatrimonial(new ArrayList<Socio>());
		setSociospreferencial(new ArrayList<Socio>());
		setSociosforauso(new ArrayList<Socio>());
		setSociosbenemerito(new ArrayList<Socio>());
	}

	public FormFile getArquivo() {
		return arquivo;
	}

	public void setArquivo(FormFile arquivo) {
		this.arquivo = arquivo;
	}

	public List<Socio> getSociospatrimonial() {
		return sociospatrimonial;
	}

	public void setSociospatrimonial(List<Socio> sociospatrimonial) {
		this.sociospatrimonial = sociospatrimonial;
	}

	public List<Socio> getSociospreferencial() {
		return sociospreferencial;
	}

	public void setSociospreferencial(List<Socio> sociospreferencial) {
		this.sociospreferencial = sociospreferencial;
	}

	public List<Socio> getSociosavulso() {
		return sociosavulso;
	}

	public void setSociosavulso(List<Socio> sociosavulso) {
		this.sociosavulso = sociosavulso;
	}

	public List<Socio> getSociosforauso() {
		return sociosforauso;
	}

	public void setSociosforauso(List<Socio> sociosforauso) {
		this.sociosforauso = sociosforauso;
	}

	public List<Socio> getSociosbenemerito() {
		return sociosbenemerito;
	}

	public void setSociosbenemerito(List<Socio> sociosbenemerito) {
		this.sociosbenemerito = sociosbenemerito;
	}
	
	
	
}
