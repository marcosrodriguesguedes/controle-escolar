package br.mod.escolar.control;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import br.mod.escolar.jdbc.ConnectionMysqlDirect;
import br.mod.escolar.model.entities.Equipe;
import br.mod.escolar.model.entities.EquipeStudents;
import br.mod.escolar.model.entities.Student;
import br.mod.escolar.model.exception.NoSuchStudentException;
import br.mod.escolar.model.util.EquipeReport;
import br.mod.escolar.model.util.HibernateUtil;
import br.mod.escolar.relatorios.beans.StudentEquipeReportDataBean;

public class EquipeManager {

	private static EquipeManager manager = null;

	private StudentManager sm;

	private EquipeManager() {

		sm = StudentManager.getInstance();
	}

	public static synchronized EquipeManager getInstance() {
		if (manager == null) {
			manager = new EquipeManager();
		}
		return manager;
	}

	public int createEquipe(Equipe e) {

		return (Integer) HibernateUtil.create(e);
	}

	public int createEquipeStudent(EquipeStudents es, Integer idEquipe) {

		es.setIdEquipe(idEquipe);

		return (Integer) HibernateUtil.create(es);
	}

	public void upadateEquipe(Equipe e) {
		HibernateUtil.update(e);

	}

	public void deleteEquipe(Equipe e) {
		HibernateUtil.delete(e);

	}

	@SuppressWarnings("unchecked")
	public List<Equipe> getEquipesStudents(String id_student) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		List<Equipe> result = session
				.createSQLQuery(
						"select "
								+ "{e.*} "
								+ "from equipe {e}, equipe_aluno {ea}  where {e}.id = {ea}.idEquipe and  {ea}.id_student = '"
								+ id_student + "'" + ";")

				.addEntity("e", Equipe.class).list();
		session.getTransaction().commit();
		return result.isEmpty() ? null : result;
	}

	@SuppressWarnings("unchecked")
	public List<Equipe> getEquipesTeacher(String id_Teachet) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		List<Equipe> result = session
				.createSQLQuery(
						"select " + "{f.*} "
								+ "from equipe {f}  where {f}.id_teacher = '"
								+ id_Teachet + "'"
								+ " order by  {f}.nomeDisciplina;")
				.addEntity("f", Equipe.class).list();
		session.getTransaction().commit();

		for (int i = 0; i < result.size(); i++) {
			if (null != result.get(i).getNota()) {

				BigDecimal v = aproximacao(result.get(i).getNota());

				result.get(i).setNota(v);

			}

		}

		return result.isEmpty() ? null : result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Equipe> getEquipes(String serie,String grau, String turma) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		List<Equipe> result = session
				.createSQLQuery(
						"select " + "{f.*} "
								+ "from equipe {f}  where {f}.serie = '"
								+ serie + "'"
								+ " and {f}.grau = '"+grau+"' and {f}.turma = '"+turma+"';")
				.addEntity("f", Equipe.class).list();
		session.getTransaction().commit();

		for (int i = 0; i < result.size(); i++) {
			if (null != result.get(i).getNota()) {

				BigDecimal v = aproximacao(result.get(i).getNota());

				result.get(i).setNota(v);

			}

		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Student> getStudentsE(String id_Equipe)
			throws NoSuchStudentException {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();

		List<EquipeStudents> result = session
				.createSQLQuery(
						"select "
								+ "{f.*} "
								+ "from equipe_aluno {f}  where {f}.idEquipe = '"
								+ id_Equipe + "'" + ";")
				.addEntity("f", EquipeStudents.class).list();

		session.getTransaction().commit();

		List<Student> students = new ArrayList<Student>();
		
		if(!result.isEmpty()){
			
			for (int i = 0; i < result.size(); i++) {

				students.add(sm.getStudent(result.get(i).getId_student()));

			}
		}

		

		return students.isEmpty() ? null : students;
	}

	private BigDecimal aproximacao(BigDecimal valor) {
		int decimalPlace = 1;

		valor = valor.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);

		return valor;

	}
	
	
	
	
	public List<EquipeReport> getRelatorioEquipes(String serie, String grau, String turma) throws NoSuchStudentException{
		
		List<EquipeReport> reports = new ArrayList<EquipeReport>();
		
		List<Equipe>  lista = getEquipes(serie, grau, turma);
		
		for (int i = 0; i < lista.size(); i++) {
			
			Equipe e = lista.get(i);
			
			EquipeReport er = new EquipeReport();
			
			er.setDescricao(e.getDescricao());
			
			er.setBimestre(e.getBimestre());
			
			List<Student> alunos = getStudentsE(String.valueOf(e.getId()));
			
			if(null != alunos){
				
				for (int j = 0; j < alunos.size(); j++) {
					
					Student st = alunos.get(j);
					
					StudentEquipeReportDataBean sb = new StudentEquipeReportDataBean();
					
					sb.setNomeAluno(st.getName());
					
					sb.setNota(e.getNota().toString());
					
					er.getAlunos().add(sb);
					
					
				}
			}
			
			
			reports.add(er);
			
		}
		
		
		return reports;
		
		
		
	}

	public List<Equipe> getEquipesStudent(String id) throws SQLException,
			ParseException {

		Connection con = new ConnectionMysqlDirect().getConnection();

		String sql = "select e.* from equipe e, equipe_aluno ea where e.id = ea.idEquipe and id_student = '"
				+ id + "' order by e.nomeDisciplina";

		PreparedStatement stmt = con.prepareStatement(sql);

		java.sql.ResultSet rs = stmt.executeQuery();

		rs.last();

		rs.beforeFirst();

		ArrayList<Equipe> lista = new ArrayList<Equipe>();

		while (rs.next()) {

			Equipe f = new Equipe();
			f.setId(rs.getInt(1));
			f.setBimestre(rs.getString(2));
			f.setDescricao(rs.getString(3));
			f.setId_disciplina(rs.getInt(4));
			f.setId_teacher(rs.getInt(5));
			f.setTurma(rs.getString(5));
			f.setNota(aproximacao(rs.getBigDecimal(6)));
			f.setGrau(rs.getString(7));
			f.setNomeDisciplina(rs.getString(8));
			f.setSerie(rs.getString(9));
			f.setTurma(rs.getString(10));
			lista.add(f);

		}

		return lista;

	}

	
}
