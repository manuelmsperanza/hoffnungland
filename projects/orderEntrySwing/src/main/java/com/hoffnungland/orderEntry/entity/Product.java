package com.hoffnungland.orderEntry.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.NaturalId;

/**
 * 
 * @author manuel.m.speranza
 * @version 0.1
 */
@Entity
public class Product {
	
	@Id
	@GeneratedValue(
		strategy=GenerationType.SEQUENCE,
		generator="product_generator"
	)
	@SequenceGenerator(
		name="product_generator",
		sequenceName="product_sequence"
	)
	private long id;
	
	@NaturalId
	private String inventoryCode;
	
	private String name;
	
	private short itemsInTheBox;
	
	private float vat;
	
	private float exciseDuty;
	
	private float price;
	
	private PackagingType packaging;
	
	@ManyToOne
	private ProductCategory productCategory;

	public long getId() {
		return id;
	}

	public String getInventoryCode() {
		return inventoryCode;
	}

	public String getName() {
		return name;
	}

	public short getItemsInTheBox() {
		return itemsInTheBox;
	}

	public float getVat() {
		return vat;
	}

	public float getExciseDuty() {
		return exciseDuty;
	}

	public float getPrice() {
		return price;
	}

	public PackagingType getPackaging() {
		return packaging;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setInventoryCode(String inventoryCode) {
		this.inventoryCode = inventoryCode;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setItemsInTheBox(short itemsInTheBox) {
		this.itemsInTheBox = itemsInTheBox;
	}

	public void setVat(float vat) {
		this.vat = vat;
	}

	public void setExciseDuty(float exciseDuty) {
		this.exciseDuty = exciseDuty;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setPackaging(PackagingType packaging) {
		this.packaging = packaging;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}
	
}
