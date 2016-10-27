package com.hotelmgmt.bean;

public class Room {
	int roomNumber;
	String roomCategory;
	String availability;
	double price;
	// All getter & setter are build
	public int getRoomNumber() {
		return roomNumber;
	}
	
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	public String getRoomCategory() {
		return roomCategory;
	}
	
	public void setRoomCategory(String roomCategory) {
		this.roomCategory = roomCategory;
	}
	
	public String getAvailability() {
		return availability;
	}
	
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	


}
