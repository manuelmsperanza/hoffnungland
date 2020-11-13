package com.hoffnungland.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 
 * @author manuel.m.speranza
 * @version 0.1
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
	
}
