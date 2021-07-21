package com.phonebook.validator.service.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.phonebook.validator.model.Customer;

@SuppressWarnings("serial")
public class CustomerSpecification implements Specification<Customer> {

    private CustomerFilter criteria;
   
	public CustomerSpecification(CustomerFilter criteria) {
		this.criteria = criteria;
	}     
	
	public CustomerFilter getCriteria() {
		return criteria;
	}

	public void setCriteria(CustomerFilter criteria) {
		this.criteria = criteria;
	}
	
	@Override
    public Predicate toPredicate (Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		
        final List<Predicate> predicates = new ArrayList<Predicate>();
     
        if(criteria.getPhoneNumberState() != null)
        	predicates.add(builder.in(root.get("phoneNumber").get("isValid")).value(criteria.getPhoneNumberState()));    
        
        if(criteria.getPhoneNumbercountries() != null)
        	predicates.add(builder.in(root.get("phoneNumber").get("country").get("name")).value(criteria.getPhoneNumbercountries()));    
        
        return builder.and(predicates.toArray(new Predicate[predicates.size()]));   
    }
}
