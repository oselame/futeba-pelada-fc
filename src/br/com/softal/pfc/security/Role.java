package br.com.softal.pfc.security;

import java.security.Principal;

public class Role implements Principal {
	private String name;
	public Role(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
