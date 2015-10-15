package br.mod.escolar.model.entities;

public class Documents {
	
	private String descrypt;
	
	private String sise;
	
	private String type;

	public Documents(String descrypt, String sise, String type) {
		
		this.descrypt = descrypt;
		this.sise = sise;
		this.type = type;
	}

	/**
	 * @return the descrypt
	 */
	public String getDescrypt() {
		return descrypt;
	}

	/**
	 * @param descrypt the descrypt to set
	 */
	public void setDescrypt(String descrypt) {
		this.descrypt = descrypt;
	}

	/**
	 * @return the sise
	 */
	public String getSise() {
		return sise;
	}

	/**
	 * @param sise the sise to set
	 */
	public void setSise(String sise) {
		this.sise = sise;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	

}
