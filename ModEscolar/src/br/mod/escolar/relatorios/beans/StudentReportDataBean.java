package br.mod.escolar.relatorios.beans;




import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class StudentReportDataBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String registrationNumber;
	private String grade;
	private String shift;
	private String grade2;
	private String classId;
	private String active;
	private String name;
	private String email;
	private String gender;
	private String addressLine;
	private String cep;
	private String district;
	private String city;
	private String state;
	private String dddPhoneNumber;
	private String dddMobilePhoneNumber;
	private String phoneNumber;
	private String mobilePhoneNumber;
	private String cpf;
	private String issuingDate;
	private String issuedBy;
	private String rg;
	private String birthday;
	private Image BANNER_LOGO;

	/**
	 * 
	 * Cria uma nova instância deste bean com todos os campos vazios.
	 * 
	 */
	public StudentReportDataBean() {
		registrationNumber = "-";
		grade = "-";
		shift = "-";
	    grade2= "-";
		classId= "-";
		active= "-";
		name= "-";
		email= "-";
		gender= "-";
		addressLine= "-";
		cep= "-";
		district= "-";
		city= "-";
		state= "-";
		dddPhoneNumber= "-";
		dddMobilePhoneNumber= "-";
		phoneNumber= "-";
		mobilePhoneNumber= "-";
		cpf= "-";
		issuingDate= "-";
		issuedBy= "-";
		rg= "-";
		birthday = "-";
		BANNER_LOGO = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
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

	public String getClassId() {
		return classId;
	}

	public Image getBANNER_LOGO() {
		return BANNER_LOGO;
	}

	public void setBANNER_LOGO(Image banner_logo) {
		BANNER_LOGO = banner_logo;
	}

	public String getActive() {
		return active;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getGender() {
		return gender;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public String getCep() {
		return cep;
	}

	public String getDistrict() {
		return district;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getDddPhoneNumber() {
		return dddPhoneNumber;
	}

	public String getDddMobilePhoneNumber() {
		return dddMobilePhoneNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	public String getCpf() {
		return cpf;
	}

	public String getIssuingDate() {
		return issuingDate;
	}

	public String getIssuedBy() {
		return issuedBy;
	}

	public String getRg() {
		return rg;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
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

	public void setClassId(String classId) {
		this.classId = classId;
	}

	

	public void setActive(String active) {
		this.active = active;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setDddPhoneNumber(String dddPhoneNumber) {
		this.dddPhoneNumber = dddPhoneNumber;
	}

	public void setDddMobilePhoneNumber(String dddMobilePhoneNumber) {
		this.dddMobilePhoneNumber = dddMobilePhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setIssuingDate(String issuingDate) {
		this.issuingDate = issuingDate;
	}

	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

}
