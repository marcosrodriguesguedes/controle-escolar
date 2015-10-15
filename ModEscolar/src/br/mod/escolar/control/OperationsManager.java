package br.mod.escolar.control;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.mod.escolar.control.DisciplineManager.DisciplineInfo;
import br.mod.escolar.model.entities.Frequencia;
import br.mod.escolar.model.entities.FrequenciaHistorico;
import br.mod.escolar.model.entities.Historico;
import br.mod.escolar.model.entities.Notas;
import br.mod.escolar.model.entities.NotasHistorico;
import br.mod.escolar.model.entities.Student;
import br.mod.escolar.model.exception.InvalidParameterException;
import br.mod.escolar.model.exception.NoSuchPermissionException;
import br.mod.escolar.model.exception.NoSuchSessionException;
import br.mod.escolar.model.exception.NoSuchStudentException;
import br.mod.escolar.model.exception.NoSuchUserTypeException;
import br.mod.escolar.model.util.HibernateUtil;

public class OperationsManager {

	private static OperationsManager manager = null;

	private NotasManager notasManager;

	private DisciplineManager disciplineManager;

	private FrequenciaManager frequencia;

	private StudentManager studentManager;

	private FacadeAuth facadeAuth;

	private OperationsManager() {

		this.notasManager = NotasManager.getInstance();

		this.disciplineManager = DisciplineManager.getInstance();

		this.frequencia = FrequenciaManager.getInstance();

		this.studentManager = StudentManager.getInstance();

		this.facadeAuth = FacadeAuth.getInstance();
	}

	public static synchronized OperationsManager getInstance() {

		if (null == manager) {

			manager = new OperationsManager();
		}

		return manager;

	}

	public boolean concluirAnoLetivoDisciplina(String session, String grau,
			String serie) throws NoSuchStudentException {

		List<Notas> notas = notasManager.getNotasAllStudentByGrade2(grau,
				String.valueOf(serie));

		List<Student> alunos = new ArrayList<Student>();

		for (int i = 0; i < notas.size(); i++) {

			Notas notaAtual = notas.get(i);

			if (notaAtual.getAtivo() == 0
					&& moveFrequenciHistoricoStudent(String.valueOf(notaAtual
							.getId_aluno()))
					&& moveToHistoricoNotasStudent(String.valueOf(notaAtual
							.getId_aluno()))) {

				alunos.add(studentManager.getStudent(notaAtual.getId_aluno()));

			} else {

				// roolback

				return false;

			}

		}

		boolean rmNotasOK = true;
		boolean rmFrequenciaOK = true;

		for (int i = 0; i < alunos.size(); i++) {

			rmFrequenciaOK = removeFrequenciaStudent(String.valueOf(alunos.get(i).getId()));
			rmNotasOK = removeNotasStudent(String.valueOf(alunos.get(i).getId()));
		}

		if (rmFrequenciaOK && rmNotasOK) {

			try {
				facadeAuth.advanceGrade(session, alunos);
			} catch (NoSuchSessionException | InvalidParameterException
					| NoSuchUserTypeException | NoSuchPermissionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

		}
		return rmFrequenciaOK && rmNotasOK;

	}

	private boolean moveFrequenciaToHistorico(String id_student) {

		try {

			FrequenciaHistorico frequenciahist = null;

			List<Frequencia> frequencias = frequencia
					.getFrequenciaStudentAllFaltas(id_student);

			if (null != frequencias) {

				for (int i = 0; i < frequencias.size(); i++) {

					Frequencia freq = frequencias.get(i);

					frequenciahist = new FrequenciaHistorico();

					String nomeDisciplina = disciplineManager
							.getDisciplineInfo(
									Integer.parseInt(freq.getIdDisciplina()),
									DisciplineInfo.NAME);

					frequenciahist.setData(freq.getData());

					frequenciahist.setFrequencia(freq.getFrequencia());

					frequenciahist.setGrau(freq.getGrau());

					frequenciahist.setSerie(freq.getSerie());

					frequenciahist.setIdAluno(freq.getIdAluno());

					frequenciahist.setNomeDisciplina(nomeDisciplina);

					frequenciahist.setIdDisciplina(freq.getIdDisciplina());

					HibernateUtil.create(frequenciahist);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	private boolean moveNotasToHistorico(String idStudent) {
		try {

			Historico hist = null;

			NotasHistorico notasHist = null;

			List<Notas> notas = notasManager.getNotasAll(idStudent);

			for (int i = 0; i < notas.size(); i++) {

				Notas nota = notas.get(i);

				String nomeDisciplina = disciplineManager.getDisciplineInfo(
						nota.getId_disciplina(), DisciplineInfo.NAME);

				hist = new Historico();

				notasHist = new NotasHistorico();

				notasHist.setAnoLetivo(nota.getDataInsercao());

				notasHist.setSerie(nota.getSerie());

				notasHist.setGrau(nota.getGrau());

				notasHist.setId_aluno(nota.getId_aluno());

				notasHist.setId_disciplina(nota.getId_disciplina());

				notasHist.setNomeDisciplina(nomeDisciplina);

				notasHist.setMb_b1(nota.getMb_b1());

				notasHist.setMb_b2(nota.getMb_b2());

				notasHist.setMb_b3(nota.getMb_b3());

				notasHist.setMb_b4(nota.getMb_b4());

				notasHist.setMf(nota.getMf());

				notasHist.setNota1_b1(nota.getNota1_b1());

				notasHist.setNota2_b1(nota.getNota2_b1());

				notasHist.setNota3_b1(nota.getNota3_b1());

				notasHist.setNota1_b2(nota.getNota1_b2());

				notasHist.setNota2_b2(nota.getNota2_b2());

				notasHist.setNota3_b2(nota.getNota3_b2());

				notasHist.setNota1_b3(nota.getNota1_b3());

				notasHist.setNota2_b3(nota.getNota2_b3());

				notasHist.setNota3_b3(nota.getNota3_b3());

				notasHist.setNota1_b4(nota.getNota1_b4());

				notasHist.setNota2_b4(nota.getNota2_b4());

				notasHist.setNota3_b4(nota.getNota3_b4());

				SimpleDateFormat format = new SimpleDateFormat("yyyy");

				hist.setAnoLetivo(format.format(nota.getDataInsercao()));

				hist.setId_aluno(nota.getId_aluno());

				hist.setId_disciplina(nota.getId_disciplina());

				hist.setCod_disciplina(nota.getCod_disciplina());

				hist.setGrau(nota.getGrau());

				hist.setSerie(nota.getSerie());

				hist.setMb_b1(nota.getMb_b1());

				hist.setMb_b2(nota.getMb_b2());

				hist.setMb_b3(nota.getMb_b3());

				hist.setMb_b4(nota.getMb_b4());

				hist.setMf(nota.getMf());

				hist.setNomeDisciplina(nomeDisciplina);

				HibernateUtil.create(notasHist);

				hist.setMp(notasManager.calculaMediaAnual(nota.getMb_b1(),
						nota.getMb_b2(), nota.getMb_b3(), nota.getMb_b4()));

				if (nota.getMf().compareTo(new BigDecimal("5.0")) == -1) {

					hist.setStatus("REPROVADO");

				} else {

					hist.setStatus("APROVADO");
				}

				HibernateUtil.create(hist);

			}

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean removeNotasStudent(String id_student) {

		try {

			List<Notas> notas = notasManager.getNotasAll(id_student);

			for (int i = 0; i < notas.size(); i++) {

				HibernateUtil.delete(notas.get(i));
			}

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean removeFrequenciaStudent(String id_student) {

		try {

			List<Frequencia> frequencias = frequencia
					.getFrequenciaStudentAll(id_student);

			for (int i = 0; i < frequencias.size(); i++) {

				HibernateUtil.delete(frequencias.get(i));
			}

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean moveFrequenciHistoricoStudent(String id_student) {

		boolean backup = moveFrequenciaToHistorico(id_student);

		return backup;

	}

	public boolean moveToHistoricoNotasStudent(String id_student) {

		boolean backup = moveNotasToHistorico(id_student);

		return backup;
	}
}