package br.com.softal.pfc.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.softal.pfc.DAO.ServiceLocator;


public class RdbUtil {

	public static void close(ResultSet rs, Statement stmt, Connection con) throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (stmt != null) {
			stmt.close();
		}
		if (con != null) {
			con.close();
		}
	}

	public static void close(Statement stmt) throws SQLException {
		close(null, stmt, null);
	}

	public static void close(Statement stmt, Connection con) throws SQLException {
		close(null, stmt, con);
	}

	public static void close(ResultSet rs, Statement stmt) throws SQLException {
		close(rs, stmt, null);
	}
	
}
