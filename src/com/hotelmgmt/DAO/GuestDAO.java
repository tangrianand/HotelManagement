package com.hotelmgmt.DAO;

import java.util.ArrayList;

import com.hotelmgmt.bean.Guest;

import com.hotelmgmt.exceptions.GuestIdNotFoundException;

public interface GuestDAO {

	public void addGuesttoDB(Guest guest);

	public void updateGuesttoDB(Guest guest,int id) throws GuestIdNotFoundException;

	public void deleteGuesttoDB(int id) throws GuestIdNotFoundException;
	
	public void guestCheckinDB(int roomno,int guestid, String checkInDate, int duration);

	public ArrayList<Guest> getAllGuestsfromDB();

	public ArrayList<String[]> getGuestsByAccomodationfromDB();
	
	public double finalBillfromDB(int guestid);
}
