package com.phonebook.validator.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.phonebook.validator.dao.CountryRepository;
import com.phonebook.validator.dao.CustomerRepository;
import com.phonebook.validator.model.Customer;
import com.phonebook.validator.model.PhoneNumber;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
	
	@InjectMocks
	CustomerService customerService;
	
	@Mock
	CustomerRepository customerRepo;
	
	@Mock
	CountryRepository countryRepo;
	
	
	List<Customer> customerTestList = new ArrayList<Customer>(
	          Arrays.asList(new Customer(1,"SUGAR STARRK BARRAGAN",new PhoneNumber("(237) 6780009592")),
			          		new Customer(2,"Yonatan Tekelay",new PhoneNumber("(251) 911168450"))));
	
	
	
	@Test
	void should_ReturnvalidatedCustomers_when_validateCustomers() {
		List<Customer> validatedCustomers = customerService.validateCustomersNumbers(customerTestList);
		
		assertAll(() -> assertFalse(validatedCustomers.get(0).getPhoneNumber().getIsValid()),
				  () -> assertTrue(validatedCustomers.get(1).getPhoneNumber().getIsValid()));
	}
	
	@Test
    void should_ReturnValidatedCustomers_when_validateDatabase() {

        Mockito.when(customerRepo.findAll()).thenReturn(customerTestList);
        
        List<Customer> validatedCustomers = customerService.ValidateDatabase();
        
		verify(customerRepo).findAll();
		
        assertAll(() -> assertFalse(validatedCustomers.get(0).getPhoneNumber().getIsValid()),
				  () -> assertTrue(validatedCustomers.get(1).getPhoneNumber().getIsValid()));

    }
	
}
