package br.com.softal.pfc;

 
@SuppressWarnings("serial")
public class NoticiaPK extends EntidadePK<Noticia> {
 
    private Integer cdNoticia;
 
    public Integer getCdNoticia() {
        return this.cdNoticia;
    }
 
    public void setCdNoticia(Integer newCdNoticia) {
        this.cdNoticia = newCdNoticia;
    }
 
}
