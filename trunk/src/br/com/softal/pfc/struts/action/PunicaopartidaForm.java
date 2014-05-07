package br.com.softal.pfc.struts.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import br.com.softal.pfc.Punicaopartida;
import br.com.softal.pfc.Sociopartida;
 
@SuppressWarnings("serial")
public class PunicaopartidaForm extends PfcForm {
	
	@SuppressWarnings("unchecked")
	private Map multas;
	
	@SuppressWarnings("unchecked")
	public PunicaopartidaForm() {
		setEntidade(new Punicaopartida());
		setMultas( new HashMap() );
	}

	@SuppressWarnings("unchecked")
	public Map getMultas() {
		return multas;
	}

	@SuppressWarnings("unchecked")
	public void setMultas(Map multas) {
		this.multas = multas;
	}
	
    @SuppressWarnings("unchecked")
	public Object getRow(int index) {
        Object item = getMultas().get(new Integer(index));
        if (item == null) {
            item = new Sociopartida();
            getMultas().put(index, item);
        }
        return item;
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
    	/*
    	List<Sociopartida> rowsx = (List<Sociopartida>) getRows();
    	for(Sociopartida sp : rowsx) { 
    		sp.setFlCartaovermelho( 0 );
    		sp.setFlCartaoazul( 0 );
    		sp.setFlCartaoamarelo( 0 );
    		
    		sp.setFlAtrazado(  0 );
    		sp.setFlGoleiro( 0 );
    	}
    	*/
    }

}
