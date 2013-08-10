package br.com.softal.pfc.DAO;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ServiceLocator {


	public static Connection getConexao() {
			String JNDINome = "jdbc/pelada";
			Connection con = null;
			// Obtém a raiz da hierarquia de nomes
			InitialContext contexto;
			try {
				contexto = new InitialContext();
				// Obtém a origem dos dados
				DataSource ds = (DataSource) contexto.lookup("java:comp/env/" + JNDINome);
				// Obtém uma conexão
				con = ds.getConnection();
			} catch (NamingException e) {
				System.out.println("Erro no jndi");
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("Erro ao pegar a Connection");
				e.printStackTrace();
			}
			// Retorna a conexão
			return con;
	}

}
