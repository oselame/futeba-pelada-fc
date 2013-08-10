package br.com.softal.pfc.struts.action;

import java.lang.reflect.Method;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.com.softal.pfc.Codigodescricao;
import br.com.softal.pfc.Constantes;
import br.com.softal.pfc.Socio;
import br.com.softal.pfc.util.Banner;
import br.com.softal.pfc.util.Util;

public class PfcAction extends Action {

	public static final String FWD_CONSULTAR = "consultar";
	public static final String FWD_CADASTRAR = "cadastrar";
	public static final String FWD_ERRO = "erro";	
	
	private static String getHora(Long secs) {
		Date d = new Date(secs);
		Format formatter = new SimpleDateFormat("HH.mm.ss");
		return formatter.format(d);
	}
	
	public enum Tipomensagem{SUCESSO, ERRO};
	
	protected void salvarMensagem(HttpServletRequest request, String mensagem, Tipomensagem tipomensagem) {
		ActionMessages messages = new ActionMessages();
		try {
			if (tipomensagem == Tipomensagem.ERRO) {
				messages.add(Constantes.TIPO_MENSAGEM_ERROR, new ActionMessage( mensagem ));
			} else if (tipomensagem == Tipomensagem.SUCESSO) {
				messages.add(Constantes.TIPO_MENSAGEM_SUCESSO, new ActionMessage( mensagem ));
			}
		} finally {
			if (!messages.isEmpty()) {
				saveMessages(request, messages);				
			}
		}
	}
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages messages = new ActionMessages();
		try {
			String methodName;
			if (mapping.getPath().indexOf("/") >= 0) {
				methodName = mapping.getPath().substring(mapping.getPath().lastIndexOf("/") + 1).trim();
			} else {
				methodName = mapping.getPath().trim();
			}
			
			// -- Carrega lista de banners
			Banner.getBanners(request);

			// --
			carregaListasDefault(request);
			
			
			Socio socio = new Socio();
			if (request.getSession().getAttribute(Constantes.SESSION_BOOLEAN_USUARIOLOGADO) != null) {
				socio = (Socio) request.getSession().getAttribute(Constantes.SESSION_SOCIO_LOGADO);
			}
			
			List<String> lista = getActionNaoPermitidas(socio, request);
			if (lista.contains(mapping.getPath().trim())) {
				messages.add(Constantes.TIPO_MENSAGEM_ERROR, new ActionMessage("msg.erro.usario.sem.permissao"));
				return mapping.findForward("semautorizacao");
			} 

			Method method = MethodUtils.getAccessibleMethod(getClass(),
					methodName, new Class[] {
							org.apache.struts.action.ActionMapping.class,
							org.apache.struts.action.ActionForm.class,
							javax.servlet.http.HttpServletRequest.class,
							javax.servlet.http.HttpServletResponse.class });

			if (method != null) {
				//System.out.println("Action Inicio: " + mapping.getPath() + "  " + PfcAction.getHora(System.currentTimeMillis()));
				Object methodResult = method.invoke(this, new Object[] {mapping, form, request, response });
				//System.out.println("Action Fim: " + mapping.getPath() + "  " + PfcAction.getHora(System.currentTimeMillis()));
				return (ActionForward) methodResult;
			} else {
				String msg = "Método \"" + methodName
						+ "\" não encontrado para ação \"" + mapping.getPath()
						+ "\"";
				throw new UnsupportedOperationException(msg);
			}
		} finally {			
			if (!messages.isEmpty()) {
				saveMessages(request, messages);				
	        }
		}
	}

	private void carregaListasDefault(HttpServletRequest request) {
		if (request.getSession().getAttribute(Constantes.SESSION_ESTADOS) == null) {
			List<Codigodescricao> estados = new ArrayList<Codigodescricao>();
			estados.add(new Codigodescricao(1, "Acre", "AC"));
			estados.add(new Codigodescricao(2, "Alagoas", "AL"));
			estados.add(new Codigodescricao(3, "Amapá", "AP"));
			estados.add(new Codigodescricao(4, "Amazonas", "AM"));
			estados.add(new Codigodescricao(5, "Bahia", "BA"));
			estados.add(new Codigodescricao(6, "Ceará", "CE"));
			estados.add(new Codigodescricao(7, "Distrito Federal", "DF"));
			estados.add(new Codigodescricao(8, "Goiás", "GO"));
			estados.add(new Codigodescricao(9, "Espírito Santo", "ES"));
			estados.add(new Codigodescricao(10, "Maranhão", "MA"));
			estados.add(new Codigodescricao(11, "Mato Grosso", "MT"));
			estados.add(new Codigodescricao(12, "Mato Grosso do Sul", "MS"));
			estados.add(new Codigodescricao(13, "Minas Gerais", "MG"));
			estados.add(new Codigodescricao(14, "Pará", "PA"));
			estados.add(new Codigodescricao(15, "Paraiba", "PB"));
			estados.add(new Codigodescricao(16, "Paraná", "PR"));
			estados.add(new Codigodescricao(17, "Pernambuco", "PE"));
			estados.add(new Codigodescricao(18, "Piauí", "PI"));
			estados.add(new Codigodescricao(19, "Rio de Janeiro", "RJ"));
			estados.add(new Codigodescricao(20, "Rio Grande do Norte", "RN"));
			estados.add(new Codigodescricao(21, "Rio Grande do Sul", "RS"));
			estados.add(new Codigodescricao(22, "Rondônia", "RO"));
			estados.add(new Codigodescricao(23, "Rorâima", "RR"));
			estados.add(new Codigodescricao(24, "São Paulo", "SP"));
			estados.add(new Codigodescricao(25, "Santa Catarina", "SC"));
			estados.add(new Codigodescricao(26, "Sergipe", "SE"));
			estados.add(new Codigodescricao(27, "Tocantins", "TO"));
			request.getSession().setAttribute(Constantes.SESSION_ESTADOS, estados);
		}
		
		if (request.getSession().getAttribute(Constantes.SESSION_TIPOS_USUARIO) == null) {
			List<Codigodescricao> tiposUsuario = new ArrayList<Codigodescricao>();
			tiposUsuario.add(new Codigodescricao(1, "Patrimonial", "PA"));
			tiposUsuario.add(new Codigodescricao(2, "Preferêncial", "PE"));
			tiposUsuario.add(new Codigodescricao(3, "Avulso", "AV"));
			tiposUsuario.add(new Codigodescricao(4, "Benemérito", "BE"));
			request.getSession().setAttribute(Constantes.SESSION_TIPOS_USUARIO, tiposUsuario);
		}
		
		if (request.getSession().getAttribute(Constantes.SESSION_MESES_COMBO) == null) {
			List<Codigodescricao> mesCombo = new ArrayList<Codigodescricao>();
			for (int i = 1; i <= 12; i++) {
				mesCombo.add(new Codigodescricao( i, Util.getRessource("label.mes." + i), Util.getRessource("label.mes.sigla." + i)));
			}
			request.getSession().setAttribute(Constantes.SESSION_MESES_COMBO, mesCombo);
		}
		
	}
	
	@Override
	protected void saveMessages(HttpServletRequest request, ActionMessages messages) {
		super.saveMessages(request, messages);
	}
	
	private List<String> getActionNaoPermitidas(Socio socio, HttpServletRequest request) {
		List<String> actions = new ArrayList<String>();
		if (socio.getTpSocio() == null || socio.getTpSocio() == Constantes.TIPO_SOCIO_AVULSO) {
			if (request.getSession().getAttribute(Constantes.SESSION_ACTIONS_PERMITIDAS_AVULSO) == null) {
				actions = getActionNaoPermitidasUsuarioAvulso(socio);
				request.getSession().setAttribute(Constantes.SESSION_ACTIONS_PERMITIDAS_AVULSO,actions);
			}
			actions = (List<String>) request.getSession().getAttribute(Constantes.SESSION_ACTIONS_PERMITIDAS_AVULSO);
		} else if (socio.getTpSocio() == Constantes.TIPO_SOCIO_PREFERENCIAL) {
			if (request.getSession().getAttribute(Constantes.SESSION_ACTIONS_PERMITIDAS_PREFENCIAL) == null) {
				actions = getActionNaoPermitidasUsuarioPrefencial(socio);
				request.getSession().setAttribute(Constantes.SESSION_ACTIONS_PERMITIDAS_PREFENCIAL,actions);
			}
			actions = (List<String>) request.getSession().getAttribute(Constantes.SESSION_ACTIONS_PERMITIDAS_PREFENCIAL);
		} else {
			if (request.getSession().getAttribute(Constantes.SESSION_ACTIONS_PERMITIDAS_PATRIMONIAL) == null) {
				actions = getActionNaoPermitidasUsuarioPatrimonial(socio);
				request.getSession().setAttribute(Constantes.SESSION_ACTIONS_PERMITIDAS_PATRIMONIAL,actions);
			}
			actions = (List<String>) request.getSession().getAttribute(Constantes.SESSION_ACTIONS_PERMITIDAS_PATRIMONIAL);
		}
		return actions;
	}
	
	private List<String> getActionNaoPermitidasUsuarioPatrimonial(Socio socio) {
		if (socio.getFlAdministrador() == null || socio.getFlAdministrador() == 0) {
			return this.getActionNaoPermitidasUsuarioPrefencial(socio);
		}
		return new ArrayList<String>();
	}
	
	private List<String> getActionNaoPermitidasUsuarioPrefencial(Socio socio) {
		List<String> lista = new ArrayList<String>();
		lista.add("/abrirConRelatorioAtrasos");
		lista.add("/consultarRelatorioAtrasos");
		lista.add("/imprimirRelatorioAtrasos");
		
		lista.add("/salvarCadConfiguracao");
		
		lista.add("/abrirSociosShowSocio");
		lista.add("/abrirCadSocio");
		lista.add("/salvarCadSocio");
		
		lista.add("/abrirCadPartida");
		lista.add("/editarCadPartida");
		lista.add("/salvarCadPartida");
		lista.add("/excluirCadPartida");
		lista.add("/reabrirCadPartida");
		lista.add("/enviarEmailEncerramentoPartida");
		lista.add("/enviarEmailAtrasadosPartida");
		
		lista.add("/abrirCadSocioPartida");
		lista.add("/salvarCadSocioPartida");
		
		lista.add("/editarCadFotos");
		lista.add("/abrirCadFotos");
		lista.add("/abrirCadEvento");
		lista.add("/editarCadEvento");
		lista.add("/salvarCadEvento");
		lista.add("/excluirCadEvento");
		lista.add("/editarCamisas");
		lista.add("/salvarCamisas");
		
		lista.add("/abrirCadNoticia");
		lista.add("/editarCadNoticia");
		lista.add("/salvarCadNoticia");
		lista.add("/excluirCadNoticia");

		lista.add("/abrirConQuadrimestre");
		lista.add("/editarCadQuadrimestre");
		lista.add("/abrirCadQuadrimestre");
		lista.add("/salvarCadQuadrimestre");
		
		lista.add("/abrirCadArquivoGaleriaAnterior");
		lista.add("/salvarCadArquivoGaleriaAnterior");
		
		lista.add("/editarDescritivoSite");
		lista.add("/salvarDescritivoSite");
		return lista;
	}
	
	private List<String> getActionNaoPermitidasUsuarioAvulso(Socio socio) {
		List<String> lista = new ArrayList<String>();
		lista.add("/abrirConRelatorioAtrasos");
		lista.add("/consultarRelatorioAtrasos");
		lista.add("/imprimirRelatorioAtrasos");
		
		lista.add("/salvarCadConfiguracao");
		
		lista.add("/abrirSociosShowSocio");
		lista.add("/abrirCadSocio");
		lista.add("/salvarCadSocio");
		
		lista.add("/abrirCadPartida");
		lista.add("/editarCadPartida");
		lista.add("/salvarCadPartida");
		lista.add("/excluirCadPartida");
		lista.add("/reabrirCadPartida");
		lista.add("/enviarEmailEncerramentoPartida");
		lista.add("/enviarEmailAtrasadosPartida");
		
		lista.add("/abrirCadSocioPartida");
		lista.add("/salvarCadSocioPartida");
		
		lista.add("/editarCadFotos");
		lista.add("/abrirCadFotos");
		lista.add("/abrirCadEvento");
		lista.add("/editarCadEvento");
		lista.add("/salvarCadEvento");
		lista.add("/excluirCadEvento");
		lista.add("/editarCamisas");
		lista.add("/salvarCamisas");
		
		lista.add("/abrirCadNoticia");
		lista.add("/editarCadNoticia");
		lista.add("/salvarCadNoticia");
		lista.add("/excluirCadNoticia");

		lista.add("/abrirConQuadrimestre");
		lista.add("/editarCadQuadrimestre");
		lista.add("/abrirCadQuadrimestre");
		lista.add("/salvarCadQuadrimestre");
		
		lista.add("/abrirCadArquivoGaleriaAnterior");
		lista.add("/salvarCadArquivoGaleriaAnterior");
		
		lista.add("/editarDescritivoSite");
		lista.add("/salvarDescritivoSite");
		return lista;
	}
	
}
