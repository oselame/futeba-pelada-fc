package br.com.softal.pfc.dto;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.softal.pfc.Entidade;
import br.com.softal.pfc.EntidadePK;
import br.com.softal.pfc.util.Util;

public class RelatorioDto extends Entidade {

	private static final long serialVersionUID = 1L;
	
	private Integer nuAno;
	private Integer nuMes;
	private String dsMes;
	
	private Integer cdSocio;
	private String nmSocios;
	private Date dtPartida;
	private Integer nuGolvencedor;
	private Integer nuGolperdedor;
	private String nmApelido;
	
	private List<RelatorioDto> socios;
	
	private List<RelatorioDto> meses;
	private List<RelatorioDto> datas;
	
	
	public RelatorioDto() {
		setSocios(new ArrayList<RelatorioDto>());
		setMeses(new ArrayList<RelatorioDto>());
		setDatas(new ArrayList<RelatorioDto>());
		
	}
	
	public Integer getNuAno() {
		return nuAno;
	}
	
	public void setNuAno(Integer nuAno) {
		this.nuAno = nuAno;
	}
	
	public Integer getNuMes() {
		return nuMes;
	}
	
	public void setNuMes(Integer nuMes) {
		this.nuMes = nuMes;
	}
	
	public Integer getCdSocio() {
		return cdSocio;
	}
	
	public void setCdSocio(Integer cdSocio) {
		this.cdSocio = cdSocio;
	}

	public String getNmSocios() {
		return nmSocios;
	}

	public void setNmSocios(String nmSocios) {
		this.nmSocios = nmSocios;
	}

	public List<RelatorioDto> getSocios() {
		return socios;
	}

	public void setSocios(List<RelatorioDto> socios) {
		this.socios = socios;
	}

	public Date getDtPartida() {
		return dtPartida;
	}

	public void setDtPartida(Date dtPartida) {
		this.dtPartida = dtPartida;
	}

	public Integer getNuGolvencedor() {
		return nuGolvencedor;
	}

	public void setNuGolvencedor(Integer nuGolvencedor) {
		this.nuGolvencedor = nuGolvencedor;
	}

	public Integer getNuGolperdedor() {
		return nuGolperdedor;
	}

	public void setNuGolperdedor(Integer nuGolperdedor) {
		this.nuGolperdedor = nuGolperdedor;
	}

	public String getNmApelido() {
		return nmApelido;
	}

	public void setNmApelido(String nmApelido) {
		this.nmApelido = nmApelido;
	}
	
	public String getDtPartidastring() {
        return Util.getDataFormatada( this.dtPartida );
    }

    public void setDtPartidastring(String newdtPartida) {
        this.dtPartida = Util.stringToDate( newdtPartida ) ;
    }
    
	public List<RelatorioDto> getMeses() {
		return meses;
	}

	public void setMeses(List<RelatorioDto> meses) {
		this.meses = meses;
	}

	public List<RelatorioDto> getDatas() {
		return datas;
	}

	public void setDatas(List<RelatorioDto> datas) {
		this.datas = datas;
	}
	
	public String getDsMes() {
		dsMes = Util.getRessource("label.mes." + (getNuMes()+1));
		return dsMes;
	}

	public void setDsMes(String dsMes) {
		this.dsMes = dsMes;
	}

	public static RelatorioDto popule(ResultSet rs) {
		RelatorioDto dto = new RelatorioDto();
		try { dto.setNuAno( rs.getInt("nuAno") ); } catch (Exception e) {}
		try { dto.setNuMes( rs.getInt("nuMes") ); } catch (Exception e) {}
		try { dto.setCdSocio( rs.getInt("cdSocio") ); } catch (Exception e) {}
		try { dto.setDtPartida( rs.getDate("dtPartida") ); } catch (Exception e) {}
		try { dto.setNuGolvencedor( rs.getInt("nuGolvencedor") ); } catch (Exception e) {}
		try { dto.setNuGolperdedor( rs.getInt("nuGolperdedor") ); } catch (Exception e) {}
		try { dto.setNmApelido( rs.getString("nmApelido") ); } catch (Exception e) {}
		return dto;
	}
	
	@Override
	public EntidadePK getPK() {
		return null;
	}
	
}
