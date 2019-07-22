package com.polarising.PortalNet.Forms;

import org.springframework.stereotype.Component;

@Component
public class LoginCredentials {
	
	private String email;
	private String password;
	
	public LoginCredentials() {}
	
	public LoginCredentials(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
}
