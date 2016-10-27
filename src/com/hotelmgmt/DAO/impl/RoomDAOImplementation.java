package com.hotelmgmt.DAO.impl;
import java.sql.*;
import java.util.ArrayList;

import com.hotelmgmt.DAO.ConnectionDAO;
import com.hotelmgmt.DAO.RoomDAO;



public class RoomDAOImplementation implements RoomDAO {
	static Connection connection;
	String availability;
	

	@Override
	public ArrayList<Integer> checkAvailabilityDB() {
		connection = ConnectionDAO.openConnection();
		//int roomNumber=room.getRoomNumber();
		ArrayList<Integer> availableRooms=new ArrayList<Integer>();
		String query="select roomno from room where availability=?";
		String availability="y";
		PreparedStatement pst=null;
		try{
			
			pst=connection.prepareStatement(query);
			pst.setString(1, availability);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
				availableRooms.add(rs.getInt(1));
					
		}
		catch(SQLException e){
		System.out.println((e.getMessage()));
		}
		return availableRooms;
	}

	@Override
	public void updateAvailabilityDB(int roomNumber, String status) {
		connection = ConnectionDAO.openConnection();
		String query="update room set availability=? where roomno=?";
		PreparedStatement pst;
		try{
			pst=connection.prepareStatement(query);
			pst.setString(1, status);
			pst.setInt(2, roomNumber);
			pst.executeQuery();	
		}
		catch(SQLException e){
			System.out.println((e.getMessage()));
			}
	}
	
	

}
