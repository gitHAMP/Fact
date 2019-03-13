package com.hampcode.core.dao;



import java.util.Optional;

import com.hampcode.core.model.User;

public interface UserRepository extends CrudRepository<User> {

	String getPassHashed(String us) throws Exception;

	Optional<User> login(User us) throws Exception;
}
