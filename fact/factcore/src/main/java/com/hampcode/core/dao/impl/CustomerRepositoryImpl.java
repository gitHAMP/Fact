package com.hampcode.core.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.hampcode.core.dao.CustomerRepository;
import com.hampcode.core.model.Customer;

@Named
public class CustomerRepositoryImpl implements CustomerRepository, Serializable {

	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "factPU")
	private EntityManager em;

	@Override
	public Integer insert(Customer t) throws Exception {
		em.persist(t);
		return t.getId();
	}

	@Override
	public Integer update(Customer t) throws Exception {
		em.merge(t);
		if (t.getPhoto() != null && t.getPhoto().length > 0) {
			Query query = em.createQuery("UPDATE Customer SET photo = ?1 WHERE id = ?2");
			query.setParameter(1, t.getPhoto());
			query.setParameter(2, t.getId());
			query.executeUpdate();
		}
		return t.getId();
	}

	@Override
	public Integer delete(Customer t) throws Exception {
		em.remove(t);
		return 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> list() throws Exception {
		List<Customer> listCustomers = new ArrayList<>();

		Query q = em.createQuery("SELECT c FROM Customer c");
		listCustomers = (List<Customer>) q.getResultList();
		return listCustomers;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Customer findById(Customer t) throws Exception {
		List<Customer> customers = new ArrayList<>();
		Query q = em.createQuery("FROM Customer c where c.id = ?1");
		q.setParameter(1, t.getId());

		customers = (List<Customer>) q.getResultList();

		return customers != null && !customers.isEmpty() ? customers.get(0) : new Customer();
	}

	@Override
	public List<Customer> findByName(String name) throws Exception {
		return em.createQuery("from Customer where name like :name", Customer.class)
				.setParameter("name", "%" + name + "%").getResultList();
	}

	@Override
	public Optional<Customer> findByDni(String dni) throws Exception {
		Customer customer;
		TypedQuery<Customer> customerExist = em.createQuery("Select c from Customer c  where c.dni =:dni",
				Customer.class);
		customerExist.setParameter("dni", dni);
		customer = customerExist.getSingleResult();

		return Optional.of(customer);
	}

}
