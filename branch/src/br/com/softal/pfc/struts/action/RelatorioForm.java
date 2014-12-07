package br.com.softal.pfc.struts.action;

import br.com.softal.pfc.dto.RelatorioDto;
 
public class RelatorioForm extends PfcForm<RelatorioDto> {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioForm() {
		setEntidade(new RelatorioDto());
	}
	
}
