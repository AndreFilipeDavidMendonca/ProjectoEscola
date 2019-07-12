package com.polarising.PortalNet.Forms;

import org.springframework.stereotype.Component;

@Component
public class ClientFormRegistration {

	private int nif;
	
	private String name;
	
	private String address;
	
	private String postalCode;
	
	private String city;
	
	private Integer mobilePhone;
	
	private String email;
	
	private String gender;
	
	private String password;
	
	private String serviceName;
	
	private String birthDate;
	
	public ClientFormRegistration () {}

	public ClientFormRegistration(int nif, String name, String address, String postalCode, String city, Integer mobilePhone,
			String email, String gender, String password, String serviceName, String birthDate) {
		super();
		this.nif = nif;
		this.name = name;
		this.address = address;
		this.postalCode = postalCode;
		this.city = city;
		this.mobilePhone = mobilePhone;
		this.email = email;
		this.gender = gender;
		this.password = password;
		this.serviceName = serviceName;
		this.birthDate = birthDate;
	}

	public int getNif() {
		return nif;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getCity() {
		return city;
	}

	public Integer getMobilePhone() {
		return mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public String getGender() {
		return gender;
	}

	public String getPassword() {
		return password;
	}
	
	public String getServiceName() {
		return serviceName;
	}
	
	public String getBirthDate() {
		return birthDate;
	}


}
