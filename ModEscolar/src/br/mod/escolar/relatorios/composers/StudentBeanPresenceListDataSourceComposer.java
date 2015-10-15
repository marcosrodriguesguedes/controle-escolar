package br.mod.escolar.relatorios.composers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import br.mod.escolar.model.entities.Student;
import br.mod.escolar.model.util.RealPathApp;
import br.mod.escolar.relatorios.beans.StudentReportPresenceListDataBean;




public class StudentBeanPresenceListDataSourceComposer implements ComposerIF<Student, StudentReportPresenceListDataBean> {
	/*
	 * Array que aramazena os dados do DataSource, Utiliza array por ser um
	 * requisito do JRDataSource.
	 */
	private ArrayList<StudentReportPresenceListDataBean> beans;
    private String titleReport;

	
	/**
	 * 
	 * Cria uma nova instância desta classe
	 * 
	 */
	public  StudentBeanPresenceListDataSourceComposer(String titleReport) {
		this.beans = new ArrayList<StudentReportPresenceListDataBean>();
		this.titleReport = titleReport;
	}


	public void addData(Student student) {
		createNewDataBean(student);
	}


	public void addData(List<Student> students) {
		for (Student s : students) {
			createNewDataBean(s);
		}
	}

	private void createNewDataBean(Student student) {
			beans.add(createNewDataBeanS(student)); 
	}

	/**
	 * 
	 * Este método é responsável por retornar uma lista contendo os itens do
	 * dataSource, chamadas ao Jasper deverão fazer .toArray() dest lista.
	 * 
	 * @return
	 */
	public ArrayList<StudentReportPresenceListDataBean> getBeansList() {
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

	
	private StudentReportPresenceListDataBean createNewDataBeanS(Student student) {
		StudentReportPresenceListDataBean dataBean = new StudentReportPresenceListDataBean();
		dataBean.setRegistrationNumber(student.getRegistrationNumber()+"");
        dataBean.setTitle(this.titleReport);
		if(student.getName()!=null){
			dataBean.setName(student.getName());
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
