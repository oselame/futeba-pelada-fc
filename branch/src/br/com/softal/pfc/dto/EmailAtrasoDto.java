package br.com.softal.pfc.dto;

import java.sql.ResultSet;
import java.util.Date;

public class EmailAtrasoDto {
	
	private Date dtPartida;
	private Integer cdSocio;
	private String nmSocio;
	private String deEmail;
	private String deApelido;
	private Integer flAtrazado;
	private Integer flCartaovermelho;
	private Integer flCartaoazul;
	private Integer flCartaoamarelo;
	
	public Date getDtPartida() {
		return dtPartida;
	}
	
	public void setDtPartida(Date dtPartida) {
		this.dtPartida = dtPartida;
	}
	
	public Integer getCdSocio() {
		return cdSocio;
	}
	
	public void setCdSocio(Integer cdSocio) {
		this.cdSocio = cdSocio;
	}
	
	public String getNmSocio() {
		return nmSocio;
	}
	
	public void setNmSocio(String nmSocio) {
		this.nmSocio = nmSocio;
	}
	
	public String getDeEmail() {
		return deEmail;
	}
	
	public void setDeEmail(String deEmail) {
		this.deEmail = deEmail;
	}
	
	public String getDeApelido() {
		return deApelido;
	}
	
	public void setDeApelido(String deApelido) {
		this.deApelido = deApelido;
	}
	
	public Integer getFlAtrazado() {
		return flAtrazado;
	}
	
	public void setFlAtrazado(Integer flAtrazado) {
		this.flAtrazado = flAtrazado;
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
	
	public static EmailAtrasoDto popule(ResultSet rs) {
		EmailAtrasoDto dto = new EmailAtrasoDto();
		try { dto.setDtPartida( rs.getDate("dtPartida") ); } catch (Exception e) {}
		try { dto.setCdSocio( rs.getInt("cdSocio") ); } catch (Exception e) {}
		try { dto.setNmSocio( rs.getString("nmSocio") ); } catch (Exception e) {}
		try { dto.setDeEmail( rs.getString("deEmail") ); } catch (Exception e) {}
		try { dto.setDeApelido( rs.getString("nmApelido") ); } catch (Exception e) {}
		try { dto.setFlAtrazado( rs.getInt("flAtrazado") ); } catch (Exception e) {}
		try { dto.setFlCartaovermelho( rs.getInt("flCartaovermelho") ); } catch (Exception e) {}
		try { dto.setFlCartaoazul( rs.getInt("flCartaoazul") ); } catch (Exception e) {}
		try { dto.setFlCartaoamarelo( rs.getInt("flCartaoamarelo") ); } catch (Exception e) {}
		return dto;
	}
	
	
}
