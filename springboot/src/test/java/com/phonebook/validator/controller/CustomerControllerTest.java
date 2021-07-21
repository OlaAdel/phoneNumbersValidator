package com.phonebook.validator.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.phonebook.validator.service.CustomerService;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {
	
	
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    void should_InvokeFindCustomers_When_NoParameters() throws Exception {
	        mockMvc.perform(get("/customers"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json("[]"));

        verify(customerService, times(1)).findCustomers(null, null);
    }
    
    @Test
    void should_InvokeFindCustomers_When_CountryDoesExist() throws Exception {
        mockMvc.perform(get("/customers?country=Cameroon"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json("[]"));
                
        List<String> countries = new ArrayList<String>();
        countries.add("Cameroon");
        verify(customerService, times(1)).findCustomers(countries, null);
    }
    
    @Test
    void should_InvokeFindCustomers_When_CountriesDoExist() throws Exception {
        mockMvc.perform(get("/customers?country=Cameroon&country=Uganda"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json("[]"));
                
        List<String> countries = new ArrayList<String>();
        countries.add("Cameroon");
        countries.add("Uganda");

        verify(customerService, times(1)).findCustomers(countries, null);
    }
    
    @Test
    void should_InvokeFindCustomers_When_StateDoesExist() throws Exception {
        mockMvc.perform(get("/customers?state=false"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json("[]"));
                
        List<Boolean> states = new ArrayList<Boolean>();
        states.add(false);
        verify(customerService, times(1)).findCustomers(null, states);
    }
    
    @Test
    void should_InvokeFindCustomers_When_StatesDoExist() throws Exception {
        mockMvc.perform(get("/customers?state=false&state=true"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json("[]"));
                
        List<Boolean> states = new ArrayList<Boolean>();
        states.add(false);
        states.add(true);

        verify(customerService, times(1)).findCustomers(null, states);
    }

    
    @Test
    void should_InvokeFindCustomers_When_CountriesDoExist_StatesDoExist() throws Exception {
        mockMvc.perform(get("/customers?country=Cameroon&state=false"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json("[]"));

        List<String> countries = new ArrayList<String>();
        countries.add("Cameroon");
        
        List<Boolean> states = new ArrayList<Boolean>();
        states.add(false);
        verify(customerService, times(1)).findCustomers(countries, states);
    }

}
