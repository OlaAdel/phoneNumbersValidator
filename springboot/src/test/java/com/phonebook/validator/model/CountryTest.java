package com.phonebook.validator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CountryTest {

	@Test
	void should_ReturnCode_When_extractCountryCodeFromPhoneNumber_1() {
		PhoneNumber number = new PhoneNumber("(212) 691933626");
		String code = Country.extractCountryCodeFromPhoneNumber(number);
		assertEquals(code, "+212");
	}
	
	@Test
	void should_ReturnCode_When_extractCountryCodeFromPhoneNumber_2() {
		PhoneNumber number = new PhoneNumber("(237) 691933626");
		String code = Country.extractCountryCodeFromPhoneNumber(number);
		assertEquals(code, "+237");
	}

}
