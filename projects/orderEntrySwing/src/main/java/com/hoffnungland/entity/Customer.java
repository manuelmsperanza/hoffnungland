package com.hoffnungland.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 
 * @author manuel.m.speranza
 * @version 0.1
 */
@Entity
public class Customer {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Enumerated(EnumType.STRING)
	private String companyName;
	
	@Enumerated(EnumType.STRING)
	private ShopType shopType;
	
	private String vatCode;
	
	private String fiscalCode;
	
	private String IBAN;
	
	@Enumerated(EnumType.STRING)
	private PaymentType preferredPaymentType;
	
}
