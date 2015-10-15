package br.mod.escolar.relatorios.composers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import br.mod.escolar.model.entities.Teacher;
import br.mod.escolar.model.util.DateFormater;
import br.mod.escolar.model.util.RealPathApp;
import br.mod.escolar.relatorios.beans.TeacherReportDataBean;



public class TeacherBeanDataSourceComposer implements ComposerIF <Teacher, TeacherReportDataBean >{
	private ArrayList<TeacherReportDataBean> beans;
    private String titleReport;

	
	/**
	 * 
	 * Cria uma nova instância desta classe
	 * 
	 */
	public TeacherBeanDataSourceComposer(String titleReport) {
		this.beans = new ArrayList<TeacherReportDataBean>();
		this.titleReport = titleReport;
	}


	public void addData(Teacher teacher) {
		createNewDataBean(teacher);
	}


	public void addData(List<Teacher> teacher) {
		for (Teacher t : teacher) {
			createNewDataBean(t);
		}
	}

	private void createNewDataBean(Teacher teacher) {
			beans.add(createNewDataBeanS(teacher)); 
	}

	/**
	 * 
	 * Este método é responsável por retornar uma lista contendo os itens do
	 * dataSource, chamadas ao Jasper deverão fazer .toArray() dest lista.
	 * 
	 * @return
	 */
	public ArrayList<TeacherReportDataBean> getBeansList() {
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

	
	private TeacherReportDataBean createNewDataBeanS(Teacher employee) {
		
		TeacherReportDataBean dataBean = new TeacherReportDataBean();
		
		dataBean.setTitle(this.titleReport);
		
		if(employee.getName()!=null){
			dataBean.setName(employee.getName());
		}
		
		if(employee.getNickName()!=null){
			dataBean.setNickName(employee.getNickName());
		}
		
		if(employee.getCpf()!=null){
			dataBean.setCpf(employee.getCpf());
		}
		
		if(employee.getDddPhoneNumber()!=null){
			dataBean.setDddPhoneNumber(employee.getDddPhoneNumber());
		}
		
		if(employee.getPhoneNumber()!=null){
			dataBean.setPhoneNumber(employee.getPhoneNumber());
			
		}
		
		if(employee.getDateOfAdmission()!=null){
			dataBean.setDateOfAdmission(DateFormater.formatadorDeDatas.format(employee.getDateOfAdmission()));
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
