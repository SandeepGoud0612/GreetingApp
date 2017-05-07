/**
 * This source code is property of MyContacts Project Team.
 */
package com.sam.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.sam.daos.GroupsDAO;
import com.sam.daos.PersonDAO;
import com.sam.models.Address;
import com.sam.models.Groups;
import com.sam.models.Occasion;
import com.sam.models.Person;

/**
 * @author Sandeep
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(true)
public class PersonDAOTests {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@BeforeClass
	public static void setUp() {
	}

	@Autowired
	private PersonDAO personDAO;

	@Autowired
	private GroupsDAO groupDAO;

	// @Ignore
	@Test
	public void createPersonWithAllDetails() {
		Groups group1 = TestUtils.getGroupObject();
		Groups group2 = TestUtils.getGroupObject();
		Groups group1Persistent = groupDAO.save(group1);
		Groups group2Persistent = groupDAO.save(group2);

		Person personTransient = TestUtils.getPersonObject();

		Address addressTransient1 = TestUtils.getAddressObject();
		Address addressTransient2 = TestUtils.getAddressObject();
		Address addressTransient3 = TestUtils.getAddressObject();
		Address addressTransient4 = TestUtils.getAddressObject();
		Address addressTransient5 = TestUtils.getAddressObject();
		Address addressTransient6 = TestUtils.getAddressObject();

		personTransient.getAddresses().add(addressTransient1);
		personTransient.getAddresses().add(addressTransient2);
		personTransient.getAddresses().add(addressTransient3);
		personTransient.getAddresses().add(addressTransient4);
		personTransient.getAddresses().add(addressTransient5);
		personTransient.getAddresses().add(addressTransient6);

		addressTransient1.setPerson(personTransient);
		addressTransient2.setPerson(personTransient);
		addressTransient3.setPerson(personTransient);
		addressTransient4.setPerson(personTransient);
		addressTransient5.setPerson(personTransient);
		addressTransient6.setPerson(personTransient);

		Occasion occasionTransient1 = TestUtils.getOccasionObject();
		Occasion occasionTransient2 = TestUtils.getOccasionObject();
		Occasion occasionTransient3 = TestUtils.getOccasionObject();
		Occasion occasionTransient4 = TestUtils.getOccasionObject();
		Occasion occasionTransient5 = TestUtils.getOccasionObject();
		Occasion occasionTransient6 = TestUtils.getOccasionObject();

		personTransient.getOccasions().add(occasionTransient1);
		personTransient.getOccasions().add(occasionTransient2);
		personTransient.getOccasions().add(occasionTransient3);
		personTransient.getOccasions().add(occasionTransient4);
		personTransient.getOccasions().add(occasionTransient5);
		personTransient.getOccasions().add(occasionTransient6);

		occasionTransient1.setPerson(personTransient);
		occasionTransient2.setPerson(personTransient);
		occasionTransient3.setPerson(personTransient);
		occasionTransient4.setPerson(personTransient);
		occasionTransient5.setPerson(personTransient);
		occasionTransient6.setPerson(personTransient);

		group1Persistent.getPersons().add(personTransient);
		group2Persistent.getPersons().add(personTransient);
		personTransient.getGroups().add(group1Persistent);
		personTransient.getGroups().add(group2Persistent);

		Person personPersisted = personDAO.save(personTransient);
		assertNotNull(personPersisted);
		assertNotNull(personPersisted.getAddresses());
		assertEquals("FirstName", personPersisted.getFirstName());
	}

	@Ignore
	@Test
	public void updatePersonWithAllDetails() {
		Person personTransient = TestUtils.getPersonObject();

		Address addressTransient1 = TestUtils.getAddressObject();
		addressTransient1.setPerson(personTransient);
		personTransient.getAddresses().add(addressTransient1);

		Occasion occasionTransient1 = TestUtils.getOccasionObject();
		occasionTransient1.setPerson(personTransient);
		personTransient.getOccasions().add(occasionTransient1);

		Person personPersisted = personDAO.save(personTransient);

		Person personFromDB = personDAO.findOne(personPersisted.getId());
		personFromDB.setFirstName(personFromDB.getFirstName() + " Updated");
		personFromDB.getAddresses().get(0).setAddressLine1("AddressLine1 Updated");
		personFromDB.getOccasions().get(0).setName("OccasionName updated");

		Person personUpdated = personDAO.save(personFromDB);
		assertNotNull(personUpdated);
		assertNotNull(personUpdated.getAddresses());
		assertNotNull(personUpdated.getOccasions());

		assertEquals("FirstName updatePersonWithAllDetails Updated", personUpdated.getFirstName());
		assertEquals("AddressLine1 Updated", personUpdated.getAddresses().get(0).getAddressLine1());
		assertEquals("OccasionName updated", personUpdated.getOccasions().get(0).getName());
	}

	@Ignore
	@Test
	public void deletePersonWithAllDetails() {
		Person personTransient = TestUtils.getPersonObject();

		Address addressTransient1 = TestUtils.getAddressObject();
		Address addressTransient2 = TestUtils.getAddressObject();
		Address addressTransient3 = TestUtils.getAddressObject();
		personTransient.getAddresses().add(addressTransient1);
		personTransient.getAddresses().add(addressTransient2);
		personTransient.getAddresses().add(addressTransient3);
		addressTransient1.setPerson(personTransient);
		addressTransient2.setPerson(personTransient);
		addressTransient3.setPerson(personTransient);

		Occasion occasionTransient1 = TestUtils.getOccasionObject();
		Occasion occasionTransient2 = TestUtils.getOccasionObject();
		Occasion occasionTransient3 = TestUtils.getOccasionObject();
		personTransient.getOccasions().add(occasionTransient1);
		personTransient.getOccasions().add(occasionTransient2);
		personTransient.getOccasions().add(occasionTransient3);
		occasionTransient1.setPerson(personTransient);
		occasionTransient2.setPerson(personTransient);
		occasionTransient3.setPerson(personTransient);

		Person personPersisted = personDAO.save(personTransient);
		assertNotNull(personPersisted);
		assertNotNull(personPersisted.getAddresses());

		personDAO.delete(personPersisted.getId());
	}

	@Ignore
	@Test
	public void deletePersonWithOneAddressOcasion() {
		Person personTransient = TestUtils.getPersonObject();

		Address addressTransient1 = TestUtils.getAddressObject();
		Address addressTransient2 = TestUtils.getAddressObject();
		Address addressTransient3 = TestUtils.getAddressObject();
		personTransient.getAddresses().add(addressTransient1);
		personTransient.getAddresses().add(addressTransient2);
		personTransient.getAddresses().add(addressTransient3);
		addressTransient1.setPerson(personTransient);
		addressTransient2.setPerson(personTransient);
		addressTransient3.setPerson(personTransient);

		Occasion occasionTransient1 = TestUtils.getOccasionObject();
		Occasion occasionTransient2 = TestUtils.getOccasionObject();
		Occasion occasionTransient3 = TestUtils.getOccasionObject();
		personTransient.getOccasions().add(occasionTransient1);
		personTransient.getOccasions().add(occasionTransient2);
		personTransient.getOccasions().add(occasionTransient3);
		occasionTransient1.setPerson(personTransient);
		occasionTransient2.setPerson(personTransient);
		occasionTransient3.setPerson(personTransient);

		Person personPersisted = personDAO.save(personTransient);
		assertNotNull(personPersisted);
		assertNotNull(personPersisted.getAddresses());
		assertEquals("FirstName deletePersonWithOneAddressOcasion", personPersisted.getFirstName());

		personPersisted.getAddresses().get(0).setPerson(null);
		personPersisted.getAddresses().set(0, null);
		personPersisted.getOccasions().get(0).setPerson(null);
		personPersisted.getOccasions().set(0, null);

		Person personFromDB = personDAO.save(personPersisted);
		assertNotNull(personFromDB);
		assertNotNull(personFromDB.getAddresses());
	}

	@Ignore
	@Test
	public void createPerson() {
		Person personTransient = TestUtils.getPersonObject();
		Person personPersisted = personDAO.save(personTransient);

		assertNotNull(personPersisted);
		assertEquals("FirstName createPerson", personPersisted.getFirstName());
	}

	@Ignore
	@Test
	public void createPersonWithImage() {
		Person personTransient = TestUtils.getPersonObject();
		Person personPersisted = personDAO.save(personTransient);

		assertNotNull(personPersisted);
		assertEquals("FirstName createPersonWithImage", personPersisted.getFirstName());
	}

	@Ignore
	@Test
	public void deleteImageFromPerson() {
		Person personTransient = TestUtils.getPersonObject();
		Person personPersisted = personDAO.save(personTransient);

		assertNotNull(personPersisted);
		assertEquals("FirstName deleteImageFromPerson", personPersisted.getFirstName());

		Person personUpdated = personDAO.save(personPersisted);
		assertNotNull(personUpdated);
	}

	@Ignore
	@Test
	public void deletePersonWithImage() {
		Person personTransient = TestUtils.getPersonObject();
		Person personPersisted = personDAO.save(personTransient);

		assertNotNull(personPersisted);
		assertEquals("FirstName deletePersonWithImage", personPersisted.getFirstName());

		personDAO.delete(personPersisted.getId());
	}

	@Ignore
	@Test
	public void createPersonDOBEmpty() {
		Person personTransient = TestUtils.getPersonObject();
		personTransient.setDob(null);
		Person personPersisted = personDAO.save(personTransient);

		assertNotNull(personPersisted);
		assertEquals("FirstName createPersonDOBEmpty", personPersisted.getFirstName());
	}

	@Ignore
	@Test
	public void findPersonById() {
		Person personTransient = TestUtils.getPersonObject();
		Person personPersisted = personDAO.save(personTransient);

		Person personFromDB = personDAO.findOne(personPersisted.getId());
		personFromDB.setAddresses(new ArrayList<>());
		personFromDB.setOccasions(new ArrayList<>());

		assertNotNull(personFromDB);
		assertEquals("FirstName findPersonById", personFromDB.getFirstName());
	}

	@Ignore
	@Test
	public void updatePerson() {
		Person personTransient = TestUtils.getPersonObject();
		Person personPersisted = personDAO.save(personTransient);

		Person personFromDB = personDAO.findOne(personPersisted.getId());
		personFromDB.setFirstName(personFromDB.getFirstName() + " Updated");
		Person personFromDB2 = personDAO.save(personFromDB);

		assertNotNull(personFromDB2);
		assertEquals("FirstName updatePerson Updated", personFromDB2.getFirstName());
	}

	@Ignore
	@Test
	public void deletePerson() {
		Person personTransient = TestUtils.getPersonObject();
		Person personPersisted = personDAO.save(personTransient);
		personDAO.delete(personPersisted.getId());
		log.info("Deleting Person " + personPersisted.getId());
	}

	@Ignore
	@Test
	public void findAll() {
		List<Person> persons = personDAO.findAll();
		for (Person person : persons) {
			person.setAddresses(new ArrayList<>());
			person.setOccasions(new ArrayList<>());
		}
		assertNotNull(persons);
	}

	@Ignore
	@Test(expected = javax.validation.ConstraintViolationException.class)
	public void createPersonWithInvalidEmailId() {
		Person personTransient = TestUtils.getPersonObject();
		personTransient.setPrimaryEmail("EmailId");
		personTransient.setSecondaryEmail("AlternateEmailId");
		personDAO.save(personTransient);
	}

	@Ignore
	@Test(expected = javax.validation.ConstraintViolationException.class)
	public void createPersonWithInvalidPhoneNumber() {
		Person personTransient = TestUtils.getPersonObject();
		personTransient.setPrimaryContact(9111145678904324324L);
		personTransient.setSecondaryContact(912224567890432434L);
		personDAO.save(personTransient);
	}

	@Ignore
	@Test(expected = javax.validation.ConstraintViolationException.class)
	public void createPersonInvalidFirstNameEmpty() {
		Person personTransient = TestUtils.getPersonObject();
		personTransient.setFirstName("");
		personDAO.save(personTransient);
	}

	@Ignore
	@Test(expected = javax.validation.ConstraintViolationException.class)
	public void createPersonInvalidDOB() {
		Person personTransient = TestUtils.getPersonObject();
		personTransient.setDob(Date.valueOf(LocalDate.of(2020, Month.FEBRUARY, 28)));
		personDAO.save(personTransient);
	}

}
