package br.mod.escolar.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class User {

	
	private Integer id;
	private Integer idType;
	private Integer idPerson;
	private String login;
	private String password;
	private String nameUser;
	private Integer active;

	/**
	 * Construtor default para User
	 */
	public User() {
	}

	/**
	 * Construtor para User
	 * @param idType
	 * @param idPerson
	 * @param login
	 * @param password
	 */
	public User(int idType, int idPerson, String login, String password) {
		super();
		this.idType = idType;
		this.idPerson = idPerson;
		this.login = login;
		this.password = password;
	}

	/**
	 * Construtor para User
	 * @param id
	 * @param idType
	 * @param idPerson
	 * @param login
	 * @param password
	 */
	public User(int id, int idType, int idPerson, String login, String password) {
		this.id = id;
		this.idType = idType;
		this.idPerson = idPerson;
		this.login = login;
		this.password = password;
	}
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	@Column(name = "idTipo")
	public Integer getIdType() {
		return idType;
	}

	@Column(name = "idPessoa")
	public Integer getIdPerson() {
		return idPerson;
	}

	@Column(name = "login")
	public String getLogin() {
		return login;
	}

	@Column(name = "senha")
	public String getPassword() {
		return password;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIdType(Integer idType) {
		this.idType = idType;
	}

	public void setIdPerson(Integer idPerson) {
		this.idPerson = idPerson;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return the nameUser
	 */
	public String getNameUser() {
		return nameUser;
	}

	/**
	 * @param nameUser the nameUser to set
	 */
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	/**
	 * @return the active
	 */
	public Integer getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(Integer active) {
		this.active = active;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", idType=" + idType + ", idPerson="
				+ idPerson + ", login=" + login + ", password=" + password
				+ ", nameUser=" + nameUser + ", active=" + active + "]";
	}

	

}