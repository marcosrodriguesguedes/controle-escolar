package br.mod.escolar.relatorios.composers;

import java.util.ArrayList;
import java.util.List;


public interface ComposerIF <T, E>{
	public void addData(T object);
	public void addData(List<T> objects);
	public ArrayList<E> getBeansList();
	public Object[] getBeansAsArray();
	//public void createNewDataBean(T object);
	
}
