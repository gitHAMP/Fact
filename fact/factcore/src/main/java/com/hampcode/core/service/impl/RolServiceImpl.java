package com.hampcode.core.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.hampcode.core.dao.RolRepository;
import com.hampcode.core.model.Rol;
import com.hampcode.core.model.User;
import com.hampcode.core.model.UserRol;
import com.hampcode.core.service.RolService;

@Named
public class RolServiceImpl implements RolService, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private RolRepository rolRepository;

	@Transactional
	@Override
	public Integer insert(Rol t) throws Exception {
		return rolRepository.insert(t);
	}

	@Transactional
	@Override
	public Integer update(Rol t) throws Exception {
		return rolRepository.update(t);
	}

	@Transactional
	@Override
	public Integer delete(Rol t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public List<Rol> list() throws Exception {
		return rolRepository.list();
	}

	@Override
	public Rol findById(Rol t) throws Exception {
		return rolRepository.findById(t);
	}

	@Transactional
	@Override
	public Integer assign(User us, List<Rol> roles) throws Exception {
		List<UserRol> users_roles = new ArrayList<>();

		roles.forEach(rol -> {
			UserRol ur = new UserRol();
			ur.setUserId(us);
			ur.setRolId(rol);
			users_roles.add(ur);
		});

		rolRepository.assign(us, users_roles);

		return 1;
	}

	@Override
	public List<UserRol> listRolesByUser(User us) throws Exception {
		return rolRepository.listRolesByUser(us);
	}

}
