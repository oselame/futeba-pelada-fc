package br.com.softal.pfc.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.softal.pfc.Configuracao;
import br.com.softal.pfc.DAO.DAOFactory;
import br.com.softal.pfc.util.Util;
 
public class ConfiguracaoAction extends PfcAction {
 
	public ActionForward abrirConClubeInicio(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ConfiguracaoForm configuracaoForm = (ConfiguracaoForm) form;
		Configuracao config = (Configuracao) configuracaoForm.getEntidade();
		config = Util.carregaConfiguracao( Configuracao.TP_CONFIG_OCLUBE_INICIO );
		configuracaoForm.setEntidade( config );
		return mapping.findForward(FWD_CONSULTAR);
	}
	
	public ActionForward abrirConClubeHistoria(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ConfiguracaoForm configuracaoForm = (ConfiguracaoForm) form;
		Configuracao config = (Configuracao) configuracaoForm.getEntidade();
		config = Util.carregaConfiguracao( Configuracao.TP_CONFIG_OCLUBE_HISTORIA );
		configuracaoForm.setEntidade( config );		
		return mapping.findForward(FWD_CONSULTAR);
	}

	public ActionForward abrirConClubeEstatuto(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ConfiguracaoForm configuracaoForm = (ConfiguracaoForm) form;
		Configuracao config = (Configuracao) configuracaoForm.getEntidade();
		config = Util.carregaConfiguracao( Configuracao.TP_CONFIG_OCLUBE_ESTATUTO_SOCIAL);
		configuracaoForm.setEntidade( config );		
		return mapping.findForward(FWD_CONSULTAR);
	}

	public ActionForward abrirConClubeDiretoria(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ConfiguracaoForm configuracaoForm = (ConfiguracaoForm) form;
		Configuracao config = (Configuracao) configuracaoForm.getEntidade();
		config = Util.carregaConfiguracao( Configuracao.TP_CONFIG_OCLUBE_DIRETORIA);
		configuracaoForm.setEntidade( config );		
		return mapping.findForward(FWD_CONSULTAR);
	}
	
	public ActionForward abrirConClubeCamisas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ConfiguracaoForm configuracaoForm = (ConfiguracaoForm) form;
		Configuracao config = (Configuracao) configuracaoForm.getEntidade();
		config = Util.carregaConfiguracao( Configuracao.TP_CONFIG_CAMISAS);
		configuracaoForm.setEntidade( config );		
		return mapping.findForward(FWD_CONSULTAR);
	}
	
	public ActionForward salvarCadConfiguracao(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ConfiguracaoForm configuracaoForm = (ConfiguracaoForm) form;
		Configuracao config = (Configuracao) configuracaoForm.getEntidade();
		if (config.isStatusInsert()) {
			DAOFactory.getConfiguracaoDAO().insert( config );
		} else {
			DAOFactory.getConfiguracaoDAO().update( config );
		}
		configuracaoForm.setEntidade( config );
		return mapping.findForward(FWD_CONSULTAR);
	}
	
	public ActionForward editarDescritivoSite(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ConfiguracaoForm configuracaoForm = (ConfiguracaoForm) form;
		Configuracao config = (Configuracao) configuracaoForm.getEntidade();
		config = Util.carregaConfiguracao( Configuracao.TP_CONFIG_DESCRITIVO_SITE );
		configuracaoForm.setEntidade( config );
		return mapping.findForward(FWD_CONSULTAR);
	}
	
	public ActionForward salvarDescritivoSite(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ConfiguracaoForm configuracaoForm = (ConfiguracaoForm) form;
		Configuracao config = (Configuracao) configuracaoForm.getEntidade();
		if (config.isStatusInsert()) {
			DAOFactory.getConfiguracaoDAO().insert( config );
		} else {
			DAOFactory.getConfiguracaoDAO().update( config );
		}
		configuracaoForm.setEntidade( config );
		return mapping.findForward(FWD_CONSULTAR);
	}
	
}
