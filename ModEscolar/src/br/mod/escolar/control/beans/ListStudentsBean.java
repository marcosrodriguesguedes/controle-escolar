package br.mod.escolar.control.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.mod.escolar.control.FacadeAuth;
import br.mod.escolar.control.HistoricoManager;
import br.mod.escolar.model.entities.HistoricoData;
import br.mod.escolar.model.entities.Student;
import br.mod.escolar.model.exception.DBException;
import br.mod.escolar.model.exception.NoSuchPermissionException;
import br.mod.escolar.model.exception.NoSuchSessionException;
import br.mod.escolar.model.exception.NoSuchStudentException;
import br.mod.escolar.model.exception.NoSuchUserException;
import br.mod.escolar.model.exception.NoSuchUserTypeException;
import br.mod.escolar.model.util.DownloadRelatoriosUtil;

@ManagedBean
@ViewScoped
public class ListStudentsBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final static Logger logger = LoggerFactory.getLogger(ListStudentsBean.class);

    private List<Student> filteredStudent;

    private List<Student> students;
    
    private List<HistoricoData> historico;
    
    private HistoricoData historicoSelecionado;

    private Student selectedStudent;

    private Student[] selectedStudents;
    
    private FacadeAuth facadeAuth;
   
    private LoginBean login;
    
    private HistoricoManager hist;
    
    public ListStudentsBean() {
    	
    	ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(true);
		
		login = (LoginBean) session.getAttribute("loginBean");
		
		

        students = new ArrayList<Student>();

        facadeAuth = FacadeAuth.getInstance();
        
        hist = HistoricoManager.getInstance();
        
          try {
            
            if(login.getSession() != null){
                
            	students = facadeAuth.getAllStudents(login.getSession());
            	
            	
           
            
            }else{
               
            	
            }

            

        } catch (NoSuchSessionException e) {

            logger.error("Erro ao buscar alunos na base. "+e.getMessage());

        }

    }

    /**
	 * @return the historicoSelecionado
	 */
	public HistoricoData getHistoricoSelecionado() {
		return historicoSelecionado;
	}

	/**
	 * @param historicoSelecionado the historicoSelecionado to set
	 */
	public void setHistoricoSelecionado(HistoricoData historicoSelecionado) {
		this.historicoSelecionado = historicoSelecionado;
	}

	public LoginBean getLogin() {
        return login;
    }

    public void setLogin(LoginBean login) {
        this.login = login;
    }

    public List<Student> getFilteredStudent() {
        return filteredStudent;
    }

    public void setFilteredStudent(List<Student> filteredStudent) {
        this.filteredStudent = filteredStudent;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Student[] getSelectedStudents() {
        return selectedStudents;
    }

    public void setSelectedStudents(Student[] selectedStudents) {
        this.selectedStudents = selectedStudents;
    }

    public Student getSelectedStudent() {
        return selectedStudent;
    }

    public void setSelectedStudent(Student selectedStudent) {
        this.selectedStudent = selectedStudent;
    }
    
    
    public void generateHistorico(ActionEvent actionEvent){
    	try {
    		
    		String path = facadeAuth.gerarHistoricoBolentin(login.getSession(), selectedStudent.getId(), historicoSelecionado.getAno());
    		DownloadRelatoriosUtil.fileDownloadController(path);
    		
    		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO", "BOLETIM GERADO, AGUARDE DOWNLOAD...");  
            
    	    RequestContext.getCurrentInstance().showMessageInDialog(message);
    		
			
		} catch (Exception e) {
			    
			    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "ERRO", "BOLETIM NAO GERADO, AGUARDE DOWNLOAD.");  
	            
	    	    RequestContext.getCurrentInstance().showMessageInDialog(message);
		}
    	
    }
    
    
    public void updateStudent(ActionEvent actionEvent){
    	
    	try {
    		
			facadeAuth.updateStudent(login.getSession(), selectedStudent);
			
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO", "ALUNO ATUALIZADO");  
            
    	    RequestContext.getCurrentInstance().showMessageInDialog(message);
			
		} catch (NoSuchPermissionException | NoSuchUserTypeException e) {
		
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "ERRO AO ATUALIZAR ALUNO: "+e);  
            
    	    RequestContext.getCurrentInstance().showMessageInDialog(message);
			
    	    e.printStackTrace();
		}
    
    }
    
    
    
    /**
	 * @return the historico
	 */
	public List<HistoricoData> getHistorico() {
		return historico;
	}

	/**
	 * @param historico the historico to set
	 */
	public void setHistorico(List<HistoricoData> historico) {
		this.historico = historico;
	}

	/**
	 * @return the facadeAuth
	 */
	public FacadeAuth getFacadeAuth() {
		return facadeAuth;
	}

	/**
	 * @param facadeAuth the facadeAuth to set
	 */
	public void setFacadeAuth(FacadeAuth facadeAuth) {
		this.facadeAuth = facadeAuth;
	}
	
	public void removerUsuario(ActionEvent actionEvent){
		
	       try {
			facadeAuth.removeUserType(login.getSession(), selectedStudent.getId());
		} catch (NoSuchSessionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchUserTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    	
	    }

	public void deleteStudent(ActionEvent actionEvent){
    	
    	try {
			
    		facadeAuth.deleteStudent(login.getSession(), selectedStudent.getId());
    		
    		students = facadeAuth.getAllStudents(login.getSession());
			
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO", "ALUNO REMOVIDO");  
            
    	    RequestContext.getCurrentInstance().showMessageInDialog(message);
		} catch (NoSuchStudentException | NoSuchSessionException
				| NoSuchUserTypeException | NoSuchPermissionException e) {
            
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "ERRO AO REMOVER ALUNO: "+e);  
            
    	    RequestContext.getCurrentInstance().showMessageInDialog(message);
			
			e.printStackTrace();
		}
    	//removerUsuario(actionEvent);
    }

    public void onRowSelect(SelectEvent event) {
    	
    	historico = hist.getHistorico(selectedStudent.getId());
    }

    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Aluno desmarcado",
                String.valueOf(((Student) event.getObject()).getId()));

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
