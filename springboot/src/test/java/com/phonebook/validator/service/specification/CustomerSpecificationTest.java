package com.phonebook.validator.service.specification;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.phonebook.validator.dao.CountryRepository;
import com.phonebook.validator.dao.CustomerRepository;
import com.phonebook.validator.model.Country;
import com.phonebook.validator.model.Customer;
import com.phonebook.validator.model.PhoneNumber;

@SpringBootTest
class CustomerSpecificationTest {
	
	@Autowired 
	CustomerRepository customerRepo;
	
	@Autowired 
	CountryRepository countryRepo;

	List<Customer> customerTestList = new ArrayList<Customer>(
	          Arrays.asList(new Customer("SUGAR STARRK BARRAGAN",new PhoneNumber("(237) 6780009592",new Country("Cameroon","+237", "\\(237\\)\\ ?[2368]\\d{7,8}$"),false)),
			          		new Customer("Yonatan Tekelay",new PhoneNumber("(251) 911168450",new Country("Ethiopia","+251", "\\\\(251\\\\)\\\\ ?[1-59]\\\\d{8}$"), true))));
	List<Country> customercountriesTestList = new ArrayList<Country>(
	          Arrays.asList(new Country("Cameroon","+237", "\\(237\\)\\ ?[2368]\\d{7,8}$"),
	        		  		new Country("Ethiopia","+251", "\\\\(251\\\\)\\\\ ?[1-59]\\\\d{8}$")));
	@BeforeEach
	public void setUp() {
		customerRepo.deleteAllInBatch();		
		countryRepo.deleteAllInBatch();
						
		customerRepo.save(customerTestList.get(0));
		customerRepo.save(customerTestList.get(1));
	}

	@Test
	void should_Return_filteredCustomers_when_OneCountryGiven() {
		
        List<String> countriesFilter = new ArrayList<String>();
        countriesFilter.add("Cameroon");
		CustomerSpecification spec = new CustomerSpecification(new CustomerFilter(countriesFilter, null));

		List<Customer> filteredCustomers = customerRepo.findAll(spec);
		assertAll( () -> assertEquals(filteredCustomers.size(), 1),
				   () -> assertEquals(filteredCustomers.get(0).getName(), customerTestList.get(0).getName()));
	}
	
	@Test
	void should_Return_filteredCustomers_when_MultipleCountriesGiven() {
		
        List<String> countriesFilter = new ArrayList<String>();
        countriesFilter.add("Cameroon");
        countriesFilter.add("Ethiopia");

		CustomerSpecification spec = new CustomerSpecification(new CustomerFilter(countriesFilter, null));

		List<Customer> filteredCustomers = customerRepo.findAll(spec);
		assertAll( () -> assertEquals(filteredCustomers.size(), 2));
	}
	
	@Test
	void should_Return_filteredCustomers_when_OneStateGiven() {
		
        List<Boolean> statesFilter = new ArrayList<Boolean>();
        statesFilter.add(true);

		CustomerSpecification spec = new CustomerSpecification(new CustomerFilter(null,statesFilter));
		List<Customer> filteredCustomers = customerRepo.findAll(spec);
		assertAll( () -> assertEquals(filteredCustomers.size(), 1),
				   () -> assertEquals(filteredCustomers.get(0).getName(), customerTestList.get(1).getName()));
	}
	
	@Test
	void should_Return_filteredCustomers_when_MultipleStatesGiven() {
		
        List<Boolean> statesFilter = new ArrayList<Boolean>();
        statesFilter.add(true);
        statesFilter.add(false);


		CustomerSpecification spec = new CustomerSpecification(new CustomerFilter(null,statesFilter));
		List<Customer> filteredCustomers = customerRepo.findAll(spec);
		
		assertAll( () -> assertEquals(filteredCustomers.size(), 2));
	}
	
	@Test
	void should_Return_filteredCustomers_when_CountriesStatesGiven() {
		
        List<Boolean> statesFilter = new ArrayList<Boolean>();
        statesFilter.add(true);
        
        List<String> countriesFilter = new ArrayList<String>();
        countriesFilter.add("Uganda");


		CustomerSpecification spec = new CustomerSpecification(new CustomerFilter(countriesFilter,statesFilter));
		List<Customer> filteredCustomers = customerRepo.findAll(spec);

		assertAll( () -> assertEquals(filteredCustomers.size(), 0));
	}

}
