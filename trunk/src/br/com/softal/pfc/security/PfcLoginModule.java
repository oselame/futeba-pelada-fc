package br.com.softal.pfc.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import javax.sql.DataSource;

public class PfcLoginModule implements LoginModule {
	private boolean commitSucceeded = false;
	private boolean succeeded = false;

	private User user;
	@SuppressWarnings("unchecked")
	private Set roles = new HashSet();

	protected Subject subject;
	protected CallbackHandler callbackHandler;
	@SuppressWarnings("unchecked")
	protected Map sharedState;
	private String dataSourceName;
	private String sqlUser;
	private String sqlRoles;

	@SuppressWarnings("unchecked")
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map sharedState, Map options) {
		this.subject = subject;
		this.callbackHandler = callbackHandler;
		this.sharedState = sharedState;
		dataSourceName = (String) options.get("dataSourceName");
		sqlUser = (String) options.get("sqlUser");
		sqlRoles = (String) options.get("sqlRoles");
	}

	@SuppressWarnings("unchecked")
	public boolean login() throws LoginException {
		// recupera o login e senha informados no form
		getUsernamePassword();

		Connection conn = null;
		try {
			// obtem a conexão
			try {
				Context initContext = new InitialContext();
				Context envContext = (Context) initContext.lookup("java:/comp/env");
				DataSource ds = (DataSource) envContext.lookup(dataSourceName);
				conn = ds.getConnection();
			} catch (NamingException e) {
				succeeded = false;
				throw new LoginException("Erro ao recuperar DataSource: " + e.getClass().getName() + ": " + e.getMessage());
			} catch (SQLException e) {
				succeeded = false;
				throw new LoginException("Erro ao obter conexão: " + e.getClass().getName() + ": " + e.getMessage());
			}
			// valida o usuario
			validaUsuario(conn);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		// acidiona o usuario e roles no mapa de compartilhamento
		sharedState.put("javax.security.auth.principal", user);
		sharedState.put("javax.security.auth.roles", roles);

		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean commit() throws LoginException {
		// adiciona o usuario no principals
		if (user != null && !subject.getPrincipals().contains(user)) {
			subject.getPrincipals().add(user);
		}
		// adiciona as roles no principals
		if (roles != null) {
			Iterator it = roles.iterator();
			while (it.hasNext()) {
				Role role = (Role) it.next();
				if (!subject.getPrincipals().contains(role)) {
					subject.getPrincipals().add(role);
				}
			}
		}

		commitSucceeded = true;
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean abort() throws LoginException {
		if (!succeeded) {
			return false;
		} else if (succeeded && !commitSucceeded) {
			succeeded = false;
		} else {
			succeeded = false;
			logout();
		}

		this.subject = null;
		this.callbackHandler = null;
		this.sharedState = null;
		this.roles = new HashSet();

		return succeeded;
	}

	public boolean logout() throws LoginException {
		// remove o usuario e as roles do principals
		subject.getPrincipals().removeAll(roles);
		subject.getPrincipals().remove(user);
		return true;
	}

	/**
	 * 119 * Valida login e senha no banco 120
	 */
	private void validaUsuario(Connection conn) throws LoginException {
		String senhaBanco = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement(sqlUser);
			statement.setString(1, loginInformado);
			rs = statement.executeQuery();
			if (rs.next()) {
				senhaBanco = rs.getString(1);
			} else {
				succeeded = false;
				throw new LoginException("Usuário não localizado.");
			}
		} catch (SQLException e) {
			succeeded = false;
			throw new LoginException("Erro ao abrir sessão: "
					+ e.getClass().getName() + ": " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (statement != null)
					statement.close();
			} catch (Exception e) {

			}
		}

		if (senhaInformado.equals(senhaBanco)) {
			user = new User(loginInformado);
			recuperaRoles(conn);
			user.setRoles(roles);
			return;
		} else {
			throw new LoginException("Senha Inválida.");
		}
	}

	/**
	 * 161 * Recupera as roles no banco 162
	 */
	@SuppressWarnings("unchecked")
	public void recuperaRoles(Connection conn) throws LoginException {
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement(sqlRoles);
			statement.setString(1, loginInformado);
			rs = statement.executeQuery();
			while (rs.next()) {
				roles.add(new Role(rs.getString(1)));
			}
			roles.add(new Role("LOGADO"));
		} catch (SQLException e) {
			succeeded = false;
			throw new LoginException("Erro ao recuperar roles: "
					+ e.getClass().getName() + ": " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (statement != null)
					statement.close();
			} catch (Exception e) {

			}
		}
	}

	/**
	 * 190 * Login do usuário. 191
	 */
	protected String loginInformado;

	/**
	 * 195 * Senha do usuário. 196
	 */
	protected String senhaInformado;

	/**
	 * 200 * Obtem o login e senha digitados 201
	 */
	protected void getUsernamePassword() throws LoginException {
		if (callbackHandler == null)
			throw new LoginException(
					"Error: no CallbackHandler available to garner authentication information from the user");

		Callback[] callbacks = new Callback[2];
		callbacks[0] = new NameCallback("Login");
		callbacks[1] = new PasswordCallback("Senha", false);
		try {
			callbackHandler.handle(callbacks);
			loginInformado = ((NameCallback) callbacks[0]).getName();
			char[] tmpPassword = ((PasswordCallback) callbacks[1])
					.getPassword();
			senhaInformado = new String(tmpPassword);
			((PasswordCallback) callbacks[1]).clearPassword();
		} catch (java.io.IOException ioe) {
			throw new LoginException(ioe.toString());
		} catch (UnsupportedCallbackException uce) {
			throw new LoginException(
					"Error: "
							+ uce.getCallback().toString()
							+ " not available to garner authentication information from the user");
		}
	}
}