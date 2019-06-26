package com.PortalNet.PortalNet;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Clients {

    private int clientID;
    private int NIF;
    private String name;
    private String address;
    private int postalCode;
    private String city;
    private int mobilePhone;
    private int homePhone;
    private String email;
    private String password;
    private Date entryDate;
    private float monthlyPay;
    private float debt;  // Mensalidade em falta
    private boolean fraudulent;
    private boolean active;
    private boolean awaitsConfirmation;

    public Clients(int clientID, int NIF, String name, String address, int postalCode, String city,
                   int mobilePhone, int homePhone, String email, String password, Date entryDate,
                   float monthlyPay, float debt, boolean fraudulent, boolean active, boolean awaitsConfirmation) {
        this.clientID = clientID;
        this.NIF = NIF;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.mobilePhone = mobilePhone;
        this.homePhone = homePhone;
        this.email = email;
        this.password = password;
        this.entryDate = entryDate;
        this.monthlyPay = monthlyPay;
        this.debt = debt;
        this.fraudulent = fraudulent;
        this.active = active;
        this.awaitsConfirmation = awaitsConfirmation;
    }

    public int getClientID() {
        return clientID;
    }

    public int getNIF() {
        return NIF;
    }

    public String getName() {
        return name;
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
        return mobilePhone;
    }

    public int getHomePhone() {
        return homePhone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public float getMonthlyPay() {
        return monthlyPay;
    }

    public float getDebt() {
        return debt;
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

    public void setMonthlyPay(float monthlyPay) {
        this.monthlyPay = monthlyPay;
    }

    public void setDebt(float debt) {
        this.debt = debt;
    }
}

