package com.hampcode.core.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.hampcode.core.dao.ProductRepository;
import com.hampcode.core.model.Product;


@Named
public class ProductRepositoryImpl implements ProductRepository,Serializable {
	

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "factPU")
	private EntityManager em;

	@Override
	public Integer insert(Product t) throws Exception {
		em.persist(t);
		return t.getId();
	}

	@Override
	public Integer update(Product t) throws Exception {
		em.merge(t);
		return t.getId();
	}

	@Override
	public Integer delete(Product t) throws Exception {
		em.remove(t);
		return 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> list() throws Exception {
		List<Product> products = new ArrayList<>();

		Query q = em.createQuery("SELECT p FROM Product p");
		products = (List<Product>) q.getResultList();
		return products;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Product findById(Product t) throws Exception {

		List<Product> products = new ArrayList<>();
		Query q = em.createQuery("FROM Product p where p.id = ?1");
		q.setParameter(1, t.getId());

		products = (List<Product>) q.getResultList();

		return products != null && !products.isEmpty() ? products.get(0) : new Product();
	}

}
