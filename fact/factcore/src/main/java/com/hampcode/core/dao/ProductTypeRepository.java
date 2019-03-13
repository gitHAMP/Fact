package com.hampcode.core.dao;

import java.util.List;

import com.hampcode.core.model.ProductType;

public interface ProductTypeRepository extends CrudRepository<ProductType> {

	List<ProductType> findByName(String name) throws Exception ;
}
