package br.mod.escolar.relatorios.composers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import br.mod.escolar.model.entities.Discipline;
import br.mod.escolar.model.util.GradesIdentifier;
import br.mod.escolar.model.util.RealPathApp;
import br.mod.escolar.relatorios.beans.DisciplineReportDataBean;



public class DisciplineBeanDataSourceComposer implements ComposerIF<Discipline,DisciplineReportDataBean>{
	/*
	 * Array que aramazena os dados do DataSource, Utiliza array por ser um
	 * requisito do JRDataSource.
	 */
	private ArrayList<DisciplineReportDataBean> beans;
    private String titleReport;

	
	/**
	 * 
	 * Cria uma nova instância desta classe
	 * 
	 */
	public DisciplineBeanDataSourceComposer(String titleReport) {
		this.beans = new ArrayList<DisciplineReportDataBean>();
		this.titleReport = titleReport;
	}

	/**
	 * 
	 * Este método é responsável por adicionar entrada(s) ao dataSource iterando
	 * entre todos os cenários do referido cenário.
	 * 
	 * @param reservoir
	 *            O reservatório a ter seus dados inseridos no dataSource
	 * @param sim
	 *            a simulação a qual este reservatório está atrelado
	 */
	public void addData(Discipline discipline) {
		createNewDataBean(discipline);
	}

	/**
	 * 
	 * Este método é responsável por adicionar entrada(s) ao dataSource iterando
	 * entre todos os cenários do referido cenário.
	 * 
	 * @param reservoirs
	 * @param sim
	 */
	public void addData(List<Discipline> disciplines) {
		for (Discipline s : disciplines) {
			createNewDataBean(s);
		}
	}

	private void createNewDataBean(Discipline discipline) {
			beans.add(createNewDataBeanS(discipline)); 
	}

	/**
	 * 
	 * Este método é responsável por retornar uma lista contendo os itens do
	 * dataSource, chamadas ao Jasper deverão fazer .toArray() dest lista.
	 * 
	 * @return
	 */
	public ArrayList<DisciplineReportDataBean> getBeansList() {
		return beans;
	}

	/**
	 * 
	 * Este método é responsável por retornar um array para alimentar o data
	 * source do Jasper
	 * 
	 * @return
	 */
	public Object[] getBeansAsArray() {
		return beans.toArray();
	}

	
	private DisciplineReportDataBean createNewDataBeanS(Discipline discipline) {
		
		DisciplineReportDataBean dataBean = new DisciplineReportDataBean();
		
		dataBean.setTitle(this.titleReport);
		
		dataBean.setGrade(GradesIdentifier.makeTitleToDisciplinesReport(discipline.getGrade()+"", discipline.getGrade2()));
		
		if(discipline.getGrade2()!=null){
			dataBean.setGrade2(discipline.getGrade2());
		}

		if(discipline.getArea()!=null){
			dataBean.setArea(discipline.getArea());
		}
	
		
		if(discipline.getName()!=null){
			dataBean.setName(discipline.getName());
		}
		
		if(discipline.getHoursPerWeek()!=null){
			dataBean.setHoursPerWeek(discipline.getHoursPerWeek());
		}
		
		if(discipline.getHoursPerYear()!=null){
			dataBean.setHoursPerYear(discipline.getHoursPerYear());
		}
		String initialPath = RealPathApp.getPathApp();
		File bannerLogoPath = new File(initialPath + "resources/img/marca_transparente.png");
		try {
			dataBean.setBANNER_LOGO(ImageIO.read(bannerLogoPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		return dataBean;
	}
}
