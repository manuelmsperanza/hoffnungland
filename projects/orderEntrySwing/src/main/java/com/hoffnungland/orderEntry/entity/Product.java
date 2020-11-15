package com.hoffnungland.orderEntry.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.NaturalId;

/**
 * 
 * @author manuel.m.speranza
 * @version 0.1
 */
@Entity
public class Product {
	
	@Id
	@GeneratedValue
	private long id;
	
	@NaturalId
	private String inventoryCode;
	
	private String name;
	
	private short itemsInTheBox;
	
	private float vat;
	
	private float exciseDuty;
	
	private float price;
	
	private PackagingType packaging;
}
