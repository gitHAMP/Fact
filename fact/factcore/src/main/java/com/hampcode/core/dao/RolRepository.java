package com.hampcode.core.dao;

import java.util.List;

import com.hampcode.core.model.Rol;
import com.hampcode.core.model.User;
import com.hampcode.core.model.UserRol;

public interface RolRepository extends CrudRepository<Rol> {

	Integer assign(User us, List<UserRol> roles) throws Exception;

	List<UserRol> listRolesByUser(User us) throws Exception;
}
