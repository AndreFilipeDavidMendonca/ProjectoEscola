package com.polarising.PortalNet.Forms;
import org.springframework.stereotype.Component;

public class ServiceForm {
	private String name;
	private String tv;
	private String internet;
	private String mobilePhone;
	private String phone;
	private int loyalty;
	private float price;
	
	public ServiceForm () {}

	public ServiceForm(String name, String tv, String internet, String mobilePhone, String phone, 
			int loyalty, float price) {
		super();
		this.name = name;
		this.tv = tv;
		this.internet = internet;
		this.mobilePhone = mobilePhone;
		this.phone = phone;
		this.loyalty = loyalty;
		this.price = price;
		
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTv() {
		return tv;
	}

	public void setTv(String tv) {
		this.tv = tv;
	}

	public String getInternet() {
		return internet;
	}

	public void setInternet(String internet) {
		this.internet = internet;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
}