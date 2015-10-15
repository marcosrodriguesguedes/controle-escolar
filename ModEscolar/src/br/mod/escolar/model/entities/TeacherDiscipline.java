package br.mod.escolar.model.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import br.mod.escolar.model.exception.InvalidParameterException;

@Entity
@Table(name = "professor_disciplina")
public class TeacherDiscipline {

	private int id;
	private int idTeacher;
	private int idDiscipline;

	/**
	 * Construtor default para TeacherDiscipline
	 */
	public TeacherDiscipline() {

	}

	/**
	 * Construtor para TeacherDiscipline
	 * @param id
	 * @param idTeacher
	 * @param idDiscipline
	 * @throws InvalidParameterException
	 */
	public TeacherDiscipline(int id, int idTeacher, int idDiscipline)
			throws InvalidParameterException {

		this.id = id;
		this.idTeacher = idTeacher;
		this.idDiscipline = idDiscipline;
	}

	@Id
	@GeneratedValue
	@Column(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "id_professor")
	public int getIdTeacher() {
		return idTeacher;
	}

	public void setIdTeacher(int idTeacher) {
		this.idTeacher = idTeacher;
	}

	@Column(name = "id_disciplina")
	public int getIdDiscipline() {
		return idDiscipline;
	}

	public void setIdDiscipline(int idDiscipline) {
		this.idDiscipline = idDiscipline;
	}

}