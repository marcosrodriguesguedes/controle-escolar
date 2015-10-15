package br.mod.escolar.model.entities;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "conceito")
public class Conceito {
	
	private Integer idAluno;
	
	private String idCompetencia;
	
	private String idHabilidade;
	
	private String conceito;
	
	private Integer conceitoFinal;

	public String getIdCompetencia() {
		return idCompetencia;
	}

	public void setIdCompetencia(String idCompetencia) {
		this.idCompetencia = idCompetencia;
	}

	public String getIdHabilidade() {
		return idHabilidade;
	}

	public void setIdHabilidade(String idHabilidade) {
		this.idHabilidade = idHabilidade;
	}

	public String getConceito() {
		return conceito;
	}

	public void setConceito(String conceito) {
		this.conceito = conceito;
	}

	@Override
	public String toString() {
		return "Conceito [idAluno=" + idAluno + ", idCompetencia="
				+ idCompetencia + ", idHabilidade=" + idHabilidade
				+ ", conceito=" + conceito + ", conceitoFinal=" + conceitoFinal
				+ "]";
	}

	public Integer getConceitoFinal() {
		return conceitoFinal;
	}

	public void setConceitoFinal(Integer conceitoFinal) {
		this.conceitoFinal = conceitoFinal;
	}

	public Integer getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Integer idAluno) {
		this.idAluno = idAluno;
	}
	
	
	
	

}
