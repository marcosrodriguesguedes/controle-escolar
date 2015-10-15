package br.mod.escolar.model.entities;

public class Competencia {
	
	private int id;
	
	private String code;
	
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Competencia [id=" + id + ", code=" + code + ", description="
				+ description + "]";
	}
	
	
	
	
	

}
