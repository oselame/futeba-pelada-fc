package br.com.softal.pfc.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.com.softal.pfc.Classificacao;
import br.com.softal.pfc.Codigodescricao;
import br.com.softal.pfc.Constantes;
import br.com.softal.pfc.Partida;
import br.com.softal.pfc.PfcBusinessDelegate;
import br.com.softal.pfc.Punicaopartida;
import br.com.softal.pfc.Quadrimestre;
import br.com.softal.pfc.Sociopartida;
import br.com.softal.pfc.Timecamisa;
import br.com.softal.pfc.DAO.ClassificacaoDAO;
import br.com.softal.pfc.DAO.DAOException;
import br.com.softal.pfc.DAO.DAOFactory;
import br.com.softal.pfc.DAO.PartidaDAO;
import br.com.softal.pfc.DAO.QuadrimestreDAO;
import br.com.softal.pfc.DAO.SociopartidaDAO;
import br.com.softal.pfc.DAO.TimecamisaDAO;
import br.com.softal.pfc.config.PfcConfig;
import br.com.softal.pfc.dto.EmailAtrasoDto;
import br.com.softal.pfc.email.SendEmail2;
import br.com.softal.pfc.exception.QuadrimestreException;
import br.com.softal.pfc.service.PeladaService;
import br.com.softal.pfc.service.PfcServiceLocator;
import br.com.softal.pfc.util.Util;
 
 
public class PartidaAction extends PfcAction {
	
	private void carregaPunicoes(ActionForm form, HttpServletRequest request) throws Exception {
		PartidaForm partidaForm = (PartidaForm) form;
		Partida partida = (Partida) partidaForm.getEntidade();
		
		List<Punicaopartida> punicoes = DAOFactory.getPunicaopartidaDAO().findAllPunicoesPartida(partida.getPartidaPK().getCdPartida());
		partidaForm.setPunicoes(punicoes);
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward abrirConJogosShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PartidaForm partidaForm = (PartidaForm) form;
		Partida partida = (Partida) partidaForm.getEntidade();
		PartidaDAO partidaDAO = DAOFactory.getPartidaDAO();
		String cdPartida = request.getParameter("cdPartida");
		if (cdPartida != null && cdPartida != "") {
			partida.getPartidaPK().setCdPartida( Integer.valueOf(cdPartida) );
		}		
		partida = (Partida) partidaDAO.findByPrimaryKey( partida );
		partida.setStatusUpdate();
		partidaForm.setEntidade( partida );		
		
		SociopartidaDAO sociopartidaDAO = DAOFactory.getSociopartidaDAO();
		Sociopartida sociopartida = new Sociopartida();
		sociopartida.getSociopartidaPK().setCdPartida( partida.getPartidaPK().getCdPartida() );
		sociopartida.setMostraForauso(true);
		List lista = sociopartidaDAO.findAll( sociopartida );
		partida.setSociosPartida( lista );
		
		configuraTela(request);
		
		this.carregaPunicoes(partidaForm, request);
		
		return mapping.findForward("showJogo");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward abrirConJogosQuadrimestre(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			PartidaForm partidaForm = (PartidaForm) form;
			Partida partida = (Partida) partidaForm.getEntidade();
			PartidaDAO partidaDAO = DAOFactory.getPartidaDAO();
			//String ano = Util.getAnoAtual();
			String ano = Util.getAnoQuadrimestreAtual().toString();
			String quadrimestre = Util.getQuadrimestreAtual(false);
			partida.setNuAno(Integer.valueOf(ano));
			partida.setCdQuadrimestre(Integer.valueOf(quadrimestre));
			
			List listJogos =  partidaDAO.findByAnoQuadrimestre(ano, quadrimestre);
			partidaForm.setRows( listJogos );
			request.setAttribute(Constantes.SESSION_TITULO, "Jogos do " + quadrimestre + "&deg; Quadrimestre de " + ano);
			configuraTela(request);
			return mapping.findForward("listJogos");
		} catch (QuadrimestreException e) {
			salvarMensagem(request, e.getMessage(), Tipomensagem.ERRO);
			e.printStackTrace();
			return mapping.findForward("error");
		}
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward abrirConJogosAno(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PartidaForm partidaForm = (PartidaForm) form;
		PartidaDAO partidaDAO = DAOFactory.getPartidaDAO();
		String ano = Util.getAnoQuadrimestreAtual().toString();
		List listJogos =  partidaDAO.findByAnoQuadrimestre(ano, "");
		partidaForm.setRows( listJogos );		
		request.setAttribute(Constantes.SESSION_TITULO, "Jogos de " + ano);
		configuraTela(request);
		return mapping.findForward("listJogos");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward abrirConPartida(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		PartidaForm partidaForm = (PartidaForm) form;
		Partida partida = (Partida) partidaForm.getEntidade();
		PartidaDAO partidaDAO = DAOFactory.getPartidaDAO();
		QuadrimestreDAO quadrimestreDAO = DAOFactory.getQuadrimestreDAO();
		String ano = Util.getAnoQuadrimestreAtual().toString();
		String quadrimestre = Util.getQuadrimestreAtual(false);
		partida.setNuAno( Integer.valueOf(ano) );
		partida.setCdQuadrimestre( Integer.valueOf(quadrimestre) );
		
		List<Codigodescricao> listAnos = partidaDAO.findAnos();
		request.getSession().setAttribute(Constantes.SESSION_LIST_ANOS, listAnos);
		
		Quadrimestre quadri = new Quadrimestre();
		quadri.getQuadrimestrePK().setNuAno( Integer.valueOf(ano) );
		quadri.setFlAnual( 0 );
		List quadrimestres = quadrimestreDAO.findAll( quadri );
		request.getSession().setAttribute(Constantes.SESSION_LIST_QUADRIMESTRES, quadrimestres);
		configuraTela(request);
		return mapping.findForward("consulta");
	}

	@SuppressWarnings("unchecked")
	public ActionForward abrirConsultaJogos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PartidaForm partidaForm = (PartidaForm) form;
		Partida partida = (Partida) partidaForm.getEntidade();
		PartidaDAO partidaDAO = DAOFactory.getPartidaDAO();
		List listJogos = partidaDAO.findByAnoQuadrimestre(partida.getNuAno().toString(), partida.getCdQuadrimestre().toString());		
		request.setAttribute(Constantes.SESSION_TITULO, "Jogos do " + partida.getCdQuadrimestre() + "º Quadrimestre de " + partida.getNuAno());		
		partidaForm.setRows( listJogos );
		return mapping.findForward("listJogos");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward abrirCadPartida(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages messages = new ActionMessages();
		try {
			Boolean ultimaPartidaEncerrada = DAOFactory.getPartidaDAO().isUltimapartidaencerrada();
			if (!ultimaPartidaEncerrada) {
				messages.add(Constantes.TIPO_MENSAGEM_ERROR, new ActionMessage("msg.erro.partida.anterior.nao.encerrada"));
				return mapping.findForward("iniciojogos");
			}
			PartidaForm partidaForm = (PartidaForm) form;
			Partida partida = new Partida();
			partida.setDtPartida( Util.dataAtual() );
			partida.setStatusInsert();
			partida.setNuJogadorportime( 8 );
			partidaForm.setEntidade( partida );
			
			Integer nuAno = Util.getAnoQuadrimestreAtual();
			partida.setNuAno(nuAno);
			
			List times = DAOFactory.getTimecamisaDAO().findAll();
			request.getSession().setAttribute(Constantes.SESSION_LISTA_TIMES, times);
			
			configuraTela(request);	
		} finally {
			if (!messages.isEmpty()) {
				saveMessages(request, messages);				
	        }
		}
		return mapping.findForward("cadastrar"); 
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward editarCadPartida(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PartidaForm partidaForm = (PartidaForm) form;
		Partida partida = (Partida) partidaForm.getEntidade();
		PartidaDAO partidaDAO = DAOFactory.getPartidaDAO();
		String cdPartida = request.getParameter("cdPartida");
		if (cdPartida != null && cdPartida != "") {
			partida.getPartidaPK().setCdPartida( Integer.valueOf(cdPartida) );
		}		
		partida = (Partida) partidaDAO.findByPrimaryKey( partida );
		partida.setStatusUpdate();
		partidaForm.setEntidade( partida );
		
		List times = DAOFactory.getTimecamisaDAO().findAll();
		request.getSession().setAttribute(Constantes.SESSION_LISTA_TIMES, times);
		configuraTela(request);
		
		Boolean mostrarBotaoEnviarAtrasos = PfcBusinessDelegate.existeAtrasadosOuCartoes(partida.getPartidaPK().getCdPartida());
		partidaForm.setMostraBotaoEnviarEmailAtrasados(mostrarBotaoEnviarAtrasos);
		
		return mapping.findForward("cadastrar"); 
	}	
	
	public ActionForward salvarCadPartida(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages messages = new ActionMessages();
		PartidaForm partidaForm = (PartidaForm) form;
		Partida partida = (Partida) partidaForm.getEntidade();
		partida.setFlEmpate( 0 );
		if (partida.getNuGolperdedor() == partida.getNuGolvencedor()) {
			partida.setFlEmpate( 1 );
		}
		PartidaDAO partidaDAO = DAOFactory.getPartidaDAO();
		try {
			if (partida.getNuJogadorportime() == null) {
				partida.setNuJogadorportime( 8 );
			}
		    if (partida.isStatusInsert()) {
		    	partidaDAO.insert( partida );
		    	messages.add(Constantes.TIPO_MENSAGEM_SUCESSO, new ActionMessage("msg.sucesso.salvar.registro"));
		    } else if (partida.isStatusUpdate()) {
		    	partidaDAO.update( partida );
		    	messages.add(Constantes.TIPO_MENSAGEM_SUCESSO, new ActionMessage("msg.sucesso.salvar.registro"));
		    } 
		    partidaForm.setEntidade(partida);
		} catch (Exception e) {
			e.printStackTrace();
			messages.add(Constantes.TIPO_MENSAGEM_ERROR, new ActionMessage("msg.erro.registro.encontrado"));
		} finally {
			if (!messages.isEmpty()) {
				saveMessages(request, messages);				
	        }			
		}
		return editarCadPartida(mapping, form, request, response);
	}
	
	
	public ActionForward excluirCadPartida(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages messages = new ActionMessages();
		PartidaForm partidaForm = (PartidaForm) form;
		Partida partida = (Partida) partidaForm.getEntidade();
		PartidaDAO partidaDAO = DAOFactory.getPartidaDAO();
		try {
			Partida ultimapartida  = DAOFactory.getPartidaDAO().getUltimapartida();
			if (!ultimapartida.isConcluida() && ultimapartida.getPartidaPK().getCdPartida().intValue() == partida.getPartidaPK().getCdPartida()) {
			    partidaDAO.excluirPartida( partida );
			    messages.add(Constantes.TIPO_MENSAGEM_SUCESSO, new ActionMessage("msg.sucesso.excluir.registro"));
			} else {
				messages.add(Constantes.TIPO_MENSAGEM_ERROR, new ActionMessage("msg.erro.excluir.partida.nao.aberta"));
				return editarCadPartida(mapping, form, request, response);
			}
		} catch (DAOException e) {
			messages.add(Constantes.TIPO_MENSAGEM_ERROR, new ActionMessage(e.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			messages.add(Constantes.TIPO_MENSAGEM_ERROR, new ActionMessage("msg.erro.generico"));
		} finally {
			if (!messages.isEmpty()) {
				saveMessages(request, messages);				
	        }			
		}
		//return mapping.findForward("iniciojogos");
		return abrirCadPartida(mapping, form, request, response);
	}
	
	private void configuraTela(HttpServletRequest request) {
		//-- Nome dos Times
		TimecamisaDAO timecamisaDAO = DAOFactory.getTimecamisaDAO();
		Timecamisa timeA = timecamisaDAO.findTimecamisa( Timecamisa.TIME_CAMISA_A );
		Timecamisa timeB = timecamisaDAO.findTimecamisa( Timecamisa.TIME_CAMISA_B );
		request.getSession().setAttribute(Constantes.SESSION_NMTIMEA, timeA.getNmTime());
		request.getSession().setAttribute(Constantes.SESSION_NMTIMEB, timeB.getNmTime());
	}
	
	public ActionForward encerrarCadPartida(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages messages = new ActionMessages();
		PartidaForm partidaForm = (PartidaForm) form;
		Partida partida = (Partida) partidaForm.getEntidade();
		partida.setFlEmpate( 0 );
		if (partida.getNuGolperdedor().intValue() == partida.getNuGolvencedor().intValue()) {
			partida.setFlEmpate( 1 );
		}
		PartidaDAO partidaDAO = DAOFactory.getPartidaDAO();
		SociopartidaDAO sociopartidaDAO = DAOFactory.getSociopartidaDAO();
		ClassificacaoDAO classificacaoDAO = DAOFactory.getClassificacaoDAO();
		try {
			if (!sociopartidaDAO.existeParticipantes( partida.getPartidaPK().getCdPartida() )) {
				messages.add(Constantes.TIPO_MENSAGEM_ERROR, new ActionMessage("msg.erro.encerrar.partida.socios.nao.informado"));
				return editarCadPartida(mapping, form, request, response);
			}
			if (!partidaDAO.partidaAnteriorEncerrada( partida.getPartidaPK().getCdPartida() )) {
				messages.add(Constantes.TIPO_MENSAGEM_ERROR, new ActionMessage("msg.erro.encerrar.partida.anterior.nao.encerrada"));
				return editarCadPartida(mapping, form, request, response);				
			}
			
			//-- PfcBusinessDelegate.encerrarPartida(partida);
			
			//-- Deleta classificacao da partida selecionada
			classificacaoDAO.excluirClassificacaoPartida(partida.getPartidaPK().getCdPartida());
			
			//-- Gera o Ranking para a Partida do Quadrimestre Atual
			classificacaoDAO.encerrarPartidaQuadrimestreAtual( partida );
			
			//-- Gera o Ranking para a Partida do Quadrimestre Anual
			classificacaoDAO.encerrarPartidaAnual( partida );
						
			//-- Encerra a partida		
			partida.setFlConcluida( 1 );
			partidaDAO.update( partida );
			
	    	messages.add(Constantes.TIPO_MENSAGEM_SUCESSO, new ActionMessage("msg.sucesso.encerrar.partida"));
		} catch (Exception e) {
			e.printStackTrace();
			messages.add(Constantes.TIPO_MENSAGEM_ERROR, new ActionMessage(e.getMessage()));
		} finally {
			if (!messages.isEmpty()) {
				saveMessages(request, messages);				
	        }			
			
		}
		//return enviarEmail(mapping, form, request, response);
		return editarCadPartida(mapping, form, request, response);
	}
	
	private String getDadosParticipante(Sociopartida sp) {
		String ret = sp.getSocio().getNmApelido();
		if (sp.getNuGol() > 0) {
			ret += " (" + sp.getNuGol() + ")";
		}
		if (sp.getFlCartaoamarelo() == 1) {
			ret += " <span style=\"color: yellow\">(Amarelo)</span>";
		} else if (sp.getFlCartaoazul() == 1) {
			ret += " <span style=\"color: blue\">(Azul)</span>";
		} else if (sp.getFlCartaovermelho() == 1) {
			ret += " <span style=\"color: red\">(Vermelho)</span>";
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	public ActionForward enviarEmailEncerramentoPartida(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages messages = new ActionMessages();
		try {
			PartidaForm partidaForm = (PartidaForm) form;
			Partida partida = (Partida) partidaForm.getEntidade();
			PartidaDAO partidaDAO = DAOFactory.getPartidaDAO();
			String cdPartida = request.getParameter("cdPartida");
			if (cdPartida != null && cdPartida != "") {
				partida.getPartidaPK().setCdPartida( Integer.valueOf(cdPartida) );
			}		
			partida = (Partida) partidaDAO.findByPrimaryKey( partida );
			partida.setStatusUpdate();
			partidaForm.setEntidade( partida );		
			//-- SociosPartida
			SociopartidaDAO sociopartidaDAO = DAOFactory.getSociopartidaDAO();
			Sociopartida sociopartida = new Sociopartida();
			sociopartida.getSociopartidaPK().setCdPartida( partida.getPartidaPK().getCdPartida() );
			List listasociopartida = sociopartidaDAO.findAll( sociopartida );
			partida.setSociosPartida( listasociopartida );
			
			this.configuraTela(request);			
			
			StringBuilder corpoEmail = new StringBuilder();
			corpoEmail.append("<html><head>");
			corpoEmail.append("</head><body>");
			
			String styleTable = "border-right-style: solid;	border-top-style: solid; border-right-width: 1px; border-top-style: 1px; border-width: 1px; border-color: green;";	
			String styleTh = "border-left-style: solid;	border-bottom-style: solid;	border-left-width: 1px; border-bottom-style: 1px; border-width: 1px; border-color: green;";
			String styleTd = "border-left-style: solid;	border-bottom-style: solid; border-left-width: 1px; border-bottom-style: 1px; border-width: 1px; border-color: green;";
			
			StringBuilder corpoRodada = new StringBuilder();
			corpoRodada.append("<table width=\"500px\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" > ");
			corpoRodada.append("<tr> ");
			corpoRodada.append("<td colspan=\"3\" align=\"center\"><strong>&Uacute;ltimo Jogo " + partida.getDtPartidaformatada() + " </strong></td> ");
			corpoRodada.append("</tr> ");
			corpoRodada.append("<tr> ");
			corpoRodada.append("<td colspan=\"3\" align=\"center\"><strong>" + partida.getDePlacar() + "</strong></td> ");
			corpoRodada.append("</tr> ");		
			corpoRodada.append("<tr> ");
			corpoRodada.append("<td width=\"45%\" align=\"center\"><strong>Time " + request.getSession().getAttribute(Constantes.SESSION_NMTIMEA) + "  </strong></td> ");
			corpoRodada.append("<td width=\"40\"><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong></td> ");
			corpoRodada.append("<td width=\"45%\" align=\"center\"><strong>Time " + request.getSession().getAttribute(Constantes.SESSION_NMTIMEB) + " </strong></td> ");
			corpoRodada.append("</tr> ");
			StringBuilder sbTimeA = new StringBuilder();
			StringBuilder sbTimeB = new StringBuilder();
			for (Sociopartida sp : partida.getSociosPartida()) {
				if (sp.getCdTime() == 1) {
					if (sbTimeA.length() > 0) {
						sbTimeA.append("<br>");
					}
					sbTimeA.append( getDadosParticipante(sp) );
					//sp.getSocio().getNmApelido() + ( sp.getNuGol() > 0 ? (" (" + sp.getNuGol() + ")") : "")
				} else if (sp.getCdTime() == 2) {
					if (sbTimeB.length() > 0) {
						sbTimeB.append("<br>");
					}
					sbTimeB.append( getDadosParticipante(sp) );
					//-- sbTimeB.append(sp.getSocio().getNmApelido() + ( sp.getNuGol() > 0 ? (" (" + sp.getNuGol() + ")") : "") );
				}
			}
			corpoRodada.append("<tr> ");
			corpoRodada.append("<td align=\"center\">" + sbTimeA + "</td> ");
			corpoRodada.append("<td>&nbsp;</td> ");
			corpoRodada.append("<td align=\"center\">" + sbTimeB + "</td> ");
			corpoRodada.append("</tr> ");		
			corpoRodada.append("</table>");
			corpoRodada.append("<br> ");
			corpoRodada.append("<br> ");
			corpoEmail.append( corpoRodada.toString() );
			
			StringBuilder cBolaCheiaMurcha = new StringBuilder();
			cBolaCheiaMurcha.append("<table width=\"500px\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\"> ");
			cBolaCheiaMurcha.append("<tr> ");
			cBolaCheiaMurcha.append("<td><strong>Bola Cheia</strong></td>");
			cBolaCheiaMurcha.append("<td><strong>Bola Murcha</strong></td>");
			cBolaCheiaMurcha.append("</tr> ");
			cBolaCheiaMurcha.append("<tr> ");
			cBolaCheiaMurcha.append("<td>" + partida.getDeBolacheia() + "</td>");
			cBolaCheiaMurcha.append("<td>" + partida.getDeBolamurcha() + "</td>");
			cBolaCheiaMurcha.append("</tr> ");
			cBolaCheiaMurcha.append("</table> ");
			
			cBolaCheiaMurcha.append(" <br><br> ");
			corpoEmail.append( cBolaCheiaMurcha.toString() );
	
			StringBuilder corpoCabecalho = new StringBuilder();
			corpoCabecalho.append("<tr> ");
	        corpoCabecalho.append("<td align=\"center\">&nbsp;</td> ");
	        corpoCabecalho.append("<td>&nbsp;</td> ");
	        corpoCabecalho.append("<td align=\"center\">&nbsp;</td> ");
	        corpoCabecalho.append("<td align=\"center\">&nbsp;</td> ");
	        corpoCabecalho.append("<td align=\"center\">&nbsp;</td> ");
	        corpoCabecalho.append("<td align=\"center\">&nbsp;</td> ");
	        corpoCabecalho.append("<td align=\"center\">&nbsp;</td> ");
	        corpoCabecalho.append("<td colspan=\"3\" align=\"center\"><strong>Cart&atilde;o</strong></td> ");
	        corpoCabecalho.append("<td align=\"center\">&nbsp;</td> ");
	        corpoCabecalho.append("</tr> ");
	        corpoCabecalho.append("<tr> ");
	        corpoCabecalho.append("<td width=\"25\" align=\"center\"><strong>&nbsp;</strong></td> ");
	        corpoCabecalho.append("<td><strong>Nome</strong></td> ");
	        corpoCabecalho.append("<td width=\"35\" align=\"center\"><strong>P</strong></td> ");
	        corpoCabecalho.append("<td width=\"35\" align=\"center\"><strong>J</strong></td> ");
	        corpoCabecalho.append("<td width=\"35\" align=\"center\"><strong>V</strong></td> ");
	        corpoCabecalho.append("<td width=\"35\" align=\"center\"><strong>E</strong></td> ");
	        corpoCabecalho.append("<td width=\"35\" align=\"center\"><strong>D</strong></td> ");
	        corpoCabecalho.append("<td width=\"35\" align=\"center\"><strong>Ver.</strong></td> ");
	        corpoCabecalho.append("<td width=\"35\" align=\"center\"><strong>Az.</strong></td> ");
	        corpoCabecalho.append("<td width=\"35\" align=\"center\"><strong>Am.</strong></td> ");
	        corpoCabecalho.append("<td width=\"35\" align=\"center\"><strong>A</strong></td> ");
	        corpoCabecalho.append("</tr> ");
			
			Classificacao classificacao = new Classificacao();
			String nuAno = Util.getAnoQuadrimestreAtual().toString();
	        /******************************************************************************
	         ************************  QUADRIMESTRE ATUAL *********************************
	         *****************************************************************************/
			classificacao.setCdQuadrimestre( partida.getCdQuadrimestre() );
			classificacao.setNuAno( Integer.valueOf( nuAno ) );
			List<Classificacao> listaQuadrimestreAtual = DAOFactory.getClassificacaoDAO().findRanking( classificacao );
			Quadrimestre quadrimestre  = (Quadrimestre) DAOFactory.getQuadrimestreDAO().getDadosQuadrimestre(classificacao.getNuAno(), classificacao.getCdQuadrimestre()) ;
			String sRanking = "Ranking do " + partida.getCdQuadrimestre() + "&ordm; Quadrimestre de " + nuAno;
			String sPeriodo = "Per&iacute;odo: " + Util.dateToString(quadrimestre.getDtInicio()) + " a " + Util.dateToString(quadrimestre.getDtFim());
	        corpoEmail.append(" <b>" + sRanking + "</b><br>");        
	        corpoEmail.append(" <table width=\"500px\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\"> ");
	        corpoEmail.append( corpoCabecalho );       
	        StringBuilder corpoDados = new StringBuilder();
	        for (Classificacao c : listaQuadrimestreAtual) {
		        corpoDados.append("	  <tr> ");
		        corpoDados.append("		<td align=\"center\">" + c.getNuClassificacao() + "</td> ");
		        corpoDados.append("		<td>" + c.getSocio().getNmApelido() + "</td> ");
		        corpoDados.append("		<td align=\"center\">" + c.getNuPontos() + "</td> ");
		        corpoDados.append("		<td align=\"center\">" + c.getNuJogos() + "</td> ");
		        corpoDados.append("		<td align=\"center\">" + c.getNuVitorias() + "</td> ");
		        corpoDados.append("		<td align=\"center\">" + c.getNuEmpates() + "</td> ");
		        corpoDados.append("		<td align=\"center\">" + c.getNuDerrotas() + "</td> ");
		        corpoDados.append("		<td align=\"center\">" + c.getNuCartaovermelho() + "</td> ");
		        corpoDados.append("		<td align=\"center\">" + c.getNuCartaoazul() + "</td> ");
		        corpoDados.append("		<td align=\"center\">" + c.getNuCartaoamarelo() + "</td> ");
		        corpoDados.append("		<td align=\"center\">" + c.getNuPosicaoanterior() + "</td> ");
		        corpoDados.append("  </tr> ");
	        }        
	        corpoEmail.append(corpoDados);
	        corpoEmail.append("</table> ");
	        corpoEmail.append(" <b>" + sPeriodo + "</b><br>");
	        corpoEmail.append("<br>");
	        corpoEmail.append("<br>");
	        
	        
	        /******************************************************************************
	         ************************  QUADRIMESTRE ANUAL *********************************
	         *****************************************************************************/
			classificacao.setCdQuadrimestre( 4 );
			classificacao.setNuAno( Integer.valueOf( nuAno ) );
			listaQuadrimestreAtual = DAOFactory.getClassificacaoDAO().findRanking( classificacao );
			quadrimestre  = (Quadrimestre) DAOFactory.getQuadrimestreDAO().getDadosQuadrimestre(classificacao.getNuAno(), classificacao.getCdQuadrimestre()) ;
			sRanking = "Ranking do ano de " + nuAno;
			sPeriodo = "Per&iacute;odo: " + Util.dateToString(quadrimestre.getDtInicio()) + " a " + Util.dateToString(quadrimestre.getDtFim());
	        corpoEmail.append(" <b>" + sRanking + "</b><br>");        
	        corpoEmail.append(" <table width=\"500px\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\"> ");
	        corpoEmail.append( corpoCabecalho );       
	        corpoDados = new StringBuilder();
	        for (Classificacao c : listaQuadrimestreAtual) {
		        corpoDados.append("	  <tr> ");
		        corpoDados.append("		<td align=\"center\">" + c.getNuClassificacao() + "</td> ");
		        corpoDados.append("		<td>" + c.getSocio().getNmApelido() + "</td> ");
		        corpoDados.append("		<td align=\"center\">" + c.getNuPontos() + "</td> ");
		        corpoDados.append("		<td align=\"center\">" + c.getNuJogos() + "</td> ");
		        corpoDados.append("		<td align=\"center\">" + c.getNuVitorias() + "</td> ");
		        corpoDados.append("		<td align=\"center\">" + c.getNuEmpates() + "</td> ");
		        corpoDados.append("		<td align=\"center\">" + c.getNuDerrotas() + "</td> ");
		        corpoDados.append("		<td align=\"center\">" + c.getNuCartaovermelho() + "</td> ");
		        corpoDados.append("		<td align=\"center\">" + c.getNuCartaoazul() + "</td> ");
		        corpoDados.append("		<td align=\"center\">" + c.getNuCartaoamarelo() + "</td> ");
		        corpoDados.append("		<td align=\"center\">" + c.getNuPosicaoanterior() + "</td> ");
		        corpoDados.append("  </tr> ");
	        }        
	        corpoEmail.append(corpoDados);
	        corpoEmail.append("</table> ");
	        corpoEmail.append(" <b>" + sPeriodo + "</b><br>");
	        corpoEmail.append("<br>");
	        corpoEmail.append("<br>");
	        corpoEmail.append("</body></html>");
	        
	        SendEmail2 email = new SendEmail2();
	        //-- Autenticacao
	        email.setUser( PfcConfig.getProperty(Constantes.CONFIG_EMAIL_PASSWORDAUTHENTICATION_USER) );
	        email.setPassword( PfcConfig.getProperty(Constantes.CONFIG_EMAIL_PASSWORDAUTHENTICATION_PASSWORD) );
	        
	        // From
	        email.setFrom( PfcConfig.getProperty(Constantes.CONFIG_EMAIL_FROM) );
	        
	        // To groups
	        email.addRecipient( PfcConfig.getProperty(Constantes.CONFIG_EMAIL_TO_GROUPS) );
	        
	        // Subject
	        email.setSubject("Pelada Futebol Clube - Resultado - " + partida.getDtPartidaformatada());
	        
	        // Corpo da Mensagem
	        email.setCorpoMsg( corpoEmail );
	        
	        email.enviarEmail();
	        
	    	messages.add(Constantes.TIPO_MENSAGEM_SUCESSO, new ActionMessage("msg.sucesso.enviar.email"));
		} catch (Exception e) {
			e.printStackTrace();
			messages.add(Constantes.TIPO_MENSAGEM_ERROR, new ActionMessage("msg.erro.enviar.email"));
		} finally {
			if (!messages.isEmpty()) {
				saveMessages(request, messages);				
	        }			
		}
		return editarCadPartida(mapping, form, request, response);
	}
	
	public ActionForward reabrirCadPartida(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages messages = new ActionMessages();
		PartidaForm partidaForm = (PartidaForm) form;
		Partida partida = (Partida) partidaForm.getEntidade();
		partida.setFlEmpate( 0 );
		if (partida.getNuGolperdedor().intValue() == partida.getNuGolvencedor().intValue()) {
			partida.setFlEmpate( 1 );
		}
		PartidaDAO partidaDAO = DAOFactory.getPartidaDAO();
		ClassificacaoDAO classificacaoDAO = DAOFactory.getClassificacaoDAO();
		try {
			if (!partidaDAO.partidaPosteriorEncerrada( partida.getPartidaPK().getCdPartida() )) {
				messages.add(Constantes.TIPO_MENSAGEM_ERROR, new ActionMessage("msg.erro.encerrar.partida.posterior.nao.encerrada"));
				return editarCadPartida(mapping, form, request, response);				
			}
			//-- Deleta classificacao da partida selecionada
			classificacaoDAO.excluirClassificacaoPartida(partida.getPartidaPK().getCdPartida());
			//-- Encerra a partida		
			partida.setFlConcluida( 0 );
			partidaDAO.update( partida );
	    	messages.add(Constantes.TIPO_MENSAGEM_SUCESSO, new ActionMessage("msg.sucesso.reabrir.partida"));
		} catch (Exception e) {
			e.printStackTrace();
			messages.add(Constantes.TIPO_MENSAGEM_ERROR, new ActionMessage("msg.erro.reabrir.partida"));
		} finally {
			if (!messages.isEmpty()) {
				saveMessages(request, messages);				
	        }			
			
		}
		return editarCadPartida(mapping, form, request, response);		
	}
	
	public ActionForward encerrarCadTodasPartida(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages messages = new ActionMessages();
		PartidaDAO partidaDAO = DAOFactory.getPartidaDAO();
		SociopartidaDAO sociopartidaDAO = DAOFactory.getSociopartidaDAO();
		ClassificacaoDAO classificacaoDAO = DAOFactory.getClassificacaoDAO();		
		
		classificacaoDAO.excluirClassificacoes();
		List<Partida> partidas = (List<Partida>) partidaDAO.findAll();
		for (Partida partida : partidas) {
			if (partida.getPartidaPK().getCdPartida() > 63) {
				break;
			}
			partida.setFlEmpate( 0 );
			if (partida.getNuGolperdedor().intValue() == partida.getNuGolvencedor().intValue()) {
				partida.setFlEmpate( 1 );
			}

			try {
				if (!sociopartidaDAO.existeParticipantes( partida.getPartidaPK().getCdPartida() )) {
					messages.add(Constantes.TIPO_MENSAGEM_ERROR, new ActionMessage("msg.erro.encerrar.partida.socios.nao.informado"));
					return editarCadPartida(mapping, form, request, response);
				}
				if (!partidaDAO.partidaAnteriorEncerrada( partida.getPartidaPK().getCdPartida() )) {
					messages.add(Constantes.TIPO_MENSAGEM_ERROR, new ActionMessage("msg.erro.encerrar.partida.anterior.nao.encerrada"));
					return editarCadPartida(mapping, form, request, response);				
				}
				//-- Gera o Ranking para a Partida do Quadrimestre Atual
				classificacaoDAO.encerrarPartidaQuadrimestreAtual( partida );
				//-- Gera o Ranking para a Partida do Quadrimestre Anul
				classificacaoDAO.encerrarPartidaAnual( partida );			
				//-- Encerra a partida		
				partida.setFlConcluida( 1 );
				partidaDAO.update( partida );
		    	messages.add(Constantes.TIPO_MENSAGEM_SUCESSO, new ActionMessage("msg.sucesso.encerrar.partida"));
			} catch (Exception e) {
				e.printStackTrace();
				messages.add(Constantes.TIPO_MENSAGEM_ERROR, new ActionMessage("msg.erro.registro.encontrado"));
			} finally {
				if (!messages.isEmpty()) {
					saveMessages(request, messages);				
		        }			
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward enviarEmailAtrasadosPartida(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages messages = new ActionMessages();
		try {
			PartidaForm partidaForm = (PartidaForm) form;
			Partida partida = (Partida) partidaForm.getEntidade();
			
			PartidaDAO partidaDAO = DAOFactory.getPartidaDAO();
			String cdPartida = request.getParameter("cdPartida");
			if (cdPartida != null && cdPartida != "") {
				partida.getPartidaPK().setCdPartida( Integer.valueOf(cdPartida) );
			}		
			partida = (Partida) partidaDAO.findByPrimaryKey( partida );
			partida.setStatusUpdate();
			partidaForm.setEntidade( partida );	
			
			List<EmailAtrasoDto> emails = DAOFactory.getSociopartidaDAO().findAllSociosAtrasadosComCartao( partida.getPartidaPK().getCdPartida() );
			for (EmailAtrasoDto dto : emails) {
				StringBuilder corpoEmail = new StringBuilder();
				corpoEmail.append("<html>");
				corpoEmail.append("<body>");
				corpoEmail.append("Prezado Peladeiro.").append("<br/><br/>");
				
				if (dto.getFlAtrazado() == 1) {
					corpoEmail.append("Informamos que vc chegou atrasado à partida do dia " + partida.getDtPartidaformatada() + ".").append("<br/>"); 
					corpoEmail.append("Desta forma, vc será penalizado com o pagamento de multa de R$ 10,00 "); 
					corpoEmail.append("(descontando na próxima mensalidade) e, caso tenha acumulado três de atrasos, ");
					corpoEmail.append("também estará suspenso da próxima partida.").append("<br/><br/>");
				}
				if (dto.getFlCartaoamarelo() + dto.getFlCartaoazul() + dto.getFlCartaovermelho() > 0) {
					
					if (dto.getFlCartaoamarelo()==1) {
						corpoEmail.append("Informamos que na partida do dia " + partida.getDtPartidaformatada() + " vc recebeu cartão AMARELO e "); 
						corpoEmail.append("será penalizado com 1 ponto negativo neste dia.").append("<br/>");
					}
					if (dto.getFlCartaoazul()==1) {
						corpoEmail.append("Informamos que na partida do dia " + partida.getDtPartidaformatada() + " vc recebeu cartão AZUL e "); 
						corpoEmail.append("será penalizado com 2 pontos negativos neste dia, além de pagar uma taxa de "); 
						corpoEmail.append("convocação de sua categoria na próxima mensalidade.").append("<br/>");
					}
					
					if (dto.getFlCartaovermelho()==1) {
						corpoEmail.append("Informamos que na partida do dia " + partida.getDtPartidaformatada() + " vc recebeu cartão VERMELHO e "); 
						corpoEmail.append("será penalizado com 3 pontos negativos neste dia, além de pagar uma taxa de "); 
						corpoEmail.append("convocação de sua categoria na próxima mensalidade e estar suspenso por um jogo (próxima partida).").append("<br/>");
					}
				}
				corpoEmail.append("</body>");
				corpoEmail.append("</html>");
				
				/*
				SendEmail2 email = new SendEmail2();
				email.setUser("adrianooselame@gmail.com");
				email.setPassword("04047501");
				
				email.setFrom( "adrianooselame@gmail.com" );
				email.setSubject("Pelada Futebol Clube - Informativo - " + partida.getDtPartidaformatada());
				email.addRecipient( dto.getDeEmail(), dto.getDeApelido() );
				
				email.setCorpoMsg( corpoEmail );
				
				email.enviarEmail();
				*/
				
				
				SendEmail2 email = new SendEmail2();
		        //-- Autenticacao
		        email.setUser( PfcConfig.getProperty(Constantes.CONFIG_EMAIL_PASSWORDAUTHENTICATION_USER) );
		        email.setPassword( PfcConfig.getProperty(Constantes.CONFIG_EMAIL_PASSWORDAUTHENTICATION_PASSWORD) );
		        
		        // From
		        email.setFrom( PfcConfig.getProperty(Constantes.CONFIG_EMAIL_FROM) );
		        
		        // To groups
		        //email.addRecipient( PfcConfig.getProperty(Constantes.CONFIG_EMAIL_TO_GROUPS) );
		        email.addRecipient( dto.getDeEmail(), dto.getDeApelido() );
		        
		        // Subject
		        email.setSubject("Pelada Futebol Clube - Informativo - " + partida.getDtPartidaformatada());
		        
		        // Corpo da Mensagem
		        email.setCorpoMsg( corpoEmail );
		        
		        email.enviarEmail();
				
			}
	        
	    	messages.add(Constantes.TIPO_MENSAGEM_SUCESSO, new ActionMessage("msg.sucesso.enviar.email"));
		} catch (Exception e) {
			e.printStackTrace();
			messages.add(Constantes.TIPO_MENSAGEM_ERROR, new ActionMessage("msg.erro.enviar.email"));
		} finally {
			if (!messages.isEmpty()) {
				saveMessages(request, messages);				
	        }			
		}
		return editarCadPartida(mapping, form, request, response);
	}
	
	public ActionForward reabrirPartidasate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String cdPartida = request.getParameter("cdPartida");
		if (cdPartida != null && cdPartida != "") {
			PfcServiceLocator.getInstance().getPeladaService().reabrirPartidasate( Integer.valueOf(cdPartida) );
		}		
		
		response.sendRedirect("abrirConJogosQuadrimestre.do");
		return null;
	}
	
	public ActionForward encerrarPartidasate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cdPartida = request.getParameter("cdPartida");
		if (cdPartida != null && cdPartida != "") {
			PfcServiceLocator.getInstance().getPeladaService().encerrarPartidasate( Integer.valueOf(cdPartida) );
		}	
		response.sendRedirect("abrirConJogosQuadrimestre.do");
		return null;
	}
	
}
