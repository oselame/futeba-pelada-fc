package br.com.softal.pfc;

 
@SuppressWarnings("serial")
public class Sociopartida extends Entidade {
 
    private SociopartidaPK sociopartidaPK;
    private Integer cdTime;
    private Integer nuGol;
    private Integer nuGolcontra;
    private Integer flGoleiro;
    private Integer flCartaovermelho;
    private Integer flCartaoazul;
    private Integer flCartaoamarelo;
    private Integer flAtrazado;
    
    private Partida partida;
    private Socio socio;
    private Timecamisa timecamisa;
    private Classificacao classificacao;
    @SuppressWarnings("unused")
	private Integer tpCartao;
    
    private Boolean mostraForauso;
    
    public Sociopartida() {
        setSociopartidaPK(new SociopartidaPK());
        setSocio(new Socio());
        setPartida(new Partida());
        //setClassificacao(new Classificacao());
        setTimecamisa(new Timecamisa());
        setFlCartaovermelho(0);
        setFlCartaoazul(0);
        setFlCartaoamarelo(0);
        setFlAtrazado(0);
    }
 
    public SociopartidaPK getSociopartidaPK() {
        return this.sociopartidaPK;
    }
 
    public void setSociopartidaPK(SociopartidaPK newsociopartidaPK) {
        this.sociopartidaPK = newsociopartidaPK;
    }
 
    public Integer getCdTime() {
        return this.cdTime;
    }
 
    public void setCdTime(Integer newCdTime) {
        this.cdTime = newCdTime;
    }
 
    public Integer getNuGol() {
        return this.nuGol;
    }
 
    public void setNuGol(Integer newNuGol) {
        this.nuGol = newNuGol;
    }
 
    public Integer getFlCartaovermelho() {
        return this.flCartaovermelho;
    }
 
    public void setFlCartaovermelho(Integer newFlCartaovermelho) {
        this.flCartaovermelho = newFlCartaovermelho;
    }
 
    public Integer getFlCartaoazul() {
        return this.flCartaoazul;
    }
 
    public void setFlCartaoazul(Integer newFlCartaoazul) {
        this.flCartaoazul = newFlCartaoazul;
    }
 
    public Integer getFlCartaoamarelo() {
        return this.flCartaoamarelo;
    }
 
    public void setFlCartaoamarelo(Integer newFlCartaoamarelo) {
        this.flCartaoamarelo = newFlCartaoamarelo;
    }
 
    public Partida getPartida() {
        return this.partida;
    }
 
    public void setPartida(Partida newPartida) {
        this.partida = newPartida;
    }
 
    public Socio getSocio() {
        return this.socio;
    }
 
    public void setSocio(Socio newSocio) {
        this.socio = newSocio;
    }
 
    public Timecamisa getTimecamisa() {
        return this.timecamisa;
    }
 
    public void setTimecamisa(Timecamisa newTimecamisa) {
        this.timecamisa = newTimecamisa;
    }
 
    public Classificacao getClassificacao() {
        return this.classificacao;
    }
 
    public void setClassificacao(Classificacao newClassificacao) {
        this.classificacao = newClassificacao;
    }

	public Integer getTpCartao() {
		if (getFlCartaovermelho() == 1) {
			return 1;	
		} else if (getFlCartaoazul() == 1) {
			return 2;	
		} else if (getFlCartaoamarelo() == 1) {
			return 3;	
		} else {
			return 0;
		}
	}

	public Integer getNuGolcontra() {
		return nuGolcontra;
	}

	public void setNuGolcontra(Integer nuGolcontra) {
		this.nuGolcontra = nuGolcontra;
	}
	
	public Integer getFlGoleiro() {
		return flGoleiro == null ? 0 : flGoleiro;
	}

	public void setFlGoleiro(Integer flGoleiro) {
		this.flGoleiro = flGoleiro;
	}

	public void setTpCartao(Integer tpCartao) {
		this.tpCartao = tpCartao;
		if (tpCartao == 1) {
			setFlCartaovermelho( 1 );
			setFlCartaoazul( 0 );
			setFlCartaoamarelo( 0 );			
		} else if (tpCartao == 2) {
			setFlCartaovermelho( 0 );
			setFlCartaoazul( 1 );
			setFlCartaoamarelo( 0 );		
		} else if (tpCartao == 3) {
			setFlCartaovermelho( 0 );
			setFlCartaoazul( 0 );
			setFlCartaoamarelo( 1 );	
		} else {
			setFlCartaovermelho( 0 );
			setFlCartaoazul( 0 );
			setFlCartaoamarelo( 0 );	
		}
	}

	public Boolean getMostraForauso() {
		return mostraForauso == null ? false : mostraForauso;
	}

	public void setMostraForauso(Boolean mostraForauso) {
		this.mostraForauso = mostraForauso;
	}

	public Integer getFlAtrazado() {
		return flAtrazado;
	}

	public void setFlAtrazado(Integer flAtrazado) {
		this.flAtrazado = flAtrazado;
	}
	
	@Override
	public EntidadePK<Sociopartida> getPK() {
		return getSociopartidaPK();
	}

	public String getFlAtrazadostr() {
		return getFlAtrazado() != null && getFlAtrazado() == 1 ? "on" : "";
	}

	public void setFlAtrazadostr(String flAtrazadostr) {
		if (flAtrazadostr != null && flAtrazadostr.equals("on")) {
			this.flAtrazado = 1;
		} else {
			this.flAtrazado = 0;
		}
	}

	public String getFlGoleirostr() {
		return getFlGoleiro() != null && getFlGoleiro() == 1 ? "on" : "";
	}

	public void setFlGoleirostr(String flGoleirostr) {
		if (flGoleirostr != null && flGoleirostr.equals("on")) {
			this.flGoleiro = 1;
		} else {
			this.flGoleiro = 0;
		}
	}
	
}
