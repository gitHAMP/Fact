package com.hampcode.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name = "create_at")
	private Date createAt;

	@Column(name = "sub_total")
	private double subTotal;

	@Column(name = "total")
	private double total;

	@Column(name = "state")
	// @Enumerated(EnumType.ORDINAL)
	// private State state;
	private int state;

	// bi-directional many-to-one association to DetalleFactura
	// 1 - CASCADE
	@OneToMany(mappedBy = "orderId", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DetailOrder> items;

	// bi-directional many-to-one association to Customer
	@ManyToOne
	private Customer customer;

	public Order() {
		this.items = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<DetailOrder> getItems() {
		return items;
	}

	public void setItems(List<DetailOrder> items) {
		this.items = items;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	/*
	 * public State getState() { return state; }
	 * 
	 * public void setState(State state) { this.state = state; }
	 */

	/*
	 * public int getState() { return state; }
	 * 
	 * public void setState(int state) { this.state = state; }
	 */

	/*
	 * public void addDetailOrder(DetailOrder item) { this.items.add(item); }
	 * 
	 * public Double getTotal() { Double total=0.0;
	 * 
	 * int size=items.size();
	 * 
	 * for (int i = 0; i < size; i++) { total+=items.get(i).calculateAmount(); }
	 * return total; }
	 */

}
