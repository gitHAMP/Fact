package com.hampcode.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.mindrot.jbcrypt.BCrypt;

import com.hampcode.core.model.Customer;
import com.hampcode.core.model.Rol;
import com.hampcode.core.model.User;
import com.hampcode.core.service.CustomerService;
import com.hampcode.core.service.RolService;

@Named
@ViewScoped
public class RegisterController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CustomerService customerService;

	@Inject
	private RolService rolService;

	private Customer customer;
	private User user;

	@PostConstruct
	public void init() {
		this.customer = new Customer();
		this.user = new User();
	}

	public String registerUser() {
		String redirect=null;
		
		try {
			String password=this.user.getPassword();
			String passwordHash=BCrypt.hashpw(password, BCrypt.gensalt());
			this.user.setPassword(passwordHash);
			this.user.setState("A");
			this.customer.setUs(this.user);
			this.user.setCustomer(this.customer);
			this.customerService.insert(this.customer);
			
			List<Rol> roles=new ArrayList<>();
			int idUser=1;
			Rol rol=new Rol();
			rol.setId(idUser);
			roles.add(rol);
			rolService.assign(this.user, roles);
			redirect="index?faces-redirect=true";
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return redirect;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
