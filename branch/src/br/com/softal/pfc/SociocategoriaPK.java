package br.com.softal.pfc;
 
@SuppressWarnings("serial")
public class SociocategoriaPK extends EntidadePK<Sociocategoria> {
 
    private Integer cdSocio;
    private Integer cdCategoria;
 
    public Integer getCdSocio() {
        return this.cdSocio;
    }
 
    public void setCdSocio(Integer newCdSocio) {
        this.cdSocio = newCdSocio;
    }
 
    public Integer getCdCategoria() {
        return this.cdCategoria;
    }
 
    public void setCdCategoria(Integer newCdCategoria) {
        this.cdCategoria = newCdCategoria;
    }
 
}
