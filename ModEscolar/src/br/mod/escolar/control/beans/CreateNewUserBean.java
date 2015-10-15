package br.mod.escolar.control.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.mod.escolar.control.FacadeAuth;
import br.mod.escolar.model.entities.User;
import br.mod.escolar.model.exception.DBException;
import br.mod.escolar.model.exception.InvalidParameterException;
import br.mod.escolar.model.exception.NoSuchPermissionException;
import br.mod.escolar.model.exception.NoSuchSessionException;
import br.mod.escolar.model.exception.NoSuchUserTypeException;

@ManagedBean
@ViewScoped
public class CreateNewUserBean implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String login;

	private String password;

	private FacadeAuth facadeAuth;

	private String sessionId;

	private int typeUser;

	public CreateNewUserBean() {
		facadeAuth = FacadeAuth.getInstance();
	}

	public int getTypeUser() {
		return typeUser;
	}

	public void setTypeUser(int typeUser) {
		this.typeUser = typeUser;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public FacadeAuth getFacadeAuth() {
		return facadeAuth;
	}

	public void setFacadeAuth(FacadeAuth facadeAuth) {
		this.facadeAuth = facadeAuth;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public void saveUser(ActionEvent actionEvent) throws DBException {

		UIParameter parameter = (UIParameter) actionEvent.getComponent()
				.findComponent("criarUsuario");

		sessionId = parameter.getValue().toString();
		FacesContext context = FacesContext.getCurrentInstance();

		User u = new User();
		u.setLogin(login);
		u.setPassword(password);
		u.setIdType(typeUser);

		if (typeUser == 0) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "ERRO",
							"Selecione o tipo de Usu·rio"));
		} else {
			try {
				facadeAuth.createUser(sessionId, u);
				context.addMessage(null, new FacesMessage("Successo",
						"Usu√°rio cadastrado no sistema: " + u.getLogin()));

			} catch (InvalidParameterException | NoSuchSessionException
					| NoSuchUserTypeException | NoSuchPermissionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}

		}
	}

}
