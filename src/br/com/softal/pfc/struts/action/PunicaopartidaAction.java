package br.com.softal.pfc.struts.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.com.softal.pfc.Constantes;
import br.com.softal.pfc.Partida;
import br.com.softal.pfc.Punicaopartida;
import br.com.softal.pfc.Sociopartida;
import br.com.softal.pfc.DAO.DAOFactory;
import br.com.softal.pfc.DAO.PartidaDAO;
import br.com.softal.pfc.DAO.PunicaopartidaDAO;
import br.com.softal.pfc.DAO.SociopartidaDAO;
 
public class PunicaopartidaAction extends PfcAction {

	public ActionForward abrirCadMultaPartida(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		PunicaopartidaForm punicaopartidaForm = (PunicaopartidaForm) form;
		Punicaopartida punicaopartida = (Punicaopartida) punicaopartidaForm.getEntidade();
		if (request.getParameter("cdPartida") != null) {
			Integer cdPartida = Integer.valueOf(request.getParameter("cdPartida"));
			punicaopartida.getPunicaopartidaPK().setCdPartida( cdPartida );
		}
		
		PunicaopartidaDAO punicaopartidaDAO = DAOFactory.getPunicaopartidaDAO();
		
		PartidaDAO partidaDAO = DAOFactory.getPartidaDAO();
		punicaopartida.setPartida( (Partida) partidaDAO.findPartida( punicaopartida.getPunicaopartidaPK().getCdPartida() ));
		
		try {
			List<Punicaopartida> socios = punicaopartidaDAO.findAllJogodoresPartida( punicaopartida.getPunicaopartidaPK().getCdPartida() );
			punicaopartidaForm.setNuJogadores( socios.size() );
			punicaopartidaForm.setRows( socios );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(FWD_CADASTRAR);
	}	
	
	@SuppressWarnings("unchecked")
	public ActionForward salvarCadMultaPartida(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages messages = new ActionMessages();
		PunicaopartidaForm punicaopartidaForm = (PunicaopartidaForm) form;
		Punicaopartida punicaopartida = (Punicaopartida) punicaopartidaForm.getEntidade();
				
		Collection multas = punicaopartidaForm.getMultas().values();
		Iterator it = multas.iterator();		
		try {
			List<Punicaopartida> punicoes = new ArrayList<Punicaopartida>();
		    while (it.hasNext()) {		    	
		    	Punicaopartida pp = (Punicaopartida) it.next();
		    	pp.getPunicaopartidaPK().setCdPartida( punicaopartida.getPunicaopartidaPK().getCdPartida() );
		    	punicoes.add(pp);
		    }
		    
		    PunicaopartidaDAO dao= DAOFactory.getPunicaopartidaDAO();
		    dao.salvaPunicoes(punicoes);
		    messages.add(Constantes.TIPO_MENSAGEM_SUCESSO, new ActionMessage("msg.sucesso.salvar.registro"));
		} catch (Exception e) {
			e.printStackTrace();
			messages.add(Constantes.TIPO_MENSAGEM_ERROR, new ActionMessage("msg.erro.registro.encontrado"));
		} finally {
			if (!messages.isEmpty()) {
				saveMessages(request, messages);				
	        }			
		}
		return abrirCadMultaPartida(mapping, form, request, response);
	}	
	
 
}
