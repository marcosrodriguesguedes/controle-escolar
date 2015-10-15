package br.mod.escolar.control;

import java.io.File;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import br.mod.escolar.control.TeacherDisciplineManager.TeacherDisciplineInfo;
import br.mod.escolar.model.entities.Discipline;
import br.mod.escolar.model.entities.Equipe;
import br.mod.escolar.model.entities.EquipeStudents;
import br.mod.escolar.model.entities.Frequencia;
import br.mod.escolar.model.entities.HorariosDisciplinas;
import br.mod.escolar.model.entities.MaterialDeApoio;
import br.mod.escolar.model.entities.Notas;
import br.mod.escolar.model.entities.ParentalInfo;
import br.mod.escolar.model.entities.RegistroDeAula;
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
import br.mod.escolar.model.exception.NoSuchPermissionException;
import br.mod.escolar.model.exception.NoSuchSessionException;
import br.mod.escolar.model.exception.NoSuchStudentException;
import br.mod.escolar.model.exception.NoSuchTeacherDisciplineException;
import br.mod.escolar.model.exception.NoSuchTeacherException;
import br.mod.escolar.model.exception.NoSuchUserException;
import br.mod.escolar.model.exception.NoSuchUserTypeException;
import br.mod.escolar.servlets.DownloadProperties;

public class FacadeAuth implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static FacadeAuth facadeAuth = null;
	public Facade facade;
	public RegistroDeAulaManager registroAulaManager;
	public NotasManager notasManager;
	public EquipeManager equipesManager;
	public DisciplineManager disciplineManager;
	public FrequenciaManager frequenciaManager;
	public static final String ERROR = "Erro: ";
	public AccessPermissionsManager accessPermissionsManager;
	public MaterialDeApoioManager materialManager;
	private final String FILE_SEPARATOR = System.getProperty("file.separator");
	private DownloadProperties properties = DownloadProperties.getInstance();
	private final String DEFAULT_PATH = properties
			.getProperty(DownloadProperties.DOWNLOAD_DIRECTORY);
	private final String URL_APP = properties
			.getProperty(DownloadProperties.URL_APP);
	private ReportsManager reportManager;

	public FacadeAuth() {
		facade = Facade.getInstance();
		accessPermissionsManager = AccessPermissionsManager.getInstance();
		notasManager = NotasManager.getInstance();
		registroAulaManager = RegistroDeAulaManager.getInstance();
		disciplineManager = DisciplineManager.getInstance();
		frequenciaManager = FrequenciaManager.getInstance();
		equipesManager = EquipeManager.getInstance();
		materialManager = MaterialDeApoioManager.getInstance();
		reportManager = ReportsManager.getInstance();

	}

	public static synchronized FacadeAuth getInstance() {
		if (facadeAuth == null) {
			facadeAuth = new FacadeAuth();
		}
		return facadeAuth;
	}

	public String typeUserLogado(String login) throws NoSuchSessionException,
			NoSuchUserTypeException, NoSuchUserException, DBException {
		return facade.getUserTypeByLogin2(login);
	}

	public String login(String login, String password)
			throws NoSuchUserException, InvalidPasswordException, DBException,
			NoSuchSessionException, NoSuchUserTypeException,
			NoSuchPermissionException, InvalidStateUser {
		String typeUser = facade.getUserTypeByLogin2(login);
		accessPermissionsManager.verifyPermissionToLogin(typeUser);
		return facade.login(login, password);
	}

	public String doLogout(String sessionID) {
		return facade.logout(sessionID);
	}

	public User getUser(String sessionId, String login) throws DBException,
			NoSuchSessionException, InvalidParameterException,
			NoSuchPermissionException, NoSuchUserTypeException {
		if (facade.existSession(sessionId)) {
			// String typeUser = facade.getUserTypeLogged2(sessionId);
			// accessPermissionsManager.verifyPermissionToUserOperations(typeUser);
			return facade.getUser(login);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public String getUserLogin(String login) throws DBException,
			InvalidParameterException {

		return facade.getUser(login).getPassword();
	}

	public int createUser(String sessionId, User u)
			throws InvalidParameterException, DBException,

			NoSuchSessionException, NoSuchUserTypeException,
			NoSuchPermissionException {
		if (facade.existSession(sessionId)) {
			String typeUser = facade.getUserTypeLogged2(sessionId);
			accessPermissionsManager.verifyPermissionToUserOperations(typeUser);
			return facade.createUser(u);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public void updateUser(String sessionId, User u)
			throws NoSuchSessionException, NoSuchUserTypeException,
			NoSuchPermissionException {
		if (facade.existSession(sessionId)) {
			String typeUser = facade.getUserTypeLogged2(sessionId);
			accessPermissionsManager.verifyPermissionToUserOperations(typeUser);
			facade.updateUser(u);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public int createUserType(String sessionId, int id, String type,
			String description, String page) throws Exception {
		if (facade.existSession(sessionId)) {
			return facade.createUserType(id, type, description, page);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public UserType getUserType(String sessionId, int id)
			throws NoSuchUserTypeException, GUIException,
			NoSuchSessionException {
		if (facade.existSession(sessionId)) {
			return facade.getUserType(id);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public UserType removeUserType(String sessionId, int id)
			throws NoSuchUserTypeException, NoSuchSessionException {
		if (facade.existSession(sessionId)) {
			return facade.removeUserType(id);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public List<UserType> getAllUserTypes(String sessionId)
			throws NoSuchSessionException {
		if (facade.existSession(sessionId)) {
			return facade.getAllUserTypes();
		} else {
			throw new NoSuchSessionException();
		}
	}

	public String getUserTypeLogged(String sessionId)
			throws NoSuchSessionException, NoSuchUserTypeException {
		if (facade.existSession(sessionId)) {
			return facade.getUserTypeLogged(sessionId);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public String getUserTypeByLogin(String sessionId, String login)
			throws NoSuchSessionException, NoSuchUserTypeException,
			NoSuchUserException, DBException {
		if (facade.existSession(sessionId)) {
			return facade.getUserTypeByLogin(login);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public void removeUser(String sessionId, String login)
			throws NoSuchUserException, DBException, NoSuchSessionException {
		if (facade.existSession(sessionId)) {
			facade.removeUser(login);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public int newParentalInfo(String sessionId, ParentalInfo pi)
			throws NoSuchSessionException {
		if (facade.existSession(sessionId)) {
			return facade.newParentalInfo(pi);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public void updateParentalInfo(String sessionId, ParentalInfo pi)
			throws NoSuchSessionException {
		if (facade.existSession(sessionId)) {
			facade.updateParentalInfo(pi);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public int createParentalInfo(String sessionId, String motherName,
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
			throws DBException, NoSuchStudentException,
			InvalidParameterException {

		if (facade.existSession(sessionId)) {
			return facade.createParentalInfo(motherName, motherBirthday,
					motherCPF, motherRG, motherIssuingDate, motherIssuingOrg,
					motherJob, motherWorkplace, motherReligion,
					motherTelephoneDDD, motherTelephone, motherMobileDDD,
					motherMobile, motherEmail, fatherName, fatherBirthday,
					fatherCPF, fatherRG, fatherIssuingDate, fatherIssuingOrg,
					fatherJob, fatherWorkplace, fatherReligion,
					fatherTelephoneDDD, fatherTelephone, fatherMobileDDD,
					fatherMobile, fatherEmail);
		}
		return -1;
	}

	public ParentalInfo getParentalInfo(String sessionId, int id)
			throws NoSuchParentalInfoException, GUIException,
			NoSuchSessionException {
		if (facade.existSession(sessionId)) {
			return facade.getParentalInfo(id);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public ParentalInfo getStudentParentalInfo(String sessionId, int id)
			throws NumberFormatException, GUIException, NoSuchSessionException {
		if (facade.existSession(sessionId)) {
			return facade.getStudentParentalInfo(id);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public ParentalInfo removeParentalInfo(String sessionId, int idStudent)
			throws NoSuchSessionException, NoSuchParentalInfoException {
		if (facade.existSession(sessionId)) {
			return facade.removeParentalInfo(idStudent);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public String updateParentalInfo1(String sessionId, int idStudent,
			String motherName, String motherBirthday, String motherCPF,
			String motherRG, String motherIssuingDate, String motherIssuingOrg,
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
		if (facade.existSession(sessionId)) {
			facade.updateParentalInfo(idStudent, motherName, motherBirthday,
					motherCPF, motherRG, motherIssuingDate, motherIssuingOrg,
					motherJob, motherWorkplace, motherReligion,
					motherTelephoneDDD, motherTelephone, motherMobileDDD,
					motherMobile, motherEmail, fatherName, fatherBirthday,
					fatherCPF, fatherRG, fatherIssuingDate, fatherIssuingOrg,
					fatherJob, fatherWorkplace, fatherReligion,
					fatherTelephoneDDD, fatherTelephone, fatherMobileDDD,
					fatherMobile, fatherEmail);
		} else {
			return new NoSuchSessionException().getMessage();
		}
		return "";
	}

	public int createStudent(String sessionId, String registrationNumber,
			String parentalInfo, String name, String gender,
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
			String discount, String startDisc, String endDisc, String language,
			String langClass, String race, String birthplace, String education,
			String transportation, String localization, String deficiency,
			String inep) throws InvalidParameterException, DBException,
			NoSuchStudentException, NoSuchSessionException {

		if (facade.existSession(sessionId)) {

			return facade.createStudent(registrationNumber, parentalInfo, name,
					gender, addressLine, number, complement, nationality,
					homeland, cep, district, city, state, dddPhoneNumber,
					dddMobilePhoneNumber, phoneNumber, mobilePhoneNumber,
					email, grade, classId, shift, grade2, pictureUrl, active,
					rg, issuingDate, issuedBy, complementRg, stateRg, cpf,
					birthday, civilCertificate, numberTerm, sheet, book,
					dateIssueCertificate, stateOfOffice, nameOfOffice,
					enrollmentDate, scholarship, discount, startDisc, endDisc,
					language, langClass, race, birthplace, education,
					transportation, localization, deficiency, inep);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public void advanceGrade(String sessionId, List<Student> students)
			throws NoSuchSessionException, InvalidParameterException,
			NoSuchUserTypeException, NoSuchPermissionException {
		if (facade.existSession(sessionId)) {
			String typeUser = facade.getUserTypeLogged2(sessionId);
		//	accessPermissionsManager
		//			.verifyPermissionToAdvanceGradeAndEditStudents(typeUser);
			facade.studentManager.advanceGrade(students);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public int newStudent(String sessionId, Student s, ParentalInfo i,
			byte[] imagem) throws NoSuchSessionException,
			NoSuchPermissionException, NoSuchUserTypeException {
		if (facade.existSession(sessionId)) {
			String typeUser = facade.getUserTypeLogged2(sessionId);
			accessPermissionsManager
					.verifyPermissionToCreateAndEditStudents(typeUser);

			return facade.newStudent(s, i);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public String updateStudent(String sessionId, Student s)
			throws NoSuchPermissionException, NoSuchUserTypeException {
		if (facade.existSession(sessionId)) {
			String typeUser = facade.getUserTypeLogged2(sessionId);
			accessPermissionsManager
					.verifyPermissionToCreateAndEditStudents(typeUser);
			facade.updateStudent(s);
		} else {
			return new NoSuchSessionException().getMessage();
		}
		return "";
	}

	public void updateStudent1(String sessionId, Student s, ParentalInfo pi)
			throws NoSuchSessionException {
		if (facade.existSession(sessionId)) {
			facade.updateStudent(s, pi);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public List<Student> getStudents(String sessionId, String criteria,
			String value) throws DBException, NoSuchStudentException,
			GUIException, NoSuchSessionException, NoSuchUserTypeException,
			NoSuchPermissionException {
		if (facade.existSession(sessionId)) {
			String typeUser = facade.getUserTypeLogged2(sessionId);
			accessPermissionsManager
					.verifyPermissionToViewAndSearchStudents(typeUser);
			return facade.getStudents(criteria, value);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public int newDiscipline(String sessionId, Discipline d,
			ArrayList<HorariosDisciplinas> lista)
			throws NoSuchSessionException, NoSuchPermissionException,
			NoSuchUserTypeException {
		if (facade.existSession(sessionId)) {
			String typeUser = facade.getUserTypeLogged2(sessionId);
			accessPermissionsManager
					.verifyPermissionToCreateDisciplines(typeUser);
			return facade.newDiscipline(d, lista);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public String getStudentInfo(String sessionId, int id, String info)
			throws NoSuchStudentException, NoSuchSessionException {
		if (facade.existSession(sessionId)) {
			return facade.getStudentInfo(id, info);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public void deleteStudent(String sessionID, int id)
			throws NoSuchStudentException, NoSuchSessionException,
			NoSuchUserTypeException, NoSuchPermissionException {
		if (facade.existSession(sessionID)) {
			String typeUser = facade.getUserTypeLogged2(sessionID);
			accessPermissionsManager.verifyPermissionToRemoveStudents(typeUser);
			facade.deleteStudent(id);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public int retrieveStudent(String sessionID, int registrationNumber)
			throws DBException, NoSuchStudentException, NoSuchSessionException {
		if (facade.existSession(sessionID)) {
			return facade.retrieveStudent(registrationNumber);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public void updateStudent2(String sessionId, int id, String parentalInfo,
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
			String inep) throws NoSuchStudentException,
			InvalidParameterException, DBException, NoSuchSessionException {
		if (facade.existSession(sessionId)) {
			facade.updateStudent(id, parentalInfo, name, gender, addressLine,
					number, complement, nationality, homeland, cep, district,
					city, state, dddPhoneNumber, dddMobilePhoneNumber,
					phoneNumber, mobilePhoneNumber, email, grade, classId,
					shift, grade2, pictureUrl, active, rg, issuingDate,
					issuedBy, complementRg, stateRg, cpf, birthday,
					civilCertificate, numberTerm, sheet, book,
					dateIssueCertificate, stateOfOffice, nameOfOffice,
					enrollmentDate, scholarship, discount, startDisc, endDisc,
					language, langClass, race, birthplace, education,
					transportation, localization, deficiency, inep);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public int createTeacher(String sessionId, String idTeacher, String name,
			String email, String gender, String addressLine, String cep,
			String district, String city, String state, String nationality,
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
			throws InvalidParameterException, NoSuchSessionException {
		if (facade.existSession(sessionId)) {
			return facade.createTeacher(idTeacher, name, email, gender,
					addressLine, cep, district, city, state, nationality,
					homeland, dddPhoneNumber, dddMobilePhoneNumber,
					phoneNumber, mobilePhoneNumber, cpf, issuingDate, issuedBy,
					rg, complementRg, stateRg, birthday, civilCertificate,
					numberTerm, sheet, book, dateIssueCertificate,
					stateOfOffice, nameOfOffice, nickName, motherName,
					socialIdentificationNumber, dateOfAdmission, numberCTPS,
					gradeCTPS, dateCTPS, stateCTPS, numberVoting, areaElection,
					section, race, birthplace);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public int newTeacher(String sessionId, Teacher t)
			throws NoSuchSessionException, NoSuchPermissionException,
			NoSuchUserTypeException {
		if (facade.existSession(sessionId)) {
			String typeUser = facade.getUserTypeLogged2(sessionId);
			accessPermissionsManager.verifyPermissionToCreateTeachers(typeUser);
			return facade.newTeacher(t);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public List<Teacher> getTeachers(String sessionId, String crit, String value)
			throws DBException, NoSuchSessionException, GUIException,
			NoSuchPermissionException, NoSuchUserTypeException {
		if (facade.existSession(sessionId)) {
			String typeUser = facade.getUserTypeLogged2(sessionId);
			accessPermissionsManager
					.verifyPermissionToSearchAndViewTeachers(typeUser);
			return facade.getTeachers(crit, value);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public List<Teacher> getAllTeachers(String sessionId)
			throws NoSuchSessionException {
		if (facade.existSession(sessionId)) {
			return facade.getAllTeachers();
		} else {
			throw new NoSuchSessionException();
		}
	}

	public List<Student> getAllStudents(String sessionId)
			throws NoSuchSessionException {
		if (facade.existSession(sessionId)) {
			return facade.getAllStudents();
		} else {
			throw new NoSuchSessionException();
		}
	}

	public Integer createEquipe(String sessionId, Equipe e)
			throws NoSuchTeacherException, NoSuchSessionException {
		if (facade.existSession(sessionId)) {

			return equipesManager.createEquipe(e);

		} else {

			throw new NoSuchSessionException();
		}

	}

	public Integer createEquipeStudent(String sessionId, EquipeStudents es,
			Integer idEquipe) throws NoSuchTeacherException,
			NoSuchSessionException {
		if (facade.existSession(sessionId)) {

			return equipesManager.createEquipeStudent(es, idEquipe);

		} else {

			throw new NoSuchSessionException();
		}

	}

	public String getTeacherInfo(String sessionID, int id, String info)
			throws NoSuchTeacherException, NoSuchSessionException {
		if (facade.existSession(sessionID)) {
			return facade.getTeacherInfo(id, info);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public void deleteTeacher(String sessionID, int id)
			throws NoSuchTeacherException, NoSuchSessionException,
			NoSuchPermissionException, NoSuchUserTypeException {
		if (facade.existSession(sessionID)) {
			String typeUser = facade.getUserTypeLogged2(sessionID);
			accessPermissionsManager.verifyPermissionToRemoveTeachers(typeUser);
			facade.deleteTeacher(id);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public int retrieveTeacher(String sessionID, int idTeacher)
			throws DBException, NoSuchSessionException {
		if (facade.existSession(sessionID)) {
			return facade.retrieveTeacher(idTeacher);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public void updateTeacher(String sessionID, Teacher t)
			throws NoSuchSessionException, NoSuchUserTypeException,
			NoSuchPermissionException {

		if (facade.existSession(sessionID)) {
			String typeUser = facade.getUserTypeLogged2(sessionID);
			accessPermissionsManager.verifyPermissionToUpdateTeachers(typeUser);
			facade.updateTeacher(t);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public void updateTeacher1(String sessionId, int id, String name,
			String email, String gender, String addressLine, String cep,
			String district, String city,

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
			throws NoSuchTeacherException, InvalidParameterException,
			NoSuchSessionException {
		if (facade.existSession(sessionId)) {
			facade.updateTeacher(id, name, email, gender, addressLine, cep,
					district, city, state, nationality, homeland,

					dddPhoneNumber, dddMobilePhoneNumber, phoneNumber,
					mobilePhoneNumber, cpf, issuingDate, issuedBy, rg,
					complementRg, stateRg, birthday, civilCertificate,
					numberTerm, sheet, book, dateIssueCertificate,
					stateOfOffice, nameOfOffice, nickName, motherName,
					socialIdentificationNumber, dateOfAdmission, numberCTPS,
					gradeCTPS, dateCTPS, stateCTPS, numberVoting, areaElection,
					section, race, birthplace);
		} else {
			throw new NoSuchSessionException();
		}

	}

	public int createDiscipline(String sessionID, String name, int grade,
			String grade2, String area, String hoursPerWeek, String hoursPerYear)
			throws Exception, NoSuchSessionException {
		if (facade.existSession(sessionID)) {
			return facade.createDiscipline(name, grade, grade2, area,
					hoursPerWeek, hoursPerYear);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public String getDisciplineInfo(String sessionID, int id, String info)
			throws NoSuchDisciplineException, NoSuchSessionException {
		if (facade.existSession(sessionID)) {
			return facade.getDisciplineInfo(id, info);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public void deleteDiscipline(String sessionID, int id)
			throws NoSuchDisciplineException, NoSuchSessionException,
			NoSuchUserTypeException, NoSuchPermissionException {
		if (facade.existSession(sessionID)) {
			String typeUser = facade.getUserTypeLogged2(sessionID);
			accessPermissionsManager
					.verifyPermissionToRemoveDisciplines(typeUser);
			facade.deleteDiscipline(id);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public int retrieveDiscipline(String sessionID, int id) throws DBException,
			NoSuchSessionException {
		if (facade.existSession(sessionID)) {
			return facade.retrieveDiscipline(id);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public void updateDiscipline1(String sessionID, int id, String name,
			int grade, String grade2, String area, String hoursPerWeek,
			String hoursPerYear) throws NoSuchDisciplineException,
			InvalidParameterException, NoSuchStudentException,
			NoSuchSessionException {
		if (facade.existSession(sessionID)) {
			facade.updateDiscipline(id, name, grade, grade2, area,
					hoursPerWeek, hoursPerYear);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public void updateDiscipline(String sessionId, Discipline d)
			throws NoSuchSessionException, NoSuchUserTypeException,
			NoSuchPermissionException {
		if (facade.existSession(sessionId)) {
			String typeUser = facade.getUserTypeLogged2(sessionId);
			accessPermissionsManager
					.verifyPermissionToUpdateDisciplines(typeUser);
			facade.updateDiscipline(d);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public List<Discipline> getDisciplines(String sessionId, String criteria,
			String value) throws DBException, NoSuchStudentException,
			NoSuchSessionException, NoSuchUserTypeException,
			NoSuchPermissionException {
		if (facade.existSession(sessionId)) {
			String typeUser = facade.getUserTypeLogged2(sessionId);
			accessPermissionsManager
					.verifyPermissionToSearchAndViewDisciplines(typeUser);
			return facade.getDisciplines(criteria, value);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public int createTeacherDiscipline(String sessionId, TeacherDiscipline td)
			throws Exception {

		if (facade.existSession(sessionId)) {
			return facade.createTeacherDiscipline2(td);
		} else {
			throw new NoSuchSessionException();
		}

	}

	public int getTeacherDisciplineInfo(String sessionId, int id,
			TeacherDisciplineInfo info)
			throws NoSuchTeacherDisciplineException, NoSuchSessionException {
		if (facade.existSession(sessionId)) {
			return facade.getTeacherDisciplineInfo(id, info);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public List<Discipline> getTeacherDisciplines(String sessionId,
			int idTeacher, String grade2)
			throws NoSuchTeacherDisciplineException, NoSuchDisciplineException,
			NoSuchSessionException {
		if (facade.existSession(sessionId)) {
			return facade.getTeacherDisciplines(idTeacher, grade2);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public Discipline getDiscipline(int id) throws NoSuchDisciplineException {
		return disciplineManager.getDiscipline(id);
	}

	public List<Discipline> getDisciplinesByTeacher(String sessionId,
			int idTeacher) throws NoSuchTeacherDisciplineException,
			NoSuchDisciplineException, NoSuchSessionException,
			NoSuchTeacherException {
		if (facade.existSession(sessionId)) {
			return facade.getTeacherDisciplines(idTeacher);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public void deleteTeacherDiscipline(String sessionId, int id)
			throws NoSuchTeacherDisciplineException, NoSuchSessionException {
		if (facade.existSession(sessionId)) {
			facade.deleteTeacherDiscipline(id);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public void deleteTeacherDiscipline1(String sessionId, int idDisc,
			int idTeacher) throws NoSuchTeacherDisciplineException,
			NoSuchSessionException {
		if (facade.existSession(sessionId)) {
			facade.deleteTeacherDiscipline(idDisc, idTeacher);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public int retrieveTeacherDiscipline(String sessionId, int id)
			throws DBException, NoSuchSessionException {
		if (facade.existSession(sessionId)) {
			return facade.retrieveTeacherDiscipline(id);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public void updateTeacherDiscipline(String sessionId, int id, int teacher,
			int discipline) throws NoSuchDisciplineException,
			InvalidParameterException, NoSuchStudentException,
			NoSuchTeacherDisciplineException, NoSuchSessionException {
		if (facade.existSession(sessionId)) {
			facade.updateTeacherDiscipline(id, teacher, discipline);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public List<Discipline> getAllDisciplines(String sessionId)
			throws NoSuchSessionException, NoSuchPermissionException,
			NoSuchUserTypeException {

		if (facade.existSession(sessionId)) {

			String typeUser = facade.getUserTypeLogged2(sessionId);
			accessPermissionsManager
					.verifyPermissionToCreateDisciplines(typeUser);

			return disciplineManager.getAllDisciplines();

		} else {
			throw new NoSuchSessionException();
		}

	}

	private void createDownloadFolders(String sessionId) {
		String folders = DEFAULT_PATH + FILE_SEPARATOR + sessionId
				+ FILE_SEPARATOR;
		File file = new File(folders);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	public List<Discipline> getSubjectsByTeacher(String sessionId)
			throws NoSuchPermissionException, NoSuchUserTypeException,
			NoSuchTeacherDisciplineException, NoSuchDisciplineException,
			NoSuchSessionException, NoSuchTeacherException {
		if (facade.existSession(sessionId)) {
			String typeUser = facade.getUserTypeLogged2(sessionId);
			accessPermissionsManager.verifyPermissionToPlanning(typeUser);
			return facade.getTeacherDisciplines(facade.getEntitieId(sessionId));
		} else {
			throw new NoSuchSessionException();
		}
	}

	public int getEntityIdLogged(String sessionId)
			throws NoSuchSessionException, NoSuchUserTypeException {
		if (facade.existSession(sessionId)) {
			return facade.getEntitieId(sessionId);
		} else {
			throw new NoSuchSessionException();
		}
	}

	public Notas getNotasAll(String sessionId, String idDisciplina,
			String idAluno) throws SQLException, NoSuchSessionException {
		if (facade.existSession(sessionId)) {

			return notasManager.getNotasAll2(idDisciplina, idAluno);

		} else {
			throw new NoSuchSessionException();
		}

	}

	public Equipe getEquipeStudent(String sessionId, String id_student,
			String serie, String grau, String bimestre)
			throws NoSuchSessionException {
		if (facade.existSession(sessionId)) {

			return notasManager.getEquipeStudent(id_student, serie, grau,
					bimestre);

		} else {
			throw new NoSuchSessionException();
		}
	}

	public List<Student> getStudentsGrauSerie(String grade, String classId,
			String grade2) {
		return facade.getStudents2(grade, classId, grade2);

	}

	public void CriarAvaliacaoAnual(Notas nota) {
		notasManager.criarNotasBimestre(nota);
	}

	public void updateNotas(Notas nota) {
		notasManager.updateNotas(nota);
	}

	public void criarRegistroDeAula(RegistroDeAula reg) {
		registroAulaManager.adicionarTurmas(reg);

	}

	public List<RegistroDeAula> listarRegistros(String idProfessor) {
		return registroAulaManager.getRegistrosAulaDoProfessor(idProfessor);

	}

	public void updateRegistroAulas(RegistroDeAula reg) {
		registroAulaManager.updateRegistro(reg);
	}

	public void removeRegistro(RegistroDeAula reg) {
		registroAulaManager.deleteRegistro(reg);
	}

	public List<RegistroDeAula> getRegistrosFiltro(String info, Object value,
			String id) throws DBException {
		return registroAulaManager.getRegistrosFiltro(info, value, id);

	}

	public int criarHorarioDisciplina(String sessionId,
			HorariosDisciplinas horario) throws NoSuchUserTypeException,
			NoSuchPermissionException, NoSuchSessionException {
		if (facade.existSession(sessionId)) {
			String typeUser = facade.getUserTypeLogged2(sessionId);
			accessPermissionsManager.verifyPermissionToUserOperations(typeUser);
			return disciplineManager.adicionarHorarios(horario);
		} else {
			throw new NoSuchSessionException();
		}

	}

	public List<Equipe> getEquipesTeacher(String sessionId, String id)
			throws NoSuchSessionException {
		if (facade.existSession(sessionId)) {

			return equipesManager.getEquipesTeacher(id);

		} else {

			throw new NoSuchSessionException();
		}
	}

	public void updateEquipe(String sessionId, Equipe e)
			throws NoSuchSessionException {
		if (facade.existSession(sessionId)) {

			equipesManager.upadateEquipe(e);

		} else {

			throw new NoSuchSessionException();
		}
	}

	public List<Equipe> getEquipesStudent(String sessionId, String id)
			throws NoSuchSessionException, SQLException, ParseException {
		if (facade.existSession(sessionId)) {

			return equipesManager.getEquipesStudent(id);

		} else {

			throw new NoSuchSessionException();
		}
	}

	public void deleteEquipe(String sessionId, Equipe e)
			throws NoSuchSessionException {
		if (facade.existSession(sessionId)) {

			equipesManager.deleteEquipe(e);

		} else {

			throw new NoSuchSessionException();
		}
	}

	public List<Student> getStudentsEquipes(String sessionId, String id_equipe)
			throws NoSuchSessionException, NoSuchStudentException {
		if (facade.existSession(sessionId)) {

			return equipesManager.getStudentsE(id_equipe);

		} else {

			throw new NoSuchSessionException();
		}
	}

	public List<HorariosDisciplinas> getHorariosDisciplina(String id) {
		return disciplineManager.getHorariosDisciplina(id);

	}

	public void removeHorarios(HorariosDisciplinas h) throws SQLException {
		disciplineManager.RemoverHorarios(h);
	}

	public List<Frequencia> loadFrequenciaAluno(String sessionId, String grau,
			String serie, String data, String turma, String id_disciplina)
			throws SQLException, ParseException, NoSuchUserTypeException,
			NoSuchPermissionException, NoSuchSessionException {
		if (facade.existSession(sessionId)) {
			String typeUser = facade.getUserTypeLogged2(sessionId);
			// accessPermissionsManager.verifyPermissionToUserOperations(typeUser);
			return frequenciaManager.loadFrequenciaAluno(grau, serie, data,
					turma, id_disciplina);

		} else {
			throw new NoSuchSessionException();
		}
	}

	public void newFrequencia(ArrayList<Frequencia> frequencia) {

		frequenciaManager.newFrequencia(frequencia);

	}

	public List<Frequencia> getFrequenciaStudent(String sessionId, String id,
			String grau, String serie, String id_disc)
			throws NoSuchPermissionException, NoSuchUserTypeException,
			NoSuchSessionException {
		if (facade.existSession(sessionId)) {

			return frequenciaManager.getFrequenciaStudent(id, grau, serie,
					id_disc);

		} else {
			throw new NoSuchSessionException();
		}

	}

	public void createFrequencia(String sessionId, Frequencia f)
			throws NoSuchPermissionException, NoSuchUserTypeException,
			NoSuchSessionException {
		if (facade.existSession(sessionId)) {

			frequenciaManager.createFrequencia(f);

		} else {
			throw new NoSuchSessionException();
		}

	}

	public void newMaterialApoio(String sessionId, MaterialDeApoio m)
			throws NoSuchSessionException {
		if (facade.existSession(sessionId)) {

			materialManager.createMaterialApoio(m);

		} else {
			throw new NoSuchSessionException();
		}

	}

	public List<MaterialDeApoio> getMaterialApoio(String sessionId)
			throws NoSuchSessionException {
		if (facade.existSession(sessionId)) {

			return materialManager.getMaterialApoio();

		} else {
			throw new NoSuchSessionException();
		}

	}

	public void updateFrequencia(String sessionId, Frequencia f)
			throws NoSuchUserTypeException, NoSuchPermissionException,
			NoSuchSessionException {
		if (facade.existSession(sessionId)) {
			String typeUser = facade.getUserTypeLogged2(sessionId);

			frequenciaManager.updateFrequencia(f);

		} else {
			throw new NoSuchSessionException();
		}

	}

	public void removeFrequencia(String sessionId, Frequencia f)
			throws NoSuchPermissionException, NoSuchUserTypeException,
			NoSuchSessionException {
		if (facade.existSession(sessionId)) {
			String typeUser = facade.getUserTypeLogged2(sessionId);
			accessPermissionsManager.verifyPermissionToUserOperations(typeUser);
			frequenciaManager.removeFrequencia(f);

		} else {
			throw new NoSuchSessionException();
		}

	}

	public List<Frequencia> DisciplinasFrequencia(String grau, String serie,
			String dia, String turma) throws SQLException, ParseException {
		return frequenciaManager.DisciplinasFrequencia(grau, serie, turma);
	}

	public List<User> getAllUser() {

		return facade.getAllUser();
	}

	public String generateMultipleStudentReportWithGeneralInformationsToAllStudents(
			String sessionId) throws Exception {
		if (facade.existSession(sessionId)) {
			// String typeUser = facade.getUserTypeLogged2(sessionId);
			// accessPermissionsManager.verifyPermissionToGenerateReportsStudents(typeUser);
			this.createDownloadFolders(sessionId);
			String fileName = "relatorioDeDadosGeraisAlunos.pdf";
			String path = DEFAULT_PATH + FILE_SEPARATOR + sessionId
					+ FILE_SEPARATOR + fileName;
			facade.generateMultipleStudentReportWithAllInformationsToAllStudents(path);
			return path;

		} else {
			throw new NoSuchSessionException();
		}
	}

	public String generateMultipleDisciplinesReportToAllDisciplines(
			String sessionId) throws Exception {
		if (facade.existSession(sessionId)) {
			String typeUser = facade.getUserTypeLogged2(sessionId);
			accessPermissionsManager
					.verifyPermissionToGenerateReportsDisciplines(typeUser);
			this.createDownloadFolders(sessionId);
			String fileName = "relatorioDeTodasAsDisciplinas.pdf";
			String path = DEFAULT_PATH + FILE_SEPARATOR + sessionId
					+ FILE_SEPARATOR + fileName;
			facade.generateMultipleDisciplinesReportToAllDisciplines(path);
			return path;
		} else {
			throw new NoSuchSessionException();
		}
	}

	public String generateMultipleDisciplinesReportToAllDisciplinesByGradeAndGrade2(
			String sessionId, int grade, String grade2) throws Exception {
		if (facade.existSession(sessionId)) {
			String typeUser = facade.getUserTypeLogged2(sessionId);
			accessPermissionsManager
					.verifyPermissionToGenerateReportsDisciplines(typeUser);
			this.createDownloadFolders(sessionId);
			String fileName = "relatorioDeDisciplinas" + grade + "a" + "Serie"
					+ grade2 + ".pdf";
			String path = DEFAULT_PATH + FILE_SEPARATOR + sessionId
					+ FILE_SEPARATOR + fileName;
			facade.generateMultipleDisciplinesReportToAllDisciplinesByGradeAndGrade2(
					path, Integer.toString(grade), grade2);
			return path;
		} else {
			throw new NoSuchSessionException();
		}
	}

	public String generateMultipleTeachersReportToAllTeachers(String sessionId)
			throws Exception {
		if (facade.existSession(sessionId)) {
			String typeUser = facade.getUserTypeLogged2(sessionId);
			accessPermissionsManager
					.verifyPermissionToGenerateReportsTeachers(typeUser);
			this.createDownloadFolders(sessionId);
			String fileName = "relatorioDeTodosOsProfessores.pdf";
			String path = DEFAULT_PATH + FILE_SEPARATOR + sessionId
					+ FILE_SEPARATOR + fileName;
			facade.generateMultipleTeachersReportToAllTeachers(path);
			return path;
		} else {
			throw new NoSuchSessionException();
		}
	}

	public String gerarRelatoriaRegistroDeAulas(String sessionId,
			String disciplina, String nomeProfessor, String serie, String turma) {
		this.createDownloadFolders(sessionId);
		String fileName = "registrodeAulas.pdf";
		String path = DEFAULT_PATH + FILE_SEPARATOR + sessionId
				+ FILE_SEPARATOR + fileName;
		facade.reportManager.generateReportsRegistroDeAulas(path, disciplina,
				nomeProfessor, serie, turma);
		return path;
	}

	public String gerarHistoricoBolentin(String sessionId, int id_aluno,
			String ano) {
		this.createDownloadFolders(sessionId);
		String fileName = "boletin_" + id_aluno + "_" + ano + ".pdf";
		String path = DEFAULT_PATH + FILE_SEPARATOR + sessionId
				+ FILE_SEPARATOR + fileName;
		facade.reportManager.generateReportsBoletin(path, id_aluno, ano);
		return path;
	}

	public String gerarRelatorioEquipes(String sessionId, String serie,
			String grau, String turma, String id_disciplina, String id_teacher,
			String ano) throws Exception {
		this.createDownloadFolders(sessionId);
		String fileName = "relatorioEquipes_" + serie + "_" + grau + "_"
				+ turma + "_" + ano + ".pdf";
		String path = DEFAULT_PATH + FILE_SEPARATOR + sessionId
				+ FILE_SEPARATOR + fileName;
		facade.reportManager.generateEquipesStudent(path, serie, grau, turma,
				id_disciplina, id_teacher, ano);
		return path;
	}

	public String generateFrequeciaAlunos(String sessionId, String serie,
			String grau, String turma, String id, String prof) {
		this.createDownloadFolders(sessionId);
		String fileName = "frequenciaAlunos.pdf";
		String path = DEFAULT_PATH + FILE_SEPARATOR + sessionId
				+ FILE_SEPARATOR + fileName;
		try {
			reportManager.generateFrequeciaAlunos(path, serie, grau, turma, id,
					prof);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}

	public String generateMultipleStudentReportWithGeneralInformationsByGradeEtc(
			String sessionId, int grade, String classId, String grade2)
			throws Exception {
		if (facade.existSession(sessionId)) {
			String typeUser = facade.getUserTypeLogged2(sessionId);
			accessPermissionsManager
					.verifyPermissionToGenerateReportsStudents(typeUser);
			this.createDownloadFolders(sessionId);
			String fileName = "relatorioDeDadosGeraisAlunos" + grade + "a"
					+ "Serie" + classId + grade2 + ".pdf";
			String path = DEFAULT_PATH + FILE_SEPARATOR + sessionId
					+ FILE_SEPARATOR + fileName;
			facade.generateMultipleStudentReportWithGeneralInformationsByGradeEtc(
					path, Integer.toString(grade), classId, grade2);
			return path;
		} else {
			throw new NoSuchSessionException();
		}

	}
}