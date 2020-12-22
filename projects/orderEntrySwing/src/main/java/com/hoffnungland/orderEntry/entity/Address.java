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
	
	private int closingDate;
	
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

	public boolean isSundayClosed() {
		int bitmask = 0b1;
		return (this.closingDate & bitmask) > 0;
	}

	public boolean isMondayClosed() {
		int bitmask = 0b10;
		return (this.closingDate & bitmask) > 0;
	}

	public boolean isTuesdayClosed() {
		int bitmask = 0b100;
		return (this.closingDate & bitmask) > 0;
	}

	public boolean isWednesdayClosed() {
		int bitmask = 0b1000;
		return (this.closingDate & bitmask) > 0;
	}

	public boolean isThursdayClosed() {
		int bitmask = 0b10000;
		return (this.closingDate & bitmask) > 0;
	}

	public boolean isFridayClosed() {
		int bitmask = 0b100000;
		return (this.closingDate & bitmask) > 0;
	}

	public boolean isSaturdayClosed() {
		int bitmask = 0b1000000;
		return (this.closingDate & bitmask) > 0;
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
	
	public void setSundayClosed(boolean sundayClosed) {
		int bitmask = 0b1;
		if(sundayClosed) {
			this.closingDate =  this.closingDate | bitmask;
		} else {
			this.closingDate =  this.closingDate & ~bitmask;
		}
	}

	public void setMondayClosed(boolean mondayClosed) {
		int bitmask = 0b10;
		if(mondayClosed) {
			this.closingDate =  this.closingDate | bitmask;
		} else {
			this.closingDate =  this.closingDate & ~bitmask;
		}
	}

	public void setTuesdayClosed(boolean tuesdayClosed) {
		int bitmask = 0b100;
		if(tuesdayClosed) {
			this.closingDate =  this.closingDate | bitmask;
		} else {
			this.closingDate =  this.closingDate & ~bitmask;
		}
	}

	public void setWednesdayClosed(boolean wednesdayClosed) {
		int bitmask = 0b1000;
		if(wednesdayClosed) {
			this.closingDate =  this.closingDate | bitmask;
		} else {
			this.closingDate =  this.closingDate & ~bitmask;
		}
	}

	public void setThursdayClosed(boolean thursdayClosed) {
		int bitmask = 0b10000;
		if(thursdayClosed) {
			this.closingDate =  this.closingDate | bitmask;
		} else {
			this.closingDate =  this.closingDate & ~bitmask;
		}
	}

	public void setFridayClosed(boolean fridayClosed) {
		int bitmask = 0b100000;
		if(fridayClosed) {
			this.closingDate =  this.closingDate | bitmask;
		} else {
			this.closingDate =  this.closingDate & ~bitmask;
		}
	}

	public void setSaturdayClosed(boolean saturdayClosed) {
		int bitmask = 0b1000000;
		if(saturdayClosed) {
			this.closingDate =  this.closingDate | bitmask;
		} else {
			this.closingDate =  this.closingDate & ~bitmask;
		}
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

	public int getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(int closingDate) {
		this.closingDate = closingDate;
	}
	
}
