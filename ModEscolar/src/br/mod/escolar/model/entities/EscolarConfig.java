package br.mod.escolar.model.entities;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Table(name = "escolar_config")
public class EscolarConfig {
	
	private Integer id;
	
	private Integer codConfig;
	
	private Integer valor;

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false) 
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the codConfig
	 */
	public Integer getCodConfig() {
		return codConfig;
	}

	/**
	 * @param codConfig the codConfig to set
	 */
	public void setCodConfig(Integer codConfig) {
		this.codConfig = codConfig;
	}

	/**
	 * @return the valor
	 */
	public Integer getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(Integer valor) {
		this.valor = valor;
	}
	
	

}
