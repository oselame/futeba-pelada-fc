package br.com.softal.pfc.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.com.softal.pfc.Constantes;
import br.com.softal.pfc.Noticia;
import br.com.softal.pfc.DAO.DAOFactory;
import br.com.softal.pfc.service.PfcServiceLocator;
 
public class NoticiaAction extends PfcAction {
 
	public ActionForward abrirConNoticias(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NoticiaForm noticiaForm = (NoticiaForm) form;
		List<Noticia> lista = DAOFactory.getNoticiaDAO().findAllNoticiasEventosatuais(Noticia.TIPO_NOTICIA);
		noticiaForm.setRows( lista );
		return mapping.findForward(FWD_CONSULTAR);
	}
	
	
	@SuppressWarnings("unchecked")
	public ActionForward consultarNoticias(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NoticiaForm noticiaForm = (NoticiaForm) form;
		Noticia noticia = (Noticia) noticiaForm.getEntidade();
		noticia.setTpNoticia(Noticia.TIPO_NOTICIA);		
		List<Noticia> lista = (List<Noticia>) DAOFactory.getNoticiaDAO().findAll( noticia );
		noticiaForm.setRows( lista );
		return mapping.findForward(FWD_CONSULTAR);
	}
	
	
	public ActionForward abrirCadNoticia(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NoticiaForm noticiaForm = (NoticiaForm) form;
		Noticia noticia = new Noticia();
		noticia.setDeNoticia(" ");
		noticia.setTpNoticia( Noticia.TIPO_NOTICIA );
		noticiaForm.setEntidade(noticia);
		noticiaForm.getEntidade().setStatusInsert();
		return mapping.findForward(FWD_CADASTRAR);
	}
	
	public ActionForward editarCadNoticia(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NoticiaForm noticiaForm = (NoticiaForm) form;
		Noticia noticia = (Noticia) noticiaForm.getEntidade();
		if (request.getParameter("cdNoticia") != null) {
			noticia.getNoticiaPK().setCdNoticia( Integer.valueOf(request.getParameter("cdNoticia").toString()) );
		}
		noticia = (Noticia) DAOFactory.getNoticiaDAO().findByPrimaryKey( noticia.getNoticiaPK() );
		noticia.setStatusUpdate();
		noticiaForm.setEntidade( noticia );
		
		return mapping.findForward(FWD_CADASTRAR);
	}	

	public ActionForward salvarCadNoticia(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages messages = new ActionMessages();
		NoticiaForm noticiaForm = (NoticiaForm) form;
		Noticia noticia = (Noticia) noticiaForm.getEntidade();
		try {		
			PfcServiceLocator.getInstance().getPeladaService().salvarNoticia(noticia);
			if (noticia.isStatusInsert()) {
				//-- DAOFactory.getNoticiaDAO().insert( noticia );
				messages.add(Constantes.TIPO_MENSAGEM_SUCESSO, new ActionMessage("msg.sucesso.salvar.registro"));
			} else if (noticia.isStatusUpdate()) {
				//-- DAOFactory.getNoticiaDAO().update( noticia );
				messages.add(Constantes.TIPO_MENSAGEM_SUCESSO, new ActionMessage("msg.sucesso.atualizar.registro"));
			} else if (noticia.isStatusDelete()) {
				//-- DAOFactory.getNoticiaDAO().delete( noticia );
				messages.add(Constantes.TIPO_MENSAGEM_SUCESSO, new ActionMessage("msg.sucesso.excluir.registro"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			messages.add(Constantes.TIPO_MENSAGEM_ERROR, new ActionMessage("msg.erro.registro.encontrado"));
		} finally {
			if (!messages.isEmpty()) {
				saveMessages(request, messages);				
	        }		
		}
		request.getSession().removeAttribute(Constantes.SESSION_LIST_NOTICIAS);	
		//--return mapping.findForward(FWD_CADASTRAR);
		return editarCadNoticia(mapping, noticiaForm, request, response);
		//--return visualizarNoticias(mapping, form, request, response);
	}
	
	public ActionForward excluirCadNoticia(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages messages = new ActionMessages();
		NoticiaForm noticiaForm = (NoticiaForm) form;
		try {
			Noticia noticia = (Noticia) noticiaForm.getEntidade();
			noticia.setStatusDelete();
			DAOFactory.getNoticiaDAO().delete(noticia);	
			messages.add(Constantes.TIPO_MENSAGEM_SUCESSO, new ActionMessage("msg.sucesso.excluir.registro"));
		} catch (Exception e) {
			e.printStackTrace();
			messages.add(Constantes.TIPO_MENSAGEM_ERROR, new ActionMessage("msg.erro.registro.encontrado"));
			return editarCadNoticia(mapping, form, request, response);
		} finally {
			if (!messages.isEmpty()) {
				saveMessages(request, messages);				
	        }	
		}	
		request.getSession().removeAttribute(Constantes.SESSION_LIST_NOTICIAS);	
		noticiaForm.setEntidade(new Noticia());
		return abrirCadNoticia(mapping, noticiaForm, request, response);
	}	
	
	public ActionForward visualizarNoticias(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NoticiaForm noticiaForm = (NoticiaForm) form;
		Noticia noticia = (Noticia) noticiaForm.getEntidade();
		if (request.getParameter("noticiaPK.cdNoticia") != null) {
			noticia.getNoticiaPK().setCdNoticia( Integer.parseInt(request.getParameter("noticiaPK.cdNoticia").toString()) );
		}
		noticia = (Noticia) DAOFactory.getNoticiaDAO().findByPrimaryKey( noticia.getNoticiaPK() );
		noticiaForm.setEntidade( noticia );
		return mapping.findForward(FWD_CONSULTAR);
	}

	public ActionForward abrirConEventos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NoticiaForm noticiaForm = (NoticiaForm) form;
		List<Noticia> lista = DAOFactory.getNoticiaDAO().findAllNoticiasEventosatuais( Noticia.TIPO_EVENTO );
		noticiaForm.setRows( lista );
		return mapping.findForward(FWD_CONSULTAR);
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward consultarEventos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NoticiaForm noticiaForm = (NoticiaForm) form;
		Noticia noticia = (Noticia) noticiaForm.getEntidade();
		noticia.setTpNoticia(Noticia.TIPO_EVENTO);
		List<Noticia> lista = (List<Noticia>) DAOFactory.getNoticiaDAO().findAll( noticia );
		noticiaForm.setRows( lista );
		return mapping.findForward(FWD_CONSULTAR);
	}
	
	public ActionForward abrirCadEvento(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NoticiaForm noticiaForm = (NoticiaForm) form;
		Noticia noticia = new Noticia();
		noticia.setDeNoticia(" ");
		noticia.setTpNoticia( Noticia.TIPO_EVENTO );
		noticiaForm.setEntidade(noticia);
		noticiaForm.getEntidade().setStatusInsert();
		return mapping.findForward(FWD_CADASTRAR);
	}

	public ActionForward editarCadEvento(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NoticiaForm noticiaForm = (NoticiaForm) form;
		Noticia noticia = (Noticia) noticiaForm.getEntidade();
		if (request.getParameter("cdNoticia") != null) {
			noticia.getNoticiaPK().setCdNoticia( Integer.valueOf(request.getParameter("cdNoticia").toString()) );
		}
		noticia = (Noticia) DAOFactory.getNoticiaDAO().findByPrimaryKey( noticia.getNoticiaPK() );
		noticia.setStatusUpdate();
		noticiaForm.setEntidade( noticia );
		request.getSession().removeAttribute(Constantes.SESSION_LISTA_EVENTOS);
		
		return mapping.findForward(FWD_CADASTRAR);
	}	

	public ActionForward salvarCadEvento(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages messages = new ActionMessages();
		NoticiaForm noticiaForm = (NoticiaForm) form;
		Noticia noticia = (Noticia) noticiaForm.getEntidade();
		try {		
			PfcServiceLocator.getInstance().getPeladaService().salvarNoticia(noticia);
			if (noticia.isStatusInsert()) {
				//-- DAOFactory.getNoticiaDAO().insert( noticia );
				messages.add(Constantes.TIPO_MENSAGEM_SUCESSO, new ActionMessage("msg.sucesso.salvar.registro"));
			} else if (noticia.isStatusUpdate()) {
				//-- DAOFactory.getNoticiaDAO().update( noticia );
				messages.add(Constantes.TIPO_MENSAGEM_SUCESSO, new ActionMessage("msg.sucesso.atualizar.registro"));
			} else if (noticia.isStatusDelete()) {
				//-- DAOFactory.getNoticiaDAO().delete( noticia );
				messages.add(Constantes.TIPO_MENSAGEM_SUCESSO, new ActionMessage("msg.sucesso.excluir.registro"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			messages.add(Constantes.TIPO_MENSAGEM_ERROR, new ActionMessage("msg.erro.registro.encontrado"));
		} finally {
			if (!messages.isEmpty()) {
				saveMessages(request, messages);				
	        }			
		}
		request.getSession().removeAttribute(Constantes.SESSION_LISTA_EVENTOS);
		return editarCadEvento(mapping, noticiaForm, request, response);
		//-- return visualizarEventos(mapping, form, request, response);
	}
	
	public ActionForward excluirCadEvento(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages messages = new ActionMessages();
		NoticiaForm noticiaForm = (NoticiaForm) form;
		try {
			Noticia noticia = (Noticia) noticiaForm.getEntidade();
			noticia.setStatusDelete();
			DAOFactory.getNoticiaDAO().delete(noticia);	
			messages.add(Constantes.TIPO_MENSAGEM_SUCESSO, new ActionMessage("msg.sucesso.excluir.registro"));
			request.getSession().removeAttribute(Constantes.SESSION_LISTA_EVENTOS);
		} catch (Exception e) {
			e.printStackTrace();
			messages.add(Constantes.TIPO_MENSAGEM_ERROR, new ActionMessage("msg.erro.registro.encontrado"));
			return editarCadNoticia(mapping, form, request, response);
		} finally {
			if (!messages.isEmpty()) {
				saveMessages(request, messages);				
	        }	
		}	
		request.getSession().setAttribute(Constantes.SESSION_LISTA_EVENTOS,null);
		noticiaForm.setEntidade(new Noticia());
		return abrirCadEvento(mapping, noticiaForm, request, response);
	}	
	
	public ActionForward visualizarEventos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NoticiaForm noticiaForm = (NoticiaForm) form;
		Noticia noticia = (Noticia) noticiaForm.getEntidade();
		if (request.getParameter("noticiaPK.cdNoticia") != null) {
			noticia.getNoticiaPK().setCdNoticia( Integer.parseInt(request.getParameter("noticiaPK.cdNoticia").toString()) );
		}
		noticia = (Noticia) DAOFactory.getNoticiaDAO().findByPrimaryKey( noticia.getNoticiaPK() );
		noticiaForm.setEntidade( noticia );
		return mapping.findForward(FWD_CONSULTAR);
	}
	
	
	
}
