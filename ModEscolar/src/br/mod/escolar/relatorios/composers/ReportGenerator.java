package br.mod.escolar.relatorios.composers;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import br.mod.escolar.control.DisciplineManager;
import br.mod.escolar.control.DisciplineManager.DisciplineInfo;
import br.mod.escolar.control.TeacherManager;
import br.mod.escolar.control.TeacherManager.TeacherInfo;
import br.mod.escolar.jdbc.ConnectionMysqlDirect;
import br.mod.escolar.model.util.RealPathApp;

import com.mysql.jdbc.Connection;



public class ReportGenerator <T,E> {

	private JasperPrint reportFilled;
    private TeacherManager controlTeacher;
    private DisciplineManager controlDiscipline;
	private String PATH_JASPER;
	//= "resources/JHidroHidBalReport.jasper";

	public ReportGenerator(String PATH_JASPER) {
		this.PATH_JASPER = PATH_JASPER;
		this.reportFilled = new JasperPrint();
		
		this.controlDiscipline = DisciplineManager.getInstance();
		
		this.controlTeacher = TeacherManager.getInstance();
	}

	/**
	 * 
	 * Este m�todo � respons�vel por gerar um relat�rio para um �nico
	 * reservat�rio contendo todos os cen�rios para o mesmo.
	 * 
	 * @param simulation
	 *            A simula��o que cont�m informa��es sobre a simula�o em que o
	 *            reservat�rio foi executado.
	 * @param reservoir
	 *            O reservat�rio do qual ser� extra�da informa��es sobre o
	 *            balan�o h�drico e cen�rios.
	 * @throws JHidroPortalChartGenException
	 */
	public void generateSingleObjectReport(T object, ComposerIF<T,E> composer)
			throws Exception {

		//ComposerIF  composer = new StudentBeanDataSourceComposer();
		composer.addData(object);
		fillReport(composer);
	}

	/**
	 * 
	 * Este m�todo � respons�vel por gerar um relat�rio para um �nico
	 * reservat�rio contendo todos os cen�rios para o mesmo.
	 * 
	 * @param simulation
	 *            A simula��o que cont�m informa��es sobre a simula�o em que o
	 *            reservat�rio foi executado.
	 * @param reservoir
	 *            O reservat�rio do qual ser� extra�da informa��es sobre o
	 *            balan�o h�drico e cen�rios.
	 * @param scenarioID
	 *            A id do cen�rio para o qual se deseja gerar o relat�rio.
	 * @throws Exception
	 */
//	public void generateHidBalSingleReservoirSingleScenarioReport(Simulation simulation,
//			Reservoir reservoir, int scenarioID) throws Exception {
//		JHidroPortalBeanDataSourceComposer composer = new JHidroPortalBeanDataSourceComposer();
//		composer.addData(reservoir, simulation, scenarioID);
//		fillReport(composer);
//	}


	/**
	 * Este m�todo � respons�vel por gerar um relat�rio para para uma lista de estudantes
	 * @param objects Lista de estudantes
	 */
	public void generateMultipleObjectsReport(List <T> objects , ComposerIF<T,E> composer)
			throws Exception {
		//ComposerIF composer = new StudentBeanDataSourceComposer();

		for (T object : objects) {
			composer.addData(object);
		}
		fillReport(composer);
	}
	
	public void generateMultipleObjectsReportEquipes(List <T> objects , ComposerIF<T,E> composer,String serie,String grau,String turma,String id_disciplina,String id_teacher,String ano)
			throws Exception {
		//ComposerIF composer = new StudentBeanDataSourceComposer();

		for (T object : objects) {
			composer.addData(object);
		}
		fillReportEquipes(composer,serie,grau,turma,id_disciplina,id_teacher,ano);
	}
	
	
	public void generateMultipleObjectsReportFrequencia(List <T> objects , ComposerIF<T,E> composer)
			throws Exception {
		//ComposerIF composer = new StudentBeanDataSourceComposer();

		for (T object : objects) {
			composer.addData(object);
		}
		fillReportFrequencia(composer);
	}
	
	
	private void fillReportFrequencia(ComposerIF<T, E> composer)
			throws Exception {
		
		ArrayList<E> dataToList = composer.getBeansList();
		HashMap<String, String> parameters = new HashMap<String, String>();
		
		JRDataSource jrDataSourse = new JRBeanArrayDataSource(dataToList.toArray());
		
		try {
			File pasta = new File(".");
			this.reportFilled = JasperFillManager.fillReport(PATH_JASPER, parameters, jrDataSourse);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void fillReportEquipes(ComposerIF<T, E> composer,String serie,String grau,String turma,String id_disciplina,String id_teacher,String ano)
			throws Exception {
		
		ConnectionMysqlDirect conexao = new ConnectionMysqlDirect();

		Connection con = (Connection) conexao.getConnection();
		ArrayList<E> dataToList = composer.getBeansList();
		HashMap<String, String> parameters = new HashMap<String, String>();
		
		parameters.put("sub_report", RealPathApp.getPathApp()+ "/resources/equipesAlunos.jasper");
		parameters.put("serie", serie);
		parameters.put("grau",  grau);
		parameters.put("turma", turma);
		parameters.put("ano", ano);
		parameters.put("idDisciplina", id_disciplina);
		parameters.put("idTeacher", id_teacher);
		parameters.put("imagem", RealPathApp.getPathApp()+ "/resources/img/marca_transparente.png");
		parameters.put("Professor", controlTeacher.getTeacherInfo(Integer.parseInt(id_teacher), TeacherInfo.NAME));
		parameters.put("disciplina", controlDiscipline.getDisciplineInfo(Integer.parseInt(id_disciplina), DisciplineInfo.NAME));
		
		
		JRDataSource jrDataSourse = new JRBeanArrayDataSource(dataToList.toArray());
		
		try {
			File pasta = new File(".");
			this.reportFilled = JasperFillManager.fillReport(PATH_JASPER, parameters, con);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void fillReport(ComposerIF<T, E> composer)
			throws Exception {
		ArrayList<E> dataToList = composer.getBeansList();
		HashMap<String, String> parameters = new HashMap<String, String>();
		JRDataSource jrDataSourse = new JRBeanArrayDataSource(dataToList.toArray());
		
		try {
			File pasta = new File(".");
			this.reportFilled = JasperFillManager.fillReport(PATH_JASPER, parameters, jrDataSourse);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * Este m�todo � respons�vel por export um relat�rio jasper para um arquivo
	 * PDF
	 * 
	 * @param reportFilled
	 *            O relat�rio J� devidamente carregado e preenchido com dados
	 * @param outputFile
	 *            O arquivo de sa�da
	 * @throws Exception
	 */
	public void exportJasperToPDF(File outputFile) throws Exception {
		try {
			JasperExportManager.exportReportToPdfFile(reportFilled, outputFile.getAbsolutePath());
		} catch (JRException e) {
			e.printStackTrace();
			throw new Exception("Erro ao gerar Arquivo PDF");

		}
	}

	/**
	 * 
	 * Este m�todo � respons�vel por export um relat�rio jasper para um arquivo
	 * Html, este arquivo Html poder� servir para preview no browser
	 * 
	 * @param reportFilled
	 *            O relat�rio J� devidamente carregado e preenchido com dados
	 * @param outputFile
	 *            O arquivo de sa�da
	 * @throws Exception
	 */
	public void exportJasperToHtml(File outputFile) throws Exception {

		try {
			JasperExportManager.exportReportToHtmlFile(reportFilled, outputFile.getAbsolutePath());
		} catch (JRException e) {
			e.printStackTrace();
			throw new Exception("Erro ao gerar Arquivo PDF");
		}
	}

	/**
	 * 
	 * Este m�todo � respons�vel por export um relat�rio jasper para um arquivo
	 * PDF
	 * 
	 * @param reportFilled
	 *            O relat�rio J� devidamente carregado e preenchido com dados
	 * @param outputFile
	 *            O arquivo de sa�da
	 * @throws Exception
	 */
//	public void exportJasperToXLS(File outputFile) throws Exception {
//		// inst�ncia o exporter
//		JRXlsExporter exporter = new JRXlsExporter();
//		// setagem de Parametros
//		exporter.setParameter(JRExporterParameter.JASPER_PRINT, reportFilled);
//		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outputFile.getAbsolutePath());
//		try {
//			System.out.println(exporter.toString());
//			exporter.exportReport();
//		} catch (JRException e) {
//			e.printStackTrace();
//			throw new Exception("Erro ao gerar Arquivo XLS");
//		}
//		
//	}
	
//	public void exportJasperToDOCX(File outputFile) throws Exception {
//		// inst�ncia o exporter
//		JRDocxExporter exporter = new JRDocxExporter();
//		// setagem de Parametros
//		exporter.setParameter(JRExporterParameter.JASPER_PRINT, reportFilled);
//		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outputFile.getAbsolutePath());
//		try {
//			exporter.exportReport();
//		} catch (JRException e) {
//			e.printStackTrace();
//			throw new Exception("Erro ao gerar Arquivo XLS");
//		}
//		
//	}

	/**
	 * 
	 * Este m�todo � respons�vel por export um relat�rio jasper para um arquivo
	 * PDF
	 * 
	 * @param reportFilled
	 *            O relat�rio J� devidamente carregado e preenchido com dados
	 * @param outputFile
	 *            O arquivo de sa�da
	 * @throws Exception
	 */
	public void exportJasperToRTF(File outputFile) throws Exception {
		// inst�ncia o exporter
		JRRtfExporter rtfExporter = new JRRtfExporter();
		// setagem de param�tros
		rtfExporter.setParameter(JRExporterParameter.JASPER_PRINT, reportFilled);
		rtfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outputFile.getAbsolutePath());

		try {
			rtfExporter.exportReport();
		} catch (JRException e) {
			e.printStackTrace();
			throw new Exception("Erro ao gerar Arquivo RTF");
		}

	}

	/**
	 * 
	 * Este m�todo � respons�vel por exportar um relat�rio jasper para um arquivo
	 * PDF
	 * 
	 * @param reportFilled
	 *            O relat�rio J� devidamente carregado e preenchido com dados
	 * @param outputFile
	 *            O arquivo de sa�da
	 * @throws Exception
	 */
	public void exportJasperToODF(File outputFile) throws Exception {
		// inst�ncia o exporter
		JROdtExporter odfExporter = new JROdtExporter();
		// setagem de parametros
		odfExporter.setParameter(JRExporterParameter.JASPER_PRINT, reportFilled);
		odfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outputFile.getAbsolutePath());

		try {
			odfExporter.exportReport();
		} catch (JRException e) {
			e.printStackTrace();
			throw new Exception("Erro ao gerar Arquivo ODF:");
		}

	}

}
