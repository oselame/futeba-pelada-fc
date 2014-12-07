package br.com.softal.pfc;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Codigodescricao implements Serializable {
	
	private Integer cdCodigo;
	private String deDescricao;
	private String sgSigla;
	private Double vlDouble;

	public Codigodescricao() {
	}
	
	public Codigodescricao(Integer codigo, String descricao, String sigla) {
		this.cdCodigo = codigo;
		this.deDescricao = descricao;
		this.sgSigla = sigla;
	}
	
	public Codigodescricao(Integer codigo, String descricao) {
		this.cdCodigo = codigo;
		this.deDescricao = descricao;
	}
	
	public Codigodescricao(String sigla, String descricao) {
		this.sgSigla = sigla;
		this.deDescricao = descricao;
	}
	
	
	public Integer getCdCodigo() {
		return cdCodigo;
	}
	
	public void setCdCodigo(Integer cdCodigo) {
		this.cdCodigo = cdCodigo;
	}
	
	public String getDeDescricao() {
		return deDescricao;
	}
	
	public void setDeDescricao(String deDescricao) {
		this.deDescricao = deDescricao;
	}
	public String getSgSigla() {
		return sgSigla;
	}
	
	public void setSgSigla(String sgSigla) {
		this.sgSigla = sgSigla;
	}
	
	public Double getVlDouble() {
		return vlDouble;
	}
	
	public void setVlDouble(Double vlDouble) {
		this.vlDouble = vlDouble;
	}
}
