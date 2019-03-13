package com.hampcode.core.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.mindrot.jbcrypt.BCrypt;

import com.hampcode.core.dao.UserRepository;
import com.hampcode.core.model.User;
import com.hampcode.core.service.UserService;

@Named
public class UserServiceImpl implements UserService,Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UserRepository userRepository;
	
	@Transactional
	@Override
	public Integer insert(User t) throws Exception {
		int rpta=userRepository.insert(t);
		return rpta>0?1:0;
	}

	@Transactional
	@Override
	public Integer update(User t) throws Exception {
		int rpta=userRepository.update(t);
		return rpta>0?1:0;
	}

	@Transactional
	@Override
	public Integer delete(User t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findById(User t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<User> login(User us) throws Exception{
		String password=us.getPassword();
		
		String passwordHash=userRepository.getPassHashed(us.getUsername());
		
		if(BCrypt.checkpw(password, passwordHash)) {
			us.setPassword(passwordHash);
			return userRepository.login(us);
		}
		return Optional.of(new User());
	}

}
