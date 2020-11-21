package com.hoffnungland.orderEntry.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * 
 * @author manuel.m.speranza
 * @version 0.1
 */
@Embeddable
public class Item {
		
	private short quantity;
	
	private float discount;
	
	private short freeQuantity;
	
	private double invoicePrice;
	
	private double netPrice;
		
	@ManyToOne
	private ProductCategory productType;
	
	@ManyToOne
	private Product productDetail;

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
