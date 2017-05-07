/**
 * 
 */
package com.sam.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;

import com.sam.enums.Gender;
import com.sam.enums.MaritalStatus;

/**
 * @author sunny
 *
 */
@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Size(min = 1, max = 50)
	@NotNull
	@Column(name = "FIRST_NAME", nullable = false, length = 50)
	private String firstName;

	@Size(min = 1, max = 50)
	@NotNull
	@Column(name = "LAST_NAME", nullable = false, length = 50)
	private String lastName;

	@Past
	@Temporal(TemporalType.DATE)
	private Date dob;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(length = 10, nullable = false)
	private Gender gender;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "MARITAL_STATUS", length = 15, nullable = false)
	private MaritalStatus maritalStatus;

	@Transient
	private static final long PHONE_MAX = 99999999999999L;

	@Max(PHONE_MAX)
	@NotNull
	@Column(name = "PRIMARY_CONTACT", length = 15, nullable = false)
	private Long primaryContact;

	@Max(PHONE_MAX)
	@Column(name = "SECONDARY_CONTACT", length = 15)
	private Long secondaryContact;

	@Email
	@Size(min = 1, max = 50)
	@NotNull
	@Column(name = "PRIMARY_EMAIL", length = 50, nullable = false)
	private String primaryEmail;

	@Email
	@Size(min = 0, max = 50)
	@Column(name = "SECONDARY_EMAIL", length = 50)
	private String secondaryEmail;

	@LazyCollection(LazyCollectionOption.TRUE)
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Address> addresses = new ArrayList<>();

	@LazyCollection(LazyCollectionOption.TRUE)
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Occasion> occasions = new ArrayList<>();

	@LazyCollection(LazyCollectionOption.TRUE)
	@ManyToMany
	@JoinTable(name = "person_group", joinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"))
	private List<Groups> groups = new ArrayList<>();
	
	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Long getPrimaryContact() {
		return primaryContact;
	}

	public void setPrimaryContact(Long primaryContact) {
		this.primaryContact = primaryContact;
	}

	public Long getSecondaryContact() {
		return secondaryContact;
	}

	public void setSecondaryContact(Long secondaryContact) {
		this.secondaryContact = secondaryContact;
	}

	public String getPrimaryEmail() {
		return primaryEmail;
	}

	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}

	public String getSecondaryEmail() {
		return secondaryEmail;
	}

	public void setSecondaryEmail(String secondaryEmail) {
		this.secondaryEmail = secondaryEmail;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Occasion> getOccasions() {
		return occasions;
	}

	public void setOccasions(List<Occasion> occasions) {
		this.occasions = occasions;
	}

	public List<Groups> getGroups() {
		return groups;
	}

	public void setGroups(List<Groups> groups) {
		this.groups = groups;
	}

}
