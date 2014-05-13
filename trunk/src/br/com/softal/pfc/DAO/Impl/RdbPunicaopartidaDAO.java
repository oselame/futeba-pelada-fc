package br.com.softal.pfc.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.softal.pfc.Punicaopartida;
import br.com.softal.pfc.DAO.DAOException;
import br.com.softal.pfc.DAO.PunicaopartidaDAO;
import br.com.softal.pfc.DAO.ServiceLocator;
import br.com.softal.pfc.util.RdbUtil;
 
public class RdbPunicaopartidaDAO implements PunicaopartidaDAO {

	private static Log log = LogFactory.getLog(RdbPunicaopartidaDAO.class);

	public void delete(Object obj) throws DAOException {
	}

	public List<?> findAll() throws DAOException {
		return null;
	}

	public List<?> findAll(Object obj) throws DAOException {
		return null;
	}

	public Object findByPrimaryKey(Object obj) throws DAOException {
		return null;
	}

	public void insert(Object obj) throws DAOException {
		Punicaopartida pp = (Punicaopartida) obj;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" insert into epfcpunicaopartida (cdSocio, cdPartida, nuPontospunicao, dePunicao)  ");
			sql.append(" values (?, ?, ?, ?)  ");
			pstmt = con.prepareStatement( sql.toString() );
			int posi = 1;
			pstmt.setInt(posi++, pp.getPunicaopartidaPK().getCdSocio());
			pstmt.setInt(posi++, pp.getPunicaopartidaPK().getCdPartida());
			pstmt.setInt(posi++, pp.getNuPontospunicao() == null ? 0 : pp.getNuPontospunicao());
			pstmt.setString(posi++, pp.getDePunicao());
			pstmt.executeUpdate();
			log.debug("Registro inserido com sucesso na epfcpunicaopartida." );
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao inserir o registro na epfcpunicaopartida." );
		} finally {
			try { RdbUtil.close(pstmt, con); } catch (SQLException e) {}
		}	
	}

	public void update(Object obj) throws DAOException {
	}
	
	private void removeTodasAsPunicoesDaPartida(Integer cdPartida) throws DAOException {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from epfcpunicaopartida where cdPartida = ?  \n");
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ServiceLocator.getConexao();
			pstmt = con.prepareStatement( sql.toString() );
			pstmt.setInt(1, cdPartida);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(pstmt, con); } catch (SQLException e) {}
		}
	}
	
	public Map<Integer, Punicaopartida> findAllMapJogodoresPartida(Integer cdPartida) throws DAOException {
		Map<Integer, Punicaopartida> map = new HashMap<Integer, Punicaopartida>();
		List<Punicaopartida> participantes = this.findAllJogodoresPartida(cdPartida);
		for (Punicaopartida pp : participantes) {
			map.put(pp.getPunicaopartidaPK().getCdSocio(), pp);
		}
		return map;
	}
	
	public List<Punicaopartida> findAllJogodoresPartida(Integer cdPartida) throws DAOException {
		List<Punicaopartida> lista = new ArrayList<Punicaopartida>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			
			sql.append("select S.cdSocio,                                 \n");
			sql.append("       S.nmApelido,                               \n");
			sql.append("       P.cdPartida,                               \n");
			sql.append("       pp.nuPontospunicao,                        \n");
			sql.append("       pp.dePunicao                               \n");
			sql.append("from epfcsocio S                                  \n");
			sql.append("left join epfcpunicaopartida pp on                \n");
			sql.append("     pp.cdsocio = s.cdsocio                       \n");
			sql.append("left join epfcPartida P on                        \n");
			sql.append("     P.cdPartida = PP.cdPartida                   \n");
			sql.append("     and P.cdpartida = ?                          \n");
			sql.append("where (S.flForauso is null or S.flForauso = 0)    \n");
			sql.append("order by S.nmApelido                              \n");
		
			pstmt = con.prepareStatement( sql.toString() );
			pstmt.setInt(1, cdPartida);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Punicaopartida sociopartida = Punicaopartida.popule(rs);
				sociopartida.setSocio( RdbSocioDAO.popule(rs) );
				sociopartida.setPartida( RdbPartidaDAO.popule(rs) );
				lista.add( sociopartida );
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return lista;
	}
	
	public void salvaPunicoes(List<Punicaopartida> punicoes) {
		if (punicoes.size() > 0) {
			Punicaopartida pp = punicoes.get(0);
			if (pp.getPunicaopartidaPK().getCdPartida() != null) {
				this.removeTodasAsPunicoesDaPartida(pp.getPunicaopartidaPK().getCdPartida());
				for (Punicaopartida px : punicoes) {
					if (px.getNuPontospunicao() > 0) {
						this.insert( px );
					}
				}
				
			}
		}
	}
	
	public List<Punicaopartida> findAllPunicoesPartida(Integer cdPartida) {
		List<Punicaopartida> lista = new ArrayList<Punicaopartida>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			
			sql.append("select S.cdSocio,             \n");
			sql.append("       S.nmApelido,           \n");
			sql.append("       pp.nuPontospunicao,    \n");
			sql.append("       pp.dePunicao           \n");
			sql.append("from epfcpunicaopartida pp    \n");
			sql.append("join epfcsocio S on           \n");
			sql.append("     pp.cdsocio = s.cdsocio   \n");
			sql.append("where pp.cdPartida = ?        \n");
			sql.append("order by S.nmApelido          \n");
		
			pstmt = con.prepareStatement( sql.toString() );
			pstmt.setInt(1, cdPartida);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Punicaopartida sociopartida = Punicaopartida.popule(rs);
				sociopartida.setSocio( RdbSocioDAO.popule(rs) );
				sociopartida.setPartida( RdbPartidaDAO.popule(rs) );
				lista.add( sociopartida );
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return lista;
	}
	
}
