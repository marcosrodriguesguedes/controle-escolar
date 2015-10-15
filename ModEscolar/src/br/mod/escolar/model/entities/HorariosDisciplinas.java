package br.mod.escolar.model.entities;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "horarios_disciplcinas")
public class HorariosDisciplinas {

     private Integer id;
     
     private String idDisciplina;
     
     private Integer idDia;
	
	 private String dia;
     
     private String hora;
	
	
     public HorariosDisciplinas(){
    	 
     }
    
    @Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false) 
    public Integer getId() {
			return id;
	  }

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "idDisciplina")
	public String getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(String idDisciplina) {
		this.idDisciplina = idDisciplina;
	}
	
	@Column(name = "idDia")
	public int getIdDia() {
			return idDia;
	}

	public void setIdDia(int idDia) {
			this.idDia = idDia;
		}


	@Column(name = "dia")
	public String getDia() {
		return dia;
	}
   
	public void setDia(String dia) {
		this.dia = dia;
	}
	
	@Column(name = "hora")
	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
     
}
