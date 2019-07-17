package com.polarising.PortalNet.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Services {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long serviceID;
    private String name;
    private String internet;
    private String tv;
    private String phone;
    private String mobilePhone;
    private int loyalty;
    private float price;
    private String creationDate;
    private boolean status;
    private String imgUrl;
    private String imgName;
    
    
    public Services() {}

    public Services(String name, String internet, String tv, String phone, String mobilePhone, int loyalty, float price, String creationDate, boolean status, String imgUrl, String imgName) {
    	this.imgName = imgName;
    	this.imgUrl = imgUrl;
    	this.name = name;
        this.internet = internet;
        this.tv = tv;
        this.phone = phone;
        this.mobilePhone = mobilePhone;
        this.loyalty = loyalty;
        this.price = price;
        this.creationDate = creationDate;
        this.status = status;
    }

	public Long getServiceID() {
		return serviceID;
	}

	public void setServiceID(Long serviceID) {
		this.serviceID = serviceID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInternet() {
		return internet;
	}

	public void setInternet(String internet) {
		this.internet = internet;
	}

	public String getTv() {
		return tv;
	}

	public void setTv(String tv) {
		this.tv = tv;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public int getLoyalty() {
		return loyalty;
	}

	public void setLoyalty(int loyalty) {
		this.loyalty = loyalty;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	
}

