package br.mod.escolar.model.util;

import java.io.File;
import java.net.URL;

import br.mod.escolar.servlets.DownloadProperties;

public class RealPathApp {

	private final static String FILE_SEPARATOR = System
			.getProperty("file.separator");
	static DownloadProperties properties = DownloadProperties.getInstance();
	private final static String DEFAULT_PATH = properties
			.getProperty(DownloadProperties.DOWNLOAD_DIRECTORY);

	public static boolean cleanDir(String sessionId) {
		File path = new File(DEFAULT_PATH + FILE_SEPARATOR + sessionId);
		return deleteDirectory(path);
	}

	private static boolean deleteDirectory(File path) {
		if (path.exists()) {
			File[] files = path.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					deleteDirectory(files[i]);
				} else {
					files[i].delete();
				}
			}
		}
		return (path.delete());
	}

	public static String getPathApp() {
		URL url = RealPathApp.class.getResource("/");
		String path = url.getPath();
		return path.replaceAll("%20", " ");
	}

}
