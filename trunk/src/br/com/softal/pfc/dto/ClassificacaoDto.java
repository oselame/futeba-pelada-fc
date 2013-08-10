package br.com.softal.pfc.dto;

 
@SuppressWarnings("unchecked")
public class ClassificacaoDto implements Comparable {

	private Integer cdSocio; 
	private String nmApelido; 
	private String nmSocio; 
	private Integer cdTime;
	private Integer nuGol;
	private Integer flCartaovermelho; 
	private Integer flCartaoazul;
	private Integer flCartaoamarelo;
	private Integer cdTimevencedor; 
	private Integer cdTimeperdedor; 
	private Integer flEmpate;
	
	private Integer nuPontos;
	private Integer nuPosicao;	
	private Integer cdQuadrimestre;
	private Integer nuAno;
	
	@Override
	public String toString() {
		return  "Pontos=" + getNuPontos() + ", " + 
			"Posicao=" + getNuPosicao() + ", " +
			"Quadrimestre=" + getCdQuadrimestre() + ", " +
			"Ano=" + getNuAno();
	}
	
	public Integer getCdSocio() {
		return cdSocio;
	}
	public void setCdSocio(Integer cdSocio) {
		this.cdSocio = cdSocio;
	}
	public String getNmApelido() {
		return nmApelido;
	}
	public void setNmApelido(String nmApelido) {
		this.nmApelido = nmApelido;
	}
	public String getNmSocio() {
		return nmSocio;
	}
	public void setNmSocio(String nmSocio) {
		this.nmSocio = nmSocio;
	}
	public Integer getCdTime() {
		return cdTime;
	}
	public void setCdTime(Integer cdTime) {
		this.cdTime = cdTime;
	}
	public Integer getNuGol() {
		return nuGol;
	}
	public void setNuGol(Integer nuGol) {
		this.nuGol = nuGol;
	}
	public Integer getFlCartaovermelho() {
		return flCartaovermelho;
	}
	public void setFlCartaovermelho(Integer flCartaovermelho) {
		this.flCartaovermelho = flCartaovermelho;
	}
	public Integer getFlCartaoazul() {
		return flCartaoazul;
	}
	public void setFlCartaoazul(Integer flCartaoazul) {
		this.flCartaoazul = flCartaoazul;
	}
	public Integer getFlCartaoamarelo() {
		return flCartaoamarelo;
	}
	public void setFlCartaoamarelo(Integer flCartaoamarelo) {
		this.flCartaoamarelo = flCartaoamarelo;
	}
	public Integer getCdTimevencedor() {
		return cdTimevencedor;
	}
	public void setCdTimevencedor(Integer cdTimevencedor) {
		this.cdTimevencedor = cdTimevencedor;
	}
	public Integer getCdTimeperdedor() {
		return cdTimeperdedor;
	}
	public void setCdTimeperdedor(Integer cdTimeperdedor) {
		this.cdTimeperdedor = cdTimeperdedor;
	}
	public Integer getFlEmpate() {
		return flEmpate;
	}
	public void setFlEmpate(Integer flEmpate) {
		this.flEmpate = flEmpate;
	}
	
	public Integer getNuPontos() {
		/*
		String sAux;
		if (getFlEmpate() == 0) {
			sAux = "10";
		} else if (getCdTime().intValue() == getCdTimevencedor().intValue()) {
			sAux = "31";
		} else {
			sAux = "00";
		}
		*/
		return nuPontos;
	}
	
	public void setNuPontos(Integer nuPontos) {
		this.nuPontos = nuPontos;
	}
	public Integer getNuPosicao() {
		return nuPosicao;
	}
	public void setNuPosicao(Integer nuPosicao) {
		this.nuPosicao = nuPosicao;
	}
	public int compareTo(Object o) {
		if (o instanceof ClassificacaoDto) {
			
		}
		return 0;
	}
	public Integer getCdQuadrimestre() {
		return cdQuadrimestre;
	}
	public void setCdQuadrimestre(Integer cdQuadrimestre) {
		this.cdQuadrimestre = cdQuadrimestre;
	}
	public Integer getNuAno() {
		return nuAno;
	}
	public void setNuAno(Integer nuAno) {
		this.nuAno = nuAno;
	}
	
}
