package com.hampcode.core.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.hampcode.core.dao.OrderRepository;
import com.hampcode.core.model.Order;
import com.hampcode.core.service.OrderService;

@Named
public class OrderServiceImpl implements OrderService,Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private OrderRepository orderRepository;

	@Transactional
	@Override
	public Integer insert(Order t) throws Exception {
		return orderRepository.insert(t);
	}

	@Override
	public Integer update(Order t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(Order t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order findById(Order t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> findOrder(String searchBy, String valueSearch) throws Exception {
		return orderRepository.findOrder(searchBy, valueSearch);
	}

}
