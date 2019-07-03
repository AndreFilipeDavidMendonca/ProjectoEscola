package com.polarising.PortalNet.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long clientID;
	private int clientNumber;
    private int nif;
    private String name;
    private String address;
    private String postalCode;
    private String city;
    private int mobilePhone;
    private String email;
    private String gender;
    private String password;
    private String entryDate;
    private String endContract;
    private int numberOfServices;
    private String serviceName;
    private String monthlyPay;
    private boolean fraudulent;
    private boolean status;
    private String birthDate;

    
    public Client() {}

    public Client( int clientNumber, int nif, String name, String address, String postalCode, String city,
                   int mobilePhone, String email, String gender, String password, String entryDate, String endContract, int numberOfServices, String serviceName,
                   String monthlyPay, boolean fraudulent, boolean status, String birthDate) {
   
    	this.clientNumber = clientNumber;
        this.nif = nif;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.gender = gender;
        this.password = password;
        this.entryDate = entryDate;
        this.numberOfServices = numberOfServices;
        this.serviceName = serviceName;
        this.endContract = endContract;
        this.monthlyPay = monthlyPay;
        this.fraudulent = fraudulent;
        this.status= status;
        this.birthDate = birthDate;
    }

	public long getClientID() {
		return clientID;
	}

	public void setClientID(long clientID) {
		this.clientID = clientID;
	}

	public int getClientNumber() {
		return clientNumber;
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

	public int getMobilePhone() {
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

	public String getEntryDate() {
		return entryDate;
	}

	public String getEndContract() {
		return endContract;
	}

	public int getNumberOfServices() {
		return numberOfServices;
	}

	public String getServiceName() {
		return serviceName;
	}

	public String getMonthlyPay() {
		return monthlyPay;
	}

	public boolean isFraudulent() {
		return fraudulent;
	}

	public boolean isStatus() {
		return status;
	}

	public String getBirthDate() {
		return birthDate;
	}

 
}

