package br.mod.escolar.servlets;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.mod.escolar.control.AvaliacaoConceitualManager;
import br.mod.escolar.control.FacadeAuth;
import br.mod.escolar.control.TurmasManager;
import br.mod.escolar.model.entities.Discipline;
import br.mod.escolar.model.entities.ParentalInfo;
import br.mod.escolar.model.entities.Student;
import br.mod.escolar.model.entities.TurmasConfig;
import br.mod.escolar.model.entities.User;
import br.mod.escolar.model.exception.DBException;
import br.mod.escolar.model.exception.InvalidParameterException;
import br.mod.escolar.model.exception.InvalidPasswordException;
import br.mod.escolar.model.exception.InvalidStateUser;
import br.mod.escolar.model.exception.NoSuchPermissionException;
import br.mod.escolar.model.exception.NoSuchSessionException;
import br.mod.escolar.model.exception.NoSuchUserException;
import br.mod.escolar.model.exception.NoSuchUserTypeException;
import br.mod.escolar.model.util.UserTypeDefinition;

public class TestUnitarios {

	static DownloadProperties properties = DownloadProperties.getInstance();
	static FacadeAuth f = FacadeAuth.getInstance();
	
	static AvaliacaoConceitualManager a = AvaliacaoConceitualManager.getInstance();

	public static void main(String[] args)  {
		
		
		
		
		for (int i = 0; i < a.getAvaliacao().getCompetencias().size(); i++) {
			
			System.out.println(a.getAvaliacao().getCompetencias().get(i));
			
			for (int j = 0; j < a.getAvaliacao().getHabilidades().size(); j++) {
				
				if(a.getAvaliacao().getHabilidades().get(j).getId() == a.getAvaliacao().getCompetencias().get(i).getId()){
					
					System.out.println(a.getAvaliacao().getHabilidades().get(j));
				}
				
				
				
			}
			

			
		}
		
		
		
	}
	

	
	
	public static void loadDisciplinas() throws NoSuchSessionException, NoSuchPermissionException, NoSuchUserTypeException, NoSuchUserException, InvalidPasswordException, DBException, InvalidStateUser{
		
		String sessao = f.login("marcosrg33","123");
		int serie = 0;
		
		
        List<String> areaCHT = new ArrayList<String>();
		
		areaCHT.add("FILOSOFIA");
		areaCHT.add("HISTORIA");
		areaCHT.add("SOCIOLOGIA");
		areaCHT.add("GEOGRAFIA");
		
		
		
		areaCHT.add("PORTUGUES");
		areaCHT.add("LITERATURA");
		areaCHT.add("ESPANHOL");
		areaCHT.add("INGLES");
		areaCHT.add("ARTE");
		areaCHT.add("EDUCACAO FISICA");
		
		
		
		areaCHT.add("BIOLOGIA");
		areaCHT.add("FISICA");
		areaCHT.add("MATEMATICA");
		areaCHT.add("QUIMICA");
		
		int index = 0;
		
		for (int i = 0; i < areaCHT.size()*3 ; i++) {
			
			if(i % 14 == 0){
			   serie++;
			   index =  0;
		   }
		
		  Discipline disciplina;
		
          disciplina = new Discipline();
		  
		  disciplina.setArea("TESTE");
		  
		  disciplina.setHoursPerWeek("0");
		  
		  disciplina.setHoursPerYear("0");
		  
		  disciplina.setName(areaCHT.get(index));
		  
		  disciplina.setCodDiscipline(areaCHT.get(index));
		  
		  disciplina.setGrade2("EM");
		  
		  disciplina.setGrade(serie);
		  index++;
		  
		  f.newDiscipline(sessao, disciplina,null);
		}
	}
	
	
	
	public static void loadStudents() throws NoSuchUserException, InvalidPasswordException, DBException, NoSuchSessionException, NoSuchUserTypeException, NoSuchPermissionException, InvalidParameterException, InvalidStateUser{
		
		
	
		TurmasManager turmasManaget = TurmasManager.getInstance();
		 
			String sessao = f.login("marcosrg33","123");
						
			// Carga de Alunos:
			
			int serie = 0 ;
			
			for (int i = 0; i < 120 ; i++) {
				
				if(i % 40 == 0){
					
					serie++;
				}
				
				 Student student = new Student();
				 
				 student.setName("ALUNOS_TESTE_"+i);
				 student.setGrade(String.valueOf(serie));
				 student.setGrade2("EM");
				 student.setBirthday(new Date());
				 student.setRg(student.getName());
				 student.setActive("1");
				 student.setPictureUrl("Logomarca+CAESI+large+size.jpg");
				 
				
			
		        	
	        	 SimpleDateFormat formatAno = new SimpleDateFormat("yyyy");
	        	
	        	List<TurmasConfig> turmaCadastrada = turmasManaget.getTurma(formatAno.format(new Date()),student.getGrade(), student.getGrade2()); 
	        	
	        	if(null == turmaCadastrada){
	        		
	        		TurmasConfig turma = new TurmasConfig();
	        		turma.setAno(formatAno.format(new Date()));
	        		turma.setGrau(student.getGrade2());
	        		turma.setSerie(student.getGrade());
	        		turma.setNumeroAlunoAtual(1);
	        		turma.setNumeroMaxAlunosTurma(20);
	        		turma.setTurma("A");
	        		turmasManaget.createTurma(turma);
	        		student.setClassId(turma.getTurma());
	        		
	        		
	        	}else{
	        		
	        		boolean adicionadoATurma = false;
	        		
	        		String ultimaTurma = "";
	        		
	        		for (int j = 0; j < turmaCadastrada.size(); j++) {
						
	        			if(turmaCadastrada.get(j).getNumeroAlunoAtual() < turmaCadastrada.get(j).getNumeroMaxAlunosTurma()){
	        				
	        				student.setClassId(turmaCadastrada.get(j).getTurma());
	        				
	        				turmaCadastrada.get(j).setNumeroAlunoAtual(turmaCadastrada.get(j).getNumeroAlunoAtual()+1);
	        				
	        				adicionadoATurma = true;
	        				
	        				turmasManaget.updateTurma(turmaCadastrada.get(j));
	        				
	        				break;
	        			
	        			}
	        			
	        		 ultimaTurma = turmaCadastrada.get(j).getTurma();
					
	        		}
	        		
	        		if(!adicionadoATurma){
	        			
	        			TurmasConfig turma = new TurmasConfig();
	            		turma.setAno(formatAno.format(new Date()));
	            		turma.setGrau(student.getGrade2());
	            		turma.setSerie(student.getGrade());
	            		turma.setNumeroAlunoAtual(1);
	            		turma.setNumeroMaxAlunosTurma(20);
	            		turma.setTurma(turmasManaget.getCaractereTurma(ultimaTurma));
	            		turmasManaget.createTurma(turma);
	            		student.setClassId(turma.getTurma());
	            		
	        			
	        		}
	        		
	        	}
	        	
	        	
	            int id = f.newStudent(sessao, student,new ParentalInfo(),null);
	            
	            SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
	           
	            User u = new User();
	    		
	            u.setLogin(student.getRg());
	    		u.setPassword(format.format(student.getBirthday()));
	    		u.setIdType(Integer.parseInt(UserTypeDefinition.STUDENT));
	    		u.setActive(new Integer(1));
	    		
	    		
	    		u.setIdPerson(id);
	    		
	    		f.createUser(sessao, u);
			 
			 
			}
			 
			
	}

}
