package br.mod.escolar.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "equipe_aluno")
public class EquipeStudents {
	
	private Integer id;
	
	private Integer idEquipe;
	
	private Integer id_student;
	
	public EquipeStudents(){
		
	}
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdEquipe() {
		return idEquipe;
	}

	public void setIdEquipe(Integer idEquipe) {
		this.idEquipe = idEquipe;
	}

	public Integer getId_student() {
		return id_student;
	}

	public void setId_student(Integer id_student) {
		this.id_student = id_student;
	}
	
	

}
