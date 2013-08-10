package br.com.softal.pfc.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.softal.pfc.Classificacao;
import br.com.softal.pfc.Partida;
import br.com.softal.pfc.Quadrimestre;
import br.com.softal.pfc.Socio;
import br.com.softal.pfc.DAO.ClassificacaoDAO;
import br.com.softal.pfc.DAO.DAOException;
import br.com.softal.pfc.DAO.DAOFactory;
import br.com.softal.pfc.DAO.PartidaDAO;
import br.com.softal.pfc.DAO.QuadrimestreDAO;
import br.com.softal.pfc.DAO.ServiceLocator;
import br.com.softal.pfc.dto.ClassificacaoDto;
import br.com.softal.pfc.util.RdbUtil;
 
public class RdbClassificacaoDAO implements ClassificacaoDAO {

	private static Log log = LogFactory.getLog(RdbClassificacaoDAO.class);
	
	public void delete(Object obj) throws DAOException {
	}
	
	@SuppressWarnings("unchecked")
	public List findAll() throws DAOException {
		return null;
	}

	@SuppressWarnings("unchecked")
	public List findAll(Object obj) throws DAOException {
		return null;
	}

	public Object findByPrimaryKey(Object obj) throws DAOException {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List findRanking(Object obj) throws DAOException {		
		Classificacao classificacao = (Classificacao) obj;
		List lista = new ArrayList();
		List listaZero = new ArrayList();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" Select s.cdSocio, s.nmApelido, s.nmSocio, \n");
			sql.append(" 	c.cdPartida, c.cdQuadrimestre, c.nuClassificacao, c.nuPontos, \n"); 
			sql.append(" 	c.nuJogos, c.nuVitorias, c.nuEmpates, c.nuDerrotas, c.nuCartaovermelho, \n");
			sql.append(" 	c.nuCartaoazul, c.nuCartaoamarelo, c.nuPosicaoanterior \n");
			sql.append(" from epfcsocio s \n");
			sql.append(" left join epfcclassificacao c on \n"); 
			sql.append(" 	s.cdSocio = c.cdSocio \n"); 
			sql.append(" 	and c.nuAno = " + classificacao.getNuAno() + " \n");
			sql.append(" 	and c.cdQuadrimestre = " + classificacao.getCdQuadrimestre() + " \n");
			if (classificacao.getCdPartida() == null || classificacao.getCdPartida() == 0) {
				sql.append(" 	and c.cdPartida = (Select max(x.cdPartida) from epfcclassificacao x ");
				sql.append(" 	where x.nuAno = " + classificacao.getNuAno() + " \n");
				sql.append(" 	and x.cdQuadrimestre = " + classificacao.getCdQuadrimestre() + ") \n");
			}
			sql.append(" Where ((s.flForauso is null) or (s.flForauso = 0)) \n");
			if (classificacao.getCdPartida() != null && classificacao.getCdPartida() > 0) {
				sql.append(" 	and c.cdPartida = " + classificacao.getCdPartida() + " \n ");
			}
			sql.append(" order by c.nuClassificacao asc ");			
			pstmt = con.prepareStatement( sql.toString() );
			rs = pstmt.executeQuery();
			while (rs.next()) {				
				Classificacao cl = popule(rs);
				Socio socio = RdbSocioDAO.popule(rs);
				cl.setSocio( socio );
				if (cl.getCdPartida() != null && cl.getCdPartida() > 0) {
					lista.add( cl );
				} else {
					listaZero.add( cl );
				}
			}
			pstmt = con.prepareStatement( sql.toString() );
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}	
		return lista;
	}
	

	public void insert(Object obj) throws DAOException {
		Classificacao classificacao = (Classificacao) obj;
		Connection con = null;
		PreparedStatement pstmt = null;		
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" insert into epfcclassificacao (nuRanking, cdSocio, cdPartida, nuAno, cdQuadrimestre, " +
					"nuClassificacao, nuPontos, nuJogos, nuVitorias, nuEmpates, " +
					"nuDerrotas, nuCartaovermelho, nuCartaoazul, nuCartaoamarelo," +
					"nuPosicaoanterior) values (?, ?, ?, ?, ?, " +
					"?, ?, ?, ?, ?, " +
					"?, ?, ?, ?," +
					"?) "); 
			
			pstmt = con.prepareStatement( sql.toString() );
			int posi = 1;
			pstmt.setNull(posi++, Types.NULL); // nuRanking
			pstmt.setInt(posi++, classificacao.getCdSocio() );
			pstmt.setInt(posi++, classificacao.getCdPartida() );
			pstmt.setInt(posi++, classificacao.getNuAno());
			pstmt.setInt(posi++, classificacao.getCdQuadrimestre() );
			pstmt.setInt(posi++, classificacao.getNuClassificacao() );
			pstmt.setInt(posi++, classificacao.getNuPontos() );
			pstmt.setInt(posi++, classificacao.getNuJogos() );
			pstmt.setInt(posi++, classificacao.getNuVitorias() );
			pstmt.setInt(posi++, classificacao.getNuEmpates() );
			pstmt.setInt(posi++, classificacao.getNuDerrotas() );
			pstmt.setInt(posi++, classificacao.getNuCartaovermelho() );
			pstmt.setInt(posi++, classificacao.getNuCartaoazul() );
			pstmt.setInt(posi++, classificacao.getNuCartaoamarelo() );
			pstmt.setInt(posi++, classificacao.getNuPosicaoanterior() );
			pstmt.executeUpdate();
			log.debug("Registro inserido com sucesso na epfcclassificacao.");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao inserir o registro na epfcclassificacao.", e);
		} finally {
			try { RdbUtil.close(pstmt, con); } catch (SQLException e) {}
		}	
	}

	public void update(Object obj) throws DAOException {
	}
	
	private Map<String, ClassificacaoDto> carregaDadosPartidaAtual(Partida partida) throws DAOException {
		Map<String, ClassificacaoDto> map = new HashMap<String, ClassificacaoDto>();
		List<ClassificacaoDto> lista = new ArrayList<ClassificacaoDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" Select s.cdSocio, s.nmApelido, s.nmSocio, \n"); 
			sql.append(" 	sp.cdTime, sp.nuGol, sp.flCartaovermelho, sp.flCartaoazul,  sp.flCartaoamarelo, \n");
			sql.append(" 	p.cdTimevencedor, p.cdTimeperdedor, p.flEmpate \n");
			sql.append(" from epfcsocio s \n");
			sql.append(" left join epfcsociopartida sp on \n");
			sql.append(" 	s.cdSocio = sp.cdSocio \n");
			sql.append(" 	and sp.cdPartida = " + partida.getPartidaPK().getCdPartida() + " \n"); 
			sql.append(" left join epfcpartida p on \n");
			sql.append(" 	sp.cdPartida = p.cdPartida \n");
			sql.append(" Where ((s.flForauso is null) or (s.flForauso = 0)) \n");
			sql.append(" order by s.nmApelido asc \n");			
			pstmt = con.prepareStatement( sql.toString() );
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ClassificacaoDto clDto = new ClassificacaoDto();
				try { clDto.setCdSocio( rs.getInt("cdSocio") );  } catch (Exception e) {}
				try { clDto.setNmApelido( rs.getString("nmApelido") );  } catch (Exception e) {}
				try { clDto.setNmSocio( rs.getString("nmSocio") );  } catch (Exception e) {}
				try { clDto.setCdTime( rs.getInt("cdTime") );  } catch (Exception e) {}
				try { clDto.setNuGol( rs.getInt("nuGol") );  } catch (Exception e) {}
				try { clDto.setFlCartaovermelho( rs.getInt("flCartaovermelho") );  } catch (Exception e) {}
				try { clDto.setFlCartaoazul( rs.getInt("flCartaoazul") );  } catch (Exception e) {}
				try { clDto.setFlCartaoamarelo( rs.getInt("flCartaoamarelo") );  } catch (Exception e) {}
				try { clDto.setCdTimevencedor( rs.getInt("cdTimevencedor") );  } catch (Exception e) {}
				try { clDto.setCdTimeperdedor( rs.getInt("cdTimeperdedor") );  } catch (Exception e) {}
				try { clDto.setFlEmpate( rs.getInt("flEmpate") );  } catch (Exception e) {}
				lista.add(clDto);
				
				map.put(clDto.getCdSocio().toString(), clDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return map;
	}
	
	private List<Classificacao> findDadosPartidaAnterior(Integer cdPartida, Integer nuAno, Boolean bQuadrimestreAnual) {
		List<Classificacao> lista = new ArrayList<Classificacao>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" Select s.cdSocio, s.nmApelido, s.nmSocio, \n");
			sql.append(" 	c.cdPartida, c.cdQuadrimestre, c.nuClassificacao, c.nuPontos, \n"); 
			sql.append(" 	c.nuJogos, c.nuVitorias, c.nuEmpates, c.nuDerrotas, c.nuCartaovermelho, \n");
			sql.append(" 	c.nuCartaoazul, c.nuCartaoamarelo, c.nuPosicaoanterior \n");
			sql.append(" from epfcsocio s \n");
			sql.append(" left join epfcclassificacao c on \n"); 
			sql.append(" 	s.cdSocio = c.cdSocio \n");
			sql.append(" 	and c.cdPartida = (Select max(x.cdPartida) from epfcclassificacao x where x.cdPartida < " + cdPartida + ") \n");
			if (bQuadrimestreAnual) {
				sql.append(" 	and c.cdQuadrimestre = 4 \n");
			} else {
				sql.append(" 	and c.cdQuadrimestre <> 4 \n");
			}
			sql.append(" 	and c.nuAno = " + nuAno);
			sql.append(" Where ((s.flForauso is null) or (s.flForauso = 0)) \n"); 
			sql.append(" order by c.nuClassificacao asc ");	
			pstmt = con.prepareStatement( sql.toString() );
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Classificacao cl = popule(rs);
				Socio socio = RdbSocioDAO.popule(rs);
				cl.setSocio( socio );				
				lista.add( cl );
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public void encerrarPartidaQuadrimestreAtual(Partida partida) throws DAOException {
		List<Classificacao> listaPosicaoAnterior = new ArrayList<Classificacao>();
		try {
			Boolean mudouAno = false;
			Integer nuAno = null;
			Integer cdPartida = partida.getPartidaPK().getCdPartida();
			Partida partidaAnterior = DAOFactory.getPartidaDAO().findPartidaanterior(cdPartida);
			
			//-- Dados da Partida Atual
			Map<String, ClassificacaoDto> map = carregaDadosPartidaAtual( partida );
			
			if (partidaAnterior != null) {
				if (partidaAnterior.getNuAno().equals(partida.getNuAno())) {
					nuAno = partida.getNuAno();
					listaPosicaoAnterior = findDadosPartidaAnterior(cdPartida, nuAno, false);
				} else {
					mudouAno = true;
					nuAno = partidaAnterior.getNuAno();
					listaPosicaoAnterior = findDadosPartidaAnterior(cdPartida, nuAno, true);
				}
			} else {
				listaPosicaoAnterior.add(new Classificacao());
			}
			
			//-- Carrega dados classificacao anterior
			try {
				
				Integer totalSocios = DAOFactory.getSocioDAO().getQuantidesocios();
				
				for (Classificacao cl : listaPosicaoAnterior) {		
					Boolean mudouQuadrimestre = cl.getCdQuadrimestre().intValue() != partida.getCdQuadrimestre().intValue();
					if (mudouAno || mudouQuadrimestre) {
						cl.setNuPontos(0);
						cl.setNuEmpates(0);
						cl.setNuVitorias(0);
						cl.setNuDerrotas(0);
						cl.setNuJogos(0);
						cl.setNuCartaovermelho(0);
						cl.setNuCartaoazul(0);
						cl.setNuCartaoamarelo(0);
					}
					
					cl.setCdPartida( partida.getPartidaPK().getCdPartida() );
					cl.setNuAno( partida.getNuAno() );
					cl.setCdQuadrimestre( partida.getCdQuadrimestre() );
					if (cl.getNuClassificacao() == 0) {
						cl.setNuPosicaoanterior( totalSocios++ );
					} else {
						cl.setNuPosicaoanterior( cl.getNuClassificacao() );
					}
					
					ClassificacaoDto pAtual = map.get(cl.getCdSocio().toString());
					
					
					//-- Calculos				
					if (pAtual.getCdTime() != null && pAtual.getCdTime() > 0) {
						if (partida.getFlEmpate() == 1) {
							cl.setNuPontos( cl.getNuPontos() + 1 );
							cl.setNuEmpates( cl.getNuEmpates() + 1 );
						} else if (partida.getCdTimevencedor().intValue() == pAtual.getCdTime().intValue()) {
							cl.setNuPontos( cl.getNuPontos() + 3 );
							cl.setNuVitorias( cl.getNuVitorias() + 1 );
						} else {
							//cl.setNuPontos( cl.getNuCartaoamarelo() );
							cl.setNuDerrotas( cl.getNuDerrotas() + 1 );
						}
						cl.setNuJogos( cl.getNuJogos() + 1 );
						if (pAtual.getFlCartaovermelho() == 1) {
							cl.setNuCartaovermelho( cl.getNuCartaovermelho() + 1);
							cl.setNuPontos( cl.getNuPontos() - 3 );
						}
						if (pAtual.getFlCartaoazul() == 1) {
							cl.setNuCartaoazul( cl.getNuCartaoazul() + 1);
							cl.setNuPontos( cl.getNuPontos() - 2 );
						}
						if (pAtual.getFlCartaoamarelo() == 1) {
							cl.setNuCartaoamarelo( cl.getNuCartaoamarelo() + 1);
							cl.setNuPontos( cl.getNuPontos() - 1 );
						}
					}
				}
	
				//-- Ordena pelo codigo auxiliar			
				Collections.sort( listaPosicaoAnterior, new Comparator() {
					public int compare(Object o1, Object o2) {
						if ((o1 instanceof Classificacao) && (o2 instanceof Classificacao)) {
							Classificacao c1 = (Classificacao) o1;
							Classificacao c2 = (Classificacao) o2;
							return c2.getCdAuxiliar().compareToIgnoreCase(c1.getCdAuxiliar());
						}
						return 0;
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
			}
			
			//-- Insere/Atualiza a classificacao atual
			int posicao = 0;
			for (Classificacao cl : listaPosicaoAnterior) {
				cl.setNuClassificacao( ++posicao );
				if (posicao == 1) {
					atualizaDadosQuadrimestre(cl, partida.getNuAno(), partida.getCdQuadrimestre());
				}
				this.insert( cl );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void encerrarPartidaAnual(Partida partida) throws DAOException {
		List<Classificacao> listaPosicaoAnterior = new ArrayList<Classificacao>();
		try {
			Boolean mudouAno = false;
			Integer nuAno = null;
			Integer cdPartida = partida.getPartidaPK().getCdPartida();
			Partida partidaAnterior = DAOFactory.getPartidaDAO().findPartidaanterior(cdPartida);
			
			//-- Dados da Partida Atual
			Map<String, ClassificacaoDto> map = carregaDadosPartidaAtual( partida );
			
			if (partidaAnterior != null) {
				if (partidaAnterior.getNuAno().equals(partida.getNuAno())) {
					nuAno = partida.getNuAno();
					listaPosicaoAnterior = findDadosPartidaAnterior(cdPartida, nuAno, true);
				} else {
					mudouAno = true;
					nuAno = partidaAnterior.getNuAno();
					listaPosicaoAnterior = findDadosPartidaAnterior(cdPartida, nuAno, true);
				}
			} else {
				listaPosicaoAnterior.add(new Classificacao());
			}
			
			//-- Carrega dados classificacao anterior
			try {
				
				Integer totalSocios = DAOFactory.getSocioDAO().getQuantidesocios();
				
				for (Classificacao cl : listaPosicaoAnterior) {		
					//Boolean mudouQuadrimestre = cl.getCdQuadrimestre().intValue() != partida.getCdQuadrimestre().intValue();
					if (mudouAno) { //--  || mudouQuadrimestre
						cl.setNuPontos(0);
						cl.setNuEmpates(0);
						cl.setNuVitorias(0);
						cl.setNuDerrotas(0);
						cl.setNuJogos(0);
						cl.setNuCartaovermelho(0);
						cl.setNuCartaoazul(0);
						cl.setNuCartaoamarelo(0);
					}
					
					cl.setCdPartida( partida.getPartidaPK().getCdPartida() );
					cl.setNuAno( partida.getNuAno() );
					cl.setCdQuadrimestre( 4 );
					if (cl.getNuClassificacao() == 0) {
						cl.setNuPosicaoanterior( totalSocios++ );
					} else {
						cl.setNuPosicaoanterior( cl.getNuClassificacao() );
					}
					
					ClassificacaoDto pAtual = map.get(cl.getCdSocio().toString());
					
					//-- Calculos				
					if (pAtual.getCdTime() != null && pAtual.getCdTime() > 0) {
						if (partida.getFlEmpate() == 1) {
							cl.setNuPontos( cl.getNuPontos() + 1 );
							cl.setNuEmpates( cl.getNuEmpates() + 1 );
						} else if (partida.getCdTimevencedor().intValue() == pAtual.getCdTime().intValue()) {
							cl.setNuPontos( cl.getNuPontos() + 3 );
							cl.setNuVitorias( cl.getNuVitorias() + 1 );
						} else {
							//cl.setNuPontos( cl.getNuCartaoamarelo() );
							cl.setNuDerrotas( cl.getNuDerrotas() + 1 );
						}
						cl.setNuJogos( cl.getNuJogos() + 1 );
						if (pAtual.getFlCartaovermelho() == 1) {
							cl.setNuCartaovermelho( cl.getNuCartaovermelho() + 1);
							cl.setNuPontos( cl.getNuPontos() - 3 );
						}
						if (pAtual.getFlCartaoazul() == 1) {
							cl.setNuCartaoazul( cl.getNuCartaoazul() + 1);
							cl.setNuPontos( cl.getNuPontos() - 2 );
						}
						if (pAtual.getFlCartaoamarelo() == 1) {
							cl.setNuCartaoamarelo( cl.getNuCartaoamarelo() + 1);
							cl.setNuPontos( cl.getNuPontos() - 1 );
						}
					}
				}
	
				//-- Ordena pelo codigo auxiliar			
				Collections.sort( listaPosicaoAnterior, new Comparator() {
					public int compare(Object o1, Object o2) {
						if ((o1 instanceof Classificacao) && (o2 instanceof Classificacao)) {
							Classificacao c1 = (Classificacao) o1;
							Classificacao c2 = (Classificacao) o2;
							return c2.getCdAuxiliar().compareToIgnoreCase(c1.getCdAuxiliar());
						}
						return 0;
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
			}
			
			//-- Insere/Atualiza a classificacao atual
			int posicao = 0;
			for (Classificacao cl : listaPosicaoAnterior) {
				cl.setNuClassificacao( ++posicao );
				if (posicao == 1) {
					atualizaDadosQuadrimestre(cl, partida.getNuAno(), cl.getCdQuadrimestre());
				}
				this.insert( cl );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}	
	
	public static Classificacao popule(ResultSet rs) {
		Classificacao cl = new Classificacao();
		try { cl.getClassificacaoPK().setNuRanking( rs.getInt("nuRanking") );  } catch (Exception e) {}
		try { cl.setCdSocio( rs.getInt("cdSocio") );  } catch (Exception e) {}
		try { cl.setCdPartida( rs.getInt("cdPartida") );  } catch (Exception e) {}
		try { cl.setCdQuadrimestre( rs.getInt("cdQuadrimestre") );  } catch (Exception e) {}
		try { cl.setNuClassificacao( rs.getInt("nuClassificacao") );  } catch (Exception e) {}
		try { cl.setNuPontos( rs.getInt("nuPontos") );  } catch (Exception e) {}
		try { cl.setNuJogos( rs.getInt("nuJogos") );  } catch (Exception e) {}
		try { cl.setNuVitorias( rs.getInt("nuVitorias") );  } catch (Exception e) {}
		try { cl.setNuEmpates( rs.getInt("nuEmpates") );  } catch (Exception e) {}
		try { cl.setNuDerrotas( rs.getInt("nuDerrotas") );  } catch (Exception e) {}
		try { cl.setNuCartaovermelho( rs.getInt("nuCartaovermelho") );  } catch (Exception e) {}
		try { cl.setNuCartaoazul( rs.getInt("nuCartaoazul") );  } catch (Exception e) {}
		try { cl.setNuCartaoamarelo( rs.getInt("nuCartaoamarelo") );  } catch (Exception e) {}
		try { cl.setNuPosicaoanterior( rs.getInt("nuPosicaoanterior") );  } catch (Exception e) {}
		return cl;
	}
	
	private void atualizaDadosQuadrimestre(Classificacao cl, Integer nuAno, Integer cdQuadrimestre) {
		//-- Dados da partida
		PartidaDAO partidaDAO = DAOFactory.getPartidaDAO();
		Integer nuTotalPartidas = partidaDAO.findTotalPartidas( nuAno, cdQuadrimestre);
		try {
			//-- Dados do Quadrimestre
			Quadrimestre quadri = new Quadrimestre();
			quadri.getQuadrimestrePK().setNuAno( nuAno );
			quadri.getQuadrimestrePK().setCdQuadrimestre( cdQuadrimestre );
			QuadrimestreDAO quadrimestreDAO = DAOFactory.getQuadrimestreDAO();
			quadri = (Quadrimestre) quadrimestreDAO.findByPrimaryKey( quadri );
			quadri.setCdSociocampeao( cl.getCdSocio() );
			quadri.setNuJogos( cl.getNuJogos() );
			quadri.setNuJogos( nuTotalPartidas );
			quadrimestreDAO.update( quadri );
			log.debug("Quadrimestre atualizado com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao atualizar o Quadrimestre.", e);
		}
	}
	
	public void excluirClassificacaoPartida(Integer cdPartida) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" delete from epfcclassificacao \n"); 
			sql.append(" where cdpartida = " + cdPartida); 
			pstmt = con.prepareStatement( sql.toString() );
			pstmt.executeUpdate();
			log.debug("Registro excluido com sucesso da epfcclassificacao.");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao excluir o registro da epfcclassificacao.", e);
		} finally {
			try { RdbUtil.close(pstmt, con); } catch (SQLException e) {}
		}	
	}
	
	public void excluirClassificacoes() throws DAOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" delete from epfcclassificacao \n"); 
			pstmt = con.prepareStatement( sql.toString() );
			pstmt.executeUpdate();
			log.debug("Registro excluido com sucesso da epfcclassificacao.");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao excluir o registro da epfcclassificacao.", e);
		} finally {
			try { RdbUtil.close(pstmt, con); } catch (SQLException e) {}
		}			
	}
 
}