package com.hoffnungland.longDrinkWeb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@SessionScoped
@Named
public class Agent implements Serializable {
	
	private static final long serialVersionUID = -4261316543839013250L;
	
	private String name;
	
	public Agent() {
		
	}
	
	@PostConstruct
	public void getSessionInfo() {
		FacesContext context = FacesContext.getCurrentInstance();
		this.name = context.getExternalContext().getUserPrincipal().getName();
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
	
	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		session.invalidate();
		return "logout";
	}
}
