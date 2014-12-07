package br.com.softal.pfc;
 
import java.util.Date;
 
@SuppressWarnings("serial")
public class Ausencia extends Entidade {
 
    private AusenciaPK ausenciaPK;
    private Date dtCadastro;
    private Date dtRetorno;
    private String deMotivo;
    private Socio socio;
 
    public Ausencia() {
        setAusenciaPK(new AusenciaPK());
    }
 
    public AusenciaPK getAusenciaPK() {
        return this.ausenciaPK;
    }
 
    public void setAusenciaPK(AusenciaPK newausenciaPK) {
        this.ausenciaPK = newausenciaPK;
    }
 
    public Date getDtCadastro() {
        return this.dtCadastro;
    }
 
    public void setDtCadastro(Date newDtCadastro) {
        this.dtCadastro = newDtCadastro;
    }
 
    public Date getDtRetorno() {
        return this.dtRetorno;
    }
 
    public void setDtRetorno(Date newDtRetorno) {
        this.dtRetorno = newDtRetorno;
    }
 
    public String getDeMotivo() {
        return this.deMotivo;
    }
 
    public void setDeMotivo(String newDeMotivo) {
        this.deMotivo = newDeMotivo;
    }
 
    public Socio getSocio() {
        return this.socio;
    }
 
    public void setSocio(Socio newSocio) {
        this.socio = newSocio;
    }

	@Override
	public EntidadePK<Ausencia> getPK() {
		return getAusenciaPK();
	}
}
