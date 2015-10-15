package br.mod.escolar.model.exception;



public class NoSuchParentalInfoException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSuchParentalInfoException() {
		super();
	}
	
	public NoSuchParentalInfoException(String message) {
		super(message);
	}
}
