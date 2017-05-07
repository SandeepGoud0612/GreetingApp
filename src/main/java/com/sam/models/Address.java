/**
 * 
 */
package com.sam.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author sunny
 *
 */
@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "ADDRESS_LINE1", nullable = false, length = 100)
	private String addressLine1;

	@Size(min = 0, max = 100)
	@Column(name = "ADDRESS_LINE2", length = 100)
	private String addressLine2;

	@Size(min = 1, max = 50)
	@NotNull
	@Column(name = "CITY", nullable = false, length = 50)
	private String city;

	@Size(min = 1, max = 50)
	@NotNull
	@Column(name = "STATE", nullable = false, length = 50)
	private String state;

	@Size(min = 1, max = 50)
	@NotNull
	@Column(name = "COUNTRY", nullable = false, length = 50)
	private String country;

	@Transient
	private static final long ZIPCODE_MAX = 999999999L;

	@Max(ZIPCODE_MAX)
	@NotNull
	@Column(nullable = false, length = 10)
	private Long zipCode;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "PERSON_ID", nullable = false)
	private Person person;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Long getZipCode() {
		return zipCode;
	}

	public void setZipCode(Long zipCode) {
		this.zipCode = zipCode;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
}
