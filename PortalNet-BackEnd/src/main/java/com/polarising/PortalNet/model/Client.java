package com.polarising.PortalNet.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;
import org.springframework.stereotype.Component;

@Entity
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int clientID;
    
    private int nif;
    private int clientNumber;
    private String fullName;
    private String address;
    private int postalCode;
    private String city;
    private int phoneNumber;
    private String email;
    private String password;
    private String gender;
    private String contractDate;
    private String endOfContract;
    private float monthlyPayment;
    private boolean fraudulent;
    private boolean active;
    private boolean awaitsConfirmation;
    
    public Client() {}

    public Client(int nif, int clientNumber, String fullName, String address, int postalCode, String city,
                   int phoneNumber, String email, String password, String gender, String contractDate, String endOfContract,
                   float monthlyPayment, boolean fraudulent, boolean active, boolean awaitsConfirmation) {
        this.nif = nif;
        this.clientNumber = clientNumber;
        this.fullName = fullName;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.contractDate = contractDate;
        this.endOfContract = endOfContract;
        this.monthlyPayment = monthlyPayment;
        this.fraudulent = fraudulent;
        this.active = active;
        this.awaitsConfirmation = awaitsConfirmation;
    }

    public int getClientNumber() {
        return clientNumber;
    }
    


    public int getNIF() {
        return nif;
    }

    public String getName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public int getMobilePhone() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    public String getEndOfContract() {
        return endOfContract;
    }
    
    public String getGender() {
        return gender;
    }

    public String getContractDate() {
        return contractDate;
    }

    public float getMonthlyPayment() {
        return monthlyPayment;
    }

    public boolean isFraudulent() {
        return fraudulent;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isAwaitsConfirmation() {
        return awaitsConfirmation;
    }

    public void setFraudulent(boolean fraudulent) {
        this.fraudulent = fraudulent;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setAwaitsConfirmation(boolean awaitsConfirmation) {
        this.awaitsConfirmation = awaitsConfirmation;
    }

    public void setMonthlyPay(float monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }
    
   
}

