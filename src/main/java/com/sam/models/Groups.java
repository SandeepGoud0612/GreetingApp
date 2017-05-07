/**
 * 
 */
package com.sam.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author sunny
 *
 */
@Entity
public class Groups {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Size(min = 1, max = 50)
	@NotNull
	@Column(nullable = false, length = 50)
	private String name;

	@ManyToMany(mappedBy = "groups", cascade = CascadeType.ALL)
	private List<Person> persons = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	
}
