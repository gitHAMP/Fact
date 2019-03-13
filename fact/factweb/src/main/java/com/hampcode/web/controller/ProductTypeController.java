package com.hampcode.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import com.hampcode.core.model.ProductType;
import com.hampcode.core.service.ProductTypeService;
import com.hampcode.web.util.Message;

@Named
@ViewScoped
public class ProductTypeController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProductTypeService productTypeService;

	private ProductType typeProduct;
	private ProductType typeProductSelec;
	private List<ProductType> listTypeProduct;

	private String filter = "";

	@PostConstruct
	public void init() {
		typeProduct = new ProductType();
		typeProductSelec = new ProductType();
		loadTypeProduct();
	}

	public void loadTypeProduct() {
		try {
			this.listTypeProduct = productTypeService.list();
		} catch (Exception e) {
			Message.messageError("Error ProductoType :" + e.getMessage());
		}
	}

	public void saveProductType() {
		try {
			if (typeProduct.getId() != 0) {
				productTypeService.update(typeProduct);
				Message.messageInfo("Registro actualizado exitosamente");
			} else {
				productTypeService.insert(typeProduct);
				Message.messageInfo("Registro guardado exitosamente");

			}
			loadTypeProduct();
			resetForm();
		} catch (Exception e) {
			Message.messageError("Error ProductoType :" + e.getMessage());
		}
	}

	public void editProductType() {
		try {
			if (this.typeProductSelec!=null) {
				this.typeProduct = typeProductSelec;
			} else {
				Message.messageInfo("Debe seleccionar tipo de producto");
			}
		} catch (Exception e) {
			Message.messageError("Error ProductoType :" + e.getMessage());
		}

	}

	public void deleteProductType() {
		try {
			if (this.typeProductSelec != null) {
				productTypeService.delete(typeProductSelec);
				loadTypeProduct();
				resetForm();

			} else {

			}
		} catch (Exception e) {

		}
	}

	public void filterProductType() {
		try {
			this.listTypeProduct = productTypeService.findByName(filter);
			if(listTypeProduct.isEmpty()) {
				Message.messageInfo("No existe producto");
			}
		} catch (Exception e) {
			Message.messageError("Error ProductoType :" + e.getMessage());
		}
	}

	public void selecProductType(SelectEvent e) {
		this.typeProductSelec = (ProductType) e.getObject();
	}

	public void resetForm() {
		this.typeProduct = new ProductType();
		this.typeProductSelec = null;
	}

	public ProductType getTypeProduct() {
		return typeProduct;
	}

	public void setTypeProduct(ProductType typeProduct) {
		this.typeProduct = typeProduct;
	}

	public ProductType getTypeProductSelec() {
		return typeProductSelec;
	}

	public void setTypeProductSelec(ProductType typeProductSelec) {
		this.typeProductSelec = typeProductSelec;
	}

	public List<ProductType> getListTypeProduct() {
		return listTypeProduct;
	}

	public void setListTypeProduct(List<ProductType> listTypeProduct) {
		this.listTypeProduct = listTypeProduct;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

}
