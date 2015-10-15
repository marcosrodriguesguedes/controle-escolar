package br.mod.escolar.control.beans;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import br.mod.escolar.control.RegistroDeAulaManager;
import br.mod.escolar.model.entities.RegistroDeAula;

@ManagedBean
@ViewScoped
public class RegistroDeAulasBean {
	
	private String conteudo;
	
	private String atividade;
	
	private Date dataRegistro;
	
	private String turma;
	
    private RegistroDeAulaManager facadeAuth;
    
   
    
    private LoginBean login;
	
	
	public RegistroDeAulasBean(){
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(true);
		login = (LoginBean) session.getAttribute("loginBean");
		
		facadeAuth = RegistroDeAulaManager.getInstance();
		
		
		
	}
	
	
    public void createRegistro(){
    	
    	try{
    		
        	
        	RegistroDeAula reg = new RegistroDeAula();
    		
    		reg.setObjetosDeConhecimento(getConteudo().toUpperCase());
    		
    		reg.setHabilidades(getAtividade().toUpperCase());
    		
    		reg.setTurma(turma);
    		
    		reg.setDataRegistro(dataRegistro);
        	
    		reg.setIdDisciplina(String.valueOf(login.getDisciplina().getId()));
    		
    		reg.setGrau(login.getDisciplina().getGrade2());
    		
    		reg.setSerie(String.valueOf(login.getDisciplina().getGrade()));
    		
            reg.setIdProfessor(login.getId_pessoal());
    		
        	facadeAuth.criarRegistroDeAula(reg);
        	
        	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO", "Registro gravado com sucesso!");  
            
    	    RequestContext.getCurrentInstance().showMessageInDialog(message); 
    		
    	}catch (Exception e){
          
    		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Registro não gravado, contacte o administrador!");  
            
    	    RequestContext.getCurrentInstance().showMessageInDialog(message); 
    	    
    	    e.printStackTrace();
    	}
    	
    	 
    	
	}
    
    
    
    
	public void subjectSelectionChanged(final AjaxBehaviorEvent event) {
          
		
	}
	
	

	public LoginBean getLogin() {
		return login;
	}

	public void setLogin(LoginBean login) {
		this.login = login;
	}

	


	public String getConteudo() {
		return conteudo;
	}


	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
    

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public String getAtividade() {
		return atividade;
	}


	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}


	public Date getDataRegistro() {
		return dataRegistro;
	}


	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
	
	
	
	

}
