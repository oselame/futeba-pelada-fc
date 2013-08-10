package br.com.softal.pfc;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.softal.pfc.util.Util;
 
@SuppressWarnings("serial")
public class Socio extends Entidade {
 
    public static final String SEQ_GERA_CODIGO = "SQ_SOCIOS";
    
	private SocioPK socioPK;
    private String nmSocio;
    private String nmApelido;
    private Date dtNascimento;
    private String nmCidade;
    private String sgUf;
    private String nmProfissao;
    private String nmEmpresa;
    private String nmTime;
    private String nuCelular;
    private String nuCasa;
    private String nuTrabalho;
    private String deEmail;
    private Integer flForauso;
    private Integer flAdministrador;
    private String deSenha;
    private Integer tpSocio;
    private byte[] imFoto;
    
    private List<String> permissoes;
    private Integer nuMesnascimento;
    
    private String className;
 
    public Socio() {
        setSocioPK(new SocioPK());
        setPermissoes(new ArrayList<String>());
    }
 
    public SocioPK getSocioPK() {
        return this.socioPK;
    }
 
    public void setSocioPK(SocioPK newsocioPK) {
        this.socioPK = newsocioPK;
    }
 
    public String getNmSocio() {
        return this.nmSocio;
    }
 
    public void setNmSocio(String newNmSocio) {
        this.nmSocio = newNmSocio;
    }
 
    public String getNmApelido() {
        return this.nmApelido;
    }
 
    public void setNmApelido(String newNmApelido) {
        this.nmApelido = newNmApelido;
    }
 
    public Date getDtNascimento() {
        return this.dtNascimento;
    }
    
    public String getDtNascimentostring() {
        return Util.getDataFormatada( this.dtNascimento );
    }

    public void setDtNascimentostring(String newDtNascimento) {
        this.dtNascimento = Util.stringToDate( newDtNascimento ) ;
    }
 
    public void setDtNascimento(Date newDtNascimento) {
        this.dtNascimento = newDtNascimento;
    }
    
    public String getDtNascimentosmall() {
        return Util.getDataFormatada( this.dtNascimento, Util.DATA_FORMATO_CURTO );
    }
 
    public String getNmCidade() {
        return this.nmCidade;
    }
 
    public void setNmCidade(String newNmCidade) {
        this.nmCidade = newNmCidade;
    }
 
    public String getSgUf() {
        return this.sgUf;
    }
 
    public void setSgUf(String newSgUf) {
        this.sgUf = newSgUf;
    }
 
    public String getNmProfissao() {
        return this.nmProfissao;
    }
 
    public void setNmProfissao(String newNmProfissao) {
        this.nmProfissao = newNmProfissao;
    }
 
    public String getNmEmpresa() {
        return this.nmEmpresa;
    }
 
    public void setNmEmpresa(String newNmEmpresa) {
        this.nmEmpresa = newNmEmpresa;
    }
 
    public String getNmTime() {
        return this.nmTime;
    }
 
    public void setNmTime(String newNmTime) {
        this.nmTime = newNmTime;
    }
 
    public String getNuCelular() {
        return this.nuCelular;
    }
 
    public void setNuCelular(String newNuCelular) {
        this.nuCelular = newNuCelular;
    }
 
    public String getNuCasa() {
        return this.nuCasa;
    }
 
    public void setNuCasa(String newNuCasa) {
        this.nuCasa = newNuCasa;
    }
 
    public String getNuTrabalho() {
        return this.nuTrabalho;
    }
 
    public void setNuTrabalho(String newNuTrabalho) {
        this.nuTrabalho = newNuTrabalho;
    }
 
    public String getDeEmail() {
        return this.deEmail;
    }
 
    public void setDeEmail(String newDeEmail) {
        this.deEmail = newDeEmail;
    }
 
    public Integer getFlForauso() {
        return this.flForauso == null ? 0 : this.flForauso;
    }
 
    public void setFlForauso(Integer newFlForauso) {
        this.flForauso = newFlForauso;
    }
 
    public byte[] getImFoto() {
        return this.imFoto;
    }
 
    public void setImFoto(byte[] newImFoto) {
        this.imFoto = newImFoto;
    }

	public Integer getFlAdministrador() {
		return this.flAdministrador == null ? 0 : this.flAdministrador;
	}

	public void setFlAdministrador(Integer flAdministrador) {
		this.flAdministrador = flAdministrador;
	}

	public String getDeSenha() {
		return deSenha;
	}

	public void setDeSenha(String deSenha) {
		this.deSenha = deSenha;
	}

	public List<String> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<String> permissoes) {
		this.permissoes = permissoes;
	}

	public Integer getTpSocio() {
		return tpSocio;
	}

	public void setTpSocio(Integer tpSocio) {
		this.tpSocio = tpSocio;
	}

	public Integer getNuMesnascimento() {
		if (getDtNascimento() != null) {
			Calendar c = Calendar.getInstance();
			c.setTime( getDtNascimento() );
			nuMesnascimento = (c.get(Calendar.MONTH) + 1);
		} 
		return nuMesnascimento;
	}

	public void setNuMesnascimento(Integer nuMesnascimento) {
		this.nuMesnascimento = nuMesnascimento;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	@Override
	public EntidadePK<Socio> getPK() {
		return getSocioPK();
	}
	
}
