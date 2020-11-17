package com.hoffnungland.orderEntry.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 * 
 * @author manuel.m.speranza
 * @version 0.1
 */
@Entity
public class ProductCategory {
	
	@Id
	@GeneratedValue(
		strategy=GenerationType.SEQUENCE,
		generator="product_category_generator"
	)
	@SequenceGenerator(
		name="product_category_generator",
		sequenceName="product_category_sequence"
	)
	private long id;
	
	private String name;
	
	private short groupedBy;
	
	private boolean isFreeQuantityAllowed;
	
	private float maxDiscount;
	
	@OneToMany(mappedBy="productCategory", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Product> productList;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public short getGroupedBy() {
		return groupedBy;
	}

	public boolean isFreeQuantityAllowed() {
		return isFreeQuantityAllowed;
	}

	public float getMaxDiscount() {
		return maxDiscount;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGroupedBy(short groupedBy) {
		this.groupedBy = groupedBy;
	}

	public void setFreeQuantityAllowed(boolean isFreeQuantityAllowed) {
		this.isFreeQuantityAllowed = isFreeQuantityAllowed;
	}

	public void setMaxDiscount(float maxDiscount) {
		this.maxDiscount = maxDiscount;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
	public void addProduct(Product product) {
		this.productList.add(product);
		product.setProductCategory(this);
	}
	
	public void removeProduct(Product product) {
		this.productList.remove(product);
		product.setProductCategory(null);
	}
}
