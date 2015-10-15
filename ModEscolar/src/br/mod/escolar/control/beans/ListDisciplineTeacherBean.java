package br.mod.escolar.control.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.mod.escolar.control.FacadeAuth;
import br.mod.escolar.control.TurmasManager;
import br.mod.escolar.model.entities.Discipline;
import br.mod.escolar.model.entities.Teacher;
import br.mod.escolar.model.entities.TeacherClassID;
import br.mod.escolar.model.entities.TeacherDiscipline;
import br.mod.escolar.model.exception.NoSuchDisciplineException;
import br.mod.escolar.model.exception.NoSuchPermissionException;
import br.mod.escolar.model.exception.NoSuchSessionException;
import br.mod.escolar.model.exception.NoSuchTeacherDisciplineException;
import br.mod.escolar.model.exception.NoSuchTeacherException;
import br.mod.escolar.model.exception.NoSuchUserTypeException;
import br.mod.escolar.model.util.ConstantesUtil;


@ManagedBean
@ViewScoped
public class ListDisciplineTeacherBean implements Serializable {
	
	final static Logger logger = LoggerFactory.getLogger(ListDisciplineTeacherBean.class);

	private static final long serialVersionUID = 1L;

	private List<Discipline> filteredDiscipline;

	private List<Discipline> disciplines;

	private List<Discipline> disciplinesTeacher;

	private Discipline selectedDiscipline;

	private Discipline[] selectedDisciplines;

	private List<Teacher> filteredTeacher;

	private List<Teacher> teachers;

	private Teacher selectedTeacher;

	private Teacher[] selectedTeachers;

	private FacadeAuth facadeAuth;
	
	private List<String> turmas;
		
	private List<String> turmasSeleciondas;
		
	private String turma;
	
	private String turmaRemover;
	
	private TurmasManager turmasController;
	

	private LoginBean login;
	
	

	public ListDisciplineTeacherBean() throws NoSuchSessionException, NoSuchPermissionException, NoSuchUserTypeException {

		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(true);
		login = (LoginBean) session.getAttribute("loginBean");
        
		teachers = FacadeAuth.getInstance().getAllTeachers(login.getSession());
		
		disciplines = FacadeAuth.getInstance().getAllDisciplines(login.getSession());
		
		turmasController = TurmasManager.getInstance();
        
		turmas = new ArrayList<String>();
	
		turmasSeleciondas = new ArrayList<String>();
		
		
		

	}
	
	public void listDisciplineTeacher(){
		
		try {
			
			disciplinesTeacher = FacadeAuth.getInstance().getDisciplinesByTeacher(login.getSession(), selectedTeacher.getId());
		
		} catch (NoSuchTeacherDisciplineException | NoSuchDisciplineException
				| NoSuchSessionException | NoSuchTeacherException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
    
    
  

	
	
	public void updateTeacher(ActionEvent actionEvent){
		
      
		
		try {
			FacadeAuth.getInstance().updateTeacher(login.getSession(), selectedTeacher);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Professor Atualizado", "Sucesso");  
	          
		    RequestContext.getCurrentInstance().showMessageInDialog(message);  
			
		} catch (NoSuchSessionException | NoSuchUserTypeException
				| NoSuchPermissionException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
			
		
	 }
	
	
	public void removeDisciplineTeacher(){
		
		try {
			
			FacadeAuth.getInstance().deleteTeacherDiscipline1(login.getSession(), selectedDiscipline.getId(), selectedTeacher.getId());
			
			String alfabeto = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
	    	
	    	String [] mapa = alfabeto.split(",");
	    	
	    	for (int i = 0; i < mapa.length; i++) {
				
	    		TeacherClassID  turmaT = turmasController.getTeacherDisciplineTurma(mapa[i], selectedTeacher.getId(), selectedDiscipline.getId());
	    		
	    		if(null != turmaT){
	    			
	    			turmasController.removeTurmaTeacher(turmaT);
	    		}
	    		
			}
			
			
			
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso","Disciplina Removida Com Sucesso");  
	        
		    RequestContext.getCurrentInstance().showMessageInDialog(message); 
		    
		} catch (NoSuchTeacherDisciplineException | NoSuchSessionException e) {
            
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", e.getMessage());  
	        
		    RequestContext.getCurrentInstance().showMessageInDialog(message); 
			e.printStackTrace();
		}
		
		 
		
	}
	
	public void removeTeacher(){
		
		try {
			
			FacadeAuth.getInstance().deleteTeacher(login.getSession(), selectedTeacher.getId());
			
			teachers = FacadeAuth.getInstance().getAllTeachers(login.getSession());
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Professor Removido com Sucesso");  
	        
		    RequestContext.getCurrentInstance().showMessageInDialog(message);
		    
		    
		
		} catch (NoSuchSessionException | NoSuchTeacherException | NoSuchPermissionException | NoSuchUserTypeException e) {
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO",e.getMessage());  
	        
		    RequestContext.getCurrentInstance().showMessageInDialog(message);
			e.printStackTrace();
		}
		
		
	    
	    
		
	}
	
	 public void removerTurma(){
		 
	    if(turmasSeleciondas.contains(turmaRemover)){
	    	 turmasSeleciondas.remove(turmaRemover);	
	    }
		
	    	
	 
	 }
	
	
	public void teacherToDiscipline(){
		 
		TeacherDiscipline td = new TeacherDiscipline();
		 
		 td.setIdDiscipline(selectedDiscipline.getId());
		 td.setIdTeacher(selectedTeacher.getId());
		 
		 
		 
		try {
			
			for (int i = 0; i < turmasSeleciondas.size(); i++) {
				
				TeacherClassID turma = new TeacherClassID();
				
				turma.setIdTeacher(selectedTeacher.getId());
				
				turma.setIdDiscipline(selectedDiscipline.getId());				
				
				turma.setClasseID(turmasSeleciondas.get(i));
				
				turmasController.createTurmaTeacher(turma);
										
			}
			
			FacadeAuth.getInstance().createTeacherDiscipline(login.getSession(), td);
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Relacao Criada", "Disciplina Associada ao Professor");  
	          
		    RequestContext.getCurrentInstance().showMessageInDialog(message);  
		    
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		logger.info("relacao criada: "+ td.getIdDiscipline() + " - " +td.getIdTeacher());
		
	}
	
	public void getDisciplineTeacherToRegistroDeAulas(){
		
		login.setId_teacher(String.valueOf(selectedTeacher.getId()));
		login.setFuncao(ConstantesUtil.REGISTROS_DE_AULA_ACTION);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("listRegistroAulasAdm.jsf");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	
	}
	
   public void getDisciplineTeacherToNotas(){
		
		login.setId_teacher(String.valueOf(selectedTeacher.getId()));
		login.setFuncao(ConstantesUtil.NOTAS_ACTION);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("listDisciplineToTeacher.jsf");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	
	
	}
   
   public void getDisciplineTeacherToFrequencia(){
		
		login.setId_teacher(String.valueOf(selectedTeacher.getId()));
		login.setFuncao(ConstantesUtil.FREQUENCIA_ACTION);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("listDisciplineToTeacher.jsf");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	
	}
   
   public void getDisciplineTeacherToEquipes(){
		
		login.setId_teacher(String.valueOf(selectedTeacher.getId()));
		login.setFuncao(ConstantesUtil.EQUIPES_ACTION);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("listEquipesAdm.jsf");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	
	}
	
	
	
	public List<Discipline> getFilteredDiscipline() {
		return filteredDiscipline;
	}

	public void setFilteredDiscipline(List<Discipline> filteredDiscipline) {
		this.filteredDiscipline = filteredDiscipline;
	}

	public List<Discipline> getDisciplines() {
		return disciplines;
	}

	public void setDisciplines(List<Discipline> disciplines) {
		this.disciplines = disciplines;
	}

	public List<Discipline> getDisciplinesTeacher() {
		return disciplinesTeacher;
	}

	public void setDisciplinesTeacher(List<Discipline> disciplinesTeacher) {
		this.disciplinesTeacher = disciplinesTeacher;
	}

	public Discipline getSelectedDiscipline() {
		return selectedDiscipline;
	}

	public void setSelectedDiscipline(Discipline selectedDiscipline) {
		this.selectedDiscipline = selectedDiscipline;
	}

	public Discipline[] getSelectedDisciplines() {
		return selectedDisciplines;
	}

	public void setSelectedDisciplines(Discipline[] selectedDisciplines) {
		this.selectedDisciplines = selectedDisciplines;
	}

	public List<Teacher> getFilteredTeacher() {
		return filteredTeacher;
	}

	public void setFilteredTeacher(List<Teacher> filteredTeacher) {
		this.filteredTeacher = filteredTeacher;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public Teacher getSelectedTeacher() {
		return selectedTeacher;
	}

	public void setSelectedTeacher(Teacher selectedTeacher) {
		this.selectedTeacher = selectedTeacher;
	}

	public Teacher[] getSelectedTeachers() {
		return selectedTeachers;
	}

	public void setSelectedTeachers(Teacher[] selectedTeachers) {
		this.selectedTeachers = selectedTeachers;
	}

	public FacadeAuth getFacadeAuth() {
		return facadeAuth;
	}

	public void setFacadeAuth(FacadeAuth facadeAuth) {
		this.facadeAuth = facadeAuth;
	}

	public LoginBean getLogin() {
		return login;
	}

	public void setLogin(LoginBean login) {
		this.login = login;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<String> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<String> turmas) {
		this.turmas = turmas;
	}

	public List<String> getTurmasSeleciondas() {
		return turmasSeleciondas;
	}

	public void setTurmasSeleciondas(List<String> turmasSeleciondas) {
		this.turmasSeleciondas = turmasSeleciondas;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public void selectionChangedBimestre(final AjaxBehaviorEvent event){
        if(!turmasSeleciondas.contains(turma) && !turma.equals("")){
        	turmasSeleciondas.add(turma);
        }
		
    	       
	    
	}

	public String getTurmaRemover() {
		return turmaRemover;
	}

	public void setTurmaRemover(String turmaRemover) {
		this.turmaRemover = turmaRemover;
	}

	public TurmasManager getTurmasController() {
		return turmasController;
	}

	public void setTurmasController(TurmasManager turmasController) {
		this.turmasController = turmasController;
	}
	

	/**
	 * @return the turmas
	 */
	
}
