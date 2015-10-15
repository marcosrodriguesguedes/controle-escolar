package br.mod.escolar.control;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.exception.ConstraintViolationException;

import br.mod.escolar.model.entities.Teacher;
import br.mod.escolar.model.exception.DBException;
import br.mod.escolar.model.exception.InvalidParameterException;
import br.mod.escolar.model.exception.NoSuchTeacherException;
import br.mod.escolar.model.util.HibernateUtil;
import br.mod.escolar.model.util.Messages;
import br.mod.escolar.model.util.Validator;

public class TeacherManager {

	public enum TeacherInfo {
		ID("idTeacher"), NAME("name"), EMAIL("email"), GENDER("gender"), ADDRESS_LINE(
				"addressLine"), DISTRICT("district"), STATE("state"), NATIONALITY(
				"nationality"), HOMELAND("homeland"), DDD_PHONE_NUMBER(
				"dddPhoneNumber"), DDD_MOBILE_PHONE_NUMBER(
				"dddMobilePhoneNumber"), CEP("cep"), ISSUING_DATE("issuingDate"), RG(
				"rg"), COMPLEMENT_RG("complementRg"), STATE_RG("stateRg"), BIRTH_DAY(
				"birthday"), CIVIL_CERTIFICATE("civilCertificate"), NUMBER_TERM(
				"numberTerm"), SHEET("sheet"), BOOK("book"), DATE_ISSUE_CERTIFICATE(
				"dateIssueCertificate"), STATE_OF_OFFICE("stateOfOffice"), NAME_OFFICE(
				"nameOfOffice"), CITY("city"), PHONE_NUMBER("phoneNumber"), MOBILE_PHONE_NUMBER(
				"mobilePhoneNumber"), SERIES("series"), MOTHER_NAME(
				"motherName"), DATE_OF_ADMISSION("dateOfAdimmision"), SOCIAL_IDENTIFICATION(
				"socialIdentification"), NICK_NAME("nickName"), NUMBER_CTPS(
				"numberCTPS"), GRADE_CTPS("gradeCTPS"), DATE_CTPS("dateCTPS"), STATE_CTPS(
				"stateCTPS"), NUMBER_VOTING("numberVoting"),  AREA_ELECTION("areaElection"), SECTION("section"), CPF(
				"cpf"), ISSUED_BY("issuedBy"), RACE("race"), BIRTHPLACE("birthplace"), ID_TEACHER("idTeacher");
		private String descricao;

		private TeacherInfo(final String descricao) {
			this.descricao = descricao;
		}

		public String getDescricao() {
			return descricao;
		}
	}

	private static TeacherManager manager = null;

	private TeacherManager() {

	}

	public static TeacherManager getInstance() {
		if (manager == null) {
			manager = new TeacherManager();
		}
		return manager;
	}

	public int createTeacher(Integer idTeacher, String name, String email, String gender,
			String addressLine, String cep, String district, String city,

			String state, String nationality,

			String homeland, String dddPhoneNumber,
			String dddMobilePhoneNumber, String phoneNumber,
			String mobilePhoneNumber, String cpf, Date issuingDate,
			String issuedBy, String rg, String complementRg, String stateRg,
			Date birthday, String civilCertificate, String numberTerm,
			String sheet, String book, Date dateIssueCertificate,
			String stateOfOffice, String nameOfOffice, String nickName,
			String motherName, String socialIdentificationNumber,
			Date dateOfAdmission, String numberCTPS, String gradeCTPS,
			Date dateCTPS, String stateCTPS, String numberVoting,
			String areaElection, String section, String race,String birthplace)
			throws InvalidParameterException {

		validate(name, email, gender, addressLine, cep, district, city, state,
				nationality, birthplace, cpf, rg, birthday, civilCertificate,
				motherName, dateOfAdmission, numberCTPS, gradeCTPS, dateCTPS,
				stateCTPS, numberVoting, areaElection, section, race);
		
		Teacher t = new Teacher(idTeacher, name, email, gender, addressLine, cep,
				district, city, state,  nationality, homeland,
				dddPhoneNumber, dddMobilePhoneNumber, phoneNumber,
				mobilePhoneNumber, cpf, issuingDate, issuedBy, rg,
				complementRg, stateRg, birthday, civilCertificate, numberTerm,
				sheet, book, dateIssueCertificate, stateOfOffice, nameOfOffice,
				nickName, motherName, socialIdentificationNumber,
				dateOfAdmission, numberCTPS, gradeCTPS, dateCTPS, stateCTPS,
				numberVoting, areaElection, section, race, birthplace);

		return (Integer) HibernateUtil.create(t);
	}
	
	public static void validate(String name, String email, String gender,
			String addressLine, String cep, String district, String city,
			String state, String nationality, String birthplace, String cpf,
			String rg, Date birthday, String civilCertificate,
			String motherName, Date dateOfAdmission, String numberCTPS,
			String gradeCTPS, Date dateCTPS, String stateCTPS,
			String numberVoting, String areaElection, String section,
			String race) throws InvalidParameterException {

		if (!Validator.isRGValid(rg))
			throw new InvalidParameterException(Messages.INVALID_RG);
		
		if (!Validator.isCPFValid(cpf))
			throw new InvalidParameterException(Messages.INVALID_CPF);
		
		if (!Validator.isStringValid(name))
			throw new InvalidParameterException(Messages.INVALID_NAME_TEACHER);
		if (!Validator.isStringValid(district))
			throw new InvalidParameterException(Messages.INVALID_DISTRICT);

		if (!Validator.isStringValid(birthplace))
			throw new InvalidParameterException(Messages.INVALID_BIRTH_PLACE);

		if (!Validator.isStringValid(state))
			throw new InvalidParameterException(Messages.INVALID_STATE);

		if (!Validator.isStringValid(civilCertificate))
			throw new InvalidParameterException(
					Messages.INVALID_CIVIL_CERTIFICATE);

		if (!Validator.isNumberValid(numberCTPS))
			throw new InvalidParameterException(Messages.INVALID_NUMBER_CTPS);

		if (!Validator.isNumberValid(gradeCTPS))
			throw new InvalidParameterException(Messages.INVALID_CTPS);

		if (!Validator.isStringValid(stateCTPS))
			throw new InvalidParameterException(Messages.INVALID_STATE);

		if (!Validator.isNumberValid(numberVoting))
			throw new InvalidParameterException(Messages.INVALID_VOTING);

		if (!Validator.isStringValid(section))
			throw new InvalidParameterException(Messages.INVALID_SECTION);

		if (!Validator.isStringValid(areaElection))
			throw new InvalidParameterException(Messages.INVALID_AREA_ELECTION);

		if (!Validator.isRaceValid(race))
			throw new InvalidParameterException(Messages.INVALID_RACE);

		if (!Validator.isStringValid(state))
			throw new InvalidParameterException(Messages.INVALID_STATE);

		if (!Validator.isStringValid(nationality))
			throw new InvalidParameterException(Messages.INVALID_NATIONALITY);

		if (!Validator.isCEPValid(cep))
			throw new InvalidParameterException(Messages.INVALID_CEP);
		if (!Validator.isEmailValid(email))
			throw new InvalidParameterException(Messages.INVALID_EMAIL);

		if (!Validator.isChoiceValid(gender, "m", "f"))
			throw new InvalidParameterException(Messages.INVALID_GENDER);

		if (!Validator.isStringValid(addressLine))
			throw new InvalidParameterException(Messages.INVALID_ADDRESS);

		if (!Validator.isStringValid(city))
			throw new InvalidParameterException(Messages.INVALID_CITY);
		
		if (!Validator.isStringValid(motherName))
			throw new InvalidParameterException(Messages.INVALID_MOTHER_NAME);
	}
	
	
	

	@SuppressWarnings("unchecked")
	private Teacher getTeacher(int id) throws NoSuchTeacherException {
		Collection<SimpleExpression> exps = new ArrayList<SimpleExpression>();
		exps.add(Restrictions.like("id", id));
		List<Teacher> teacher = null;
		try {
			teacher = (List<Teacher>) HibernateUtil.createQuery(Teacher.class,
					exps);
		} catch (ConstraintViolationException e) {
			throw new NoSuchTeacherException();
		}

		return teacher.isEmpty() ? null : teacher.get(0);
	}

	public String getTeacherInfo(int id, TeacherInfo info)
			throws NoSuchTeacherException {
		if (getTeacher(id) == null)
			throw new NoSuchTeacherException();
		switch (info) {
		case NAME:
			return getTeacher(id).getName();
		case DATE_OF_ADMISSION:
			return String.valueOf(getTeacher(id).getDateOfAdmission()
					.toString());
		case EMAIL:
			return getTeacher(id).getEmail();
		case GENDER:
			return getTeacher(id).getGender();
		case ADDRESS_LINE:
			return getTeacher(id).getAddressLine();
		case CEP:
			return getTeacher(id).getCep();
		case DISTRICT:
			return getTeacher(id).getDistrict();
		case CITY:
			return getTeacher(id).getCity();
		case STATE:
			return getTeacher(id).getState();
		case NATIONALITY:
			return getTeacher(id).getNationality();
		case HOMELAND:
			return getTeacher(id).getHomeland();
		case DDD_MOBILE_PHONE_NUMBER:
			return getTeacher(id).getDddMobilePhoneNumber();
		case DDD_PHONE_NUMBER:
			return getTeacher(id).getDddPhoneNumber();
		case MOBILE_PHONE_NUMBER:
			return getTeacher(id).getMobilePhoneNumber();
		case PHONE_NUMBER:
			return getTeacher(id).getPhoneNumber();
		case CPF:
			return getTeacher(id).getCpf();
		case ISSUING_DATE:
			return getTeacher(id).getIssuingDate().toString();
		case ISSUED_BY:
			return getTeacher(id).getIssuedBy();
		case RG:
			return getTeacher(id).getRg();
		case COMPLEMENT_RG:
			return getTeacher(id).getComplementRg();
		case STATE_RG:
			return Validator.formatDate(getTeacher(id).getIssuingDate());
		case BIRTH_DAY:
			return Validator.formatDate(getTeacher(id).getBirthday());
		case CIVIL_CERTIFICATE:
			return getTeacher(id).getCivilCertificate();
		case NUMBER_TERM:
			return getTeacher(id).getNumberTerm();
		case SHEET:
			return getTeacher(id).getSheet();
		case BOOK:
			return getTeacher(id).getBook();
		case DATE_ISSUE_CERTIFICATE:
			return Validator.formatDate(getTeacher(id).getDateIssueCertificate());
		case STATE_OF_OFFICE:
			return getTeacher(id).getStateOfTitulo();
		case NAME_OFFICE:
			return getTeacher(id).getNameOfOffice();
		case NICK_NAME:
			return getTeacher(id).getNickName();
		case MOTHER_NAME:
			return getTeacher(id).getMotherName();
		case SOCIAL_IDENTIFICATION:
			return getTeacher(id).getSocialIdentificationNumber();
		case STATE_CTPS:
			return getTeacher(id).getStateCTPS();
		case NUMBER_CTPS:
			return getTeacher(id).getNumberCTPS();
		case GRADE_CTPS:
			return getTeacher(id).getGradeCTPS();
		case NUMBER_VOTING:
			return getTeacher(id).getNumberVoting();
		case AREA_ELECTION:
			return getTeacher(id).getAreaElection();
		case SECTION:
			return getTeacher(id).getSection();
		case RACE:
			return getTeacher(id).getRace();
		case BIRTHPLACE:
			return getTeacher(id).getBirthplace();	
		default:
			return new String();
		}
	}

	/**
	 * Busca na base de dados o valor <code>value</code> segundo o critério de
	 * busca <code>crit</code>.
	 * @param crit
	 *            o critério de busca
	 * @param value
	 *            o valor a ser buscado
	 * @return
	 * @throws DBException
	 */
	@SuppressWarnings("unchecked")
	public List<Teacher> getTeacher(TeacherInfo info, Object value)
			throws DBException {
		Collection<SimpleExpression> exps = new ArrayList<SimpleExpression>();
		if (info.equals(TeacherInfo.NAME)) {
			exps.add(Restrictions.like(info.getDescricao(), (String) value,
					MatchMode.ANYWHERE));
		} else if (info.equals(TeacherInfo.ID)) {
			exps.add(Restrictions.like(info.getDescricao(), Integer
					.parseInt((String) value)));
		} else {
			exps.add(Restrictions.like(info.getDescricao(), value));
		}

		List<Teacher> teacher = null;
		try {
			teacher = (List<Teacher>) HibernateUtil.createQuery(Teacher.class,
					exps);
		} catch (ConstraintViolationException e) {
			throw new DBException(
					"A busca ocasionou uma violação nas regras de integridade do banco de dados.");
		}

		return teacher;
	}
	
	public Teacher removeTeacher(int id) throws NoSuchTeacherException {
		Teacher t = getTeacher(id);
		HibernateUtil.delete(t);
		return t;
	}

	public void updateTeacher(int id, String name, String email, String gender,
			String addressLine, String cep, String district, String city,
			String state,  
			String nationality, String homeland, String dddPhoneNumber,
			String dddMobilePhoneNumber, String phoneNumber,
			String mobilePhoneNumber, String cpf, Date issuingDate,
			String issuedBy, String rg, String complementRg, String stateRg,
			Date birthday, String civilCertificate, String numberTerm,
			String sheet, String book, Date dateIssueCertificate,
			String stateOfOffice, String nameOfOffice, String nickName,
			String motherName, String socialIdentificationNumber,
			Date dateOfAdmission, String numberCTPS, String gradeCTPS,
			Date dateCTPS, String stateCTPS, String numberVoting,
			String areaElection, String section, String race, String birthplace) throws NoSuchTeacherException,
			InvalidParameterException {
		Teacher t = getTeacher(id);
		if(t==null){
			throw new NoSuchTeacherException();
		}
		t.setName(name);
		t.setEmail(email);
		t.setGender(gender);
		t.setAddressLine(addressLine);
		t.setCep(cep);
		t.setDistrict(district);
		t.setCity(city);
		t.setState(state);
		t.setNationality(nationality);
		t.setHomeland(homeland);
		t.setDddPhoneNumber(dddPhoneNumber);
		t.setPhoneNumber(phoneNumber);
		t.setDddMobilePhoneNumber(dddMobilePhoneNumber);
		t.setMobilePhoneNumber(mobilePhoneNumber);
		t.setCpf(cpf);
		t.setIssuingDate(issuingDate);
		t.setIssuedBy(issuedBy);
		t.setRg(rg);
		t.setComplementRg(complementRg);
		t.setStateRg(stateRg);
		t.setBirthday(birthday);
		t.setCivilCertificate(civilCertificate);
		t.setNumberTerm(numberTerm);
		t.setSheet(sheet);
		t.setBook(book);
		t.setDateIssueCertificate(dateIssueCertificate);
		t.setStateOfTitulo(stateOfOffice);
		t.setNameOfOffice(nameOfOffice);
		t.setNickName(nickName);
		t.setMotherName(motherName);
		t.setDateOfAdmission(dateOfAdmission);
		t.setSocialIdentificationNumber(socialIdentificationNumber);
		t.setDateCTPS(dateCTPS);
		t.setStateCTPS(stateCTPS);
		t.setNumberCTPS(numberCTPS);
		t.setGradeCTPS(gradeCTPS);
		t.setNumberVoting(numberVoting);
		t.setAreaElection(areaElection);
		t.setSection(section);
		t.setRace(race);
		t.setBirthplace(birthplace);
		
		HibernateUtil.update(t);
	}
	
	@SuppressWarnings("unchecked")
	public List<Teacher> getAllTeachers() {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		List<Teacher> result = session.createSQLQuery(
				"select " + "{t.*} " + "from professores {t} " +
					" order by  {t}.nome;").addEntity("t", Teacher.class).list();
		session.getTransaction().commit();
		return result;
	}
}
