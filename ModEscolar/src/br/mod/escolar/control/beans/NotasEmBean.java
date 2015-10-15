package br.mod.escolar.control.beans;

import java.math.BigDecimal;
import java.sql.SQLException;
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
import org.primefaces.event.SelectEvent;

import br.mod.escolar.control.FacadeAuth;
import br.mod.escolar.control.NotasManager;
import br.mod.escolar.control.OperationsManager;
import br.mod.escolar.control.StudentManager;
import br.mod.escolar.control.TurmasManager;
import br.mod.escolar.model.entities.Notas;
import br.mod.escolar.model.entities.Student;
import br.mod.escolar.model.entities.TeacherClassID;
import br.mod.escolar.model.exception.NoSuchSessionException;
import br.mod.escolar.model.exception.NoSuchTeacherDisciplineException;
import br.mod.escolar.model.util.HibernateUtil;

@ManagedBean
@ViewScoped
public class NotasEmBean {

	private List<Student> filteredStudent;

	private List<Student> students;

	private Student selectedStudent;

	private Student[] selectedStudents;

	private FacadeAuth facadeAuth;

	private NotasManager notasManager;

	private LoginBean login;

	private String turma;

	private String bimestre;

	private Double nota1;

	private Double nota2;

	private Double nota3;

	private Double rec1;

	private Double rec2;

	private Double rec3;

	private Notas notasAluno;

	private boolean create;

	private Double media;

	private Double mediaBimestral;

	private BigDecimal mediaParcialFinal;

	private BigDecimal notaFinal;

	private BigDecimal mediaFinal;

	private String status;

	public OperationsManager controleAnoLetivo;

	public TurmasManager controleTurmas;
	
	public StudentManager studentManager;

	public NotasEmBean() {

		setTurma("A");
		setBimestre("1");
		setStatus("Cursando");
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(true);

		login = (LoginBean) session.getAttribute("loginBean");

		facadeAuth = FacadeAuth.getInstance();

		notasManager = NotasManager.getInstance();

		controleTurmas = TurmasManager.getInstance();
		
		studentManager = StudentManager.getInstance();

		TeacherClassID turmaTeacher = null;

		try {
			if (null != login.getId_teacher()) {
               
				if(null != login.getDisciplina()){
				
                	turmaTeacher = controleTurmas.getTeacherDisciplineTurma(turma,
						Integer.parseInt(login.getId_teacher()), login
								.getDisciplina().getId());
                }

			} else {
                if(null != login.getDisciplina()){
				
                	turmaTeacher = controleTurmas.getTeacherDisciplineTurma(turma,
						Integer.parseInt(login.getId_pessoal()), login
								.getDisciplina().getId());
                }
			}

		} catch (NumberFormatException | NoSuchTeacherDisciplineException e1) {
		
			e1.printStackTrace();
		}

		if (null != turmaTeacher) {

			students = getFacadeAuth().getStudentsGrauSerie(
					String.valueOf(login.getDisciplina().getGrade()),
					getTurma(), login.getDisciplina().getGrade2());
		}

		if (null != students && !students.isEmpty()) {

			selectedStudent = students.get(0);

			try {

				notasAluno = facadeAuth.getNotasAll(login.getSession(),
						String.valueOf(login.getDisciplina().getId()),
						String.valueOf(selectedStudent.getId()));

				if (null != notasAluno) {

					create = false;

					atualizaCamposNotas(notasAluno.getNota1_b1(),
							notasAluno.getNota2_b1(), notasAluno.getNota3_b1(),
							notasAluno.getRec1_b1(), notasAluno.getRec2_b1(),
							notasAluno.getRec3_b1(), getBimestre(), notasAluno);
				} else {

					create = true;

					notasAluno = new Notas();
				}

			} catch (SQLException | NoSuchSessionException e) {

				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "ERRO",
						"ERRO AO BUSCAR NOTAS DO ALUNO: " + e);

				RequestContext.getCurrentInstance()
						.showMessageInDialog(message);

				e.printStackTrace();
			}
		}

	}

	public void selectionChangedTurma(final AjaxBehaviorEvent event) {

		selectedStudent = null;

		setNota1(null);

		setNota2(null);

		setNota3(null);

		setRec1(null);

		setRec2(null);

		setRec3(null);

		setMediaFinal(null);

		setMediaParcialFinal(null);

		setMediaBimestral(null);

		setMedia(null);

		setStatus("Cursando");

		TeacherClassID turmaTeacher = null;

		try {

			if (null != login.getId_teacher()) {

				turmaTeacher = controleTurmas.getTeacherDisciplineTurma(turma,
						Integer.parseInt(login.getId_teacher()), login
								.getDisciplina().getId());

			} else {

				turmaTeacher = controleTurmas.getTeacherDisciplineTurma(turma,
						Integer.parseInt(login.getId_pessoal()), login
								.getDisciplina().getId());
			}

		} catch (NumberFormatException | NoSuchTeacherDisciplineException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (null != turmaTeacher) {

			students = getFacadeAuth().getStudentsGrauSerie(
					String.valueOf(login.getDisciplina().getGrade()),
					getTurma(), login.getDisciplina().getGrade2());
		}

		if (null != students && !students.isEmpty()) {

			try {

				selectedStudent = students.get(0);

				notasAluno = facadeAuth.getNotasAll(login.getSession(),
						String.valueOf(login.getDisciplina().getId()),
						String.valueOf(selectedStudent.getId()));

				if (null != notasAluno) {

					create = false;

					atualizaCamposNotas(notasAluno.getNota1_b1(),
							notasAluno.getNota2_b1(), notasAluno.getNota3_b1(),
							notasAluno.getRec1_b1(), notasAluno.getRec2_b1(),
							notasAluno.getRec3_b1(), getBimestre(), notasAluno);
				} else {

					create = true;

					notasAluno = new Notas();
				}

			} catch (SQLException | NoSuchSessionException e) {

				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "ERRO",
						"ERRO AO BUSCAR NOTAS DO ALUNO: " + e);

				RequestContext.getCurrentInstance()
						.showMessageInDialog(message);

				e.printStackTrace();
			}
		} else {

			students = null;

		}
	}

	public void onRowSelect(SelectEvent event) {

		FacesMessage msg = new FacesMessage("Aluno Selecionado",
				((Student) event.getObject()).getName());

		FacesContext.getCurrentInstance().addMessage(null, msg);

		try {

			notasAluno = facadeAuth.getNotasAll(login.getSession(),
					String.valueOf(login.getDisciplina().getId()),
					String.valueOf(selectedStudent.getId()));

			if (null != notasAluno) {

				create = false;

				switch (bimestre) {

				case "1":

					atualizaCamposNotas(notasAluno.getNota1_b1(),
							notasAluno.getNota2_b1(), notasAluno.getNota3_b1(),
							notasAluno.getRec1_b1(), notasAluno.getRec2_b1(),
							notasAluno.getRec3_b1(), getBimestre(), notasAluno);

					break;

				case "2":

					atualizaCamposNotas(notasAluno.getNota1_b2(),
							notasAluno.getNota2_b2(), notasAluno.getNota3_b2(),
							notasAluno.getRec1_b2(), notasAluno.getRec2_b2(),
							notasAluno.getRec3_b2(), getBimestre(), notasAluno);

					break;

				case "3":

					atualizaCamposNotas(notasAluno.getNota1_b3(),
							notasAluno.getNota2_b3(), notasAluno.getNota3_b3(),
							notasAluno.getRec1_b3(), notasAluno.getRec2_b3(),
							notasAluno.getRec3_b3(), getBimestre(), notasAluno);
					break;

				case "4":

					atualizaCamposNotas(notasAluno.getNota1_b4(),
							notasAluno.getNota2_b4(), notasAluno.getNota3_b4(),
							notasAluno.getRec1_b4(), notasAluno.getRec2_b4(),
							notasAluno.getRec3_b4(), getBimestre(), notasAluno);
					break;

				default:
					break;
				}

			} else {

				setNota1(null);
				setNota2(null);
				setNota3(null);
				setRec1(null);
				setRec2(null);
				setRec3(null);
				setMedia(null);
				setMediaBimestral(null);
				setMediaParcialFinal(null);
				setMediaFinal(null);
				setStatus("Cursando");

				create = true;

				notasAluno = new Notas();
			}

		} catch (SQLException | NoSuchSessionException e) {

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO",
					"ERRO AO BUSCAR NOTAS DO ALUNO: "
							+ selectedStudent.getName());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			e.printStackTrace();
		}

	}
	
	
	public void desativarAluno(){
		
		notasAluno.setAtivo(0);
		HibernateUtil.update(notasAluno);
		
		FacesMessage message = new FacesMessage(
				FacesMessage.SEVERITY_INFO, "SUCESSO",
				"ALUNO DESATIVADO");

		RequestContext.getCurrentInstance()
				.showMessageInDialog(message);
			
	}

	public void concluirAnoLetivo() {
		
		boolean disciplinaAnoLetivoCompleto = true;
		
		List<Notas> notas = notasManager.getNotasAllStudentByGrade(
				login.getDisciplina().getGrade2(), String.valueOf(login.getDisciplina().getGrade()),
				String.valueOf(login.getDisciplina().getId()), turma);
		
		for (int i = 0; i < notas.size(); i++) {
			
			Notas notaAtual = notas.get(i);
			
			if(null != notaAtual.getMf() ){
				
				if(notaAtual.getAtivo() == 1){
					
					notaAtual.setAtivo(0);
					
					HibernateUtil.update(notaAtual);
				}
				
				
			}else{
				
				disciplinaAnoLetivoCompleto = false;
				
			}
			
		}
		
		if(disciplinaAnoLetivoCompleto){
			
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_INFO, "SUCESSO",
					"Disciplina Finalizada");

			RequestContext.getCurrentInstance()
					.showMessageInDialog(message);
			
		}else{
		
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO",
					"Erro ao Finalizar Disciplina ");

			RequestContext.getCurrentInstance().showMessageInDialog(message);

		
			
			
			
		}
		
		
	}

	public void saveNotas() {

		if (create) {

			try {

				notasAluno.setId_aluno(selectedStudent.getId());

				notasAluno.setId_disciplina(login.getDisciplina().getId());

				notasAluno.setCod_disciplina(login.getDisciplina()
						.getCod_discipline());

				notasAluno.setGrau(login.getDisciplina().getGrade2());

				notasAluno.setSerie(String.valueOf(login.getDisciplina()
						.getGrade()));

				notasAluno.setDataInsercao(new Date());

				facadeAuth.CriarAvaliacaoAnual(notasAluno);

				create = false;

				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO, "SUCESSO",
						"NOTAS DO ALUNO : "
								+ selectedStudent.getName().toUpperCase()
								+ " SALVAS");

				RequestContext.getCurrentInstance()
						.showMessageInDialog(message);

			} catch (Exception e) {

				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "ERRO",
						"ERRO AO SALVAR NOTAS DO ALUNO: "
								+ selectedStudent.getName().toUpperCase());

				RequestContext.getCurrentInstance()
						.showMessageInDialog(message);

				e.printStackTrace();

			}

		} else {

			try {

				facadeAuth.updateNotas(notasAluno);

				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO, "SUCESSO",
						"NOTAS DO ALUNO : "
								+ selectedStudent.getName().toUpperCase()
								+ " ATUALIZADAS");

				RequestContext.getCurrentInstance()
						.showMessageInDialog(message);

			} catch (Exception e) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "ERRO",
						"ERRO AO ATUALIZAR NOTAS DO ALUNO: "
								+ selectedStudent.getName().toUpperCase());

				RequestContext.getCurrentInstance()
						.showMessageInDialog(message);

				e.printStackTrace();
			}

		}

	}

	public void selectionChangedBimestre(final AjaxBehaviorEvent event) {

		switch (bimestre) {

		case "1":

			atualizaCamposNotas(notasAluno.getNota1_b1(),
					notasAluno.getNota2_b1(), notasAluno.getNota3_b1(),
					notasAluno.getRec1_b1(), notasAluno.getRec2_b1(),
					notasAluno.getRec3_b1(), getBimestre(), notasAluno);
			

			break;

		case "2":

			atualizaCamposNotas(notasAluno.getNota1_b2(),
					notasAluno.getNota2_b2(), notasAluno.getNota3_b2(),
					notasAluno.getRec1_b2(), notasAluno.getRec2_b2(),
					notasAluno.getRec3_b2(), getBimestre(), notasAluno);
			

			break;

		case "3":

			atualizaCamposNotas(notasAluno.getNota1_b3(),
					notasAluno.getNota2_b3(), notasAluno.getNota3_b3(),
					notasAluno.getRec1_b3(), notasAluno.getRec2_b3(),
					notasAluno.getRec3_b3(), getBimestre(), notasAluno);
			
			break;

		case "4":

			atualizaCamposNotas(notasAluno.getNota1_b4(),
					notasAluno.getNota2_b4(), notasAluno.getNota3_b4(),
					notasAluno.getRec1_b4(), notasAluno.getRec2_b4(),
					notasAluno.getRec3_b4(), getBimestre(), notasAluno);
			
			break;

		default:
			
			break;
		}

	}

	public void atualizaCamposNotas(BigDecimal n1, BigDecimal n2,
			BigDecimal n3, BigDecimal r1, BigDecimal r2, BigDecimal r3,
			String bimestre, Notas notas) {

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

		BigDecimal mediaBimestral;

		mediaBimestral = notasManager.calcularApEm(temp1, temp2, temp3,
				bimestre, notas);

		setMedia(mediaBimestral.doubleValue());

		setMediaBimestral(mediaBimestral.doubleValue());
		
		setMediaFinal(notas.getMf());

		setMediaParcialFinal(notas.getMf());
		
		setStatus("Cursando");
		
		//setNotaFinal(notas.getNotaFinal().setScale(1));

		if (null != notasAluno.getNota3_b4() || null != notasAluno.getRec3_b4()) {

			BigDecimal nota = null;

			boolean fezProvaFinal = false;

			if (notaFinal == null) {

				nota = new BigDecimal("-1");

				setStatus("Exame Final");

			} else {

				//nota = new BigDecimal(notaFinal);
				nota = notaFinal;

				fezProvaFinal = true;
			}

			BigDecimal resultado = notasManager.CalculaMediaFinal(
					notasAluno.getMb_b1(), notasAluno.getMb_b2(),
					notasAluno.getMb_b3(), notasAluno.getMb_b4(), nota);

			if (resultado.compareTo(new BigDecimal("5")) == -1) {

				setStatus("REPROVADO");

			} else if (!fezProvaFinal
					&& resultado.compareTo(new BigDecimal("7")) == -1) {

				setStatus("Exame Final");

			} else {

				setStatus("APROVADO");

			}

			setMediaFinal(resultado);

			notasAluno.setMf(resultado);

			setMediaParcialFinal(resultado);
			
			
		}
		
		setNotaFinal(null);

		if (null != n1) {
			setNota1(aproximacao(n1).doubleValue());
		} else {
			setNota1(null);
		}

		if (null != n2) {
			setNota2(aproximacao(n2).doubleValue());
		} else {
			setNota2(null);
		}

		if (null != n3) {
			setNota3(aproximacao(n3).doubleValue());
		} else {
			setNota3(null);
		}

		if (null != r1) {
			setRec1(aproximacao(r1).doubleValue());
		} else {
			setRec1(null);
		}

		if (null != r2) {
			setRec2(aproximacao(r2).doubleValue());
		} else {
			setRec2(null);
		}

		if (null != r3) {
			setRec3(aproximacao(r3).doubleValue());
		} else {
			setRec3(null);
		}
	}

	public void changeNota1(final AjaxBehaviorEvent event) {
		try {

			switch (bimestre) {

			case "1":

				notasAluno.setNota1_b1(new BigDecimal(getNota1()));
				atualizaCamposNotas(notasAluno.getNota1_b1(),
						notasAluno.getNota2_b1(), notasAluno.getNota3_b1(),
						notasAluno.getRec1_b1(), notasAluno.getRec2_b1(),
						notasAluno.getRec3_b1(), getBimestre(), notasAluno);
				saveNotas();
				break;

			case "2":

				notasAluno.setNota1_b2(new BigDecimal(getNota1()));
				atualizaCamposNotas(notasAluno.getNota1_b2(),
						notasAluno.getNota2_b2(), notasAluno.getNota3_b2(),
						notasAluno.getRec1_b2(), notasAluno.getRec2_b2(),
						notasAluno.getRec3_b2(), getBimestre(), notasAluno);
				saveNotas();
				break;

			case "3":

				notasAluno.setNota1_b3(new BigDecimal(getNota1()));
				atualizaCamposNotas(notasAluno.getNota1_b3(),
						notasAluno.getNota2_b3(), notasAluno.getNota3_b3(),
						notasAluno.getRec1_b3(), notasAluno.getRec2_b3(),
						notasAluno.getRec3_b3(), getBimestre(), notasAluno);
				saveNotas();
				break;

			case "4":

				notasAluno.setNota1_b4(new BigDecimal(getNota1()));
				atualizaCamposNotas(notasAluno.getNota1_b4(),
						notasAluno.getNota2_b4(), notasAluno.getNota3_b4(),
						notasAluno.getRec1_b4(), notasAluno.getRec2_b4(),
						notasAluno.getRec3_b4(), getBimestre(), notasAluno);
				saveNotas();
				break;

			default:
				break;
			}

		} catch (Exception e) {

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO",
					"VALOR N�O NUM�RICO INSERIDO, FAVOR VERIFICAR: ");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}
	}

	public void changeNota2() {
		try {

			switch (bimestre) {

			case "1":

				notasAluno.setNota2_b1(new BigDecimal(getNota2()));
				atualizaCamposNotas(notasAluno.getNota1_b1(),
						notasAluno.getNota2_b1(), notasAluno.getNota3_b1(),
						notasAluno.getRec1_b1(), notasAluno.getRec2_b1(),
						notasAluno.getRec3_b1(), getBimestre(), notasAluno);
				saveNotas();
				break;

			case "2":

				notasAluno.setNota2_b2(new BigDecimal(getNota2()));
				atualizaCamposNotas(notasAluno.getNota1_b2(),
						notasAluno.getNota2_b2(), notasAluno.getNota3_b2(),
						notasAluno.getRec1_b2(), notasAluno.getRec2_b2(),
						notasAluno.getRec3_b2(), getBimestre(), notasAluno);
				saveNotas();
				break;

			case "3":

				notasAluno.setNota2_b3(new BigDecimal(getNota2()));
				atualizaCamposNotas(notasAluno.getNota1_b3(),
						notasAluno.getNota2_b3(), notasAluno.getNota3_b3(),
						notasAluno.getRec1_b3(), notasAluno.getRec2_b3(),
						notasAluno.getRec3_b3(), getBimestre(), notasAluno);
				saveNotas();
				break;

			case "4":

				notasAluno.setNota2_b4(new BigDecimal(getNota2()));
				atualizaCamposNotas(notasAluno.getNota1_b4(),
						notasAluno.getNota2_b4(), notasAluno.getNota3_b4(),
						notasAluno.getRec1_b4(), notasAluno.getRec2_b4(),
						notasAluno.getRec3_b4(), getBimestre(), notasAluno);
				saveNotas();
				break;

			default:
				break;
			}

		} catch (Exception e) {

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO",
					"VALOR N�O NUM�RICO INSERIDO, FAVOR VERIFICAR: ");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}

	}

	public void changeNota3() {
		try {

			switch (bimestre) {

			case "1":

				notasAluno.setNota3_b1(new BigDecimal(getNota3()));
				atualizaCamposNotas(notasAluno.getNota1_b1(),
						notasAluno.getNota2_b1(), notasAluno.getNota3_b1(),
						notasAluno.getRec1_b1(), notasAluno.getRec2_b1(),
						notasAluno.getRec3_b1(), getBimestre(), notasAluno);
				saveNotas();
				break;

			case "2":

				notasAluno.setNota3_b2(new BigDecimal(getNota3()));
				atualizaCamposNotas(notasAluno.getNota1_b2(),
						notasAluno.getNota2_b2(), notasAluno.getNota3_b2(),
						notasAluno.getRec1_b2(), notasAluno.getRec2_b2(),
						notasAluno.getRec3_b2(), getBimestre(), notasAluno);
				saveNotas();
				break;

			case "3":

				notasAluno.setNota3_b3(new BigDecimal(getNota3()));
				atualizaCamposNotas(notasAluno.getNota1_b3(),
						notasAluno.getNota2_b3(), notasAluno.getNota3_b3(),
						notasAluno.getRec1_b3(), notasAluno.getRec2_b3(),
						notasAluno.getRec3_b3(), getBimestre(), notasAluno);
				saveNotas();
				break;

			case "4":

				notasAluno.setNota3_b4(new BigDecimal(getNota3()));
				atualizaCamposNotas(notasAluno.getNota1_b4(),
						notasAluno.getNota2_b4(), notasAluno.getNota3_b4(),
						notasAluno.getRec1_b4(), notasAluno.getRec2_b4(),
						notasAluno.getRec3_b4(), getBimestre(), notasAluno);
				saveNotas();
				break;

			default:
				break;
			}

		} catch (Exception e) {

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO",
					"VALOR N�O NUM�RICO INSERIDO, FAVOR VERIFICAR: ");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}

	}

	public void changeRec1() {
		try {

			switch (bimestre) {

			case "1":

				notasAluno.setRec1_b1(new BigDecimal(getRec1()));
				atualizaCamposNotas(notasAluno.getNota1_b1(),
						notasAluno.getNota2_b1(), notasAluno.getNota3_b1(),
						notasAluno.getRec1_b1(), notasAluno.getRec2_b1(),
						notasAluno.getRec3_b1(), getBimestre(), notasAluno);
				saveNotas();
				break;

			case "2":

				notasAluno.setRec1_b2(new BigDecimal(getRec1()));
				atualizaCamposNotas(notasAluno.getNota1_b2(),
						notasAluno.getNota2_b2(), notasAluno.getNota3_b2(),
						notasAluno.getRec1_b2(), notasAluno.getRec2_b2(),
						notasAluno.getRec3_b2(), getBimestre(), notasAluno);
				saveNotas();
				break;

			case "3":

				notasAluno.setRec1_b3(new BigDecimal(getRec1()));
				atualizaCamposNotas(notasAluno.getNota1_b3(),
						notasAluno.getNota2_b3(), notasAluno.getNota3_b3(),
						notasAluno.getRec1_b3(), notasAluno.getRec2_b3(),
						notasAluno.getRec3_b3(), getBimestre(), notasAluno);
				saveNotas();
				break;

			case "4":

				notasAluno.setRec1_b4(new BigDecimal(getRec1()));
				atualizaCamposNotas(notasAluno.getNota1_b4(),
						notasAluno.getNota2_b4(), notasAluno.getNota3_b4(),
						notasAluno.getRec1_b4(), notasAluno.getRec2_b4(),
						notasAluno.getRec3_b4(), getBimestre(), notasAluno);
				saveNotas();
				break;

			default:
				break;
			}

		} catch (Exception e) {

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO",
					"VALOR N�O NUM�RICO INSERIDO, FAVOR VERIFICAR: ");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}

	}

	public void changeRec2() {
		try {

			switch (bimestre) {

			case "1":

				notasAluno.setRec2_b1(new BigDecimal(getRec2()));
				atualizaCamposNotas(notasAluno.getNota1_b1(),
						notasAluno.getNota2_b1(), notasAluno.getNota3_b1(),
						notasAluno.getRec1_b1(), notasAluno.getRec2_b1(),
						notasAluno.getRec3_b1(), getBimestre(), notasAluno);
				saveNotas();
				break;

			case "2":

				notasAluno.setRec2_b2(new BigDecimal(getRec2()));
				atualizaCamposNotas(notasAluno.getNota1_b2(),
						notasAluno.getNota2_b2(), notasAluno.getNota3_b2(),
						notasAluno.getRec1_b2(), notasAluno.getRec2_b2(),
						notasAluno.getRec3_b2(), getBimestre(), notasAluno);
				saveNotas();
				break;

			case "3":

				notasAluno.setRec2_b3(new BigDecimal(getRec2()));
				atualizaCamposNotas(notasAluno.getNota1_b3(),
						notasAluno.getNota2_b3(), notasAluno.getNota3_b3(),
						notasAluno.getRec1_b3(), notasAluno.getRec2_b3(),
						notasAluno.getRec3_b3(), getBimestre(), notasAluno);
				saveNotas();
				break;

			case "4":

				notasAluno.setRec2_b4(new BigDecimal(getRec2()));
				atualizaCamposNotas(notasAluno.getNota1_b4(),
						notasAluno.getNota2_b4(), notasAluno.getNota3_b4(),
						notasAluno.getRec1_b4(), notasAluno.getRec2_b4(),
						notasAluno.getRec3_b4(), getBimestre(), notasAluno);
				saveNotas();
				break;

			default:
				break;
			}
		} catch (Exception e) {

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO",
					"VALOR NAO NUMERICO INSERIDO, FAVOR VERIFICAR: ");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}
	}

	public void changeRec3() {
		try {

			switch (bimestre) {

			case "1":

				notasAluno.setRec3_b1(new BigDecimal(getRec3()));
				atualizaCamposNotas(notasAluno.getNota1_b1(),
						notasAluno.getNota2_b1(), notasAluno.getNota3_b1(),
						notasAluno.getRec1_b1(), notasAluno.getRec2_b1(),
						notasAluno.getRec3_b1(), getBimestre(), notasAluno);
				saveNotas();
				break;

			case "2":

				notasAluno.setRec3_b2(new BigDecimal(getRec3()));
				atualizaCamposNotas(notasAluno.getNota1_b2(),
						notasAluno.getNota2_b2(), notasAluno.getNota3_b2(),
						notasAluno.getRec1_b2(), notasAluno.getRec2_b2(),
						notasAluno.getRec3_b2(), getBimestre(), notasAluno);
				saveNotas();
				break;

			case "3":

				notasAluno.setRec3_b3(new BigDecimal(getRec3()));
				atualizaCamposNotas(notasAluno.getNota1_b3(),
						notasAluno.getNota2_b3(), notasAluno.getNota3_b3(),
						notasAluno.getRec1_b3(), notasAluno.getRec2_b3(),
						notasAluno.getRec3_b3(), getBimestre(), notasAluno);
				saveNotas();
				break;

			case "4":

				notasAluno.setRec3_b4(new BigDecimal(getRec3()));
				atualizaCamposNotas(notasAluno.getNota1_b4(),
						notasAluno.getNota2_b4(), notasAluno.getNota3_b4(),
						notasAluno.getRec1_b4(), notasAluno.getRec2_b4(),
						notasAluno.getRec3_b4(), getBimestre(), notasAluno);
				saveNotas();
				break;

			default:
				break;
			}
		} catch (Exception e) {

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO",
					"VALOR NAO NUM�RICO INSERIDO, FAVOR VERIFICAR: ");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}

	}

	public void changeFinal() {
		try {

			//notasAluno.setNotaFinal(new BigDecimal(notaFinal));
			notasAluno.setNotaFinal(notaFinal);
			if (null != notasAluno.getNota3_b4()
					|| null != notasAluno.getRec3_b4()) {

				BigDecimal nota = null;

				if (notaFinal == null) {

					nota = new BigDecimal("-1");

				} else {

					//nota = new BigDecimal(notaFinal);
					nota = notaFinal;
				}

				BigDecimal mediaFinal = notasManager.CalculaMediaFinal(
						notasAluno.getMb_b1(), notasAluno.getMb_b2(),
						notasAluno.getMb_b3(), notasAluno.getMb_b4(), nota);

				setMediaParcialFinal(mediaFinal);
				setMediaFinal(mediaFinal);
				saveNotas();

			}
		} catch (Exception e) {

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO",
					"VALOR NÃO NUM�RICO INSERIDO, FAVOR VERIFICAR: ");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}

	}

	public Notas getNotasAluno() {
		return notasAluno;
	}

	public void setNotasAluno(Notas notasAluno) {
		this.notasAluno = notasAluno;
	}

	public FacadeAuth getFacadeAuth() {
		return facadeAuth;
	}

	

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public String getBimestre() {
		return bimestre;
	}

	public void setBimestre(String bimestre) {
		this.bimestre = bimestre;
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

	public List<Student> getFilteredStudent() {
		return filteredStudent;
	}

	public void setFilteredStudent(List<Student> filteredStudent) {
		this.filteredStudent = filteredStudent;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Student getSelectedStudent() {
		return selectedStudent;
	}

	public void setSelectedStudent(Student selectedStudent) {
		this.selectedStudent = selectedStudent;
	}

	public Student[] getSelectedStudents() {
		return selectedStudents;
	}

	public void setSelectedStudents(Student[] selectedStudents) {
		this.selectedStudents = selectedStudents;
	}

	public boolean isCreate() {
		return create;
	}
	
	public NotasManager getNotasManager() {
		return notasManager;
	}

	public void setNotasManager(NotasManager notasManager) {
		this.notasManager = notasManager;
	}

	public Double getNota1() {
		return nota1;
	}

	public void setNota1(Double nota1) {
		this.nota1 = nota1;
	}

	public Double getNota2() {
		return nota2;
	}

	public void setNota2(Double nota2) {
		this.nota2 = nota2;
	}

	public Double getNota3() {
		return nota3;
	}

	public void setNota3(Double nota3) {
		this.nota3 = nota3;
	}

	public Double getRec1() {
		return rec1;
	}

	public void setRec1(Double rec1) {
		this.rec1 = rec1;
	}

	public Double getRec2() {
		return rec2;
	}

	public void setRec2(Double rec2) {
		this.rec2 = rec2;
	}

	public Double getRec3() {
		return rec3;
	}

	public void setRec3(Double rec3) {
		this.rec3 = rec3;
	}

	public OperationsManager getControleAnoLetivo() {
		return controleAnoLetivo;
	}

	public void setControleAnoLetivo(OperationsManager controleAnoLetivo) {
		this.controleAnoLetivo = controleAnoLetivo;
	}

	public TurmasManager getControleTurmas() {
		return controleTurmas;
	}

	public void setControleTurmas(TurmasManager controleTurmas) {
		this.controleTurmas = controleTurmas;
	}

	public StudentManager getStudentManager() {
		return studentManager;
	}

	public void setStudentManager(StudentManager studentManager) {
		this.studentManager = studentManager;
	}

	public void setMedia(Double media) {
		this.media = media;
	}

	public void setMediaBimestral(Double mediaBimestral) {
		this.mediaBimestral = mediaBimestral;
	}

	public void setMediaParcialFinal(BigDecimal mediaParcialFinal) {
		this.mediaParcialFinal = mediaParcialFinal;
	}

	public void setNotaFinal(BigDecimal notaFinal) {
		this.notaFinal = notaFinal;
	}

	public void setMediaFinal(BigDecimal mediaFinal) {
		this.mediaFinal = mediaFinal;
	}

	public void setCreate(boolean create) {
		this.create = create;
	}

	

	public Double getMedia() {
		return media;
	}

	public Double getMediaBimestral() {
		return mediaBimestral;
	}

	public BigDecimal getMediaParcialFinal() {
		return mediaParcialFinal;
	}

	public BigDecimal getNotaFinal() {
		return notaFinal;
	}

	public BigDecimal getMediaFinal() {
		return mediaFinal;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private BigDecimal aproximacao(BigDecimal valor) {
		int decimalPlace = 1;

		valor = valor.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);

		return valor;

	}

}
