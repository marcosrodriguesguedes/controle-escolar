package br.mod.escolar.control.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.mod.escolar.control.FacadeAuth;
import br.mod.escolar.model.entities.RegistroDeAula;
import br.mod.escolar.model.util.DownloadRelatoriosUtil;
import br.mod.escolar.model.util.HibernateUtil;

@ManagedBean
@ViewScoped
public class ListRegistroDeAulaBean implements Serializable {

	final static Logger logger = LoggerFactory.getLogger(LoginBean.class);

	private static final long serialVersionUID = 1L;

	private List<RegistroDeAula> filteredRegistroDeAula;

	private List<RegistroDeAula> registroDeAula;

	private RegistroDeAula selectedRegistroDeAula;

	private RegistroDeAula[] selectedRegistroDeAulas;

	private FacadeAuth facadeAuth;

	private LoginBean login;

	public ListRegistroDeAulaBean() {

		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(true);
		login = (LoginBean) session.getAttribute("loginBean");

		facadeAuth = FacadeAuth.getInstance();
		
		if(null != login.getId_teacher()){
			registroDeAula = facadeAuth.listarRegistros(login.getId_teacher());
		}else{
			registroDeAula = facadeAuth.listarRegistros(login.getId_pessoal());
		}
			
			

		

	}

	public void removeRegistro() {
        HibernateUtil.delete(selectedRegistroDeAula);
	}

	public void updateRegistro() {
        facadeAuth.updateRegistroAulas(selectedRegistroDeAula);

	}
		
	public void gerarRelatorio(){
    	
		String path = facadeAuth.gerarRelatoriaRegistroDeAulas(login.getSession(),selectedRegistroDeAula.getIdDisciplina(),selectedRegistroDeAula.getIdProfessor(), selectedRegistroDeAula.getSerie(), selectedRegistroDeAula.getTurma());
		
		DownloadRelatoriosUtil.fileDownloadController(path);
		
    }

	public List<RegistroDeAula> getFilteredRegistroDeAula() {
		return filteredRegistroDeAula;
	}

	public void setFilteredRegistroDeAula(
			List<RegistroDeAula> filteredRegistroDeAula) {
		this.filteredRegistroDeAula = filteredRegistroDeAula;
	}

	public List<RegistroDeAula> getRegistroDeAula() {
		return registroDeAula;
	}

	public void setRegistroDeAula(List<RegistroDeAula> registroDeAula) {
		this.registroDeAula = registroDeAula;
	}

	public RegistroDeAula getSelectedRegistroDeAula() {
		return selectedRegistroDeAula;
	}

	public void setSelectedRegistroDeAula(RegistroDeAula selectedRegistroDeAula) {
		this.selectedRegistroDeAula = selectedRegistroDeAula;
	}

	public RegistroDeAula[] getSelectedRegistroDeAulas() {
		return selectedRegistroDeAulas;
	}

	public void setSelectedRegistroDeAulas(
			RegistroDeAula[] selectedRegistroDeAulas) {
		this.selectedRegistroDeAulas = selectedRegistroDeAulas;
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

}
