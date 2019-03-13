package com.hampcode.core.service;

import java.util.List;

import com.hampcode.core.model.Rol;
import com.hampcode.core.model.User;
import com.hampcode.core.model.UserRol;

public interface RolService extends CrudService<Rol> {

	Integer assign(User us, List<Rol> roles) throws Exception;

	List<UserRol> listRolesByUser(User us) throws Exception;
}
