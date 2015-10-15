package br.mod.escolar.model.entities;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "turmas")
public class TurmasConfig {
	
	private Integer id;
	
	private int numeroMaxAlunosTurma;
	
	private int numeroAlunoAtual;
	
	private String anoLetivo;
	
	private String serie;
	
	private String grau;
	
	private String turma;

	/**
	 * @return the id
	 */
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the numeroMaxAlunosTurma
	 */
	public int getNumeroMaxAlunosTurma() {
		return numeroMaxAlunosTurma;
	}

	/**
	 * @param numeroMaxAlunosTurma the numeroMaxAlunosTurma to set
	 */
	public void setNumeroMaxAlunosTurma(int numeroMaxAlunosTurma) {
		this.numeroMaxAlunosTurma = numeroMaxAlunosTurma;
	}

	/**
	 * @return the numeroAlunoAtual
	 */
	public int getNumeroAlunoAtual() {
		return numeroAlunoAtual;
	}

	/**
	 * @param numeroAlunoAtual the numeroAlunoAtual to set
	 */
	public void setNumeroAlunoAtual(int numeroAlunoAtual) {
		this.numeroAlunoAtual = numeroAlunoAtual;
	}

	/**
	 * @return the ano
	 */
	public String getAno() {
		return anoLetivo;
	}

	/**
	 * @param ano the ano to set
	 */
	public void setAno(String ano) {
		this.anoLetivo = ano;
	}

	/**
	 * @return the serie
	 */
	public String getSerie() {
		return serie;
	}

	/**
	 * @param serie the serie to set
	 */
	public void setSerie(String serie) {
		this.serie = serie;
	}

	/**
	 * @return the grau
	 */
	public String getGrau() {
		return grau;
	}

	/**
	 * @param grau the grau to set
	 */
	public void setGrau(String grau) {
		this.grau = grau;
	}

	/**
	 * @return the turma
	 */
	public String getTurma() {
		return turma;
	}

	/**
	 * @param turma the turma to set
	 */
	public void setTurma(String turma) {
		this.turma = turma;
	}
	
	
	
	

}


