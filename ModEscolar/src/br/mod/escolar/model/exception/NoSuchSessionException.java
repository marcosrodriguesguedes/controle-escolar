package br.mod.escolar.model.exception;




public class NoSuchSessionException extends Exception{

	private static final long serialVersionUID = 1L;

	public NoSuchSessionException() {
		super("Ocorreu um problema com sua sessão, por favor autentique-se novamente no sistema.");
	}
	
}
