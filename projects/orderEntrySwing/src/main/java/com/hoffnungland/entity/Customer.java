package com.hoffnungland.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String companyName;
	
	private ShopType shopType;
	
	private String vatCode;
	
	private String fiscalCode;
	
	private String IBAN;
	
	private PaymentType preferredPaymentType;
	
}
