package com.hampcode.core.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.hampcode.core.dao.ProductRepository;
import com.hampcode.core.model.Product;
import com.hampcode.core.service.ProductService;

@Named
public class ProductServiceImpl implements ProductService, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProductRepository productRepository;

	@Transactional
	@Override
	public Integer insert(Product t) throws Exception {
		return productRepository.insert(t);
	}

	@Transactional
	@Override
	public Integer update(Product t) throws Exception {
		return productRepository.update(t);
	}

	@Transactional
	@Override
	public Integer delete(Product t) throws Exception {
		return productRepository.delete(t);
	}

	@Override
	public List<Product> list() throws Exception {
		return productRepository.list();
	}

	@Override
	public Product findById(Product t) throws Exception {
		return productRepository.findById(t);
	}

}
