package br.mod.escolar.control;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

import org.hibernate.Session;

import br.mod.escolar.model.entities.Equipe;
import br.mod.escolar.model.entities.Historico;
import br.mod.escolar.model.entities.Notas;
import br.mod.escolar.model.util.HibernateUtil;

public class NotasManager {
	

	private static NotasManager manager = null;
	
	public static String PORCENTAGEM_MEDIA = "0.6";
	
	public static String PORCENTAGEM_FINAL = "0.4";
	/**
	 * construtor default
	 */
	
	private NotasManager(){
		
	}
	
	
	/**
	 * Padrão Singleton
	 */
	public static synchronized NotasManager getInstance() {
		if (manager == null) {
			manager = new NotasManager();
		}
		return manager;
	}
	
	
	
	public int criarNotasBimestre(Notas notas) {
		
		return (Integer) HibernateUtil.create(notas);
		
	}
	

	
	
	@SuppressWarnings("unchecked")
	public Notas getNotasAll2(String idDisciplina, String idAluno) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		List<Notas> result = session.createSQLQuery(
				"select " + "{n.*} " + "from notas {n}  where  {n}.id_aluno = '"+idAluno+"' and {n}.id_disciplina = '"+idDisciplina+"'").addEntity("n", Notas.class).list();
		session.getTransaction().commit();
		
		return result.isEmpty() ? null : result.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<Notas> getNotasAll(String idAluno) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		List<Notas> result = session.createSQLQuery(
				"select " + "{n.*} " + "from notas {n}  where  {n}.id_aluno = '"+idAluno+"'").addEntity("n", Notas.class).list();
		session.getTransaction().commit();
		
		return result.isEmpty() ? null : result;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Notas> getNotasAllStudentByGrade(String grau, String serie, String disciplina,String turma) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		List<Notas> result = session.createSQLQuery(
				"select n.*  from notas n, alunos a "
				+ " where a.id = n.id_aluno and n.grau = '"+grau+"' and  n.serie = '"+serie+"'" 
				+ " and  n.id_disciplina = '"+disciplina+"' and a.turma = '"+turma+"'").addEntity("n", Notas.class).list();
		session.getTransaction().commit();
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Notas> getNotasAllStudentByGrade2(String grau, String serie) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		List<Notas> result = session.createSQLQuery(
				"select n.*  from notas n, alunos a "
				+ " where a.id = n.id_aluno and n.grau = '"+grau+"' and  n.serie = '"+serie+"'").addEntity("n", Notas.class).list();
		session.getTransaction().commit();
		
		return result;
	}

	
	
	@SuppressWarnings("unchecked")
	public List<Historico> getNotasAllHistorico(String idAluno,String anoLetivo) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		List<Historico> result = session.createSQLQuery(
				"select " + "{n.*} " + "from historico_escolar {n}  where {n}.anoletivo = '"+anoLetivo+"' and {n}.id_aluno = '"+idAluno+"'").addEntity("n", Historico.class).list();
		session.getTransaction().commit();
		
		return result.isEmpty() ? null : result;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Historico> getHistorico(String idAluno) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		List<Historico> result = session.createSQLQuery(
				"select " + "{n.*} " + "from historico_escolar {n}  where {n}.id_aluno = '"+idAluno+"' order by anoLetivo").addEntity("n", Historico.class).list();
		session.getTransaction().commit();
		
		return result.isEmpty() ? null : result;
	}
	
	
	public BigDecimal calcularApEm(BigDecimal ap1, BigDecimal ap2, BigDecimal ap3,String bimestre, Notas notas) {
		
		
		
		if(ap1 != null  && ap2!= null && ap3 != null){
			
			
			BigDecimal media = aproximacao(((ap1.add(ap2)).add(ap3)).divide(new BigDecimal("3"),new MathContext(2,RoundingMode.HALF_EVEN)));
			
			   switch (bimestre) {
			   
			   
			   	case "1":
			   		
			   		notas.setMb_b1(media);
			   		
			   		break;
				
			   	case "2":
			   		
			   		notas.setMb_b2(media);
			   		
			   		break;
			   		
			   	case "3":
			   		
			   		notas.setMb_b3(media);
			   		
			   		break;
			   	case "4":
			   		
			   		notas.setMb_b4(media);
			   		
			   		break;
                    
			   	default:
			   		break;
			   }
			   
			   return media;
		
		}else if( ap1 != null && ap2 != null && ap3 == null){
			
			   return aproximacao((ap1.add(ap2)).divide(new BigDecimal("3"),new MathContext(2,RoundingMode.HALF_EVEN)));
		     
		}else if(ap1 != null && ap2 == null && ap3 == null){
				 
			   return aproximacao(ap1.divide(new BigDecimal("3"),new MathContext(2,RoundingMode.HALF_EVEN)));
			   
		}else  if(ap1 == null && ap2 == null && ap3 != null){
			
			return aproximacao(ap3.divide(new BigDecimal("3"), new MathContext(2,RoundingMode.HALF_EVEN)));
		
		}else if(ap1 == null && ap2 != null && ap3 == null){
			
			return aproximacao(ap2.divide(new BigDecimal("3"),new MathContext(2,RoundingMode.HALF_EVEN)));
		}
				 
			 
		return new BigDecimal(0);
	}
	
	
	public BigDecimal calculaMediaAnual(BigDecimal ap1, BigDecimal ap2, BigDecimal ap3, BigDecimal ap4){
		
		return   (((ap1.add(ap2)).add(ap3)).add(ap4)).divide(
				new BigDecimal("4"), new MathContext(2,RoundingMode.HALF_EVEN));
	}
	
	public BigDecimal CalculaMediaFinal(BigDecimal ap1, BigDecimal ap2, BigDecimal ap3, BigDecimal ap4, BigDecimal exameFinal){
		
		BigDecimal md = aproximacao((((ap1.add(ap2)).add(ap3)).add(ap4)).divide(new BigDecimal("4"),new MathContext(2,RoundingMode.HALF_EVEN)));
	
		
		if (md.compareTo(new BigDecimal("7")) == -1 && md.compareTo(new BigDecimal("4.9")) == 1 && exameFinal.compareTo(new BigDecimal("-1")) == 1 ) {

			BigDecimal porcentagemMedia = new BigDecimal(PORCENTAGEM_MEDIA);

			BigDecimal porcentagemFinal = new BigDecimal(PORCENTAGEM_MEDIA);

			BigDecimal media = (((ap1.add(ap2)).add(ap3)).add(ap4)).divide(
					new BigDecimal("4"), new MathContext(2,RoundingMode.HALF_EVEN));

			BigDecimal mediaParcialPercentagem = media
					.multiply(porcentagemMedia);

			BigDecimal finalParcialPercentagem = exameFinal
					.multiply(porcentagemFinal);

			return aproximacao(mediaParcialPercentagem
					.add(finalParcialPercentagem));

		} else {

			return md;
		}

	}
		
	
	
	
	private BigDecimal aproximacao(BigDecimal valor){
        int decimalPlace = 1; 
        
        valor  = valor.setScale(decimalPlace,BigDecimal.ROUND_HALF_UP);  
		
		return valor;
		
	}
	
	


	public void updateNotas(Notas nota){
		 HibernateUtil.update(nota);
	}
	
	
	@SuppressWarnings("unchecked")
	public Equipe getEquipeStudent(String id_student, String serie, String grau, String bimestre) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		List<Equipe> result = session.createSQLQuery(
				"select " + "{e.*} " + "from equipe {e}, equipe_aluno {ea}  where {e}.id = {ea}.idEquipe and  {ea}.id_student = '"+id_student+"'" + " and {e}.grau = '"+grau+"'" + "{e}.serie = '"+serie+"'" + " {e}.bimestre = '"+bimestre+"'" + " and {e}.nota is not null  order by  {f}.nomeDisciplina;").addEntity("f", Equipe.class).list();
		session.getTransaction().commit();
		
		return result.isEmpty() ? null : result.get(0);
	}
	
	
	
	
	

	
	
	
	
	

}