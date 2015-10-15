package br.mod.escolar.model.exception;



import br.mod.escolar.model.util.Messages;

public class NoSuchUserTypeException extends Exception {



	/**
	 * 
	 */
	private static final long serialVersionUID = 3841922069045792183L;

	public NoSuchUserTypeException() {
		super(Messages.INVALID_USER_TYPE);
	}
}
