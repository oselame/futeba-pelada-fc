package br.com.softal.pfc;

 
@SuppressWarnings("serial")
public class SocioPK extends EntidadePK<Socio> {
 
    private Integer cdSocio;
 
    public Integer getCdSocio() {
        return this.cdSocio;
    }
 
    public void setCdSocio(Integer newCdSocio) {
        this.cdSocio = newCdSocio;
    }
 
}
