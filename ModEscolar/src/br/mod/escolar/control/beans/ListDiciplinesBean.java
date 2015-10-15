package br.mod.escolar.control.beans;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.SelectableDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.mod.escolar.control.FacadeAuth;
import br.mod.escolar.model.data.DisciplineDataModel;
import br.mod.escolar.model.entities.Discipline;
import br.mod.escolar.model.exception.NoSuchDisciplineException;
import br.mod.escolar.model.exception.NoSuchPermissionException;
import br.mod.escolar.model.exception.NoSuchSessionException;
import br.mod.escolar.model.exception.NoSuchTeacherDisciplineException;
import br.mod.escolar.model.exception.NoSuchTeacherException;
import br.mod.escolar.model.exception.NoSuchUserTypeException;
import br.mod.escolar.model.util.UserTypeDefinition;

@ManagedBean
@ViewScoped
public class ListDiciplinesBean implements SelectableDataModel<Discipline>  {

	final static Logger logger = LoggerFactory.getLogger(LoginBean.class);

	private static final long serialVersionUID = 1L;

	private List<Discipline> filteredDiscipline;

	private List<Discipline> disciplines;

	private List<Discipline> disciplinesTeacher;

	private Discipline selectedDiscipline;

	private Discipline[] selectedDisciplines;

	private FacadeAuth facadeAuth;

	private LoginBean login;

	private String id_teacher;

	public ListDiciplinesBean() throws NoSuchSessionException,
			NoSuchPermissionException, NoSuchUserTypeException,
			NumberFormatException, NoSuchTeacherDisciplineException,
			NoSuchDisciplineException, NoSuchTeacherException {

		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(true);
		login = (LoginBean) session.getAttribute("loginBean");

		if (login.getUser().getIdType() == Integer.parseInt(UserTypeDefinition.ADMINISTRATOR)) {

			disciplines = FacadeAuth.getInstance().getAllDisciplines(login.getSession());

		} else {

			disciplinesTeacher = FacadeAuth.getInstance()
					.getDisciplinesByTeacher(login.getSession(),
							Integer.parseInt(login.getId_pessoal()));

			if (null != disciplinesTeacher && !disciplinesTeacher.isEmpty()) {

				selectedDiscipline = disciplinesTeacher.get(0);

			} else {

				Discipline defalt = new Discipline();

				defalt.setId(0);

				selectedDiscipline = defalt;
			}

		}

	}

	public void listDisciplineTeacher() {

		try {
			if (null != login.getId_pessoal()) {

				disciplinesTeacher = facadeAuth.getDisciplinesByTeacher(
						login.getSession(),
						Integer.parseInt(login.getId_pessoal()));
				logger.debug(login.getId_pessoal());
			} else {
				logger.debug(login.getId_pessoal());
			}

		} catch (NoSuchTeacherDisciplineException | NoSuchDisciplineException
				| NoSuchSessionException | NoSuchTeacherException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onRowSelect(SelectEvent event) {

		login.setDisciplina(selectedDiscipline);
	}

	public void updateDiscipline() {

		try {

			FacadeAuth.getInstance().updateDiscipline(login.getSession(),
					getSelectedDiscipline());

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"SUCESSO", selectedDiscipline.getName().toUpperCase()
							+ " ATUALIZADA");

			RequestContext.getCurrentInstance().showMessageInDialog(message);

		} catch (NoSuchSessionException | NoSuchUserTypeException
				| NoSuchPermissionException e) {

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_FATAL, "ERRO", selectedDiscipline
							.getName().toUpperCase() + " N�O ATUALIZADA");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			e.printStackTrace();
		}

	}

	public void removeDiscipline() {
		try {

			FacadeAuth.getInstance().deleteDiscipline(login.getSession(),
					getSelectedDiscipline().getId());

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"SUCESSO", selectedDiscipline.getName().toUpperCase()
							+ " REMOVIDA");
	
			 
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect("listDisciplineData.jsf");
			
			RequestContext.getCurrentInstance().showMessageInDialog(message);

		} catch (NoSuchSessionException | NoSuchUserTypeException
				| NoSuchPermissionException | NoSuchDisciplineException | IOException e) {

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_FATAL, "ERRO", selectedDiscipline
							.getName().toUpperCase() + " N�O REMOVIDA");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			e.printStackTrace();
		}

	}

	public List<Discipline> getFilteredDiscipline() {
		return filteredDiscipline;
	}

	public String getId_teacher() {
		return id_teacher;
	}

	public void setId_teacher(String id_teacher) {
		this.id_teacher = id_teacher;
	}

	public void setFilteredDiscipline(List<Discipline> filteredDiscipline) {
		this.filteredDiscipline = filteredDiscipline;
	}

	public List<Discipline> getDisciplinesTeacher() {
		return disciplinesTeacher;
	}

	public void setDisciplinesTeacher(List<Discipline> disciplinesTeacher) {
		this.disciplinesTeacher = disciplinesTeacher;
	}

	public List<Discipline> getDisciplines() {
		return disciplines;
	}

	public void setDisciplines(List<Discipline> disciplines) {
		this.disciplines = disciplines;
	}

	public Discipline getSelectedDiscipline() {
		return selectedDiscipline;
	}

	public void setSelectedDiscipline(Discipline selectedDiscipline) {
		this.selectedDiscipline = selectedDiscipline;
	}

	public Discipline[] getSelectedDisciplines() {
		return selectedDisciplines;
	}

	public void setSelectedDisciplines(Discipline[] selectedDisciplines) {
		this.selectedDisciplines = selectedDisciplines;
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

	@Override
	public Discipline getRowData(String arg0) {
	
          
	
		return null;
	}

	@Override
	public Object getRowKey(Discipline arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
