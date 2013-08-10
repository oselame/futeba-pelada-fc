package br.com.softal.pfc;
 
@SuppressWarnings("serial")
public class QuadrimestrePK extends EntidadePK<Quadrimestre> {
 
	private Integer nuAno;
	private Integer cdQuadrimestre;
 
    public Integer getCdQuadrimestre() {
        return this.cdQuadrimestre;
    }
 
    public void setCdQuadrimestre(Integer newCdQuadrimestre) {
        this.cdQuadrimestre = newCdQuadrimestre;
    }

    public Integer getNuAno() {
        return this.nuAno;
    }
 
    public void setNuAno(Integer newNuAno) {
        this.nuAno = newNuAno;
    }
}
