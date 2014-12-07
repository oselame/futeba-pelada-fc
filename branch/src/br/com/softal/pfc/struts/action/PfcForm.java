package br.com.softal.pfc.struts.action;
 
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import sun.reflect.Reflection;

import br.com.softal.pfc.Entidade;
 
public class PfcForm<T extends Entidade> extends ActionForm {
 
	private static final long serialVersionUID = 1L;
	
	protected List<? extends Entidade> rows;
	private Entidade entidade;
	
	public PfcForm() {
		setRows(new ArrayList<Entidade>());
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
    }

	public List<? extends Entidade> getRows() {
		return rows;
	}

	public void setRows(List<? extends Entidade> rows) {
		this.rows = rows;
	}

	public Entidade getEntidade() {
		return entidade;
	}

	public void setEntidade(Entidade entidade) {
		this.entidade = entidade;
	}
	
}
