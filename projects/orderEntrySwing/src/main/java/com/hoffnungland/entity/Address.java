package com.hoffnungland.entity;

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
	private int id;
	
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
	
}
