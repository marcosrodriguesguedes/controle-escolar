package br.mod.escolar.model.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "notas_historico")
public class NotasHistorico {
    
	private Integer id;
	
	private Integer id_aluno;
	
	private Integer id_disciplina;
	
	private String nomeDisciplina;
	
	private String serie;
	
	private String grau;
	
	private Date anoLetivo;
	
	private BigDecimal nota1_b1;
	
	private BigDecimal nota2_b1;
	
	private BigDecimal nota3_b1;
	
    private BigDecimal rec1_b1;
	
	private BigDecimal rec2_b1;
	
	private BigDecimal rec3_b1;
	
	private BigDecimal nota1_b2;
	
	private BigDecimal nota2_b2;
	
	private BigDecimal nota3_b2;
	
	private BigDecimal rec1_b2;
		
	private BigDecimal rec2_b2;
		
	private BigDecimal rec3_b2;
	
	private BigDecimal nota1_b3;
	
	private BigDecimal nota2_b3;
	
	private BigDecimal nota3_b3;
	
	private BigDecimal rec1_b3;
		
	private BigDecimal rec2_b3;
		
	private BigDecimal rec3_b3;
	
    private BigDecimal nota1_b4;
	
	private BigDecimal nota2_b4;
	
	private BigDecimal nota3_b4;
	
	private BigDecimal rec1_b4;
		
	private BigDecimal rec2_b4;
		
	private BigDecimal rec3_b4;
	
	private BigDecimal mb_b1;
	
	private BigDecimal mb_b2;
	
	private BigDecimal mb_b3;
	
	private BigDecimal mb_b4;
	
	private BigDecimal mf;
	
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

	public BigDecimal getNota1_b1() {
		return nota1_b1;
	}

	public void setNota1_b1(BigDecimal nota1_b1) {
		this.nota1_b1 = nota1_b1;
	}

	public BigDecimal getNota2_b1() {
		return nota2_b1;
	}

	public void setNota2_b1(BigDecimal nota2_b1) {
		this.nota2_b1 = nota2_b1;
	}

	public BigDecimal getNota3_b1() {
		return nota3_b1;
	}

	public void setNota3_b1(BigDecimal nota3_b1) {
		this.nota3_b1 = nota3_b1;
	}

	public BigDecimal getRec1_b1() {
		return rec1_b1;
	}

	public void setRec1_b1(BigDecimal rec1_b1) {
		this.rec1_b1 = rec1_b1;
	}

	public BigDecimal getRec2_b1() {
		return rec2_b1;
	}

	public void setRec2_b1(BigDecimal rec2_b1) {
		this.rec2_b1 = rec2_b1;
	}

	public BigDecimal getRec3_b1() {
		return rec3_b1;
	}

	public void setRec3_b1(BigDecimal rec3_b1) {
		this.rec3_b1 = rec3_b1;
	}

	public BigDecimal getNota1_b2() {
		return nota1_b2;
	}

	public void setNota1_b2(BigDecimal nota1_b2) {
		this.nota1_b2 = nota1_b2;
	}

	public BigDecimal getNota2_b2() {
		return nota2_b2;
	}

	public void setNota2_b2(BigDecimal nota2_b2) {
		this.nota2_b2 = nota2_b2;
	}

	public BigDecimal getNota3_b2() {
		return nota3_b2;
	}

	public void setNota3_b2(BigDecimal nota3_b2) {
		this.nota3_b2 = nota3_b2;
	}

	public BigDecimal getRec1_b2() {
		return rec1_b2;
	}

	public void setRec1_b2(BigDecimal rec1_b2) {
		this.rec1_b2 = rec1_b2;
	}

	public BigDecimal getRec2_b2() {
		return rec2_b2;
	}

	public void setRec2_b2(BigDecimal rec2_b2) {
		this.rec2_b2 = rec2_b2;
	}

	public BigDecimal getRec3_b2() {
		return rec3_b2;
	}

	public void setRec3_b2(BigDecimal rec3_b2) {
		this.rec3_b2 = rec3_b2;
	}

	public BigDecimal getNota1_b3() {
		return nota1_b3;
	}

	public void setNota1_b3(BigDecimal nota1_b3) {
		this.nota1_b3 = nota1_b3;
	}

	public BigDecimal getNota2_b3() {
		return nota2_b3;
	}

	public void setNota2_b3(BigDecimal nota2_b3) {
		this.nota2_b3 = nota2_b3;
	}

	public BigDecimal getNota3_b3() {
		return nota3_b3;
	}

	public void setNota3_b3(BigDecimal nota3_b3) {
		this.nota3_b3 = nota3_b3;
	}

	public BigDecimal getRec1_b3() {
		return rec1_b3;
	}

	public void setRec1_b3(BigDecimal rec1_b3) {
		this.rec1_b3 = rec1_b3;
	}

	public BigDecimal getRec2_b3() {
		return rec2_b3;
	}

	public void setRec2_b3(BigDecimal rec2_b3) {
		this.rec2_b3 = rec2_b3;
	}

	public BigDecimal getRec3_b3() {
		return rec3_b3;
	}

	public void setRec3_b3(BigDecimal rec3_b3) {
		this.rec3_b3 = rec3_b3;
	}

	public BigDecimal getNota1_b4() {
		return nota1_b4;
	}

	public void setNota1_b4(BigDecimal nota1_b4) {
		this.nota1_b4 = nota1_b4;
	}

	public BigDecimal getNota2_b4() {
		return nota2_b4;
	}

	public void setNota2_b4(BigDecimal nota2_b4) {
		this.nota2_b4 = nota2_b4;
	}

	public BigDecimal getNota3_b4() {
		return nota3_b4;
	}

	public void setNota3_b4(BigDecimal nota3_b4) {
		this.nota3_b4 = nota3_b4;
	}

	public BigDecimal getRec1_b4() {
		return rec1_b4;
	}

	public void setRec1_b4(BigDecimal rec1_b4) {
		this.rec1_b4 = rec1_b4;
	}

	public BigDecimal getRec2_b4() {
		return rec2_b4;
	}

	public void setRec2_b4(BigDecimal rec2_b4) {
		this.rec2_b4 = rec2_b4;
	}

	public BigDecimal getRec3_b4() {
		return rec3_b4;
	}

	public void setRec3_b4(BigDecimal rec3_b4) {
		this.rec3_b4 = rec3_b4;
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

	public Date getAnoLetivo() {
		return anoLetivo;
	}

	public void setAnoLetivo(Date anoLetivo) {
		this.anoLetivo = anoLetivo;
	}
	
	
	
}
