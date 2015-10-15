package br.mod.escolar.model.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.mod.escolar.model.exception.InvalidParameterException;

@Entity
@Table(name = "alunos")
public class Student extends Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer registrationNumber;
	private Integer parentalInfo;
	private String grade;
	private String shift;
	private String grade2;
	private String classId;
	private String pictureUrl;
	private String active;
	private Date enrollmentDate;
	private String scholarship;
	private String transportation;
	private String localization;
	private String deficiency;
	private String inep;
	private String periody;
	private String numberVoting;
	private String areaElection;
	private String section;
	private String numeroDeIrmaosEstudantes;
	private String numeroDeIrmaosEstudantesBolsistas;
	private String unidadeDeEnsinoOriundo;
	private String enderecoUnidadeDeEnsinoOriundo;
	private String periodoUnidadeDeEnsinoOriundo;
	private String anoUnidadeDeEnsinoOriundo;
	private String ufUnidadeDeEnsinoOriundo;
	private String nivelUnidadeDeEnsinoOriundo;
	private String telefoneUnidadeDeEnsinoOriundo;
	private String municipioUnidadeDeEnsinoOriundo;
	private String dadosEducacaoFisica;
	private String esporte;
	private String cultura;
	private String arte;
	private String outras;
	private String regiaoOndeReside;
	//private String bicletaComoTranporte;
	private String ctps;
	private String reservista;
	private String anoLetivo;
	private String estadoCivil;
	private boolean paiVivo;
	private boolean maeViva;
	private boolean paiNaoVivo;
	private boolean maeNaoViva;
	private String previdencia;
	private String profissao;
	private String endProfissao;
	private String municioProfissao;
	private String estadoProfissao;
	private String nomeResponsavel;
	private String endReponsavel;
	private String idadeReponsavel;
	private String estadoReponsavel;
	private String telefoneReponsavel;
	private String grauParentescoReponsavel;
	private String rendaFamiliarReponsavel;
	private String grauEstudoReponsavel;
	private String profissaoReponsavel;
	private String endProfissionalReponsavel;
	private String MunicipioReponsavel;
	private boolean rual;
	private boolean urbano;
	private boolean utilizaBicicleta;
	private boolean nutilizaBicicleta;
	private String nomePai;
	private String nomeMae;
	//private String parentyResponsability;
	private String formationScoreResponsability;
	private String parentyScoreResponsability;
	private String municipioReponsavel2;
	private boolean utilizaTransporteEscolar;
	private boolean nUtilizaTransporteEscolar;

	/**
	 * Construtor default para Student
	 */
	public Student() {

	}

	/**
	 * Construtor para Student
	 * 
	 * @param registrationNumber
	 * @param parentalInfo
	 * @param name
	 * @param gender
	 * @param addressLine
	 * @param number
	 * @param complement
	 * @param nationality
	 * @param birthplace
	 * @param cep
	 * @param district
	 * @param city
	 * @param state
	 * @param dddPhoneNumber
	 * @param dddMobilePhoneNumber
	 * @param phoneNumber
	 * @param mobilePhoneNumber
	 * @param email
	 * @param grade
	 * @param classId
	 * @param shift
	 * @param grade2
	 * @param pictureUrl
	 * @param active
	 * @param rg
	 * @param issuingDate
	 * @param issuedBy
	 * @param complementRg
	 * @param stateRg
	 * @param cpf
	 * @param birthday
	 * @param civilCertificate
	 * @param numberTerm
	 * @param sheet
	 * @param book
	 * @param dateIssueCertificate
	 * @param stateOfOffice
	 * @param nameOfOffice
	 * @param enrollmentDate
	 * @param scholarship
	 * @param discount
	 * @param startDisc
	 * @param endDisc
	 * @param language
	 * @param langClass
	 * @param race
	 * @param naturality
	 * @param education
	 * @param transportation
	 * @param localization
	 * @param deficiency
	 * @param inep
	 * @throws InvalidParameterException
	 */
	public Student(Integer registrationNumber, Integer parentalInfo,
			String name, String gender, String addressLine, String number,
			String complement, String nationality, String birthplace,
			String cep, String district, String city, String state,
			String dddPhoneNumber, String dddMobilePhoneNumber,
			String phoneNumber, String mobilePhoneNumber, String email,
			String grade, String classId, String shift, String grade2,
			String pictureUrl, String active, String rg, Date issuingDate,
			String issuedBy, String complementRg, String stateRg, String cpf,
			Date birthday, String civilCertificate, String numberTerm,
			String sheet, String book, Date dateIssueCertificate,
			String stateOfOffice, String nameOfOffice, Date enrollmentDate,
			String scholarship, Double discount, Date startDisc, Date endDisc,
			String language, String langClass, String race, String naturality,
			String education, String transportation, String localization,
			String deficiency, String inep) throws InvalidParameterException {

		super(name, email, gender, addressLine, cep, district, city, state,
				nationality, birthplace, dddPhoneNumber, dddMobilePhoneNumber,
				phoneNumber, mobilePhoneNumber, cpf, issuingDate, issuedBy, rg,
				complementRg, stateRg, birthday, civilCertificate, numberTerm,
				sheet, book, dateIssueCertificate, stateOfOffice, nameOfOffice,
				race, naturality);

		this.registrationNumber = registrationNumber;
		this.parentalInfo = parentalInfo;
		this.grade = grade;
		this.classId = classId;
		this.shift = shift;
		this.grade2 = grade2;
		this.pictureUrl = pictureUrl;
		this.active = active;

		this.transportation = transportation;
		this.localization = localization;
		this.deficiency = deficiency;
		this.inep = inep;

	}

	@Column(name = "inep")
	public String getInep() {
		return inep;
	}

	@Column(name = "periody")
	public String getPeriody() {
		return periody;
	}

	/**
	 * @return the estadoCivil
	 */
	public String getEstadoCivil() {
		return estadoCivil;
	}

	/**
	 * @param estadoCivil
	 *            the estadoCivil to set
	 */
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	/**
	 * @return the anoLetivo
	 */
	public String getAnoLetivo() {
		return anoLetivo;
	}

	/**
	 * @param anoLetivo
	 *            the anoLetivo to set
	 */
	public void setAnoLetivo(String anoLetivo) {
		this.anoLetivo = anoLetivo;
	}

	public void setPeriody(String periody) {
		this.periody = periody;
	}

	public void setInep(String inep) {
		this.inep = inep;
	}

	@Column(name = "infoPais")
	public Integer getParentalInfo() {
		return parentalInfo;
	}

	public void setParentalInfo(Integer parentalInfo) {
		this.parentalInfo = parentalInfo;
	}

	/**
	 * @return the paiVivo
	 */
	public boolean getPaiVivo() {
		return paiVivo;
	}

	/**
	 * @param paiVivo
	 *            the paiVivo to set
	 */
	public void setPaiVivo(boolean paiVivo) {
		this.paiVivo = paiVivo;
	}

	/**
	 * @return the maeViva
	 */
	public boolean getMaeViva() {
		return maeViva;
	}

	/**
	 * @return the previdencia
	 */
	public String getPrevidencia() {
		return previdencia;
	}

	/**
	 * @param previdencia
	 *            the previdencia to set
	 */
	public void setPrevidencia(String previdencia) {
		this.previdencia = previdencia;
	}

	/**
	 * @param maeViva
	 *            the maeViva to set
	 */
	public void setMaeViva(boolean maeViva) {
		this.maeViva = maeViva;
	}

	@Column(name = "transpPublico")
	public String getTransportation() {
		return transportation;
	}

	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}

	@Column(name = "localizacao")
	public String getLocalization() {
		return localization;
	}

	public void setLocalization(String localization) {
		this.localization = localization;
	}

	@Column(name = "deficiencia")
	public String getDeficiency() {
		return deficiency;
	}

	public void setDeficiency(String deficiency) {
		this.deficiency = deficiency;
	}

	@Column(name = "dataMatricula")
	public Date getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(Date enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}

	@Column(name = "bolsista")
	public String getScholarship() {
		return scholarship;
	}

	public void setScholarship(String scholarship) {
		this.scholarship = scholarship;
	}

	@Column(name = "matricula")
	public Integer getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(Integer registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	@Column(name = "serie")
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Column(name = "turma")
	public String getClassId() {
		return classId;
	}

	public void setClassId(String year) {
		this.classId = year;
	}

	@Column(name = "turno")
	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	@Column(name = "grau")
	public String getGrade2() {
		return grade2;
	}

	public void setGrade2(String grade2) {
		this.grade2 = grade2;
	}

	@Column(name = "urlImagem")
	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	@Column(name = "matriculado")
	public String getActive() {
		return active;
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

	public void setActive(String active) {
		this.active = active;
	}

	public String getNumeroDeIrmaosEstudantes() {
		return numeroDeIrmaosEstudantes;
	}

	public void setNumeroDeIrmaosEstudantes(String numeroDeIrmaosEstudantes) {
		this.numeroDeIrmaosEstudantes = numeroDeIrmaosEstudantes;
	}

	public String getNumeroDeIrmaosEstudantesBolsistas() {
		return numeroDeIrmaosEstudantesBolsistas;
	}

	public void setNumeroDeIrmaosEstudantesBolsistas(
			String numeroDeIrmaosEstudantesBolsistas) {
		this.numeroDeIrmaosEstudantesBolsistas = numeroDeIrmaosEstudantesBolsistas;
	}

	public String getUnidadeDeEnsinoOriundo() {
		return unidadeDeEnsinoOriundo;
	}

	public void setUnidadeDeEnsinoOriundo(String unidadeDeEnsinoOriundo) {
		this.unidadeDeEnsinoOriundo = unidadeDeEnsinoOriundo;
	}

	public String getEnderecoUnidadeDeEnsinoOriundo() {
		return enderecoUnidadeDeEnsinoOriundo;
	}

	public void setEnderecoUnidadeDeEnsinoOriundo(
			String enderecoUnidadeDeEnsinoOriundo) {
		this.enderecoUnidadeDeEnsinoOriundo = enderecoUnidadeDeEnsinoOriundo;
	}

	public String getPeriodoUnidadeDeEnsinoOriundo() {
		return periodoUnidadeDeEnsinoOriundo;
	}

	public void setPeriodoUnidadeDeEnsinoOriundo(
			String periodoUnidadeDeEnsinoOriundo) {
		this.periodoUnidadeDeEnsinoOriundo = periodoUnidadeDeEnsinoOriundo;
	}

	public String getAnoUnidadeDeEnsinoOriundo() {
		return anoUnidadeDeEnsinoOriundo;
	}

	public void setAnoUnidadeDeEnsinoOriundo(String anoUnidadeDeEnsinoOriundo) {
		this.anoUnidadeDeEnsinoOriundo = anoUnidadeDeEnsinoOriundo;
	}

	/**
	 * @return the paiNaoVivo
	 */
	public boolean getPaiNaoVivo() {
		return paiNaoVivo;
	}

	/**
	 * @param paiNaoVivo
	 *            the paiNaoVivo to set
	 */
	public void setPaiNaoVivo(boolean paiNaoVivo) {
		this.paiNaoVivo = paiNaoVivo;
	}

	/**
	 * @return the maeNaoViva
	 */
	public boolean getMaeNaoViva() {
		return maeNaoViva;
	}

	/**
	 * @param maeNaoViva
	 *            the maeNaoViva to set
	 */
	public void setMaeNaoViva(boolean maeNaoViva) {
		this.maeNaoViva = maeNaoViva;
	}

	public String getUfUnidadeDeEnsinoOriundo() {
		return ufUnidadeDeEnsinoOriundo;
	}

	public void setUfUnidadeDeEnsinoOriundo(String ufUnidadeDeEnsinoOriundo) {
		this.ufUnidadeDeEnsinoOriundo = ufUnidadeDeEnsinoOriundo;
	}

	public String getNivelUnidadeDeEnsinoOriundo() {
		return nivelUnidadeDeEnsinoOriundo;
	}

	public void setNivelUnidadeDeEnsinoOriundo(
			String nivelUnidadeDeEnsinoOriundo) {
		this.nivelUnidadeDeEnsinoOriundo = nivelUnidadeDeEnsinoOriundo;
	}

	public String getTelefoneUnidadeDeEnsinoOriundo() {
		return telefoneUnidadeDeEnsinoOriundo;
	}

	public void setTelefoneUnidadeDeEnsinoOriundo(
			String telefoneUnidadeDeEnsinoOriundo) {
		this.telefoneUnidadeDeEnsinoOriundo = telefoneUnidadeDeEnsinoOriundo;
	}

	public String getMunicipioUnidadeDeEnsinoOriundo() {
		return municipioUnidadeDeEnsinoOriundo;
	}

	public void setMunicipioUnidadeDeEnsinoOriundo(
			String municipioUnidadeDeEnsinoOriundo) {
		this.municipioUnidadeDeEnsinoOriundo = municipioUnidadeDeEnsinoOriundo;
	}

	public String getDadosEducacaoFisica() {
		return dadosEducacaoFisica;
	}

	public void setDadosEducacaoFisica(String dadosEducacaoFisica) {
		this.dadosEducacaoFisica = dadosEducacaoFisica;
	}

	public String getEsporte() {
		return esporte;
	}

	public void setEsporte(String esporte) {
		this.esporte = esporte;
	}

	public String getCultura() {
		return cultura;
	}

	public void setCultura(String cultura) {
		this.cultura = cultura;
	}

	public String getArte() {
		return arte;
	}

	public void setArte(String arte) {
		this.arte = arte;
	}

	public String getOutras() {
		return outras;
	}

	public void setOutras(String outras) {
		this.outras = outras;
	}

	public String getRegiaoOndeReside() {
		return regiaoOndeReside;
	}

	public void setRegiaoOndeReside(String regiaoOndeReside) {
		this.regiaoOndeReside = regiaoOndeReside;
	}

	public String getCtps() {
		return ctps;
	}

	public void setCtps(String ctps) {
		this.ctps = ctps;
	}

	public String getReservista() {
		return reservista;
	}

	public void setReservista(String reservista) {
		this.reservista = reservista;
	}

	/**
	 * @return the profissao
	 */
	public String getProfissao() {
		return profissao;
	}

	/**
	 * @param profissao
	 *            the profissao to set
	 */
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	/**
	 * @return the endProfissao
	 */
	public String getEndProfissao() {
		return endProfissao;
	}

	/**
	 * @param endProfissao
	 *            the endProfissao to set
	 */
	public void setEndProfissao(String endProfissao) {
		this.endProfissao = endProfissao;
	}

	/**
	 * @return the municioProfissao
	 */
	public String getMunicioProfissao() {
		return municioProfissao;
	}

	/**
	 * @param municioProfissao
	 *            the municioProfissao to set
	 */
	public void setMunicioProfissao(String municioProfissao) {
		this.municioProfissao = municioProfissao;
	}

	/**
	 * @return the estadoProfissao
	 */
	public String getEstadoProfissao() {
		return estadoProfissao;
	}

	/**
	 * @param estadoProfissao
	 *            the estadoProfissao to set
	 */
	public void setEstadoProfissao(String estadoProfissao) {
		this.estadoProfissao = estadoProfissao;
	}

	/**
	 * @return the nomeResponsavel
	 */
	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	/**
	 * @param nomeResponsavel
	 *            the nomeResponsavel to set
	 */
	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	/**
	 * @return the endReponsavel
	 */
	public String getEndReponsavel() {
		return endReponsavel;
	}

	/**
	 * @param endReponsavel
	 *            the endReponsavel to set
	 */
	public void setEndReponsavel(String endReponsavel) {
		this.endReponsavel = endReponsavel;
	}

	/**
	 * @return the idadeReponsavel
	 */
	public String getIdadeReponsavel() {
		return idadeReponsavel;
	}

	/**
	 * @param idadeReponsavel
	 *            the idadeReponsavel to set
	 */
	public void setIdadeReponsavel(String idadeReponsavel) {
		this.idadeReponsavel = idadeReponsavel;
	}

	/**
	 * @return the estadoReponsavel
	 */
	public String getEstadoReponsavel() {
		return estadoReponsavel;
	}

	/**
	 * @param estadoReponsavel
	 *            the estadoReponsavel to set
	 */
	public void setEstadoReponsavel(String estadoReponsavel) {
		this.estadoReponsavel = estadoReponsavel;
	}

	/**
	 * @return the telefoneReponsavel
	 */
	public String getTelefoneReponsavel() {
		return telefoneReponsavel;
	}

	/**
	 * @param telefoneReponsavel
	 *            the telefoneReponsavel to set
	 */
	public void setTelefoneReponsavel(String telefoneReponsavel) {
		this.telefoneReponsavel = telefoneReponsavel;
	}

	/**
	 * @return the grauParentescoReponsavel
	 */
	public String getGrauParentescoReponsavel() {
		return grauParentescoReponsavel;
	}

	/**
	 * @param grauParentescoReponsavel
	 *            the grauParentescoReponsavel to set
	 */
	public void setGrauParentescoReponsavel(String grauParentescoReponsavel) {
		this.grauParentescoReponsavel = grauParentescoReponsavel;
	}

	/**
	 * @return the rendaFamiliarReponsavel
	 */
	public String getRendaFamiliarReponsavel() {
		return rendaFamiliarReponsavel;
	}

	/**
	 * @param rendaFamiliarReponsavel
	 *            the rendaFamiliarReponsavel to set
	 */
	public void setRendaFamiliarReponsavel(String rendaFamiliarReponsavel) {
		this.rendaFamiliarReponsavel = rendaFamiliarReponsavel;
	}

	/**
	 * @return the grauEstudoReponsavel
	 */
	public String getGrauEstudoReponsavel() {
		return grauEstudoReponsavel;
	}

	/**
	 * @param grauEstudoReponsavel
	 *            the grauEstudoReponsavel to set
	 */
	public void setGrauEstudoReponsavel(String grauEstudoReponsavel) {
		this.grauEstudoReponsavel = grauEstudoReponsavel;
	}

	/**
	 * @return the profissaoReponsavel
	 */
	public String getProfissaoReponsavel() {
		return profissaoReponsavel;
	}

	/**
	 * @param profissaoReponsavel
	 *            the profissaoReponsavel to set
	 */
	public void setProfissaoReponsavel(String profissaoReponsavel) {
		this.profissaoReponsavel = profissaoReponsavel;
	}

	/**
	 * @return the endProfissionalReponsavel
	 */
	public String getEndProfissionalReponsavel() {
		return endProfissionalReponsavel;
	}

	/**
	 * @param endProfissionalReponsavel
	 *            the endProfissionalReponsavel to set
	 */
	public void setEndProfissionalReponsavel(String endProfissionalReponsavel) {
		this.endProfissionalReponsavel = endProfissionalReponsavel;
	}

	/**
	 * @return the municipioReponsavel
	 */
	public String getMunicipioPais() {
		return MunicipioReponsavel;
	}

	/**
	 * @param municipioReponsavel
	 *            the municipioReponsavel to set
	 */
	public void setMunicipioPais(String municipioReponsavel) {
		MunicipioReponsavel = municipioReponsavel;
	}
	
	public String getMunicipioReponsavel() {
		return municipioReponsavel2;
	}

	/**
	 * @param municipioReponsavel
	 *            the municipioReponsavel to set
	 */
	public void setMunicipioReponsavel(String municipioReponsavel2) {
		this.municipioReponsavel2 = municipioReponsavel2;
	}

	/**
	 * @return the rual
	 */
	public boolean getRual() {
		return rual;
	}

	/**
	 * @param rual
	 *            the rual to set
	 */
	public void setRual(boolean rual) {
		this.rual = rual;
	}

	/**
	 * @return the urbano
	 */
	public boolean getUrbano() {
		return urbano;
	}

	/**
	 * @param urbano
	 *            the urbano to set
	 */
	public void setUrbano(boolean urbano) {
		this.urbano = urbano;
	}

	public boolean getUtilizaBicicleta() {
		return utilizaBicicleta;
	}

	public void setUtilizaBicicleta(boolean utilizaBicicleta) {
		this.utilizaBicicleta = utilizaBicicleta;
	}

	public boolean getNutilizaBicicleta() {
		return nutilizaBicicleta;
	}

	public void setNutilizaBicicleta(boolean nutilizaBicicleta) {
		this.nutilizaBicicleta = nutilizaBicicleta;
	}

	public boolean getUtilizaTransporteEscolar() {
		return utilizaTransporteEscolar;
	}

	public void setUtilizaTransporteEscolar(boolean utilizaTransporteEscolar) {
		this.utilizaTransporteEscolar = utilizaTransporteEscolar;
	}

	public boolean getnUtilizaTransporteEscolar() {
		return nUtilizaTransporteEscolar;
	}

	public void setnUtilizaTransporteEscolar(boolean nUtilizaTransporteEscolar) {
		this.nUtilizaTransporteEscolar = nUtilizaTransporteEscolar;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String toString() {
		return String.valueOf(getId());
	}

	/**
	 * @return the nomePai
	 */
	public String getNomePai() {
		return nomePai;
	}

	/**
	 * @param nomePai
	 *            the nomePai to set
	 */
	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	/**
	 * @return the nomeMae
	 */
	public String getNomeMae() {
		return nomeMae;
	}

	/**
	 * @param nomeMae
	 *            the nomeMae to set
	 */
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}
	
    
    public String getFormationScoreResponsability() {
        return formationScoreResponsability;
    }

    public void setFormationScoreResponsability(
            String formationScoreResponsability) {
        this.formationScoreResponsability = formationScoreResponsability;
    }
    
    public String getParentyScoreResponsability() {
        return parentyScoreResponsability;
    }

    public void setParentyScoreResponsability(
            String parentyScoreResponsability) {
        this.parentyScoreResponsability = parentyScoreResponsability;
    }
}