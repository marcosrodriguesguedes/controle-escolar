package br.mod.escolar.control.beans;

import java.io.Serializable;
import java.util.List;

import br.mod.escolar.control.FacadeAuth;
import br.mod.escolar.model.entities.TurmasConfig;
import br.mod.escolar.model.exception.NoSuchPermissionException;
import br.mod.escolar.model.exception.NoSuchSessionException;
import br.mod.escolar.model.exception.NoSuchUserTypeException;
import br.mod.escolar.model.util.HibernateUtil;

public class ListTurmasConfig implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<TurmasConfig> filteredTurma;

    private List<TurmasConfig> turmas;
    
    private TurmasConfig selectedTurma;

    private TurmasConfig[] selectedTurmas;
    
    private FacadeAuth facadeAuth;
    
    private LoginBean login;

	private String novoLogin;

	public ListTurmasConfig() {
		
	}
	
	 public void updateTurma(){
		 
	      HibernateUtil.update(selectedTurma);
	}
	 
	 public void removeTurma(){
	    	
		  HibernateUtil.delete(selectedTurma);
	    
	 }

	/**
	 * @return the filteredTurma
	 */
	public List<TurmasConfig> getFilteredTurma() {
		return filteredTurma;
	}

	/**
	 * @param filteredTurma the filteredTurma to set
	 */
	public void setFilteredTurma(List<TurmasConfig> filteredTurma) {
		this.filteredTurma = filteredTurma;
	}

	/**
	 * @return the turmas
	 */
	public List<TurmasConfig> getTurmas() {
		return turmas;
	}

	/**
	 * @param turmas the turmas to set
	 */
	public void setTurmas(List<TurmasConfig> turmas) {
		this.turmas = turmas;
	}

	/**
	 * @return the selectedTurma
	 */
	public TurmasConfig getSelectedTurma() {
		return selectedTurma;
	}

	/**
	 * @param selectedTurma the selectedTurma to set
	 */
	public void setSelectedTurma(TurmasConfig selectedTurma) {
		this.selectedTurma = selectedTurma;
	}

	/**
	 * @return the selectedTurmas
	 */
	public TurmasConfig[] getSelectedTurmas() {
		return selectedTurmas;
	}

	/**
	 * @param selectedTurmas the selectedTurmas to set
	 */
	public void setSelectedTurmas(TurmasConfig[] selectedTurmas) {
		this.selectedTurmas = selectedTurmas;
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

	/**
	 * @return the novoLogin
	 */
	public String getNovoLogin() {
		return novoLogin;
	}

	/**
	 * @param novoLogin the novoLogin to set
	 */
	public void setNovoLogin(String novoLogin) {
		this.novoLogin = novoLogin;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
	
}
