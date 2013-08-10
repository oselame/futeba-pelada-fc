package br.com.softal.pfc.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.softal.pfc.Sociopartida;
import br.com.softal.pfc.DAO.DAOException;
import br.com.softal.pfc.DAO.ServiceLocator;
import br.com.softal.pfc.DAO.SociopartidaDAO;
import br.com.softal.pfc.dto.EmailAtrasoDto;
import br.com.softal.pfc.dto.RelatorioDto;
import br.com.softal.pfc.util.RdbUtil;
import br.com.softal.pfc.util.Util;
 
public class RdbSociopartidaDAO implements SociopartidaDAO {

	private static Log log = LogFactory.getLog(RdbSociopartidaDAO.class);
	
	public void deleteSociospartida(Integer cdPartida) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" delete from epfcsociopartida  ");
			sql.append(" where cdPartida = " + cdPartida);
			pstmt = con.prepareStatement( sql.toString() );
			pstmt.executeUpdate();
			log.debug("Registro excluído da epfcsociopartida");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao excluir o registro da epfcsociopartida", e);
		} finally {
			try { RdbUtil.close(pstmt, con); } catch (SQLException e) {}
		}
	}
	
	public void delete(Object obj) throws DAOException {
		Sociopartida sociopartida = (Sociopartida) obj;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" delete from epfcsociopartida  ");
			sql.append(" where cdPartida = " + sociopartida.getSociopartidaPK().getCdPartida());
			sql.append(" and cdSocio = " + sociopartida.getSociopartidaPK().getCdSocio());
			pstmt = con.prepareStatement( sql.toString() );
			pstmt.executeUpdate();
			log.debug("Registro excluído da epfcsociopartida");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao excluir o registro da epfcsociopartida", e);
		} finally {
			try { RdbUtil.close(pstmt, con); } catch (SQLException e) {}
		}	
	}

	@SuppressWarnings("unchecked")
	public List findAll() throws DAOException {
		return null;
	}

	@SuppressWarnings("unchecked")
	public List findAll(Object obj) throws DAOException {
		Sociopartida sociopartida = (Sociopartida) obj; 
		List lista = new ArrayList();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" Select * \n");
			sql.append(" from epfcsociopartida SP \n");
			sql.append(" join epfcpartida P on \n");
			sql.append("   P.cdPartida = SP.cdPartida \n");
			sql.append(" join epfcsocio S on \n");
			sql.append("   S.cdSocio = SP.cdSocio \n");
			sql.append(" where 1 = 1 \n");
			if (!sociopartida.getMostraForauso()) {
				sql.append(" and (S.flForauso is null or S.flForauso = 0) ");
			}
			
			if (sociopartida.getSociopartidaPK().getCdPartida() != null) {
				sql.append(" and SP.cdPartida = " + sociopartida.getSociopartidaPK().getCdPartida());
			}
			
			sql.append(" order by SP.cdPartida, SP.cdTime, SP.flgoleiro desc \n");			
			pstmt = con.prepareStatement( sql.toString() );
			rs = pstmt.executeQuery();
			while (rs.next()) {
				sociopartida = popule(rs);
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

	public Object findByPrimaryKey(Object obj) throws DAOException {
		return null;
	}

	public void insert(Object obj) throws DAOException {
		Sociopartida sociopartida = (Sociopartida) obj;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" insert into epfcsociopartida (cdsocio, cdpartida, cdtime, nugol, nuGolcontra, " +
					"flcartaovermelho, flcartaoazul, flcartaoamarelo, flgoleiro, flatrazado)  ");
			sql.append(" values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)  ");
			pstmt = con.prepareStatement( sql.toString() );
			int posi = 1;
			pstmt.setInt(posi++, sociopartida.getSociopartidaPK().getCdSocio());
			pstmt.setInt(posi++, sociopartida.getSociopartidaPK().getCdPartida());
			pstmt.setInt(posi++, sociopartida.getCdTime());
			pstmt.setInt(posi++, sociopartida.getNuGol() == null ? 0 : sociopartida.getNuGol());
			pstmt.setInt(posi++, sociopartida.getNuGolcontra() == null ? 0 : sociopartida.getNuGolcontra());
			pstmt.setInt(posi++, sociopartida.getFlCartaovermelho());
			pstmt.setInt(posi++, sociopartida.getFlCartaoazul());
			pstmt.setInt(posi++, sociopartida.getFlCartaoamarelo());
			pstmt.setInt(posi++, sociopartida.getFlGoleiro() == null ? 0 : sociopartida.getFlGoleiro());
			pstmt.setInt(posi++, sociopartida.getFlAtrazado() == null ? 0 : sociopartida.getFlAtrazado());
			pstmt.executeUpdate();
			log.debug("Registro inserido com sucesso na epfcsociopartida." );
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao inserir o registro na epfcsociopartida." );
		} finally {
			try { RdbUtil.close(pstmt, con); } catch (SQLException e) {}
		}	
	}

	public void update(Object obj) throws DAOException {
	}
	
	@SuppressWarnings("unchecked")
	public List findAllJogodoresTimes(Object obj) throws DAOException {
		Sociopartida sociopartida = (Sociopartida) obj; 
		List lista = new ArrayList();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" Select S.*, SP.*, TC.* \n");
			sql.append(" from epfcsocio S \n");
			sql.append(" left join epfcsociopartida SP on \n");
			sql.append("     SP.cdsocio = S.cdsocio \n");
			sql.append("     and SP.cdpartida = " + sociopartida.getSociopartidaPK().getCdPartida());
			sql.append(" left join epfcpartida P on \n");
			sql.append("     P.cdpartida = SP.cdpartida \n");
			sql.append(" left join epfctimecamisa TC on \n");
			sql.append("   SP.cdtime = TC.cdtime \n");
			sql.append(" where 1 = 1 \n");
			sql.append(" and (S.flForauso is null or S.flForauso = 0) ");
			sql.append(" Order by S.nmapelido asc \n");			
			pstmt = con.prepareStatement( sql.toString() );
			rs = pstmt.executeQuery();
			while (rs.next()) {
				sociopartida = popule(rs);
				sociopartida.setSocio( RdbSocioDAO.popule(rs) );
				sociopartida.setPartida( RdbPartidaDAO.popule(rs) );
				sociopartida.setTimecamisa( RdbTimecamisaDAO.popule(rs) );
				lista.add( sociopartida );
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return lista;
	}
	
	public static Sociopartida popule(ResultSet rs) {
		Sociopartida sociopartida = new Sociopartida();
		try { sociopartida.getSociopartidaPK().setCdSocio( rs.getInt("cdSocio") ); } catch (Exception e) {}
		try { sociopartida.getSociopartidaPK().setCdPartida( rs.getInt("cdPartida") ); } catch (Exception e) {}
		try { sociopartida.setCdTime( rs.getInt("cdTime") ); } catch (Exception e) {}
		try { sociopartida.setNuGol( rs.getInt("nuGol") ); } catch (Exception e) {}
		try { sociopartida.setNuGolcontra( rs.getInt("nuGolcontra") ); } catch (Exception e) {}
		try { sociopartida.setFlCartaovermelho( rs.getInt("flCartaovermelho") ); } catch (Exception e) {}
		try { sociopartida.setFlCartaoazul( rs.getInt("flCartaoazul") ); } catch (Exception e) {}
		try { sociopartida.setFlCartaoamarelo( rs.getInt("flCartaoamarelo") ); } catch (Exception e) {}
		try { sociopartida.setFlGoleiro( rs.getInt("flGoleiro") ); } catch (Exception e) {}
		try { sociopartida.setFlAtrazado( rs.getInt("flAtrazado") ); } catch (Exception e) {}
		return sociopartida;
		
	}
	
	public Boolean existeParticipantes(Integer cdPartida) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" select count(*) as total  ");
			sql.append(" from epfcsociopartida");
			sql.append(" where cdPartida = " + cdPartida );
			pstmt = con.prepareStatement( sql.toString() );
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return (rs.getInt("total") > 0);
			}
			log.debug("Registro inserido na epfcsociopartida");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao inserir o registro na epfcsociopartida ", e);
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}	
		return false;
	} 
	
	public List<RelatorioDto> findAllSociosAtrazados(RelatorioDto relatorioDto) {
		List<RelatorioDto> lista = new ArrayList<RelatorioDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT p.nuAno, p.dtPartida, p.nuGolvencedor, p.nuGolperdedor, e.cdSocio, s.nmApelido").append("\n");
			sql.append(" FROM pelada.epfcsociopartida e").append("\n");
			sql.append(" join pelada.epfcpartida p on").append("\n");
			sql.append("   p.cdpartida = e.cdpartida").append("\n");
			sql.append(" join pelada.epfcsocio s on").append("\n");
			sql.append("   s.cdsocio = e.cdsocio").append("\n");
			sql.append(" where e.flAtrazado = 1").append("\n");
			sql.append(" and p.nuAno = ?").append("\n");
			if (!Util.isNullOrZero(relatorioDto.getNuMes())) {
				sql.append(" and month(p.dtPartida) = ?").append("\n");
			}
			if (!Util.isNullOrZero(relatorioDto.getCdSocio())) {
				sql.append(" and e.cdSocio = ?").append("\n");
			}
			sql.append(" order by p.nuAno, p.dtPartida, s.nmApelido").append("\n");		
			pstmt = con.prepareStatement( sql.toString() );
			int pos = 0;
			pstmt.setInt(++pos, relatorioDto.getNuAno());
			if (!Util.isNullOrZero(relatorioDto.getNuMes())) {
				pstmt.setInt(++pos, relatorioDto.getNuMes());
			}
			if (!Util.isNullOrZero(relatorioDto.getCdSocio())) {
				pstmt.setInt(++pos, relatorioDto.getCdSocio());
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lista.add( RelatorioDto.popule(rs) );
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return lista;
	}
	
	public Boolean existeAtrasadosOuCartoes(Integer cdPartida) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total = 0;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT  \n");  
			sql.append("    IFNULL(sum(x.flatrazado),0) + \n");
			sql.append("    IFNULL(sum(x.flCartaovermelho),0) + \n");
			sql.append("    IFNULL(sum(x.flCartaoazul),0) + \n");
			sql.append("    IFNULL(sum(x.flCartaoamarelo),0) AS total \n");
			sql.append(" FROM epfcsociopartida x \n");
			sql.append(" WHERE x.cdPartida=?	 \n");		
			pstmt = con.prepareStatement( sql.toString() );
			pstmt.setInt(1, cdPartida);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				total = rs.getInt("total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return total > 0;
	}
	
	public List<EmailAtrasoDto> findAllSociosAtrasadosComCartao(Integer cdPartida) {
		List<EmailAtrasoDto> lista = new ArrayList<EmailAtrasoDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();

			sql.append(" SELECT p.dtPartida, x.cdSocio, s.nmsocio, s.deemail, s.nmApelido,  \n");
			sql.append(" 	IFNULL(sum(x.flatrazado),0) AS flAtrazado,  \n");
			sql.append(" 	IFNULL(sum(x.flCartaovermelho),0) AS flCartaovermelho, \n");
			sql.append(" 	IFNULL(sum(x.flCartaoazul),0) AS flCartaoazul, \n");
			sql.append(" 	IFNULL(sum(x.flCartaoamarelo),0) AS flCartaoamarelo \n");
			sql.append(" FROM epfcsociopartida x \n");
			sql.append(" JOIN epfcsocio s ON \n"); 
			sql.append(" 	s.cdSocio = x.cdSocio \n");
			sql.append(" JOIN epfcpartida p ON \n");
			sql.append("   x.cdPartida = p.cdPartida \n");
			sql.append(" WHERE x.cdPartida = ? \n");
			sql.append(" AND s.flForauso <> 1 \n");
			sql.append(" GROUP BY p.dtPartida, x.cdSocio, s.nmsocio, s.deemail, s.nmApelido \n");
			sql.append(" HAVING  \n");
			sql.append(" 	(IFNULL(sum(x.flAtrazado),0) + \n");
			sql.append(" 	IFNULL(sum(x.flCartaovermelho),0) + \n");
			sql.append(" 	IFNULL(sum(x.flCartaoazul),0) + \n");
			sql.append(" 	IFNULL(sum(x.flCartaoamarelo),0)) > 0 \n");		
			
			pstmt = con.prepareStatement( sql.toString() );
			int pos = 0;
			pstmt.setInt(++pos, cdPartida);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lista.add( EmailAtrasoDto.popule(rs) );
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return lista;
	}
	
}
