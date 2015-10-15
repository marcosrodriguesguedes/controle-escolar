package br.mod.escolar.control.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

import br.mod.escolar.control.FacadeAuth;
import br.mod.escolar.model.util.DownloadRelatoriosUtil;

@ManagedBean
@ViewScoped
public class DownloadRelatorios {

	private StreamedContent file;

	private FacadeAuth fachada;

	private LoginBean login;

	private String serie;

	private String turma;

	private String grau;

	public DownloadRelatorios() {
		fachada = FacadeAuth.getInstance();
		
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(true);
		login = (LoginBean) session.getAttribute("loginBean");
	}
	
	public void relatorioAlunos(){
		
		try {
			String path = fachada.generateMultipleStudentReportWithGeneralInformationsByGradeEtc(login.getSession(),
					Integer.parseInt(serie), turma, grau);
			
			DownloadRelatoriosUtil.fileDownloadController(path);
		} catch (Exception e) {
			 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "ATENÇÃO", e.getMessage());  
	            
	    	 RequestContext.getCurrentInstance().showMessageInDialog(message); 
			e.printStackTrace();
		}
	}
	
	public void relatorioDisciplinas(){
	
		try {
			String path = fachada.generateMultipleDisciplinesReportToAllDisciplinesByGradeAndGrade2(login.getSession(), Integer.parseInt(serie), grau);
			DownloadRelatoriosUtil.fileDownloadController(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void relatorioProfessor(){
	
	try {
		String path = fachada.generateMultipleTeachersReportToAllTeachers(login.getSession());
		DownloadRelatoriosUtil.fileDownloadController(path);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
	

	/**
	 * @return the serie
	 */
	public String getSerie() {
		return serie;
	}

	/**
	 * @param serie
	 *            the serie to set
	 */
	public void setSerie(String serie) {
		this.serie = serie;
	}

	/**
	 * @return the turma
	 */
	public String getTurma() {
		return turma;
	}

	/**
	 * @param turma
	 *            the turma to set
	 */
	public void setTurma(String turma) {
		this.turma = turma;
	}

	/**
	 * @return the grau
	 */
	public String getGrau() {
		return grau;
	}

	/**
	 * @param grau
	 *            the grau to set
	 */
	public void setGrau(String grau) {
		this.grau = grau;
	}

	public void fileDownloadController() {

		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(true);
		login = (LoginBean) session.getAttribute("loginBean");

		
		try {
			

			 

			File file = new File( fachada.generateMultipleStudentReportWithGeneralInformationsToAllStudents(login
					.getSession()));
			
			byte[] bytes = getBytesFromFile(file);
			
			HttpServletResponse response = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();

			response.setContentType("text/html;charset=utf-8");
			
			response.setHeader("Content-Disposition", "attachment;filename=\""
				+"relatorioDeDadosGeraisAlunos.pdf" + "\"");
			response.setContentLength(bytes.length);
			response.getOutputStream().write(bytes);
			response.getOutputStream().flush();
			response.getOutputStream().close();
			FacesContext.getCurrentInstance().responseComplete();

		} catch (Exception e) {

		}

	}
	
	
	
	@SuppressWarnings("resource")
	public static byte[] getBytesFromFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);

		// Get the size of the file
		long length = file.length();

		if (length > Integer.MAX_VALUE) {
			// File is too large
		}

		// Create the byte array to hold the data
		byte[] bytes = new byte[(int) length];

		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}

		// Ensure all the bytes have been read in
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file "
					+ file.getName());
		}

		// Close the input stream and return bytes
		is.close();
		return bytes;
	}

	
	public static synchronized void downloadFile(String fileName,
			String mimeType, HttpServletResponse response, byte[] bytes) {

		response.setHeader("Content-Disposition", "attachment;filename=\""
				+ fileName + "\"");
		response.setContentLength(bytes.length);
		response.setContentType(mimeType);
		// response.setCharacterEncoding(arg0);

		try {
			OutputStream out = response.getOutputStream();
			out.write(bytes);
			out.flush();
			out.close();
		} catch (IOException ex) {
			System.err.println("Error in downloadFile: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * @return the file
	 */
	public StreamedContent getFile() {
		return file;
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(StreamedContent file) {
		this.file = file;
	}

	/**
	 * @return the fachada
	 */
	public FacadeAuth getFachada() {
		return fachada;
	}

	/**
	 * @param fachada
	 *            the fachada to set
	 */
	public void setFachada(FacadeAuth fachada) {
		this.fachada = fachada;
	}

	/**
	 * @return the login
	 */
	public LoginBean getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(LoginBean login) {
		this.login = login;
	}

}
