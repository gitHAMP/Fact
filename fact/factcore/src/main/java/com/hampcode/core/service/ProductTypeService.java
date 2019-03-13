package com.hampcode.core.service;

import java.util.List;

import com.hampcode.core.model.ProductType;

public interface ProductTypeService extends CrudService<ProductType> {

	public List<ProductType> findByName(String name) throws Exception;
}
