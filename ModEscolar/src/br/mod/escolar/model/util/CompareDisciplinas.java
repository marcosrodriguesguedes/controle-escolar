package br.mod.escolar.model.util;

import java.util.Comparator;

import br.mod.escolar.model.entities.Historico;



public class CompareDisciplinas implements Comparator<Historico> {

	@Override
	public int compare(Historico o1, Historico o2) {
		
		return o1.getCod_disciplina() - o2.getCod_disciplina();
	}

}
