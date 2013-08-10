package br.com.softal.pfc;

 
@SuppressWarnings("serial")
public class Convocacao extends Entidade {
 
    private ConvocacaoPK convocacaoPK;
    private Partida partida;
    private Socio socio;
 
    public Convocacao() {
        setConvocacaoPK(new ConvocacaoPK());
    }
 
    public ConvocacaoPK getConvocacaoPK() {
        return this.convocacaoPK;
    }
 
    public void setConvocacaoPK(ConvocacaoPK newconvocacaoPK) {
        this.convocacaoPK = newconvocacaoPK;
    }
 
    public Partida getPartida() {
        return this.partida;
    }
 
    public void setPartida(Partida newPartida) {
        this.partida = newPartida;
    }
 
    public Socio getSocio() {
        return this.socio;
    }
 
    public void setSocio(Socio newSocio) {
        this.socio = newSocio;
    }

	@Override
	public EntidadePK<Convocacao> getPK() {
		return getConvocacaoPK();
	}
}
