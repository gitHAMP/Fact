package com.hampcode.core.service;

import java.util.List;

import com.hampcode.core.model.Order;

public interface OrderService extends CrudService<Order> {
	public List<Order> findOrder(String searchBy,String valueSearch) throws Exception;
}
