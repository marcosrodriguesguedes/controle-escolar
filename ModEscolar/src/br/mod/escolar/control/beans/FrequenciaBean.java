package br.mod.escolar.control.beans;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;

import br.mod.escolar.control.FacadeAuth;
import br.mod.escolar.control.TeacherManager;
import br.mod.escolar.control.TeacherManager.TeacherInfo;
import br.mod.escolar.model.entities.Frequencia;
import br.mod.escolar.model.exception.NoSuchDisciplineException;
import br.mod.escolar.model.exception.NoSuchPermissionException;
import br.mod.escolar.model.exception.NoSuchSessionException;
import br.mod.escolar.model.exception.NoSuchTeacherException;
import br.mod.escolar.model.exception.NoSuchUserTypeException;
import br.mod.escolar.model.util.DownloadRelatoriosUtil;

@ManagedBean
@ViewScoped
public class FrequenciaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static int UPDATE = 1;

	private Date dataSelecionada;

	private String[] flagFrequencia;

	private LoginBean login;

	private String turma;
	
	private String grau;
	
	private String serie;
	
	private String nomeDisciplina;

	private List<Frequencia> lisFrequencia;

	private FacadeAuth fachada;
	
	private TeacherManager teacherManager;

	public FrequenciaBean() throws NoSuchDisciplineException {

		flagFrequencia = new String[2];

		flagFrequencia[0] = "P";

		flagFrequencia[1] = "F";

		setTurma("A");

		dataSelecionada = new Date();

		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(true);
		login = (LoginBean) session.getAttribute("loginBean");

		fachada = FacadeAuth.getInstance();
		teacherManager = TeacherManager.getInstance();
		if(null != login.getDisciplina()){
				
			if(null == login.getDisciplina().getName()){
		
			throw new NoSuchDisciplineException();
			}
		
			try {

				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
				setNomeDisciplina(login.getDisciplina().getName());
			
				setGrau(login.getDisciplina().getGrade2());
			
				setSerie(String.valueOf(login.getDisciplina().getGrade()));
			
				lisFrequencia = fachada.loadFrequenciaAluno(login.getSession(),
					login.getDisciplina().getGrade2(),
					String.valueOf(login.getDisciplina().getGrade()),
					format.format(dataSelecionada), getTurma(),String.valueOf(login.getDisciplina().getId()));

			} catch (SQLException | ParseException | NoSuchUserTypeException
					| NoSuchPermissionException | NoSuchSessionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}else{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "ATENCAO", "SELECIONE UMA DISCIPLINA NA PAGINA ANTERIOR!");  
	          
		    RequestContext.getCurrentInstance().showMessageInDialog(message);  
		}
	}

	public Date getDataSelecionada() {
		return dataSelecionada;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public void setDataSelecionada(Date dataSelecionada) {
		this.dataSelecionada = dataSelecionada;
	}

	public List<Frequencia> getLisFrequencia() {
		return lisFrequencia;
	}

	public void setLisFrequencia(List<Frequencia> lisFrequencia) {
		this.lisFrequencia = lisFrequencia;
	}

	public LoginBean getLogin() {
		return login;
	}

	public void setLogin(LoginBean login) {
		this.login = login;
	}

	public String[] getFlagFrequencia() {
		return flagFrequencia;
	}

	public void setFlagFrequencia(String[] flagFrequencia) {
		this.flagFrequencia = flagFrequencia;
	}
	
	public void gerarRelatorioFrequencia(){
		 
		  String path;
		try {
			path = fachada.generateFrequeciaAlunos(login.getSession(), serie, grau, turma,String.valueOf(login.getDisciplina().getId()),teacherManager.getTeacherInfo(Integer.parseInt(login.getId_teacher()),TeacherInfo.NAME));
			DownloadRelatoriosUtil.fileDownloadController(path);
		} catch (NumberFormatException | NoSuchTeacherException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	     
	}

	public void saveFrequencia() {

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		for (int i = 0; i < lisFrequencia.size(); i++) {

			if (lisFrequencia.get(i).getAtualizar() == UPDATE) {

				try {
					
					fachada.updateFrequencia(login.getSession(),lisFrequencia.get(i));
					
					 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Frequência Atualizada", "Frequência gravada com sucesso!");  
			          
				     RequestContext.getCurrentInstance().showMessageInDialog(message);  
				
				
				} catch (NoSuchUserTypeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchPermissionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchSessionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {

				lisFrequencia.get(i).setData(format.format(dataSelecionada));
				lisFrequencia.get(i).setIdDisciplina(
						String.valueOf(login.getDisciplina().getId()));

				try {
					fachada.createFrequencia(login.getSession(),lisFrequencia.get(i));
					lisFrequencia.get(i).setAtualizar(UPDATE);
					
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Frequência Salva", "Frequência gravada com sucesso!");  
			          
				    RequestContext.getCurrentInstance().showMessageInDialog(message);  
				} catch (NoSuchPermissionException | NoSuchUserTypeException
						| NoSuchSessionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

	}

	public void subjectSelectionChanged(final AjaxBehaviorEvent event) {

		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

			lisFrequencia = fachada.loadFrequenciaAluno(login.getSession(),
					login.getDisciplina().getGrade2(),
					String.valueOf(login.getDisciplina().getGrade()),
					format.format(dataSelecionada), getTurma(),String.valueOf(login.getDisciplina().getId()));
		} catch (SQLException | ParseException | NoSuchUserTypeException
				| NoSuchPermissionException | NoSuchSessionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void handleDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		try {
			lisFrequencia = fachada.loadFrequenciaAluno(login.getSession(),
					login.getDisciplina().getGrade2(),
					String.valueOf(login.getDisciplina().getGrade()),
					format.format(event.getObject()), getTurma(),String.valueOf(login.getDisciplina().getId()));
		} catch (SQLException | ParseException | NoSuchUserTypeException
				| NoSuchPermissionException | NoSuchSessionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Data Selecionada", format.format(event.getObject())));
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Frequencia Atualizada:", "De: " + oldValue + ", Para:"
							+ newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina=nomeDisciplina;
			
		
		
	}

	public String getGrau() {
		return grau;
	}

	public void setGrau(String grau) {
		this.grau = grau;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}
	

}
