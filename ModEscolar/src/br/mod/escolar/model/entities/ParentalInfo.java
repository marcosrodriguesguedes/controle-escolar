package br.mod.escolar.model.entities;





import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "info_pais")
public class ParentalInfo {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	@Column(name = "nomeMae")
	private String motherName;
	@Column(name = "aniversarioMae")
	private Date motherBirthday;
	@Column(name = "cpfMae")
	private String motherCPF;
	@Column(name = "rgMae")
	private String motherRG;
	@Column(name = "dataRGMae")
	private Date motherIssuingDate;
	@Column(name = "emissorRGMae")
	private String motherIssuingOrg;
	@Column(name = "TrabalhoMae")
	private String motherJob;
	@Column(name = "localTrabalhoMae")
	private String motherWorkplace;
	@Column(name = "religiaoMae")
	private String motherReligion;
	@Column(name = "telDDDMae")
	private String motherTelephoneDDD;
	@Column(name = "telMae")
	private String motherTelephone;
	@Column(name = "mobDDDMae")
	private String motherMobileDDD;
	@Column(name = "mobMae")
	private String motherMobile;
	@Column(name = "emailMae")
	private String motherEmail;
	
	@Column(name = "nomePai")
	private String fatherName;
	@Column(name = "aniversarioPai")
	private Date fatherBirthday;
	@Column(name = "cpfPai")
	private String fatherCPF;
	@Column(name = "rgPai")
	private String fatherRG;
	@Column(name = "dataRGPai")
	private Date fatherIssuingDate;
	@Column(name = "emissorRGPai")
	private String fatherIssuingOrg;
	@Column(name = "TrabalhoPai")
	private String fatherJob;
	@Column(name = "localTrabalhoPai")
	private String fatherWorkplace;
	@Column(name = "religiaoPai")
	private String fatherReligion;
	@Column(name = "telDDDPai")
	private String fatherTelephoneDDD;
	@Column(name = "telPai")
	private String fatherTelephone;
	@Column(name = "mobDDDPai")
	private String fatherMobileDDD;
	@Column(name = "mobPai")
	private String fatherMobile;
	@Column(name = "emailPai")
	private String fatherEmail;
	
	
	/**
	 * Construtor default para ParentalInfo
	 */
	public ParentalInfo() {
		
	}
	
	/**
	 * Construtor para ParentalInfo
	 * @param motherName
	 * @param motherBirthday
	 * @param motherCPF
	 * @param motherRG
	 * @param motherIssuingDate
	 * @param motherIssuingOrg
	 * @param motherJob
	 * @param motherWorkplace
	 * @param motherReligion
	 * @param motherTelephoneDDD
	 * @param motherTelephone
	 * @param motherMobileDDD
	 * @param motherMobile
	 * @param motherEmail
	 * @param fatherName
	 * @param fatherBirthday
	 * @param fatherCPF
	 * @param fatherRG
	 * @param fatherIssuingDate
	 * @param fatherIssuingOrg
	 * @param fatherJob
	 * @param fatherWorkplace
	 * @param fatherReligion
	 * @param fatherTelephoneDDD
	 * @param fatherTelephone
	 * @param fatherMobileDDD
	 * @param fatherMobile
	 * @param fatherEmail
	 */
	public ParentalInfo(String motherName, Date motherBirthday,
			String motherCPF, String motherRG, Date motherIssuingDate,
			String motherIssuingOrg, String motherJob, String motherWorkplace,
			String motherReligion, String motherTelephoneDDD,
			String motherTelephone, String motherMobileDDD,
			String motherMobile, String motherEmail, String fatherName,
			Date fatherBirthday, String fatherCPF, String fatherRG,
			Date fatherIssuingDate, String fatherIssuingOrg, String fatherJob,
			String fatherWorkplace, String fatherReligion,
			String fatherTelephoneDDD, String fatherTelephone,
			String fatherMobileDDD, String fatherMobile, String fatherEmail) {
		this.motherName = motherName;
		this.motherBirthday = motherBirthday;
		this.motherCPF = motherCPF;
		this.motherRG = motherRG;
		this.motherIssuingDate = motherIssuingDate;
		this.motherIssuingOrg = motherIssuingOrg;
		this.motherJob = motherJob;
		this.motherWorkplace = motherWorkplace;
		this.motherReligion = motherReligion;
		this.motherTelephoneDDD = motherTelephoneDDD;
		this.motherTelephone = motherTelephone;
		this.motherMobileDDD = motherMobileDDD;
		this.motherMobile = motherMobile;
		this.motherEmail = motherEmail;
		this.fatherName = fatherName;
		this.fatherBirthday = fatherBirthday;
		this.fatherCPF = fatherCPF;
		this.fatherRG = fatherRG;
		this.fatherIssuingDate = fatherIssuingDate;
		this.fatherIssuingOrg = fatherIssuingOrg;
		this.fatherJob = fatherJob;
		this.fatherWorkplace = fatherWorkplace;
		this.fatherReligion = fatherReligion;
		this.fatherTelephoneDDD = fatherTelephoneDDD;
		this.fatherTelephone = fatherTelephone;
		this.fatherMobileDDD = fatherMobileDDD;
		this.fatherMobile = fatherMobile;
		this.fatherEmail = fatherEmail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public Date getMotherBirthday() {
		return motherBirthday;
	}

	public void setMotherBirthday(Date motherBirthday) {
		this.motherBirthday = motherBirthday;
	}

	public String getMotherCPF() {
		return motherCPF;
	}

	public void setMotherCPF(String motherCPF) {
		this.motherCPF = motherCPF;
	}

	public String getMotherRG() {
		return motherRG;
	}

	public void setMotherRG(String motherRG) {
		this.motherRG = motherRG;
	}

	public Date getMotherIssuingDate() {
		return motherIssuingDate;
	}

	public void setMotherIssuingDate(Date motherIssuingDate) {
		this.motherIssuingDate = motherIssuingDate;
	}

	public String getMotherIssuingOrg() {
		return motherIssuingOrg;
	}

	public void setMotherIssuingOrg(String motherIssuingOrg) {
		this.motherIssuingOrg = motherIssuingOrg;
	}

	public String getMotherJob() {
		return motherJob;
	}

	public void setMotherJob(String motherJob) {
		this.motherJob = motherJob;
	}

	public String getMotherWorkplace() {
		return motherWorkplace;
	}

	public void setMotherWorkplace(String motherWorkplace) {
		this.motherWorkplace = motherWorkplace;
	}

	public String getMotherReligion() {
		return motherReligion;
	}

	public void setMotherReligion(String motherReligion) {
		this.motherReligion = motherReligion;
	}

	public String getMotherTelephoneDDD() {
		return motherTelephoneDDD;
	}

	public void setMotherTelephoneDDD(String motherTelephoneDDD) {
		this.motherTelephoneDDD = motherTelephoneDDD;
	}

	public String getMotherTelephone() {
		return motherTelephone;
	}

	public void setMotherTelephone(String motherTelephone) {
		this.motherTelephone = motherTelephone;
	}

	public String getMotherMobileDDD() {
		return motherMobileDDD;
	}

	public void setMotherMobileDDD(String motherMobileDDD) {
		this.motherMobileDDD = motherMobileDDD;
	}

	public String getMotherMobile() {
		return motherMobile;
	}

	public void setMotherMobile(String motherMobile) {
		this.motherMobile = motherMobile;
	}

	public String getMotherEmail() {
		return motherEmail;
	}

	public void setMotherEmail(String motherEmail) {
		this.motherEmail = motherEmail;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public Date getFatherBirthday() {
		return fatherBirthday;
	}

	public void setFatherBirthday(Date fatherBirthday) {
		this.fatherBirthday = fatherBirthday;
	}

	public String getFatherCPF() {
		return fatherCPF;
	}

	public void setFatherCPF(String fatherCPF) {
		this.fatherCPF = fatherCPF;
	}

	public String getFatherRG() {
		return fatherRG;
	}

	public void setFatherRG(String fatherRG) {
		this.fatherRG = fatherRG;
	}

	public Date getFatherIssuingDate() {
		return fatherIssuingDate;
	}

	public void setFatherIssuingDate(Date fatherIssuingDate) {
		this.fatherIssuingDate = fatherIssuingDate;
	}

	public String getFatherIssuingOrg() {
		return fatherIssuingOrg;
	}

	public void setFatherIssuingOrg(String fatherIssuingOrg) {
		this.fatherIssuingOrg = fatherIssuingOrg;
	}

	public String getFatherJob() {
		return fatherJob;
	}

	public void setFatherJob(String fatherJob) {
		this.fatherJob = fatherJob;
	}

	public String getFatherWorkplace() {
		return fatherWorkplace;
	}

	public void setFatherWorkplace(String fatherWorkplace) {
		this.fatherWorkplace = fatherWorkplace;
	}

	public String getFatherReligion() {
		return fatherReligion;
	}

	public void setFatherReligion(String fatherReligion) {
		this.fatherReligion = fatherReligion;
	}

	public String getFatherTelephoneDDD() {
		return fatherTelephoneDDD;
	}

	public void setFatherTelephoneDDD(String fatherTelephoneDDD) {
		this.fatherTelephoneDDD = fatherTelephoneDDD;
	}

	public String getFatherTelephone() {
		return fatherTelephone;
	}

	public void setFatherTelephone(String fatherTelephone) {
		this.fatherTelephone = fatherTelephone;
	}

	public String getFatherMobileDDD() {
		return fatherMobileDDD;
	}

	public void setFatherMobileDDD(String fatherMobileDDD) {
		this.fatherMobileDDD = fatherMobileDDD;
	}

	public String getFatherMobile() {
		return fatherMobile;
	}

	public void setFatherMobile(String fatherMobile) {
		this.fatherMobile = fatherMobile;
	}

	public String getFatherEmail() {
		return fatherEmail;
	}

	public void setFatherEmail(String fatherEmail) {
		this.fatherEmail = fatherEmail;
	}
}
