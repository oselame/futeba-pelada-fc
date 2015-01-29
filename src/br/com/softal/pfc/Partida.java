package br.com.softal.pfc;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.softal.pfc.util.Util;
 
@SuppressWarnings("serial")
public class Partida extends Entidade {
 
	public static final String SEQ_GERA_CODIGO = "SQ_PARTIDAS";
	
    private PartidaPK partidaPK;
    private Integer nuAno;
    private Integer cdQuadrimestre;
    private Integer cdTimeperdedor;
    private Integer cdTimevencedor;
    private Date dtPartida;
    private Integer flEmpate;
    private Integer nuGolvencedor;
    private Integer nuGolperdedor;
    private String nmJuiz;
    private String deBolamurcha;
    private String deBolacheia;
    private String deObservacao;
    private Integer flConcluida;
    private Integer nuJogadorportime;
    
    private Quadrimestre quadrimestre;
    private Timecamisa timecamisa1;
    private Timecamisa timecamisa2;
    
    //--    
    @SuppressWarnings("unused")
	private String dePlacar;
    private List<Sociopartida> sociosPartida; 
    private String deMes;
 
    public Partida() {
        setPartidaPK(new PartidaPK());
        setTimecamisa1(new Timecamisa());
        setTimecamisa2(new Timecamisa());
        setQuadrimestre(new Quadrimestre());
    }
 
    public PartidaPK getPartidaPK() {
        return this.partidaPK;
    }
 
    public void setPartidaPK(PartidaPK newpartidaPK) {
        this.partidaPK = newpartidaPK;
    }
 
    public Integer getCdQuadrimestre() {
        return this.cdQuadrimestre;
    }
 
    public void setCdQuadrimestre(Integer newCdQuadrimestre) {
        this.cdQuadrimestre = newCdQuadrimestre;
    }
 
    public Integer getCdTimeperdedor() {
        return this.cdTimeperdedor;
    }
 
    public void setCdTimeperdedor(Integer newCdTimeperdedor) {
        this.cdTimeperdedor = newCdTimeperdedor;
    }
 
    public Integer getCdTimevencedor() {
        return this.cdTimevencedor;
    }
 
    public void setCdTimevencedor(Integer newCdTimevencedor) {
        this.cdTimevencedor = newCdTimevencedor;
    }
 
    public Date getDtPartida() {
        return this.dtPartida;
    }
    
    public String getDtPartidaformatada() {
        return Util.dateToString( getDtPartida() );
    }
 
    public void setDtPartida(Date newDtPartida) {
        this.dtPartida = newDtPartida;
    }
    
    public void setDtPartidaformatada(String newDtPartida) {
        this.dtPartida = Util.stringToDate(newDtPartida);
    }
    
 
    public Integer getFlEmpate() {
        return this.flEmpate;
    }
 
    public void setFlEmpate(Integer newFlEmpate) {
        this.flEmpate = newFlEmpate;
    }
 
    public Integer getNuGolvencedor() {
        return this.nuGolvencedor == null ? 0 : this.nuGolvencedor;
    }
 
    public void setNuGolvencedor(Integer newNuGolvencedor) {
        this.nuGolvencedor = newNuGolvencedor;
    }
 
    public Integer getNuGolperdedor() {
        return this.nuGolperdedor == null ? 0 : this.nuGolperdedor;
    }
 
    public void setNuGolperdedor(Integer newNuGolperdedor) {
        this.nuGolperdedor = newNuGolperdedor;
    }
 
    public String getNmJuiz() {
        return this.nmJuiz;
    }
 
    public void setNmJuiz(String newNmJuiz) {
        this.nmJuiz = newNmJuiz;
    }
 
    public String getDeBolamurcha() {
        return this.deBolamurcha;
    }
 
    public void setDeBolamurcha(String newDeBolamurcha) {
        this.deBolamurcha = newDeBolamurcha;
    }
 
    public String getDeBolacheia() {
        return this.deBolacheia;
    }
 
    public void setDeBolacheia(String newDeBolacheia) {
        this.deBolacheia = newDeBolacheia;
    }
 
    public String getDeObservacao() {
        return this.deObservacao;
    }
 
    public void setDeObservacao(String newDeObservacao) {
        this.deObservacao = newDeObservacao;
    }
 
    public Quadrimestre getQuadrimestre() {
        return this.quadrimestre;
    }
 
    public void setQuadrimestre(Quadrimestre newQuadrimestre) {
        this.quadrimestre = newQuadrimestre;
    }
 
    public Timecamisa getTimecamisa1() {
        return this.timecamisa1;
    }
 
    public void setTimecamisa1(Timecamisa newTimecamisa) {
        this.timecamisa1 = newTimecamisa;
    }
 
    public Timecamisa getTimecamisa2() {
        return this.timecamisa2;
    }
 
    public void setTimecamisa2(Timecamisa newTimecamisa) {
        this.timecamisa2 = newTimecamisa;
    }
    
	public Integer getNuAnodatapartida() {
		if (getDtPartida() != null) {
			Calendar c = Calendar.getInstance();
			c.setTime( this.getDtPartida() );
			return (nuAno = c.get(Calendar.YEAR));
		} else {
			return nuAno; 
		}
	}

	public Integer getNuAno() {
		return nuAno; 
	}

	public void setNuAno(Integer nuAno) {
		this.nuAno = nuAno;
	}

	public String getDePlacar() {
		try {
			if (getCdTimevencedor() == 1) {
				return dePlacar = (getNuGolvencedor() + " x " + getNuGolperdedor());
			} else {
				return dePlacar = (getNuGolperdedor() + " x " + getNuGolvencedor());
			}
		} catch (Exception e) {
			return "";
		}
	}

	public void setDePlacar(String dePlacar) {
		this.dePlacar = dePlacar;
	}

	public List<Sociopartida> getSociosPartida() {
		return sociosPartida;
	}

	public void setSociosPartida(List<Sociopartida> sociosPartida) {
		this.sociosPartida = sociosPartida;
	}

	public Integer getFlConcluida() {
		return this.flConcluida == null ? 0 : this.flConcluida;
	}
	
	public boolean isConcluida() {
		return getFlConcluida().intValue()==1;
	}

	public void setFlConcluida(Integer flConcluida) {
		this.flConcluida = flConcluida;
	}

	public String getDeMes() {
		if (getDtPartida() != null) {
			Calendar c = Calendar.getInstance();
			c.setTime( getDtPartida() );
			deMes = Util.getMesExtenso(c.get(Calendar.MONTH));
		}
		return deMes;
	}

	public void setDeMes(String deMes) {
		this.deMes = deMes;
	}
	
	public Integer getNuJogadorportime() {
		return nuJogadorportime;
	}

	public void setNuJogadorportime(Integer nuJogadorportime) {
		this.nuJogadorportime = nuJogadorportime;
	}

	@Override
	public EntidadePK<Partida> getPK() {
		return getPartidaPK();
	}

}
