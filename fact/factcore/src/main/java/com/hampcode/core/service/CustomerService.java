package com.hampcode.core.service;

import java.util.List;
import java.util.Optional;

import com.hampcode.core.model.Customer;


public interface CustomerService extends CrudService<Customer> {

	List<Customer> findByName(String name) throws Exception ;
	
	Optional<Customer> findByDni(String dni)throws Exception ;
}
