package com.phonebook.validator.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PhoneNumberTest {

	@Test
	void should_ReturnFalse_When_DoesNotMatchCode_DoesNotMatchRegex() {
		
		PhoneNumber number = new PhoneNumber("(212) 6007989253");
		number.setCountry(new Country());
		number.getCountry().setRegex("\\(237\\)\\ ?[2368]\\d{7,8}$");
		number.ValidateNumber();
		
		assertFalse(number.getIsValid());
	}
	
	@Test
	void should_ReturnFalse_When_DoesMatchCode_DoesNotMatchRegex() {
		
		PhoneNumber number = new PhoneNumber("(212) 6007989253");
		number.setCountry(new Country());
		number.getCountry().setRegex("\\(212\\)\\ ?[5-9]\\d{8}$");
		number.ValidateNumber();
		
		assertFalse(number.getIsValid());
	}
	
	@Test
	void should_ReturnFalse_When_DoesNotMatchCode_DoesMatchRegex() {
		
		PhoneNumber number = new PhoneNumber("(212) 691933626");
		number.setCountry(new Country());
		number.getCountry().setRegex("\\(251\\)\\ ?[1-59]\\d{8}$");
		number.ValidateNumber();
		
		assertFalse(number.getIsValid());
	}
	
	@Test
	void should_ReturnTrue_When_DoesMatchCode_DoesMatchRegex() {
		
		PhoneNumber number = new PhoneNumber("(212) 691933626");
		number.setCountry(new Country());
		number.getCountry().setRegex("\\(212\\)\\ ?[5-9]\\d{8}$");
		number.ValidateNumber();
		
		assertTrue(number.getIsValid());
	}

}
