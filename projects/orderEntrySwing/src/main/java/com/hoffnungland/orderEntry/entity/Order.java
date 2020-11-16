package com.hoffnungland.orderEntry.entity;

import java.time.DayOfWeek;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * 
 * @author manuel.m.speranza
 * @version 0.1
 */
@Entity
public class Order {
	
	@Id
	@GeneratedValue(
		strategy=GenerationType.SEQUENCE,
		generator="order_generator"
	)
	@GenericGenerator(
		name = "order_generator",
		strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		parameters = {
			@Parameter(name = "sequence_name", value = "order_sequence"),
			@Parameter(name = "initial_value", value = "1"),
			@Parameter(name = "increment_size", value = "5"),
			@Parameter(name = "optimizer", value = "pooled")
		}
	)
	private long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date creationDate;
	
	@Temporal(TemporalType.TIME)
	private java.util.Date deliveryTimeFrom;
	
	@Temporal(TemporalType.TIME)
	private java.util.Date deliveryTimeTo;
	
	@Enumerated(EnumType.STRING)
	private DayOfWeek closingDate;
	
	private String otherAddressDetail;
	
	private float channelDiscount;
	
	private float financialDiscount;
	
	private String IBAN;
	
	@Enumerated(EnumType.STRING)
	private PaymentType paymentType;
	
	@ManyToOne
	private Customer customerOrder;
	
	@ManyToOne
	private Address orderAddress;
	
	@ManyToOne
	private Agent seller;
	
	@OneToMany(mappedBy="orderItems", cascade = CascadeType.ALL, orphanRemoval = true)
	List<Item> itemList;
	
	public long getId() {
		return id;
	}

	public java.util.Date getCreationDate() {
		return creationDate;
	}

	public java.util.Date getDeliveryTimeFrom() {
		return deliveryTimeFrom;
	}

	public java.util.Date getDeliveryTimeTo() {
		return deliveryTimeTo;
	}

	public DayOfWeek getClosingDate() {
		return closingDate;
	}

	public String getOtherAddressDetail() {
		return otherAddressDetail;
	}

	public float getChannelDiscount() {
		return channelDiscount;
	}

	public float getFinancialDiscount() {
		return financialDiscount;
	}

	public String getIBAN() {
		return IBAN;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public Customer getCustomerOrder() {
		return customerOrder;
	}

	public Address getOrderAddress() {
		return orderAddress;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setCreationDate(java.util.Date creationDate) {
		this.creationDate = creationDate;
	}

	public void setDeliveryTimeFrom(java.util.Date deliveryTimeFrom) {
		this.deliveryTimeFrom = deliveryTimeFrom;
	}

	public void setDeliveryTimeTo(java.util.Date deliveryTimeTo) {
		this.deliveryTimeTo = deliveryTimeTo;
	}

	public void setClosingDate(DayOfWeek closingDate) {
		this.closingDate = closingDate;
	}

	public void setOtherAddressDetail(String otherAddressDetail) {
		this.otherAddressDetail = otherAddressDetail;
	}

	public void setChannelDiscount(float channelDiscount) {
		this.channelDiscount = channelDiscount;
	}

	public void setFinancialDiscount(float financialDiscount) {
		this.financialDiscount = financialDiscount;
	}

	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public void setCustomerOrder(Customer customerOrder) {
		this.customerOrder = customerOrder;
	}

	public void setOrderAddress(Address orderAddress) {
		this.orderAddress = orderAddress;
	}

	public Agent getSeller() {
		return seller;
	}

	public void setSeller(Agent seller) {
		this.seller = seller;
	}
	
	public void addItem(Item item) {
		this.itemList.add(item);
		item.setOrderItems(this);
	}
	
	public void removeItem(Item item) {
		this.itemList.remove(item);
		item.setOrderItems(null);
	}
	
	public void copyCustomerInformation() {
		this.IBAN = this.customerOrder.getIBAN();
		this.paymentType = this.customerOrder.getPreferredPaymentType();
		this.orderAddress = this.customerOrder.getDeliveryAddress();
		this.copyAddressInformation();
	}
	
	public void copyAddressInformation() {
		this.deliveryTimeFrom = this.orderAddress.getDeliveryTimeFrom();
		this.deliveryTimeTo = this.orderAddress.getDeliveryTimeTo();
		this.closingDate = this.orderAddress.getClosingDate();
		this.otherAddressDetail = this.orderAddress.getOtherAddressDetail();
	}
	
}
