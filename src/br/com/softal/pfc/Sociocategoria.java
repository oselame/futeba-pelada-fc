package br.com.softal.pfc;

import java.util.Date;
 
@SuppressWarnings("serial")
public class Sociocategoria extends Entidade {
 
    private SociocategoriaPK sociocategoriaPK;
    private Date dtCriacao;
    private Integer flAtivo;
    private Categoria categoria;
    private Socio socio;
 
    public Sociocategoria() {
        setSociocategoriaPK(new SociocategoriaPK());
    }
 
    public SociocategoriaPK getSociocategoriaPK() {
        return this.sociocategoriaPK;
    }
 
    public void setSociocategoriaPK(SociocategoriaPK newsociocategoriaPK) {
        this.sociocategoriaPK = newsociocategoriaPK;
    }
 
    public Date getDtCriacao() {
        return this.dtCriacao;
    }
 
    public void setDtCriacao(Date newDtCriacao) {
        this.dtCriacao = newDtCriacao;
    }
 
    public Integer getFlAtivo() {
        return this.flAtivo;
    }
 
    public void setFlAtivo(Integer newFlAtivo) {
        this.flAtivo = newFlAtivo;
    }
 
    public Categoria getCategoria() {
        return this.categoria;
    }
 
    public void setCategoria(Categoria newCategoria) {
        this.categoria = newCategoria;
    }
 
    public Socio getSocio() {
        return this.socio;
    }
 
    public void setSocio(Socio newSocio) {
        this.socio = newSocio;
    }
    
    @Override
    public EntidadePK<Sociocategoria> getPK() {
    	return getSociocategoriaPK();
    }
}
