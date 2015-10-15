package br.mod.escolar.model.entities;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "frequencia")
public class Frequencia {
	
	private Integer id;
	
	private String idAluno;
	
	private String idDisciplina;
	
	private String nomeDisciplina;
	
	private Integer atualizar;

	private String horario;
	
	private String grau;
	
	private String serie;
	
	private String turma;
	
	private String dia;
	
	private String mes;
	
	private String ano;
	
	private String data;
	
	private String frequencia;
	
	private String diaDaSemana;
	
	private String turno;
	
	public Frequencia(){
		
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
    
	@Column(name = "idAluno")
	public String getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(String idAluno) {
		this.idAluno = idAluno;
	}
	@Column(name = "idDisciplina")
	public String getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(String idDisciplina) {
		this.idDisciplina = idDisciplina;
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
	@Column(name = "dia")
	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}
	@Column(name = "mes")
	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}
	@Column(name = "ano")
	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}
	@Column(name = "data")
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
	@Column(name = "nomeDisciplina")
	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}
	@Column(name = "horario")
	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}
	@Column(name = "frequencia")
	public String getFrequencia() {
		return frequencia;
	}
	
	public void setFrequencia(String f) {
		this.frequencia = f;
	}
	@Column(name = "diaDaSemana")
	public String getDiaDaSemana() {
		return diaDaSemana;
	}

	public void setDiaDaSemana(String diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}
	@Column(name = "turno")
	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}
	
	

	@Override
	public String toString() {
		return "Frequencia [id=" + id + ", idAluno=" + idAluno
				+ ", idDisciplina=" + idDisciplina + ", nomeDisciplina="
				+ nomeDisciplina + ", horario=" + horario + ", grau=" + grau
				+ ", serie=" + serie + ", turma=" + turma + ", dia=" + dia
				+ ", mes=" + mes + ", ano=" + ano + ", data=" + data
				+ ", frequencia=" + frequencia + ", diaDaSemana=" + diaDaSemana
				+ ", turno=" + turno + "]";
	}
	@Column(name = "atualizar")
	public Integer getAtualizar() {
		return atualizar;
	}

	public void setAtualizar(Integer atualizar) {
		this.atualizar = atualizar;
	}
	
	
}
