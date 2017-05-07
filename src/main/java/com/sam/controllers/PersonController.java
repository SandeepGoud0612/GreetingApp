/**
 * 
 */
package com.sam.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sam.CloneUtil;
import com.sam.models.Person;
import com.sam.services.PersonService;

/**
 * @author JavaTraining
 *
 */
@RestController
@RequestMapping(value = "/person")
public class PersonController {

	@Autowired
	private PersonService personService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> getPersonById(@PathVariable Integer id) {
		Person personPersistent = personService.getPersonById(id);
		return new ResponseEntity<Person>(personPersistent, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Person>> getAllPersons() {
		List<Person> personsPersistent = personService.getAllPersons();
		return new ResponseEntity<List<Person>>(personsPersistent, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> createPerson(@RequestBody Person person) {
		Person newPerson = personService.createPerson(person);
		return new ResponseEntity<Person>(newPerson, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> updatePerson(@PathVariable Integer id, @RequestBody Person person) {
		Person personsPersistent = personService.getPersonById(id);
		CloneUtil.copyPerson(person, personsPersistent);
		Person newPerson = personService.updatePerson(personsPersistent);
		return new ResponseEntity<Person>(newPerson, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletePersonById(@PathVariable Integer id) {
		personService.deletePerson(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
