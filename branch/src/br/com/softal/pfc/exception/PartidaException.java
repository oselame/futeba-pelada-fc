package br.com.softal.pfc.exception;


public class PartidaException extends Exception {
	
	public PartidaException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public PartidaException(String message) {
		super(message);
	}
	
}
