package br.mod.escolar.control;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.exception.ConstraintViolationException;

import br.mod.escolar.model.entities.TeacherClassID;
import br.mod.escolar.model.entities.TurmasConfig;
import br.mod.escolar.model.exception.NoSuchTeacherDisciplineException;
import br.mod.escolar.model.util.HibernateUtil;

public class TurmasManager {

	private static TurmasManager manager = null;

	private TurmasManager() {

	}

	public static synchronized TurmasManager getInstance() {

		if (manager == null) {

			manager = new TurmasManager();
		}
		return manager;

	}
	
	
	public int createTurma(TurmasConfig c){
		
		 return (Integer) HibernateUtil.create(c);
		
	}
	
	public void updateTurma(TurmasConfig c){
		
		HibernateUtil.update(c);
	}
	
    public void deketeTurma(TurmasConfig c){
		
		HibernateUtil.delete(c);
	}
    
    public String getCaractereTurma(String turma){
    	
    	
    	
    	String alfabeto = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
    	
    	String [] mapa = alfabeto.split(",");
    	
    	for (int i = 0; i < mapa.length-1; i++) {
			
    		if(mapa[i].equals(turma)){
    			
    			return mapa[i+1];
    		}
		}
		return mapa[0];
			
		
    		
		
    	
    }
	
	
    @SuppressWarnings("unchecked")
	public List<TurmasConfig> getTurma(String ano, String serie, String grau) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		List<TurmasConfig> result = session.createSQLQuery(
				"select " + "{n.*} " + "from turmas {n}  where  {n}.ano = '"+ano+"' and {n}.serie = '"+serie+"' and {n}.grau = '"+grau+"'").addEntity("n", TurmasConfig.class).list();
		session.getTransaction().commit();
		
		return result.isEmpty() ? null : result;
	}
    
    @SuppressWarnings("unchecked")
	public TeacherClassID getTeacherDisciplineTurma(String turma , int idTeacher, int idDiscipline)
			throws NoSuchTeacherDisciplineException {
		Collection<SimpleExpression> exps = new ArrayList<SimpleExpression>();
		exps.add(Restrictions.like("idTeacher", idTeacher));
		exps.add(Restrictions.like("idDiscipline", idDiscipline));
		exps.add(Restrictions.like("classeID", turma));
		List<TeacherClassID> teacherDiscipline = null;
		try {
			teacherDiscipline = (List<TeacherClassID>) HibernateUtil
					.createQuery(TeacherClassID.class, exps);
		} catch (ConstraintViolationException e) {
			throw new NoSuchTeacherDisciplineException();
			
		}

		if (teacherDiscipline.isEmpty())
			return null;

		return teacherDiscipline.get(0);
	}
    
    
    public int createTurmaTeacher(TeacherClassID tt){
    	
    	return (Integer) HibernateUtil.create(tt);
    }
   
    public void removeTurmaTeacher(TeacherClassID tt){
    	
    	HibernateUtil.delete(tt);
    	
    }
    
    public void updateTurmaTeacher(TeacherClassID tt){
    	
    	HibernateUtil.update(tt);
    	
    }

}
