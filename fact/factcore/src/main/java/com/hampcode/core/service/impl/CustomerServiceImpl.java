package com.hampcode.core.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.hampcode.core.dao.CustomerRepository;
import com.hampcode.core.model.Customer;
import com.hampcode.core.service.CustomerService;

@Named
public class CustomerServiceImpl implements CustomerService,Serializable {


	private static final long serialVersionUID = 1L;
	
	@Inject
	private CustomerRepository customerRepository;

	@Transactional
	@Override
	public Integer insert(Customer t) throws Exception {
		return customerRepository.insert(t);
	}

	@Transactional
	@Override
	public Integer update(Customer t) throws Exception {
		return customerRepository.update(t);
	}

	@Transactional
	@Override
	public Integer delete(Customer t) throws Exception {
		return customerRepository.delete(t);
	}

	@Override
	public List<Customer> list() throws Exception {
		return customerRepository.list();
	}

	@Override
	public Customer findById(Customer t) throws Exception {
		return customerRepository.findById(t);
	}

	@Override
	public List<Customer> findByName(String name)throws Exception {
		return customerRepository.findByName(name);
	}

	@Override
	public Optional<Customer> findByDni(String dni) throws Exception {
		return customerRepository.findByDni(dni);
	}

}
