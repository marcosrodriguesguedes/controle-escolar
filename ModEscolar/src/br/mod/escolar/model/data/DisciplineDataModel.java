package br.mod.escolar.model.data;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.mod.escolar.model.entities.Discipline;


public class DisciplineDataModel extends ListDataModel<Discipline> implements SelectableDataModel<Discipline>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DisciplineDataModel(){
		
	}
	
	 public DisciplineDataModel(List<Discipline> data) {  
	        super(data);  
	    }  

	@Override
	public Discipline getRowData(String rowKey) {
		  @SuppressWarnings("unchecked")
	        List<Discipline> students = (List<Discipline>) getWrappedData();  
	          
	        for(Discipline stud : students) {  
	            if(String.valueOf(stud.getId()).equals(rowKey))  
	                return stud;  
	        }  
	          
	          
		return null;
	}

	@Override
	public Object getRowKey(Discipline dis) {
		
		return dis.getId();
	}

}
