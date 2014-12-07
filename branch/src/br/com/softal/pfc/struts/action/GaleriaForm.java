package br.com.softal.pfc.struts.action;

import org.apache.struts.upload.FormFile;

import br.com.softal.pfc.Galeria;
 
public class GaleriaForm extends PfcForm {
	
	private static final long serialVersionUID = 1L;
	
	private FormFile arquivo;
	
	//-- private FormFile fotos[];
	
	public GaleriaForm() {
		setEntidade(new Galeria());
	}
/*
	public FormFile[] getFotos() {
		return fotos;
	}

	public void setFotos(FormFile[] fotos) {
		this.fotos = fotos;
	}
	*/

	public FormFile getArquivo() {
		return arquivo;
	}

	public void setArquivo(FormFile arquivo) {
		this.arquivo = arquivo;
	}
}
