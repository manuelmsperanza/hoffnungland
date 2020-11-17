package com.hoffnungland.orderEntry.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * 
 * @author manuel.m.speranza
 * @version 0.1
 */
@Entity
public class Item {
	
	@Id
	@GeneratedValue(
		strategy=GenerationType.SEQUENCE,
		generator="order_item_generator"
	)
	@GenericGenerator(
		name = "order_item_generator",
		strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		parameters = {
			@Parameter(name = "sequence_name", value = "order_item_sequence"),
			@Parameter(name = "initial_value", value = "1"),
			@Parameter(name = "increment_size", value = "5"),
			@Parameter(name = "optimizer", value = "pooled")
		}
	)
	private long id;
	
	private short quantity;
	
	private float discount;
	
	private short freeQuantity;
	
	private double invoicePrice;
	
	private double netPrice;
	
	@ManyToOne
	private Order orderItems;
	
	@ManyToOne
	private ProductCategory productType;
	
	@ManyToOne
	private Product productDetail;

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

	public ProductCategory getProductType() {
		return productType;
	}

	public Product getProductDetail() {
		return productDetail;
	}

	public void setProductType(ProductCategory productType) {
		this.productType = productType;
	}

	public void setProductDetail(Product productDetail) {
		this.productDetail = productDetail;
	}
	
}
