package com.hampcode.core.dao.impl;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.hampcode.core.dao.OrderRepository;
import com.hampcode.core.model.Order;
import com.hampcode.core.model.State;
import com.hampcode.core.util.Util;

@Named
public class OrderRepositoryImpl implements OrderRepository, Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "factPU")
	private EntityManager em;

	@Override
	public Integer insert(Order t) throws Exception {
		t.setState(State.EMITIDO.getCodeState());
		em.persist(t);
		return t.getId();
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
		List<Order> orders = new ArrayList<>();

		TypedQuery<Order> orderExits = null;

		if (searchBy.equals(Util.CLIENTE.toString())) {
			orderExits = em.createQuery("SELECT o FROM Order o WHERE o.customer.name like :name", Order.class);
			orderExits.setParameter("name", "%" + valueSearch + "%");
		} else if (searchBy.equals(Util.ESTADO.toString())) {
			int value = 0;
			if (valueSearch.equals(State.EMITIDO.toString())) {
				value = 1;
			} else if (valueSearch.equals(State.ELIMINADO.toString())) {
				value = 2;
			} else if (valueSearch.equals(State.ANULADO.toString())) {
				value = 3;
			}
			orderExits = em.createQuery("SELECT o FROM Order o WHERE o.state= :state", Order.class);
			orderExits.setParameter("state",value);
		} else {
			try {
				SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
				Date dateSearch = formatoFecha.parse(valueSearch);
				orderExits = em.createQuery("Select o from Order o where o.createAt >= :dateSearch", Order.class);
				orderExits.setParameter("dateSearch", dateSearch);
			} catch (ParseException e) {
				orders = new ArrayList<>();
			}
		}
		orders = orderExits.getResultList();

		return orders;
	}

}
