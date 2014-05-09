package br.com.softal.pfc.struts.action;

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
import br.com.softal.pfc.Sociopartida;
import br.com.softal.pfc.Timecamisa;
import br.com.softal.pfc.DAO.DAOFactory;
import br.com.softal.pfc.DAO.PartidaDAO;
import br.com.softal.pfc.DAO.SociopartidaDAO;
import br.com.softal.pfc.DAO.TimecamisaDAO;
 
public class SociopartidaAction extends PfcAction {

	@SuppressWarnings("unchecked")
	public ActionForward abrirCadSocioPartida(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		SociopartidaForm sociopartidaForm = (SociopartidaForm) form;
		Sociopartida sociopartida = (Sociopartida) sociopartidaForm.getEntidade();
		if (request.getParameter("cdPartida") != null) {
			Integer cdPartida = Integer.valueOf(request.getParameter("cdPartida"));
			sociopartida.getSociopartidaPK().setCdPartida( cdPartida );
		}
		
		SociopartidaDAO sociopartidaDAO = DAOFactory.getSociopartidaDAO();
		
		PartidaDAO partidaDAO = DAOFactory.getPartidaDAO();
		sociopartida.setPartida( (Partida) partidaDAO.findPartida( sociopartida.getSociopartidaPK().getCdPartida() ));
		
		TimecamisaDAO timecamisaDAO = DAOFactory.getTimecamisaDAO();
		try {
			Timecamisa timeA = timecamisaDAO.findTimecamisa( Timecamisa.TIME_CAMISA_A );
			Timecamisa timeB = timecamisaDAO.findTimecamisa( Timecamisa.TIME_CAMISA_B );
			request.getSession().setAttribute(Constantes.SESSION_NMTIMEA, timeA.getNmTime());
			request.getSession().setAttribute(Constantes.SESSION_NMTIMEB, timeB.getNmTime());
			List socios = sociopartidaDAO.findAllJogodoresTimes( sociopartida.getSociopartidaPK().getCdPartida() );
			sociopartidaForm.setNuJogadores( socios.size() );
			sociopartidaForm.setRows( socios );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(FWD_CADASTRAR);
	}	
	
	@SuppressWarnings("unchecked")
	public ActionForward salvarCadSocioPartida(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages messages = new ActionMessages();
		SociopartidaForm sociopartidaForm = (SociopartidaForm) form;
		Sociopartida sociopartida = (Sociopartida) sociopartidaForm.getEntidade();
		SociopartidaDAO sociopartidaDAO = DAOFactory.getSociopartidaDAO();
		PartidaDAO partidaDAO = DAOFactory.getPartidaDAO();
		
		Integer nuGolsTimeA = 0;
		Integer nuGolsTimeB = 0;		
		Collection jogos = sociopartidaForm.getJogos().values();
		Iterator it = jogos.iterator();		
		try {
		    while (it.hasNext()) {		    	
		    	Sociopartida sp = (Sociopartida) it.next();
		    	sp.getSociopartidaPK().setCdPartida( sociopartida.getSociopartidaPK().getCdPartida() );
		    	if (sp.getCdTime() == 0) {
		    		sociopartidaDAO.delete(sp);
		    	} else {
		    		sociopartidaDAO.delete(sp);
		    		sociopartidaDAO.insert(sp);
		    		if (sp.getCdTime() == 1 && sp.getNuGol() > 0) {
		    			nuGolsTimeA += sp.getNuGol();
		    		} else if (sp.getCdTime() == 2 && sp.getNuGol() > 0) {
		    			nuGolsTimeB += sp.getNuGol();
		    		}
		    		
		    		if (sp.getCdTime() == 1 && sp.getNuGolcontra() > 0) {
		    			nuGolsTimeB += sp.getNuGolcontra();
		    		}
		    		if (sp.getCdTime() == 2 && sp.getNuGolcontra() > 0) {
		    			nuGolsTimeA += sp.getNuGolcontra();
		    		}
		    	}
		    }
		    Integer cdTimeVencedor = 1;
		    Integer nuGolsVencedor = nuGolsTimeA;
		    Integer nuGolsPerdedor = nuGolsTimeB;
		    if (nuGolsTimeA < nuGolsTimeB) {
		    	cdTimeVencedor = 2;
			    nuGolsVencedor = nuGolsTimeB;
			    nuGolsPerdedor = nuGolsTimeA;
		    }
		    partidaDAO.atualizaResultado(sociopartida.getSociopartidaPK().getCdPartida(), 
		    		cdTimeVencedor, nuGolsVencedor, nuGolsPerdedor);	
		    messages.add(Constantes.TIPO_MENSAGEM_SUCESSO, new ActionMessage("msg.sucesso.salvar.registro"));
		} catch (Exception e) {
			e.printStackTrace();
			messages.add(Constantes.TIPO_MENSAGEM_ERROR, new ActionMessage("msg.erro.registro.encontrado"));
		} finally {
			if (!messages.isEmpty()) {
				saveMessages(request, messages);				
	        }			
		}
		return abrirCadSocioPartida(mapping, form, request, response);
	}	
	
 
}
