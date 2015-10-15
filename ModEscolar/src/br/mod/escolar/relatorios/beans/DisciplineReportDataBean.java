package br.mod.escolar.relatorios.beans;


import java.awt.Image;
import java.awt.image.BufferedImage;

public class DisciplineReportDataBean {

	private String title;
	//private String dId;
	private String name;
	private String grade;
	private String grade2;
	private String area;
	private String hoursPerWeek;
	private String hoursPerYear;
	private Image BANNER_LOGO;

	public DisciplineReportDataBean() {
		super();
		this.title = "-";
		//this.dId = "-";
		this.name = "-";
		this.grade = "-";
		this.grade2 = "-";
		this.area = "-";
		this.hoursPerWeek = "-";
		this.hoursPerYear = "-";
		BANNER_LOGO = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
	}

//	public String getDId() {
//		return dId;
//	}
//
//	public void setDId(String id) {
//		dId = id;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public String getGrade() {
		return grade;
	}

	public String getGrade2() {
		return grade2;
	}


	public String getArea() {
		return area;
	}

	public String getHoursPerWeek() {
		return hoursPerWeek;
	}

	public String getHoursPerYear() {
		return hoursPerYear;
	}

	public Image getBANNER_LOGO() {
		return BANNER_LOGO;
	}

	public void setTitle(String title) {
		this.title = title;
	}



	public void setGrade(String grade) {
		this.grade = grade;
	}

	public void setGrade2(String grade2) {
		this.grade2 = grade2;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public void setHoursPerWeek(String hoursPerWeek) {
		this.hoursPerWeek = hoursPerWeek;
	}

	public void setHoursPerYear(String hoursPerYear) {
		this.hoursPerYear = hoursPerYear;
	}

	public void setBANNER_LOGO(Image banner_logo) {
		BANNER_LOGO = banner_logo;
	}
}
