package br.mod.escolar.relatorios.composers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import br.mod.escolar.model.entities.Student;
import br.mod.escolar.model.util.GradesIdentifier;
import br.mod.escolar.model.util.RealPathApp;
import br.mod.escolar.relatorios.beans.StudentReportGeneralDataBean;



public class StudentBeanGeneralDataSourceComposer implements ComposerIF<Student, StudentReportGeneralDataBean>{

	/*
	 * Array que aramazena os dados do DataSource, Utiliza array por ser um
	 * requisito do JRDataSource.
	 */
	private ArrayList<StudentReportGeneralDataBean> beans;
    private String titleReport;

	
	/**
	 * 
	 * Cria uma nova instância desta classe
	 * 
	 */
	public  StudentBeanGeneralDataSourceComposer(String titleReport) {
		this.beans = new ArrayList<StudentReportGeneralDataBean>();
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
	public void addData(Student student) {
		createNewDataBean(student);
	}

	/**
	 * 
	 * Este método é responsável por adicionar entrada(s) ao dataSource iterando
	 * entre todos os cenários do referido cenário.
	 * 
	 * @param reservoirs
	 * @param sim
	 */
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
	public ArrayList<StudentReportGeneralDataBean> getBeansList() {
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

	
	private StudentReportGeneralDataBean createNewDataBeanS(Student student) {
		
		StudentReportGeneralDataBean dataBean = new StudentReportGeneralDataBean();
		
		
		dataBean.setRegistrationNumber(student.getRegistrationNumber()+"");
        dataBean.setTitle(this.titleReport);
		if(student.getGrade()!= null){
			//dataBean.setGrade(student.getGrade());
			dataBean.setGrade(GradesIdentifier.makeTitleToStudentsReport(student.getGrade(), student.getGrade2()));
		}
		
		if(student.getShift()!=null){
		   dataBean.setShift(student.getShift());
		}

		if(student.getGrade2()!=null){
			dataBean.setGrade2(student.getGrade2());
		}

		if(student.getClassId()!=null){
		    dataBean.setClassId(student.getClassId());
		}
		
		
		
		if(student.getName()!=null){
			dataBean.setName(student.getName());
		}
		
		
		
		if(student.getGender()!=null){
			dataBean.setGender(student.getGender());
		}
		
		if(student.getDddPhoneNumber()!=null){
			dataBean.setDddPhoneNumber(student.getDddPhoneNumber());
		}

		if(student.getPhoneNumber()!=null){
			dataBean.setPhoneNumber(student.getPhoneNumber());
		}
		
		
		if(student.getBirthday()!=null){
			dataBean.setBirthday(student.getBirthday().toString());
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
