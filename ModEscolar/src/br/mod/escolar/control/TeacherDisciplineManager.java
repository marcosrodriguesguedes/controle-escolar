package br.mod.escolar.control;

/**
 * UNIVERSIDADE FEDERAL DE CAMPINA GRANDE
 * DEPARTAMENTO DE SISTEMAS E COMPUTAÇÃO
 * CURSO DE CIÊNCIA DA COMPUTAÇÃO
 * 
 * @author Antônio Junior
 * @author Romeryto Lira
 * @author Vicente Matias
 * 
 * Esta classe faz parte do projeto desenvolvido para a disciplina Projeto 1, do curso de 
 * ciência da computação, no período 2009.2, ministrada pela professora Francilene Garcia
 * 
 * Favor não redistribuir sem permissão ou sem citação dos respectivos autores
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.exception.ConstraintViolationException;

import br.mod.escolar.control.DisciplineManager.DisciplineInfo;
import br.mod.escolar.model.entities.TeacherDiscipline;
import br.mod.escolar.model.exception.DBException;
import br.mod.escolar.model.exception.InvalidParameterException;
import br.mod.escolar.model.exception.NoSuchDisciplineException;
import br.mod.escolar.model.exception.NoSuchStudentException;
import br.mod.escolar.model.exception.NoSuchTeacherDisciplineException;
import br.mod.escolar.model.util.HibernateUtil;
import br.mod.escolar.model.util.Messages;
import br.mod.escolar.model.util.Validator;

public class TeacherDisciplineManager {

	public enum TeacherDisciplineInfo {
		ID("id"), ID_TEACHER("id_teacher"), ID_DISCIPLINE("id_discipline");

		private String descricao;

		private TeacherDisciplineInfo(final String descricao) {
			this.descricao = descricao;
		}

		public String getDescricao() {
			return descricao;
		}
	}

	private static TeacherDisciplineManager manager = null;

	/**
	 * Construtor default privado para TeacherDisciplineManager
	 */
	private TeacherDisciplineManager() {

	}

	/**
	 * Padrão Singleton
	 * 
	 */
	public static TeacherDisciplineManager getInstance() {
		if (manager == null) {
			manager = new TeacherDisciplineManager();
		}
		return manager;
	}

	/**
	 * Este método cria um objeto TeacherDiscipline
	 * @param id
	 * @param idTeacher
	 * @param idDiscipline
	 * @return identificador gerado pelo sistema gerenciador do banco dedados
	 *         que representa a entidade no sistema
	 * @throws InvalidParameterException
	 */
	public int createTeacherDisciplineRelationship(int id, int idTeacher,
			int idDiscipline) throws InvalidParameterException {
		validate(id, idTeacher, idDiscipline);
		TeacherDiscipline t = new TeacherDiscipline(id, idTeacher, idDiscipline);

		return (Integer) HibernateUtil.create(t);

	}

	/**
	 * Este método faz a validação dos parâmetros abaixo
	 * @param id
	 * @param idTeacher
	 * @param idDiscipline
	 * @throws InvalidParameterException
	 */
	public static void validate(int id, int idTeacher, int idDiscipline)
			throws InvalidParameterException {
		if (!Validator.isNumberValid(id)) {
			throw new InvalidParameterException(
					Messages.INVALID_TEACHER_DISCIPLINE_ID);
		}
		if (!Validator.isNumberValid(idTeacher)) {
			throw new InvalidParameterException(Messages.INVALID_TEACHER_ID);
		}
		if (!Validator.isNumberValid(idDiscipline)) {
			throw new InvalidParameterException(Messages.INVALID_DISCIPLINE_ID);
		}

	}
	/**
	 * 
	 * @param id
	 * @return um objeto da classe TeacherDiscipline com o id informado
	 * @throws NoSuchStudentException
	 */
	@SuppressWarnings("unchecked")
	public TeacherDiscipline getTeacherDiscipline(int id)
			throws NoSuchTeacherDisciplineException {
		Collection<SimpleExpression> exps = new ArrayList<SimpleExpression>();
		exps.add(Restrictions.like("idTeacher", id));
		List<TeacherDiscipline> teacherDiscipline = null;
		try {
			teacherDiscipline = (List<TeacherDiscipline>) HibernateUtil
					.createQuery(TeacherDiscipline.class, exps);
		} catch (ConstraintViolationException e) {
			throw new NoSuchTeacherDisciplineException();
		}

		return teacherDiscipline.isEmpty() ? null : teacherDiscipline.get(0);
	}


	/**
	 * 
	 * @param id
	 * @param info
	 * @return uma informação desejada sobre um objeto TeacherDiscipline
	 * @throws NoSuchTeacherDisciplineException
	 */
	@SuppressWarnings("unchecked")
	public List<TeacherDiscipline> getDisciplines(int idTeacher)
			throws NoSuchTeacherDisciplineException {
		Collection<SimpleExpression> exps = new ArrayList<SimpleExpression>();
		exps.add(Restrictions.like("idTeacher", idTeacher));
		List<TeacherDiscipline> teacherDiscipline = null;
		try {
			teacherDiscipline = (List<TeacherDiscipline>) HibernateUtil
					.createQuery(TeacherDiscipline.class, exps);
		} catch (ConstraintViolationException e) {
			throw new NoSuchTeacherDisciplineException();
		}

		return teacherDiscipline;
	}

	@SuppressWarnings("unchecked")
	public TeacherDiscipline getTeacherDiscipline(int idDisc, int idTeacher)
			throws NoSuchTeacherDisciplineException {
		Collection<SimpleExpression> exps = new ArrayList<SimpleExpression>();
		exps.add(Restrictions.like("idTeacher", idTeacher));
		exps.add(Restrictions.like("idDiscipline", idDisc));
		List<TeacherDiscipline> teacherDiscipline = null;
		try {
			teacherDiscipline = (List<TeacherDiscipline>) HibernateUtil
					.createQuery(TeacherDiscipline.class, exps);
		} catch (ConstraintViolationException e) {
			throw new NoSuchTeacherDisciplineException();
		}

		if (teacherDiscipline.isEmpty())
			throw new NoSuchTeacherDisciplineException();

		return teacherDiscipline.get(0);
	}

	public void removeTeacherDiscipline(int idDisc, int idTeacher)
			throws NoSuchTeacherDisciplineException {
		HibernateUtil.delete(getTeacherDiscipline(idDisc, idTeacher));
	}
	

	public int getTeacherDisciplineInfo(int id, TeacherDisciplineInfo info)
			throws NoSuchTeacherDisciplineException {
		switch (info) {
		case ID_TEACHER:
			return getTeacherDiscipline(id).getIdTeacher();
		case ID_DISCIPLINE:
			return getTeacherDiscipline(id).getIdDiscipline();



		default:
			return 0;
		}
	}

	/**
	 * 
	 * @param info
	 * @param value
	 * @return conjunto de objetos encontrados com o dado informado
	 * @throws DBException
	 */
	@SuppressWarnings("unchecked")
	public List<TeacherDiscipline> getTeacherDiscipline(
			TeacherDisciplineInfo info, String value) throws DBException {
		Collection<SimpleExpression> exps = new ArrayList<SimpleExpression>();
		if (info.equals(DisciplineInfo.NAME)) {
			exps.add(Restrictions.like(info.getDescricao(), value,
					MatchMode.ANYWHERE));

			exps.add(Restrictions.like(info.getDescricao(), value));
		}

		List<TeacherDiscipline> teacherDiscipline = null;
		try {
			teacherDiscipline = (List<TeacherDiscipline>) HibernateUtil
					.createQuery(TeacherDiscipline.class, exps);
		} catch (ConstraintViolationException e) {
			throw new DBException(
					"A busca ocasionou uma violação nas regras de integridade do banco de dados.");
		}

		return teacherDiscipline;
	}

	/**
	 * Este método remove um objeto TeacherDiscipline do sistema
	 * @param id
	 * @return objeto da classe TeacherDiscipline que foi removido
	 * @throws NoSuchDisciplineException
	 */
	public TeacherDiscipline removeTeacherDiscipline(int id)
			throws NoSuchTeacherDisciplineException {

		TeacherDiscipline d = getTeacherDiscipline(id);
		HibernateUtil.delete(d);
		return d;
	}

	/**
	 * Este método faz a atualização dos dados de um objeto TeacherDiscipline
	 * @param id
	 * @param teacher
	 * @param discipline
	 * @throws InvalidParameterException
	 * @throws NoSuchTeacherDisciplineException
	 */
	public void updateTeacherDiscipline(int id, int teacher, int discipline)
			throws InvalidParameterException, NoSuchTeacherDisciplineException {
		TeacherDiscipline d = null;
		if (!Validator.isNumberValid(id)) {
			throw new InvalidParameterException(
					Messages.INVALID_TEACHER_DISCIPLINE_ID);
		} else {
			d = getTeacherDiscipline(id);
		}

		if (d == null) {
			throw new NoSuchTeacherDisciplineException();
		}

		d.setIdDiscipline(discipline);
		d.setIdTeacher(teacher);

		HibernateUtil.update(d);
	}

}
