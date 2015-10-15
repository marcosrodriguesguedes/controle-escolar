package br.mod.escolar.model.exception;

public class NoSuchTeacherException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoSuchTeacherException() {
		super("O professor requisitado não existe.");
	}
}
