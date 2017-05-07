/**
 * This source code is property of MyContacts Project Team.
 */
package com.sam.dao;

import java.sql.Date;
import java.time.LocalDate;

import com.sam.enums.Gender;
import com.sam.enums.MaritalStatus;
import com.sam.models.Address;
import com.sam.models.Groups;
import com.sam.models.Occasion;
import com.sam.models.Person;

/**
 * @author JavaTraining
 *
 */
public class TestUtils {

	public static Groups getGroupObject() {
		Groups group = new Groups();
		group.setName("Group1");
		return group;
	}

	public static Person getPersonObject() {
		Person personTransient = new Person();
		personTransient.setFirstName("FirstName");
		personTransient.setLastName("LastName");
		personTransient.setDob(Date.valueOf(LocalDate.now()));
		personTransient.setGender(Gender.MALE);
		personTransient.setMaritalStatus(MaritalStatus.SINGLE);
		personTransient.setPrimaryEmail("EmailId@gmail.com");
		personTransient.setSecondaryEmail("AlternateEmailId@gmail.com");
		personTransient.setPrimaryContact(911114567L);
		personTransient.setSecondaryContact(911114567L);
		return personTransient;
	}

	public static Address getAddressObject() {
		Address address = new Address();
		address.setAddressLine1("Address Line 1");
		address.setAddressLine2("Address Line 2");
		address.setCity("City");
		address.setState("State");
		address.setCountry("Country");
		address.setZipCode(77077L);
		return address;
	}

	public static Occasion getOccasionObject() {
		Occasion occasion = new Occasion();
		occasion.setName("Occasion");
		occasion.setActive(true);
		occasion.setDate(Date.valueOf(LocalDate.now()));
		return occasion;
	}

}
