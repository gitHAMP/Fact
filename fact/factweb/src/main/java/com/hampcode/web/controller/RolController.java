package com.hampcode.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


import org.primefaces.event.SelectEvent;

import com.hampcode.core.model.Rol;
import com.hampcode.core.service.RolService;
import com.hampcode.web.util.Message;

@Named
@ViewScoped
public class RolController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private RolService rolService;
	
	private Rol rol;
	private Rol rolSelec;
	private List<Rol> roles;

	
	@PostConstruct
	public void init() {
		this.rol = new Rol();
		this.rolSelec=new Rol();
		this.loadRoles();
	}

	
	public void loadRoles() {
		try {
			this.roles = rolService.list();
		} catch (Exception e) {
			Message.messageError("Error ProductoType :" + e.getMessage());
		}
	}

	public void saveRole() {
		try {
			if (rol.getId() != 0) {
				rolService.update(rol);
				Message.messageInfo("Registro actualizado exitosamente");
			} else {
				rolService.insert(rol);
				Message.messageInfo("Registro guardado exitosamente");

			}
			loadRoles();
			resetForm();
		} catch (Exception e) {
			Message.messageError("Error Rol :" + e.getMessage());
		}
	}
	
	public void editRol() {
		try {
			if (this.rolSelec.getId()>0) {
				this.rol = rolSelec;
			} else {
				Message.messageInfo("Debe seleccionar un rol");
			}
		} catch (Exception e) {
			Message.messageError("Error Rol :" + e.getMessage());
		}

	}

	public void selecRol(SelectEvent e) {
		this.rolSelec = (Rol) e.getObject();
	}
	
	public void resetForm() {
		this.rol = new Rol();
		this.rolSelec = null;
	}
	
	public Rol getRol() {
		return rol;
	}


	public void setRol(Rol rol) {
		this.rol = rol;
	}


	public Rol getRolSelec() {
		return rolSelec;
	}


	public void setRolSelec(Rol rolSelec) {
		this.rolSelec = rolSelec;
	}


	public List<Rol> getRoles() {
		return roles;
	}


	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}
	
	
	
	
	/*public void listRoles() {
		try {
			roles = rolService.list();
		} catch (Exception e) {

		}
	}

	public void saveOrUpdate(String accion) {
		try {
			if (accion.equalsIgnoreCase("R")) {
				this.rolService.insert(this.rol);
			} else if (accion.equalsIgnoreCase("M")) {
				this.rolService.update(this.rol);
			}
			this.listRoles();
		} catch (Exception e) {

		} finally {
			// this.limpiarControles();
		}
	}

	public void onRowEdit(RowEditEvent event) {
		try {
			this.rolService.update((Rol) event.getObject());
			FacesMessage msg = new FacesMessage("Se modificó", ((Rol) event.getObject()).getType());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {

		}
	}

	public void viewRol(Rol rol) {
		this.rol = rol;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}*/

}
