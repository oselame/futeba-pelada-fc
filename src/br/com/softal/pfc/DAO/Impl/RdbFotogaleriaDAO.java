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

import br.com.softal.pfc.Fotogaleria;
import br.com.softal.pfc.Galeria;
import br.com.softal.pfc.DAO.DAOException;
import br.com.softal.pfc.DAO.FotogaleriaDAO;
import br.com.softal.pfc.DAO.ServiceLocator;
import br.com.softal.pfc.util.RdbUtil;
 
public class RdbFotogaleriaDAO implements FotogaleriaDAO {

	private static Log log = LogFactory.getLog(RdbFotogaleriaDAO.class);
	
	public void delete(Object obj) throws DAOException {
		Fotogaleria fotogaleria = (Fotogaleria) obj;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" Delete from epfcfotogaleria \n");
			sql.append(" where cdFotogaleria = ? ");
			pstmt = con.prepareStatement( sql.toString() );
			pstmt.setInt(1, fotogaleria.getFotogaleriaPK().getCdFotogaleria() );
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao excluir o registro na na epfcfotogaleria.", e);
		} finally {
			try { RdbUtil.close(pstmt, con); } catch (SQLException e) {}
		}	
	
	}
	
	public void deleteAllFotogaleria(Galeria galeria) throws DAOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" Delete from epfcfotogaleria \n");
			sql.append(" where cdgaleria = ? ");
			pstmt = con.prepareStatement( sql.toString() );
			pstmt.setInt(1, galeria.getGaleriaPK().getCdGaleria() );
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao excluir os registros da epfcfotogaleria.", e);
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
			sql.append(" select * from epfcfotogaleria ");
			sql.append(" order by cdgaleria desc ");
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
		Fotogaleria fotogaleria = (Fotogaleria) obj;
		List lista = new ArrayList();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" select * from epfcfotogaleria ");
			sql.append(" where cdgaleria = " + fotogaleria.getCdGaleria());
			sql.append(" order by cdFotogaleria asc ");
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
		Fotogaleria fotogaleria = (Fotogaleria) obj;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" select * from epfcfotogaleria ");
			sql.append(" where cdfotogaleria = " + fotogaleria.getFotogaleriaPK().getCdFotogaleria());
			pstmt = con.prepareStatement( sql.toString() );
			rs = pstmt.executeQuery();
			if (rs.next()) {
				fotogaleria = popule(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return fotogaleria;
	}
	
	public void insert(Object obj) throws DAOException {
		Fotogaleria fotogaleria = (Fotogaleria) obj;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			
			//-- Verifica se o arquivo ja existe
			StringBuilder sql = new StringBuilder();
			sql.append(" Select * from epfcfotogaleria \n");
			sql.append(" where nmarqfoto = ? \n");
			sql.append(" and cdgaleria = ? ");
			pstmt = con.prepareStatement( sql.toString() );
			pstmt.setString(1, fotogaleria.getNmArqfoto());
			pstmt.setInt(2, fotogaleria.getCdGaleria());
			rs = pstmt.executeQuery();
			
			//-- Insere se nao existe
			if (!rs.next()) {
				sql = new StringBuilder();
				sql.append(" insert into epfcfotogaleria (cdfotogaleria, cdgaleria, nmarqfoto)  ");
				sql.append(" values (?, ?, ?)  ");
				pstmt = con.prepareStatement( sql.toString() );
				int posi = 1;
				pstmt.setNull(posi++, Types.NULL);
				pstmt.setInt(posi++, fotogaleria.getCdGaleria());
				pstmt.setString(posi++, fotogaleria.getNmArqfoto());
				pstmt.executeUpdate();
				fotogaleria.getFotogaleriaPK().setCdFotogaleria( getCodigoGerado(con) );
				log.debug("Registro inserido com sucesso na epfcfotogaleria.");			
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao inserir o registro na na epfcfotogaleria.", e);
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
		Fotogaleria fotogaleria = (Fotogaleria) obj;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {			
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" update epfcfotogaleria ");
			sql.append(" set ");
			sql.append(" 	 cdgaleria = ?, "); 
			sql.append("     nmarqfoto = ?");			
			sql.append(" where cdfotogaleria = " + fotogaleria.getFotogaleriaPK().getCdFotogaleria());		
			pstmt = con.prepareStatement( sql.toString() );
			int posi = 1;
			pstmt.setInt(posi++, fotogaleria.getCdGaleria());
			pstmt.setString(posi++, fotogaleria.getNmArqfoto());
			pstmt.executeUpdate();
			log.debug("Registro atualizado com sucesso na epfcfotogaleria.");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao atualizar o registro na epfcfotogaleria.", e);
			throw new DAOException("Erro ao atualizar o galeria! \n" + e.getMessage());
		} finally {
			try { RdbUtil.close(pstmt, con); } catch (SQLException e) {}
		}	
	}
	
	public static Fotogaleria popule(ResultSet rs) {
		Fotogaleria fotogaleria = new Fotogaleria();
		try { fotogaleria.getFotogaleriaPK().setCdFotogaleria( rs.getInt("cdFotogaleria") ); } catch (Exception e) {}
		try { fotogaleria.setCdGaleria( rs.getInt("cdGaleria") ); } catch (Exception e) {}
		try { fotogaleria.setNmArqfoto( rs.getString("nmArqfoto") ); } catch (Exception e) {}
		return fotogaleria;
	}
	
}
