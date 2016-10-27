package com.hotelmgmt.service;

import java.util.ArrayList;

public interface RoomServices {
	

		public ArrayList<Integer> checkAvailability();

		public void updateAvailability(int roomNumber, String status);


}