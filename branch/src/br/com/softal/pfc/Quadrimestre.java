package br.com.softal.pfc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.softal.pfc.util.Util;
 
public class Quadrimestre extends Entidade {
	
	private static final long serialVersionUID = 1L;
	
	public static final int ANO_INICIO = 2008;
	public static final int ANO_TERMINO = 2030;	
	
    private QuadrimestrePK quadrimestrePK;
    private Integer cdSociocampeao;
    private Integer cdTitulo;
    
    private Date dtInicio;
    private Date dtFim;
    private Integer nuJogos;
    private Integer flAnual;
    private Integer nuJogoscampeao;
    
    private String deQuadrimestre;
    private Integer nuTitulos;
    private Socio socio;
    private Titulodisputa titulodisputa;
    
    private List<Quadrimestre> quadrimestres;
 
    public Quadrimestre() {
        setQuadrimestrePK(new QuadrimestrePK());
        setSocio(new Socio());
        setQuadrimestres(new ArrayList<Quadrimestre>());
    }
 
    public QuadrimestrePK getQuadrimestrePK() {
        return this.quadrimestrePK;
    }
 
    public void setQuadrimestrePK(QuadrimestrePK newquadrimestrePK) {
        this.quadrimestrePK = newquadrimestrePK;
    }
 
    public Integer getCdSociocampeao() {
        return this.cdSociocampeao;
    }
 
    public void setCdSociocampeao(Integer newCdSociocampeao) {
        this.cdSociocampeao = newCdSociocampeao;
    }
 
    public Integer getCdTitulo() {
        return this.cdTitulo;
    }
 
    public void setCdTitulo(Integer newCdTitulo) {
        this.cdTitulo = newCdTitulo;
    }
 
    public Date getDtInicio() {
        return this.dtInicio;
    }
 
    public void setDtInicio(Date newDtInicio) {
        this.dtInicio = newDtInicio;
    }
    
    public String getDtIniciostring() {
        return Util.getDataFormatada( this.dtInicio );
    }

    public void setDtIniciostring(String newDtInicio) {
        this.dtInicio = Util.stringToDate( newDtInicio ) ;
    }
 
    public Date getDtFim() {
        return this.dtFim;
    }
 
    public void setDtFim(Date newDtFim) {
        this.dtFim = newDtFim;
    }
    
    public String getDtFimstring() {
        return Util.getDataFormatada( this.dtFim );
    }

    public void setDtFimstring(String newDtFim) {
        this.dtFim = Util.stringToDate( newDtFim ) ;
    }   
 
    public Integer getNuJogos() {
        return this.nuJogos;
    }
 
    public void setNuJogos(Integer newNuJogos) {
        this.nuJogos = newNuJogos;
    }
 
    public Integer getFlAnual() {
        return this.flAnual;
    }
 
    public void setFlAnual(Integer newFlAnual) {
        this.flAnual = newFlAnual;
    }
 
    public Integer getNuJogoscampeao() {
        return this.nuJogoscampeao;
    }
 
    public void setNuJogoscampeao(Integer newNuJogoscampeao) {
        this.nuJogoscampeao = newNuJogoscampeao;
    }
 
    public Socio getSocio() {
        return this.socio;
    }
 
    public void setSocio(Socio newSocio) {
        this.socio = newSocio;
    }
 
    public Titulodisputa getTitulodisputa() {
        return this.titulodisputa;
    }
 
    public void setTitulodisputa(Titulodisputa newTitulodisputa) {
        this.titulodisputa = newTitulodisputa;
    }

	public String getDeQuadrimestre() {
		String s = deQuadrimestre;
		try {
			s = getQuadrimestrePK().getCdQuadrimestre() + "º quadrimestre de " + getQuadrimestrePK().getNuAno();
		} catch (Exception e) {}
		return s;
	}

	public void setDeQuadrimestre(String deQuadrimestre) {
		this.deQuadrimestre = deQuadrimestre;
	}

	public List<Quadrimestre> getQuadrimestres() {
		return quadrimestres;
	}

	public void setQuadrimestres(List<Quadrimestre> quadrimestres) {
		this.quadrimestres = quadrimestres;
	}

	public Integer getNuTitulos() {
		return nuTitulos == null ? 0 : nuTitulos;
	}

	public void setNuTitulos(Integer nuTitulos) {
		this.nuTitulos = nuTitulos;
	}
	
	@Override
	public EntidadePK<Quadrimestre> getPK() {
		return getQuadrimestrePK();
	}
    
}
