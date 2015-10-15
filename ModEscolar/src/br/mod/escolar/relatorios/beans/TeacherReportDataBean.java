package br.mod.escolar.relatorios.beans;


import java.awt.Image;
import java.awt.image.BufferedImage;

public class TeacherReportDataBean {

	private String title;
	//private String Id;
	private String name;
	private String nickName;
	private String cpf;
	private String dddPhoneNumber;
	private String phoneNumber;
	private String socialIdentificationNumber;
	private String DateOfAdmission;
	private Image BANNER_LOGO;
	
	public TeacherReportDataBean() {
		super();
		this.title = "-";
		//Id = "-";
		this.name = "-";
		this.nickName = "-";
		this.cpf = "-";
		this.dddPhoneNumber = "-";
		this.phoneNumber = "-";
		this.socialIdentificationNumber = "-";
		DateOfAdmission = "-";
		BANNER_LOGO = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
	}

	public String getTitle() {
		return title;
	}

//	public String getId() {
//		return Id;
//	}

	public String getName() {
		return name;
	}

	public String getNickName() {
		return nickName;
	}

	public String getCpf() {
		return cpf;
	}

	public String getDddPhoneNumber() {
		return dddPhoneNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getSocialIdentificationNumber() {
		return socialIdentificationNumber;
	}

	public String getDateOfAdmission() {
		return DateOfAdmission;
	}

	public void setTitle(String title) {
		this.title = title;
	}

//	public void setId(String id) {
//		Id = id;
//	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setDddPhoneNumber(String dddPhoneNumber) {
		this.dddPhoneNumber = dddPhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setSocialIdentificationNumber(String socialIdentificationNumber) {
		this.socialIdentificationNumber = socialIdentificationNumber;
	}

	public void setDateOfAdmission(String dateOfAdmission) {
		DateOfAdmission = dateOfAdmission;
	}
	
	public Image getBANNER_LOGO() {
		return BANNER_LOGO;
	}

	public void setBANNER_LOGO(Image banner_logo) {
		BANNER_LOGO = banner_logo;
	}
	
}
