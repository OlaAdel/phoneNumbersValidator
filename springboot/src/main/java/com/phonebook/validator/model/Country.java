package com.phonebook.validator.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Country {
	private static final Character countryCodePrefix = '+';
	
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="country_seq")
	@SequenceGenerator(name="country_seq", initialValue=1)
	private int country_id;
	
	private String name;	
	private String code;
	private String regex;
	
	public Country() {
	}
	public Country(String name, String code) {
		this.name = name;
		this.code = code;
	}
	public Country(String name, String code, String regex) {
		this(name, code);
		this.regex=regex;
	}
	
	public Country(int id, String name, String code, String regex) {
		this(name, code, regex);
		this.country_id=id;
	}
	
	public int getCountry_id() {
		return country_id;
	}

	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getRegex() {
		return regex;
	}
	
	public void setRegex(String regex) {
		this.regex = regex;
	}
	
	public static String extractCountryCodeFromPhoneNumber(PhoneNumber number) {
		return countryCodePrefix + number.getNumber().substring(1, 4);
	}
	
}
