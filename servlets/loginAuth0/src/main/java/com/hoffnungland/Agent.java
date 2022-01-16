package com.hoffnungland;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.auth0.jwt.interfaces.Claim;

@SessionScoped
@Named
public class Agent implements Serializable {
	
	private static final long serialVersionUID = -4261316543839013250L;
	
	private String name;
	private String profileJson;
	private String picture;
	private String email;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Agent() {
		
	}
	
	@PostConstruct
	public void getSessionInfo() {
		FacesContext context = FacesContext.getCurrentInstance();
		this.setName(context.getExternalContext().getUserPrincipal().getName());
		
		Map<String, Object> requestMap = context.getExternalContext().getRequestMap();
		this.setProfileJson((String) requestMap.get("profileJson"));
		Map<String, Claim> claims = (Map<String, Claim>) requestMap.get("profile");
		this.setEmail(claims.get("email").asString());
		this.setPicture(claims.get("picture").asString());
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

	public String getProfileJson() {
		return profileJson;
	}

	public void setProfileJson(String profileJson) {
		this.profileJson = profileJson;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
}
