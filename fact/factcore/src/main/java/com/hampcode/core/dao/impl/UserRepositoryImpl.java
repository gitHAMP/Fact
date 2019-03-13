package com.hampcode.core.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.hampcode.core.dao.UserRepository;
import com.hampcode.core.model.User;

@Named
public class UserRepositoryImpl implements UserRepository, Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "factPU")
	private EntityManager em;

	@Override
	public Integer insert(User t) throws Exception {
		em.persist(t);
		return t.getCustomer().getId();
	}

	@Override
	public Integer update(User t) throws Exception {
		em.merge(t);
		return t.getCustomer().getId();
	}

	@Override
	public Integer delete(User t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> list() throws Exception {
		List<User> listUser = new ArrayList<>();

		Query query = em.createQuery("SELECT c FROM User c");
		listUser = (List<User>) query.getResultList();

		return listUser;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User findById(User t) throws Exception {
		User u = new User();
		List<User> listUser = new ArrayList<>();

		Query query = em.createQuery("FROM User c WHERE c.id=?1");
		query.setParameter(1, t.getCustomer().getId());

		listUser = (List<User>) query.getResultList();

		if (listUser != null && !listUser.isEmpty()) {
			u = listUser.get(0);
		}

		return u;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getPassHashed(String us) throws Exception {
		User u = new User();
		List<User> listUser = new ArrayList<>();

		Query query = em.createQuery("FROM User u WHERE u.username=?1");
		query.setParameter(1, us);

		listUser = (List<User>) query.getResultList();

		if (!listUser.isEmpty()) {
			u = listUser.get(0);
		}

		return u != null ? u.getPassword() : "";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Optional<User> login(User us) throws Exception {
		User u = new User();
		List<User> listUser = new ArrayList<>();
		try {

			Query query = em.createQuery("FROM User u WHERE u.username=?1 and u.password=?2");
			query.setParameter(1, us.getUsername());
			query.setParameter(2, us.getPassword());

			listUser = (List<User>) query.getResultList();

			if (!listUser.isEmpty()) {
				u = listUser.get(0);
			}

		} catch (Exception e) {
			
		}

		return Optional.of(u);
	}

}
