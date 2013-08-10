package br.com.softal.pfc;

import br.com.softal.pfc.util.Util;
 
@SuppressWarnings("serial")
public class Classificacao extends Entidade {
	
	public static int NU_QUADRIMESTRE_ANUAL = 4;
	
    private ClassificacaoPK classificacaoPK;
    private Integer cdSocio;
    private Integer cdPartida;
    private Integer nuAno;
    private Integer cdQuadrimestre;
    private Integer nuClassificacao;
    private Integer nuPontos;
    private Integer nuJogos;
    private Integer nuVitorias;
    private Integer nuEmpates;
    private Integer nuDerrotas;
    private Integer nuCartaovermelho;
    private Integer nuCartaoazul;
    private Integer nuCartaoamarelo;
    private Integer nuPosicaoanterior;
    private String cdAuxiliar;
    
    private Socio socio;
    private Quadrimestre quadrimestre;
    
    public Classificacao() {
    	setClassificacaoPK( new ClassificacaoPK() );
    	setSocio( new Socio() );
    	setQuadrimestre(new Quadrimestre());
    }
 
    public ClassificacaoPK getClassificacaoPK() {
		return classificacaoPK;
	}

	public void setClassificacaoPK(ClassificacaoPK classificacaoPK) {
		this.classificacaoPK = classificacaoPK;
	}

	public Integer getCdSocio() {
		return cdSocio;
	}

	public void setCdSocio(Integer cdSocio) {
		this.cdSocio = cdSocio;
	}

	public Integer getCdPartida() {
		return cdPartida;
	}

	public void setCdPartida(Integer cdPartida) {
		this.cdPartida = cdPartida;
	}

	public Integer getCdQuadrimestre() {
		return cdQuadrimestre;
	}

	public void setCdQuadrimestre(Integer cdQuadrimestre) {
		this.cdQuadrimestre = cdQuadrimestre;
	}

	public Integer getNuClassificacao() {
		return nuClassificacao == null ? 0 : nuClassificacao;
	}

	public void setNuClassificacao(Integer nuClassificacao) {
		this.nuClassificacao = nuClassificacao;
	}

	public Integer getNuPontos() {
		return nuPontos == null ? 0 : nuPontos;
	}

	public void setNuPontos(Integer nuPontos) {
		this.nuPontos = nuPontos;
	}

	public Integer getNuJogos() {
		return nuJogos == null ? 0 : nuJogos;
	}

	public void setNuJogos(Integer nuJogos) {
		this.nuJogos = nuJogos;
	}

	public Integer getNuVitorias() {
		return nuVitorias == null ? 0 : nuVitorias;
	}

	public void setNuVitorias(Integer nuVitorias) {
		this.nuVitorias = nuVitorias;
	}

	public Integer getNuEmpates() {
		return nuEmpates == null ? 0 : nuEmpates;
	}

	public void setNuEmpates(Integer nuEmpates) {
		this.nuEmpates = nuEmpates;
	}

	public Integer getNuDerrotas() {
		return nuDerrotas == null ? 0 : nuDerrotas;
	}

	public void setNuDerrotas(Integer nuDerrotas) {
		this.nuDerrotas = nuDerrotas;
	}

	public Integer getNuCartaovermelho() {
		return nuCartaovermelho == null ? 0 : nuCartaovermelho;
	}

	public void setNuCartaovermelho(Integer nuCartaovermelho) {
		this.nuCartaovermelho = nuCartaovermelho;
	}

	public Integer getNuCartaoazul() {
		return nuCartaoazul == null ? 0 : nuCartaoazul;
	}

	public void setNuCartaoazul(Integer nuCartaoazul) {
		this.nuCartaoazul = nuCartaoazul;
	}

	public Integer getNuCartaoamarelo() {
		return nuCartaoamarelo == null ? 0 : nuCartaoamarelo;
	}

	public void setNuCartaoamarelo(Integer nuCartaoamarelo) {
		this.nuCartaoamarelo = nuCartaoamarelo;
	}

	public Integer getNuPosicaoanterior() {
		return nuPosicaoanterior == null ? 0 : nuPosicaoanterior;
	}

	public void setNuPosicaoanterior(Integer nuPosicaoanterior) {
		this.nuPosicaoanterior = nuPosicaoanterior;
	}
	
	@Override
	public String toString() {
		return  "Classificacao=" + getNuClassificacao() + ", " +  
				"Pontos=" + getNuPontos() + ", " + 
				"Jogos=" + getNuJogos() + ", " +
				"Vitorias=" + getNuVitorias() + ", " +
				"Empates=" + getNuEmpates() + ", " +
				"Derrotas=" + getNuDerrotas() + ", " +
				"Vermelho=" + getNuCartaovermelho() + ", " +
				"Azul=" + getNuCartaoazul() + ", " +
				"Amarelo=" + getNuCartaoamarelo() + ", " + 
				"Posicaoanterior=" + getNuPosicaoanterior() + ", " + 
				"Auxiliar=" + getCdAuxiliar();
	}

	public String getCdAuxiliar() {
		cdAuxiliar = (
				Util.zeroEsquerda(3, getNuPontos().toString()) + 
				Util.zeroEsquerda(3, getNuJogos().toString()) + 
				Util.zeroEsquerda(3, getNuVitorias().toString()) + 
				Util.zeroEsquerda(3, getNuEmpates().toString()) +				
				Util.zeroEsquerda(3, String.valueOf(999 - getNuPosicaoanterior()) ));
		return cdAuxiliar;
	}

	public void setCdAuxiliar(String cdAuxiliar) {
		this.cdAuxiliar = cdAuxiliar;
	}

	public Socio getSocio() {
		return socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}

	public Integer getNuAno() {
		return nuAno;
	}

	public void setNuAno(Integer nuAno) {
		this.nuAno = nuAno;
	}

	public Quadrimestre getQuadrimestre() {
		return quadrimestre;
	}

	public void setQuadrimestre(Quadrimestre quadrimestre) {
		this.quadrimestre = quadrimestre;
	}

	@Override
	public EntidadePK<Classificacao> getPK() {
		return getClassificacaoPK();
	}
	
}
