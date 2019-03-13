package com.hampcode.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.mindrot.jbcrypt.BCrypt;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;

import com.hampcode.core.model.Customer;
import com.hampcode.core.model.Rol;
import com.hampcode.core.model.User;
import com.hampcode.core.service.CustomerService;
import com.hampcode.core.service.RolService;
import com.hampcode.web.util.Message;

@Named
@ViewScoped
public class CustomerController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CustomerService customerService;

	private Customer customer;
	private Customer customerSele;

	private List<Customer> customers;

	private UploadedFile photo;

	@Inject
	private RolService rolService;

	private User user;

	@PostConstruct
	public void init() {
		customer = new Customer();
		customerSele = new Customer();
		customers = new ArrayList<>();
		this.user = new User();
		this.listCustomers();
	}

	public void listCustomers() {
		try {
			customers = customerService.list();
		} catch (Exception e) {
			Message.messageError("Error Customer :" + e.getMessage());
		}
	}

	public void findCustomer() {
		try {
			this.customers = customerService.findByName(this.customer.getName());
			if (customers.isEmpty()) {
				Message.messageInfo("No existe cliente");
			}
		} catch (Exception e) {
			Message.messageError("Error Customer :" + e.getMessage());
		}
	}

	public void saveCustomer() {
		try {
			if (photo != null) {
				this.customer.setPhoto(this.photo.getContents());
			}

			if (customer.getId() != 0) {
				customerService.update(customer);
				Message.messageInfo("Registro actualizado exitosamente");
			} else {

				customerService.insert(customer);
				Message.messageInfo("Registro guardado exitosamente");

			}
			listCustomers();
			resetForm();
		} catch (Exception e) {
			Message.messageError("Error Customer :" + e.getMessage());
		}
	}

	public void editCustomer() {
		try {
			System.out.println("Customer Selec:" + customerSele.getName());
			if (this.customerSele.getId() > 0) {
				this.customer = customerSele;
				this.user = customerSele.getUs();
			} else {
				Message.messageInfo("Debe seleccionar un cliente");
				System.out.println("Hola:");
			}

		} catch (Exception e) {
			Message.messageError("Error Cliente :" + e.getMessage());
		}

	}

	public void resetForm() {
		this.customer = new Customer();
		this.customerSele = null;
	}

	public void selecCustomer(SelectEvent e) {
		this.customerSele = (Customer) e.getObject();
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Customer getCustomerSele() {
		return customerSele;
	}

	public void setCustomerSele(Customer customerSele) {
		this.customerSele = customerSele;
	}

	public UploadedFile getPhoto() {
		return photo;
	}

	public void setPhoto(UploadedFile photo) {
		this.photo = photo;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
