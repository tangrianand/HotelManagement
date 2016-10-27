package com.hotelmgmt.service;

import java.util.ArrayList;

import com.hotelmgmt.bean.Guest;

import com.hotelmgmt.exceptions.GuestIdNotFoundException;

public interface GuestServices {

	public void addGuest(Guest guest);

	public void updateGuest(Guest guest, int id) throws GuestIdNotFoundException;

	public void deleteGuest(int id) throws GuestIdNotFoundException;
	
	public void guestCheckin(int roomno,int guestid, String checkInDate, int duration);

	public ArrayList<Guest> getAllGuests();

	public ArrayList<String[]> getGuestsByAccomodation();
	
	public double finalBill(int guestid);
}
