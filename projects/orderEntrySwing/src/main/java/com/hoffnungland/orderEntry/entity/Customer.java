package com.hoffnungland.orderEntry.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.h2.util.StringUtils;

/**
 * 
 * @author manuel.m.speranza
 * @version 0.1
 */
@Entity
public class Customer {
	
	@Id
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "customer-generator"
	)
	@SequenceGenerator(
		name = "customer-generator",
		sequenceName = "customer_sequence"
	)
	private long id;
	
	private String companyName;
	
	@Enumerated(EnumType.STRING)
	private ShopType shopType;
	
	private String vatCode;
	
	private String taxpayerIdentificationNumber;
	
	private String IBAN;
	
	@Enumerated(EnumType.STRING)
	private PaymentType preferredPaymentType;
	
	@ManyToOne
	private Agent referent;
	
	@OneToOne
	private Address registeredOffice;
	
	@OneToMany(mappedBy="customer", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
	private List<Address> deliveryAddress = new ArrayList<>();
	
	/*@OneToMany(mappedBy="customerOrder", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
	private List<Order> orderList = new ArrayList<Order>();*/
	
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

	public String getTaxpayerIdentificationNumber() {
		return taxpayerIdentificationNumber;
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

	public List<Address> getDeliveryAddress() {
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

	public void setTaxpayerIdentificationNumber(String taxpayerIdentificationNumber) {
		this.taxpayerIdentificationNumber = taxpayerIdentificationNumber;
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

	public void setDeliveryAddress(List<Address> deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	/*public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}*/

	public void addDeliveryAddress(Address address) {
		this.deliveryAddress.add(address);
		address.setCustomer(this);
	}
	
	public void removeOrder(Address address) {
		this.deliveryAddress.remove(address);
		address.setCustomer(null);
	}
	
	public String getFiscalId() {
		return StringUtils.isNullOrEmpty(this.vatCode) ? this.taxpayerIdentificationNumber : this.vatCode;
	}
	
}
