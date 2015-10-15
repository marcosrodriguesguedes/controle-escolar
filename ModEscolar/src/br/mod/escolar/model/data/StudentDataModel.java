package br.mod.escolar.model.data;
import java.io.Serializable;  
import java.util.List;  

import javax.faces.model.ListDataModel;  

import org.primefaces.model.SelectableDataModel;  

import br.mod.escolar.model.entities.Student;
  
public class StudentDataModel extends ListDataModel<Student> implements SelectableDataModel<Student>, Serializable {    
  
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public StudentDataModel() {  
   
    
    }  
  
    public StudentDataModel(List<Student> data) {  
        super(data);  
    }  
      
    @Override  
    public Student getRowData(String rowKey) {  
        
        @SuppressWarnings("unchecked")
        List<Student> students = (List<Student>) getWrappedData();  
          
        for(Student stud : students) {  
            if(String.valueOf(stud.getId()).equals(rowKey))  
                return stud;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Student stud) {  
        return stud.getId();  
    }  
}  