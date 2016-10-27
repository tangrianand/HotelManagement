package com.hotelmgmt.service.implementation;

import java.util.ArrayList;

import com.hotelmgmt.DAO.GuestDAO;
import com.hotelmgmt.DAO.impl.GuestDAOImplementation;
import com.hotelmgmt.bean.Guest;

import com.hotelmgmt.exceptions.GuestIdNotFoundException;
import com.hotelmgmt.service.GuestServices;

public class GuestServicesImplementation implements GuestServices{
    GuestDAO guestDAO=new GuestDAOImplementation();
	@Override
	public void addGuest(Guest guest) {
		guestDAO.addGuesttoDB(guest);
		
	}

	@Override
	public void updateGuest(Guest guest, int id) throws GuestIdNotFoundException {
		guestDAO.updateGuesttoDB(guest,id);
		
	}

	@Override
	public void deleteGuest(int id) throws GuestIdNotFoundException {
		guestDAO.deleteGuesttoDB(id);
		
		
	}

	@Override
	public ArrayList<Guest> getAllGuests() {
		return guestDAO.getAllGuestsfromDB();
		
	}

	@Override
	public ArrayList<String[]> getGuestsByAccomodation() {
		return guestDAO.getGuestsByAccomodationfromDB();
	}

	@Override
	public void guestCheckin(int roomno, int guestid, String checkInDate, int duration) {
		 guestDAO.guestCheckinDB(roomno,guestid,checkInDate,duration);
		 

		
	}

	@Override
	public double finalBill(int guestid) {
	        return guestDAO.finalBillfromDB(guestid);
	}

}
