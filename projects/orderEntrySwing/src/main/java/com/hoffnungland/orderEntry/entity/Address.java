package com.hoffnungland.orderEntry.entity;

import java.time.DayOfWeek;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	
	@Enumerated(EnumType.STRING)
	private DayOfWeek closingDate;
	
	private String otherAddressDetail;

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

	public DayOfWeek getClosingDate() {
		return closingDate;
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

	public void setClosingDate(DayOfWeek closingDate) {
		this.closingDate = closingDate;
	}

	public void setOtherAddressDetail(String otherAddressDetail) {
		this.otherAddressDetail = otherAddressDetail;
	}
	
	
	
}
