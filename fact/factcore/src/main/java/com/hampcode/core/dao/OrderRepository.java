package com.hampcode.core.dao;

import java.util.List;

import com.hampcode.core.model.Order;

public interface OrderRepository extends CrudRepository<Order> {
	
	public List<Order> findOrder(String searchBy,String valueSearch) throws Exception;

}
