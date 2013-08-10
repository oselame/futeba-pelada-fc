package br.com.softal.pfc.DAO;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CopyOfConexao {

	private static CopyOfConexao con = null;
	private Connection conexao;
	private DataSource ds = null;

	private CopyOfConexao() {
		try {
			Context ctx = new javax.naming.InitialContext();  
			ds = (javax.sql.DataSource) ctx.lookup("java:comp/env/jdbc/pelada");			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		try {
			conexao = ds.getConnection();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public Connection getConnection() {
		return conexao;
	}

	// Criando a conexao
	public synchronized static CopyOfConexao getConexao() {
		if (con == null) {
			con = new CopyOfConexao();
		}
		return con;
	}

/*
	public ResultSet getResultSet(String sql) {

		ResultSet rs = null;
		Statement stmt = null;

		try {
			stmt = conexao.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	public int ExecuteSQL(String sql) throws SQLException {
		ResultSet rs = null;
		Statement stmt = null;
		int res = 0;
		stmt = conexao.createStatement();
		res = stmt.executeUpdate(sql);
		return res;
	}
*/
	
}
