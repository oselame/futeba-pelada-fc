package br.com.softal.pfc.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.softal.pfc.Socio;
import br.com.softal.pfc.DAO.DAOException;
import br.com.softal.pfc.DAO.ServiceLocator;
import br.com.softal.pfc.DAO.SocioDAO;
import br.com.softal.pfc.util.RdbUtil;
 
public class RdbSocioDAO implements SocioDAO {

	private static Log log = LogFactory.getLog(RdbSocioDAO.class);
	
	public void delete(Object obj) throws DAOException {
	}
	
	@SuppressWarnings("unchecked")
	public List<Socio> findAll() throws DAOException {
		List lista = new ArrayList();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" select * from epfcsocio ");
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

	public List<Socio> findAll(Object obj) throws DAOException {
		return null;
	}

	public Socio findByPrimaryKey(Object obj) throws DAOException {
		Socio socio = (Socio) obj;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" select * from epfcsocio ");
			sql.append(" where cdSocio = " + socio.getSocioPK().getCdSocio());
			pstmt = con.prepareStatement( sql.toString() );
			rs = pstmt.executeQuery();
			if (rs.next()) {
				socio = popule(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return socio;
	}
	
	public List<Socio> findAniversariantes(Integer nuMesaniversario) throws DAOException {
		List<Socio> lista = new ArrayList<Socio>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int nuAniversariantes = 0;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" select * from epfcsocio ");
			sql.append(" where dtNascimento is not null ");
			sql.append(" and YEAR(dtNascimento) > 1900 ");
			sql.append(" and MONTH(dtNascimento) = ? ");
			sql.append(" order by MONTH(dtNascimento) asc, DAY(dtNascimento) asc ");
			pstmt = con.prepareStatement( sql.toString() );
			pstmt.setInt(1, nuMesaniversario );
			rs = pstmt.executeQuery();
			while (rs.next() && nuAniversariantes < 3) {
				lista.add( popule(rs) );
				nuAniversariantes++;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return lista;
	}
	
	public List<Socio> findAniversariantes(Integer nuMesaniversario, Integer... tiposocio) throws DAOException {
		List<Socio> lista = new ArrayList<Socio>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int nuAniversariantes = 0;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" select * from epfcsocio ");
			sql.append(" where dtNascimento is not null ");
			sql.append(" and (YEAR(dtNascimento) > 1900 ");
			sql.append(" and ((MONTH(dtNascimento) = ? ");
			sql.append(" AND DAY(dtNascimento) >= ?)"); 
			sql.append(" or MONTH(dtNascimento) > ?)) ");
			
			sql.append(" and (flForauso is null or flForauso = 0) ");	
			if (tiposocio != null) {
				String sTpSocios = null;
				for (Integer i : tiposocio) {
					if (sTpSocios == null) {
						sTpSocios = i + "";
					} else {
						sTpSocios += ", " + i;
					}
				}
				sql.append(" and tpSocio in (" + sTpSocios + ")");
			}
			sql.append(" order by MONTH(dtNascimento) asc, DAY(dtNascimento) asc ");
			pstmt = con.prepareStatement( sql.toString() );
			pstmt.setInt(1, nuMesaniversario );
			pstmt.setInt(2, Calendar.getInstance().get(Calendar.DATE) );
			pstmt.setInt(3, nuMesaniversario );
			rs = pstmt.executeQuery();
			while (rs.next() && nuAniversariantes < 3) {
				lista.add( popule(rs) );
				nuAniversariantes++;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return lista;
	}

	public void insert(Object obj) throws DAOException {
		Socio socio = (Socio) obj;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" insert into epfcsocio (cdsocio, nmsocio, nmapelido, dtnascimento, nmcidade, sguf, nmprofissao, nmempresa, nmtime, nucelular, nucasa, nutrabalho, deemail, flforauso, flAdministrador, deSenha, tpSocio, imfoto)  ");
			sql.append(" values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)  ");
			pstmt = con.prepareStatement( sql.toString() );
			int posi = 1;
			pstmt.setNull(posi++, Types.NULL);
			pstmt.setString(posi++, socio.getNmSocio());
			pstmt.setString(posi++, socio.getNmApelido());
			if (socio.getDtNascimento() == null) {
				pstmt.setNull(posi++, Types.DATE);
			} else {
				pstmt.setDate(posi++, new java.sql.Date(socio.getDtNascimento().getTime()));
			}
			pstmt.setString(posi++, socio.getNmCidade()); 
			pstmt.setString(posi++, socio.getSgUf()); 
			pstmt.setString(posi++, socio.getNmProfissao()); 
			pstmt.setString(posi++, socio.getNmEmpresa()); 
			pstmt.setString(posi++, socio.getNmTime()); 
			pstmt.setString(posi++, socio.getNuCelular()); 
			pstmt.setString(posi++, socio.getNuCasa()); 
			pstmt.setString(posi++, socio.getNuTrabalho()); 
			pstmt.setString(posi++, socio.getDeEmail()); 
			pstmt.setInt(posi++, socio.getFlForauso());
			pstmt.setInt(posi++, socio.getFlAdministrador());
			pstmt.setString(posi++, socio.getDeSenha());
			pstmt.setInt(posi++, socio.getTpSocio());
			pstmt.setBytes(posi++, socio.getImFoto());			
			pstmt.executeUpdate();
			socio.getSocioPK().setCdSocio( getCodigoGerado( con ) );
			log.debug("Registro inserido com sucesso na epfcsocio.");			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao inserir o registro na na epfcsocio.", e);
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
		Socio socio = (Socio) obj;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {			
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" update epfcsocio ");
			sql.append(" set ");
			sql.append(" 	 nmsocio = ?, ");
			sql.append("     nmapelido = ?, ");
			sql.append("     dtnascimento = ?, ");
			sql.append("     nmcidade = ?, ");
			sql.append("     sguf = ?, ");
			sql.append("     nmprofissao = ?, ");
			sql.append("     nmempresa = ?, ");
			sql.append("     nmtime = ?, ");
			sql.append("     nucelular = ?, ");
			sql.append("     nucasa = ?, ");
			sql.append("     nutrabalho = ?, ");
			sql.append("     deemail = ?, ");
			sql.append("     flForauso = ?, ");
			sql.append("     flAdministrador = ?, ");
			sql.append("     deSenha = ?,");
			sql.append("     tpSocio = ?");			
			if (socio.getImFoto() != null && socio.getImFoto().length > 0) {
				sql.append("     ,imfoto = ? ");
			}
			sql.append(" where cdsocio = " + socio.getSocioPK().getCdSocio());		
			pstmt = con.prepareStatement( sql.toString() );
			int posi = 1;
			pstmt.setString(posi++, socio.getNmSocio());
			pstmt.setString(posi++, socio.getNmApelido()); 
			if (socio.getDtNascimento() == null) {
				pstmt.setNull(posi++, Types.NULL);
			} else {
				pstmt.setDate(posi++, new java.sql.Date(socio.getDtNascimento().getTime()));
			}
			pstmt.setString(posi++, socio.getNmCidade()); 
			pstmt.setString(posi++, socio.getSgUf()); 
			pstmt.setString(posi++, socio.getNmProfissao()); 
			pstmt.setString(posi++, socio.getNmEmpresa()); 
			pstmt.setString(posi++, socio.getNmTime()); 
			pstmt.setString(posi++, socio.getNuCelular()); 
			pstmt.setString(posi++, socio.getNuCasa()); 
			pstmt.setString(posi++, socio.getNuTrabalho()); 
			pstmt.setString(posi++, socio.getDeEmail()); 
			pstmt.setInt(posi++, socio.getFlForauso());
			pstmt.setInt(posi++, socio.getFlAdministrador());
			pstmt.setString(posi++, socio.getDeSenha());
			pstmt.setInt(posi++, socio.getTpSocio());
			if (socio.getImFoto() != null && socio.getImFoto().length > 0) {
				pstmt.setBytes(posi++, socio.getImFoto());
			}
			pstmt.executeUpdate();
			log.debug("Registro atualizado com sucesso na epfcsocio.");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao atualizar o registro na epfcsocio.", e);
			throw new DAOException("Erro ao atualizar o socio! \n" + e.getMessage());
		} finally {
			try { RdbUtil.close(pstmt, con); } catch (SQLException e) {}
		}	
	}
	
	public static Socio popule(ResultSet rs) {
		Socio socio = new Socio();
		try { socio.getSocioPK().setCdSocio( rs.getInt("cdSocio") ); } catch (Exception e) {}
		try { socio.setNmSocio( rs.getString("nmSocio") ); } catch (Exception e) {}
		try { socio.setNmApelido( rs.getString("nmApelido") ); } catch (Exception e) {}
		try { socio.setDtNascimento( rs.getDate("dtNascimento") ); } catch (Exception e) {}
		try { socio.setNmCidade( rs.getString("nmCidade") ); } catch (Exception e) {}
		try { socio.setSgUf( rs.getString("sgUf") ); } catch (Exception e) {}
		try { socio.setNmProfissao( rs.getString("nmProfissao") ); } catch (Exception e) {}
		try { socio.setNmEmpresa( rs.getString("nmEmpresa") ); } catch (Exception e) {}
		try { socio.setNmTime( rs.getString("nmTime") ); } catch (Exception e) {}
		try { socio.setNuCelular( rs.getString("nuCelular") ); } catch (Exception e) {}
		try { socio.setNuCasa( rs.getString("nuCasa") ); } catch (Exception e) {}
		try { socio.setNuTrabalho( rs.getString("nuTrabalho") ); } catch (Exception e) {}
		try { socio.setDeEmail( rs.getString("deEmail") ); } catch (Exception e) {}
		try { socio.setFlForauso( rs.getInt("flForauso") ); } catch (Exception e) {}
		try { socio.setImFoto( rs.getBytes("imFoto") ); } catch (Exception e) {}
		try { socio.setFlAdministrador( rs.getInt("flAdministrador") ); } catch (Exception e) {}
		try { socio.setDeSenha( rs.getString("deSenha") ); } catch (Exception e) {}
		try { socio.setTpSocio( rs.getInt("tpSocio") ); } catch (Exception e) {}
		return socio;
	}
	
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
	
	public Socio findLoginUsuario(String nmApelido, String deSenha) throws DAOException {
		Socio socio = new Socio();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" select * from epfcsocio ");
			sql.append(" where upper(nmApelido) = upper('" + nmApelido.toUpperCase().trim() + "')");
			sql.append(" and upper(deSenha) = upper('" + deSenha.toUpperCase().trim() + "')");
			//sql.append(" and flAdministrador = 1 ");
			sql.append(" and (flForauso is null or flForauso = 0) ");
			pstmt = con.prepareStatement( sql.toString() );
			rs = pstmt.executeQuery();
			if (rs.next()) {
				socio = popule(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return socio;
	}
	
	public Integer getQuantidesocios() {
		Integer total = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ServiceLocator.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" select count(*) as total from epfcsocio ");
			sql.append(" where (flForauso is null or flForauso = 0) ");
			pstmt = con.prepareStatement( sql.toString() );
			rs = pstmt.executeQuery();
			if (rs.next()) {
				total = rs.getInt("total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { RdbUtil.close(rs, pstmt, con); } catch (SQLException e) {}
		}
		return total;
	}
	
	
}
