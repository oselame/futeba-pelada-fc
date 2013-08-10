package br.com.softal.pfc;
 
 
@SuppressWarnings("serial")
public class Anexo extends Entidade {
 
    private AnexoPK anexoPK;
    private String nmAnexo;    
    private Integer nuTamanho; 
    private byte[] blAnexo;
    private Integer cdSocio;
    private Integer cdNoticia;
    
    
    private Socio socio;
    private Noticia noticia;
	
    public Anexo() {
		setAnexoPK(new AnexoPK());
		//-- setSocio(new Socio());
		//-- setNoticia(new Noticia());
	}
    
    public AnexoPK getAnexoPK() {
		return anexoPK;
	}
	
	public void setAnexoPK(AnexoPK anexoPK) {
		this.anexoPK = anexoPK;
	}
	
	public String getNmAnexo() {
		return nmAnexo;
	}
	
	public void setNmAnexo(String nmAnexo) {
		this.nmAnexo = nmAnexo;
	}
	
	public byte[] getBlAnexo() {
		return blAnexo;
	}
	
	public void setBlAnexo(byte[] blAnexo) {
		this.blAnexo = blAnexo;
	}
	
	public Integer getNuTamanho() {
		return nuTamanho;
	}
	
	public void setNuTamanho(Integer nuTamanho) {
		this.nuTamanho = nuTamanho;
	}
	
	public Integer getCdSocio() {
		return cdSocio;
	}
	
	public void setCdSocio(Integer cdSocio) {
		this.cdSocio = cdSocio;
	}
	
	public Integer getCdNoticia() {
		return cdNoticia;
	}
	
	public void setCdNoticia(Integer cdNoticia) {
		this.cdNoticia = cdNoticia;
	}
	
	public Socio getSocio() {
		return socio;
	}
	
	public void setSocio(Socio socio) {
		this.socio = socio;
	}
	
	public Noticia getNoticia() {
		return noticia;
	}

	
	public void setNoticia(Noticia noticia) {
		this.noticia = noticia;
	}

	@Override
	public EntidadePK<Anexo> getPK() {
		return getAnexoPK();
	}
    
}
