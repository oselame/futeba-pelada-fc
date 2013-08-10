package br.com.softal.pfc;

 
@SuppressWarnings("serial")
public class CategoriaPK extends EntidadePK<Categoria> {
 
    private Integer cdCategoria;
 
    public Integer getCdCategoria() {
        return this.cdCategoria;
    }
 
    public void setCdCategoria(Integer newCdCategoria) {
        this.cdCategoria = newCdCategoria;
    }
 
}
