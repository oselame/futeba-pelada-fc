package br.com.softal.pfc;

import java.util.Date;

import br.com.softal.pfc.util.Util;
 
@SuppressWarnings("serial")
public class Noticia extends Entidade {
	
	public static final int TIPO_NOTICIA = 1;
	public static final int TIPO_EVENTO = 2;
 
    private NoticiaPK noticiaPK;
    private String deNoticia;    
    private Date dtNoticia;
    private String deLink;
    private String deTitulo;
    private Date dtFimevento;
    private Integer tpNoticia;    
    private Integer flPopup;    
	
    private String styleClass;
 
    public Noticia() {
        setNoticiaPK(new NoticiaPK());
    }
 
    public NoticiaPK getNoticiaPK() {
        return this.noticiaPK;
    }
 
    public void setNoticiaPK(NoticiaPK newnoticiaPK) {
        this.noticiaPK = newnoticiaPK;
    }
 
    public Date getDtNoticia() {
        return this.dtNoticia;
    }
 
    public void setDtNoticia(Date newDtNoticia) {
        this.dtNoticia = newDtNoticia;
    }
    
    public String getDtNoticiastring() {
        return Util.getDataFormatada( this.dtNoticia );
    }

    public void setDtNoticiastring(String newDtNoticia) {
        this.dtNoticia = Util.stringToDate( newDtNoticia ) ;
    }
 
    public String getDeNoticia() {
        return this.deNoticia;
    }
 
    public void setDeNoticia(String newDeNoticia) {
        this.deNoticia = newDeNoticia;
    }
 
    /*
    public byte[] getImNoticia() {
        return this.imNoticia;
    }
 
    public void setImNoticia(byte[] newImNoticia) {
        this.imNoticia = newImNoticia;
    }*/
 
    public String getDeLink() {
        return this.deLink;
    }
 
    public void setDeLink(String newDeLink) {
        this.deLink = newDeLink;
    }

	public String getDeTitulo() {
		return deTitulo;
	}

	public void setDeTitulo(String deTitulo) {
		this.deTitulo = deTitulo;
	}

	public Integer getTpNoticia() {
		return tpNoticia;
	}

	public void setTpNoticia(Integer tpNoticia) {
		this.tpNoticia = tpNoticia;
	}

	public Date getDtFimevento() {
		return dtFimevento;
	}

	public void setDtFimevento(Date dtFimevento) {
		this.dtFimevento = dtFimevento;
	}
	
    public String getDtFimeventostring() {
        return Util.getDataFormatada( this.dtFimevento );
    }

    public void setDtFimeventostring(String newDtFimevento) {
        this.dtFimevento = Util.stringToDate( newDtFimevento ) ;
    }

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}
	
	public Integer getFlPopup() {
		return this.flPopup == null ? 0 : this.flPopup;
	}

	public void setFlPopup(Integer flPopup) {
		this.flPopup = flPopup;
	}
	
	public Boolean getBlPopup() {
		return (this.flPopup == 1);
	}
	
	public void setBlPopup(Boolean blPopup) {
		this.flPopup = (blPopup == null ? 0 : (blPopup ? 1 : 0));
	}
	
	@Override
	public EntidadePK<Noticia> getPK() {
		return getNoticiaPK();
	}
    
}
