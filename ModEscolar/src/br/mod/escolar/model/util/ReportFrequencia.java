package br.mod.escolar.model.util;

public class ReportFrequencia  {
	
	
	private String disciplina;
	
	private String serie;
	
	private String grau;
	
	private String turma;
	
	private String professor;
	
	private String nomeAluno;
	
    private String faltas;
    
    private String  percentagem;
		
	public ReportFrequencia(){
	  
	  
		
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