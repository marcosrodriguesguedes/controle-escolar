package br.mod.escolar.servlets;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class showBrowzer extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	byte[] arquivo = null;

	File file = new File("C:\\teste.pdf");

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			arquivo = fileToByte(file);
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.setContentType("application/pdf");
		response.setContentLength(arquivo.length);
		ServletOutputStream ouputStream = response.getOutputStream();
		ouputStream.write(arquivo, 0, arquivo.length);
		ouputStream.flush();
		ouputStream.close();
	}

	public static InputStream byteToInputStream(byte[] bytes) throws Exception {
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		return bais;
	}

	public static byte[] fileToByte(File imagem) throws Exception {
		FileInputStream fis = new FileInputStream(imagem);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int bytesRead = 0;
		while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
			baos.write(buffer, 0, bytesRead);
		}
		return baos.toByteArray();
	}
}
