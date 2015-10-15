package br.mod.escolar.model.entities;

import java.util.ArrayList;
import java.util.List;

public class AvaliacaoConceitual {
	
	
	private List<Competencia> competencias;
	
	private List<Habilidade> habilidades;
	
	private List<Conceito> conceitos;
	
	
	public AvaliacaoConceitual(){
		
		 competencias = new ArrayList<Competencia>();
		 habilidades = new ArrayList<Habilidade>();
		 conceitos = new ArrayList<Conceito>();
	}
	
	public List<Competencia> getCompetencias() {
		return competencias;
	}

	public void setCompetencias(List<Competencia> competencias) {
		this.competencias = competencias;
	}

	public List<Habilidade> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(List<Habilidade> habilidades) {
		this.habilidades = habilidades;
	}

	public List<Conceito> getConceitos() {
		return conceitos;
	}

	public void setConceitos(List<Conceito> conceitos) {
		this.conceitos = conceitos;
	}
	
	
}
