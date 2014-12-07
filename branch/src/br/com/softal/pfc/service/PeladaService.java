package br.com.softal.pfc.service;

import br.com.softal.pfc.Galeria;
import br.com.softal.pfc.Noticia;
import br.com.softal.pfc.Partida;

public interface PeladaService {
	
	public void salvarGaleria(Galeria galeria) throws Exception;
	public void salvarNoticia(Noticia noticia) throws Exception;
	
	//Partida
	public void reabrirCadPartida(Partida partida) throws Exception;
	public void encerrarCadPartida(Partida partida) throws Exception;
	
	public void reabrirPartidasate(Integer cdPartida) throws Exception;
	public void encerrarPartidasate(Integer cdPartida) throws Exception;
	public void encerrarTodasAsPartidas(Integer cdPartida) throws Exception;

}
