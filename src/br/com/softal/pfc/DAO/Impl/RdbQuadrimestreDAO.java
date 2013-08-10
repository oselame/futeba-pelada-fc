package br.com.softal.pfc.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.softal.pfc.Codigodescricao;
import br.com.softal.pfc.Quadrimestre;
import br.com.softal.pfc.DAO.DAOException;
import br.com.softal.pfc.DAO.QuadrimestreDAO;
import br.com.softal.pfc.DAO.ServiceLocator;
import br.com.softal.pfc.util.RdbUtil;
 
public class RdbQuadrimestreDAO implements QuadrimestreDAO {

	private static Log log = LogFactory.getLog(RdbQuadrimestreDAO.class);
	
	public List<Codigodescricao> findAnosQuadrimestres() throws DAOException {
		List<Codigodescricao> lista = new ArrayList<Codigodescricao>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" Select distinct(q.nuAno) as nuAno \n");
			sql.append(" From epfcquadrimestre q \n");
			pstmt = con.prepareStatement( sql.toString() );
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Codigodescricao cd = new Codigodescricao();
				cd.setCdCodigo( rs.getInt("nuAno") );
				cd.setDeDescricao( String.valueOf(rs.getInt("nuAno")) );
				lista.add( cd );
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return lista;
	}
	
	public void delete(Object obj) throws DAOException {
	}

	@SuppressWarnings("unchecked")
	public List findAll() throws DAOException {
		List lista = new ArrayList();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" select q.* \n");
			sql.append(" from epfcquadrimestre q \n");
			sql.append(" order by q.nuAno desc, q.cdQuadrimestre asc ");
			pstmt = con.prepareStatement( sql.toString() );
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lista.add( popule(rs) );
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	public List findAll(Object obj) throws DAOException {
		Quadrimestre quadrimestre = (Quadrimestre) obj;
		List lista = new ArrayList();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" select q.* \n");
			sql.append(" from epfcquadrimestre q \n");
			sql.append(" where 1 = 1 ");
			if (quadrimestre.getFlAnual() != null) {
				sql.append(" and q.flAnual = " + quadrimestre.getFlAnual()); 
			}
			if (quadrimestre.getQuadrimestrePK().getNuAno() != null) {
				sql.append(" and q.nuAno = " + quadrimestre.getQuadrimestrePK().getNuAno());
			}
			if (quadrimestre.getQuadrimestrePK().getCdQuadrimestre() != null) {
				sql.append(" and q.cdquadrimestre = " + quadrimestre.getQuadrimestrePK().getCdQuadrimestre());
			}
			sql.append(" order by q.cdQuadrimestre asc ");
			pstmt = con.prepareStatement( sql.toString() );
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lista.add( popule(rs) );
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return lista;
	}

	public Object findByPrimaryKey(Object obj) throws DAOException {
		Quadrimestre quadrimestre = (Quadrimestre) obj;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" select q.* \n");
			sql.append(" from epfcquadrimestre q \n");
			sql.append(" where q.nuAno = " + quadrimestre.getQuadrimestrePK().getNuAno());
			sql.append(" and q.cdQuadrimestre = " + quadrimestre.getQuadrimestrePK().getCdQuadrimestre());
			pstmt = con.prepareStatement( sql.toString() );
			rs = pstmt.executeQuery();
			if (rs.next()) {
				quadrimestre = popule(rs);
			} else {
				quadrimestre = new Quadrimestre();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return quadrimestre;
	}

	public void insert(Object obj) throws DAOException {
		Quadrimestre quadrimestre = (Quadrimestre) obj;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {			
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" insert into epfcquadrimestre (nuAno, cdQuadrimestre, cdSociocampeao, cdTitulo, dtInicio, dtFim, nuJogos, flAnual, nuJogosCampeao) ");
			sql.append(" values (?, ?, ?, ?, ?, ?, ?, ?, ?) ");
			pstmt = con.prepareStatement( sql.toString() );
			int posi = 1;
			//-- NuAno
			pstmt.setInt(posi++, quadrimestre.getQuadrimestrePK().getNuAno() );
			//-- CdQuadrimestre
			pstmt.setInt(posi++, quadrimestre.getQuadrimestrePK().getCdQuadrimestre() );
			//-- Sociocampeao
			if (quadrimestre.getCdSociocampeao() == null || quadrimestre.getCdSociocampeao() <= 0) {
				pstmt.setNull(posi++, Types.NULL);
			} else {
				pstmt.setInt(posi++, quadrimestre.getCdSociocampeao() );
			}
			//-- titulo
			if (quadrimestre.getCdTitulo() == null || quadrimestre.getCdTitulo() <= 0) {
				pstmt.setNull(posi++, Types.NULL);
			} else {
				pstmt.setInt(posi++, quadrimestre.getCdTitulo() );
			}
			//-- DataInicio
			if (quadrimestre.getDtInicio() == null) {
				pstmt.setNull(posi++, Types.NULL);
			} else {
				pstmt.setDate(posi++, new java.sql.Date(quadrimestre.getDtInicio().getTime()));
			}
			//-- DataFim
			if (quadrimestre.getDtFim() == null) {
				pstmt.setNull(posi++, Types.NULL);
			} else {
				pstmt.setDate(posi++, new java.sql.Date(quadrimestre.getDtFim().getTime()));
			}
			//--NuJogos
			if (quadrimestre.getNuJogos() == null) {
				pstmt.setNull(posi++, Types.NULL);
			} else {
				pstmt.setInt(posi++, quadrimestre.getNuJogos() );
			}
			//-- FlAnual
			pstmt.setInt(posi++, quadrimestre.getFlAnual() == null ? 0 : quadrimestre.getFlAnual());
			//-- NuJogoscampeao
			if (quadrimestre.getNuJogoscampeao() == null) {
				pstmt.setNull(posi++, Types.NULL);
			} else {
				pstmt.setInt(posi++, quadrimestre.getNuJogoscampeao() );
			}
			pstmt.executeUpdate();
			log.debug("Registro atualizado com sucesso na epfcquadrimestre");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao atualizar o registro na epfcquadrimestre", e);
			throw new DAOException("Erro ao atualizar o quadrimestre! \n" + e.getMessage());
		} finally {
			try { RdbUtil.close(pstmt, con); } catch (SQLException e) {}
		}	
	}

	public void update(Object obj) throws DAOException {
		Quadrimestre quadrimestre = (Quadrimestre) obj;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {			
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" update epfcquadrimestre ");
			sql.append(" set ");
			sql.append("     cdSociocampeao = ?, ");
			sql.append("     cdTitulo = ?, ");
			sql.append("     dtInicio = ?, ");
			sql.append("     dtFim = ?, ");
			sql.append("     nuJogos = ?, ");
			sql.append("     flAnual = ?, ");
			sql.append("     nuJogosCampeao = ? ");
			sql.append(" where nuAno = " + quadrimestre.getQuadrimestrePK().getNuAno() );
			sql.append(" and cdQuadrimestre = " + quadrimestre.getQuadrimestrePK().getCdQuadrimestre() );
			pstmt = con.prepareStatement( sql.toString() );
			int posi = 1;
			//-- SocioCampeao
			if (quadrimestre.getCdSociocampeao() == null || quadrimestre.getCdSociocampeao() <= 0) {
				pstmt.setNull(posi++, Types.NULL);
			} else {
				pstmt.setInt(posi++, quadrimestre.getCdSociocampeao() );
			}
			
			//-- titulo
			if (quadrimestre.getCdTitulo() == null || quadrimestre.getCdTitulo() <= 0) {
				pstmt.setNull(posi++, Types.NULL);
			} else {
				pstmt.setInt(posi++, quadrimestre.getCdTitulo() );
			}
			
			//-- DataInicio
			if (quadrimestre.getDtInicio() == null) {
				pstmt.setNull(posi++, Types.NULL);
			} else {
				pstmt.setDate(posi++, new java.sql.Date(quadrimestre.getDtInicio().getTime()));
			}
			
			//-- DataFim
			if (quadrimestre.getDtFim() == null) {
				pstmt.setNull(posi++, Types.NULL);
			} else {
				pstmt.setDate(posi++, new java.sql.Date(quadrimestre.getDtFim().getTime()));
			}
			
			//--NuJogos
			if (quadrimestre.getNuJogos() == null) {
				pstmt.setNull(posi++, Types.NULL);
			} else {
				pstmt.setInt(posi++, quadrimestre.getNuJogos() );
			}

			//-- FlAnual
			pstmt.setInt(posi++, quadrimestre.getFlAnual() == null ? 0 : quadrimestre.getFlAnual());

			//-- NuJogoscampeao
			if (quadrimestre.getNuJogoscampeao() == null) {
				pstmt.setNull(posi++, Types.NULL);
			} else {
				pstmt.setInt(posi++, quadrimestre.getNuJogoscampeao() );
			}
			pstmt.executeUpdate();
			log.debug("Registro atualizado com sucesso na epfcquadrimestre");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao atualizar o registro na epfcquadrimestre", e);
			throw new DAOException("Erro ao atualizar o quadrimestre! \n" + e.getMessage());
		} finally {
			try { RdbUtil.close(pstmt, con); } catch (SQLException e) {}
		}	
	}
	
	public Object findQuadrimestrePorData(Date data, Integer flAnual) {
		Quadrimestre quadrimestre = new Quadrimestre();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" select q.* \n");
			sql.append(" from epfcquadrimestre q \n");
			sql.append(" where ? between q.dtInicio and q.dtFim ");
			if (flAnual != null) {
				sql.append(" and q.flAnual = " + flAnual); 
			}
			pstmt = con.prepareStatement( sql.toString() );
			pstmt.setDate(1 , new java.sql.Date(data.getTime()) );			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				quadrimestre = popule(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return quadrimestre;
	}
	
	public static Quadrimestre popule(ResultSet rs) {
		Quadrimestre quadrimestre = new Quadrimestre();
		try { quadrimestre.getQuadrimestrePK().setNuAno( rs.getInt("nuAno") ); } catch (Exception e) {}
		try { quadrimestre.getQuadrimestrePK().setCdQuadrimestre( rs.getInt("cdQuadrimestre") ); } catch (Exception e) {}
		try { quadrimestre.setCdSociocampeao( rs.getInt("cdSociocampeao") ); } catch (Exception e) {}
		try { quadrimestre.setCdTitulo( rs.getInt("cdTitulo") ); } catch (Exception e) {}
		try { quadrimestre.setDtInicio( rs.getDate("dtInicio") ); } catch (Exception e) {}
		try { quadrimestre.setDtFim( rs.getDate("dtFim") ); } catch (Exception e) {}
		try { quadrimestre.setNuJogos( rs.getInt("nuJogos") ); } catch (Exception e) {}
		try { quadrimestre.setFlAnual( rs.getInt("flAnual") ); } catch (Exception e) {}
		try { quadrimestre.setNuJogoscampeao( rs.getInt("nuJogoscampeao") ); } catch (Exception e) {}
		return quadrimestre;
	}
	
	public Object getDadosQuadrimestre(Integer nuAno, Integer cdQuadrimestre) throws DAOException {
		Quadrimestre q = new Quadrimestre();
		q.getQuadrimestrePK().setNuAno( nuAno );
		q.getQuadrimestrePK().setCdQuadrimestre( cdQuadrimestre );
		return findByPrimaryKey(q);
	}

	public Integer findUltimoAnoCadastrado() throws DAOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" Select max(q.nuAno) as nuAno \n");
			sql.append(" From epfcquadrimestre q \n");
			pstmt = con.prepareStatement( sql.toString() );
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("nuAno");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Quadrimestre> findDadosCampeoes(Integer nuAno)
			throws DAOException {
		List lista = new ArrayList();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" select \n");
			sql.append("   q.nuano, q.cdquadrimestre, s.nmApelido, s.cdsocio, s.imfoto \n");
			sql.append(" from epfcquadrimestre q \n");
			sql.append(" left join epfcsocio s on \n");
			sql.append("   q.cdsociocampeao = s.cdsocio \n");
			sql.append(" where q.nuAno <= " + nuAno + " \n");
			sql.append(" and q.dtfim <= curdate() \n");
			sql.append(" order by q.nuano desc, q.cdquadrimestre asc ");			
			pstmt = con.prepareStatement( sql.toString() );
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Quadrimestre quadri = popule(rs);
				quadri.setSocio( RdbSocioDAO.popule(rs) );
				lista.add( quadri );
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Quadrimestre> findAllMaiorescampeoes() throws DAOException {
		List lista = new ArrayList();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT s.nmapelido, e.nuano, e.cdQuadrimestre \n");
			sql.append(" FROM epfcquadrimestre e \n");
			sql.append(" join epfcsocio s on \n");
			sql.append(" 	e.cdsociocampeao = s.cdsocio \n");
			sql.append(" order by s.nmapelido, e.nuano, e.cdQuadrimestre asc \n");		
			pstmt = con.prepareStatement( sql.toString() );
			rs = pstmt.executeQuery();
			Quadrimestre quadri = new Quadrimestre();
			String nmSocio = "";
			while (rs.next()) {
				Quadrimestre qx = popule(rs);
				qx.setSocio( RdbSocioDAO.popule(rs) );
				if (nmSocio.compareToIgnoreCase(qx.getSocio().getNmApelido()) != 0) {
					nmSocio = qx.getSocio().getNmApelido();
					quadri = popule(rs);
					quadri.setSocio( RdbSocioDAO.popule(rs) );
					quadri.getQuadrimestres().add(qx);
					quadri.setNuTitulos(1);
					lista.add( quadri );
				} else {
					quadri.getQuadrimestres().add(qx);
					quadri.setNuTitulos( quadri.getNuTitulos() + 1  );
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		
		Collections.sort(lista, new Comparator<Quadrimestre>() {
			public int compare(Quadrimestre o1, Quadrimestre o2) {
				return o2.getNuTitulos().compareTo(o1.getNuTitulos());
			}
		});
		return lista;
	}
 
}
