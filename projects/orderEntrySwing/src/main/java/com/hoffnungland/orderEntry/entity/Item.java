package com.hoffnungland.orderEntry.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * 
 * @author manuel.m.speranza
 * @version 0.1
 */
@Entity
public class Item {
	
	@Id
	@GeneratedValue
	private long id;
	
	private short quantity;
	
	private float discount;
	
	private short freeQuantity;
	
	private double invoicePrice;
	
	private double netPrice;
	
	@ManyToOne
	private Order orderItems;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public short getQuantity() {
		return quantity;
	}

	public void setQuantity(short quantity) {
		this.quantity = quantity;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public short getFreeQuantity() {
		return freeQuantity;
	}

	public void setFreeQuantity(short freeQuantity) {
		this.freeQuantity = freeQuantity;
	}

	public double getInvoicePrice() {
		return invoicePrice;
	}

	public void setInvoicePrice(double invoicePrice) {
		this.invoicePrice = invoicePrice;
	}

	public double getNetPrice() {
		return netPrice;
	}

	public void setNetPrice(double netPrice) {
		this.netPrice = netPrice;
	}

	public Order getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Order orderItems) {
		this.orderItems = orderItems;
	}
	
	
	
}
