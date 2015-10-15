package br.mod.escolar.model.exception;



public class NoSuchStudentException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoSuchStudentException() {
		super("O aluno requisitado não existe.");
	}
	
	public NoSuchStudentException(String message) {
		super(message);
	}
}
