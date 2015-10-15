package br.mod.escolar.control.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import br.mod.escolar.control.FacadeAuth;
import br.mod.escolar.model.entities.Equipe;
import br.mod.escolar.model.entities.EquipeStudents;
import br.mod.escolar.model.entities.Student;
import br.mod.escolar.model.exception.NoSuchSessionException;
import br.mod.escolar.model.exception.NoSuchTeacherException;

@ManagedBean
@ViewScoped
public class EquipesBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DualListModel<Student> students;
	
	private List<String>  ids ;
	
	private FacadeAuth fachada;
	
	private LoginBean login;
	
	private String turma;
	
	private String nota;
	
	private String descricao;
	
	private String bimestre;
	
	List<Student> todos ;
	
	public EquipesBean(){
		
		setTurma("A");
		
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(true);
		login = (LoginBean) session.getAttribute("loginBean");
		
		fachada = FacadeAuth.getInstance();
		
		todos = new ArrayList<Student>();  
        List<Student> selecionados = new ArrayList<Student>(); 
                        
        if(login.getDisciplina() == null){
        	
        	 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "ATENÇÃO", "Selecione uma disciplina para continuar!");  
	          
		     RequestContext.getCurrentInstance().showMessageInDialog(message);  
       
        }else{
        	 
        	 todos = fachada.getStudentsGrauSerie(String.valueOf(login.getDisciplina().getGrade()),
             		getTurma(), login.getDisciplina().getGrade2());
             
             students = new DualListModel<Student>(todos,selecionados);
        	
        }
        
        
       
		
	}
	
	public void subjectSelectionChanged(final AjaxBehaviorEvent event) {
		
		List<Student> selecionados = new ArrayList<Student>(); 
		
		todos = fachada.getStudentsGrauSerie(String.valueOf(login.getDisciplina().getGrade()),
          		getTurma(), login.getDisciplina().getGrade2());
          
       students = new DualListModel<Student>(todos,selecionados);
		
	}
	
	
	
	public void createEquipe(){
		
		 boolean sucesso = true;
		 
		Equipe e = new Equipe();
		
	    e.setDescricao(getDescricao());
	    
	    e.setId_disciplina(login.getDisciplina().getId());
	    
	    e.setId_teacher(Integer.parseInt(login.getId_pessoal()));
	    
	    e.setNomeDisciplina(login.getDisciplina().getName());
	    
	    e.setGrau(login.getDisciplina().getGrade2());
	    
	    e.setSerie(String.valueOf(login.getDisciplina().getGrade()));
	    
	    e.setTurma(getTurma());
	    
	    e.setBimestre(getBimestre());
	    
	    SimpleDateFormat formatAno = new SimpleDateFormat("yyyy");
	    
	    e.setAno(formatAno.format(new Date()));

	    try {
			
			Integer id = fachada.createEquipe(login.getSession(),e);
			 
			 for (int i = 0; i < ids.size(); i++) {
					
					EquipeStudents es = new EquipeStudents();
					
					es.setId_student(Integer.parseInt(ids.get(i)));
					
					fachada.createEquipeStudent(login.getSession(), es, id);
			 }
		
		} catch (NoSuchTeacherException e1) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERRO", "TEACHER");  
	          
		    RequestContext.getCurrentInstance().showMessageInDialog(message); 
			e1.printStackTrace();
			
			sucesso = false;
			
		} catch (NoSuchSessionException e1) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERRO", "SESSAO EXPIRADA");  
	          
		    RequestContext.getCurrentInstance().showMessageInDialog(message); 
			e1.printStackTrace();
			
			sucesso= false;
		}catch (Exception e2) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERRO", "SESSAO EXPIRADA");  
	          
		    RequestContext.getCurrentInstance().showMessageInDialog(message);
		    e2.printStackTrace();
		    
		    sucesso = false;
		}
		
		if(sucesso){
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "EQUIPE SALVA", "equipe gravada com sucesso!");  
	        
		    RequestContext.getCurrentInstance().showMessageInDialog(message); 
			
		}
		
				
			
		 
		
	}  
		
	
	
	 public void onTransfer(TransferEvent event) {  
		    ids = new ArrayList<String>();
	        for(Object item : event.getItems()) {  
	        
	        	
	        	if(!ids.contains(item)){
	        		ids.add((String) item);
	        	}
	           
	        }  
	          
	       
	        
	        
	    }  
	


	public DualListModel<Student> getStudents() {
		return students;
	}

	public void setStudents(DualListModel<Student> students) {
		this.students = students;
	}

	public FacadeAuth getFachada() {
		return fachada;
	}



	public void setFachada(FacadeAuth fachada) {
		this.fachada = fachada;
	}



	public LoginBean getLogin() {
		return login;
	}



	public void setLogin(LoginBean login) {
		this.login = login;
	}



	public String getTurma() {
		return turma;
	}



	public void setTurma(String turma) {
		this.turma = turma;
	}



	public String getNota() {
		return nota;
	}



	public void setNota(String nota) {
		this.nota = nota;
	}



	public String getDescricao() {
		return descricao;
	}



	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



	public String getBimestre() {
		return bimestre;
	}



	public void setBimestre(String bimestre) {
		this.bimestre = bimestre;
	}

	public List<Student> getTodos() {
		return todos;
	}

	public void setTodos(List<Student> todos) {
		this.todos = todos;
	}
	
	

}
