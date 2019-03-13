package com.hampcode.core.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.hampcode.core.dao.ProductTypeRepository;
import com.hampcode.core.model.ProductType;


@Named
public class ProductTypeRepositoryImpl implements ProductTypeRepository, Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "factPU")
	private EntityManager em;

	@Override
	public Integer insert(ProductType t) throws Exception {
		em.persist(t);
		return t.getId();
	}

	@Override
	public Integer update(ProductType t) throws Exception {
		em.merge(t);
		return t.getId();
	}

	@Override
	public Integer delete(ProductType t) throws Exception {
		em.remove(t);
		return 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductType> list() throws Exception {
		List<ProductType> listProductTypes = new ArrayList<>();

		Query q = em.createQuery("SELECT p FROM ProductType p");
		listProductTypes = (List<ProductType>) q.getResultList();
		return listProductTypes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ProductType findById(ProductType t) throws Exception {
		
		List<ProductType> productTypes = new ArrayList<>();
		Query q = em.createQuery("FROM ProductType p where p.id = ?1");
		q.setParameter(1, t.getId());

		productTypes = (List<ProductType>) q.getResultList();

		return productTypes != null && !productTypes.isEmpty() ? productTypes.get(0) : new ProductType();
	}

	@Override
	public List<ProductType> findByName(String name) throws Exception {
		return em.createQuery("from ProductType where name like :name", ProductType.class)
				.setParameter("name", "%" + name.toUpperCase() + "%")
				.getResultList();
	}

}
