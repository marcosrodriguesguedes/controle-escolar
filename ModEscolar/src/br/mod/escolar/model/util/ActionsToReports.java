package br.mod.escolar.model.util;



public class ActionsToReports {

	//http://localhost:8080/NewSGE2011/download?action=generateMultipleStudentReportWithAllInformationsToAllStudents&sessionId=3
	public static final String ACTION_STUDENTS_REPORT_COMPLETE_ALL = "generateMultipleStudentReportWithAllInformationsToAllStudents";

	public static final String ACTION_STUDENTS_REPORT_COMPLETE_BY_GRADE_ETC = "generateMultipleStudentReportWithAllInformationsByGradeEtc";

	public static final String ACTION_STUDENTS_REPORT_GENERAL_ALL = "generateMultipleStudentReportWithGeneralInformationsToAllStudents";

	public static final String ACTION_STUDENTS_REPORT_GENERAL_BY_GRADE_ETC = "generateMultipleStudentReportWithGeneralInformationsByGradeEtc";

	public static final String ACTION_STUDENTS_REPORT_PRESENCE_LIST_ALL = "generateMultipleStudentReportWithPresenceListInformationsToAllStudents";

	public static final String ACTION_STUDENTS_REPORT_PRESENCE_LIST_ALL_BY_GRADE_ETC = "generateMultipleStudentReportWithPresenceListInformationsByGradeEtc";

	public static final String ACTION_STUDENTS_REPORT_FAMILY_INFORMATIONS_ALL = "generateMultipleStudentReportWithFamilyInformationsToAllStudents";

	public static final String ACTION_STUDENTS_REPORT_FAMILY_INFORMATIONS_BY_GRADE_ETC = "generateMultipleStudentReportWithFamilyInformationsByGradeEtc";

	public static final String ACTION_DISCIPLINES_REPORT_ALL_BY_GRADE_AND_GRADE2 = "generateMultipleDisciplinesReportToAllDisciplinesByGradeAndGrade2";
	
	public static final String ACTION_DISCIPLINES_REPORT_ALL = "generateMultipleDisciplinesReportToAllDisciplines";

	public static final String ACTION_EMPLOYEES_REPORT_DISCIPLINES_ALL = "generateMultipleEmployeesReportToAllEmployees";

	public static final String ACTION_TEACHERS_REPORT_DISCIPLINES_ALL = "generateMultipleTeachersReportToAllTeachers";
	
	public static final String ACTION_EFII_REPORT = "generateEFIIReport";

	public static final Object ACTION_EI_REPORT = "generateEIReport";
	
	public static final Object ACTION_EM_REPORT = "generateEMReport";
	
	public static final String ACTION_EM_REPORT_REGISTRO_AULAS = "generateEmRegistroAulas";
	
	public static final String ACTION_EM_REPORT_FREQUENCIA = "generateFrequeciaAlunos";
	
	public static final String 	DOWNLOAD_REF = "downloadRef";

}
