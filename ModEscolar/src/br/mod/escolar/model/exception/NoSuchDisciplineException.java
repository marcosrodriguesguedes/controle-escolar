package br.mod.escolar.model.exception;



public class NoSuchDisciplineException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoSuchDisciplineException() {
		super("A disciplina requisitada não é válida.");
	}

}
