package br.mod.escolar.control.beans;

import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;

import br.mod.escolar.control.FacadeAuth;
import br.mod.escolar.model.entities.Discipline;
import br.mod.escolar.model.exception.NoSuchDisciplineException;
import br.mod.escolar.model.exception.NoSuchSessionException;
import br.mod.escolar.model.exception.NoSuchTeacherDisciplineException;
import br.mod.escolar.model.exception.NoSuchTeacherException;


@ManagedBean
@ViewScoped
public class ListDisciplineToTeacher {

	private List<Discipline> filteredDiscipline;
	
	private List<Discipline> disciplinesTeacher;

	private Discipline selectedDiscipline;

	private Discipline[] selectedDisciplines;
	
	private FacadeAuth facadeAuth;

	private LoginBean login;

	
	public ListDisciplineToTeacher(){
		
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(true);
		login = (LoginBean) session.getAttribute("loginBean");
		
		facadeAuth = FacadeAuth.getInstance();
		
			try {
				disciplinesTeacher = facadeAuth.getDisciplinesByTeacher(login.getSession(),
						Integer.parseInt(login.getId_teacher()));
			} catch (NumberFormatException | NoSuchTeacherDisciplineException
					| NoSuchDisciplineException | NoSuchSessionException
					| NoSuchTeacherException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public void onRowSelect(SelectEvent event) {

		login.setDisciplina(selectedDiscipline);	
		
		switch (login.getFuncao()) {
		
		case "N":
			
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("notasEmAdm.jsf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			
			break;
			
		case "R":
			
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("listRegistroAulasAdm.jsf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			break;
		
		case "F":
			
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("frequenciaAdm.jsf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			break;
			
		case "E":
			
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("listEquipesAdm.jsf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			break;

		default:
			break;
		}
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
	 * @return the disciplinesTeacher
	 */
	public List<Discipline> getDisciplinesTeacher() {
		return disciplinesTeacher;
	}


	/**
	 * @param disciplinesTeacher the disciplinesTeacher to set
	 */
	public void setDisciplinesTeacher(List<Discipline> disciplinesTeacher) {
		this.disciplinesTeacher = disciplinesTeacher;
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
	 * @return the selectedDisciplines
	 */
	public Discipline[] getSelectedDisciplines() {
		return selectedDisciplines;
	}


	/**
	 * @param selectedDisciplines the selectedDisciplines to set
	 */
	public void setSelectedDisciplines(Discipline[] selectedDisciplines) {
		this.selectedDisciplines = selectedDisciplines;
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


	/**
	 * @return the login
	 */
	public LoginBean getLogin() {
		return login;
	}


	/**
	 * @param login the login to set
	 */
	public void setLogin(LoginBean login) {
		this.login = login;
	}
	
	
	
	
}
