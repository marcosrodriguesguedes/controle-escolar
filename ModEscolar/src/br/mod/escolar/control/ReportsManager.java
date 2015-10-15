package br.mod.escolar.control;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import br.mod.escolar.control.DisciplineManager.DisciplineInfo;
import br.mod.escolar.control.TeacherManager.TeacherInfo;
import br.mod.escolar.jdbc.ConnectionFactory;
import br.mod.escolar.model.entities.Discipline;
import br.mod.escolar.model.entities.HistoricoRelatorio;
import br.mod.escolar.model.entities.Student;
import br.mod.escolar.model.entities.Teacher;
import br.mod.escolar.model.exception.NoSuchDisciplineException;
import br.mod.escolar.model.util.EquipeReport;
import br.mod.escolar.model.util.GradesIdentifier;
import br.mod.escolar.model.util.RealPathApp;
import br.mod.escolar.model.util.ReportFrequencia;
import br.mod.escolar.relatorios.beans.DisciplineReportDataBean;
import br.mod.escolar.relatorios.beans.EquipesReportDataBean;
import br.mod.escolar.relatorios.beans.FrequenciaReportDataBean;
import br.mod.escolar.relatorios.beans.StudentReportDataBean;
import br.mod.escolar.relatorios.beans.StudentReportGeneralDataBean;
import br.mod.escolar.relatorios.beans.StudentReportPresenceListDataBean;
import br.mod.escolar.relatorios.beans.TeacherReportDataBean;
import br.mod.escolar.relatorios.composers.ComposerIF;
import br.mod.escolar.relatorios.composers.DisciplineBeanDataSourceComposer;
import br.mod.escolar.relatorios.composers.EquipesBeanDataSourceComposer;
import br.mod.escolar.relatorios.composers.FrequenciaBeanDataSourceComposer;
import br.mod.escolar.relatorios.composers.ReportGenerator;
import br.mod.escolar.relatorios.composers.StudentBeanDataSourceComposer;
import br.mod.escolar.relatorios.composers.StudentBeanGeneralDataSourceComposer;
import br.mod.escolar.relatorios.composers.StudentBeanPresenceListDataSourceComposer;
import br.mod.escolar.relatorios.composers.TeacherBeanDataSourceComposer;

import com.mysql.jdbc.Connection;

public class ReportsManager {
	private static ReportsManager manager = null;
	// public StudentManager studentManager;
	public DisciplineManager disciplineManager;
	// public EmployeeManager employeeManager;
	public TeacherManager teacherManager;
	
	public HistoricoManager operationsHist;

	private ReportFrequencia report;

	private ReportsManager() {
		// studentManager = StudentManager.getInstance();
		disciplineManager = DisciplineManager.getInstance();
		// employeeManager = EmployeeManager.getEmployeeManager();
		teacherManager = TeacherManager.getInstance();
		
		operationsHist = HistoricoManager.getInstance();

	}

	/**
	 * Padrão Singleton
	 * 
	 */
	public static synchronized ReportsManager getInstance() {
		if (manager == null) {
			manager = new ReportsManager();
		}
		return manager;
	}

	private void generateMultipleStudentReportWithAllInformations(String path,
			List<Student> students) throws Exception {
		ComposerIF<Student, StudentReportDataBean> composer = new StudentBeanDataSourceComposer();
		String initialPath = RealPathApp.getPathApp();
		ReportGenerator<Student, StudentReportDataBean> report = new ReportGenerator<Student, StudentReportDataBean>(
				initialPath + "/resources/AlunoReportSimple.jasper");
		report.generateMultipleObjectsReport(students, composer);
		report.exportJasperToPDF(new File(path));
	}

	private void generateMultipleStudentReportWithGeneralInformations(
			String path, List<Student> students, String titleReport)
			throws Exception {
		ComposerIF<Student, StudentReportGeneralDataBean> composer = new StudentBeanGeneralDataSourceComposer(
				titleReport);
		String initialPath = RealPathApp.getPathApp();
		ReportGenerator<Student, StudentReportGeneralDataBean> report = new ReportGenerator<Student, StudentReportGeneralDataBean>(
				initialPath + "/resources/AlunoReportDadosGerais.jasper");
		report.generateMultipleObjectsReport(students, composer);
		report.exportJasperToPDF(new File(path));
	}

	private void generateMultipleStudentReportPersenceList(String path,
			List<Student> students, String titleReport) throws Exception {
		ComposerIF<Student, StudentReportPresenceListDataBean> composer = new StudentBeanPresenceListDataSourceComposer(
				titleReport);
		String initialPath = RealPathApp.getPathApp();
		ReportGenerator<Student, StudentReportPresenceListDataBean> report = new ReportGenerator<Student, StudentReportPresenceListDataBean>(
				initialPath + "/resources/AlunoReportListaDePresenca.jasper");
		report.generateMultipleObjectsReport(students, composer);
		report.exportJasperToPDF(new File(path));
	}

	private void generateMultipleDisciplinesReport(String path,
			List<Discipline> disciplines, String titleReport) throws Exception {
		ComposerIF<Discipline, DisciplineReportDataBean> composer = new DisciplineBeanDataSourceComposer(
				titleReport);
		String initialPath = RealPathApp.getPathApp();
		ReportGenerator<Discipline, DisciplineReportDataBean> report = new ReportGenerator<Discipline, DisciplineReportDataBean>(
				initialPath + "/resources/DisciplinasReport.jasper");
		report.generateMultipleObjectsReport(disciplines, composer);
		report.exportJasperToPDF(new File(path));
	}
	
	private void generateEquipesAlunos(String path,
			List<EquipeReport> equipes,String serie,String grau,String turma,String id_disciplina,String id_teacher,String ano) throws Exception {
		ComposerIF<EquipeReport, EquipesReportDataBean> composer = new EquipesBeanDataSourceComposer();
		String initialPath = RealPathApp.getPathApp();
		ReportGenerator<EquipeReport, EquipesReportDataBean> report = new ReportGenerator<EquipeReport, EquipesReportDataBean>(
				initialPath + "resources/equipes.jasper");
		report.generateMultipleObjectsReportEquipes(equipes, composer,serie,grau,turma,id_disciplina,id_teacher,ano);
		report.exportJasperToPDF(new File(path));
	}

	

	private void generateFrequenciaAlunos(String path,
			List<ReportFrequencia> frequencia) throws Exception {
		ComposerIF<ReportFrequencia, FrequenciaReportDataBean> composer = new FrequenciaBeanDataSourceComposer();
		String initialPath = RealPathApp.getPathApp();
		ReportGenerator<ReportFrequencia, FrequenciaReportDataBean> report = new ReportGenerator<ReportFrequencia, FrequenciaReportDataBean>(
				initialPath + "resources/frequencia.jasper");
		report.generateMultipleObjectsReport(frequencia, composer);
		report.exportJasperToPDF(new File(path));
	}

	public void generateMultipleStudentReportWithAllInformationsToAllStudents(
			String path, List<Student> students) throws Exception {
		// List<Student> students = studentManager.getAllStudents();
		this.generateMultipleStudentReportWithAllInformations(path, students);
	}
	
	
	public void generateEquipesStudent(String path, String serie, String grau,String turma,String id_disciplina,String id_teacher,String ano) throws Exception{
		
		//List<EquipeReport> equipes = EquipeManager.getInstance().getRelatorioEquipes(serie, grau, turma) ;
		
		List<EquipeReport> equipes = new ArrayList<EquipeReport>();
		generateEquipesAlunos(path, equipes,serie,grau,turma,id_disciplina,id_teacher,ano);
		
	}

	public void generateFrequeciaAlunos(String path, String serie, String grau,
			String turma,String idDisciplina,String professor) throws Exception {
		List<ReportFrequencia> frequencia = this.generateReportFrequencia(
				serie, grau, turma,idDisciplina,professor);
		generateFrequenciaAlunos(path, frequencia);
	}

	public void generateMultipleStudentReportWithAllInformationsByGradeEtc(
			String path, String grade, String classId, String grade2,
			List<Student> students) throws Exception {
		// List<Student> students = studentManager.getAllStudentsBy(grade,
		// classId, grade2);
		this.generateMultipleStudentReportWithAllInformations(path, students);
	}

	public void generateMultipleStudentReportWithGeneralInformationsToAllStudents(
			String path, List<Student> students) throws Exception {
		// List<Student> students = studentManager.getAllStudents();
		this.generateMultipleStudentReportWithGeneralInformations(path,
				students, "Relatório Geral de Alunos");
	}

	public void generateMultipleStudentReportWithGeneralInformationsByGradeEtc(
			String path, String grade, String classId, String grade2,
			List<Student> students) throws Exception {
		// List<Student> students = studentManager.getAllStudentsBy(grade,
		// classId, grade2);
		// String titleReport = "Alunos da "+grade+"ª "+classId+" do "+grade2;
		String titleReport = makeTitleToStudentsReport(grade, classId, grade2);
		this.generateMultipleStudentReportWithGeneralInformations(path,
				students, titleReport);
	}

	public void generateMultipleStudentReportWithPresenceListInformationsToAllStudents(
			String path, List<Student> students) throws Exception {
		// List<Student> students = studentManager.getAllStudents();
		this.generateMultipleStudentReportPersenceList(path, students,
				"Relatório Geral de Alunos");
	}

	public void generateMultipleStudentReportWithPresenceListInformationsByGradeEtc(
			String path, String grade, String classId, String grade2,
			List<Student> students) throws Exception {
		// List<Student> students = studentManager.getAllStudentsBy(grade,
		// classId, grade2);
		// String titleReport = "Alunos da "+grade+"ª "+classId+" do "+grade2;
		String titleReport = makeTitleToStudentsReport(grade, classId, grade2);
		this.generateMultipleStudentReportPersenceList(path, students,
				titleReport);
	}

	public void generateMultipleDisciplinesReportToAllDisciplinesByGradeAndGrade2(
			String path, String grade, String grade2,
			List<Discipline> disciplines) throws Exception {
		// List<Discipline> disciplines =
		// disciplineManager.getAllDisciplinesByGradeAndGrade2(Integer.parseInt(grade),
		// grade2);
		String titleReport = makeTitleToDisciplinesReport(grade, grade2);
		this.generateMultipleDisciplinesReport(path, disciplines, titleReport);
	}

	public void generateMultipleDisciplinesReportToAllDisciplines(String path,
			List<Discipline> disciplines) throws Exception {
		// List<Discipline> disciplines = disciplineManager.getAllDisciplines();
		String titleReport = "Relatório Geral de Disciplinas";
		this.generateMultipleDisciplinesReport(path, disciplines, titleReport);
	}

	public void generateMultipleTeachersReportToAllTeachers(String path,
			List<Teacher> teachers) throws Exception {
		// List<Teacher> teachers = teacherManager.getAllTeachers();
		String titleReport = "Relatório Geral de Professores";
		this.generateMultipleTeachersReport(path, teachers, titleReport);
	}

	private String makeTitleToStudentsReport(String grade, String classId,
			String grade2) {
		String title = "";
		if (grade2.equals(GradesIdentifier.EI)) {
			if (grade.equals(GradesIdentifier.G0)) {
				title = "Alunos do Maternal " + classId + " do " + grade2;
			} else {
				title = "Alunos da " + grade + "ª " + classId + " do " + grade2;
			}
		} else if (grade2.equals(GradesIdentifier.EM)) {
			if (grade.equals(GradesIdentifier.G4)) {
				title = "Alunos do Pré-Vestibular " + classId + " do " + grade2;
			} else {
				title = "Alunos da " + grade + "ª Série " + classId + " do "
						+ grade2;
			}

		} else if (grade2.equals(GradesIdentifier.EF)) {
			if (grade.equals(GradesIdentifier.G4)) {
				title = "Alunos do Pré-Vestibular " + classId + " do " + grade2;
			} else {
				title = "Alunos do " + grade + "º Ano " + classId + " do "
						+ grade2;
			}

		} else if (grade2.equals(GradesIdentifier.EP)) {
			if (grade.equals(GradesIdentifier.G1)) {
				title = "Alunos do Profissional " + classId + " do " + grade2;
			} else {
				title = "Alunos da " + grade + "ª " + classId + " do " + grade2;
			}
		} else {
			title = "Alunos da " + grade + "ª " + classId + " do " + grade2;
		}
		return title;
	}

	private String makeTitleToDisciplinesReport(String grade, String grade2) {
		String title = "";
		if (grade2.equals(GradesIdentifier.EI)) {
			if (grade.equals(GradesIdentifier.G0)) {
				title = "Alunos do Maternal do " + grade2;
			}
		} else if (grade2.equals(GradesIdentifier.EM)) {
			if (grade.equals(GradesIdentifier.G4)) {
				title = "Alunos do Pré-Vestibular do " + grade2;
			} else {
				title = "Relatório de Disciplinas da " + grade + "ª Série do "
						+ grade2;
			}
		} else if (grade2.equals(GradesIdentifier.EFI)) {
			title = "Relatório de Disciplinas da " + grade + "º Ano do "
					+ grade2;
		} else if (grade2.equals(GradesIdentifier.EFII)) {
			title = "Relatório de Disciplinas da " + grade + "º Ano do "
					+ grade2;
		} else if (grade2.equals(GradesIdentifier.EP)) {
			if (grade.equals(GradesIdentifier.G1)) {
				title = "Disciplinas da" + grade + "ª Série do " + grade2;
			}
		} else {
			title = "Relatório de Disciplinas da " + grade + "ª Série do "
					+ grade2;
		}
		return title;
	}

	// TODO a partir daqui

	private void generateMultipleTeachersReport(String path,
			List<Teacher> teachers, String titleReport) throws Exception {
		ComposerIF<Teacher, TeacherReportDataBean> composer = new TeacherBeanDataSourceComposer(
				titleReport);
		ReportGenerator<Teacher, TeacherReportDataBean> report = new ReportGenerator<Teacher, TeacherReportDataBean>(
				RealPathApp.getPathApp()
						+ "/resources/ProfessoresEFuncionariosReport.jasper");
		report.generateMultipleObjectsReport(teachers, composer);
		report.exportJasperToPDF(new File(path));
	}

	public void generateReportsRegistroDeAulas(String pach,
			String iddisciplina, String idprofessor, String serie, String turma) {
		try {

			Map<String, Object> parametros = new HashMap<String, Object>();

			parametros.put("disciplina", disciplineManager.getDisciplineInfo(
					Integer.parseInt(iddisciplina), DisciplineInfo.NAME));
			parametros.put("professor", teacherManager.getTeacherInfo(
					Integer.parseInt(idprofessor), TeacherInfo.NAME));
			parametros.put("Serie", serie);
			parametros.put("turma", turma);
			parametros.put("ano", "2014");

			ConnectionFactory conexao = new ConnectionFactory();

			Connection con = (Connection) conexao.getConnection();

			PreparedStatement stmt = con
					.prepareStatement("SELECT  e.habilidades,e.objetosDeConhecimento,e.numeroAula,e.data,e.competencias FROM  registro_de_aula e 	Where e.idDisciplina = '"
							+ iddisciplina
							+ "' and e.idProfessor = '"
							+ idprofessor + "' and turma = '" + turma + "'");

			ResultSet res = stmt.executeQuery();

			/* implementação da interface JRDataSource para DataSource ResultSet */
			JRResultSetDataSource jrRS = new JRResultSetDataSource(res);

			String initialPath = RealPathApp.getPathApp();
			// parametros.put("TotalContratado",valores[0].toString());

			JasperReport jr = (JasperReport) JRLoader.loadObject(initialPath
					+ "resources/teste2.jasper");

			JasperPrint impressao = JasperFillManager.fillReport(jr,
					parametros, jrRS);

			JasperExportManager.exportReportToPdfFile(impressao, pach);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("quebrou");
		}

	}
	
	public void generateReportsBoletin(String pach,int idStudent,String anoLetivo) {
		try {

			Map<String, Object> parametros = new HashMap<String, Object>();

			List<HistoricoRelatorio> hist = operationsHist.generateHistorico(idStudent, anoLetivo);

			ConnectionFactory conexao = new ConnectionFactory();

			Connection con = (Connection) conexao.getConnection();
			
			if(null != hist){
				
				int indexParam = 1;
				
				for (int i = 0; i < hist.size(); i++) {
					
					int ref = 0;
					
					for (int j = indexParam; j < indexParam+10; j++) {
						
						
						String parameter  = "parameter";
						
						parameter += j;
						
						
						switch (ref) {
						
						case 0:
							
							parametros.put(parameter, hist.get(i).getMb1());
							indexParam++;
							break;
							
						case 1:
							
							parametros.put(parameter, hist.get(i).getMb2());
							indexParam++;
							break;	
						case 2:
							
							parametros.put(parameter, hist.get(i).getMb3());
							indexParam++;
							break;
						case 3:
							
							parametros.put(parameter, hist.get(i).getMb4());
							indexParam++;
							break;
						
						case 4:
							
							parametros.put(parameter, hist.get(i).getMediaPacial());
							indexParam++;
							break;
						
						case 5:
							
							parametros.put(parameter, hist.get(i).getProvaFinal());
							indexParam++;
							break;
							
						case 6:
							
							parametros.put(parameter, hist.get(i).getHorasAula());
							indexParam++;
							break;	
						case 7:
							
							parametros.put(parameter, hist.get(i).getFaltas());
							indexParam++;
							break;
						case 8:
							
							parametros.put(parameter, hist.get(i).getFrequencia());
							indexParam++;
							break;
						
						case 9:
							
							parametros.put(parameter, hist.get(i).getMediaFinal());
							indexParam++;
							break;
						
						

						default:
							break;
						}
						
						ref++;
						
						
					}
					
					
					
				}
				
			}

		
			String initialPath = RealPathApp.getPathApp();
			// parametros.put("TotalContratado",valores[0].toString());

			JasperReport jr = (JasperReport) JRLoader.loadObject(initialPath
					+ "resources/PrincipalVersoHistorico.jasper");

			JasperPrint impressao = JasperFillManager.fillReport(jr,
					parametros, con);

			JasperExportManager.exportReportToPdfFile(impressao, pach);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("erro ao gerar boletin");
		}

	}

	private List<ReportFrequencia> generateReportFrequencia(String serie,
			String grau, String turma,String id_disciplina,String professor) throws SQLException, NoSuchDisciplineException {
		
		String falta = "F";

		ConnectionFactory conexao = new ConnectionFactory();

		Connection con = (Connection) conexao.getConnection();

		PreparedStatement stmt = con
				.prepareStatement("select fr.nomeDisciplina, count(*), fr.grau, fr.serie, fr.turma, idDisciplina  from frequencia fr Where fr.frequencia = '"+ falta +"' and fr.serie = '"+ serie +"' and fr.grau = '"+ grau +"'and fr.iddisciplina = '"+ id_disciplina +"' and fr.turma = '"+ turma +"' group by fr.nomeDisciplina");

		java.sql.ResultSet rs = stmt.executeQuery();

		rs.last();

		rs.beforeFirst();

		

		List<ReportFrequencia> lista = new ArrayList<ReportFrequencia>();

		rs.next();

		rs.last();

		rs.beforeFirst();

		while (rs.next()) {

			report = new ReportFrequencia();
			
			report.setNomeAluno(rs.getString(1));
			
			report.setFaltas(String.valueOf(rs.getInt(2)));
			
			report.setGrau(rs.getString(3));
			
			report.setSerie(rs.getString(4));
			
			report.setTurma(rs.getString(5));
			
			report.setDisciplina(disciplineManager.getDisciplineInfo(rs.getInt(6), DisciplineInfo.NAME));
			
			report.setProfessor(professor);
			lista.add(report);
		
		}

		
		
		return lista;

	}

}
