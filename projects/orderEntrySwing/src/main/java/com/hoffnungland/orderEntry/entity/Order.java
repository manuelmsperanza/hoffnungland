package com.hoffnungland.orderEntry.entity;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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

import org.hibernate.annotations.ColumnTransformer;
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
	
	/*@Enumerated(EnumType.STRING)
	private DayOfWeek closingDate;*/
	
	@Column(name = "closingDate")
	@ColumnTransformer(
		read = "bitget(closingDate, 1)",
		write = "case when ? then bitor(0, 1) else bitand(0, bitnot(1)) end"
	)
	private boolean sundayClosure;
	
	@Column(name = "closingDate")
	@ColumnTransformer(
		read = "bitget(closingDate, 1)",
		write = "case when ? then bitor(0, 2) else bitand(0, bitnot(2)) end"
	)
	private boolean mondayClosure;
	
	@Column(name = "closingDate")
	@ColumnTransformer(
		read = "bitget(closingDate, 1)",
		write = "case when ? then bitor(0, 4) else bitand(0, bitnot(4)) end"
	)
	private boolean tuesdayClosure;
	
	@Column(name = "closingDate")
	@ColumnTransformer(
		read = "bitget(closingDate, 1)",
		write = "case when ? then bitor(0, 8) else bitand(0, bitnot(8)) end"
	)
	private boolean wednesdayClosure;
	
	@Column(name = "closingDate")
	@ColumnTransformer(
		read = "bitget(closingDate, 1)",
		write = "case when ? then bitor(0, 16) else bitand(0, bitnot(16)) end"
	)
	private boolean thursdayClosure;
	
	@Column(name = "closingDate")
	@ColumnTransformer(
		read = "bitget(closingDate, 1)",
		write = "case when ? then bitor(0, 32) else bitand(0, bitnot(32)) end"
	)
	private boolean fridayClosure;
	
	@Column(name = "closingDate")
	@ColumnTransformer(
		read = "bitget(closingDate, 1)",
		write = "case when ? then bitor(0, 64) else bitand(0, bitnot(64)) end"
	)
	private boolean saturdayClosure;
	
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
	
	@ElementCollection
	private List<Item> itemList = new ArrayList<Item>();
	
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

	public boolean isSundayClosure() {
		return sundayClosure;
	}

	public boolean isMondayClosure() {
		return mondayClosure;
	}

	public boolean isTuesdayClosure() {
		return tuesdayClosure;
	}

	public boolean isWednesdayClosure() {
		return wednesdayClosure;
	}

	public boolean isThursdayClosure() {
		return thursdayClosure;
	}

	public boolean isFridayClosure() {
		return fridayClosure;
	}

	public boolean isSaturdayClosure() {
		return saturdayClosure;
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
	
	public void setSundayClosure(boolean sundayClosure) {
		this.sundayClosure = sundayClosure;
	}

	public void setMondayClosure(boolean mondayClosure) {
		this.mondayClosure = mondayClosure;
	}

	public void setTuesdayClosure(boolean tuesdayClosure) {
		this.tuesdayClosure = tuesdayClosure;
	}

	public void setWednesdayClosure(boolean wednesdayClosure) {
		this.wednesdayClosure = wednesdayClosure;
	}

	public void setThursdayClosure(boolean thursdayClosure) {
		this.thursdayClosure = thursdayClosure;
	}

	public void setFridayClosure(boolean fridayClosure) {
		this.fridayClosure = fridayClosure;
	}

	public void setSaturdayClosure(boolean saturdayClosure) {
		this.saturdayClosure = saturdayClosure;
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
	
	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	
	public void copyCustomerInformation() {
		this.IBAN = this.customerOrder.getIBAN();
		this.paymentType = this.customerOrder.getPreferredPaymentType();
		this.orderAddress = this.customerOrder.getRegisteredOffice();
		this.copyAddressInformation();
	}
	
	public void copyAddressInformation() {
		this.deliveryTimeFrom = this.orderAddress.getDeliveryTimeFrom();
		this.deliveryTimeTo = this.orderAddress.getDeliveryTimeTo();
		this.sundayClosure = this.orderAddress.isSundayClosure();
		this.mondayClosure = this.orderAddress.isMondayClosure();
		this.tuesdayClosure = this.orderAddress.isTuesdayClosure();
		this.wednesdayClosure = this.orderAddress.isWednesdayClosure();
		this.thursdayClosure = this.orderAddress.isThursdayClosure();
		this.fridayClosure = this.orderAddress.isFridayClosure();
		this.saturdayClosure = this.orderAddress.isSaturdayClosure();
		this.otherAddressDetail = this.orderAddress.getOtherAddressDetail();
	}
	
}
