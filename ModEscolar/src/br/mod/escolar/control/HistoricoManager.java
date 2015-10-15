package br.mod.escolar.control;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.mod.escolar.model.entities.Historico;
import br.mod.escolar.model.entities.HistoricoData;
import br.mod.escolar.model.entities.HistoricoRelatorio;
import br.mod.escolar.model.util.CompareDisciplinas;

public class HistoricoManager {

	
	private static HistoricoManager manager = null;
	
	private NotasManager notaManager;
	
	private FrequenciaManager frequenciaManager;
	
	private RegistroDeAulaManager registroManager;
	
	private static Integer NUMERO_DE_DISCIPLINAS = 21;
	
	private HistoricoManager(){
		
		notaManager = NotasManager.getInstance();
		frequenciaManager = FrequenciaManager.getInstance();
		registroManager = RegistroDeAulaManager.getInstance();
		
	}
	
	public static synchronized HistoricoManager getInstance(){
		if(manager == null){
			
			manager = new HistoricoManager();
		}
		
		return manager;
	}
	
	
	public List<HistoricoData> getHistorico(int idStudent){
		
		HistoricoData histData = null;
		
		List<HistoricoData> listHist = new ArrayList<HistoricoData>();
		
		List<Historico> notasStudent = notaManager.getHistorico(String.valueOf(idStudent));
		
	    if(null != notasStudent){
	    	
	    	String anoLetivo = "";
	    	
	    	int index = -1;
	    			
			
			for (int i = 0; i < notasStudent.size(); i++) {
				   
			   histData = new HistoricoData();
				
			   histData.setAno(notasStudent.get(i).getAnoLetivo());
			   histData.setGrau(notasStudent.get(i).getGrau());
			   histData.setSerie(notasStudent.get(i).getSerie());
			   histData.setStatus(notasStudent.get(i).getStatus());
			   histData.setIdStudent(notasStudent.get(i).getId_aluno());
			  
			   if(!anoLetivo.equals(notasStudent.get(i).getAnoLetivo())){
				    
				   listHist.add(histData);
				   index ++;
				   anoLetivo = notasStudent.get(i).getAnoLetivo();
			   
			   }else{
				   
				   if(notasStudent.get(i).getStatus().equals("REPROVADO")){
					   
					   listHist.get(index).setStatus("REPROVADO");
					   
				   }
			   }
			  
			  
			
			}
			
			
			
		}
		
     	return listHist;
		
	}
	
	
	public List<HistoricoRelatorio> generateHistorico(int idStudent,String anoLetivo){
		
		
		List<Historico> notasStudent = notaManager.getNotasAllHistorico(String.valueOf(idStudent),anoLetivo);
		if(null != notasStudent){
			
			Collections.sort(notasStudent, new CompareDisciplinas());
			
			List<HistoricoRelatorio> historico = new ArrayList<HistoricoRelatorio>();
			
			HistoricoRelatorio hist = null;
			
			for (int i = 0; i < NUMERO_DE_DISCIPLINAS; i++) {
				
				hist = new HistoricoRelatorio();
                
				hist.setDisciplina("");
				
				hist.setFrequencia("");
				
				hist.setHorasAula("");
				
				hist.setFaltas("");
				
				hist.setMediaPacial("");
				
				hist.setMb1("");
				
				hist.setMb2("");
				
				hist.setMb3("");
				
				hist.setMb4("");
				
				hist.setProvaFinal("");
				
				hist.setStatus("");
				
				hist.setMediaFinal("");
				
				hist.setCod_disciplina(null);
				
				historico.add(hist);
			}
			
			for (int i = 0; i < notasStudent.size(); i++) {
				
				hist = new HistoricoRelatorio();
				
				double frequencia = 0;
				
				double frequenciaPercentagem = 100;
				
				Integer numeroDeAulas = registroManager.getRegistrosAula(String.valueOf(notasStudent.get(i).getId_disciplina()));
				
				Integer faltas = frequenciaManager.getFrequenciaStudentHistorico(String.valueOf(idStudent), String.valueOf(notasStudent.get(i).getId_disciplina()));
				
				if(numeroDeAulas != 0){
					
					frequencia = numeroDeAulas - faltas;
					
					frequenciaPercentagem = (frequencia/numeroDeAulas)*100;
					
				}
					
				
				hist.setDisciplina(notasStudent.get(i).getNomeDisciplina());
				
				hist.setFrequencia(String.valueOf(frequenciaPercentagem));
				
				hist.setHorasAula(String.valueOf(numeroDeAulas));
				
				hist.setFaltas(String.valueOf(faltas));
				
				hist.setMediaPacial(aproximacao(notasStudent.get(i).getMp()).toString());
				
				hist.setMb1(aproximacao(notasStudent.get(i).getMb_b1()).toString());
				
				hist.setMb2(aproximacao(notasStudent.get(i).getMb_b2()).toString());
				
				hist.setMb3(aproximacao(notasStudent.get(i).getMb_b3()).toString());
				
				hist.setMb4(aproximacao(notasStudent.get(i).getMb_b4()).toString());
				
				if(null!= notasStudent.get(i).getNotaFinal()){
					
					hist.setProvaFinal(aproximacao(notasStudent.get(i).getNotaFinal()).toString());
				}
				
				hist.setStatus(notasStudent.get(i).getStatus());
				
				hist.setMediaFinal(aproximacao(notasStudent.get(i).getMf()).toString());
				
				hist.setCod_disciplina(notasStudent.get(i).getCod_disciplina());
				
				historico.add(hist.getCod_disciplina(), hist);
				
				
				
				
				
				
			}
			
			return historico;
		}else{
			
			return null; 
		}
		
		
	}
	
	private BigDecimal aproximacao(BigDecimal valor){
        int decimalPlace = 1; 
        
        valor  = valor.setScale(decimalPlace,BigDecimal.ROUND_HALF_UP);  
		
		return valor;
		
	}
	
	
	
	
}
