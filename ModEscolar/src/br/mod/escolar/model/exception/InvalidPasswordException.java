package br.mod.escolar.model.exception;


import br.mod.escolar.model.util.Messages;

public class InvalidPasswordException extends Exception {
	
	private static final long serialVersionUID = 1365843493018274290L;

	public InvalidPasswordException() {
		super(Messages.INVALID_PASSWORD);
	}
	
}
