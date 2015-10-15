package br.mod.escolar.relatorios.beans;



import java.awt.Image;
import java.awt.image.BufferedImage;

public class StudentReportPresenceListDataBean {

	private String title;
	private String registrationNumber;
	private String name;
	private Image BANNER_LOGO;
	
	public StudentReportPresenceListDataBean() {
		super();
		this.title = "-";
		this.registrationNumber = "-";
		this.name = "-";
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

	public void setTitle(String title) {
		this.title = title;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public void setName(String name) {
		this.name = name;
	}
}
