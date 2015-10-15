package br.mod.escolar.control.beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.mod.escolar.control.FacadeAuth;
import br.mod.escolar.model.entities.User;
import br.mod.escolar.model.exception.DBException;
import br.mod.escolar.model.exception.NoSuchPermissionException;
import br.mod.escolar.model.exception.NoSuchSessionException;
import br.mod.escolar.model.exception.NoSuchUserException;
import br.mod.escolar.model.exception.NoSuchUserTypeException;


@ManagedBean
@ViewScoped
public class ListUsersBean  {
	
	
	/**
	 * 
	 */
	

	private List<User> filteredUser;

    private List<User> users;
    
    private User selectedUser;

    private User[] selectedUsers;
    
    private FacadeAuth facadeAuth;
    
    private LoginBean login;

	private String novoLogin;
    
    
    public ListUsersBean() {
    	 
    	 ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();  
    	 HttpSession session = (HttpSession) externalContext.getSession(true);  
    	 login = (LoginBean )session.getAttribute("loginBean"); 
    	 
    	 users = FacadeAuth.getInstance().getAllUser();
    	 
    	 facadeAuth = FacadeAuth.getInstance();
    	 
    	 
    }
    
    
    public void updateUser(){
    	try {
			facadeAuth.updateUser(login.getSession(), selectedUser);
		} catch (NoSuchSessionException | NoSuchUserTypeException
				| NoSuchPermissionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
     
   
    
    public void removerUsuario(){
    	
       try {
		facadeAuth.removeUser(login.getSession(), selectedUser.getLogin());
	} catch (NoSuchUserException | DBException | NoSuchSessionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    	
    }

	public List<User> getFilteredUser() {
		return filteredUser;
	}

	public void setFilteredUser(List<User> filteredUser) {
		this.filteredUser = filteredUser;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	

	public User[] getSelectedUsers() {
		return selectedUsers;
	}

	public void setSelectedUsers(User[] selectedUsers) {
		this.selectedUsers = selectedUsers;
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


	public User getSelectedUser() {
		return selectedUser;
	}


	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}
	
	public String getNovoLogin() {
		return novoLogin;
	}


	public void setNovoLogin(String novoLogin) {
		this.novoLogin = novoLogin;
	}
    
    public void validaLoginsRepetidos(){
    	
    	
        boolean existeLogin = false;
       
    	for (int i = 0; i < getUsers().size(); i++) {
    		
			if(getNovoLogin().equals(getUsers().get(i).getLogin())){
				existeLogin=true;
			}
		}
    	if(existeLogin){
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Login já existe!"));
    	}else{
    		getSelectedUser().setLogin(getNovoLogin());
    	}
    	setNovoLogin("");
    }
    
    
    
    
    

}
