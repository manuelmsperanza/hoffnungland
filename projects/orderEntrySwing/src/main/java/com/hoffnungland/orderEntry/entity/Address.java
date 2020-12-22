package com.hoffnungland.orderEntry.entity;

import java.time.DayOfWeek;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnTransformer;

/**
 * 
 * @author manuel.m.speranza
 * @version 0.2
 */
@Entity
public class Address {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String signboard;
	
	private String country;
	
	private String countryProvince;
	
	private String city;
	
	private String zipPostalCode;
	
	private String streetName;
	
	private int streetNumber;
	
	private String email;
	
	private String phone;
	
	private String mobile;
	
	@Temporal(TemporalType.TIME)
	private java.util.Date deliveryTimeFrom;
	
	@Temporal(TemporalType.TIME)
	private java.util.Date deliveryTimeTo;
	
	/*@Enumerated(EnumType.STRING)
	private DayOfWeek closingDate;*/
	
	@Column(name = "closingDate", columnDefinition = "INT")
	@ColumnTransformer(
		read = "bitget(closingDate, 1)",
		write = "case when ? then bitor(0, 1) else bitand(0, bitnot(1)) end"
	)
	private boolean sundayClosure;
	
	@Column(name = "closingDate", columnDefinition = "INT")
	@ColumnTransformer(
		read = "bitget(closingDate, 1)",
		write = "case when ? then bitor(0, 2) else bitand(0, bitnot(2)) end"
	)
	private boolean mondayClosure;
	
	@Column(name = "closingDate", columnDefinition = "INT")
	@ColumnTransformer(
		read = "bitget(closingDate, 1)",
		write = "case when ? then bitor(0, 4) else bitand(0, bitnot(4)) end"
	)
	private boolean tuesdayClosure;
	
	@Column(name = "closingDate", columnDefinition = "INT")
	@ColumnTransformer(
		read = "bitget(closingDate, 1)",
		write = "case when ? then bitor(0, 8) else bitand(0, bitnot(8)) end"
	)
	private boolean wednesdayClosure;
	
	@Column(name = "closingDate", columnDefinition = "INT")
	@ColumnTransformer(
		read = "bitget(closingDate, 1)",
		write = "case when ? then bitor(0, 16) else bitand(0, bitnot(16)) end"
	)
	private boolean thursdayClosure;
	
	@Column(name = "closingDate", columnDefinition = "INT")
	@ColumnTransformer(
		read = "bitget(closingDate, 1)",
		write = "case when ? then bitor(0, 32) else bitand(0, bitnot(32)) end"
	)
	private boolean fridayClosure;
	
	@Column(name = "closingDate", columnDefinition = "INT")
	@ColumnTransformer(
		read = "bitget(closingDate, 1)",
		write = "case when ? then bitor(0, 64) else bitand(0, bitnot(64)) end"
	)
	private boolean saturdayClosure;
	
	private String otherAddressDetail;
	
	@ManyToOne
	private Customer customer;

	public long getId() {
		return id;
	}

	public String getSignboard() {
		return signboard;
	}

	public String getCountry() {
		return country;
	}

	public String getCountryProvince() {
		return countryProvince;
	}

	public String getCity() {
		return city;
	}

	public String getZipPostalCode() {
		return zipPostalCode;
	}

	public String getStreetName() {
		return streetName;
	}

	public int getStreetNumber() {
		return streetNumber;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getMobile() {
		return mobile;
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
	
	public void setId(long id) {
		this.id = id;
	}

	public void setSignboard(String signboard) {
		this.signboard = signboard;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCountryProvince(String countryProvince) {
		this.countryProvince = countryProvince;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setZipPostalCode(String zipPostalCode) {
		this.zipPostalCode = zipPostalCode;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
