package br.mod.escolar.model.data;

import java.util.ArrayList;
import java.util.List;

import br.mod.escolar.model.entities.Student;

public class EquipeListStudent {
	
	private String Descricao;
	
	private String nota;
	
	private String nomeDisciplina;
	
	private List<Student> alunos ;
	
	public EquipeListStudent(){
		
		alunos = new ArrayList<Student>();
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return Descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		Descricao = descricao;
	}

	/**
	 * @return the nota
	 */
	public String getNota() {
		return nota;
	}

	/**
	 * @param nota the nota to set
	 */
	public void setNota(String nota) {
		this.nota = nota;
	}

	/**
	 * @return the alunos
	 */
	public List<Student> getAlunos() {
		return alunos;
	}

	/**
	 * @param alunos the alunos to set
	 */
	public void setAlunos(List<Student> alunos) {
		this.alunos = alunos;
	}

	/**
	 * @return the nomeDisciplina
	 */
	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	/**
	 * @param nomeDisciplina the nomeDisciplina to set
	 */
	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

}
