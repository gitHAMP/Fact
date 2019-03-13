package com.hampcode.web.controller;

import java.io.Serializable;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import com.hampcode.core.model.Product;
import com.hampcode.core.model.ProductType;
import com.hampcode.core.service.ProductService;
import com.hampcode.core.service.ProductTypeService;
import com.hampcode.web.util.Message;

@Named
@ViewScoped
public class ProductController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProductTypeService productTypeService;

	@Inject
	private ProductService productService;

	private Product product;
	private Product productSel;
	private List<Product> products;

	private ProductType productType;
	private List<ProductType> productTypes;

	@PostConstruct
	public void init() {
		product = new Product();
		productSel = new Product();
		//products = new ArrayList<>();

//		/productType = new ProductType();
		//productTypes = new ArrayList<>();

		this.loadProduct();
		this.loadTypeProduct();
	}

	public void loadTypeProduct() {
		try {
			this.productTypes = productTypeService.list();
		} catch (Exception e) {

		}
	}

	public void loadProduct() {
		try {
			this.products = productService.list();
		} catch (Exception e) {

		}
	}

	public void saveProduct() {
		try {
			if (product.getId() != 0) {
				product.setProductType(productType);
				productService.update(product);
				Message.messageInfo("Registro actualizado exitosamente");
			} else {
				product.setProductType(productType);
				productService.insert(product);
				Message.messageInfo("Registro guardado exitosamente");

			}
			loadProduct();
			resetForm();
		} catch (Exception e) {
			Message.messageError("Error ProductoType :" + e.getMessage());
		}
	}

	public void editProduct() {
		try {
			if (this.productSel.getId() >0) {
				this.product = this.productSel;
				// this.product.setProductType(this.productSel.getProductType());
			} else {
				Message.messageInfo("Debe seleccionar un  producto");
			}
		} catch (Exception e) {
			Message.messageError("Error Product :" + e.getMessage());
		}

	}

	public void deleteProduct() {
		try {
			if (this.productSel != null) {
				productService.delete(productSel);
				loadProduct();
				resetForm();

			} else {

			}
		} catch (Exception e) {

		}
	}

	public void selecProduct(SelectEvent e) {
		this.productSel = (Product) e.getObject();
	}

	public void resetForm() {
		this.product = new Product();
		this.productSel = null;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Product getProductSel() {
		return productSel;
	}

	public void setProductSel(Product productSel) {
		this.productSel = productSel;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<ProductType> getProductTypes() {
		return productTypes;
	}

	public void setProductTypes(List<ProductType> productTypes) {
		this.productTypes = productTypes;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

}
