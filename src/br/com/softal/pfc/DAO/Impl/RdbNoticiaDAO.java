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

import br.com.softal.pfc.Constantes;
import br.com.softal.pfc.Noticia;
import br.com.softal.pfc.NoticiaPK;
import br.com.softal.pfc.DAO.DAOException;
import br.com.softal.pfc.DAO.NoticiaDAO;
import br.com.softal.pfc.DAO.ServiceLocator;
import br.com.softal.pfc.util.RdbUtil;
 
public class RdbNoticiaDAO implements NoticiaDAO {

	private static Log log = LogFactory.getLog(RdbSocioDAO.class);
	
	public void delete(Object obj) throws DAOException {
		Noticia noticia = (Noticia) obj;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" delete from epfcnoticia ");
			sql.append(" where cdNoticia = ? ");
			pstmt = con.prepareStatement( sql.toString() );
			pstmt.setInt(1, noticia.getNoticiaPK().getCdNoticia());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(pstmt, con); } catch (SQLException e) {}
		}
	}

	public List<Noticia> findAll() throws DAOException {
		List<Noticia> lista = new ArrayList<Noticia>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" select * from epfcnoticia ");
			sql.append(" order by dtNoticia desc ");
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

	public List<Noticia> findAll(Object obj) throws DAOException {
		Noticia noticia = (Noticia) obj;
		List<Noticia> lista = new ArrayList<Noticia>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" select * from epfcnoticia ");
			if (noticia.getTpNoticia() != null) {
				sql.append(" where tpnoticia = " + noticia.getTpNoticia());
			}
			sql.append(" order by dtNoticia desc");
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
		NoticiaPK pk = (NoticiaPK) obj;
		Noticia noticia = new Noticia();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" select * from epfcnoticia ");
			sql.append(" where cdnoticia = " + pk.getCdNoticia() );
			pstmt = con.prepareStatement( sql.toString() );
			rs = pstmt.executeQuery();
			if (rs.next()) {
				noticia = popule(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return noticia;
	}

	public void insert(Object obj) throws DAOException {
		Noticia noticia = (Noticia) obj;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" insert into epfcnoticia (cdnoticia, detitulo, denoticia, dtnoticia, delink, tpNoticia, dtFimevento, flpopup)  ");
			sql.append(" values (?, ?, ?, ?, ?, ?, ?, ?)  ");
			pstmt = con.prepareStatement( sql.toString() );
			int posi = 1;
			pstmt.setNull(posi++, Types.NULL);
			pstmt.setString(posi++, noticia.getDeTitulo());
			pstmt.setString(posi++, noticia.getDeNoticia()); 
			pstmt.setDate(posi++, new java.sql.Date(noticia.getDtNoticia().getTime()));
			pstmt.setString(posi++, noticia.getDeLink());
			pstmt.setInt(posi++, noticia.getTpNoticia() == null ? 0 : noticia.getTpNoticia());
			if (noticia.getDtFimevento() == null) {
				pstmt.setNull(posi++, Types.NULL);
			} else {
				pstmt.setDate(posi++, new java.sql.Date(noticia.getDtFimevento().getTime()));
			}
			pstmt.setInt(posi++, noticia.getFlPopup());
			pstmt.executeUpdate();
			noticia.getNoticiaPK().setCdNoticia( getCodigoGerado( con ) );
			log.debug("Registro inserido com sucesso na epfcnoticia.");			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao inserir o registro na epfcnoticia.", e);
		} finally {
			try { RdbUtil.close(pstmt, con); } catch (SQLException e) {}
		}	
	}

	public void update(Object obj) throws DAOException {
		Noticia noticia = (Noticia) obj;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {			
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" update epfcnoticia ");
			sql.append(" set ");
			sql.append(" 	 detitulo = ?, ");
			sql.append("     denoticia = ?, ");
			sql.append("     dtnoticia = ?, ");
			sql.append("     delink = ?, ");
			sql.append("     tpnoticia = ?, ");
			sql.append("     dtFimevento = ?, ");
			sql.append("     flpopup = ? ");
			sql.append(" where cdnoticia = " + noticia.getNoticiaPK().getCdNoticia());		
			pstmt = con.prepareStatement( sql.toString() );
			int posi = 1;
			pstmt.setString(posi++, noticia.getDeTitulo());
			pstmt.setString(posi++, noticia.getDeNoticia()); 
			if (noticia.getDtNoticia() == null) {
				pstmt.setNull(posi++, Types.NULL);
			} else {
				pstmt.setDate(posi++, new java.sql.Date(noticia.getDtNoticia().getTime()));
			}
			pstmt.setString(posi++, noticia.getDeLink());
			pstmt.setInt(posi++, noticia.getTpNoticia() == null ? 0 : noticia.getTpNoticia());
			if (noticia.getDtFimevento() == null) {
				pstmt.setNull(posi++, Types.NULL);
			} else {
				pstmt.setDate(posi++, new java.sql.Date(noticia.getDtFimevento().getTime()));
			}			
			pstmt.setInt(posi++, noticia.getFlPopup());
			pstmt.executeUpdate();
			log.debug("Registro atualizado com sucesso na epfcnoticia.");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao atualizar o registro na epfcnoticia.", e);
			throw new DAOException("Erro ao atualizar o noticia! \n" + e.getMessage());
		} finally {
			try { RdbUtil.close(pstmt, con); } catch (SQLException e) {}
		}	
	}

	public List<Noticia> findAllNoticiasEventosatuais(Integer tipo) throws DAOException {
		List<Noticia> lista = new ArrayList<Noticia>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int nuNoticias = 0;
		int nuTTNoticias = 9999;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" select * from epfcnoticia ");
			if (tipo != null) {
				sql.append(" where tpnoticia = " + tipo);
				if (tipo == Noticia.TIPO_EVENTO) {
					sql.append(" and dtFimevento >= CURDATE() ");
					sql.append(" order by dtNoticia desc, deNoticia asc ");
				} else {
					sql.append(" order by dtNoticia desc, deNoticia asc ");
					nuTTNoticias = Constantes.QTDE_EVENTOS;
				}
			} else {
				sql.append(" order by dtNoticia desc, deNoticia asc ");
			}
			
			pstmt = con.prepareStatement( sql.toString() );
			rs = pstmt.executeQuery();
			while (rs.next() && nuNoticias < nuTTNoticias) {
				lista.add( popule(rs) );
				nuNoticias++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return lista;
	}
	
	public static Noticia popule(ResultSet rs) {
		Noticia noticia = new Noticia();
		try { noticia.getNoticiaPK().setCdNoticia( rs.getInt("cdNoticia") ); } catch (Exception e) {}
		try { noticia.setDeTitulo( rs.getString("deTitulo") ); } catch (Exception e) {}
		try { noticia.setDtNoticia( rs.getDate("dtNoticia") ); } catch (Exception e) {}		
		try { noticia.setDeNoticia( rs.getString("deNoticia") ); } catch (Exception e) {}
		try { noticia.setDeLink( rs.getString("deLink") ); } catch (Exception e) {}
		try { noticia.setTpNoticia( rs.getInt("tpNoticia") ); } catch (Exception e) {}
		try { noticia.setDtFimevento( rs.getDate("dtFimevento") ); } catch (Exception e) {}
		try { noticia.setFlPopup( rs.getInt("flpopup") ); } catch (Exception e) {}
		return noticia;
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
	
	public void removeFlagPopupOutrasNoticias(Noticia noticia) throws DAOException {
		if (noticia.getFlPopup() == 1) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {			
				con = ServiceLocator.getConexao();
				StringBuilder sql = new StringBuilder();
				sql.append(" update epfcnoticia 			");
				sql.append(" set 							");
				sql.append("     flpopup = 0 				");
				sql.append(" where cdnoticia <> ? 			");	
				sql.append(" and tpnoticia = ? 				");	
				
				pstmt = con.prepareStatement( sql.toString() );
				int posi = 1;
				pstmt.setInt(posi++,  noticia.getNoticiaPK().getCdNoticia());
				pstmt.setInt(posi++,  noticia.getTpNoticia());
				pstmt.executeUpdate();
				log.debug("Atualizacao da flPopup nas noticias realizado com sucesso.");
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Erro na atualizacao da flPopup nas noticias realizado com sucesso.", e);
				throw new DAOException("Erro ao atualizar o noticia! \n" + e.getMessage());
			} finally {
				try { RdbUtil.close(pstmt, con); } catch (SQLException e) {}
			}	
		}
	}
	
 
}
