package br.com.softal.pfc;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.softal.pfc.util.Util;
 
@SuppressWarnings("serial")
public class Galeria extends Entidade {
 
    private GaleriaPK galeriaPK;
    private String deGaleria;
    private Date dtGaleria;
    
    private List<Fotogaleria> fotogaleria;
    private Integer nuFotos;

    public Galeria() {
		setGaleriaPK(new GaleriaPK());
		setFotogaleria(new ArrayList<Fotogaleria>());
	}
    
    public GaleriaPK getGaleriaPK() {
		return galeriaPK;
	}
    
	public void setGaleriaPK(GaleriaPK galeriaPK) {
		this.galeriaPK = galeriaPK;
	}
	
	public String getDeGaleria() {
		return deGaleria;
	}
	
	public void setDeGaleria(String deGaleria) {
		this.deGaleria = deGaleria;
	}
	
	public Date getDtGaleria() {
		return dtGaleria;
	}
	
	public void setDtGaleria(Date dtGaleria) {
		this.dtGaleria = dtGaleria;
	}
	
	public String getDtGaleriastring() {
		return Util.getDataFormatada( this.dtGaleria );
	}
	
	public void setDtGaleriastring(String dtGaleriastring) {
		this.dtGaleria = Util.stringToDate( dtGaleriastring ) ;
	}

	public List<Fotogaleria> getFotogaleria() {
		return fotogaleria;
	}

	public void setFotogaleria(List<Fotogaleria> fotogaleria) {
		this.fotogaleria = fotogaleria;
	}

	public Integer getNuFotos() {
		return nuFotos == null ? 0 : nuFotos;
	}

	public void setNuFotos(Integer nuFotos) {
		this.nuFotos = nuFotos;
	}


	@Override
	public EntidadePK<Galeria> getPK() {
		return getGaleriaPK();
	}
	
}
