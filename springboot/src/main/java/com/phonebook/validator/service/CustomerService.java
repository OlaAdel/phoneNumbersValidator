package com.phonebook.validator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phonebook.validator.dao.CountryRepository;
import com.phonebook.validator.dao.CustomerRepository;
import com.phonebook.validator.model.Country;
import com.phonebook.validator.model.Customer;
import com.phonebook.validator.service.specification.CustomerFilter;
import com.phonebook.validator.service.specification.CustomerSpecification;
import com.phonebook.validator.util.CountryUtil;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired 
	CountryRepository countryRepo;
	
	public List<Customer> findCustomers(List<String> countries, List<Boolean> states) 
	{
		CustomerSpecification spec = new CustomerSpecification(new CustomerFilter(countries, states));	
		return customerRepo.findAll(spec);
	}
	
	public List<Customer> validateCustomersNumbers(List<Customer> customers) 
	{
		for(Customer c:customers)
		{	
			String CustomerPhoneNumberCountryCode = Country.extractCountryCodeFromPhoneNumber(c.getPhoneNumber());	
			if(CountryUtil.isCodeExist(CustomerPhoneNumberCountryCode))
			{
				Country CustomerPhoneNumberCountry = CountryUtil.getCountryByCode(CustomerPhoneNumberCountryCode);
				c.getPhoneNumber().setCountry(CustomerPhoneNumberCountry);
				c.getPhoneNumber().ValidateNumber();
			}
		}
		return customers;
	}	
	
	public List<Customer> ValidateDatabase() 
	{			
		List<Customer> databaseCustomers = customerRepo.findAll();
		List<Customer> validatedCustomer = validateCustomersNumbers(databaseCustomers);
		for(Customer c:validatedCustomer)
		{	
			Country CustomerPhoneNumberCountry = countryRepo.findByCode(c.getPhoneNumber().getCountry().getCode());
		
			if(CustomerPhoneNumberCountry == null)
				countryRepo.save(c.getPhoneNumber().getCountry());
			else
				c.getPhoneNumber().setCountry(CustomerPhoneNumberCountry);
			
			customerRepo.save(c);
		}
		return validatedCustomer;
	}	
}