/**
 * 
 */
package com.example.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Coin Entity
 * 
 * @author Varusai
 *
 */
@Entity
@Table(name = "COIN")
public class Coin {
	@Id
	@Column(name = "VALUE")
	private Double denominationValue;

	@Column(name = "COUNT")
	private Integer availableCount;

	/**
	 * Default constructor
	 */
	public Coin() {
		super();
	}

	/**
	 * @param denominationValue
	 * @param availableCount
	 */
	public Coin(Double denominationValue, Integer availableCount) {
		super();
		this.denominationValue = denominationValue;
		this.availableCount = availableCount;
	}

	/**
	 * @return the denominationValue
	 */
	public Double getDenominationValue() {
		return denominationValue;
	}

	/**
	 * @param denominationValue the denominationValue to set
	 */
	public void setDenominationValue(Double denominationValue) {
		this.denominationValue = denominationValue;
	}

	/**
	 * @return the availableCount
	 */
	public Integer getAvailableCount() {
		return availableCount;
	}

	/**
	 * @param availableCount the availableCount to set
	 */
	public void setAvailableCount(Integer availableCount) {
		this.availableCount = availableCount;
	}

}
