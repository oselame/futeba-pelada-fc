package br.com.softal.pfc.struts.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import br.com.softal.pfc.Sociopartida;
 
@SuppressWarnings("serial")
public class SociopartidaForm extends PfcForm {
	
	@SuppressWarnings("unchecked")
	private Map jogos;
	private Integer nuJogadorTimeA;
	private Integer nuJogadorTimeB;
	private Integer nuJogadores;
	
	@SuppressWarnings("unchecked")
	public SociopartidaForm() {
		setEntidade(new Sociopartida());
		setJogos( new HashMap() );
	}

	@SuppressWarnings("unchecked")
	public Map getJogos() {
		return jogos;
	}

	@SuppressWarnings("unchecked")
	public void setJogos(Map jogos) {
		this.jogos = jogos;
	}
	
    @SuppressWarnings("unchecked")
	public Object getRow(int index) {
        Object item = getJogos().get(new Integer(index));
        if (item == null) {
            item = new Sociopartida();
            getJogos().put(index, item);
        }
        return item;
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
    	List<Sociopartida> rowsx = (List<Sociopartida>) getRows();
    	for(Sociopartida sp : rowsx) { 
    		sp.setFlCartaovermelho( 0 );
    		sp.setFlCartaoazul( 0 );
    		sp.setFlCartaoamarelo( 0 );
    		
    		sp.setFlAtrazado(  0 );
    		sp.setFlGoleiro( 0 );
    	}
    	
    }

	public Integer getNuJogadorTimeA() {
		return nuJogadorTimeA;
	}

	public void setNuJogadorTimeA(Integer nuJogadorTimeA) {
		this.nuJogadorTimeA = nuJogadorTimeA;
	}

	public Integer getNuJogadorTimeB() {
		return nuJogadorTimeB;
	}

	public void setNuJogadorTimeB(Integer nuJogadorTimeB) {
		this.nuJogadorTimeB = nuJogadorTimeB;
	}

	public Integer getNuJogadores() {
		return nuJogadores;
	}

	public void setNuJogadores(Integer nuJogadores) {
		this.nuJogadores = nuJogadores;
	}
}
