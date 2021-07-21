package com.phonebook.validator.model;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class PhoneNumber {
	
	private String number;
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.PERSIST)
	@JoinColumn(name = "country_id")
	private Country country;
	
	private Boolean isValid;
	
	
	public PhoneNumber() {
		
	}

	public PhoneNumber(String number) {
		this.number = number;
	}
	
	public PhoneNumber(String number, Boolean isValid) {
		this(number);
		this.isValid = isValid;
	}
	
	public PhoneNumber(String number, Country country, Boolean isValid) {
		this(number, isValid);
		this.country = country;
	}
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public Country getCountry() {
		return country;
	}
	
	public void setCountry(Country country) {
		this.country = country;
	}
	
	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
	
	public void ValidateNumber() {
		if(number.matches(country.getRegex()))
			this.isValid = true;
		else
			this.isValid = false;
	}
	
}
