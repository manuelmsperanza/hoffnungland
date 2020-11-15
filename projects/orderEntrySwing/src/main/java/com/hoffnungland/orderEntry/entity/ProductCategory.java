package com.hoffnungland.orderEntry.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 
 * @author manuel.m.speranza
 * @version 0.1
 */
@Entity
public class ProductCategory {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	
	private short groupedBy;
	
	private boolean isFreeQuantityAllowed;
	
	private float maxDiscount;
	
}
