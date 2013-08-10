package br.com.softal.pfc;
 
 
@SuppressWarnings("serial")
public class Fotogaleria extends Entidade {
 
    private FotogaleriaPK fotogaleriaPK;
    private Integer cdGaleria;
    private String nmArqfoto;
    
    public Fotogaleria() {
		setFotogaleriaPK(new FotogaleriaPK());
	}
    
	public FotogaleriaPK getFotogaleriaPK() {
		return fotogaleriaPK;
	}
	public void setFotogaleriaPK(FotogaleriaPK fotogaleriaPK) {
		this.fotogaleriaPK = fotogaleriaPK;
	}
	public Integer getCdGaleria() {
		return cdGaleria;
	}
	public void setCdGaleria(Integer cdGaleria) {
		this.cdGaleria = cdGaleria;
	}
	public String getNmArqfoto() {
		return nmArqfoto;
	}
	public void setNmArqfoto(String nmArqfoto) {
		this.nmArqfoto = nmArqfoto;
	}

	@Override
	public EntidadePK<Fotogaleria> getPK() {
		return getFotogaleriaPK();
	}
    
}
