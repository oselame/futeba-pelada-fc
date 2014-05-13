package br.com.softal.pfc.dto;

import java.io.Serializable;

public class Logo implements Serializable {
	private String nmLogo;
	private String deCaminho;
	private String nmLink;

	public String getNmLogo() {
		return nmLogo;
	}

	public void setNmLogo(String nmLogo) {
		this.nmLogo = nmLogo;
	}

	public String getDeCaminho() {
		return deCaminho;
	}

	public void setDeCaminho(String deCaminho) {
		this.deCaminho = deCaminho;
	}

	public String getNmLink() {
		return nmLink;
	}

	public void setNmLink(String nmLink) {
		this.nmLink = nmLink;
	}

}
