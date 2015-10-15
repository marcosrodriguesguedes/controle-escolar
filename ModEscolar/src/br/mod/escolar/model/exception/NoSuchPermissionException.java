package br.mod.escolar.model.exception;

public class NoSuchPermissionException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoSuchPermissionException() {
		super();
	}
	
	public NoSuchPermissionException(String message) {
		super(message);
	}
}
