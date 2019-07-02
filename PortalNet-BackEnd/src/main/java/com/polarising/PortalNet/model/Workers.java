package com.polarising.PortalNet.model;

import org.springframework.stereotype.Component;

@Component
public class Workers {

    private int numID;
    private String name;
    private String email;
    private String role;
    private String password;
    
    public Workers() {}

    public Workers(int numID, String name, String email, String role, String password) {
        this.numID = numID;
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
    }

	public int getNumID() {
		return numID;
	}

	public void setNumID(int numID) {
		this.numID = numID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

   
}

