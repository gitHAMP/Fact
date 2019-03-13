package com.hampcode.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

import org.primefaces.event.SelectEvent;

import com.hampcode.core.model.Customer;
import com.hampcode.core.model.DetailOrder;
import com.hampcode.core.model.Order;
import com.hampcode.core.model.Product;
import com.hampcode.core.service.CustomerService;
import com.hampcode.core.service.OrderService;
import com.hampcode.core.service.ProductService;
import com.hampcode.web.util.Message;


@Named
@ViewScoped
public class OrderController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private OrderService orderService;

	@Inject
	private CustomerService customerService;

	@Inject
	private ProductService productService;

	private Order order;
	private Order orderSel;
	private List<Order> orders;

	private DetailOrder detailOrder;
	private DetailOrder detailOrderSel;
	private List<DetailOrder> detailOrders;

	private Customer customer;

	private Product product;
	private List<Product> products;

	private String searchBy;
	private String valueSearch;

	@PostConstruct
	public void init() {
		order = new Order();
		orderSel = new Order();
		orders = new ArrayList<>();

		detailOrder = new DetailOrder();
		detailOrderSel = new DetailOrder();
		detailOrders = new ArrayList<>();

		customer = new Customer();
		product = new Product();
		products = new ArrayList<>();

		this.loadProduct();
		this.loadOrders();
	}

	public void findCustomerByDni() {
		try {

			Optional<Customer> customerExist = customerService.findByDni(customer.getDni());

			if (customerExist.isPresent()) {
				customer = customerExist.get();
			} 
		}catch(NoResultException e) {
			Message.messageInfo("Cliente no existe");
			resetForm();
		}catch (Exception e) {
			Message.messageError("Error Order :" + e.getMessage());
		}
	}

	public void addDetail() {
		detailOrder.setOrderId(order);
		detailOrder.setProduct(product);

		detailOrders.add(detailOrder);

		cleanDetail();
	}

	public void loadProduct() {
		try {
			this.products = productService.list();
		} catch (Exception e) {

		}
	}

	public List<Product> autoCompleteProduct(String query) {

		List<Product> productsFilter = new ArrayList<Product>();

		try {
			List<Product> products = productService.list();// adminProducto.consultarTodos();
			for (int i = 0; i < products.size(); i++) {
				Product proTmp = products.get(i);

				if (proTmp.getName().startsWith(query)) {
					System.out.println("Query:" + query);
					System.out.println("Product Filter:" + proTmp);
					productsFilter.add(proTmp);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return productsFilter;
	}

	public void updatePriceProduct() {
		this.detailOrder.setPrice(product.getPrice());
	}

	public void calculateSutTotalDetail() {
		double subTotalD;
		subTotalD = this.detailOrder.getPrice() * this.detailOrder.getQuantity();
		this.detailOrder.setSubTotal(subTotalD);
	}

	public void saveOrder() {
		try {
			if (this.customer != null) {
				if (!detailOrders.isEmpty()) {
					order.setCustomer(customer);
					//order.setState(State.EMITIDO.getValueNumeric());
					order.setItems(detailOrders);

					orderService.insert(order);
					resetForm();
					cleanDetail();
					Message.messageInfo("Registro guardado correctamente");
				} else {
					Message.messageInfo("La orden debe tener detalle");
				}
			} else {
				Message.messageInfo("Debe buscar un cliente");
			}
		} catch (Exception e) {
			Message.messageError("Error Order :" + e.getMessage());
		}
	}

	public void cleanDetail() {
		this.detailOrder = new DetailOrder();
		this.product = new Product();
	}

	public void resetForm() {
		this.order = new Order();
		this.orderSel = null;
		this.detailOrders.clear();
		this.customer = new Customer();
	}
	
	public void searhOrder() {
		try {
			if (searchBy!=null) {
				this.orders = orderService.findOrder(searchBy, valueSearch);
				Message.messageInfo("Se han encontrado " + orders.size() + " ordenes con ese criterio");
				
			} else {
				this.orders=orderService.list();
			}
			searchBy = null;
			valueSearch = null;

			
			
		} catch (Exception e) {
			Message.messageError("Error Order :" + e.getMessage());
		}
	}

	public void loadOrders() {
		try {
			this.orders = orderService.list();
		} catch (Exception e) {
			
		}

	}
	
	public void selectOrder(SelectEvent ev) {
		this.orderSel = (Order) ev.getObject();
	}

	public void selectDetailOrder(SelectEvent ev) {
		this.detailOrder = (DetailOrder) ev.getObject();
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Order getOrderSel() {
		return orderSel;
	}

	public void setOrderSel(Order orderSel) {
		this.orderSel = orderSel;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public DetailOrder getDetailOrder() {
		return detailOrder;
	}

	public void setDetailOrder(DetailOrder detailOrder) {
		this.detailOrder = detailOrder;
	}

	public DetailOrder getDetailOrderSel() {
		return detailOrderSel;
	}

	public void setDetailOrderSel(DetailOrder detailOrderSel) {
		this.detailOrderSel = detailOrderSel;
	}

	public List<DetailOrder> getDetailOrders() {
		return detailOrders;
	}

	public void setDetailOrders(List<DetailOrder> detailOrders) {
		this.detailOrders = detailOrders;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String getSearchBy() {
		return searchBy;
	}

	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}

	public String getValueSearch() {
		return valueSearch;
	}

	public void setValueSearch(String valueSearch) {
		this.valueSearch = valueSearch;
	}

}
