package com.phonebook.validator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phonebook.validator.model.Customer;
import com.phonebook.validator.service.CustomerService;

@RestController
@CrossOrigin
public class CustomerController {
	

	@Autowired
	CustomerService customerService;
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> validateOldDatabase(@RequestParam(name = "country", required = false) List<String> countries, @RequestParam(name="state", required = false) List<Boolean> states) {
		
	    List<Customer> customers = customerService.findCustomers(countries, states);
	    return new ResponseEntity<>(customers, HttpStatus.OK);
	}
	
	

}
