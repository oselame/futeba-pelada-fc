package br.com.softal.pfc.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.softal.pfc.Galeria;
import br.com.softal.pfc.DAO.DAOException;
import br.com.softal.pfc.DAO.GaleriaDAO;
import br.com.softal.pfc.DAO.ServiceLocator;
import br.com.softal.pfc.util.RdbUtil;
 
public class RdbGaleriaDAO implements GaleriaDAO {

	private static Log log = LogFactory.getLog(RdbGaleriaDAO.class);
	
	public void delete(Object obj) throws DAOException {
		Galeria galeria = (Galeria) obj;
		Connection con = null;
		PreparedStatement pstmt = null;
		con = ServiceLocator.getConexao();
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" delete from epfcgaleria \n");
			sql.append(" where cdgaleria = ? ");
			pstmt = con.prepareStatement( sql.toString() );
			pstmt.setInt(1, galeria.getGaleriaPK().getCdGaleria());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		} finally {
			try { RdbUtil.close(pstmt, con); } catch (SQLException e) {}
		}
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
			//--sql.append(" select * from epfcgaleria \");
			sql.append(" SELECT g.cdGaleria, g.deGaleria, g.dtGaleria, count(fg.cdfotogaleria) as nuFotos FROM epfcgaleria g \n ");
			sql.append(" left join epfcfotogaleria fg on \n");
			sql.append(" g.cdgaleria = fg.cdgaleria \n");
			sql.append(" group by g.cdGaleria \n");
			sql.append(" order by dtGaleria desc ");
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
		return null;
	}

	public Object findByPrimaryKey(Object obj) throws DAOException {
		Galeria galeria = (Galeria) obj;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" select * from epfcgaleria ");
			sql.append(" where cdgaleria = " + galeria.getGaleriaPK().getCdGaleria());
			pstmt = con.prepareStatement( sql.toString() );
			rs = pstmt.executeQuery();
			if (rs.next()) {
				galeria = popule(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return galeria;
	}
	
	public void insert(Object obj) throws DAOException {
		Galeria galeria = (Galeria) obj;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" insert into epfcgaleria (cdgaleria, degaleria, dtgaleria)  ");
			sql.append(" values (?, ?, ?)  ");
			pstmt = con.prepareStatement( sql.toString() );
			int posi = 1;
			pstmt.setNull( posi++, Types.NULL);
			pstmt.setString( posi++, galeria.getDeGaleria() );
			pstmt.setDate( posi++, new java.sql.Date(galeria.getDtGaleria().getTime()) );
			pstmt.executeUpdate();
			galeria.getGaleriaPK().setCdGaleria( getCodigoGerado(con) );
			log.debug("Registro inserido com sucesso na epfcgaleria.");			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao inserir o registro na na epfcgaleria.", e);
		} finally {
			try { RdbUtil.close(pstmt, con); } catch (SQLException e) {}
		}	
	}
	
	private Integer getCodigoGerado(Connection con) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer codigo = 1;
		try {
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
		Galeria galeria = (Galeria) obj;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {			
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" update epfcgaleria ");
			sql.append(" set ");
			sql.append(" 	 degaleria = ?, ");
			sql.append("     dtgaleria = ?");			
			sql.append(" where cdgaleria = " + galeria.getGaleriaPK().getCdGaleria());		
			pstmt = con.prepareStatement( sql.toString() );
			int posi = 1;
			pstmt.setString(posi++, galeria.getDeGaleria());
			if (galeria.getDtGaleria() == null) {
				pstmt.setNull(posi++, Types.NULL);
			} else {
				pstmt.setDate(posi++, new java.sql.Date(galeria.getDtGaleria().getTime()));
			}
			pstmt.executeUpdate();
			log.debug("Registro atualizado com sucesso na epfcgaleria.");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao atualizar o registro na epfcgaleria.", e);
			throw new DAOException("Erro ao atualizar o galeria! \n" + e.getMessage());
		} finally {
			try { RdbUtil.close(pstmt, con); } catch (SQLException e) {}
		}	
	}
	
	public static Galeria popule(ResultSet rs) {
		Galeria galeria = new Galeria();
		try { galeria.getGaleriaPK().setCdGaleria( rs.getInt("cdGaleria") ); } catch (Exception e) {}
		try { galeria.setDeGaleria( rs.getString("deGaleria") ); } catch (Exception e) {}
		try { galeria.setDtGaleria( rs.getDate("dtGaleria") ); } catch (Exception e) {}
		try { galeria.setNuFotos( rs.getInt("nuFotos") ); } catch (Exception e) {}
		return galeria;
	}
	
	/*
	@SuppressWarnings({ "static-access", "unused" })
	private Integer geraCodigo(Object obj) {
		Socio socio = (Socio) obj;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer codigoNovo = 0;
		Integer codigoAtual = 0;
		Integer codigoSequence = 0;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" select max(cdSocio) as codigo from epfcsocio ");
			pstmt = con.prepareStatement( sql.toString() );
			rs = pstmt.executeQuery( sql.toString() );
			if (rs.next()) {
				codigoAtual =  rs.getInt("codigo");
			}
			sql = new StringBuilder();
			sql.append("SELECT GEN_ID(" + socio.SEQ_GERA_CODIGO + ",0) FROM RDB$DATABASE");
			rs = pstmt.executeQuery( sql.toString() );
			if (rs.next()) {
				codigoSequence = rs.getInt(1); 
			}
			if (codigoAtual > codigoSequence) {
				sql = new StringBuilder();
				sql.append("ALTER SEQUENCE " + socio.SEQ_GERA_CODIGO + " RESTART WITH " + codigoAtual);
				pstmt.executeUpdate(sql.toString());				
			}
			sql = new StringBuilder();
			sql.append("SELECT GEN_ID(" + socio.SEQ_GERA_CODIGO + ",1) FROM RDB$DATABASE");
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
    */
}
