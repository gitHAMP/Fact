package com.hampcode.core.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.hampcode.core.dao.RolRepository;
import com.hampcode.core.model.Rol;
import com.hampcode.core.model.User;
import com.hampcode.core.model.UserRol;

@Named
public class RolRepositoryImpl implements RolRepository, Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "factPU")
	private EntityManager em;

	@Override
	public Integer insert(Rol t) throws Exception {
		em.persist(t);
		return t.getId();
	}

	@Override
	public Integer update(Rol t) throws Exception {
		em.merge(t);
		return t.getId();
	}

	@Override
	public Integer delete(Rol t) throws Exception {
		em.remove(t);
		return 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rol> list() throws Exception {
		List<Rol> listRol = new ArrayList<>();

		Query q = em.createQuery("SELECT r FROM Rol r");
		listRol = (List<Rol>) q.getResultList();
		return listRol;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Rol findById(Rol t) throws Exception {
		
		List<Rol> listRol = new ArrayList<>();
		Query query = em.createQuery("FROM Rol r where r.id = ?1");
		query.setParameter(1, t.getId());

		listRol = (List<Rol>) query.getResultList();

		// return rol;
		return listRol != null && !listRol.isEmpty() ? listRol.get(0) : new Rol();
	}

	@Override
	public Integer assign(User us, List<UserRol> roles) throws Exception {
		/*Query query = em.createNativeQuery("DELETE FROM users_roles ur where ur.user_id =?1");
		query.setParameter(1, us.getCustomer().getId());
		query.executeUpdate();*/

		int[] iarr = { 0 };
		roles.forEach(r -> {
			em.persist(r);
			if (iarr[0] % 100 == 0) {
				em.flush();
				em.clear();
			}
			iarr[0]++;
		});
		return 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRol> listRolesByUser(User us) throws Exception {
		List<UserRol> listUserRol = new ArrayList<UserRol>();

		Query query = em.createQuery("FROM UserRol ur where ur.userId.customer.id =?1");
		query.setParameter(1, us.getCustomer().getId());

		listUserRol = (List<UserRol>) query.getResultList();

		return listUserRol;
	}

}
