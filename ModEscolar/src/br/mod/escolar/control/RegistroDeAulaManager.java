package br.mod.escolar.control;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.exception.ConstraintViolationException;

import br.mod.escolar.model.entities.RegistroDeAula;
import br.mod.escolar.model.exception.DBException;
import br.mod.escolar.model.util.HibernateUtil;

public class RegistroDeAulaManager {

	private static RegistroDeAulaManager manager = null;
	
	
	private RegistroDeAulaManager(){
		
	}
	
	
	/**
	 * Padrao Singleton
	 */
	public static synchronized RegistroDeAulaManager getInstance() {
		if (manager == null) {
			manager = new RegistroDeAulaManager();
		}
		return manager;
	}
	
	
	
	public int criarRegistroDeAula(RegistroDeAula reg){
		
		return (Integer) HibernateUtil.create(reg);
		
	}
	
	
	public void adicionarTurmas(RegistroDeAula reg){
		
		
		List<String> l = new  ArrayList<String>();
		
		String[] turmas = reg.getTurma().split("/");
		
		for (int i = 0; i < turmas.length; i++) {
			if(!l.contains(turmas[i])){
				l.add(turmas[i]);
			}
		}
		
		for (int i = 0; i < l.size(); i++) {
			reg.setTurma(l.get(i));
			criarRegistroDeAula(reg);
		}
		
		
		
		
	}
	
	@SuppressWarnings("unchecked")
	public List<RegistroDeAula> getRegistrosAulaDoProfessor(String idProfessor){
		
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		List<RegistroDeAula> result = session.createSQLQuery(
				"select " + "{n.*} " + "from registro_de_aula {n}  where  {n}.idProfessor = '"+idProfessor+"' order by  {n}.dataRegistro").addEntity("n", RegistroDeAula.class).list();
		session.getTransaction().commit();
		
		for (int i = 0; i < result.size(); i++) {
			result.get(i).setNumeroAula(String.valueOf(i));
			HibernateUtil.update(result.get(i));
		}
		
		
		
		
		return result.isEmpty() ? null : result;
		
		
		
	
	}
	
	
	@SuppressWarnings("unchecked")
	public Integer getRegistrosAula(String idDisciplina){
		
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		List<RegistroDeAula> result = session.createSQLQuery(
				"select " + "{n.*} " + "from registro_de_aula {n}  where  {n}.idDisciplina = '"+idDisciplina+"' order by  {n}.dataRegistro").addEntity("n", RegistroDeAula.class).list();
		session.getTransaction().commit();
		
		return  result.size();
		
		
		
	
	}
	
	@SuppressWarnings("unchecked")
	public List<RegistroDeAula> getRegistrosFiltro(String info, Object value,String id)
			throws DBException {
		Collection<SimpleExpression> exps = new ArrayList<SimpleExpression>();
		if (info.equals(info.equals("data"))) {
			exps.add(Restrictions.like(info, (String) value,MatchMode.ANYWHERE));
		} else if (info.equals("id")) {
			exps.add(Restrictions.like(info, Integer.parseInt((String) value)));
		} else {
			exps.add(Restrictions.like(info, value));
		}

		List<RegistroDeAula> registros = null;
		try {
			registros = (List<RegistroDeAula>) HibernateUtil.createQuery(RegistroDeAula.class,exps);
		} catch (ConstraintViolationException e) {
			throw new DBException(
					"A busca ocasionou uma violacao nas regras de integridade do banco de dados.");
		}
		
		for (int i = 0; i < registros.size(); i++) {
			if(registros.get(i).getId() != Integer.parseInt(id)){
				registros.remove(i);
			}
		}

		return registros;
	}
	
	
	public void updateRegistro(RegistroDeAula reg){
		HibernateUtil.update(reg);
	}
	
	public void deleteRegistro(RegistroDeAula reg){
		HibernateUtil.delete(reg);
	}

}
