package com.hoffnungland.planetDrivingApp.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * 
 * @author manuel.m.speranza
 * @version 0.1
 */
@Entity
public class DrivingSchool {
	
	@Id
	@GeneratedValue
	private long id;

	@Version
	private Integer version;
	
	@CreationTimestamp
	private Date createdDate;
	
	@UpdateTimestamp
	private Date lastModifiedDate;
	
    private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    
}
