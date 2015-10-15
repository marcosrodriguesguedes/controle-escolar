package br.mod.escolar.model.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

public class DownloadRelatoriosUtil {
		
	
	public static void fileDownloadController(String diretorio) {

		
		try {
			
			File file = new File(diretorio);
			
			byte[] bytes = getBytesFromFile(file);
			
			HttpServletResponse response = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();

			response.setContentType("text/html;charset=utf-8");
			
			response.setHeader("Content-Disposition", "attachment;filename=\""
				+file.getName() + "\"");
			response.setContentLength(bytes.length);
			response.getOutputStream().write(bytes);
			response.getOutputStream().flush();
			response.getOutputStream().close();
			FacesContext.getCurrentInstance().responseComplete();

		} catch (Exception e) {

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

}
