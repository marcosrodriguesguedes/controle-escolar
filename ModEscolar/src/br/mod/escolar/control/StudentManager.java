package br.mod.escolar.control;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.exception.ConstraintViolationException;

import br.mod.escolar.model.entities.Student;
import br.mod.escolar.model.exception.DBException;
import br.mod.escolar.model.exception.InvalidParameterException;
import br.mod.escolar.model.exception.NoSuchDisciplineException;
import br.mod.escolar.model.exception.NoSuchStudentException;
import br.mod.escolar.model.util.HibernateUtil;
import br.mod.escolar.model.util.Messages;
import br.mod.escolar.model.util.Validator;

public class StudentManager {

	public enum StudentInfo {
		REGISTRATION_NUMBER("registrationNumber"), NAME("name"), GENDER(
				"gender"), ADDRESS_LINE("addressLine"), CEP("cep"), DISTRICT(
				"district"), CITY("city"), PHONE_NUMBER("phoneNumber"), MOBILE_PHONE_NUMBER(
				"mobilePhoneNumber"), EMAIL("email"), GRADE("grade"), CLASS_ID(
				"classId"), SHIFT("shift"), GRADE_2("grade2"), PICTURE_URL(
				"urlpictureUrl"), ACTIVE("active"), RG("rg"), CPF("cpf"), BIRTHDAY(

		"birthday"), NATIONALITY("nationality"), PARENTAL_INFO("parentalInfo"), INEP("inep"), ID("id");

		private String description;

		private StudentInfo(final String description) {
			this.description = description;
		}

		public String getDescription() {
			return description;
		}
	}

	private static StudentManager manager = null;

	/**
	 * Construtor defafult privado para StudentManager
	 */
	private StudentManager() {
	}

	/**
	 * Padrão Singleton
	 * 
	 */
	public static synchronized StudentManager getInstance() {
		if (manager == null) {
			manager = new StudentManager();
		}
		return manager;
	}

	/**
	 * Este método faz a validação dos parâmetros abaixo
	 * 
	 * @param registrationNumber
	 * @param name
	 * @param gender
	 * @param addressLine
	 * @param number
	 * @param complement
	 * @param nationality
	 * @param homeland
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
	 * @param race
	 * @param education
	 * @param transportation
	 * @param localization
	 * @param deficiency
	 * @throws InvalidParameterException
	 * @throws DBException
	 */
	public void validate(String registrationNumber, String name, String gender,
			String addressLine, String number, String complement,
			String nationality, String homeland, String cep, String district,
			String city, String state, String dddPhoneNumber,
			String dddMobilePhoneNumber, String phoneNumber,
			String mobilePhoneNumber, String email, String grade,
			String classId, String shift, String grade2, String pictureUrl,
			String active, String rg, String issuingDate, String issuedBy,
			String complementRg, String stateRg, String cpf, String birthday,
			String civilCertificate, String numberTerm, String sheet,
			String book, String dateIssueCertificate, String stateOfOffice,
			String nameOfOffice, String enrollmentDate, String scholarship,
			String discount, String startDisc, String endDisc, String race,
			String education, String transportation, String localization,
			String deficiency) throws InvalidParameterException, DBException {
		// langClass, language
		if (!Validator.isNumberValid(registrationNumber))
			throw new InvalidParameterException(
					Messages.INVALID_REGISTRATION_NUMBER);

		if (!Validator.isStringValid(name))
			throw new InvalidParameterException(Messages.INVALID_NAME_STUDENT);

		if (!Validator.isChoiceValid(gender, "m", "f"))
			throw new InvalidParameterException(Messages.INVALID_GENDER_STUDENT);

		if (!Validator.isStringValid(addressLine))
			throw new InvalidParameterException(
					Messages.INVALID_ADDRESS_STUDENT);

		if (!Validator.isNumberValid(number))
			throw new InvalidParameterException(Messages.INVALID_ADDRESS_NUMBER);

		if (!Validator.isStringValid(nationality))
			throw new InvalidParameterException(Messages.INVALID_NATIONALITY);

		if (!Validator.isStringValid(homeland))
			throw new InvalidParameterException(Messages.INVALID_HOMELAND);

		if (!Validator.isCEPValid(cep))
			throw new InvalidParameterException(Messages.INVALID_CEP);

		if (!Validator.isStringValid(district))
			throw new InvalidParameterException(
					Messages.INVALID_DISTRICT_STUDENT);

		if (!Validator.isStringValid(city))
			throw new InvalidParameterException(Messages.INVALID_CITY_STUDENT);

		if (!Validator.isDDDValid(dddPhoneNumber))
			throw new InvalidParameterException(Messages.INVALID_TEL_DDD);

		if (!Validator.isPhoneNumberValid(phoneNumber))
			throw new InvalidParameterException(Messages.INVALID_PHONE);

		if (!(Validator.isDDDValid(dddMobilePhoneNumber) || dddMobilePhoneNumber
				.isEmpty()))
			throw new InvalidParameterException(Messages.INVALID_MOB_DDD);

		if (!(Validator.isPhoneNumberValid(mobilePhoneNumber) || mobilePhoneNumber
				.isEmpty()))
			throw new InvalidParameterException(Messages.INVALID_MOBILE_PHONE);

		if (!Validator.isEmailValid(email))
			throw new InvalidParameterException(Messages.INVALID_EMAIL);

		if (!Validator.isClassIdValid(classId))
			throw new InvalidParameterException(Messages.INVALID_CLASS_ID);

		if (!Validator.isStringValid(grade2))
			throw new InvalidParameterException(Messages.INVALID_GRADE2);

		if (!Validator.isURLValid(pictureUrl))
			throw new InvalidParameterException(Messages.INVALID_PICTURE_URL);

		if (!Validator.isDateValid(birthday))
			throw new InvalidParameterException(Messages.INVALID_DATE
					+ "Data de nascimento");

		if (!Validator.isChoiceValid(active, "Sim", "Não"))
			throw new InvalidParameterException(Messages.INVALID_ACTIVE);

		if (!(Validator.isCPFValid(cpf) || cpf.isEmpty()))
			throw new InvalidParameterException(Messages.INVALID_CPF);

		if (!(Validator.isRGValid(rg) || rg.isEmpty()))
			throw new InvalidParameterException(Messages.INVALID_RG);

		if (!(Validator.isDateValid(issuingDate) || issuingDate.isEmpty()))
			throw new InvalidParameterException(Messages.INVALID_DATE
					+ "Data de expedição");

		if (!(Validator.isStringValid(stateRg) || stateRg.isEmpty()))
			throw new InvalidParameterException(Messages.INVALID_ISSUED_BY);

		if (!discount.equals("0"))
			if (!Validator.isNumberValid(discount))
				throw new InvalidParameterException(Messages.INVALID_DISCOUNT);

		if (!(Validator.isDateValid(startDisc) || startDisc.isEmpty()))
			throw new InvalidParameterException(Messages.INVALID_DATE
					+ " Início do desconto");

		if (!(Validator.isDateValid(endDisc) || endDisc.isEmpty()))
			throw new InvalidParameterException(Messages.INVALID_DATE
					+ " Fim do desconto");

		if (!Validator.isChoiceValid(scholarship, "Sim", "Não"))
			throw new InvalidParameterException(Messages.INVALID_FIELD
					+ "Bolsista?");

		if (!Validator.isChoiceValid(deficiency, "Sim", "Não"))
			throw new InvalidParameterException(Messages.INVALID_FIELD
					+ "Possui deficiência?");

		if (!Validator.isChoiceValid(transportation, "Sim", "Não"))
			throw new InvalidParameterException(Messages.INVALID_FIELD
					+ "Utiliza transporte público?");
	}

	public int newStudent(Student s) {
		return (Integer) HibernateUtil.create(s);
	}
	/**
	 * Este método cria um objeto Student
	 * 
	 * @param registrationNumber
	 * @param parentalInfo
	 * @param name
	 * @param gender
	 * @param addressLine
	 * @param number
	 * @param complement
	 * @param nationality
	 * @param homeland
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
	 * @param birthplace
	 * @param education
	 * @param transportation
	 * @param localization
	 * @param deficiency
	 * @return identificador gerado pelo sistema gerenciador do banco dedados
	 *         que representa a entidade no sistema
	 * @throws DBException
	 * @throws NoSuchStudentException
	 * @throws InvalidParameterException
	 */
	public int createStudent(String registrationNumber, String parentalInfo,
			String name, String gender, String addressLine, String number,
			String complement, String nationality, String homeland, String cep,
			String district, String city, String state, String dddPhoneNumber,
			String dddMobilePhoneNumber, String phoneNumber,
			String mobilePhoneNumber, String email, String grade,
			String classId, String shift, String grade2, String pictureUrl,
			String active, String rg, String issuingDate, String issuedBy,
			String complementRg, String stateRg, String cpf, String birthday,
			String civilCertificate, String numberTerm, String sheet,
			String book, String dateIssueCertificate, String stateOfOffice,
			String nameOfOffice, String enrollmentDate, String scholarship,
			String discount, String startDisc, String endDisc, String language,
			String langClass, String race, String birthplace, String education,
			String transportation, String localization, String deficiency, String inep)
			throws DBException, NoSuchStudentException,
			InvalidParameterException {
		if (Validator.isNumberValid(registrationNumber)) {
			if (!getStudents(StudentInfo.REGISTRATION_NUMBER,
					String.valueOf(registrationNumber)).isEmpty())
				throw new InvalidParameterException(
						Messages.UNAVAILABLE_REGISTRATION_NUMBER);
		} else
			throw new InvalidParameterException(
					Messages.INVALID_REGISTRATION_NUMBER);

		validate(registrationNumber, name, gender, addressLine, number,
				complement, nationality, birthplace, cep, district, city,
				state, dddPhoneNumber, dddMobilePhoneNumber, phoneNumber,
				mobilePhoneNumber, email, grade, classId, shift, grade2,
				pictureUrl, active, rg, issuingDate, issuedBy, complementRg,
				stateRg, cpf, birthday, civilCertificate, numberTerm, sheet,
				book, dateIssueCertificate, stateOfOffice, nameOfOffice,
				enrollmentDate, scholarship, discount, startDisc, endDisc,
				race, education, transportation, localization, deficiency);

		Student s = new Student(Integer.parseInt(registrationNumber), Integer
				.parseInt(parentalInfo), name, gender, addressLine, number,
				complement, nationality, homeland, cep, district, city, state,
				dddPhoneNumber, dddMobilePhoneNumber, phoneNumber,
				mobilePhoneNumber, email, grade, classId, shift, grade2,
				pictureUrl, active, rg, Validator.generateDate(issuingDate),
				issuedBy, complementRg, stateRg, cpf, Validator
						.generateDate(birthday), civilCertificate, numberTerm,
				sheet, book, Validator.generateDate(dateIssueCertificate),
				stateOfOffice, nameOfOffice, Validator
						.generateDate(enrollmentDate), scholarship, Double
						.parseDouble(discount), Validator
						.generateDate(startDisc), Validator
						.generateDate(endDisc), language, langClass, race,
				birthplace, education, transportation, localization, deficiency, inep);

		return (Integer) HibernateUtil.create(s);
	}

	public void advanceGrade(List<Student> students)
			throws InvalidParameterException {
		String gradeStr = new String();
		int grade = 0;
		for (Iterator<Student> iterator = students.iterator(); iterator.hasNext();) {
			Student student = (Student) iterator.next();
			gradeStr = student.getGrade();
			if (gradeStr.length() == 3)
				throw new InvalidParameterException(
						"Foi encontrado um aluno com o formato do campo Serie inconsistente. "
								+ "Verifique se todos os alunos tem o campo Serie no formato ");
			grade = Integer.parseInt(gradeStr.charAt(0) + "");
			String grade2 = student.getGrade2();
			if (grade2.equals("EI")) {
				if ((grade >= 0) && (grade <= 2)) {
					grade++;
					grade2 = "EI";
				} else {
					grade = 1;
					grade2 = "EFI";
				}
			} else if (grade2.startsWith("EF")) {
				if ((grade >= 1) && (grade <= 4)) {
					grade++;
					grade2 = "EFI";
				} else if (grade == 5) {
					grade++;
					grade2 = "EFII";
				} else if ((grade >= 6) && (grade <= 8)) {
					grade++;
					grade2 = "EFII";
				} else {
					grade = 1;
					grade2 = "EM";
				}
			} else if (grade2.equals("EM") || grade2.equals("EMI")) {
				if ((grade >= 0) && (grade <= 2)) {
					grade++;					
				} else {
					throw new InvalidParameterException(
							"Essa operacao nao pode ser realizada sobre alunos no 3 ano do ensino medio.");
				}
			} else {
				throw new InvalidParameterException(
						"Essa operacao nao pode ser realizada sobre alunos no ensino profissional.");
			}
			student.setGrade(String.valueOf(grade));
			student.setGrade2(grade2);
			HibernateUtil.update(student);
		}
	}

	/**
	 * 
	 * @param id
	 * @return um objeto da classe Student com o id informado
	 * @throws NoSuchStudentException
	 */
	@SuppressWarnings("unchecked")
	public Student getStudent(int id) throws NoSuchStudentException {
		Collection<SimpleExpression> exps = new ArrayList<SimpleExpression>();
		exps.add(Restrictions.like("id", id));
		List<Student> student = null;
		try {
			student = (List<Student>) HibernateUtil.createQuery(Student.class,
					exps);
		} catch (ConstraintViolationException e) {
			throw new NoSuchStudentException();
		}

		return student.isEmpty() ? null : student.get(0);
	}

	/**
	 * 
	 * @param id
	 * @param info
	 * @return uma informação desejada sobre um objeto Student
	 * @throws NoSuchStudentException
	 */
	public String getStudentInfo(int id, StudentInfo info)
			throws NoSuchStudentException {
		if (getStudent(id) == null)
			throw new NoSuchStudentException("O aluno com ID " + id
					+ " não existe.");
		switch (info) {
		case ACTIVE:
			return getStudent(id).getActive();
		case ADDRESS_LINE:
			return getStudent(id).getAddressLine();
		case BIRTHDAY:
			return Validator.formatTimestamp(getStudent(id).getBirthday());
		case CEP:
			return getStudent(id).getCep();
		case CITY:
			return getStudent(id).getCity();
		case CLASS_ID:
			return getStudent(id).getClassId();
		case CPF:
			return getStudent(id).getCpf();
		case DISTRICT:
			return getStudent(id).getDistrict();
		case EMAIL:
			return getStudent(id).getEmail();
		case GENDER:
			return String.valueOf(getStudent(id).getGender());
		case GRADE:
			return getStudent(id).getGrade();
		case GRADE_2:
			return getStudent(id).getGrade2();
		case MOBILE_PHONE_NUMBER:
			return getStudent(id).getMobilePhoneNumber();
		case NAME:
			return getStudent(id).getName();
		case PHONE_NUMBER:
			return getStudent(id).getPhoneNumber();
		case PARENTAL_INFO:
			return String.valueOf(getStudent(id).getParentalInfo());
		case PICTURE_URL:
			return getStudent(id).getPictureUrl();
		case REGISTRATION_NUMBER:
			return String.valueOf(getStudent(id).getRegistrationNumber());
		case RG:
			return getStudent(id).getRg();
		case NATIONALITY:
			return getStudent(id).getNationality();
		case SHIFT:
			return getStudent(id).getShift();
		case INEP:
			return getStudent(id).getInep();
		default:
			return new String();
		}
	}

	/**
	 * 
	 * @param info
	 * @param value
	 * @return conjunto de objetos encontrados com o dado informado
	 * @throws DBException
	 */
	@SuppressWarnings("unchecked")
	public List<Student> getStudents(StudentInfo info, String value)
			throws DBException {
		Collection<SimpleExpression> exps = new ArrayList<SimpleExpression>();
		if (info.equals(StudentInfo.NAME) || info.equals(StudentInfo.GRADE)) {
			exps.add(Restrictions.like(info.getDescription(), value,
					MatchMode.ANYWHERE));
		} else if (info.equals(StudentInfo.REGISTRATION_NUMBER)) {
			exps.add(Restrictions.like(info.getDescription(), Integer
					.parseInt(value)));
		} else {
			exps.add(Restrictions.like(info.getDescription(), value));
		}

		List<Student> student = null;
		try {
			student = (List<Student>) HibernateUtil.createQuery(Student.class,exps);
		} catch (ConstraintViolationException e) {
			throw new DBException(
					"A busca ocasionou uma violação nas regras de integridade do banco de dados.");
		}

		// if (student.isEmpty()) throw new NoSuchStudentException();
		return student;
	}

	/**
	 * Este método remove um Estudante do sistema
	 * 
	 * @param id
	 * @return objeto da classe Discipline que foi removido
	 * @throws NoSuchDisciplineException
	 */
	public Student removeStudent(int id) throws NoSuchStudentException {
		Student s = getStudent(id);
		if (s == null)
			throw new NoSuchStudentException();
		HibernateUtil.delete(s);
		return s;
	}

	/**
	 * Este faz a atualização dos dados de um Estudante
	 * 
	 * @param id
	 * @param parentalInfo
	 * @param name
	 * @param gender
	 * @param addressLine
	 * @param number
	 * @param complement
	 * @param nationality
	 * @param homeland
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
	 * @param birthplace
	 * @param education
	 * @param transportation
	 * @param localization
	 * @param deficiency
	 * @throws NoSuchStudentException
	 * @throws InvalidParameterException
	 * @throws DBException
	 */
	public void updateStudent(int id, String parentalInfo, String name,
			String gender, String addressLine, String number,
			String complement, String nationality, String homeland, String cep,
			String district, String city, String state, String dddPhoneNumber,
			String dddMobilePhoneNumber, String phoneNumber,
			String mobilePhoneNumber, String email, String grade,
			String classId, String shift, String grade2, String pictureUrl,
			String active, String rg, String issuingDate, String issuedBy,
			String complementRg, String stateRg, String cpf, String birthday,
			String civilCertificate, String numberTerm, String sheet,
			String book, String dateIssueCertificate, String stateOfOffice,
			String nameOfOffice, String enrollmentDate, String scholarship,
			String discount, String startDisc, String endDisc, String language,
			String langClass, String race, String birthplace, String education,
			String transportation, String localization, String deficiency, String inep)
			throws NoSuchStudentException, InvalidParameterException,
			DBException {
		Student s = getStudent(id);

		if (s == null) {
			throw new NoSuchStudentException();
		}

		validate("1", name, gender, addressLine, number, complement,
				nationality, homeland, cep, district, city, state,
				dddPhoneNumber, dddMobilePhoneNumber, phoneNumber,
				mobilePhoneNumber, email, grade, classId, shift, grade2,
				pictureUrl, active, rg, issuingDate, issuedBy, complementRg,
				stateRg, cpf, birthday, civilCertificate, numberTerm, sheet,
				book, dateIssueCertificate, stateOfOffice, nameOfOffice,
				enrollmentDate, scholarship, discount, startDisc, endDisc,
				race, education, transportation, localization, deficiency);

		s.setName(name);
		s.setParentalInfo(Integer.parseInt(parentalInfo));
		s.setGender(gender);
		s.setAddressLine(addressLine);
		s.setNumber(number);
		s.setComplement(complement);
		s.setNationality(nationality);
		s.setHomeland(homeland);
		s.setCep(cep);
		s.setDistrict(district);
		s.setCity(city);
		s.setState(state);
		s.setDddPhoneNumber(dddPhoneNumber);
		s.setDddMobilePhoneNumber(dddMobilePhoneNumber);
		s.setPhoneNumber(phoneNumber);
		s.setMobilePhoneNumber(mobilePhoneNumber);
		s.setEmail(email);
		s.setGrade(grade);
		s.setClassId(classId);
		s.setShift(shift);
		s.setGrade2(grade2);
		s.setPictureUrl(pictureUrl);
		s.setActive(active);
		s.setRg(rg);
		s.setIssuedBy(issuedBy);
		s.setIssuingDate(Validator.generateDate(issuingDate));
		s.setCpf(cpf);
		s.setBirthday(Validator.generateDate(birthday));
		s.setComplementRg(complementRg);
		s.setStateRg(stateRg);
		s.setCivilCertificate(civilCertificate);
		s.setNumberTerm(numberTerm);
		s.setSheet(sheet);
		s.setBook(book);
		s.setDateIssueCertificate(Validator.generateDate(dateIssueCertificate));
		s.setStateOfTitulo(stateOfOffice);
		s.setNameOfOffice(nameOfOffice);
		s.setEnrollmentDate(Validator.generateDate(enrollmentDate));
		s.setScholarship(scholarship);
		s.setRace(race);
		s.setBirthplace(birthplace);
		s.setTransportation(transportation);
		s.setLocalization(localization);
		s.setDeficiency(deficiency);
		s.setInep(inep);

		HibernateUtil.update(s);
	}

	@SuppressWarnings("unchecked")
	public List<Student> getAllStudents() {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		List<Student> result = session.createSQLQuery(
				"select " + "{a.*} " + "from alunos {a} order by {a}.serie, {a}.turma, {a}.grau, {a}.nome;")
				.addEntity("a", Student.class).list();
		session.getTransaction().commit();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Student> getAllStudents2() {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		List<Student> result = session.createSQLQuery(
				"select " + "{a.*} " + "from evl_alunos {a} "
						+ "order by {a}.nome;")
				.addEntity("a", Student.class).list();
		session.getTransaction().commit();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Student> getAllStudentsBy(String grade, String classId,String grade2) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		int matriculado = 1;
		String query = "select "
			+ "{a.*} "
			+ "from alunos {a} "
			+ "where ({a}.serie=\'"	+ grade +"\' || {a}.serie=concat(\'"+grade+"\',\'º\')"+")"
			+ " and {a}.turma=\'"
			+ classId
			+ "\' and {a}.grau=\'"
			+ grade2
			+ "\' and {a}.matriculado=\'"
			+ matriculado
			+ "\' order by {a}.serie, {a}.turma, {a}.grau, {a}.nome;";
		List<Student> result = session
				.createSQLQuery(query)
				.addEntity("a", Student.class).list();
		session.getTransaction().commit();
		return result;
	}
}
