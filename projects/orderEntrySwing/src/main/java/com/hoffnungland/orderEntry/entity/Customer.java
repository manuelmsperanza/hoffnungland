package com.hoffnungland.orderEntry.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.h2.util.StringUtils;

/**
 * 
 * @author manuel.m.speranza
 * @version 0.1
 */
@Entity
public class Customer {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String companyName;
	
	@Enumerated(EnumType.STRING)
	private ShopType shopType;
	
	private String vatCode;
	
	private String fiscalCode;
	
	private String IBAN;
	
	@Enumerated(EnumType.STRING)
	private PaymentType preferredPaymentType;
	
	@ManyToOne
	private Agent referent;
	
	@ManyToOne
	private Address registeredOffice;
	
	@ManyToOne
	private Address deliveryAddress;
	
	
	
	public long getId() {
		return id;
	}



	public String getCompanyName() {
		return companyName;
	}



	public ShopType getShopType() {
		return shopType;
	}



	public String getVatCode() {
		return vatCode;
	}



	public String getFiscalCode() {
		return fiscalCode;
	}



	public String getIBAN() {
		return IBAN;
	}



	public PaymentType getPreferredPaymentType() {
		return preferredPaymentType;
	}



	public Agent getReferent() {
		return referent;
	}



	public Address getRegisteredOffice() {
		return registeredOffice;
	}



	public Address getDeliveryAddress() {
		return deliveryAddress;
	}



	public void setId(long id) {
		this.id = id;
	}



	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}



	public void setShopType(ShopType shopType) {
		this.shopType = shopType;
	}



	public void setVatCode(String vatCode) {
		this.vatCode = vatCode;
	}



	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}



	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}



	public void setPreferredPaymentType(PaymentType preferredPaymentType) {
		this.preferredPaymentType = preferredPaymentType;
	}



	public void setReferent(Agent referent) {
		this.referent = referent;
	}



	public void setRegisteredOffice(Address registeredOffice) {
		this.registeredOffice = registeredOffice;
	}



	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}



	public String getFiscalId() {
		
		return StringUtils.isNullOrEmpty(this.vatCode) ? this.fiscalCode : this.vatCode;
		
	}
	
}
