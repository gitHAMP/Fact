package com.hampcode.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.hampcode.core.model.User;
import com.hampcode.core.model.UserRol;
import com.hampcode.core.service.RolService;

@Named
@ViewScoped
public class MasterController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private RolService rolService;

	public void checkSesion() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			User us = (User) context.getExternalContext().getSessionMap().get("user");

			if (us == null) {
				context.getExternalContext().redirect("./../index.xhtml");
			} else {
				// verificacion de roles
				String viewId = context.getViewRoot().getViewId();
				boolean rpta = this.checkMenu(viewId);

				if (!rpta) {
					context.getExternalContext().redirect("./../403.xhtml");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public boolean checkMenu(String viewId) {
		FacesContext context = FacesContext.getCurrentInstance();
		User us = (User) context.getExternalContext().getSessionMap().get("user");

		try {
			List<UserRol> roles = rolService.listRolesByUser(us);
			String rol = "";

			switch (viewId) {
			case "/protected/asigns.xhtml":
				rol = "ADMIN";
				break;
			case "/protected/customers.xhtml":
				rol = "ADMIN,USER";
				break;
			case "/protected/products.xhtml":
				rol = "ADMIN";
				break;
			case "/protected/producttypes.xhtml":
				rol = "ADMIN";
				break;
			case "/protected/roles.xhtml":
				rol = "ADMIN";
				break;
			case "/protected/users.xhtml":
				rol = "ADMIN";
				break;
			case "/protected/orders.xhtml":
				rol = "ADMIN,USER";
				break;
			default:
				break;
			}

			String arreglo[] = rol.split(",");

			int[] iarr = { 0 };

			roles.forEach(r -> {
				for (String i : arreglo) {
					if (r.getRolId().getType().equals(i)) {
						iarr[0]++;
					}
				}

			});

			if (iarr[0] == 0) {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}
	
	public void signOut() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}

}
