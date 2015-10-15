package br.mod.escolar.model.entities;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class Person {

	private int id;
	private String name;
	private String email;
	private String gender;
	private String addressLine;
	private String number;
	private String complement;
	private String cep;
	private String district;
	private String city;
	private String state;
	private String nationality;
	private String homeland;
	private String dddPhoneNumber;
	private String dddMobilePhoneNumber;
	private String phoneNumber;
	private String mobilePhoneNumber;
	private String cpf;
	private Date issuingDate;
	private String issuedBy;
	private String rg;
	private String complementRg;
	private String stateRg;
	private Date birthday;
	private String race;
	private String civilCertificate;
	private String numberTerm;
	private String sheet;
	private String book;
	private Date dateIssueCertificate;
	private String stateOfTitulo;
	private String nameOfOffice;
	private String birthplace;

	/**
	 * Construtor default para Person
	 */
	public Person() {

	}

	/**
	 * Construtor para Person
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
	 * @param race
	 * @param birthplace
	 */
	public Person(String name, String email, String gender,
			String addressLine,  String cep, String district, String city,
			String state, String nationality,
			String homeland, String dddPhoneNumber,
			String dddMobilePhoneNumber, String phoneNumber,
			String mobilePhoneNumber, String cpf, Date issuingDate,
			String issuedBy, String rg, String complementRg,String stateRg, Date birthday,
			String civilCertificate, String numberTerm, String sheet,
			String book, Date dateIssueCertificate, String stateOfTitulo,
			String nameOfOffice,String race, String birthplace) {

		this.name = name;
		this.email = email;
		this.gender = gender;
		this.addressLine = addressLine;
		this.cep = cep;
		this.district = district;
		this.city = city;
		this.state = state;
		this.race = race;
		this.nationality = nationality;
		this.homeland = homeland;
		this.dddPhoneNumber = dddPhoneNumber;
		this.dddMobilePhoneNumber = dddMobilePhoneNumber;
		this.phoneNumber = phoneNumber;
		this.mobilePhoneNumber = mobilePhoneNumber;
		this.cpf = cpf;
		this.issuingDate = issuingDate;
		this.issuedBy = issuedBy;
		this.rg = rg;
		this.complementRg = complementRg;
		this.stateRg = stateRg;
		this.birthday = birthday;
		this.civilCertificate = civilCertificate;
		this.numberTerm = numberTerm;
		this.sheet = sheet;
		this.book = book;
		this.dateIssueCertificate = dateIssueCertificate;
		this.stateOfTitulo = stateOfTitulo;
		this.nameOfOffice = nameOfOffice;
		this.birthplace = birthplace;
	}

	@Id
	@GeneratedValue
	@Column(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "nome")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "sexo")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "endereco")
	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	@Column(name = "bairro")
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}
	@Column(name = "raça")
	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}


	@Column(name = "cidade")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "telefone")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "celular")
	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "cpf")
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Column(name = "rg")
	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	@Column(name = "cep")
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
	@Column(name = "dataNascimento")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "dddTelefone")
	public String getDddPhoneNumber() {
		return dddPhoneNumber;
	}

	public void setDddPhoneNumber(String dddPhoneNumber) {
		this.dddPhoneNumber = dddPhoneNumber;
	}

	@Column(name = "dddCelular")
	public String getDddMobilePhoneNumber() {
		return dddMobilePhoneNumber;
	}

	public void setDddMobilePhoneNumber(String dddMobilePhoneNumber) {
		this.dddMobilePhoneNumber = dddMobilePhoneNumber;
	}

	@Column(name = "dataEmissao")
	public Date getIssuingDate() {
		return issuingDate;
	}

	public void setIssuingDate(Date issuingDate) {
		this.issuingDate = issuingDate;
	}

	@Column(name = "orgaoEmissor")
	public String getIssuedBy() {
		return issuedBy;
	}

	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}

	@Column(name = "estado")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Column(name = "nacionalidade")
	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	@Column(name = "paísDeOrigem")
	public String getHomeland() {
		return homeland;
	}

	public void setHomeland(String homeland) {
		this.homeland = homeland;
	}
	
	@Column(name = "naturalidade")
	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	@Column(name = "complementoRG")
	public String getComplementRg() {
		return complementRg;
	}

	public void setComplementRg(String complementRg) {
		this.complementRg = complementRg;
	}
	
	@Column(name = "certidaoCivil")
	public String getCivilCertificate() {
		return civilCertificate;
	}

	public void setCivilCertificate(String civilCertificate) {
		this.civilCertificate = civilCertificate;
	}
	
	@Column(name = "numeroTermo")
	public String getNumberTerm() {
		return numberTerm;
	}

	public void setNumberTerm(String numberTerm) {
		this.numberTerm = numberTerm;
	}
	
	@Column(name = "folha")
	public String getSheet() {
		return sheet;
	}

	public void setSheet(String sheet) {
		this.sheet = sheet;
	}
	
	@Column(name = "livro")
	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}
	
	@Column(name = "dataEmissaoCertidao")
	public Date getDateIssueCertificate() {
		return dateIssueCertificate;
	}

	public void setDateIssueCertificate(Date dateIssueCertificate) {
		this.dateIssueCertificate = dateIssueCertificate;
	}
	
	@Column(name = "estadoDoTitulo")
	public String getStateOfTitulo() {
		return stateOfTitulo;
	}

	public void setStateOfTitulo(String stateOfTitulo) {
		this.stateOfTitulo = stateOfTitulo;
	}
	
	@Column(name = "nomeDoCartorio")
	public String getNameOfOffice() {
		return nameOfOffice;
	}

	public void setNameOfOffice(String nameOfOffice) {
		this.nameOfOffice = nameOfOffice;
	}
	
	@Column(name = "estadoDoRG")
	public String getStateRg() {
		return stateRg;
	}

	public void setStateRg(String ufRg) {
		this.stateRg = ufRg;
	}

	@Column(name = "numero")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Column(name = "complemento")
	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}
	
}