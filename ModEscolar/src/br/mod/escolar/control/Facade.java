package br.mod.escolar.control;

import java.util.ArrayList;
import java.util.List;

import br.mod.escolar.control.DisciplineManager.DisciplineInfo;
import br.mod.escolar.control.StudentManager.StudentInfo;
import br.mod.escolar.control.TeacherDisciplineManager.TeacherDisciplineInfo;
import br.mod.escolar.control.TeacherManager.TeacherInfo;
import br.mod.escolar.control.UserManager.UserInfo;
import br.mod.escolar.control.UserTypeManager.UserTypeInfo;
import br.mod.escolar.model.entities.Discipline;
import br.mod.escolar.model.entities.HorariosDisciplinas;
import br.mod.escolar.model.entities.ParentalInfo;
import br.mod.escolar.model.entities.Student;
import br.mod.escolar.model.entities.Teacher;
import br.mod.escolar.model.entities.TeacherDiscipline;
import br.mod.escolar.model.entities.User;
import br.mod.escolar.model.entities.UserType;
import br.mod.escolar.model.exception.DBException;
import br.mod.escolar.model.exception.GUIException;
import br.mod.escolar.model.exception.InvalidParameterException;
import br.mod.escolar.model.exception.InvalidPasswordException;
import br.mod.escolar.model.exception.InvalidStateUser;
import br.mod.escolar.model.exception.NoSuchDisciplineException;
import br.mod.escolar.model.exception.NoSuchParentalInfoException;
import br.mod.escolar.model.exception.NoSuchSessionException;
import br.mod.escolar.model.exception.NoSuchStudentException;
import br.mod.escolar.model.exception.NoSuchTeacherDisciplineException;
import br.mod.escolar.model.exception.NoSuchTeacherException;
import br.mod.escolar.model.exception.NoSuchUserException;
import br.mod.escolar.model.exception.NoSuchUserTypeException;
import br.mod.escolar.model.util.Base64Coder;
import br.mod.escolar.model.util.HibernateUtil;
import br.mod.escolar.model.util.Messages;
import br.mod.escolar.model.util.MountIDSession;
import br.mod.escolar.model.util.RealPathApp;
import br.mod.escolar.model.util.Validator;

public class Facade {

	public static Facade facade = null;
	public StudentManager studentManager;
	public TeacherManager teacherManager;
	public DisciplineManager disciplineManager;
	public SessionsManager sessionsManager;
	public UserManager userManager;
	public TeacherDisciplineManager teacherDisciplineManager;
	public ParentalInfoManager parentalInfoManager;
	public UserTypeManager userTypeManager;
	public ReportsManager reportManager;
	private static final String NEW_LINE = System.getProperty("line.separator");

	public Facade() {
		studentManager = StudentManager.getInstance();
		teacherManager = TeacherManager.getInstance();
		disciplineManager = DisciplineManager.getInstance();
		userManager = UserManager.getInstance();
		sessionsManager = SessionsManager.getInstance();
		teacherDisciplineManager = TeacherDisciplineManager.getInstance();
		parentalInfoManager = ParentalInfoManager.getInstance();
		userTypeManager = UserTypeManager.getInstance();
		reportManager = ReportsManager.getInstance();

	}

	/**
	 * Padrão Singleton
	 * 
	 * @return
	 */
	public static synchronized Facade getInstance() {
		if (facade == null) {
			facade = new Facade();
		}
		return facade;
	}

	public String login(String login, String password)
			throws NoSuchUserException, InvalidPasswordException, DBException,
			NoSuchUserTypeException, InvalidStateUser {
		String truePassword = userManager.getUserInfo(login, UserInfo.PASSWORD);
		String sessionID = "";

		if (password.equals(Base64Coder.decodeString(truePassword))) {

			String ativo = userManager.getUserInfo(login, UserInfo.STATUS);

			if (ativo.equals("1")) {
				sessionID = MountIDSession.generateIDSession();

			} else {
				throw new InvalidStateUser();
			}

		} else {
			throw new InvalidPasswordException();
		}

		sessionsManager.addSession(sessionID, userManager.getUser(login));

		return sessionID;

	}

	public static Facade getFacade() {
		return facade;
	}

	public static void setFacade(Facade facade) {
		Facade.facade = facade;
	}

	public String logout(String sessionID) {
		sessionsManager.removeSession(sessionID);
		RealPathApp.cleanDir(sessionID);
		return Messages.SUCCESS_LOGOUT;
	}

	public boolean existSession(String sessionID) {
		return sessionsManager.existSession(sessionID);
	}

	public int createUser(User u) throws InvalidParameterException, DBException {
		return userManager.createUser(u);
	}

	public void updateUser(User u) {
		userManager.updateUser(u);
	}

	public void removeUser(String login) throws NoSuchUserException,
			DBException {
		userManager.removeUser(login);
	}

	public String getUserTypeLogged(String sessionId)
			throws NoSuchUserTypeException {
		int id = sessionsManager.getUserOfSession(sessionId).getIdType();
		String type = userTypeManager.getUserTypeInfo(id, UserTypeInfo.TYPE);
		return type;
	}

	public String getUserTypeByLogin(String login)
			throws NoSuchSessionException, NoSuchUserTypeException,
			NoSuchUserException, DBException {
		int idType = Integer.parseInt(userManager.getUserInfo(login,
				UserInfo.ID_TYPE));
		return userTypeManager.getUserTypeInfo(idType, UserTypeInfo.TYPE);
	}

	public String getUserTypeLogged2(String sessionId)
			throws NoSuchUserTypeException {
		int id = sessionsManager.getUserOfSession(sessionId).getIdType();
		return Integer.toString(id);
	}

	public int getEntitieId(String sessionId) throws NoSuchUserTypeException {
		return sessionsManager.getUserOfSession(sessionId).getIdPerson();
	}

	public int getEntityId(String sessionId) throws NoSuchUserTypeException {
		return sessionsManager.getUserOfSession(sessionId).getIdPerson();
	}

	public String getUserTypeByLogin2(String login)
			throws NoSuchSessionException, NoSuchUserTypeException,
			NoSuchUserException, DBException {
		int idType = Integer.parseInt(userManager.getUserInfo(login,
				UserInfo.ID_TYPE));
		return Integer.toString(idType);
	}

	public int newParentalInfo(ParentalInfo pi) {
		return (Integer) HibernateUtil.create(pi);
	}

	public void updateParentalInfo(ParentalInfo pi) {
		HibernateUtil.update(pi);
	}

	public int createParentalInfo(String motherName, String motherBirthday,
			String motherCPF, String motherRG, String motherIssuingDate,
			String motherIssuingOrg, String motherJob, String motherWorkplace,
			String motherReligion, String motherTelephoneDDD,
			String motherTelephone, String motherMobileDDD,
			String motherMobile, String motherEmail, String fatherName,
			String fatherBirthday, String fatherCPF, String fatherRG,
			String fatherIssuingDate, String fatherIssuingOrg,
			String fatherJob, String fatherWorkplace, String fatherReligion,
			String fatherTelephoneDDD, String fatherTelephone,
			String fatherMobileDDD, String fatherMobile, String fatherEmail)
			throws DBException, NoSuchStudentException,
			InvalidParameterException {

		return parentalInfoManager.createParentalInfo(motherName,
				motherBirthday, motherCPF, motherRG, motherIssuingDate,
				motherIssuingOrg, motherJob, motherWorkplace, motherReligion,
				motherTelephoneDDD, motherTelephone, motherMobileDDD,
				motherMobile, motherEmail, fatherName, fatherBirthday,
				fatherCPF, fatherRG, fatherIssuingDate, fatherIssuingOrg,
				fatherJob, fatherWorkplace, fatherReligion, fatherTelephoneDDD,
				fatherTelephone, fatherMobileDDD, fatherMobile, fatherEmail);
	}

	public ParentalInfo getParentalInfo(int id)
			throws NoSuchParentalInfoException, GUIException {
		return parentalInfoManager.getParentalInfo(id);
	}

	public ParentalInfo getStudentParentalInfo(int regNumber)
			throws NumberFormatException, GUIException {
		String pId = "";
		try {
			int sId = studentManager
					.getStudents(StudentInfo.REGISTRATION_NUMBER,
							Integer.toString(regNumber)).get(0).getId();
			pId = studentManager.getStudentInfo(sId, StudentInfo.PARENTAL_INFO);
			if (pId.equals("null"))
				throw new GUIException(
						"Nao foram encontrados os dados dos pais do aluno de matricula "
								+ regNumber + ".");
			return parentalInfoManager.getParentalInfo(Integer.parseInt(pId));
		} catch (NoSuchStudentException e) {
			throw new GUIException(
					e.getMessage()
							+ NEW_LINE
							+ "Certifique-se de que voce digitou a matricula corretamente.");
		} catch (DBException e) {
			throw new GUIException(
					"Ocorreu algum problema com o banco de dados. Contate o desenvolvedor. Erro: "
							+ e.getMessage());
		}
	}

	public ParentalInfo removeParentalInfo(int idStudent)
			throws NoSuchParentalInfoException {
		return parentalInfoManager.removeParentalInfo(idStudent);
	}

	public void updateParentalInfo(int idStudent, String motherName,
			String motherBirthday, String motherCPF, String motherRG,
			String motherIssuingDate, String motherIssuingOrg,
			String motherJob, String motherWorkplace, String motherReligion,
			String motherTelephoneDDD, String motherTelephone,
			String motherMobileDDD, String motherMobile, String motherEmail,
			String fatherName, String fatherBirthday, String fatherCPF,
			String fatherRG, String fatherIssuingDate, String fatherIssuingOrg,
			String fatherJob, String fatherWorkplace, String fatherReligion,
			String fatherTelephoneDDD, String fatherTelephone,
			String fatherMobileDDD, String fatherMobile, String fatherEmail)
			throws DBException, InvalidParameterException,
			NoSuchParentalInfoException {
		parentalInfoManager.updateParentalInfo(idStudent, motherName,
				motherBirthday, motherCPF, motherRG, motherIssuingDate,
				motherIssuingOrg, motherJob, motherWorkplace, motherReligion,
				motherTelephoneDDD, motherTelephone, motherMobileDDD,
				motherMobile, motherEmail, fatherName, fatherBirthday,
				fatherCPF, fatherRG, fatherIssuingDate, fatherIssuingOrg,
				fatherJob, fatherWorkplace, fatherReligion, fatherTelephoneDDD,
				fatherTelephone, fatherMobileDDD, fatherMobile, fatherEmail);
	}

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
			String transportation, String localization, String deficiency,
			String inep) throws InvalidParameterException, DBException,
			NoSuchStudentException {
		return studentManager.createStudent(registrationNumber, parentalInfo,
				name, gender, addressLine, number, complement, nationality,
				homeland, cep, district, city, state, dddPhoneNumber,
				dddMobilePhoneNumber, phoneNumber, mobilePhoneNumber, email,
				grade, classId, shift, grade2, pictureUrl, active, rg,
				issuingDate, issuedBy, complementRg, stateRg, cpf, birthday,
				civilCertificate, numberTerm, sheet, book,
				dateIssueCertificate, stateOfOffice, nameOfOffice,
				enrollmentDate, scholarship, discount, startDisc, endDisc,
				language, langClass, race, birthplace, education,
				transportation, localization, deficiency, inep);
	}

	public int newStudent(Student s) {
		return studentManager.newStudent(s);
	}

	public int newStudent(Student s, ParentalInfo i) {
		int iid = (Integer) HibernateUtil.create(i);
		s.setParentalInfo(iid);
		return (Integer) HibernateUtil.create(s);
	}

	public void updateStudent(Student s) {
		HibernateUtil.update(s);
	}

	public void updateStudent(Student s, ParentalInfo pi) {
		int id = pi.getId();
		if (id == 0) {
			id = (Integer) HibernateUtil.create(pi);
		} else {
			HibernateUtil.update(pi);
		}
		s.setParentalInfo(id);
		HibernateUtil.update(s);
	}

	public List<Student> getStudents(String criteria, String value)
			throws DBException, NoSuchStudentException, GUIException {
		try {
			return studentManager.getStudents(StudentInfo.valueOf(criteria),
					value);
		} catch (NumberFormatException e) {
			throw new GUIException(
					"Para buscar um aluno por matricula, insira apenas numeros.");
		}

	}

	public int newDiscipline(Discipline d, ArrayList<HorariosDisciplinas> lista) {
		int id = (Integer) HibernateUtil.create(d);

		return id;
	}

	public String getStudentInfo(int id, String info)
			throws NoSuchStudentException {
		return studentManager.getStudentInfo(id, StudentInfo.valueOf(info));
	}

	public void deleteStudent(int id) throws NoSuchStudentException {
		studentManager.removeStudent(id);
	}

	public int retrieveStudent(int registrationNumber) throws DBException,
			NoSuchStudentException {
		List<Student> s = studentManager.getStudents(
				StudentInfo.REGISTRATION_NUMBER,
				String.valueOf(registrationNumber));
		return (s.isEmpty()) ? -1 : s.get(0).getId();
	}

	public Student getStudent(int registrationNumber) throws DBException,
			NoSuchStudentException {
		List<Student> s = studentManager.getStudents(
				StudentInfo.REGISTRATION_NUMBER,
				String.valueOf(registrationNumber));
		return (s.isEmpty()) ? null : ((Student) s.get(0));
	}

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
			String transportation, String localization, String deficiency,
			String inep) throws NoSuchStudentException,
			InvalidParameterException, DBException {
		studentManager.updateStudent(id, parentalInfo, name, gender,
				addressLine, number, complement, nationality, homeland, cep,
				district, city, state, dddPhoneNumber, dddMobilePhoneNumber,
				phoneNumber, mobilePhoneNumber, email, grade, classId, shift,
				grade2, pictureUrl, active, rg, issuingDate, issuedBy,
				complementRg, stateRg, cpf, birthday, civilCertificate,
				numberTerm, sheet, book, dateIssueCertificate, stateOfOffice,
				nameOfOffice, enrollmentDate, scholarship, discount, startDisc,
				endDisc, language, langClass, race, birthplace, education,
				transportation, localization, deficiency, inep);

	}

	public int createTeacher(String idTeacher, String name, String email,
			String gender, String addressLine, String cep, String district,
			String city, String state, String nationality, String homeland,
			String dddPhoneNumber, String dddMobilePhoneNumber,
			String phoneNumber, String mobilePhoneNumber, String cpf,
			String issuingDate, String issuedBy, String rg,
			String complementRg, String stateRg, String birthday,
			String civilCertificate, String numberTerm, String sheet,
			String book, String dateIssueCertificate, String stateOfOffice,
			String nameOfOffice, String nickName, String motherName,
			String socialIdentificationNumber, String dateOfAdmission,
			String numberCTPS, String gradeCTPS, String dateCTPS,
			String stateCTPS, String numberVoting, String areaElection,
			String section, String race, String birthplace)
			throws InvalidParameterException {

		return teacherManager.createTeacher(Integer.valueOf(idTeacher), name,
				email, gender, addressLine, cep, district, city, state,
				nationality, homeland,

				dddPhoneNumber, dddMobilePhoneNumber, phoneNumber,
				mobilePhoneNumber, cpf, Validator.generateDate(issuingDate),
				issuedBy, rg, complementRg, stateRg,
				Validator.generateDate(birthday), civilCertificate, numberTerm,
				sheet, book, Validator.generateDate(dateIssueCertificate),
				stateOfOffice, nameOfOffice, nickName, motherName,
				socialIdentificationNumber,
				Validator.generateDate(dateOfAdmission), numberCTPS, gradeCTPS,
				Validator.generateDate(dateCTPS), stateCTPS, numberVoting,
				areaElection, section, race, birthplace);
	}

	public Integer newTeacher(Teacher t) {

		return (Integer) HibernateUtil.create(t);
	}

	public List<Teacher> getTeachers(String crit, String value)
			throws DBException, GUIException {
		Object val;
		try {
			val = crit.equals("ID_TEACHER") ? Integer.valueOf(value) : value;
		} catch (NumberFormatException e) {
			throw new GUIException(
					"Insira apenas números para buscar por matricula.");
		}

		return teacherManager.getTeacher(TeacherInfo.valueOf(crit), val);
	}

	public List<Teacher> getAllTeachers() {
		return teacherManager.getAllTeachers();
	}

	public List<Student> getAllStudents() {
		return studentManager.getAllStudents();
	}

	public String getTeacherInfo(int id, String info)
			throws NoSuchTeacherException {
		return teacherManager.getTeacherInfo(id, TeacherInfo.valueOf(info));
	}

	public void deleteTeacher(int id) throws NoSuchTeacherException {
		teacherManager.removeTeacher(id);
	}

	public int retrieveTeacher(int idTeacher) throws DBException {
		return teacherManager
				.getTeacher(TeacherInfo.ID, String.valueOf(idTeacher)).get(0)
				.getId();
	}

	public void updateTeacher(Teacher t) {
		HibernateUtil.update(t);
	}

	public void updateTeacher(int id, String name, String email, String gender,
			String addressLine, String cep, String district, String city,

			String state, String nationality,

			String homeland, String dddPhoneNumber,
			String dddMobilePhoneNumber, String phoneNumber,
			String mobilePhoneNumber, String cpf, String issuingDate,
			String issuedBy, String rg, String complementRg, String stateRg,
			String birthday, String civilCertificate, String numberTerm,
			String sheet, String book, String dateIssueCertificate,
			String stateOfOffice, String nameOfOffice, String nickName,
			String motherName, String socialIdentificationNumber,
			String dateOfAdmission, String numberCTPS, String gradeCTPS,
			String dateCTPS, String stateCTPS, String numberVoting,
			String areaElection, String section, String race, String birthplace)
			throws NoSuchTeacherException, InvalidParameterException {

		teacherManager.updateTeacher(id, name, email, gender, addressLine, cep,
				district, city, state, nationality, homeland,

				dddPhoneNumber, dddMobilePhoneNumber, phoneNumber,
				mobilePhoneNumber, cpf, Validator.generateDate(issuingDate),
				issuedBy, rg, complementRg, stateRg,
				Validator.generateDate(birthday), civilCertificate, numberTerm,
				sheet, book, Validator.generateDate(dateIssueCertificate),
				stateOfOffice, nameOfOffice, nickName, motherName,
				socialIdentificationNumber,
				Validator.generateDate(dateOfAdmission), numberCTPS, gradeCTPS,
				Validator.generateDate(dateCTPS), stateCTPS, numberVoting,
				areaElection, section, race, birthplace);

	}

	public int createDiscipline(String name, int grade, String grade2,
			String area, String hoursPerWeek, String hoursPerYear)
			throws Exception {
		return disciplineManager.createDiscipline(name, grade, grade2, area,
				hoursPerWeek, hoursPerYear);
	}

	public String getDisciplineInfo(int id, String info)
			throws NoSuchDisciplineException {
		return disciplineManager.getDisciplineInfo(id,
				DisciplineInfo.valueOf(info));
	}

	public void deleteDiscipline(int id) throws NoSuchDisciplineException {
		disciplineManager.removeDiscipline(id);
	}

	public int retrieveDiscipline(int id) throws DBException {

		return disciplineManager
				.getDiscipline(DisciplineInfo.ID, String.valueOf(id)).get(0)
				.getId();
	}

	public void updateDiscipline(int id, String name, int grade, String grade2,
			String area, String hoursPerWeek, String hoursPerYear)
			throws NoSuchDisciplineException, InvalidParameterException,
			NoSuchStudentException {
		disciplineManager.updateDiscipline(id, name, grade, grade2, area,
				hoursPerWeek, hoursPerYear);
	}

	public void updateDiscipline(Discipline d) {
		HibernateUtil.update(d);
	}

	public List<Discipline> getTeacherDisciplines(int idTeacher)
			throws NoSuchTeacherDisciplineException, NoSuchDisciplineException,
			NoSuchTeacherException {
		List<TeacherDiscipline> tds = teacherDisciplineManager
				.getDisciplines(idTeacher);
		// String nome = getTeacherInfo(idTeacher,"NAME");
		List<Discipline> discs = new ArrayList<Discipline>();
		for (TeacherDiscipline td : tds) {
			Discipline d = disciplineManager
					.getDiscipline(td.getIdDiscipline());

			discs.add(d);
		}
		return discs;
	}

	public List<Discipline> getTeacherDisciplines(int idTeacher, String grade2)
			throws NoSuchTeacherDisciplineException, NoSuchDisciplineException {
		List<TeacherDiscipline> tds = teacherDisciplineManager
				.getDisciplines(idTeacher);
		List<Discipline> discs = new ArrayList<Discipline>();
		for (TeacherDiscipline td : tds) {
			Discipline d = disciplineManager
					.getDiscipline(td.getIdDiscipline());
			if (d.getGrade2().equals(grade2))
				discs.add(d);
		}
		return discs;
	}

	public List<Discipline> getDisciplines(String criteria, String value)
			throws DBException, NoSuchStudentException {
		DisciplineInfo searchCriteria = criteria.equals("nome") ? DisciplineInfo.NAME
				: criteria.equals("grau") ? DisciplineInfo.GRADE2
						: DisciplineInfo.AREA;
		return disciplineManager.getDiscipline(searchCriteria, value);
	}

	public int createTeacherDiscipline2(TeacherDiscipline td) {
		return (Integer) HibernateUtil.create(td);
	}

	public int createTeacherDiscipline(int id, int teacher, int discipline)
			throws Exception {
		return teacherDisciplineManager.createTeacherDisciplineRelationship(id,
				teacher, discipline);
	}

	public int getTeacherDisciplineInfo(int id, TeacherDisciplineInfo info)
			throws NoSuchTeacherDisciplineException {
		return teacherDisciplineManager.getTeacherDisciplineInfo(id, info);
	}

	public void deleteTeacherDiscipline(int id)
			throws NoSuchTeacherDisciplineException {
		teacherDisciplineManager.removeTeacherDiscipline(id);
	}

	public void deleteTeacherDiscipline(int idDisc, int idTeacher)
			throws NoSuchTeacherDisciplineException {
		teacherDisciplineManager.removeTeacherDiscipline(idDisc, idTeacher);
	}

	public int retrieveTeacherDiscipline(int id) throws DBException {
		return teacherDisciplineManager
				.getTeacherDiscipline(TeacherDisciplineInfo.ID,
						String.valueOf(id)).get(0).getId();
	}

	public void updateTeacherDiscipline(int id, int teacher, int discipline)
			throws NoSuchDisciplineException, InvalidParameterException,
			NoSuchStudentException, NoSuchTeacherDisciplineException {
		teacherDisciplineManager.updateTeacherDiscipline(id, teacher,
				discipline);
	}

	public void updateUserTypeInfo(UserTypeInfo pi) {
		HibernateUtil.update(pi);
	}

	public int createUserType(int id, String type, String description,
			String page) throws Exception {
		return userTypeManager.createUserType(id, type, description, page);
	}

	public UserType getUserType(int id) throws NoSuchUserTypeException,
			GUIException {
		return userTypeManager.getUserType(id);
	}

	public UserType removeUserType(int id) throws NoSuchUserTypeException {
		return userTypeManager.removeUserType(id);
	}

	public List<UserType> getAllUserTypes() {
		return userTypeManager.getAllUserTypes();
	}

	public List<User> getAllUser() {

		return userManager.getAllUsers();

	}

	public User getUser(String login) throws DBException,
			InvalidParameterException {
		User u = userManager.getUser(login);
		try {
			u.setPassword(Base64Coder.decodeString(u.getPassword()));
		} catch (NullPointerException e) {
			throw new InvalidParameterException("O usuario com login \""
					+ login + "\" nao existe.");
		}
		return u;
	}

	public void generateMultipleStudentReportWithAllInformationsToAllStudents(
			String path) throws Exception {
		List<Student> students = studentManager.getAllStudents();
		verifyIfIsEmptyResult(students);
		reportManager
				.generateMultipleStudentReportWithAllInformationsToAllStudents(
						path, students);
	}

	public void generateMultipleStudentReportWithPresenceListInformationsByGradeEtc(
			String path, String grade, String classId, String grade2)
			throws Exception {
		List<Student> students = studentManager.getAllStudentsBy(grade,
				classId, grade2);
		verifyIfIsEmptyResult(students);
		reportManager
				.generateMultipleStudentReportWithPresenceListInformationsByGradeEtc(
						path, grade, classId, grade2, students);
	}

	public void updateParentalInfo(int id, String type, String description,
			String page) throws InvalidParameterException,
			NoSuchUserTypeException {
		userTypeManager.updateUserType(id, type, description, page);
	}

	public List<Student> getStudents2(String grade, String classId,
			String grade2) {
		return studentManager.getAllStudentsBy(grade, classId, grade2);

	}

	public void generateMultipleDisciplinesReportToAllDisciplinesByGradeAndGrade2(
			String path, String grade, String grade2) throws Exception {
		List<Discipline> disciplines = disciplineManager
				.getAllDisciplinesByGradeAndGrade2(Integer.parseInt(grade),
						grade2);
		verifyIfIsEmptyResult(disciplines);
		reportManager
				.generateMultipleDisciplinesReportToAllDisciplinesByGradeAndGrade2(
						path, grade, grade2, disciplines);
	}

	public void generateMultipleDisciplinesReportToAllDisciplines(String path)
			throws Exception {
		List<Discipline> disciplines = disciplineManager.getAllDisciplines();
		verifyIfIsEmptyResult(disciplines);
		reportManager.generateMultipleDisciplinesReportToAllDisciplines(path,
				disciplines);
	}

	public void generateMultipleStudentReportWithGeneralInformationsByGradeEtc(
			String path, String grade, String classId, String grade2)
			throws Exception {
		List<Student> students = studentManager.getAllStudentsBy(grade,
				classId, grade2);
		verifyIfIsEmptyResult(students);
		reportManager
				.generateMultipleStudentReportWithGeneralInformationsByGradeEtc(
						path, grade, classId, grade2, students);
	}

	public void generateMultipleTeachersReportToAllTeachers(String path)
			throws Exception {
		List<Teacher> teachers = teacherManager.getAllTeachers();
		verifyIfIsEmptyResult(teachers);
		reportManager.generateMultipleTeachersReportToAllTeachers(path,
				teachers);
	}

	private void verifyIfIsEmptyResult(List<?> list) throws Exception {
		if (list.isEmpty()) {
			throw new Exception(
					"Não existem dados armazenados no nosso banco de dados para esta consulta. Entre em contato com o administrador do sistema.");
		}
	}
}
