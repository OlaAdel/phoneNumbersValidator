package com.phonebook.validator.service.specification;

import java.util.List;

public class CustomerFilter {
	private List<String> phoneNumbercountries;
	private List<Boolean> PhoneNumberState;
	
	public CustomerFilter(List<String> phoneNumbercountries, List<Boolean> phoneNumberState) {
		this.phoneNumbercountries = phoneNumbercountries;
		PhoneNumberState = phoneNumberState;
	}
	public List<String> getPhoneNumbercountries() {
		return phoneNumbercountries;
	}
	public void setPhoneNumbercountries(List<String> phoneNumbercountries) {
		this.phoneNumbercountries = phoneNumbercountries;
	}
	public List<Boolean> getPhoneNumberState() {
		return PhoneNumberState;
	}
	public void setPhoneNumberState(List<Boolean> phoneNumberState) {
		PhoneNumberState = phoneNumberState;
	}
}
