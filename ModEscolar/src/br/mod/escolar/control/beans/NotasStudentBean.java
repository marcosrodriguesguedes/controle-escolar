package br.mod.escolar.control.beans;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import br.mod.escolar.control.FacadeAuth;
import br.mod.escolar.control.NotasManager;
import br.mod.escolar.model.entities.Discipline;
import br.mod.escolar.model.entities.Notas;
import br.mod.escolar.model.entities.Student;
import br.mod.escolar.model.exception.NoSuchSessionException;
import br.mod.escolar.model.exception.NoSuchStudentException;

@ManagedBean
@ViewScoped
public class NotasStudentBean {

	private Student student;

	private String turma;

	private String bimestre;

	private String nota1;

	private String nota2;

	private String nota3;

	private String rec1;

	private String rec2;

	private String rec3;

	private String media;

	private String mediaBimestral;

	private String mediaParcialFinal;

	private String notaFinal;

	private String mediaFinal;

	private String status;

	private LoginBean login;

	private List<Discipline> filteredDiscipline;

	private List<Discipline> disciplines;

	private Discipline selectedDiscipline;

	private Discipline[] selectedDisciplines;

	private Notas notasAluno;

	private FacadeAuth facadeAuth;
	
	private NotasManager notasManager;

	public NotasStudentBean() {

		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(true);

		login = (LoginBean) session.getAttribute("loginBean");
		
		notasManager = NotasManager.getInstance();
		
		facadeAuth = FacadeAuth.getInstance();
		
		setBimestre("1");
		
		
		
		try {
			
			student = facadeAuth.facade.studentManager.getStudent(Integer.parseInt(login.getId_pessoal()));
			
			disciplines = facadeAuth.facade.disciplineManager.getAllDisciplinesByGradeAndGrade2(Integer.parseInt(student.getGrade()), student.getGrade2());
		
		
		} catch (NumberFormatException | NoSuchStudentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void selectionChangedBimestre(final AjaxBehaviorEvent event) {
		
		switch (bimestre) {
		
		
		
		case "1":
			
			atualizaCamposNotas(notasAluno.getNota1_b1(), notasAluno.getNota2_b1(), notasAluno.getNota3_b1(), 
					notasAluno.getRec1_b1(), notasAluno.getRec2_b1(), notasAluno.getRec3_b1(),bimestre, notasAluno);
    		
    		break;
		
		case "2":
			
			
			atualizaCamposNotas(notasAluno.getNota1_b2(), notasAluno.getNota2_b2(), notasAluno.getNota3_b2(), 
					notasAluno.getRec1_b2(), notasAluno.getRec2_b2(), notasAluno.getRec3_b2(),bimestre, notasAluno);
		
			break;
			
			
		case "3":
			
			atualizaCamposNotas(notasAluno.getNota1_b3(), notasAluno.getNota2_b3(), notasAluno.getNota3_b3(), 
					notasAluno.getRec1_b3(), notasAluno.getRec2_b3(), notasAluno.getRec3_b3(),bimestre, notasAluno);
			break;
			
		
		case "4":
			
			atualizaCamposNotas(notasAluno.getNota1_b4(), notasAluno.getNota2_b4(), notasAluno.getNota3_b4(), 
					notasAluno.getRec1_b4(), notasAluno.getRec2_b4(), notasAluno.getRec3_b4(),bimestre, notasAluno);
			break;	
			
			

		default:
			break;
		}
    
    	
    	
    	

	}

	public void onRowSelect(SelectEvent event) {

		FacesMessage msg = new FacesMessage("Disciplina Selecionada",
				((Discipline) event.getObject()).getName());

		FacesContext.getCurrentInstance().addMessage(null, msg);

		try {

			notasAluno = facadeAuth.getNotasAll(login.getSession(),
					String.valueOf(selectedDiscipline.getId()),
					login.getId_pessoal());

			if (null != notasAluno) {

				switch (bimestre) {

				case "1":

					atualizaCamposNotas(notasAluno.getNota1_b1(),
							notasAluno.getNota2_b1(), notasAluno.getNota3_b1(),
							notasAluno.getRec1_b1(), notasAluno.getRec2_b1(),
							notasAluno.getRec3_b1(),bimestre, notasAluno);

					break;

				case "2":

					atualizaCamposNotas(notasAluno.getNota1_b2(),
							notasAluno.getNota2_b2(), notasAluno.getNota3_b2(),
							notasAluno.getRec1_b2(), notasAluno.getRec2_b2(),
							notasAluno.getRec3_b2(),bimestre, notasAluno);

					break;

				case "3":

					atualizaCamposNotas(notasAluno.getNota1_b3(),
							notasAluno.getNota2_b3(), notasAluno.getNota3_b3(),
							notasAluno.getRec1_b3(), notasAluno.getRec2_b3(),
							notasAluno.getRec3_b3(),bimestre, notasAluno);
					break;

				case "4":

					atualizaCamposNotas(notasAluno.getNota1_b4(),
							notasAluno.getNota2_b4(), notasAluno.getNota3_b4(),
							notasAluno.getRec1_b4(), notasAluno.getRec2_b4(),
							notasAluno.getRec3_b4(),bimestre, notasAluno);
					break;

				default:
					break;
				}

			} else {

				setNota1("");
				setNota2("");
				setNota3("");
				setRec1("");
				setRec2("");
				setRec3("");
				setMedia("");
				setMediaBimestral("");
				setMediaParcialFinal("");
				setMediaFinal("");

				
			}

		} catch (SQLException | NoSuchSessionException e) {

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO",
					"ERRO AO BUSCAR NOTAS DO ALUNO: "
							+ student.getName());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			e.printStackTrace();
		}

	}

	/**
	 * @return the turma
	 */
	public String getTurma() {
		return turma;
	}

	/**
	 * @param turma
	 *            the turma to set
	 */
	public void setTurma(String turma) {
		this.turma = turma;
	}

	/**
	 * @return the bimestre
	 */
	public String getBimestre() {
		return bimestre;
	}

	/**
	 * @param bimestre
	 *            the bimestre to set
	 */
	public void setBimestre(String bimestre) {
		this.bimestre = bimestre;
	}

	/**
	 * @return the nota1
	 */
	public String getNota1() {
		return nota1;
	}

	/**
	 * @param nota1
	 *            the nota1 to set
	 */
	public void setNota1(String nota1) {
		this.nota1 = nota1;
	}

	/**
	 * @return the nota2
	 */
	public String getNota2() {
		return nota2;
	}

	/**
	 * @param nota2
	 *            the nota2 to set
	 */
	public void setNota2(String nota2) {
		this.nota2 = nota2;
	}

	/**
	 * @return the nota3
	 */
	public String getNota3() {
		return nota3;
	}

	/**
	 * @param nota3
	 *            the nota3 to set
	 */
	public void setNota3(String nota3) {
		this.nota3 = nota3;
	}

	/**
	 * @return the rec1
	 */
	public String getRec1() {
		return rec1;
	}

	/**
	 * @param rec1
	 *            the rec1 to set
	 */
	public void setRec1(String rec1) {
		this.rec1 = rec1;
	}

	/**
	 * @return the rec2
	 */
	public String getRec2() {
		return rec2;
	}

	/**
	 * @param rec2
	 *            the rec2 to set
	 */
	public void setRec2(String rec2) {
		this.rec2 = rec2;
	}

	/**
	 * @return the rec3
	 */
	public String getRec3() {
		return rec3;
	}

	/**
	 * @param rec3
	 *            the rec3 to set
	 */
	public void setRec3(String rec3) {
		this.rec3 = rec3;
	}

	/**
	 * @return the media
	 */
	public String getMedia() {
		return media;
	}

	/**
	 * @param media
	 *            the media to set
	 */
	public void setMedia(String media) {
		this.media = media;
	}

	/**
	 * @return the mediaBimestral
	 */
	public String getMediaBimestral() {
		return mediaBimestral;
	}

	/**
	 * @param mediaBimestral
	 *            the mediaBimestral to set
	 */
	public void setMediaBimestral(String mediaBimestral) {
		this.mediaBimestral = mediaBimestral;
	}

	/**
	 * @return the mediaParcialFinal
	 */
	public String getMediaParcialFinal() {
		return mediaParcialFinal;
	}

	/**
	 * @param mediaParcialFinal
	 *            the mediaParcialFinal to set
	 */
	public void setMediaParcialFinal(String mediaParcialFinal) {
		this.mediaParcialFinal = mediaParcialFinal;
	}

	/**
	 * @return the notaFinal
	 */
	public String getNotaFinal() {
		return notaFinal;
	}

	/**
	 * @param notaFinal
	 *            the notaFinal to set
	 */
	public void setNotaFinal(String notaFinal) {
		this.notaFinal = notaFinal;
	}

	/**
	 * @return the mediaFinal
	 */
	public String getMediaFinal() {
		return mediaFinal;
	}

	/**
	 * @param mediaFinal
	 *            the mediaFinal to set
	 */
	public void setMediaFinal(String mediaFinal) {
		this.mediaFinal = mediaFinal;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the login
	 */
	public LoginBean getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(LoginBean login) {
		this.login = login;
	}

	/**
	 * @return the filteredDiscipline
	 */
	public List<Discipline> getFilteredDiscipline() {
		return filteredDiscipline;
	}

	/**
	 * @param filteredDiscipline
	 *            the filteredDiscipline to set
	 */
	public void setFilteredDiscipline(List<Discipline> filteredDiscipline) {
		this.filteredDiscipline = filteredDiscipline;
	}

	/**
	 * @return the disciplines
	 */
	public List<Discipline> getDisciplines() {
		return disciplines;
	}

	/**
	 * @param disciplines
	 *            the disciplines to set
	 */
	public void setDisciplines(List<Discipline> disciplines) {
		this.disciplines = disciplines;
	}


	/**
	 * @return the selectedDiscipline
	 */
	public Discipline getSelectedDiscipline() {
		return selectedDiscipline;
	}

	/**
	 * @param selectedDiscipline
	 *            the selectedDiscipline to set
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
	 * @param selectedDisciplines
	 *            the selectedDisciplines to set
	 */
	public void setSelectedDisciplines(Discipline[] selectedDisciplines) {
		this.selectedDisciplines = selectedDisciplines;
	}

	/**
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * @param student
	 *            the student to set
	 */
	public void setStudent(Student student) {
		this.student = student;
	}

	private void atualizaCamposNotas(BigDecimal n1, BigDecimal n2,
			BigDecimal n3, BigDecimal r1, BigDecimal r2, BigDecimal r3,String bimestre, Notas nota) {

		BigDecimal temp1 = n1;

		BigDecimal temp2 = n2;

		BigDecimal temp3 = n3;

		if (null != n1 && null != r1) {

			if (n1.compareTo(r1) == -1) {
				temp1 = r1;
			}

		}

		if (null != n2 && null != r2) {

			if (n2.compareTo(r2) == -1) {
				temp2 = r2;
			}
		}

		if (null != n3 && null != r3) {

			if (n3.compareTo(r3) == -1) {
				temp3 = r3;
			}
		}
		
		
		

		try {
			
			BigDecimal notas = notasManager.calcularApEm(temp1, temp2, temp3,bimestre, notasAluno);
			setMedia(notas.toString());
			setMediaBimestral(notas.toString());
			
		} catch (Exception e) {
			  FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", e.getMessage());  
	            
	    	  RequestContext.getCurrentInstance().showMessageInDialog(message); 
			e.printStackTrace();
		}
		
		
		if ((null != notasAluno.getNota3_b4() || null != notasAluno.getRec3_b4()) && (notaFinal == null || notaFinal.equals(""))) {
			
    		setMediaParcialFinal(notasManager.CalculaMediaFinal(notasAluno.getMb_b1(), notasAluno.getMb_b2(), notasAluno.getMb_b3(), notasAluno.getMb_b4(), new BigDecimal(notaFinal)).toString());
		}

		if (null != n1) {
			setNota1(aproximacao(n1).toString());
		} else {
			setNota1("");
		}

		if (null != n2) {
			setNota2(aproximacao(n2).toString());
		} else {
			setNota2("");
		}

		if (null != n3) {
			setNota3(aproximacao(n3).toString());
		} else {
			setNota3("");
		}

		if (null != r1) {
			setRec1(aproximacao(r1).toString());
		} else {
			setRec1("");
		}

		if (null != r2) {
			setRec2(aproximacao(r2).toString());
		} else {
			setRec2("");
		}

		if (null != r3) {
			setRec3(aproximacao(r3).toString());
		} else {
			setRec3("");
		}
	}

	private BigDecimal aproximacao(BigDecimal valor) {
		int decimalPlace = 1;

		valor = valor.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);

		return valor;

	}

}
