package br.com.softal.pfc;
 
@SuppressWarnings("serial")
public class Titulodisputa extends Entidade {
 
    private TitulodisputaPK titulodisputaPK;
    private String deTitulo;
    private Integer flForauso;
 
    public Titulodisputa() {
        setTitulodisputaPK(new TitulodisputaPK());
    }
 
    public TitulodisputaPK getTitulodisputaPK() {
        return this.titulodisputaPK;
    }
 
    public void setTitulodisputaPK(TitulodisputaPK newtitulodisputaPK) {
        this.titulodisputaPK = newtitulodisputaPK;
    }
 
    public String getDeTitulo() {
        return this.deTitulo;
    }
 
    public void setDeTitulo(String newDeTitulo) {
        this.deTitulo = newDeTitulo;
    }
 
    public Integer getFlForauso() {
        return this.flForauso;
    }
 
    public void setFlForauso(Integer newFlForauso) {
        this.flForauso = newFlForauso;
    }
    
    @Override
    public EntidadePK<Titulodisputa> getPK() {
    	return getTitulodisputaPK();
    }
    
}
