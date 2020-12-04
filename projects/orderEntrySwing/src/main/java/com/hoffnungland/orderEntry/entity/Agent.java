package com.hoffnungland.orderEntry.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.NaturalId;

/**
 * 
 * @author manuel.m.speranza
 * @version 0.2
 */

@Entity
public class Agent {
	
	@Id
	@GeneratedValue(
		strategy=GenerationType.SEQUENCE,
		generator="agent_generator"
	)
	@SequenceGenerator(
		name="agent_generator",
		sequenceName="agent_sequence"
	)
	private long id;
	
	@NaturalId
	private String userName;
	
	private String name;
	
	private String surname;
	
	private String email;
	
	@OneToMany(mappedBy="referent", fetch=FetchType.LAZY)
	private List<Customer> customerList = new ArrayList<Customer>();
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agent other = (Agent) obj;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	public void addCustomer(Customer customer) {
		this.customerList.add(customer);
		customer.setReferent(this);
	}
	
	public void removeCustomer(Customer customer) {
		this.customerList.remove(customer);
		customer.setReferent(null);
	}

}
