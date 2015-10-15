package br.mod.escolar.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import br.mod.escolar.model.exception.InvalidParameterException;

@Entity
@Table(name = "disciplinas")
public class Discipline {

	private int id;
	private String name;
	private int grade;
	private String grade2;
	private String area;
	private String hoursPerWeek;
	private String hoursPerYear;
	private Integer cod_discipline;
	private String turno;
	
	

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	/**
	 * Construtor default para Discipline
	 */
	public Discipline() {

	}

	/**
	 * Construtor para Discipline
	 * @param name
	 * @param grade
	 * @param grade2
	 * @param area
	 * @param hoursPerWeek
	 * @param hoursPerYear
	 * @throws InvalidParameterException
	 */
	public Discipline(String name, int grade, String grade2, String area,
			String hoursPerWeek, String hoursPerYear)
			throws InvalidParameterException {
		
		this.name = name;
		this.grade = grade;
		this.grade2 = grade2;
		this.area = area;
		this.hoursPerWeek = hoursPerWeek;
		this.hoursPerYear = hoursPerYear;
	}

	

	@Id
	@GeneratedValue
	@Column(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "NomeDisciplina")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Serie")
	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Column(name = "Grau")
	public String getGrade2() {
		return grade2;
	}

	public void setGrade2(String grade2) {
		this.grade2 = grade2;
	}

	@Column(name = "area")
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(name = "CaragaHorariaSemanal")
	public String getHoursPerWeek() {
		return hoursPerWeek;
	}

	public void setHoursPerWeek(String hoursPerWeek) {
		this.hoursPerWeek = hoursPerWeek;
	}

	@Column(name = "CaragaHorariaAnual")
	public String getHoursPerYear() {
		return hoursPerYear;
	}
	
	

	public void setHoursPerYear(String hoursPerYear) {
		this.hoursPerYear = hoursPerYear;
	}

	public String toString() {
		return name + " " + grade + " " + grade2 + " " + area + " "+ cod_discipline ;
	}

	/**
	 * @return the cod_discipline
	 */
	public Integer getCod_discipline() {
		return cod_discipline;
	}

	/**
	 * @param cod_discipline the cod_discipline to set
	 */
	public void setCod_discipline(Integer cod_discipline) {
		this.cod_discipline = cod_discipline;
	}
	
	
	public void setCodDiscipline(String nome){
		  switch (nome) {
			
		   case "FILOSOFIA":
			   
			   setCod_discipline(4);
				
				break;
				
			case "HISTÃ“RIA":
				
				setCod_discipline(7);
				break;
				
			case "SOCIOLOGIA":
				setCod_discipline(9);
				break;
				
			case "GEOGRAFIA":
				setCod_discipline(8);
				break;
				
			case "PORTUGUES":
				setCod_discipline(0);
				break;
				
			case "LITERATURA":
				setCod_discipline(15);
				break;
				
			case "ESPANHOL":
				setCod_discipline(12);
				break;
				
			case "INGLES":
				setCod_discipline(11);
				break;
				
			case "EDUCACAO FISICA":
				setCod_discipline(2);
				break;
				
			case "BIOLOGIA":
				setCod_discipline(3);
				break;
				
			case "MATEMÃ�TICA":
				setCod_discipline(6);
				break;
				
			case "FISICA":
				setCod_discipline(5);
				break;
				
			case "QUÃ�MICA":
				setCod_discipline(10);
				break;
				
			case "ARTE":
				setCod_discipline(1);
				break;

			default:
				setCod_discipline(22);
				break;
			}
	}
	

}
