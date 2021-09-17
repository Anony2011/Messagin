package com.wissam.messaging.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AccountCredentials {
	private String username;
	private String password;
	private String role;
	

	  @JsonIgnore
	public String getPassword() {
		return password;
	}
	  @JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}
	
}
