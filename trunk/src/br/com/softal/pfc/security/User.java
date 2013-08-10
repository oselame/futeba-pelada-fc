package br.com.softal.pfc.security;

import java.security.Principal;
import java.util.Set;

public class User implements Principal {
	private String name;
	@SuppressWarnings("unchecked")
	private Set roles;

	public User(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@SuppressWarnings("unchecked")
	public Set getRoles() {
		return roles;
	}

	@SuppressWarnings("unchecked")
	public void setRoles(Set roles) {
		if (this.roles == null)
			this.roles = roles;
	}
}
