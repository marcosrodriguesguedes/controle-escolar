package br.mod.escolar.control.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.PropertyConfigurator;
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.mod.escolar.control.FacadeAuth;
import br.mod.escolar.model.entities.Discipline;
import br.mod.escolar.model.entities.Sessions;
import br.mod.escolar.model.entities.User;
import br.mod.escolar.model.util.UserTypeDefinition;

/**
 * 
 * @author marcos.r.guedes
 *
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	
	final static Logger logger = LoggerFactory.getLogger(LoginBean.class);
	
	private static final long serialVersionUID = 1L;
	
	private static String SESSAO_ATIVA = "A"; 

    private String username;

	private String password;

	private FacadeAuth facadeAuth;
	
	private String id_pessoal;
	
	private String id_teacher;
	
	private String id_student;
	
	private String funcao;

	private String session;
	
	private Discipline disciplina;

	private User user;
	

	public LoginBean() {
		
		PropertyConfigurator.configure("log4j.properties");
		
		facadeAuth = FacadeAuth.getInstance();
		
	}
	
	
	public void verifySessions(){
		
	// List<Sessions> list = facadeAuth.facade.sessionsManager.getSessionsUser(getId_pessoal(),SESSAO_ATIVA);
	
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public Discipline getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Discipline disciplina) {
		this.disciplina = disciplina;
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
	
	
	public String getId_pessoal() {
		return id_pessoal;
	}

	public void setId_pessoal(String id_pessoal) {
		this.id_pessoal = id_pessoal;
	}
	
	public void logout(ActionEvent actionEvent){
		facadeAuth.doLogout(session);
		facadeAuth.facade.sessionsManager.updateSessionToInitivo(getSession(),getId_pessoal(),SESSAO_ATIVA);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void login(ActionEvent actionEvent) {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		boolean loggedIn = false;

		try {

			setSession(facadeAuth.login(username, password));
			
			setUser(facadeAuth.getUser(getSession(), username));
			
			setId_pessoal(String.valueOf(user.getIdPerson()));
			
			facadeAuth.facade.sessionsManager.updateSessionToInitivo(getSession(),getId_pessoal(),SESSAO_ATIVA);
			
			Sessions e = new Sessions();
			
			e.setAtiva(SESSAO_ATIVA);
			e.setDataAtivacao(new Date());
			e.setId_user(Integer.parseInt(getId_pessoal()));
			e.setSessionID(getSession());

			
			facadeAuth.facade.sessionsManager.createSession(e);
			
			
			
			if (user.getIdType() == Integer
					.parseInt(UserTypeDefinition.ADMINISTRATOR)) {

				loggedIn = true;
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"BEM VINDO!", username);
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("homeAdmin.jsf");
				
				    logger.info("Administrador logado: " + new Date()+ " : "+ user.getNameUser());
			

				
			} else {

				if (user.getIdType() == Integer
						.parseInt(UserTypeDefinition.TEACHER)) {

					loggedIn = true;
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
							"BEM VINDO!", username);
					FacesContext.getCurrentInstance().getExternalContext()
							.redirect("homeTeacher.jsf");
					
				     logger.info("Professor logado: " + new Date()+ " : "+ user.getNameUser());
					

				} else {

					if (user.getIdType() == Integer
							.parseInt(UserTypeDefinition.STUDENT)) {

						loggedIn = true;
						msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
								"BEM VINDO!", username);
						FacesContext.getCurrentInstance().getExternalContext()
								.redirect("homeStudent.jsf");
						
						logger.info("Aluno logado: " + new Date()+ " : "+ user.getNameUser());
						

					} else {

						loggedIn = true;
						msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
								"BEM VINDO!", username);
						FacesContext.getCurrentInstance().getExternalContext()
								.redirect("homeSecretary.jsf");
					}
				}
			}

		} catch (Exception e) {

			loggedIn = false;
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Erro",
					e.getMessage());
			
			logger.debug("Falha de login :" + new Date()+ " : "+ e.getMessage() +" "+username);
			

		}

		FacesContext.getCurrentInstance().addMessage(null, msg);
		context.addCallbackParam("loggedIn", loggedIn);
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}


	public String getId_teacher() {
		return id_teacher;
	}


	public void setId_teacher(String id_teacher) {
		this.id_teacher = id_teacher;
	}


	public String getId_student() {
		return id_student;
	}


	public void setId_student(String id_student) {
		this.id_student = id_student;
	}


	/**
	 * @return the funcao
	 */
	public String getFuncao() {
		return funcao;
	}


	/**
	 * @param funcao the funcao to set
	 */
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	
	

}
