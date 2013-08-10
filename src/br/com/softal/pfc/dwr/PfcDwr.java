package br.com.softal.pfc.dwr;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.com.softal.pfc.Codigodescricao;
import br.com.softal.pfc.Partida;
import br.com.softal.pfc.DAO.DAOFactory;

public class PfcDwr  {
	
	public List<Codigodescricao> getCodigoDescricao() {
		List<Codigodescricao> list = new ArrayList<Codigodescricao>();
		Codigodescricao cd = new Codigodescricao();
		cd.setCdCodigo(1);
		cd.setDeDescricao("um");
		list.add(cd);
		
		cd = new Codigodescricao();
		cd.setCdCodigo(2); 
		cd.setDeDescricao("dois");
		list.add(cd);
		return list;
	}
	
	public Map<Integer, String> getDatasquadrimestre(Integer nuAno, Integer cdQuadrimestre) {
		Map<Integer, String> map = new LinkedHashMap<Integer, String>();
		
		List<Partida> lista = DAOFactory.getPartidaDAO().findPartidasquadrimestre(nuAno, cdQuadrimestre);
        for (Partida p : lista) {
            map.put(p.getPartidaPK().getCdPartida(), p.getDtPartidaformatada());
        }
		return map;
	}

}
