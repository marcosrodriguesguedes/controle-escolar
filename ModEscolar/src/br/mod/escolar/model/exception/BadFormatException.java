package br.mod.escolar.model.exception;



public class BadFormatException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BadFormatException(String message) {
		super(message);
	}
	
	public BadFormatException() {
		super("O arquivo com as habilidades está em um formato impróprio.");
	}
}