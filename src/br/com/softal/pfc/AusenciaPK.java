package br.com.softal.pfc;

 
@SuppressWarnings("serial")
public class AusenciaPK extends EntidadePK<Ausencia> {
 
    private Integer cdSocio;
    private Integer cdAusencia;
 
    public Integer getCdSocio() {
        return this.cdSocio;
    }
 
    public void setCdSocio(Integer newCdSocio) {
        this.cdSocio = newCdSocio;
    }
 
    public Integer getCdAusencia() {
        return this.cdAusencia;
    }
 
    public void setCdAusencia(Integer newCdAusencia) {
        this.cdAusencia = newCdAusencia;
    }
 
}
