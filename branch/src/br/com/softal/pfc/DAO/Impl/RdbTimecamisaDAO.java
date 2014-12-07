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

import br.com.softal.pfc.Socio;
import br.com.softal.pfc.Timecamisa;
import br.com.softal.pfc.DAO.DAOException;
import br.com.softal.pfc.DAO.ServiceLocator;
import br.com.softal.pfc.DAO.TimecamisaDAO;
import br.com.softal.pfc.util.RdbUtil;
 
public class RdbTimecamisaDAO implements TimecamisaDAO {
	
	private static Log log = LogFactory.getLog(RdbTimecamisaDAO.class);

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
			sql.append(" select * from epfctimecamisa \n");
			sql.append(" where flForauso is null or flForauso <> 1 ");
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
		Timecamisa timecamisa = (Timecamisa) obj;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" select * from epfctimecamisa \n");
			sql.append(" where cdTime = " + timecamisa.getTimecamisaPK().getCdTime());
			pstmt = con.prepareStatement( sql.toString() );
			rs = pstmt.executeQuery();
			if (rs.next()) {
				timecamisa = popule(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return timecamisa;
	}

	public void insert(Object obj) throws DAOException {
	}

	public void update(Object obj) throws DAOException {
		Timecamisa timecamisa = (Timecamisa) obj;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {			
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" update epfctimecamisa ");
			sql.append(" set ");
			sql.append(" 	 nmTime = ?, ");
			sql.append("     flForauso = ? ");
			sql.append(" where cdtime = " + timecamisa.getTimecamisaPK().getCdTime());		
			pstmt = con.prepareStatement( sql.toString() );
			int posi = 1;
			pstmt.setString(posi++, timecamisa.getNmTime());
			pstmt.setInt(posi++, timecamisa.getFlForauso()); 
			pstmt.executeUpdate();
			log.debug("Registro atualizado com sucesso na epfctimecamisa.");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao atualizar o registro na epfctimecamisa.", e);
			throw new DAOException("Erro ao atualizar o socio! \n" + e.getMessage());
		} finally {
			try { RdbUtil.close(pstmt, con); } catch (SQLException e) {}
		}		
	}
	
	public static Timecamisa popule(ResultSet rs) {
		Timecamisa timecamisa = new Timecamisa();
		try { timecamisa.getTimecamisaPK().setCdTime( rs.getInt("cdTime") ); } catch (Exception e) {}
		try { timecamisa.setNmTime( rs.getString("nmTime") ); } catch (Exception e) {}
		try { timecamisa.setFlForauso( rs.getInt("flForauso") ); } catch (Exception e) {}
		return timecamisa;
	}
	
	public Timecamisa findTimecamisa(Integer cdTimecamisa) throws DAOException {
		Timecamisa timecamisa = new Timecamisa();
		timecamisa.getTimecamisaPK().setCdTime( cdTimecamisa );
		return (Timecamisa) findByPrimaryKey( timecamisa );
	}
}
