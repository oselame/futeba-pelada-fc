package br.com.softal.pfc;
 
 
@SuppressWarnings("serial")
public class Configuracao extends Entidade {
 
	public static final int TP_CONFIG_OCLUBE_INICIO = 1;
	public static final int TP_CONFIG_OCLUBE_HISTORIA = 2;
	public static final int TP_CONFIG_OCLUBE_ESTATUTO_SOCIAL = 3;
	public static final int TP_CONFIG_OCLUBE_DIRETORIA = 4;
	public static final int TP_CONFIG_GALERIA_ANTERIOR = 5;
	public static final int TP_CONFIG_DESCRITIVO_SITE = 6;
	public static final int TP_CONFIG_CAMISAS = 7;
	
    private ConfiguracaoPK configuracaoPK;
    private String deConfiguracao;
    private String vlConfiguracao;
	
    public Configuracao() {
    	setConfiguracaoPK(new ConfiguracaoPK());
	}

	public ConfiguracaoPK getConfiguracaoPK() {
		return configuracaoPK;
	}

	public void setConfiguracaoPK(ConfiguracaoPK configuracaoPK) {
		this.configuracaoPK = configuracaoPK;
	}

	public String getVlConfiguracao() {
		return vlConfiguracao;
	}

	public void setVlConfiguracao(String vlConfiguracao) {
		this.vlConfiguracao = vlConfiguracao;
	}

	public String getDeConfiguracao() {
		if (this.getConfiguracaoPK().getCdConfiguracao() != null) {
			if (this.getConfiguracaoPK().getCdConfiguracao() == 1) {
				return "O Clube";
			}
			if (this.getConfiguracaoPK().getCdConfiguracao() == 2) {
				return "História";
			}
			if (this.getConfiguracaoPK().getCdConfiguracao() == 3) {
				return "Estatuto Social";
			}
			if (this.getConfiguracaoPK().getCdConfiguracao() == 4) {
				return "Diretoria";
			}
			if (this.getConfiguracaoPK().getCdConfiguracao() == 5) {
				return "Galeria Anterior";
			}			
			if (this.getConfiguracaoPK().getCdConfiguracao() == 6) {
				return "Descritivo Site";
			}
			if (this.getConfiguracaoPK().getCdConfiguracao() == 7) {
				return "Camisas Históricas";
			}
		}
		return deConfiguracao;
	}

	public void setDeConfiguracao(String deConfiguracao) {
		this.deConfiguracao = deConfiguracao;
	}

	@Override
	public EntidadePK<Configuracao> getPK() {
		return getConfiguracaoPK();
	}
	
}
