package br.mod.escolar.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.mod.escolar.control.SessionsManager;
import br.mod.escolar.model.util.ActionsToReports;

/**
 * Servlet que faz o download de arquivos txt para ao usuário
 * 
 * @author Romeryto Lira
 * 
 */
public class MyFileServlet extends HttpServlet {

	// private final String DEFAUT_PATH = ".//resources//";
	private final String FILE_SEPARATOR = System.getProperty("file.separator");
	DownloadProperties properties = DownloadProperties.getInstance();
	private final String DEFAULT_PATH = properties
			.getProperty(DownloadProperties.DOWNLOAD_DIRECTORY);
	public static final String DOWNLOAD_DIR_ATRIBUTE = "downloaddir";

	private static final long serialVersionUID = 2797994533312326810L;

	/*
	 * (non-javadoc)
	 */
	/**
		 * 
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
		 * 
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SessionsManager sessionManager = SessionsManager.getInstance();
		String fileName = "r.pdf";

		String sessionId = request.getParameter("sessionId");
		String action = request.getParameter("action");
		String grade = request.getParameter("grade");
		String classId = request.getParameter("classId");
		String grade2 = request.getParameter("grade2");
		File pathOut = new File(DEFAULT_PATH +FILE_SEPARATOR+ sessionId + FILE_SEPARATOR);
		if (!pathOut.exists()) {
			pathOut.mkdirs();
		}
		if (sessionManager.existSession(sessionId)) {
		//	sessionManager.existSession(sessionId)
		//if (sessionId.equals("3")) {
			try {
				if (action
						.equals(ActionsToReports.ACTION_STUDENTS_REPORT_COMPLETE_ALL)) {
					fileName = "relatorioDeDadosPessoaisAlunos.pdf";
				} else if (action
						.equals(ActionsToReports.ACTION_STUDENTS_REPORT_COMPLETE_BY_GRADE_ETC)) {
					fileName = "relatorioDeDadosPessoaisAlunos" + grade + "a"
							+ "Serie" + classId + grade2 + ".pdf";
				} else if (action
						.equals(ActionsToReports.ACTION_STUDENTS_REPORT_GENERAL_ALL)) {
					fileName = "relatorioDeDadosGeraisAlunos.pdf";
				} else if (action
						.equals(ActionsToReports.ACTION_STUDENTS_REPORT_GENERAL_BY_GRADE_ETC)) {
					fileName = "relatorioDeDadosGeraisAlunos" + grade + "a"
							+ "Serie" + classId + grade2 + ".pdf";
				} else if (action
						.equals(ActionsToReports.ACTION_STUDENTS_REPORT_PRESENCE_LIST_ALL)) {
					fileName = "listaDePresencaDeTodosOsAlunos.pdf";
				} else if (action
						.equals(ActionsToReports.ACTION_STUDENTS_REPORT_PRESENCE_LIST_ALL_BY_GRADE_ETC)) {
					fileName = "listaDePresencaDeTodosOsAlunos" + grade + "a"
							+ "Serie" + classId + grade2 + ".pdf";
				} else if (action
						.equals(ActionsToReports.ACTION_STUDENTS_REPORT_FAMILY_INFORMATIONS_ALL)) {
					fileName = "relatorioDeDadosFamiliaresAlunos.pdf";
				} else if (action
						.equals(ActionsToReports.ACTION_STUDENTS_REPORT_FAMILY_INFORMATIONS_BY_GRADE_ETC)) {
					fileName = "relatorioDeDadosFamiliaresAlunos" + grade + "a"
							+ "Serie" + classId + grade2 + ".pdf";
				} else if (action
						.equals(ActionsToReports.ACTION_DISCIPLINES_REPORT_ALL_BY_GRADE_AND_GRADE2)) {
					fileName = "relatorioDeDisciplinas" + grade + "a" + "Serie"
							+ grade2 + ".pdf";
				} else if (action
						.equals(ActionsToReports.ACTION_DISCIPLINES_REPORT_ALL)) {
					fileName = "relatorioDeTodasAsDisciplinas.pdf";
				} else if (action
						.equals(ActionsToReports.ACTION_EMPLOYEES_REPORT_DISCIPLINES_ALL)) {
					fileName = "relatorioDeTodosOsFuncionarios.pdf";
				} else if (action
						.equals(ActionsToReports.ACTION_TEACHERS_REPORT_DISCIPLINES_ALL)) {
					fileName = "relatorioDeTodosOsProfessores.pdf";
				} else if (action
						.equals(ActionsToReports.ACTION_EFII_REPORT)) {
					fileName = "relatorioPlanejamentoEFII.html";
				}else if (action
						.equals(ActionsToReports.ACTION_EI_REPORT)) {
					fileName = "relatorioPlanejamentoEI.html";
				}else if (action
						.equals(ActionsToReports.ACTION_EM_REPORT_REGISTRO_AULAS)) {
					fileName = "registrodeAulas3.pdf";
				}else if (action
						.equals(ActionsToReports.ACTION_EM_REPORT_FREQUENCIA)) {
					fileName = "FrequenciaAlunos.pdf";
				}else if (action
						.equals(ActionsToReports.DOWNLOAD_REF)) {
					fileName = "r.pdf";	
				
				}else if (action
						.equals(ActionsToReports.ACTION_EM_REPORT)) {
					fileName = "relatorioPlanejamentoEM.html";
				}
				
			} catch (Exception e) {
				System.err.println("Nao foi possível escrever no diretorio especificado");
			}
		}
		File downFile = new File(DEFAULT_PATH +FILE_SEPARATOR+ sessionId+ FILE_SEPARATOR + fileName);
		System.out.println("Download directory:" + DEFAULT_PATH + FILE_SEPARATOR + sessionId+ FILE_SEPARATOR + fileName);
		// File downFile = new
		// File(DEFAULT_PATH+FILE_SEPARATOR+uploadDirectoryName+FILE_SEPARATOR+fileName);

		byte[] bytes = getBytesFromFile(downFile);
		String contentType = "text/plain";
		if(action.equals(ActionsToReports.ACTION_EFII_REPORT)||action.equals(ActionsToReports.ACTION_EI_REPORT)||action.equals(ActionsToReports.ACTION_EM_REPORT)){
			contentType = "text/html;charset=utf-8";
		}
		downloadFile(fileName, contentType, response, bytes);
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

}

// private static final long serialVersionUID = -8594746653916812694L;
// protected void doGet(HttpServletRequest req, HttpServletResponse resp)
// throws ServletException, IOException {
// System.out.println(req.getCharacterEncoding());
// String nomeDoArquivo = req.getParameter("filename");
// System.out.println("No serVLet" + nomeDoArquivo);
// String conteudoDoArquivo =
// (String)req.getSession().getAttribute(nomeDoArquivo);
// resp.setContentType("text/plain");
// resp.setHeader("Content-Disposition","attachment; filename="+nomeDoArquivo);
// System.out.println("----->"+resp.getCharacterEncoding());
// System.out.println("-------------------------------------------------------------------------------------------");
// System.out.println("Arquivo:   "+conteudoDoArquivo);
// System.out.println("-------------------------------------------------------------------------------------------");
// try {
// PrintWriter writer = resp.getWriter();
// writer.write(conteudoDoArquivo);
// } catch (IOException ex) {
// System.err.println("Error in downloadFile: " + ex.getMessage());
// ex.printStackTrace();
// }
// }
