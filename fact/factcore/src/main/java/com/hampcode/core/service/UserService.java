package com.hampcode.core.service;



import java.util.Optional;

import com.hampcode.core.model.User;

public interface UserService extends CrudService<User> {

	Optional<User> login(User us) throws Exception;
}
