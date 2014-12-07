package br.com.softal.pfc.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
 
public class AusenciaAction extends PfcAction {
 
    public ActionForward listAusencia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		@SuppressWarnings("unused")
		AusenciaForm ausenciaForm = (AusenciaForm) form;
        try {
            return mapping.findForward("listAusencia");
        } finally {
        }
    }
 
    public ActionForward addAusencia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
 
        @SuppressWarnings("unused")
		AusenciaForm ausenciaForm = (AusenciaForm) form;
        try {
            return mapping.findForward("cadAusencia");
        } finally {
        }
    }
 
    public ActionForward editAusencia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
 
        @SuppressWarnings("unused")
		AusenciaForm ausenciaForm = (AusenciaForm) form;
        try {
            return mapping.findForward("cadAusencia");
        } finally {
        }
    }
 
    public ActionForward deleteAusencia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
 
    	@SuppressWarnings("unused")
    	AusenciaForm ausenciaForm = (AusenciaForm) form;
        try {
            return mapping.findForward("listAusencia");
        } finally {
        }
    }
 
    public ActionForward saveAusencia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
 
    	@SuppressWarnings("unused")
    	AusenciaForm ausenciaForm = (AusenciaForm) form;
        try {
            return mapping.findForward("listAusencia");
        } finally {
        }
    }
 
}
