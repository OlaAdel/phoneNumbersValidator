package com.phonebook.validator.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.phonebook.validator.model.Customer;
import com.phonebook.validator.model.PhoneNumber;

@SpringBootTest
class CustomerRepositoryTest {

	@Autowired
	CustomerRepository customerRepo;
	
	@BeforeEach
	void setUp() {
		customerRepo.deleteAll();
	}
	
	@Test
	void should_ReturnAllCustomers_When_FindAll() {
		
		customerRepo.save(new Customer(1,"Yosaf Karrouch",new PhoneNumber("(212) 698054317")));
		customerRepo.save(new Customer(2,"Younes Boutikyad",new PhoneNumber("(212) 6546545369")));
		
		List<Customer> allCustomers = customerRepo.findAll();
				
		assertEquals(allCustomers.size(), 2);

	}
}
