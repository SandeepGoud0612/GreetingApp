/**
 * 
 */
package com.sam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sam.daos.PersonDAO;
import com.sam.models.Person;

/**
 * @author JavaTraining
 *
 */
@Service
@Transactional
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDAO personDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sam.services.PersonService#getPersonById(java.lang.Integer)
	 */
	@Override
	public Person getPersonById(Integer id) {
		return personDAO.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sam.services.PersonService#getAllPersons()
	 */
	@Override
	public List<Person> getAllPersons() {
		return personDAO.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sam.services.PersonService#createPerson(com.sam.models.Person)
	 */
	@Override
	public Person createPerson(Person person) {
		return personDAO.save(person);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sam.services.PersonService#updatePerson(com.sam.models.Person)
	 */
	@Override
	public Person updatePerson(Person person) {
		return personDAO.save(person);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sam.services.PersonService#deletePerson(java.lang.Integer)
	 */
	@Override
	public void deletePerson(Integer id) {
		personDAO.delete(id);
	}

}
