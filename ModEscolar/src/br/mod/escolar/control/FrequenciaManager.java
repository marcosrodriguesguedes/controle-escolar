package br.mod.escolar.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import br.mod.escolar.jdbc.ConnectionMysqlDirect;
import br.mod.escolar.model.entities.Frequencia;
import br.mod.escolar.model.entities.FrequenciaHistorico;
import br.mod.escolar.model.util.HibernateUtil;


public class FrequenciaManager {
	
	private static FrequenciaManager manager = null;
	
	private static int UPDATE = 1;
	
	private FrequenciaManager() {

	}
	
	public static synchronized FrequenciaManager getInstance() {
		if (manager == null) {
			manager = new FrequenciaManager();
		}
		return manager;
	}
	
	
	public int createFrequencia(Frequencia f){
		
		return (Integer) HibernateUtil.create(f);
		
	}
	
	public void newFrequencia(ArrayList<Frequencia> frequencia){
		for (int i = 0; i < frequencia.size(); i++) {
	
			createFrequencia(frequencia.get(i));
		}
	}
	
	public void updateFrequencia(Frequencia f){
		HibernateUtil.update(f);
		
		
	}
	
	public void removeFrequencia(Frequencia f){
		HibernateUtil.delete(f);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Frequencia> getFrequenciaPorData(String data,String turma,String id_disciplina) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		List<Frequencia> result = session.createSQLQuery(
				"select " + "{f.*} " + "from frequencia {f}  where {f}.data = '"+data+"'" + " and {f}.turma = '"+turma+"' and {f}.iddisciplina = '"+id_disciplina+"' order by  {f}.nomeDisciplina;").addEntity("f", Frequencia.class).list();
		session.getTransaction().commit();
		
		return result.isEmpty() ? null : result;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Frequencia> getFrequenciaStudent(String id_student,String grau, String serie,String id_dics) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		List<Frequencia> result = session.createSQLQuery(
				"select " + "{f.*} " + "from frequencia {f}  where {f}.idAluno = '"+id_student+"'" + " and {f}.grau = '"+grau+"'" + " and {f}.serie = '"+serie+"' and {f}.idDisciplina = '"+id_dics+"'  order by  {f}.data;").addEntity("f", Frequencia.class).list();
		session.getTransaction().commit();
		
		return result.isEmpty() ? null : result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Frequencia> getFrequenciaStudentAllFaltas(String id_student) {
		Session session = HibernateUtil.getSession();
		String falta = "F";
		session.beginTransaction();
		List<Frequencia> result = session.createSQLQuery(
				"select " + "{f.*} " + "from frequencia {f}  where {f}.idAluno = '"+id_student+"' and {f}.frequencia = '"+falta+"'  order by  {f}.data;").addEntity("f", Frequencia.class).list();
		session.getTransaction().commit();
		
		return result.isEmpty() ? null : result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Frequencia> getFrequenciaStudentAll(String id_student) {
		Session session = HibernateUtil.getSession();
		
		session.beginTransaction();
		List<Frequencia> result = session.createSQLQuery(
				"select " + "{f.*} " + "from frequencia {f}  where {f}.idAluno = '"+id_student+"' order by  {f}.data;").addEntity("f", Frequencia.class).list();
		session.getTransaction().commit();
		
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	public Integer getFrequenciaStudentHistorico(String id_student, String id_disciplina) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		List<FrequenciaHistorico> result = session.createSQLQuery(
				"select " + "{f.*} " + "from frequencia_historico {f}  where {f}.idDisciplina = '"+id_disciplina+"' and {f}.idAluno = '"+id_student+"'  order by  {f}.data;").addEntity("f", FrequenciaHistorico.class).list();
		session.getTransaction().commit();
		
		return result.size();
	}
	
	
	
	public List<Frequencia> loadFrequenciaAluno(String grau,String serie,String data,String turma,String id_disciplina) throws SQLException, ParseException{
		
		List<Frequencia> retorno = getFrequenciaPorData(data,turma,id_disciplina);
		
		if(retorno != null){
			
			for (int i = 0; i < retorno.size(); i++) {
				retorno.get(i).setAtualizar(UPDATE);
				
			}
			
			return retorno;
		
		}else{
			
			return DisciplinasFrequencia(grau, serie,turma);
		}
		
		
		
		
	}
	
	
	
	
	
	public List<Frequencia>	DisciplinasFrequencia(String grau,String serie,String turma) throws SQLException, ParseException{
    
    	
    	Connection con = new ConnectionMysqlDirect().getConnection();
	    
	    String sql = "select a.id, a.nome, a.serie, a.grau, a.turma from alunos a where a.serie = '"+serie+"' and a.grau = '"+grau+"' and a.turma = '"+turma+"' order by a.nome";
	    
	    PreparedStatement stmt = con.prepareStatement(sql);
	    
	    java.sql.ResultSet rs = stmt.executeQuery();
	    
	    rs.last();
	    
	    rs.beforeFirst();

	   ArrayList<Frequencia> listaFrequencia = new ArrayList<Frequencia>();
	    
	   while(rs.next()){
	    	
		    Frequencia f = new Frequencia();
	    	f.setIdAluno(rs.getString(1));
	    	f.setNomeDisciplina(rs.getString(2));
	    	f.setFrequencia("P");
	    	f.setSerie(rs.getString(3));
	    	f.setGrau(rs.getString(4));
	    	f.setTurma(rs.getString(5));
	    	f.setAtualizar(0);
	    	listaFrequencia.add(f);
	    	
	  }
    
		return listaFrequencia;
		 
        
       
    	
    }
	

}
