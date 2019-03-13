package com.hampcode.web.controller;

import java.io.Serializable;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.hampcode.core.model.User;
import com.hampcode.core.service.UserService;
import com.hampcode.web.util.Message;

@Named
@ViewScoped
public class IndexController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UserService userService;
	private User user;

	@PostConstruct
	public void init() {
		this.user = new User();
	}

	public String login() {
		String redirect = null;
		try {
			Optional<User> us = this.userService.login(this.user);

			if (us.isPresent() && us.get().getState().equalsIgnoreCase("A")) {

				// Almacenar en la sesi�n de JSF
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", us);
				redirect = "/protected/roles?faces-redirect=true";
			} else {
				Message.messageError("Credenciales incorrectas");
				//FacesContext.getCurrentInstance().addMessage(null,
				//		new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Credenciales incorrectas"));
			}

		} catch (Exception e) {
			//FacesContext.getCurrentInstance().addMessage(null,
			//		new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Credenciales incorrectas"));
			Message.messageError("Credenciales incorrectas");

		}
		return redirect;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
