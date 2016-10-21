package io.srqsoftware;

import java.util.Date;

public class UserDetail {

	private int userId;
	public int getUserId() { return userId; }
	public void setUserId(int userId) { this.userId = userId; }
	
	private String emailAddress;
	public String getEmailAddress() { return emailAddress; }
	public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
	
	private String address1;
	public String getAddress1() { return address1; }
	public void setAddress1(String address1) { this.address1 = address1; }
	
	private String address2;
	public String getAddress2() { return address2; }
	public void setAddress2(String address2) { this.address2 = address2; }
	
	private String city;
	public String getCity() { return city; }
	public void setCity(String city) { this.city = city; }
	
	private String state;
	public String getState() { return state; }
	public void setState(String state) { this.state = state; }

	private String zipcode;
	public String getZipcode() { return zipcode; }
	public void setZipcode(String zipcode) { this.zipcode = zipcode; }
}
