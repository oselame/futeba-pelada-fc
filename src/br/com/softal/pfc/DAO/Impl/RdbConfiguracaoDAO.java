package br.com.softal.pfc.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.softal.pfc.Configuracao;
import br.com.softal.pfc.ConfiguracaoPK;
import br.com.softal.pfc.DAO.ConfiguracaoDAO;
import br.com.softal.pfc.DAO.DAOException;
import br.com.softal.pfc.DAO.ServiceLocator;
import br.com.softal.pfc.util.RdbUtil;
 
public class RdbConfiguracaoDAO implements ConfiguracaoDAO {

	private static Log log = LogFactory.getLog(RdbConfiguracaoDAO.class);
	
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
		ConfiguracaoPK configuracaoPK = (ConfiguracaoPK) obj;
		Configuracao configuracao = new Configuracao();
		configuracao.setConfiguracaoPK( configuracaoPK );
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" select * from epfcconfiguracao ");
			sql.append(" where cdConfiguracao = " + configuracaoPK.getCdConfiguracao() );
			pstmt = con.prepareStatement( sql.toString() );
			rs = pstmt.executeQuery();
			if (rs.next()) {
				configuracao = popule(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return configuracao;
	}

	public void insert(Object obj) throws DAOException {
		Configuracao configuracao = (Configuracao) obj;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" insert into epfcconfiguracao (cdconfiguracao, vlconfiguracao)  ");
			sql.append(" values (?, ?)  ");
			pstmt = con.prepareStatement( sql.toString() );
			pstmt.setInt(1, configuracao.getConfiguracaoPK().getCdConfiguracao() );
			pstmt.setString(2, configuracao.getVlConfiguracao());
			pstmt.executeUpdate();
			log.debug("Registro inserido com sucesso na epfcConfiguracao.");			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao inserir o registro na na epfcConfiguracao.", e);
		} finally {
			try { RdbUtil.close(pstmt, con); } catch (SQLException e) {}
		}	
	}

	public void update(Object obj) throws DAOException {
		Configuracao configuracao = (Configuracao) obj;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" update epfcconfiguracao set  ");
			sql.append(" vlconfiguracao = ?  ");
			sql.append(" where cdconfiguracao = ?");
			pstmt = con.prepareStatement( sql.toString() );
			pstmt.setString(1, configuracao.getVlConfiguracao());
			pstmt.setInt(2, configuracao.getConfiguracaoPK().getCdConfiguracao());
			pstmt.executeUpdate();
			log.debug("Registro atualizado com sucesso na epfcConfiguracao.");			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao atualizado o registro na epfcConfiguracao.", e);
		} finally {
			try { RdbUtil.close(pstmt, con); } catch (SQLException e) {}
		}	
	}
	
	public static Configuracao popule(ResultSet rs) {
		Configuracao configuracao = new Configuracao();
		try { configuracao.getConfiguracaoPK().setCdConfiguracao( rs.getInt("cdConfiguracao") ); } catch (Exception e) {}
		try { configuracao.setVlConfiguracao( rs.getString("vlConfiguracao") ); } catch (Exception e) {}
		return configuracao;
	}
 
}
