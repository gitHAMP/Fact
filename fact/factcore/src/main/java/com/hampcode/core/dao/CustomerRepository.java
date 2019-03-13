package com.hampcode.core.dao;

import java.util.List;
import java.util.Optional;

import com.hampcode.core.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer> {

	List<Customer> findByName(String name) throws Exception ;
	
	Optional<Customer> findByDni(String dni)throws Exception ;
}
