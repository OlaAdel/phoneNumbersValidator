package com.phonebook.validator.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Customer {

	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="customer_seq")
	@SequenceGenerator(name="customer_seq", initialValue=0)
	@Column(unique = true)
	private int id;
	
	private String name;
	
	@Embedded
    @AttributeOverrides(value = { @AttributeOverride( name = "number", column = @Column(name = "phone")),
    							  @AttributeOverride( name = "state", column = @Column(name = "number_state"))})
    private PhoneNumber phoneNumber;

	
	public Customer() {
		
	}
	
	public Customer(String name, PhoneNumber phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	public Customer(int id, String name, PhoneNumber phoneNumber) {
		this(name, phoneNumber);
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(PhoneNumber phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
