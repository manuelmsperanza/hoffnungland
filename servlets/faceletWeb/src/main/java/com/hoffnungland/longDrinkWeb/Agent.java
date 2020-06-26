package com.hoffnungland.longDrinkWeb;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class Agent implements Serializable {
	
	private static final long serialVersionUID = -4261316543839013250L;
	
	private String name;
	
	public Agent() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void reset() {
		this.name = null;
	}
}
