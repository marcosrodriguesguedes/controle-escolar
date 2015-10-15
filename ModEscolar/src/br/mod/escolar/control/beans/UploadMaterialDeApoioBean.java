package br.mod.escolar.control.beans;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.poi.util.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.mod.escolar.control.FacadeAuth;
import br.mod.escolar.model.entities.MaterialDeApoio;

@ManagedBean
@ViewScoped
public class UploadMaterialDeApoioBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

	private LoginBean login;
	
	private FacadeAuth fachada;
	
	private String nucleos;
	 
	private String disci;
	
	List<String> disciplinas = new ArrayList<String>();  
	
	List<String> LCTL = new ArrayList<String>();
	
	List<String> CNTL = new ArrayList<String>();
	
	List<String> MTL = new ArrayList<String>();
	
	List<String> CHTL = new ArrayList<String>();
	
	List<String> PDL = new ArrayList<String>();
	
	List<String> MACROL = new ArrayList<String>();
    
	List<String> OUTROSL = new ArrayList<String>();
	
	public UploadMaterialDeApoioBean(){
		
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(true);
		login = (LoginBean) session.getAttribute("loginBean");
		 
		fachada = FacadeAuth.getInstance();
		
		

        LCTL.add("LINGUA PORTUGUESA");
        LCTL.add("ARTE");
        LCTL.add("EDUCAÇÃO FÍSICA");
        
        
        CNTL.add("BIOLOGIA");
        CNTL.add("FÍSICA");
        CNTL.add("QUÍMICA");
         
        MTL.add("MATEMÁTICA");
          
        CHTL.add("HISTÓRIA");
        CHTL.add("GEOGRAFIA");
        CHTL.add("SOCIOLOGIA");
        CHTL.add("FILOSOFIA");
        
      
        PDL.add("LINGUA INGLESA");
        
        PDL.add("LINGUA ESPANHOLA");
        
        MACROL.add("INTEGRAÇÃO CURRICULA");
        MACROL.add("INICIAÇÃO CIENTIFICA E PESQUISA");
        MACROL.add("LEITURA E LETRAMENTO");
        MACROL.add("CULTURA CORPORAL");
        MACROL.add("Comunicação, cultura digital e uso as Midias");
        MACROL.add("LINGUAS ESTRANGEIRAS");
        MACROL.add("PRODUÇÃO E FRUIÇÃO DAS ARTES");
        
        OUTROSL.add("OUTROS");
       
        
      		
	}
	



	/**
	 * @return the disci
	 */
	public String getDisci() {
		return disci;
	}


	/**
	 * @param disci the disci to set
	 */
	public void setDisci(String disci) {
		this.disci = disci;
	}



	public void fileUploadAction(FileUploadEvent event) {
        try {
           

            FacesContext aFacesContext = FacesContext.getCurrentInstance();
            ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();
           
            String realPath = context.getRealPath("/");
            
            
            // Aqui cria o diretorio caso nao exista
            File file = new File(realPath + "/documentos/");
            file.mkdirs();
            
            UploadedFile arq = event.getFile();
          	 
        	InputStream in = new BufferedInputStream(arq.getInputstream());
        	
        	byte[] arquivo = IOUtils.toByteArray(in);
            
        	String caminho = realPath + "/documentos/" + event.getFile().getFileName();    
      
           // esse trecho grava o arquivo no diretorio
        	
            FileOutputStream fos = new FileOutputStream(caminho);
            fos.write(arquivo);
            fos.close();
            
            MaterialDeApoio material = new MaterialDeApoio();
            
            material.setDataInclusao(new Date());
            
            material.setId_usuario(Integer.parseInt(login.getId_pessoal()));
            
            material.setUrlArquivo(event.getFile().getFileName());
            
            material.setDisciplina(getDisci());
            
            material.setNucleo(getNucleos());
            
            fachada.newMaterialApoio(login.getSession(), material);
            
            
            
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Transferência concluida para o servido!");  
	        
		    RequestContext.getCurrentInstance().showMessageInDialog(message);

        } catch (Exception ex) {
            
        	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERRO", "Transferência NÃO concluida para o servido!");  
	        
		    RequestContext.getCurrentInstance().showMessageInDialog(message);
            System.out.println("Erro no upload de imagem" + ex);
        }
    }
	
	public void handleCityChange2(final AjaxBehaviorEvent event) {
		System.out.println(getDisci());
	}
	
	public void handleCityChange(final AjaxBehaviorEvent event) {
        if(nucleos !=null && !nucleos.equals("")){
        	
        	switch (nucleos) {
			case "LCT":
				
				disciplinas = LCTL;
				
				break;
				
			case "CNT":
				
				disciplinas = CNTL ;
				
				break;
				
			case "MT":
				
				disciplinas = MTL ;
				
				break;	
				
           case "CHT":
				
				disciplinas = CHTL ;
				
				break;		
				
           case "PD":
				
				disciplinas = PDL ;
				
				break;
				
           case "MACRO":
				
				disciplinas = MACROL ;
				
				break;
				
           case "OUTROS":
				
				disciplinas = OUTROSL ;
				
				break;			

			default:
				break;
			}
        	
        }else
           
        	disciplinas = new ArrayList<String>();
    }
 
    public void displayLocation() {
       
    }



	public LoginBean getLogin() {
		return login;
	}



	public void setLogin(LoginBean login) {
		this.login = login;
	}



	/**
	 * @return the disciplinas
	 */
	public List<String> getDisciplinas() {
		return disciplinas;
	}







	/**
	 * @param disciplinas the disciplinas to set
	 */
	public void setDisciplinas(List<String> disciplinas) {
		this.disciplinas = disciplinas;
	}







	/**
	 * @return the lCTL
	 */
	public List<String> getLCTL() {
		return LCTL;
	}







	/**
	 * @param lCTL the lCTL to set
	 */
	public void setLCTL(List<String> lCTL) {
		LCTL = lCTL;
	}







	/**
	 * @return the cNTL
	 */
	public List<String> getCNTL() {
		return CNTL;
	}







	/**
	 * @param cNTL the cNTL to set
	 */
	public void setCNTL(List<String> cNTL) {
		CNTL = cNTL;
	}







	/**
	 * @return the mTL
	 */
	public List<String> getMTL() {
		return MTL;
	}







	/**
	 * @param mTL the mTL to set
	 */
	public void setMTL(List<String> mTL) {
		MTL = mTL;
	}







	/**
	 * @return the cHTL
	 */
	public List<String> getCHTL() {
		return CHTL;
	}







	/**
	 * @param cHTL the cHTL to set
	 */
	public void setCHTL(List<String> cHTL) {
		CHTL = cHTL;
	}







	/**
	 * @return the pDL
	 */
	public List<String> getPDL() {
		return PDL;
	}







	/**
	 * @param pDL the pDL to set
	 */
	public void setPDL(List<String> pDL) {
		PDL = pDL;
	}







	/**
	 * @return the mACROL
	 */
	public List<String> getMACROL() {
		return MACROL;
	}







	/**
	 * @param mACROL the mACROL to set
	 */
	public void setMACROL(List<String> mACROL) {
		MACROL = mACROL;
	}







	/**
	 * @return the oUTROSL
	 */
	public List<String> getOUTROSL() {
		return OUTROSL;
	}







	/**
	 * @param oUTROSL the oUTROSL to set
	 */
	public void setOUTROSL(List<String> oUTROSL) {
		OUTROSL = oUTROSL;
	}







	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}







	public FacadeAuth getFachada() {
		return fachada;
	}



	public void setFachada(FacadeAuth fachada) {
		this.fachada = fachada;
	}


	public String getNucleos() {
		return nucleos;
	}



	public void setNucleos(String nucleos) {
		this.nucleos = nucleos;
	}



	
	

}
