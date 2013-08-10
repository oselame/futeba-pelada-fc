package br.com.softal.pfc;

 
@SuppressWarnings("serial")
public class Categoria extends Entidade {
 
    private CategoriaPK categoriaPK;
    private String nmCategoria;
 
    public Categoria() {
        setCategoriaPK(new CategoriaPK());
    }
 
    public CategoriaPK getCategoriaPK() {
        return this.categoriaPK;
    }
 
    public void setCategoriaPK(CategoriaPK newcategoriaPK) {
        this.categoriaPK = newcategoriaPK;
    }
 
    public String getNmCategoria() {
        return this.nmCategoria;
    }
 
    public void setNmCategoria(String newNmCategoria) {
        this.nmCategoria = newNmCategoria;
    }

	@Override
	public EntidadePK<Categoria> getPK() {
		return getCategoriaPK();
	}
    
}
