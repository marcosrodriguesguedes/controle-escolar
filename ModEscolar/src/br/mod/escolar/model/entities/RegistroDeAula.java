package br.mod.escolar.model.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "registro_de_aula")
public class RegistroDeAula {
	
	private Integer id;
	
	private String idDisciplina;
	
	private String idProfessor;
	
	private String grau;
	
	private String serie;
	
	private String turma;
	
	private String objetosDeConhecimento;
	
	private String competencias;
	
	private String habilidades;
	
	private String nomeProfessor;
	
	private String numeroAula;
	
	private Date dataRegistro;
	
	
	public RegistroDeAula(){
		
	}


	 @Id
	 @GeneratedValue(strategy = IDENTITY)
	 @Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	@Column(name = "idDisciplina")
	public String getIdDisciplina() {
		return idDisciplina;
	}


	public void setIdDisciplina(String idDisciplina) {
		this.idDisciplina = idDisciplina;
	}



	@Column(name = "idProfessor")
	public String getIdProfessor() {
		return idProfessor;
	}


	public void setIdProfessor(String idProfessor) {
		this.idProfessor = idProfessor;
	}



	@Column(name = "grau")
	public String getGrau() {
		return grau;
	}






	public void setGrau(String grau) {
		this.grau = grau;
	}





	@Column(name = "serie")
	public String getSerie() {
		return serie;
	}






	public void setSerie(String serie) {
		this.serie = serie;
	}





	@Column(name = "turma")
	public String getTurma() {
		return turma;
	}






	public void setTurma(String turma) {
		this.turma = turma;
	}





	@Column(name = "objetosDeConhecimento")
	public String getObjetosDeConhecimento() {
		return objetosDeConhecimento;
	}






	public void setObjetosDeConhecimento(String objetosDeConhecimento) {
		this.objetosDeConhecimento = objetosDeConhecimento;
	}





	@Column(name = "competencias")
	public String getCompetencias() {
		return competencias;
	}



	public void setCompetencias(String competencias) {
		this.competencias = competencias;
	}



	@Column(name = "habilidades")
	public String getHabilidades() {
		return habilidades;
	}




	public void setHabilidades(String habilidades) {
		this.habilidades = habilidades;
	}



	@Column(name = "nomeProfessor")
	public String getNomeProfessor() {
		return nomeProfessor;
	}



	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}


	@Column(name = "numeroAula")
	public String getNumeroAula() {
		return numeroAula;
	}


	public void setNumeroAula(String numeroAula) {
		this.numeroAula = numeroAula;
	}



	@Column(name = "dataRegistro")
	public Date getDataRegistro() {
		return dataRegistro;
	}


	public void setDataRegistro(Date data) {
		this.dataRegistro = data;
	}
	
	
	
	
	

}
