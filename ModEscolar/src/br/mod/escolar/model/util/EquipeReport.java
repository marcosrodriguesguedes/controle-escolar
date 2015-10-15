package br.mod.escolar.model.util;

import java.util.ArrayList;
import java.util.List;

import br.mod.escolar.relatorios.beans.StudentEquipeReportDataBean;

public class EquipeReport {
	
      private String descricao;
	 
	  private String bimestre;
	  
	  private List<StudentEquipeReportDataBean> alunos;
	  
	  public EquipeReport(){
		  
		  alunos = new ArrayList<StudentEquipeReportDataBean>();
	  }

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the bimestre
	 */
	public String getBimestre() {
		return bimestre;
	}

	/**
	 * @param bimestre the bimestre to set
	 */
	public void setBimestre(String bimestre) {
		this.bimestre = bimestre;
	}

	/**
	 * @return the alunos
	 */
	public List<StudentEquipeReportDataBean> getAlunos() {
		return alunos;
	}

	/**
	 * @param alunos the alunos to set
	 */
	public void setAlunos(List<StudentEquipeReportDataBean> alunos) {
		this.alunos = alunos;
	}
	  
	
	  

}
