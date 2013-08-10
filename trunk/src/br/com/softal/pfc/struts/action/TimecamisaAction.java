package br.com.softal.pfc.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.softal.pfc.Timecamisa;
import br.com.softal.pfc.DAO.DAOFactory;
import br.com.softal.pfc.DAO.TimecamisaDAO;
 
 
public class TimecamisaAction extends PfcAction {
 
	@SuppressWarnings("unchecked")
	public ActionForward editarCamisas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TimecamisaForm timecamisaForm = (TimecamisaForm) form;
		TimecamisaDAO timecamisaDAO = DAOFactory.getTimecamisaDAO();
		Timecamisa timeA = timecamisaDAO.findTimecamisa( Timecamisa.TIME_CAMISA_A );
		Timecamisa timeB = timecamisaDAO.findTimecamisa( Timecamisa.TIME_CAMISA_B );
		timecamisaForm.setNmTimeA(timeA.getNmTime());
		timecamisaForm.setNmTimeB(timeB.getNmTime());
		return mapping.findForward(FWD_CADASTRAR);
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward salvarCamisas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TimecamisaForm timecamisaForm = (TimecamisaForm) form;
		TimecamisaDAO timecamisaDAO = DAOFactory.getTimecamisaDAO();
		Timecamisa timeA = timecamisaDAO.findTimecamisa( Timecamisa.TIME_CAMISA_A );
		Timecamisa timeB = timecamisaDAO.findTimecamisa( Timecamisa.TIME_CAMISA_B );
		timeA.setNmTime(timecamisaForm.getNmTimeA());
		timeB.setNmTime(timecamisaForm.getNmTimeB());
		timecamisaDAO.update(timeA);
		timecamisaDAO.update(timeB);
		return mapping.findForward(FWD_CADASTRAR);
	}
}
