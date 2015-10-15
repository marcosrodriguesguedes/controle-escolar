package br.mod.escolar.model.exception;

import br.mod.escolar.model.util.Messages;

public class InvalidStateUser extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidStateUser(){
		super(Messages.USUARIO_INATIVO_NA_BASE);
	}
	
	

}
