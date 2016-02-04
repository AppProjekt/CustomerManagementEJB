package com.cm.persistence.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
//import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
//import com.cm.persistence.enums.Gender;
//import com.cm.persistence.enums.Relationship;

@Entity
@NamedQuery(
		name = Customer.QUERY_GETALL,
		query = "SELECT c FROM Customer c")

//@NamedNativeQuery(
//		name = Customer.QUERY_BIRTHDAYS,
//		query = "SELECT * FROM customer WHERE DAY(birthday) = ? AND MONTH(birthday) = ?",
//		resultClass = Customer.class)
public class Customer  implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public static final String QUERY_GETALL = "Customer.GetAll";
	public static final String QUERY_BIRTHDAYS = "Customer.GetByBirthday";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Size(min=1, max=50)
	private String firstName;
	
	@NotNull
	@Size(min=1, max=100)
	private String lastName;
	
	@NotNull
	@Size(max=100)
	private String city;
	
	@NotNull
	@Size(max=100)
	private String street;
	
	@NotNull
	@Size(max=10)
	private String zip;
	
	@NotNull
	@Size(max=100)
	private String kontakt;
	
//	private Gender gender;
//	
//	private Relationship relationship;
//	
//	private Date birthday;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true,  fetch=FetchType.EAGER)
	@JoinColumn(name = "customerid")
	private List<Address> addresses = new ArrayList<Address>();
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true,  fetch=FetchType.EAGER)
	@JoinColumn(name = "customerid")
	private List<Communication> communications = new ArrayList<Communication>();
	
	@Version
	private Timestamp lastChanged;
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public String getCity() {
		return city;
	}
	
	@NotNull
	@Size(max=50)
	private String country;

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public String getKontakt() {
		return kontakt;
	}

	public void setKontakt(String kontakt) {
		this.kontakt = kontakt;
	}

//	public Gender getGender() {
//		return gender;
//	}
//
//	public void setGender(Gender gender) {
//		this.gender = gender;
//	}
//
//	public Relationship getRelationship() {
//		return relationship;
//	}
//
//	public void setRelationship(Relationship relationship) {
//		this.relationship = relationship;
//	}
//
//	public Date getBirthday() {
//		return birthday;
//	}
//
//	public void setBirthday(Date birthday) {
//		this.birthday = birthday;
//	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public List<Communication> getCommunications() {
		return communications;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public void setCommunications(List<Communication> communications) {
		this.communications = communications;
	}
}
