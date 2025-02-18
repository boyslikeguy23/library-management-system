package com.library.request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
	
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;

	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

}
