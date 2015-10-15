package br.mod.escolar.model.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "historico_escolar")
public class Historico {
	
    private Integer id;
	
	private Integer id_aluno;
	
	private Integer id_disciplina;
	
	private Integer cod_disciplina;
	
	private String nomeDisciplina;
	
	private String serie;
	
	private String grau;
	
	private String status;
	
	private String anoLetivo;
	
    private BigDecimal mb_b1;
	
	private BigDecimal mb_b2;
	
	private BigDecimal mb_b3;
	
	private BigDecimal mb_b4;
	
	private BigDecimal mf;
	
	private BigDecimal mp;
	
	private BigDecimal notaFinal;
	
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_aluno() {
		return id_aluno;
	}

	public void setId_aluno(Integer id_aluno) {
		this.id_aluno = id_aluno;
	}

	/**
	 * @return the notaFinal
	 */
	public BigDecimal getNotaFinal() {
		return notaFinal;
	}

	/**
	 * @param notaFinal the notaFinal to set
	 */
	public void setNotaFinal(BigDecimal notaFinal) {
		this.notaFinal = notaFinal;
	}

	public Integer getId_disciplina() {
		return id_disciplina;
	}

	public void setId_disciplina(Integer id_disciplina) {
		this.id_disciplina = id_disciplina;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getGrau() {
		return grau;
	}

	public void setGrau(String grau) {
		this.grau = grau;
	}

	public String getStatus() {
		return status;
	}
	
	

	/**
	 * @return the mp
	 */
	public BigDecimal getMp() {
		return mp;
	}

	/**
	 * @param mp the mp to set
	 */
	public void setMp(BigDecimal mp) {
		this.mp = mp;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

	/**
	 * @return the anoLetivo
	 */
	public String getAnoLetivo() {
		return anoLetivo;
	}

	/**
	 * @param anoLetivo the anoLetivo to set
	 */
	public void setAnoLetivo(String anoLetivo) {
		this.anoLetivo = anoLetivo;
	}

	public BigDecimal getMb_b1() {
		return mb_b1;
	}

	public void setMb_b1(BigDecimal mb_b1) {
		this.mb_b1 = mb_b1;
	}

	public BigDecimal getMb_b2() {
		return mb_b2;
	}

	public void setMb_b2(BigDecimal mb_b2) {
		this.mb_b2 = mb_b2;
	}

	public BigDecimal getMb_b3() {
		return mb_b3;
	}

	public void setMb_b3(BigDecimal mb_b3) {
		this.mb_b3 = mb_b3;
	}

	public BigDecimal getMb_b4() {
		return mb_b4;
	}

	public void setMb_b4(BigDecimal mb_b4) {
		this.mb_b4 = mb_b4;
	}

	public BigDecimal getMf() {
		return mf;
	}

	public void setMf(BigDecimal mf) {
		this.mf = mf;
	}

	/**
	 * @return the cod_disciplina
	 */
	public Integer getCod_disciplina() {
		return cod_disciplina;
	}

	/**
	 * @param cod_disciplina the cod_disciplina to set
	 */
	public void setCod_disciplina(Integer cod_disciplina) {
		this.cod_disciplina = cod_disciplina;
	}
	

}
