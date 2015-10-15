package br.mod.escolar.control.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

@ManagedBean
@ViewScoped
public class ControllerTurmasBean {
	
    private List<String> turmas;
	
	private List<String> turmasSeleciondas;
	
	private String turma;

	
	
	public ControllerTurmasBean(){
		
		turmas = new ArrayList<String>();
	
		
		turmasSeleciondas = new ArrayList<String>();
	
		turmas.add("A");
		turmas.add("B");
		turmas.add("C");
		turmas.add("D");
		turmas.add("E");
		turmas.add("F");
		turmas.add("G");
		turmas.add("H");
		turmas.add("I");
		turmas.add("J");
		turmas.add("K");
		turmas.add("L");
		turmas.add("M");
		turmas.add("N");
		turmas.add("O");
		turmas.add("P");
		turmas.add("Q");
		turmas.add("S");
		turmas.add("W");
		turmas.add("X");
		turmas.add("Y");
		turmas.add("Z");
	}
	
	public void inserirTurma(){
	     
    	
    	
    	
     
     
    }
	
	public void selectionChangedBimestre(final AjaxBehaviorEvent event){
        if(!turmasSeleciondas.contains(turma) && !turma.equals("")){
        	turmasSeleciondas.add(turma);
        }
		
    	
	    
	}
	
    public void removerTurma(){
    	
    	
    }



	public List<String> getTurmas() {
		return turmas;
	}



	public void setTurmas(List<String> turmas) {
		this.turmas = turmas;
	}



	public List<String> getTurmasSeleciondas() {
		return turmasSeleciondas;
	}



	public void setTurmasSeleciondas(List<String> turmasSeleciondas) {
		this.turmasSeleciondas = turmasSeleciondas;
	}



	public String getTurma() {
		return turma;
	}



	public void setTurma(String turma) {
		this.turma = turma;
	}
}
