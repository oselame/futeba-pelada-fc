package br.com.softal.pfc.exception;


public class QuadrimestreException extends Exception {
	
	public QuadrimestreException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public QuadrimestreException(String message) {
		super(message);
	}
	
}
