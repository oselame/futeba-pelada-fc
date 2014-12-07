package br.com.softal.pfc;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class Entidade implements Serializable {
	private String status;

	public String getStatus() {
		return status == null ? "" : status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public boolean isStatusInsert() {
		return getStatus().equals("I");
	}
	
	public boolean isStatusDelete() {
		return getStatus().equals("D");
	}
	
	public boolean isStatusUpdate() {
		return getStatus().equals("U");
	}

	public void setStatusInsert() {
		this.status = "I";
	}
	
	public void setStatusDelete() {
		this.status = "D";
	}
	
	public void setStatusUpdate() {
		this.status = "U";
	}
	
	@SuppressWarnings("unchecked")
	public abstract EntidadePK getPK();
	
	
}
