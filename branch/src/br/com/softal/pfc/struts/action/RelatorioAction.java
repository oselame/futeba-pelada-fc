package br.com.softal.pfc.struts.action;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.softal.pfc.Codigodescricao;
import br.com.softal.pfc.Socio;
import br.com.softal.pfc.DAO.DAOFactory;
import br.com.softal.pfc.DAO.SocioDAO;
import br.com.softal.pfc.DAO.SociopartidaDAO;
import br.com.softal.pfc.dto.RelatorioDto;
import br.com.softal.pfc.relatorio.RelSociosAtrazados;
import br.com.softal.pfc.relatorio.RelSociosAtrazados2;
import br.com.softal.pfc.util.Util;

public class RelatorioAction extends PfcAction {

 
	@SuppressWarnings("unchecked")
	public ActionForward abrirConRelatorioAtrasos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		RelatorioForm relatorioForm = (RelatorioForm) form;
		RelatorioDto relatorioDto = (RelatorioDto) relatorioForm.getEntidade();
		Calendar c = Calendar.getInstance();
		relatorioDto.setNuAno( c.get(Calendar.YEAR) );
		
		this.configuraTela(relatorioForm, request);
		
        return mapping.findForward(FWD_CADASTRAR);
    }
	
	@SuppressWarnings("unchecked")
	public ActionForward consultarRelatorioAtrasos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		RelatorioForm relatorioForm = (RelatorioForm) form;
		RelatorioDto relatorioDto = (RelatorioDto) relatorioForm.getEntidade();
		SociopartidaDAO sociopartidaDAO = DAOFactory.getSociopartidaDAO();
		List<RelatorioDto> atrazados = sociopartidaDAO.findAllSociosAtrazados(relatorioDto);
		relatorioDto.setSocios( this.processaAtrazados( atrazados ) );
		this.configuraTela(relatorioForm, request);
        return mapping.findForward(FWD_CADASTRAR);
    }
	
	@SuppressWarnings("unchecked")
	public ActionForward imprimirRelatorioAtrasos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		RelatorioForm relatorioForm = (RelatorioForm) form;
		RelatorioDto relatorioDto = (RelatorioDto) relatorioForm.getEntidade();
		SociopartidaDAO sociopartidaDAO = DAOFactory.getSociopartidaDAO();
		List<RelatorioDto> atrazados = sociopartidaDAO.findAllSociosAtrazados(relatorioDto);
		relatorioDto.setSocios( this.processaAtrazados( atrazados ) );
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		//InputStream logo = request.getSession().getServletContext().getResourceAsStream("/imagens/logoPelada.gif");
		String logo = request.getSession().getServletContext().getRealPath("/imagens/logoPelada.gif");
		
		RelSociosAtrazados2 report = new RelSociosAtrazados2(baos, logo, relatorioDto.getSocios());
		if (report != null) {
			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			String nome = "RelAtrasados_" + relatorioDto.getNuAno();
			if (!Util.isNullOrZero(relatorioDto.getNuMes())) {
				nome += "_" + relatorioDto.getNuMes();
			}
			if (!Util.isNullOrZero(relatorioDto.getCdSocio())) {
				Socio obj = new Socio();
				obj.getSocioPK().setCdSocio(relatorioDto.getCdSocio());
				obj = (Socio) DAOFactory.getSocioDAO().findByPrimaryKey(obj);
				nome += "_" + obj.getNmApelido();
			}
			nome += ".pdf";
			response.setHeader("Content-Disposition", "attachment;filename=\"" + nome + "\"");
			response.setHeader("Pragma", "public");
			response.setContentType("application/pdf");
			response.setContentLength(baos.size());
			ServletOutputStream out = response.getOutputStream();
			baos.writeTo(out);
			out.flush();

			return null;
		} else {
			this.configuraTela(relatorioForm, request);
			return mapping.findForward(FWD_CADASTRAR);
		}
    }
	
	
	private void configuraTela(ActionForm form,
			HttpServletRequest request) {
		List<Codigodescricao> listSocios = carregaSocios();
		request.setAttribute("listSocios", listSocios);	
	}	
	
	private List<Codigodescricao> carregaSocios() {
		List<Codigodescricao> listSocios = new ArrayList<Codigodescricao>();
		SocioDAO socioDAO = (SocioDAO) DAOFactory.getDAO("SocioDAO");
		List<Socio> socios = (List<Socio>) socioDAO.findAll();
		Collections.sort(socios, new Comparator<Socio>() {
			public int compare(Socio o1, Socio o2) {
				// TODO Auto-generated method stub
				return o1.getNmApelido().compareTo(o2.getNmApelido());
			}
		});
		for (Socio s : socios) {
			Codigodescricao cd = new Codigodescricao(s.getSocioPK().getCdSocio(), s.getNmApelido() + " - " + s.getNmSocio());
			listSocios.add(cd);
		}
		return listSocios;
	}
	
	private List<RelatorioDto> processaAtrazados(List<RelatorioDto> lista) {
		Map<String, Map<Date, RelatorioDto>> x = new HashMap<String, Map<Date, RelatorioDto>>();
	
		for (RelatorioDto dto : lista) {
			String mesKey = dto.getDtPartida().getMonth() + "";
			if (x.containsKey( mesKey )) {
				Map<Date, RelatorioDto> mes = x.get(mesKey);
				
				Date dataKey = dto.getDtPartida();
				if (mes.containsKey( dataKey )) {
					RelatorioDto a = mes.get( dataKey );
					String aux = a.getNmApelido();
					if (aux != null && aux != "") {
						aux += ", " + dto.getNmApelido();
					} else {
						aux = dto.getNmApelido();
					}
					a.setNmApelido(aux);
				} else {
					mes.put( dataKey , dto);
				}
			} else {
				Map<Date, RelatorioDto> mes = new HashMap<Date, RelatorioDto>();
				mes.put(dto.getDtPartida(), dto);
				x.put(mesKey, mes );
			}
		}
		
		List<RelatorioDto> retorno = new ArrayList<RelatorioDto>();
		Set<Entry<String, Map<Date, RelatorioDto>>> set = x.entrySet();
		for (Entry<String, Map<Date, RelatorioDto>> me : set) {
			RelatorioDto dto = new RelatorioDto();
			dto.setNuMes( Integer.valueOf( me.getKey() ) );
			retorno.add(dto);
			
			Map<Date, RelatorioDto> a1 = me.getValue(); //datas
			Set<Entry<Date, RelatorioDto>> set2 = a1.entrySet();
			for (Entry<Date, RelatorioDto> a2 : set2) {
				dto.getSocios().add(a2.getValue());
			}
			
		}
		
		Collections.sort(retorno, new Comparator<RelatorioDto>() {
			public int compare(RelatorioDto o1, RelatorioDto o2) {
				return o1.getNuMes().compareTo(o2.getNuMes());
			}
		});
		for (RelatorioDto dto : retorno) {
			Collections.sort(dto.getSocios(), new Comparator<RelatorioDto>() {
				public int compare(RelatorioDto o1, RelatorioDto o2) {
					return o1.getDtPartida().compareTo(o2.getDtPartida());
				}
			});
		}
		return retorno;
	}

}
