package br.mod.escolar.control.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.mod.escolar.control.FacadeAuth;
import br.mod.escolar.model.entities.Teacher;
import br.mod.escolar.model.exception.NoSuchSessionException;


@ManagedBean
@ViewScoped
public class ListTeacherBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<Teacher> filteredTeacher;

    private List<Teacher> teachers;

    private Teacher selectedTeacher;

    private Teacher[] selectedTeachers;
    
    private FacadeAuth facadeAuth;
    
    private LoginBean login;
    
    
    public ListTeacherBean() throws NoSuchSessionException{
    	
    	 ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();  
    	 HttpSession session = (HttpSession) externalContext.getSession(true);  
    	 login = (LoginBean )session.getAttribute("loginBean"); 
    	 
    	 
    	 teachers = FacadeAuth.getInstance().getAllTeachers(login.getSession());
    	
    	
    	
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
    
    

}
