package io.srqsoftware;

import java.util.Date;

public class User {
	private int userId;
	public int getUserId() { return userId; }
	public void setUserId(int userId) { this.userId = userId; }
	
	private String rfidId;
	public String getRfidId() { return rfidId; }
	public void setRfidId(String rfidId) { this.rfidId = rfidId; }
	
	private Date timestamp;
	public Date getTimestamp() { return timestamp; }
	public void setTimestamp(Date timestamp) { this.timestamp = timestamp != null ? new Date(timestamp.getTime()) : null; }
	
	private String firstName;
	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }

	private String lastName;
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	
	private int status;
	public int getStatus() { return status; }
	public void setStatus(int status) { this.status = status; }
	
}
