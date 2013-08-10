package br.com.softal.pfc.service;

import br.com.softal.pfc.Galeria;
import br.com.softal.pfc.Noticia;

public interface PeladaService {
	
	public void salvarGaleria(Galeria galeria) throws Exception;
	public void salvarNoticia(Noticia noticia) throws Exception;

}
