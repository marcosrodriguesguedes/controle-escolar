package br.mod.escolar.servlets;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Properties;

public class DownloadProperties {

	        public static final String DOWNLOAD_DIRECTORY = "downloadDir";
	        private static final String DEFAULT_DOWNLOAD_DIRECTORY = "downloads";
	        private static DownloadProperties instance  = null;
	        private Properties props;
	        private String propertiesPath;
	        public static final String PROPERTIES_PATH_DEF = "/diretorios_relatorios.xml";
			public static final String URL_APP = "urlApp";
	        
	        private DownloadProperties(){
	                loadProperties(PROPERTIES_PATH_DEF);
	        }
	        
	        private void loadProperties(String path) {
	                props = new Properties();
	                URL cfgFilePath = DownloadProperties.class.getResource(path);
	                try {
	                        props.loadFromXML(new FileInputStream(new File(cfgFilePath.toURI())));
	                } catch (Exception e) {
	                        props.put(DOWNLOAD_DIRECTORY , DEFAULT_DOWNLOAD_DIRECTORY);

	                        try {
	                                props.storeToXML(new FileOutputStream(new File(cfgFilePath.toURI())),"");
	                        } catch (Exception e1) {
	                                e1.printStackTrace();
	                        } 
	                }
	        }


	        public static DownloadProperties getInstance(){
	                if(instance == null){
	                        instance = new DownloadProperties();
	                }
	                return instance;
	        }
	        
	        public String getProperty(String property){
	                return props.getProperty(property);
	        }


	        public void setPropertiesPath(String propertiesPath) {
	                this.propertiesPath = propertiesPath;
	                loadProperties(propertiesPath);
	        }


	        public String getPropertiesPath() {
	                return propertiesPath;
	        }
	        

	

	
	
	
//	public static String SEPARADOR = System.getProperty("file.separator");
//	private String download_dir;
//	private File file;
//	public UploadProperties(){
//		 file = new File("."+SEPARADOR+"downloads");
//		 download_dir = criarDiretorioDeDownload();
//	}
//	
//	public String getDiretorioDeDownload(){
//		return this.download_dir;
//	}
//	
//	
//
//	private String criarDiretorioDeDownload(){  
//		if(!file.exists()){
//			file.mkdir();
//			file.setWritable(true);
//			file.setReadable(true);
//		}
//		String caminho ="";
//		try {
//			caminho = file.getCanonicalPath().toString();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return caminho;
//		
//	}
//	
//	

	//
	// public static void main(String[] args) {
	// UploadProperties up = new UploadProperties();
	// System.out.println(up.getDiretorioDeDownload());
	// }
	
}
