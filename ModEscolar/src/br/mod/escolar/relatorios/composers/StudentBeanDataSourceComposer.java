package br.mod.escolar.relatorios.composers;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import br.mod.escolar.model.entities.Student;
import br.mod.escolar.model.util.DateFormater;
import br.mod.escolar.model.util.GradesIdentifier;
import br.mod.escolar.model.util.RealPathApp;
import br.mod.escolar.relatorios.beans.StudentReportDataBean;




public class StudentBeanDataSourceComposer implements ComposerIF<Student, StudentReportDataBean> {

	/*
	 * Array que aramazena os dados do DataSource, Utiliza array por ser um
	 * requisito do JRDataSource.
	 */
	private ArrayList<StudentReportDataBean> beans;


	
	/**
	 * 
	 * Cria uma nova instância desta classe
	 * 
	 */
	public StudentBeanDataSourceComposer() {
		this.beans = new ArrayList<StudentReportDataBean>();
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
	public ArrayList<StudentReportDataBean> getBeansList() {
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

	
	private StudentReportDataBean createNewDataBeanS(Student student) {
		
		StudentReportDataBean dataBean = new StudentReportDataBean();
		
		
		dataBean.setRegistrationNumber(student.getRegistrationNumber()+"");

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
		
		if(student.getActive()!=null){
			dataBean.setActive(student.getActive());
		}
		
		if(student.getName()!=null){
			dataBean.setName(student.getName());
		}
		
		if(student.getEmail()!=null){
			dataBean.setEmail(student.getEmail());
		}
		
		if(student.getGender()!=null){
			dataBean.setGender(student.getGender());
		}
		
		if(student.getAddressLine()!=null){
			dataBean.setAddressLine(student.getAddressLine());
		}

		if(student.getCep()!=null){
		dataBean.setCep(student.getCep());
		}
		if(student.getDistrict()!=null){
			dataBean.setDistrict(student.getDistrict());
		}

		if(student.getCity()!=null){
			dataBean.setCity(student.getCity());
		}

		if(student.getState()!=null){
			dataBean.setState(student.getState());
		}
		
		if(student.getDddPhoneNumber()!=null){
			dataBean.setDddPhoneNumber(student.getDddPhoneNumber());
		}

		if(student.getDddMobilePhoneNumber()!=null){
			dataBean.setDddMobilePhoneNumber(student.getDddMobilePhoneNumber());
		}

		if(student.getPhoneNumber()!=null){
			dataBean.setPhoneNumber(student.getPhoneNumber());
		}
		
		if(student.getMobilePhoneNumber()!=null){
			dataBean.setMobilePhoneNumber(student.getMobilePhoneNumber());
		}
		
		if(student.getCpf()!=null){
			dataBean.setCpf(student.getCpf());
		}
		
		if(student.getIssuingDate()!=null){
			dataBean.setIssuingDate(student.getIssuingDate().toString());
		}
		if(student.getIssuedBy()!=null){
			dataBean.setIssuedBy(student.getIssuedBy());
		}
		
		if(student.getRg()!=null){
			dataBean.setRg(student.getRg());
		}
		
		if(student.getBirthday()!=null){
			dataBean.setBirthday(DateFormater.formatadorDeDatas.format(student.getBirthday()));
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
