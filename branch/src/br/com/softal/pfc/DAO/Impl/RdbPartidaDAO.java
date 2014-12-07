package br.com.softal.pfc.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.softal.pfc.Codigodescricao;
import br.com.softal.pfc.Partida;
import br.com.softal.pfc.Quadrimestre;
import br.com.softal.pfc.DAO.DAOException;
import br.com.softal.pfc.DAO.DAOFactory;
import br.com.softal.pfc.DAO.PartidaDAO;
import br.com.softal.pfc.DAO.QuadrimestreDAO;
import br.com.softal.pfc.DAO.ServiceLocator;
import br.com.softal.pfc.util.RdbUtil;
import br.com.softal.pfc.util.Util;
 
public class RdbPartidaDAO implements PartidaDAO {

	private static Log log = LogFactory.getLog(RdbPartidaDAO.class);
	
	public void delete(Object obj) throws DAOException {
		Partida partida = (Partida) obj;
		Connection con = null;
		PreparedStatement pstmt = null;
		con = ServiceLocator.getConexao();
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" delete from epfcpartida \n");
			sql.append(" where cdpartida = ? ");
			pstmt = con.prepareStatement( sql.toString() );
			pstmt.setInt(1, partida.getPartidaPK().getCdPartida());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		} finally {
			try { RdbUtil.close(pstmt, con); } catch (SQLException e) {}
		}
	}
	
	public void excluirPartida(Partida partida) throws DAOException {
		Connection con = ServiceLocator.getConexao();
		try {
			con.setAutoCommit(false);
			DAOFactory.getSociopartidaDAO().deleteSociospartida( partida.getPartidaPK().getCdPartida() );
			delete(partida);
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new DAOException(e.getMessage());
		} finally {
			try { 
				RdbUtil.close(null, null, con); 
				con.setAutoCommit(true);
			} catch (SQLException e) {}
		}
	}
	
	public void atualizaResultado(Integer cdPartida, Integer cdTimeVencedor, 
			Integer nuGolsVencedor, Integer nuGolsPerdedor) throws DAOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {			
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("update epfcpartida \n");
			sql.append("set cdTimeperdedor = ?, \n");
			sql.append("    cdTimevencedor = ?, \n");
			sql.append("    flEmpate = ?, \n");
			sql.append("    nuGolvencedor = ?, \n");
			sql.append("    nuGolperdedor = ? \n");
			sql.append("where cdPartida = " + cdPartida);			
			pstmt = con.prepareStatement( sql.toString() );
			int posi = 1;
			if (cdTimeVencedor == 1) {
				pstmt.setInt(posi++, 2);
				pstmt.setInt(posi++, 1);
			} else {
				pstmt.setInt(posi++, 1);
				pstmt.setInt(posi++, 2);
			}
			if (nuGolsPerdedor == nuGolsVencedor) {
				pstmt.setInt(posi++, 1);
			} else {
				pstmt.setInt(posi++, 0);
			}
			pstmt.setInt(posi++, nuGolsVencedor);
			pstmt.setInt(posi++, nuGolsPerdedor);
			pstmt.executeUpdate();
			log.debug("Registro atualizado com sucesso na epfcpartida.");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao atualizar o registro nana epfcpartida.", e);
		} finally {
			try { RdbUtil.close(pstmt, con); } catch (SQLException e) {}
		}	
	}

	public List<Partida> findAll() throws DAOException {
		List<Partida> lista = new ArrayList<Partida>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" select p.* \n");
			sql.append(" from epfcpartida p \n");
			sql.append(" order by p.cdpartida asc \n");
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

	public List<Partida> findAll(Object obj) throws DAOException {
		return null;
	}
	
	public List<Partida> findPartidasquadrimestre(Integer nuAno, Integer cdQuadrimestre) throws DAOException {
		List<Partida> lista = new ArrayList<Partida>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" select p.* \n");
			sql.append(" from epfcpartida p \n");
			sql.append(" join epfcquadrimestre q on \n");
			sql.append(" 	q.cdQuadrimestre = p.cdQuadrimestre \n");
			sql.append(" 	and q.nuAno = p.nuAno \n");
			sql.append(" where q.nuAno = " + nuAno);
			if (cdQuadrimestre != null && cdQuadrimestre > 0) {
				sql.append("\n and p.cdquadrimestre = " + cdQuadrimestre);
			}
			sql.append("\n order by p.dtpartida desc, p.cdPartida desc \n");
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
		Partida partida = (Partida) obj;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" select p.* \n");
			sql.append(" from epfcpartida p \n");
			sql.append(" where p.cdPartida = " + partida.getPartidaPK().getCdPartida() );
			pstmt = con.prepareStatement( sql.toString() );
			rs = pstmt.executeQuery();
			if (rs.next()) {
				partida = popule(rs);
			} else {
				partida = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return partida;
	}

	public void insert(Object obj) throws DAOException {
		Partida ultimaPartida = getUltimapartida();
		Integer cdPartida = 1;
		if (ultimaPartida != null) {
			cdPartida += ultimaPartida.getPartidaPK().getCdPartida();
		}
		Partida partida = (Partida) obj;
		partida.getPartidaPK().setCdPartida(cdPartida);
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Integer cdQuadrimestre = pegaQuadrimestre(partida.getDtPartida());
			Integer nuAno = pegaAnoQuadrimestre(partida.getDtPartida());
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" insert into epfcpartida (cdpartida, nuano, cdquadrimestre, cdtimeperdedor, cdtimevencedor, dtpartida, " +
					"flempate, nugolvencedor, nugolperdedor, nmjuiz, debolamurcha, debolacheia, deobservacao, flconcluida)  ");
			sql.append(" values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)  ");
			pstmt = con.prepareStatement( sql.toString() );
			int posi = 1;
			pstmt.setInt(posi++, partida.getPartidaPK().getCdPartida());
			pstmt.setInt(posi++, nuAno);
			pstmt.setInt(posi++, cdQuadrimestre);
			pstmt.setNull(posi++, Types.NULL);
			pstmt.setNull(posi++, Types.NULL);
			pstmt.setDate(posi++, new java.sql.Date(partida.getDtPartida().getTime()) );
			pstmt.setInt(posi++, partida.getFlEmpate());
			pstmt.setInt(posi++, partida.getNuGolvencedor());
			pstmt.setInt(posi++, partida.getNuGolperdedor());
			pstmt.setString(posi++, partida.getNmJuiz());
			pstmt.setString(posi++, partida.getDeBolamurcha() );
			pstmt.setString(posi++, partida.getDeBolacheia() );
			pstmt.setString(posi++, partida.getDeObservacao() );
			pstmt.setInt(posi++, partida.getFlConcluida() );
			pstmt.executeUpdate();
			//partida.getPartidaPK().setCdPartida( getCodigoGerado() );
			log.debug("Registro inserido com sucesso na epfcpartida.");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao inserir o registro na na epfcpartida.", e);
		} finally {
			try { RdbUtil.close(pstmt, con); } catch (SQLException e) {}
		}	
	}
	
	@SuppressWarnings("unused")
	private Integer getCodigoGerado(Connection con) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer codigo = 1;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT LAST_INSERT_ID() ");
			pstmt = con.prepareStatement( sql.toString() );
			rs = pstmt.executeQuery();
			if (rs.next()) {
				codigo = rs.getInt(1);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt); } catch (SQLException e) {}
		}	
		return codigo;	
	}

	public void update(Object obj) throws DAOException {
		Partida partida = (Partida) obj;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Integer cdQuadrimestre = pegaQuadrimestre(partida.getDtPartida());			
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("update epfcpartida \n");
			sql.append("set nuano = ?, \n");
			sql.append("	cdquadrimestre = ?, \n");
			sql.append("    cdtimeperdedor = ?, \n");
			sql.append("    cdtimevencedor = ?, \n");
			sql.append("    dtpartida = ?, \n");
			sql.append("    flempate = ?, \n");
			sql.append("    nugolvencedor = ?, \n");
			sql.append("    nugolperdedor = ?, \n");
			sql.append("    nmjuiz = ?, \n");
			sql.append("    debolamurcha = ?, \n");
			sql.append("    debolacheia = ?, \n");
			sql.append("    deobservacao = ?, \n");
			sql.append("    flconcluida = ? \n");
			sql.append("where cdpartida = " + partida.getPartidaPK().getCdPartida());			
			pstmt = con.prepareStatement( sql.toString() );
			int posi = 1;
			pstmt.setInt(posi++, partida.getNuAno());			
			pstmt.setInt(posi++, cdQuadrimestre);
			pstmt.setInt(posi++, partida.getCdTimeperdedor());
			pstmt.setInt(posi++, partida.getCdTimevencedor());
			pstmt.setDate(posi++, new java.sql.Date(partida.getDtPartida().getTime()));
			pstmt.setInt(posi++, partida.getFlEmpate());
			pstmt.setInt(posi++, partida.getNuGolvencedor());
			pstmt.setInt(posi++, partida.getNuGolperdedor());
			pstmt.setString(posi++, partida.getNmJuiz());
			pstmt.setString(posi++, partida.getDeBolamurcha() );
			pstmt.setString(posi++, partida.getDeBolacheia() );
			pstmt.setString(posi++, partida.getDeObservacao() );
			pstmt.setInt(posi++, partida.getFlConcluida() );
			pstmt.executeUpdate();
			log.debug("Registro atualizado com sucesso na epfcpartida.");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao atualizar o registro na epfcpartida.", e);
		} finally {
			try { RdbUtil.close(pstmt, con); } catch (SQLException e) {}
		}	
	}

	public List<Partida> findByAnoQuadrimestre(String ano, String quadrimestre)
			throws DAOException {
		List<Partida> lista = new ArrayList<Partida>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" select p.* \n");
			sql.append(" from epfcpartida p \n");
			sql.append(" join epfcquadrimestre q on ");
			sql.append(" 	q.cdQuadrimestre = p.cdQuadrimestre ");
			sql.append(" and q.nuAno = p.nuAno ");
			sql.append(" where q.nuAno = " + ano);
			if (quadrimestre != null && quadrimestre != "") {
				sql.append("\n and p.cdquadrimestre = " + quadrimestre);
			}
			sql.append("\n order by p.dtpartida desc, p.cdPartida desc \n");
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
	
	public static Partida popule(ResultSet rs) {
		Partida partida = new Partida();
		try { partida.getPartidaPK().setCdPartida( rs.getInt("cdPartida") ); } catch (Exception e) {};
		try { partida.setNuAno( rs.getInt("nuAno") ); } catch (Exception e) {}
		try { partida.setCdQuadrimestre( rs.getInt("cdQuadrimestre") ); } catch (Exception e) {}
		try { partida.setCdTimeperdedor( rs.getInt("cdTimeperdedor") ); } catch (Exception e) {}
		try { partida.setCdTimevencedor( rs.getInt("cdTimevencedor") ); } catch (Exception e) {}
		try { partida.setDtPartida( rs.getDate("dtPartida") ); } catch (Exception e) {}
		try { partida.setFlEmpate( rs.getInt("flEmpate") ); } catch (Exception e) {}
		try { partida.setNuGolvencedor( rs.getInt("nuGolvencedor") ); } catch (Exception e) {}
		try { partida.setNuGolperdedor( rs.getInt("nuGolperdedor") ); } catch (Exception e) {}
		try { partida.setNmJuiz( rs.getString("nmJuiz") ); } catch (Exception e) {}
		try { partida.setDeBolamurcha( rs.getString("deBolamurcha") ); } catch (Exception e) {}
		try { partida.setDeBolacheia( rs.getString("deBolacheia") ); } catch (Exception e) {}
		try { partida.setDeObservacao( rs.getString("deObservacao") ); } catch (Exception e) {}
		try { partida.setFlConcluida( rs.getInt("flConcluida") ); } catch (Exception e) {}
		return partida;
	}

	public List<Codigodescricao> findAnos() throws DAOException {
		List<Codigodescricao>  lista = new ArrayList<Codigodescricao> ();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			//--sql.append(" Select distinct EXTRACT(year from P.dtpartida) as nuAno from epfcpartida P ");
			sql.append(" Select distinct p.nuAno from epfcpartida p ");
			//--sql.append(" join epfcquadrimestre q on ");
			//sql.append(" 	q.cdQuadrimestre = p.cdQuadrimestre ");
			sql.append(" order by 1 desc ");
			pstmt = con.prepareStatement( sql.toString() );
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Codigodescricao c = new Codigodescricao();
				c.setCdCodigo( rs.getInt("nuAno") );
				c.setDeDescricao( String.valueOf( rs.getInt("nuAno") ) );
				lista.add( c );
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return lista;
	}
	
	@SuppressWarnings({ "static-access", "unused" })
	private Integer geraCodigo(Object obj) {
		Partida partida = (Partida) obj;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer codigoNovo = 0;
		Integer codigoAtual = 0;
		Integer codigoSequence = 0;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" select max(cdPartida) as codigo from epfcpartida ");
			pstmt = con.prepareStatement( sql.toString() );
			rs = pstmt.executeQuery( sql.toString() );
			if (rs.next()) {
				codigoAtual =  rs.getInt("codigo");
			}
			
			sql = new StringBuilder();
			sql.append("SELECT GEN_ID(" + partida.SEQ_GERA_CODIGO + ",0) FROM RDB$DATABASE");
			rs = pstmt.executeQuery( sql.toString() );
			if (rs.next()) {
				codigoSequence = rs.getInt(1); 
			}
			if (codigoAtual > codigoSequence) {
				sql = new StringBuilder();
				sql.append("ALTER SEQUENCE " + partida.SEQ_GERA_CODIGO + " RESTART WITH " + codigoAtual);
				pstmt.executeUpdate(sql.toString());				
			}
			
			sql = new StringBuilder();
			sql.append("SELECT GEN_ID(" + partida.SEQ_GERA_CODIGO + ",1) FROM RDB$DATABASE");
			rs = pstmt.executeQuery( sql.toString() );
			if (rs.next()) {
				codigoNovo = rs.getInt(1); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return codigoNovo;
	}
	
	private int pegaQuadrimestre(Date data) throws Exception {
		return Util.getQuadrimestreAtual_Integer(new java.sql.Date(data.getTime()), false);		
	}
	
	private int pegaAnoQuadrimestre(Date data) throws Exception {
		QuadrimestreDAO dao = DAOFactory.getQuadrimestreDAO();
		Quadrimestre quadrimestre = (Quadrimestre) dao.findQuadrimestrePorData( data, null);
		if (quadrimestre.getQuadrimestrePK().getNuAno() == null) {
			throw new Exception("Não existe quadrimestre cadastrado!");
		}
		return quadrimestre.getQuadrimestrePK().getNuAno();
	}
	
	public Object findUltimapartida() {
		Partida partida = new Partida();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" select p.* \n");
			sql.append(" from epfcpartida p \n");
			sql.append(" where p.cdpartida = (Select max(x.cdpartida) from epfcpartida x where x.flconcluida = 1) ");
			pstmt = con.prepareStatement( sql.toString() );
			rs = pstmt.executeQuery();
			if (rs.next()) {
				partida = popule(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return partida;
	}
	
	public Object findPartida(Integer cdPartida) {
		Partida partida = new Partida();
		partida.getPartidaPK().setCdPartida( cdPartida );
		return findByPrimaryKey( partida );
	}
	
	public Integer findTotalPartidas(Integer nuAno, Integer cdQuadrimestre) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer nuTotal = 0;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" select count(*) as total \n");
			sql.append(" from epfcpartida p \n");
			sql.append(" where 1 = 1 ");
			if ((nuAno != null) && (nuAno > 0)) {
				sql.append(" and p.nuAno = " + nuAno );	
			}
			if ((cdQuadrimestre != null) && (cdQuadrimestre > 0)) {
				sql.append(" and p.cdQuadrimestre = " + cdQuadrimestre );
			}
			pstmt = con.prepareStatement( sql.toString() );
			rs = pstmt.executeQuery();
			if (rs.next()) {
				nuTotal = rs.getInt("total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return nuTotal;
	}
	
	public Boolean partidaAnteriorEncerrada(Integer cdPartida) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" select count(*) as total from epfcpartida x  \n");
			sql.append(" where x.flconcluida <> 1 ");
			sql.append(" 	and x.cdPartida < " + cdPartida + "");			 	
			pstmt = con.prepareStatement( sql.toString() );
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return (rs.getInt("total") == 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return true;		
	}
	
	public Boolean partidaPosteriorEncerrada(Integer cdPartida) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" select count(*) as total from epfcpartida x  \n");
			sql.append(" where x.flconcluida = 1 ");
			sql.append(" 	and x.cdPartida > " + cdPartida + "");			 	
			pstmt = con.prepareStatement( sql.toString() );
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return (rs.getInt("total") == 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return true;		
	}
	
	public Partida getPartidaAnterior(Integer cdPartidaAtual) {
		Partida partida = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" select * from epfcpartida  \n");
			sql.append(" where cdpartida = (Select max(y.cdpartida) from epfcpartida y \n");
			sql.append(" 	where y.cdPartida < " + cdPartidaAtual + ") ");
			
			pstmt = con.prepareStatement( sql.toString() );
			rs = pstmt.executeQuery();
			if (rs.next()) {
				partida = popule(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return partida;
	}
 
	public Boolean isUltimapartidaencerrada() {
		Partida partida = this.getUltimapartida();
		return (partida.getFlConcluida().intValue()==1);
	}
	
	public Partida getUltimapartida() {
		Partida partida = new Partida();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" select p.* \n");
			sql.append(" from epfcpartida p \n");
			sql.append(" where p.cdpartida = (Select max(x.cdpartida) from epfcpartida x) ");
			pstmt = con.prepareStatement( sql.toString() );
			rs = pstmt.executeQuery();
			if (rs.next()) {
				partida = popule(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return partida;
	}
	
	public Partida findPartidaanterior(Integer cdPartida) {
		Partida partida = new Partida();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT y.* FROM epfcpartida y \n");
			sql.append(" WHERE y.cdPartida = (SELECT max(x.cdPartida) FROM epfcpartida x \n");
			sql.append(" 					  WHERE x.cdPartida < ?) \n");
			pstmt = con.prepareStatement( sql.toString() );
			pstmt.setInt(1, cdPartida);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				partida = popule(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return partida;
	}
	
	public void encerrarPartida(Partida partida) {
		Connection con = ServiceLocator.getConexao();
		try {
			con.setAutoCommit(false);
			//-- Gera o Ranking para a Partida do Quadrimestre Atual
			DAOFactory.getClassificacaoDAO().encerrarPartidaQuadrimestreAtual( partida );
			
			//-- Gera o Ranking para a Partida do Quadrimestre Anual
			DAOFactory.getClassificacaoDAO().encerrarPartidaAnual( partida );
						
			//-- Encerra a partida		
			partida.setFlConcluida( 1 );
			this.update( partida );			
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new DAOException(e.getMessage());
		} finally {
			try { 
				RdbUtil.close(null, null, con); 
				con.setAutoCommit(true);
			} catch (SQLException e) {}
		}		

	}
	
}
