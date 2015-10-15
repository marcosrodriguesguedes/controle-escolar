package br.mod.escolar.control;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.exception.ConstraintViolationException;

import br.mod.escolar.jdbc.ConnectionMysqlDirect;
import br.mod.escolar.model.entities.Discipline;
import br.mod.escolar.model.entities.HorariosDisciplinas;
import br.mod.escolar.model.exception.DBException;
import br.mod.escolar.model.exception.InvalidParameterException;
import br.mod.escolar.model.exception.NoSuchDisciplineException;
import br.mod.escolar.model.util.HibernateUtil;
import br.mod.escolar.model.util.Messages;
import br.mod.escolar.model.util.Validator;

public class DisciplineManager {

	public enum DisciplineInfo {
		ID("id"), NAME("name"), GRADE("grade"), GRADE2("grade2"), AREA("area"), HOURSPERWEEK(
				"hoursPerWeek"), HOURSPERYEAR("hoursPerYear");

		private String descricao;

		private DisciplineInfo(final String descricao) {
			this.descricao = descricao;
		}

		public String getDescricao() {
			return descricao;
		}

	}

	private static DisciplineManager manager = null;

	/**
	 * Construtor default privado para DisciplineManager
	 */
	private DisciplineManager() {

	}

	/**
	 * Padrão Singleton
	 */
	public static synchronized DisciplineManager getInstance() {
		if (manager == null) {
			manager = new DisciplineManager();
		}
		return manager;
	}

	/**
	 * Este método cria um objeto da classe Discipline
	 * 
	 * @param name
	 * @param grade
	 * @param grade2
	 * @param area
	 * @param hoursPerWeek
	 * @param hoursPerYear
	 * @return identificador gerado pelo sistema gerenciador do banco dedados
	 *         que representa a entidade no sistema
	 * @throws Exception
	 */
	public int createDiscipline(String name, int grade, String grade2,
			String area, String hoursPerWeek, String hoursPerYear)
			throws Exception {
		validate(name, grade, grade2, area, hoursPerWeek, hoursPerYear);

		Discipline d = new Discipline(name, grade, grade2, area, hoursPerWeek,
				hoursPerYear);
		return (Integer) HibernateUtil.create(d);

	}
	 
	/**
	 * Este método faz a validação dos parâmetros abaixo
	 * @param name
	 * @param grade
	 * @param grade2
	 * @param area
	 * @param hoursPerWeek
	 * @param hoursPerYear
	 * @throws InvalidParameterException
	 */
	public static void validate(String name, int grade, String grade2,
			String area, String hoursPerWeek, String hoursPerYear)
			throws InvalidParameterException {

		if (!Validator.isStringValid(name))
			throw new InvalidParameterException(
					Messages.INVALID_DISCIPLINE_NAME);

		if (!Validator.isStringValid(area))
			throw new InvalidParameterException(
					Messages.INVALID_DISCIPLINE_AREA);

		if (!Validator.isNumberValid(grade))
			throw new InvalidParameterException(Messages.INVALID_GRADE);

		if (!Validator.isStringValid(grade2))
			throw new InvalidParameterException(Messages.INVALID_GRADE2);

		if (!Validator.isNumberValid(hoursPerWeek))
			throw new InvalidParameterException(
					Messages.INVALID_DISCIPLINE_HOURS);
		if (!Validator.isNumberValid(hoursPerYear))
			throw new InvalidParameterException(
					Messages.INVALID_DISCIPLINE_HOURS);

	}
	
	/**
	 * @param id
	 * @return um objeto da classe Discipline com o id informado 
	 * @throws NoSuchDisciplineException
	 */
	@SuppressWarnings("unchecked")
	public Discipline getDiscipline(int id) throws NoSuchDisciplineException {
		Collection<SimpleExpression> exps = new ArrayList<SimpleExpression>();
		exps.add(Restrictions.like("id", id));
		List<Discipline> discipline = null;
		try {
			discipline = (List<Discipline>) HibernateUtil.createQuery(
					Discipline.class, exps);
		} catch (ConstraintViolationException e) {
			throw new NoSuchDisciplineException();
		}

		return discipline.isEmpty() ? null : discipline.get(0);
	}
	
	
	

	/**
	 * 
	 * @param id
	 * @param info
	 * @return uma informação sobre um objeto Discipline
	 * @throws NoSuchDisciplineException
	 */
	public String getDisciplineInfo(int id, DisciplineInfo info)
			throws NoSuchDisciplineException {
		if (getDiscipline(id) == null)
			throw new NoSuchDisciplineException();
		switch (info) {
		case NAME:
			return getDiscipline(id).getName();
		case GRADE:
			return getDiscipline(id).getGrade() + "";
		case GRADE2:
			return getDiscipline(id).getGrade2();
		case AREA:
			return getDiscipline(id).getArea();

		case HOURSPERWEEK:
			return getDiscipline(id).getHoursPerWeek();
		case HOURSPERYEAR:
			return getDiscipline(id).getHoursPerYear();
		default:
			return new String();
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
	public List<Discipline> getDiscipline(DisciplineInfo info, String value)
			throws DBException {
		Collection<SimpleExpression> exps = new ArrayList<SimpleExpression>();
		if (info.equals(DisciplineInfo.NAME)) {
			exps.add(Restrictions.like(info.getDescricao(), value,
					MatchMode.ANYWHERE));
		} else if (info.equals(DisciplineInfo.ID)) {
			exps.add(Restrictions.like(info.getDescricao(), Integer.parseInt(value)));
		} else {
			exps.add(Restrictions.like(info.getDescricao(), value));
		}

		List<Discipline> discipline = null;
		try {
			discipline = (List<Discipline>) HibernateUtil.createQuery(
					Discipline.class, exps);
		} catch (ConstraintViolationException e) {
			throw new DBException(
					"A busca ocasionou uma violacao nas regras de integridade do banco de dados.");
		}

		return discipline;
	}

	/**
	 * Este método remove uma Disciplina do sistema
	 * @param id
	 * @return objeto da classe Discipline que foi removido
	 * @throws NoSuchDisciplineException
	 */
	public Discipline removeDiscipline(int id) throws NoSuchDisciplineException {

		Discipline d = getDiscipline(id);
		HibernateUtil.delete(d);
		return d;
	}

	public void updateDiscipline(int id, String name, int grade, String grade2,
			String area, String hoursPerWeek, String hoursPerYear)
			throws InvalidParameterException, NoSuchDisciplineException {
		Discipline d = null;
		if (Validator.isNumberValid(id)) {
			d = getDiscipline(id);
			validate(name, grade, grade2, area, hoursPerWeek, hoursPerYear);

		} else {
			throw new InvalidParameterException(Messages.INVALID_DISCIPLINE_ID);
		}

		if (d == null) {
			throw new NoSuchDisciplineException();
		}

		d.setId(id);
		d.setName(name);
		d.setGrade(grade);
		d.setGrade2(grade2);
		d.setArea(area);
		d.setHoursPerWeek(hoursPerWeek);
		d.setHoursPerYear(hoursPerYear);

		HibernateUtil.update(d);
	}

	@SuppressWarnings("unchecked")
	public List<Discipline> getAllDisciplinesByGradeAndGrade2(int grade,
			String grade2) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		List<Discipline> result = session.createSQLQuery(
				"select " + "{d.*} " + "from disciplinas {d} "
					+ "where {d}.serie="+ grade  +
					" and {d}.grau=\'"+ grade2 +
					"\' order by  {d}.serie, {d}.grau, {d}.NomeDisciplina;").addEntity("d", Discipline.class).list();
		session.getTransaction().commit();
		return result;
	}
	
		@SuppressWarnings("unchecked")
		public List<Discipline> getAllDisciplines() {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		List<Discipline> result = session.createSQLQuery(
				"select " + "{d.*} " + "from disciplinas {d} " +
					" order by  {d}.serie, {d}.grau, {d}.NomeDisciplina;").addEntity("d", Discipline.class).list();
		session.getTransaction().commit();
		return result;
	}
		
		
	public int adicionarHorarios(HorariosDisciplinas horario){
		
		return (Integer) HibernateUtil.create(horario);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<HorariosDisciplinas> getHorariosDisciplina(String id){
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		List<HorariosDisciplinas> result = session.createSQLQuery(
				"select " + "{h.*} " + "from evl_horarios_disciplcinas {h} "+ "where {h}.idDisciplina= " +id+ " order by  {h}.dia;").addEntity("h", HorariosDisciplinas.class).list();
		session.getTransaction().commit();
		return result;
	}
	
    public void RemoverHorarios(HorariosDisciplinas horario) throws SQLException{
        
    	System.out.println(horario.getId());
    	
    	Connection con = new ConnectionMysqlDirect().getConnection();
	    
	    String sql = "delete from evl_horarios_disciplcinas h where h.id = '"+horario.getId()+"'";
	    
	    PreparedStatement stmt = con.prepareStatement(sql);
	    
	    stmt.executeQuery();
		
		  
		
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
}
