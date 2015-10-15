package br.mod.escolar.control.beans;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.mod.escolar.control.FacadeAuth;
import br.mod.escolar.model.data.EquipeListStudent;
import br.mod.escolar.model.entities.Equipe;
import br.mod.escolar.model.entities.Student;
import br.mod.escolar.model.exception.NoSuchSessionException;
import br.mod.escolar.model.exception.NoSuchStudentException;


@ManagedBean
@ViewScoped
public class ListEquipesStudent {

	private List<EquipeListStudent> equipes;
	
	private List<Equipe> equipesStudent;
	
	private List<Student> listStudent;
	
	private FacadeAuth fachada;
	
	private LoginBean login;
	
	
	public ListEquipesStudent(){
		
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(true);
		login = (LoginBean) session.getAttribute("loginBean");
    	
    	fachada = FacadeAuth.getInstance();
    	equipes = new ArrayList<EquipeListStudent>();
    	
    	try {
    		
			equipesStudent = fachada.getEquipesStudent(login.getSession(), login.getId_pessoal());
			
			
			
		} catch (NoSuchSessionException | SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	for (int i = 0; i < equipesStudent.size(); i++) {
    		
    		EquipeListStudent e = new EquipeListStudent();
    		
    		e.setDescricao(equipesStudent.get(i).getDescricao());
    		
    		e.setNota(equipesStudent.get(i).getNota().toString());
    		
    		e.setNomeDisciplina(equipesStudent.get(i).getNomeDisciplina());
    		
    		try {
    			
				listStudent = fachada.getStudentsEquipes(login.getSession(), String.valueOf(equipesStudent.get(i).getId()));
				
			} catch (NoSuchSessionException | NoSuchStudentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

    		for (int j = 0; j < listStudent.size(); j++) {
				
    			e.getAlunos().add(listStudent.get(j));
			}
    		
    		
    		equipes.add(e);
    		
			
		}
		
	}


	/**
	 * @return the equipes
	 */
	public List<EquipeListStudent> getEquipes() {
		return equipes;
	}


	/**
	 * @param equipes the equipes to set
	 */
	public void setEquipes(List<EquipeListStudent> equipes) {
		this.equipes = equipes;
	}


	/**
	 * @return the equipesStudent
	 */
	public List<Equipe> getEquipesStudent() {
		return equipesStudent;
	}


	/**
	 * @param equipesStudent the equipesStudent to set
	 */
	public void setEquipesStudent(List<Equipe> equipesStudent) {
		this.equipesStudent = equipesStudent;
	}


	/**
	 * @return the fachada
	 */
	public FacadeAuth getFachada() {
		return fachada;
	}


	/**
	 * @param fachada the fachada to set
	 */
	public void setFachada(FacadeAuth fachada) {
		this.fachada = fachada;
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
