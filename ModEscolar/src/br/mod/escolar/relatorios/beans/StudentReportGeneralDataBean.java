package br.mod.escolar.relatorios.beans;


import java.awt.Image;
import java.awt.image.BufferedImage;

public class StudentReportGeneralDataBean {

	
	private String title;
	private String registrationNumber;
	private String name;
	private String grade;
	private String shift;
	private String grade2;
	private String gender;
	private String dddPhoneNumber;
	private String phoneNumber;
	private String birthday;
	private String classId;
	private Image BANNER_LOGO;
	
	public StudentReportGeneralDataBean() {
		super();
		this.title = "-";
		this.registrationNumber = "-";
		this.name = "-";
		this.grade = "-";
		this.shift = "-";
		this.grade2 = "-";
		this.gender = "-";
		this.dddPhoneNumber = "-";
		this.phoneNumber = "-";
		this.birthday = "-";
		this.classId = "-";
		BANNER_LOGO = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
	}

	public Image getBANNER_LOGO() {
		return BANNER_LOGO;
	}

	public void setBANNER_LOGO(Image banner_logo) {
		BANNER_LOGO = banner_logo;
	}

	public String getTitle() {
		return title;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public String getName() {
		return name;
	}

	public String getGrade() {
		return grade;
	}

	public String getShift() {
		return shift;
	}

	public String getGrade2() {
		return grade2;
	}

	public String getGender() {
		return gender;
	}

	public String getDddPhoneNumber() {
		return dddPhoneNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getClassId() {
		return classId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public void setGrade2(String grade2) {
		this.grade2 = grade2;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setDddPhoneNumber(String dddPhoneNumber) {
		this.dddPhoneNumber = dddPhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}
	
	
	
}
