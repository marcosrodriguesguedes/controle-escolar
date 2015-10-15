package br.mod.escolar.model.exception;




public class NoSuchTeacherDisciplineException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSuchTeacherDisciplineException() {
		super("A relacao professor disciplina solicitada não existe");
	}
}
