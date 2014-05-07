package br.com.softal.pfc;

 
@SuppressWarnings("serial")
public class PunicaopartidaPK extends EntidadePK<Punicaopartida> {
 
    private Integer cdSocio;
    private Integer cdPartida;
 
    public Integer getCdSocio() {
        return this.cdSocio;
    }
 
    public void setCdSocio(Integer newCdSocio) {
        this.cdSocio = newCdSocio;
    }
 
    public Integer getCdPartida() {
        return this.cdPartida;
    }
 
    public void setCdPartida(Integer newCdPartida) {
        this.cdPartida = newCdPartida;
    }
 
}
