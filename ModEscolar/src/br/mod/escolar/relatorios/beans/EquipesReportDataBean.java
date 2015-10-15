package br.mod.escolar.relatorios.beans;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;

public class EquipesReportDataBean {
	
	
	 private Image BANNER_LOGO;
	 
	 private String descricao;
	 
	 private String bimestre;
	 
	 private List<StudentEquipeReportDataBean> alunos;
	 
	 
	 public EquipesReportDataBean(){
		 
		 super();
		 
		 this.descricao = "-";
		 
		 this.bimestre = "-";
			
		 BANNER_LOGO = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
	 }

	/**
	 * @return the bANNER_LOGO
	 */
	public Image getBANNER_LOGO() {
		return BANNER_LOGO;
	}

	/**
	 * @param bANNER_LOGO the bANNER_LOGO to set
	 */
	public void setBANNER_LOGO(Image bANNER_LOGO) {
		BANNER_LOGO = bANNER_LOGO;
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
