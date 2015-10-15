package br.mod.escolar.control.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;

import br.mod.escolar.control.FacadeAuth;
import br.mod.escolar.model.entities.Discipline;
import br.mod.escolar.model.entities.Frequencia;
import br.mod.escolar.model.entities.Student;
import br.mod.escolar.model.exception.NoSuchPermissionException;
import br.mod.escolar.model.exception.NoSuchSessionException;
import br.mod.escolar.model.exception.NoSuchStudentException;
import br.mod.escolar.model.exception.NoSuchUserTypeException;

@ManagedBean
@ViewScoped
public class FrequenciaStudentBean {
	
	private List<Discipline> filteredDiscipline;

	private List<Discipline> disciplines;

	private Discipline selectedDiscipline;

	private List<Frequencia> filteredFrequencia;
	
	private List<Frequencia> frequencias;
	
	private LoginBean login;
			
    private FacadeAuth facadeAuth;
    
    private Student student;
    
    private String faltas;
    
    public FrequenciaStudentBean(){
    	
    	ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(true);

		login = (LoginBean) session.getAttribute("loginBean");
		
		facadeAuth = FacadeAuth.getInstance();
		
		try {
			
			student = facadeAuth.facade.studentManager.getStudent(Integer.parseInt(login.getId_pessoal()));
			
			disciplines = facadeAuth.facade.disciplineManager.getAllDisciplinesByGradeAndGrade2(Integer.parseInt(student.getGrade()), student.getGrade2());
		
		} catch (NumberFormatException | NoSuchStudentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
						
    	
    }
    
    public void onRowSelect(SelectEvent event) {
    	
    	try {
			
    		frequencias = facadeAuth.getFrequenciaStudent(login.getSession(), login.getId_pessoal(),selectedDiscipline.getGrade2(), String.valueOf(selectedDiscipline.getGrade()),String.valueOf(selectedDiscipline.getId()));
			
			if(null != frequencias){
				int count = 0;
				for (int i = 0; i < frequencias.size(); i++) {
					
					if(frequencias.get(i).getFrequencia().equals("F")){
						count++;
					}
					
				}
				
				setFaltas(String.valueOf(count));
			}
			
		} catch (NoSuchPermissionException | NoSuchUserTypeException
				| NoSuchSessionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    }

	/**
	 * @return the faltas
	 */
	public String getFaltas() {
		return faltas;
	}

	/**
	 * @param faltas the faltas to set
	 */
	public void setFaltas(String faltas) {
		this.faltas = faltas;
	}

	/**
	 * @return the filteredDiscipline
	 */
	public List<Discipline> getFilteredDiscipline() {
		return filteredDiscipline;
	}

	/**
	 * @param filteredDiscipline the filteredDiscipline to set
	 */
	public void setFilteredDiscipline(List<Discipline> filteredDiscipline) {
		this.filteredDiscipline = filteredDiscipline;
	}

	/**
	 * @return the disciplines
	 */
	public List<Discipline> getDisciplines() {
		return disciplines;
	}

	/**
	 * @param disciplines the disciplines to set
	 */
	public void setDisciplines(List<Discipline> disciplines) {
		this.disciplines = disciplines;
	}


	/**
	 * @return the selectedDiscipline
	 */
	public Discipline getSelectedDiscipline() {
		return selectedDiscipline;
	}

	/**
	 * @param selectedDiscipline the selectedDiscipline to set
	 */
	public void setSelectedDiscipline(Discipline selectedDiscipline) {
		this.selectedDiscipline = selectedDiscipline;
	}

	

	/**
	 * @return the login
	 */
	public LoginBean getLogin() {
		return login;
	}

	public List<Frequencia> getFrequencias() {
		return frequencias;
	}

	public void setFrequencias(List<Frequencia> frequencias) {
		this.frequencias = frequencias;
	}

	public List<Frequencia> getFilteredFrequencia() {
		return filteredFrequencia;
	}

	public void setFilteredFrequencia(List<Frequencia> filteredFrequencia) {
		this.filteredFrequencia = filteredFrequencia;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(LoginBean login) {
		this.login = login;
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
    
    
    

}
