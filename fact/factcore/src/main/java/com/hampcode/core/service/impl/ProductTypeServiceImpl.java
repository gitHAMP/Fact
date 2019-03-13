package com.hampcode.core.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.hampcode.core.dao.ProductTypeRepository;
import com.hampcode.core.model.ProductType;
import com.hampcode.core.service.ProductTypeService;

@Named
public class ProductTypeServiceImpl implements ProductTypeService, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProductTypeRepository productTypeRepository;

	@Transactional
	@Override
	public Integer insert(ProductType t) throws Exception {
		return productTypeRepository.insert(t);
	}

	@Transactional
	@Override
	public Integer update(ProductType t) throws Exception {
		return productTypeRepository.update(t);
	}

	@Transactional
	@Override
	public Integer delete(ProductType t) throws Exception {
		return productTypeRepository.delete(t);
	}

	@Override
	public List<ProductType> list() throws Exception {
		return productTypeRepository.list();
	}

	@Override
	public ProductType findById(ProductType t) throws Exception {
		return productTypeRepository.findById(t);
	}

	@Override
	public List<ProductType> findByName(String name) throws Exception {
		return productTypeRepository.findByName(name);
	}

}
