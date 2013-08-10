package br.com.softal.pfc.DAO;

public class DAOException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DAOException() {
		super();
	}

	public DAOException(String desc) {
		super(desc);
	}

	public DAOException(String desc, Throwable causa) {
		super(desc, causa);
	}

}