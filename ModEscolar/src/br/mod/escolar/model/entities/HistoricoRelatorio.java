package br.mod.escolar.model.entities;

public class HistoricoRelatorio {
	
	private String disciplina;
	
	private String mb1;
	
	private String mb2;
	
	private String mb3;
	
	private String mb4;
	
	private String mediaPacial;
	
	private String horasAula;
	
	private String frequencia;
	
	private String faltas;
	
	private String mediaFinal;
	
	private String provaFinal;
	
	private String status;
	
	private Integer cod_disciplina;

	/**
	 * @return the disciplina
	 */
	public String getDisciplina() {
		return disciplina;
	}

	/**
	 * @param disciplina the disciplina to set
	 */
	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	/**
	 * @return the provaFianl
	 */
	public String getProvaFinal() {
		return provaFinal;
	}

	/**
	 * @param provaFianl the provaFianl to set
	 */
	public void setProvaFinal(String provaFianl) {
		this.provaFinal = provaFianl;
	}

	/**
	 * @return the mb1
	 */
	public String getMb1() {
		return mb1;
	}

	/**
	 * @param mb1 the mb1 to set
	 */
	public void setMb1(String mb1) {
		this.mb1 = mb1;
	}

	/**
	 * @return the mb2
	 */
	public String getMb2() {
		return mb2;
	}

	/**
	 * @param mb2 the mb2 to set
	 */
	public void setMb2(String mb2) {
		this.mb2 = mb2;
	}

	/**
	 * @return the mb3
	 */
	public String getMb3() {
		return mb3;
	}

	/**
	 * @param mb3 the mb3 to set
	 */
	public void setMb3(String mb3) {
		this.mb3 = mb3;
	}

	/**
	 * @return the mb4
	 */
	public String getMb4() {
		return mb4;
	}

	/**
	 * @param mb4 the mb4 to set
	 */
	public void setMb4(String mb4) {
		this.mb4 = mb4;
	}

	/**
	 * @return the horasAula
	 */
	public String getHorasAula() {
		return horasAula;
	}

	/**
	 * @param horasAula the horasAula to set
	 */
	public void setHorasAula(String horasAula) {
		this.horasAula = horasAula;
	}

	/**
	 * @return the frequencia
	 */
	public String getFrequencia() {
		return frequencia;
	}

	/**
	 * @param frequencia the frequencia to set
	 */
	public void setFrequencia(String frequencia) {
		this.frequencia = frequencia;
	}

	/**
	 * @return the mediaFinal
	 */
	public String getMediaFinal() {
		return mediaFinal;
	}

	/**
	 * @param mediaFinal the mediaFinal to set
	 */
	public void setMediaFinal(String mediaFinal) {
		this.mediaFinal = mediaFinal;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HistoricoRelatorio [disciplina=" + disciplina + ", mb1=" + mb1
				+ ", mb2=" + mb2 + ", mb3=" + mb3 + ", mb4=" + mb4
				+ ", horasAula=" + horasAula + ", frequencia=" + frequencia
				+ ", mediaFinal=" + mediaFinal + ", provaFinal=" + provaFinal
				+ ", status=" + status + "]";
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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

	/**
	 * @return the mediaPacial
	 */
	public String getMediaPacial() {
		return mediaPacial;
	}

	/**
	 * @param mediaPacial the mediaPacial to set
	 */
	public void setMediaPacial(String mediaPacial) {
		this.mediaPacial = mediaPacial;
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
	
	
	

}
