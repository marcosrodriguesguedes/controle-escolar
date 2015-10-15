package br.mod.escolar.model.exception;


import br.mod.escolar.model.util.Messages;

public class NoSuchUserException extends Exception  {

	private static final long serialVersionUID = 7342565207633130827L;

	public NoSuchUserException() {
		super(Messages.INVALID_USER);
	}
}
