package br.mod.escolar.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipos_usuarios")
public class UserType {
	
	private int id;
	private String type;
	private String description;
	private String page;
	
	public UserType(){}
	
	public UserType(int id, String type, String description, String page) {
		super();
		this.id = id;
		this.type = type;
		this.description = description;
		this.page = page;
	}

	@Id
	@GeneratedValue
	@Column(name = "id")
	public int getId() {
		return id;
	}

	@Column(name = "tipo")
	public String getType() {
		return type;
	}

	@Column(name = "descricao")
	public String getDescription() {
		return description;
	}

	@Column(name = "pagina")
	public String getPage() {
		return page;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPage(String page) {
		this.page = page;
	}
	
}