package com.hotelmgmt.service.implementation;

import java.util.ArrayList;

import com.hotelmgmt.DAO.RoomDAO;

import com.hotelmgmt.DAO.impl.RoomDAOImplementation;

import com.hotelmgmt.service.RoomServices;

public class RoomServicesImplementation implements RoomServices {
	RoomDAO roomDAO=new RoomDAOImplementation();
	@Override
	public ArrayList<Integer> checkAvailability() {
		return roomDAO.checkAvailabilityDB();
		
	}

	@Override
	public void updateAvailability(int roomNumber, String status) {
		roomDAO.updateAvailabilityDB(roomNumber, status);
		
	}

}
