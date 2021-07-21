package com.phonebook.validator.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.phonebook.validator.model.Country;

@SpringBootTest
class CountryRepositoryTest {
	
	@Autowired
	CountryRepository countryRepo;
	
	@BeforeEach
	void setUp() {
		countryRepo.deleteAll();
	}
	
	
	@Test
	void should_ReturnCountry_When_findByCode() {
		countryRepo.save(new Country("Cameroon","+237"));
				
		assertEquals(countryRepo.findByCode("+237").getName(), "Cameroon");
	}

}
