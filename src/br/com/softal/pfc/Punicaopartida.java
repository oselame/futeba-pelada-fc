package br.com.softal.pfc;

import java.sql.ResultSet;

@SuppressWarnings("serial")
public class Punicaopartida extends Entidade {

	private PunicaopartidaPK punicaopartidaPK;
	private Integer nuPontospunicao;
	private String dePunicao;
	
	private Partida partida;
	private Socio socio;

	public Punicaopartida() {
		setPunicaopartidaPK(new PunicaopartidaPK());
		setPartida(new Partida());
		setSocio(new Socio());
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

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	public Socio getSocio() {
		return socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}
	
	public Integer getNuPontospunicao() {
		return nuPontospunicao;
	}

	public void setNuPontospunicao(Integer nuPontospunicao) {
		this.nuPontospunicao = nuPontospunicao;
	}

	public String getDePunicao() {
		return dePunicao;
	}

	public void setDePunicao(String dePunicao) {
		this.dePunicao = dePunicao;
	}

	public static Punicaopartida popule(ResultSet rs) {
		Punicaopartida pp = new Punicaopartida();
		try { pp.getPunicaopartidaPK().setCdPartida( rs.getInt("cdPartida") ); } catch (Exception e) {}
		try { pp.getPunicaopartidaPK().setCdSocio( rs.getInt("cdSocio")); } catch (Exception e) {}
		try { pp.setNuPontospunicao( rs.getInt("nuPontospunicao")); } catch (Exception e) {}
		try { pp.setDePunicao( rs.getString("dePunicao")); } catch (Exception e) {}
		return pp;
	}
	
}
