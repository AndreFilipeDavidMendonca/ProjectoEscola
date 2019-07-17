package com.polarising.PortalNet.jwt;

import org.springframework.stereotype.Component;

@Component
public class JwtResponse {

	private String jwt;
	private String message;
	
	public JwtResponse() {};
	
	public JwtResponse(String jwt, String message) {
		super();
		this.jwt = jwt;
		this.message = message;
	}

	public String getJwt() {
		return jwt;
	}

	public String getMessage() {
		return message;
	}
}
