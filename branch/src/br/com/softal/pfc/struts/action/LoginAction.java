package br.com.softal.pfc.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.com.softal.pfc.Constantes;
import br.com.softal.pfc.Socio;
import br.com.softal.pfc.DAO.DAOFactory;
import br.com.softal.pfc.DAO.SocioDAO;

public class LoginAction extends PfcAction {

	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		LoginForm loginForm = (LoginForm) form;
		ActionMessages messages = new ActionMessages();
		String usuario = loginForm.getUsuario();
		String senha = loginForm.getSenha();
		SocioDAO socioDAO = DAOFactory.getSocioDAO();
		Socio socio = new Socio();
		try {
			if ((usuario != null && !usuario.equalsIgnoreCase("")) && (senha != null && !senha.equalsIgnoreCase(""))) {			
				socio.setNmApelido( usuario );
				socio.setDeSenha( senha );
				socio = (Socio) socioDAO.findLoginUsuario( usuario , senha );
				if (socio != null && socio.getSocioPK().getCdSocio() != null) {					
					request.getSession().setAttribute(Constantes.SESSION_SOCIO_LOGADO, socio);
					request.getSession().setAttribute(Constantes.SESSION_NMAPELIDO, socio.getNmApelido());
					request.getSession().setAttribute(Constantes.SESSION_BOOLEAN_USUARIOLOGADO, true);
					if (socio.getTpSocio() == 1 && socio.getFlAdministrador() == 0) {
						request.getSession().setAttribute(Constantes.SESSION_TIPOUSUARIO, 2);
						return mapping.findForward("home");
					} else {
						request.getSession().setAttribute(Constantes.SESSION_TIPOUSUARIO, 1);
						return mapping.findForward("inicio");
					}
					//messages.add(Constantes.TIPO_MENSAGEM_SUCESSO, new ActionMessage("msg.sucesso.salvar.registro"));					
					//return mapping.findForward("inicio");
					//return mapping.findForward("home");
				} else {
					request.getSession().removeAttribute(Constantes.SESSION_SOCIO_LOGADO);
					request.getSession().removeAttribute(Constantes.SESSION_BOOLEAN_USUARIOLOGADO);
					request.getSession().setAttribute(Constantes.SESSION_NMAPELIDO, "Visitante");
					request.getSession().setAttribute(Constantes.SESSION_TIPOUSUARIO, 3);
					messages.add(Constantes.TIPO_MENSAGEM_LOGIN, new ActionMessage("msg.erro.usario.senha.incorreto"));
					return mapping.findForward("home");
				}
			} else if (Boolean.parseBoolean(request.getSession().getAttribute(Constantes.SESSION_BOOLEAN_USUARIOLOGADO).toString())) {
				if (request.getSession().getAttribute(Constantes.SESSION_TIPOUSUARIO).toString().compareTo("1") == 0) {
					return mapping.findForward("inicio");
				} else {
					return mapping.findForward("home");
				}
			} else {				
				messages.add(Constantes.TIPO_MENSAGEM_LOGIN, new ActionMessage("msg.erro.usario.senha.incorreto"));
				return mapping.findForward("mensagem"); 
			}
		} catch (Exception e) {
			request.getSession().removeAttribute(Constantes.SESSION_SOCIO_LOGADO);
			request.getSession().removeAttribute(Constantes.SESSION_BOOLEAN_USUARIOLOGADO);
			request.getSession().setAttribute(Constantes.SESSION_NMAPELIDO, "Visitante");
			request.getSession().setAttribute(Constantes.SESSION_TIPOUSUARIO, 3);
			messages.add(Constantes.TIPO_MENSAGEM_LOGIN, new ActionMessage("msg.erro.usario.senha.incorreto"));
			return mapping.findForward("home");
		} finally {
			if (!messages.isEmpty()) {
				saveMessages(request, messages);				
	        }			
		}
	}
	
	public ActionForward logoff(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		request.getSession().removeAttribute(Constantes.SESSION_SOCIO_LOGADO);
		request.getSession().removeAttribute(Constantes.SESSION_BOOLEAN_USUARIOLOGADO);
		request.getSession().setAttribute(Constantes.SESSION_NMAPELIDO, "Visitante");
		return mapping.findForward("inicio"); 
	}
}
