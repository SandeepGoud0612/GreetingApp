/**
 * 
 */
package com.sam.services;

import java.util.List;

import com.sam.models.Person;

/**
 * @author JavaTraining
 *
 */
public interface PersonService {

	Person getPersonById(Integer id);

	List<Person> getAllPersons();

	Person createPerson(Person person);

	Person updatePerson(Person person);

	void deletePerson(Integer id);

}
