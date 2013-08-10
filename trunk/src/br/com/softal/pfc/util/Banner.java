package br.com.softal.pfc.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import br.com.softal.pfc.Constantes;
import br.com.softal.pfc.dto.Logo;

public class Banner {
	
	@SuppressWarnings("unused")
	private final String path = "/banners";
	
	public enum Tipoimagem{BANNER, PATROCIONIO};
	
	private static String retornaCaminhoBanner(HttpServletRequest request, Tipoimagem tipoimagem) {
        String sCaminho = request.getSession().getServletContext().getRealPath("");
        sCaminho = sCaminho.replaceAll("\\\\", "/");
        try {
        	sCaminho = sCaminho.substring(0, sCaminho.lastIndexOf("/"));
		} catch (Exception e) {
			sCaminho = sCaminho.substring(0, sCaminho.lastIndexOf("//"));
		}
		if (tipoimagem==Tipoimagem.BANNER) {
			return sCaminho += "/fotos/banners/"; 
		} else if (tipoimagem==Tipoimagem.PATROCIONIO) {
			return sCaminho += "/fotos/patrocinios/";
		} else {
			return sCaminho += "/fotos/banners/"; 
		}
	}

	@SuppressWarnings("unchecked")
	public static void getBanners(HttpServletRequest request){
		ArrayList<Logo> bannersList = new ArrayList<Logo>();
		if (request.getSession().getAttribute(Constantes.SESSION_BANNERS) == null) {	
			String sCaminhoBanners = retornaCaminhoBanner(request, Tipoimagem.BANNER);
			Banner.getBannersPatrocionios(bannersList, sCaminhoBanners, Tipoimagem.BANNER);
			request.getSession().setAttribute(Constantes.SESSION_BANNERS, bannersList);
		} 
		bannersList = (ArrayList) request.getSession().getAttribute(Constantes.SESSION_BANNERS);
			
		Collections.shuffle(bannersList);
		int x = 0;
		int tamBanners = bannersList.size();
		for(int i = 0; i < 7; i++){
			/*if (i % (bannersList.size()-1) == 0) {
				x = 0;
			}*/
			if (tamBanners < 7) {
				request.setAttribute(Constantes.SESSION_BANNER + String.valueOf(i), bannersList.get(x));
				x++;
				if (x >= tamBanners) {
					x = 0;
				}
			} else {
				request.setAttribute(Constantes.SESSION_BANNER + String.valueOf(i), bannersList.get(x));
				x++;
			}
			/*if ((i % (bannersList.size()-1) == 0) || (i > (bannersList.size()-1))) {
				x = 0;
			}*/
		
		}
	}

	private static void getBannersPatrocionios(ArrayList<Logo> bannersList,
			String sCaminhoBanners, Tipoimagem tipoimagem) {
		File dirBanners = new File(sCaminhoBanners);
		File[] banners = dirBanners.listFiles();
		String arquivoLinks = "";
		for(int i = 0; i < banners.length; i++){
			String banner = FileToString(banners[i], tipoimagem);
			boolean isLinks = ehArquivoDeLinks(banner);
			if (!isLinks) {
				Logo logo = new Logo();
				logo.setNmLogo(banner);
				logo.setDeCaminho( banners[i].getPath() );
				//logo.setNmLink(nmLink);
				bannersList.add( logo );
			} else {
				arquivoLinks = banners[i].getPath();
			}
		}
		
		File arquivoDeLinks = new File(arquivoLinks);
		if (arquivoDeLinks.exists()) {
			try {
				Properties propers = new Properties();
				propers.load(new FileInputStream(arquivoLinks));
				for (Logo logo : bannersList) {
					String nmLink = propers.getProperty(logo.getNmLogo());
					logo.setNmLink( nmLink );
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void getPatrocinios(HttpServletRequest request) {
		JSONArray jsonArray = new JSONArray();
		ArrayList<Logo> bannersList = new ArrayList<Logo>();
		if (request.getSession().getAttribute(Constantes.SESSION_PATROCIONIOS) == null) {	
			String sCaminhoBanners = retornaCaminhoBanner(request,Tipoimagem.PATROCIONIO);
			Banner.getBannersPatrocionios(bannersList, sCaminhoBanners, Tipoimagem.PATROCIONIO);
			request.getSession().setAttribute(Constantes.SESSION_PATROCIONIOS, bannersList);
		} 
		bannersList = (ArrayList) request.getSession().getAttribute(Constantes.SESSION_PATROCIONIOS);
			
		Collections.shuffle(bannersList);
		int x = 0;
		for (Logo s : bannersList) {
			jsonArray.add( s.getNmLogo() );
			request.setAttribute(Constantes.SESSION_PATROCIONIO + x, s);
			x++;
		}
		request.getSession().setAttribute(Constantes.SESSION_JSON_PATROCIONIOS, jsonArray.toString());
		request.getSession().setAttribute(Constantes.SESSION_NUMERO_PATROCIONIOS, bannersList.size());
	}
	
	private static boolean ehArquivoDeLinks(String banner) {
		if (banner.equalsIgnoreCase(Constantes.ARQUIVO_LINKS)) {
			return true;
		}
		return false;
	}

	private static String FileToString(File file, Tipoimagem tipoimagem){
		String caminho = "";
		try {
			String tipo = "banners";
			if (tipoimagem==Tipoimagem.PATROCIONIO) {
				tipo = "patrocinios";
			}
			String nome = file.getPath();
			int posBan = nome.lastIndexOf(tipo);
			caminho = nome.substring(posBan + tipo.length() + 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return caminho;
	}
	
}
