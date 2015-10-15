package br.mod.escolar.relatorios.beans;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class FrequenciaReportDataBean implements Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private String disciplina;
	
	private String serie;
	
	private String grau;
	
	private String turma;
	
	private String professor;

	private String nomeAluno;
	
	private String faltas;
	
	private String  percentagem;
	
	private Image BANNER_LOGO;
	
	
	public FrequenciaReportDataBean(){
		nomeAluno = "-";
		faltas = "-";
		percentagem = "-";
		BANNER_LOGO = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		
	}


	


	/**
	 * @return the nomeAluno
	 */
	public String getNomeAluno() {
		return nomeAluno;
	}





	/**
	 * @param nomeAluno the nomeAluno to set
	 */
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}





	/**
	 * @return the faltas
	 */
	public String getFaltas() {
		return faltas;
	}


	/**
	 * @param faltas the faltas to set
	 */
	public void setFaltas(String faltas) {
		this.faltas = faltas;
	}


	/**
	 * @return the percentagem
	 */
	public String getPercentagem() {
		return percentagem;
	}


	/**
	 * @param percentagem the percentagem to set
	 */
	public void setPercentagem(String percentagem) {
		this.percentagem = percentagem;
	}


	public Image getBANNER_LOGO() {
		return BANNER_LOGO;
	}


	public void setBANNER_LOGO(Image bANNER_LOGO) {
		BANNER_LOGO = bANNER_LOGO;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}





	public String getDisciplina() {
		return disciplina;
	}





	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
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





	public String getTurma() {
		return turma;
	}





	public void setTurma(String turma) {
		this.turma = turma;
	}





	public String getProfessor() {
		return professor;
	}





	public void setProfessor(String professor) {
		this.professor = professor;
	}
	
}
