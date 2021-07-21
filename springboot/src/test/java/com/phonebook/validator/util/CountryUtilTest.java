package com.phonebook.validator.util;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.phonebook.validator.model.Country;


@SpringBootTest
class CountryUtilTest {
	
	@Test
	void should_ReturnTrue_When_CodesFileDoesExist() {
		File file = new File(CountryUtil.codeCountryFilePath);
		assertTrue(file.exists());
	}
	
	@Test
	void should_ReturnTrue_When_CountiresFileDoesExist() {
		File file = new File(CountryUtil.countryRegexFilePath);
		assertTrue(file.exists());
	}
	
	@Test
	void should_ReturnTrue_When_CodesFileLoadedCorrectly() {
		assertTrue(CountryUtil.codeCountryMap.size() == 5);
	}
	
	@Test
	void should_ReturnTrue_When_CountriesFileLoadedCorrectly() {
		assertTrue(CountryUtil.countryRegexMap.size() == 5);
	}
	
	@Test
	void should_ReturnTrue_When_CodeDoesExist() {
		assertTrue(CountryUtil.isCodeExist("+237"));
	}
	
	@Test
	void should_ReturnFalse_When_CodeDoesNotExist() {
		assertFalse(CountryUtil.isCodeExist("+210"));
	}
	
	@Test
	void should_ReturnTCountry_When_getCountryByCode() {
		Country country = CountryUtil.getCountryByCode("+237");
		
		assertAll(() -> assertEquals(country.getName(), "Cameroon"),
				  () -> assertEquals(country.getRegex(), "\\(237\\)\\ ?[2368]\\d{7,8}$"));
		
	}

}
