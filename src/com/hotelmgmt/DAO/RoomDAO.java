package com.hotelmgmt.DAO;

import java.util.ArrayList;

public interface RoomDAO {
	

		public ArrayList<Integer> checkAvailabilityDB();

		public void updateAvailabilityDB(int roomNumber, String status);


}
