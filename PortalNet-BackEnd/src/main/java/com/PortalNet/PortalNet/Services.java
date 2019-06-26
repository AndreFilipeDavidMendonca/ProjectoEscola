package com.PortalNet.PortalNet;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Services {

    private int serviceID;
    private String name;
    private String netSpeed;
    private String numOfChannels;
    private String housePhonePlan;
    private String mobilePhonePlan;
    private int loyalty;
    private float price;
    private Date contractCreationDate;
    private boolean active;

    public Services(int serviceID, String name, String netSpeed, String numOfChannels, String housePhonePlan, String mobilePhonePlan, int loyalty, float price, Date contractCreationDate, boolean active) {
        this.serviceID = serviceID;
        this.name = name;
        this.netSpeed = netSpeed;
        this.numOfChannels = numOfChannels;
        this.housePhonePlan = housePhonePlan;
        this.mobilePhonePlan = mobilePhonePlan;
        this.loyalty = loyalty;
        this.price = price;
        this.contractCreationDate = contractCreationDate;
        this.active = active;
    }

    public int getServiceID() {
        return serviceID;
    }

    public String getName() {
        return name;
    }

    public String getNetSpeed() {
        return netSpeed;
    }

    public String getNumOfChannels() {
        return numOfChannels;
    }

    public String getHousePhonePlan() {
        return housePhonePlan;
    }

    public String getMobilePhonePlan() {
        return mobilePhonePlan;
    }

    public int getLoyalty() {
        return loyalty;
    }

    public float getPrice() {
        return price;
    }

    public Date getContractCreationDate() {
        return contractCreationDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}

