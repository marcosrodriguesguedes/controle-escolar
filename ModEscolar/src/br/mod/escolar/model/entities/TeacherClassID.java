package br.mod.escolar.model.entities;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "professor_turmas")
public class TeacherClassID {
	
	private Integer id;

	private Integer idTeacher;
	
	private Integer idDiscipline;
	
	private String classeID;

	/**
	 * @return the id
	 */
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public TeacherClassID() {
		
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the idTeacher
	 */
	public Integer getIdTeacher() {
		return idTeacher;
	}

	/**
	 * @param idTeacher the idTeacher to set
	 */
	public void setIdTeacher(Integer idTeacher) {
		this.idTeacher = idTeacher;
	}

	public String getClasseID() {
		return classeID;
	}

	public void setClasseID(String classeID) {
		this.classeID = classeID;
	}

	public Integer getIdDiscipline() {
		return idDiscipline;
	}

	public void setIdDiscipline(Integer idDiscipline) {
		this.idDiscipline = idDiscipline;
	}

	
	

}
