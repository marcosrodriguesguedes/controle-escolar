package br.mod.escolar.model.entities;




import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.mod.escolar.model.exception.InvalidParameterException;

@Entity
@Table(name = "professores")
public class Teacher extends Person {

	private Integer idTeacher;
	private String nickName;
	private String motherName;
	private String socialIdentificationNumber;
	private Date dateOfAdmission;
	private String numberCTPS;
	private String gradeCTPS;
	private Date dateCTPS;
	private String stateCTPS;
	private String numberVoting;
	private String areaElection;
	private String section;
	private String nomeFather;
	private String pis_pasep;
	private String banco;
	private String agencia;
	private String conta;
    private String lotacao;
    private String cidadefuncional;
    private Date dataContrato;
	 

	/**
	 * Construtor default para Teacher
	 */
	public Teacher() {
	}

	/**
	 * Construtor para Teacher
	 * 
	 * @param idTeacher
	 * @param name
	 * @param email
	 * @param gender
	 * @param addressLine
	 * @param cep
	 * @param district
	 * @param city
	 * @param state
	 * @param nationality
	 * @param homeland
	 * @param dddPhoneNumber
	 * @param dddMobilePhoneNumber
	 * @param phoneNumber
	 * @param mobilePhoneNumber
	 * @param cpf
	 * @param issuingDate
	 * @param issuedBy
	 * @param rg
	 * @param complementRg
	 * @param stateRg
	 * @param birthday
	 * @param civilCertificate
	 * @param numberTerm
	 * @param sheet
	 * @param book
	 * @param dateIssueCertificate
	 * @param stateOfOffice
	 * @param nameOfOffice
	 * @param nickName
	 * @param motherName
	 * @param socialIdentificationNumber
	 * @param dateOfAdmission
	 * @param numberCTPS
	 * @param gradeCTPS
	 * @param dateCTPS
	 * @param stateCTPS
	 * @param numberVoting
	 * @param areaElection
	 * @param section
	 * @param race
	 * @param birthplace
	 * @throws InvalidParameterException
	 */
	public Teacher(Integer idTeacher, String name, String email, String gender,
			String addressLine, String cep, String district, String city,
			String state, String nationality, String homeland,
			String dddPhoneNumber, String dddMobilePhoneNumber,
			String phoneNumber, String mobilePhoneNumber, String cpf,
			Date issuingDate, String issuedBy, String rg, String complementRg,
			String stateRg, Date birthday, String civilCertificate,
			String numberTerm, String sheet, String book,
			Date dateIssueCertificate, String stateOfOffice,
			String nameOfOffice, String nickName, String motherName,
			String socialIdentificationNumber, Date dateOfAdmission,
			String numberCTPS, String gradeCTPS, Date dateCTPS,
			String stateCTPS, String numberVoting, String areaElection,
			String section, String race, String birthplace)
			throws InvalidParameterException {

		super(name, email, gender, addressLine, cep, district, city, state,
				nationality, homeland, dddPhoneNumber, dddMobilePhoneNumber,
				phoneNumber, mobilePhoneNumber, cpf, issuingDate, issuedBy, rg,
				complementRg, stateRg, birthday, civilCertificate, numberTerm,
				sheet, book, dateIssueCertificate, stateOfOffice, nameOfOffice,
				race, birthplace);

		this.idTeacher = idTeacher;
		this.nickName = nickName;
		this.motherName = motherName;
		this.socialIdentificationNumber = socialIdentificationNumber;
		this.dateOfAdmission = dateOfAdmission;
		this.numberVoting = numberVoting;
		this.numberCTPS = numberCTPS;
		this.dateCTPS = dateCTPS;
		this.gradeCTPS = gradeCTPS;
		this.stateCTPS = stateCTPS;
		this.areaElection = areaElection;
		this.section = section;

	}

	@Column(name = "matricula")
	public Integer getIdTeacher() {
		return idTeacher;
	}

	public void setIdTeacher(Integer idTeacher) {
		this.idTeacher = idTeacher;
	}

	@Column(name = "apelido")
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Column(name = "dataAdmissao")
	public Date getDateOfAdmission() {
		return dateOfAdmission;
	}

	public void setDateOfAdmission(Date dateOfAdmission) {
		this.dateOfAdmission = dateOfAdmission;
	}

	@Column(name = "identificacaoSocial")
	public String getSocialIdentificationNumber() {
		return socialIdentificationNumber;
	}

	@Column(name = "numeroCTPS")
	public String getNumberCTPS() {
		return numberCTPS;
	}

	public void setNumberCTPS(String numberCTPS) {
		this.numberCTPS = numberCTPS;
	}

	@Column(name = "serieCTPS")
	public String getGradeCTPS() {
		return gradeCTPS;
	}

	public void setGradeCTPS(String gradeCTPS) {
		this.gradeCTPS = gradeCTPS;
	}

	@Column(name = "dataCTPS")
	public Date getDateCTPS() {
		return dateCTPS;
	}

	public void setDateCTPS(Date dateCTPS) {
		this.dateCTPS = dateCTPS;
	}

	@Column(name = "ufCTPS")
	public String getStateCTPS() {
		return stateCTPS;
	}

	public void setStateCTPS(String stateCTPS) {
		this.stateCTPS = stateCTPS;
	}

	@Column(name = "tituloEleitor")
	public String getNumberVoting() {
		return numberVoting;
	}

	public void setNumberVoting(String numberVoting) {
		this.numberVoting = numberVoting;
	}

	@Column(name = "zonaEleitoral")
	public String getAreaElection() {
		return areaElection;
	}

	public void setAreaElection(String areaElection) {
		this.areaElection = areaElection;
	}

	@Column(name = "secaoEleitoral")
	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public void setSocialIdentificationNumber(String socialIdentificationNumber) {
		this.socialIdentificationNumber = socialIdentificationNumber;
	}

	@Column(name = "nomeDaMae")
	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getNomeFather() {
		return nomeFather;
	}

	public void setNomeFather(String nomeFather) {
		this.nomeFather = nomeFather;
	}

	public String getPis_pasep() {
		return pis_pasep;
	}

	public void setPis_pasep(String pis_pasep) {
		this.pis_pasep = pis_pasep;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	
	public String getLotacao() {
		return lotacao;
	}

	public void setLotacao(String lotacao) {
		this.lotacao = lotacao;
	}

	public String getCidadefuncional() {
		return cidadefuncional;
	}

	public void setCidadefuncional(String cidadefuncional) {
		this.cidadefuncional = cidadefuncional;
	}

	public Date getDataContrato() {
		return dataContrato;
	}

	public void setDataContrato(Date dataContrato) {
		this.dataContrato = dataContrato;
	}
	
	

}