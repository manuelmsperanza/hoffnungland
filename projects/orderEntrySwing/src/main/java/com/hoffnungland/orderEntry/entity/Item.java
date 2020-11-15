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
public class Item {
	
	@Id
	@GeneratedValue
	private long id;
	
	private short quantity;
	
	private float discount;
	
	private short freeQuantity;
	
	private double invoicePrice;
	
	private double netPrice;
	
}
