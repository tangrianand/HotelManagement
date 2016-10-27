package com.hotelmgmt.DAO.impl;

import java.sql.*;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.hotelmgmt.exceptions.GuestIdNotFoundException;
import com.hotelmgmt.DAO.GuestDAO;

import com.hotelmgmt.DAO.ConnectionDAO;

import com.hotelmgmt.bean.Guest;

public class GuestDAOImplementation implements GuestDAO{
	static Logger logger = Logger.getLogger(GuestDAO.class);

	static Connection connection;
	@Override
	public void addGuesttoDB(Guest guest) {            // To add new guest to db
		String name=guest.getName();
		String address=guest.getAddress();
		long contact=guest.getContact();
		connection=ConnectionDAO.openConnection();
		String query="insert into guest_details (name,address,contact) values(?,?,?)";
		PreparedStatement pst=null;
		try
		{
			pst=connection.prepareStatement(query);
			pst.setString(1, name);
			pst.setString(2,address);
			pst.setLong(3, contact);
			pst.execute();
			logger.info("Inserted Sucessfully");
		}
		catch(SQLException e)
		{
			logger.error(e.getMessage());
		}	
	}

	@Override
	public void updateGuesttoDB(Guest guest, int id) throws GuestIdNotFoundException {     // To update guest info in db
		connection = ConnectionDAO.openConnection();
		String name=guest.getName();
		String address=guest.getAddress();
		long contact=guest.getContact();
		String query="update guest_details set name=?,address=?,contact=? where id=?";
		PreparedStatement pst;
		String validate="select * from guest_details where id=?";
		try{
			pst=connection.prepareStatement(validate);
			pst.setInt(1, id);
			ResultSet rs=pst.executeQuery();
			if(!rs.next())
				throw new GuestIdNotFoundException("OOPS! No such Guest Id!");
			else{
			pst=connection.prepareStatement(query);
			pst.setString(1, name);
			pst.setString(2, address);
			pst.setLong(3, contact);
			pst.setInt(4, id);
			pst.executeQuery();	
			logger.info("Updated Sucessfully");
			}
		}
		catch(SQLException e){
			logger.error((e.getMessage()));
			}
		
	}

	@Override
	public void deleteGuesttoDB(int id) throws GuestIdNotFoundException {      // to delete guest from DB after check out
		connection = ConnectionDAO.openConnection();
		PreparedStatement pst;
		String validate="select * from guest_details where id=?";
		try{
				pst=connection.prepareStatement(validate);
				pst.setInt(1, id);
				ResultSet rs=pst.executeQuery();
				if(!rs.next())
					throw new GuestIdNotFoundException("OOPS! No such Guest Id!");
				
		
				else{	
		             String query="delete from guest_details where id=?";
			         pst=connection.prepareStatement(query);
			         pst.setInt(1, id);
			         pst.executeQuery();
			         logger.info("Deleted Sucessfully");
				    }
		   }
		catch(SQLException e){
			logger.error((e.getMessage()));
			}	
		
	}

	@Override
	public ArrayList<Guest> getAllGuestsfromDB() {                        // to display the list of all guests 
		 ArrayList<Guest> guestList=new ArrayList<Guest>();
		 PreparedStatement pst=null;
		 try{
				connection = ConnectionDAO.openConnection();
				String getQuery="Select * from guest_details";
				pst=connection.prepareStatement(getQuery);
				ResultSet rs=pst.executeQuery();
				while(rs.next()){
					int guestId=rs.getInt(1);
					String guestName=rs.getString(2);
					String guestAddress=rs.getString(3);
					long contact=rs.getLong(4);
					Guest guest=new Guest();
					guest.setId(guestId);
					guest.setName(guestName);
					guest.setAddress(guestAddress);
					guest.setContact(contact);
					guestList.add(guest);
				}
		 }catch(SQLException e){
			 logger.error(e.getMessage());
		 }
		 return guestList;
	}

	@Override
	public ArrayList<String[]> getGuestsByAccomodationfromDB() {                // to display all guests according to their accomodation
		ArrayList<String[]> guestList=new ArrayList<String[]>();
		 PreparedStatement pst=null;
		 try{
				connection = ConnectionDAO.openConnection();
				String getQuery="Select guest_details.name,guest_details.address,room.type from accomodation JOIN guest_details ON guest_details.id=accomodation.id JOIN room ON room.roomno=accomodation.roomno";
				pst=connection.prepareStatement(getQuery);
				ResultSet rs=pst.executeQuery();
				while(rs.next()){
					String guestName=rs.getString(1);
					String guestAddress=rs.getString(2);
					String type=rs.getString(3);
					String object[]={guestName,guestAddress,type};
					
			
					guestList.add(object);
				}
		 }catch(SQLException e){
			 logger.error(e.getMessage());
		 }
		 return guestList;
		
	}

	@Override
	public void guestCheckinDB(int roomno, int guestid, String checkInDate, int duration) {    // To chinkIn the guest
		connection=ConnectionDAO.openConnection();
		String query="insert into accomodation values(?,?,?,?)";
		PreparedStatement pst=null;
		try
		{
			pst=connection.prepareStatement(query);
			pst.setInt(1, roomno);
			pst.setInt(2,guestid);
			pst.setString(3, checkInDate);
			pst.setInt(4, duration);
			pst.execute();
			logger.info("Guest accomodation Sucessfull");
		}
		catch(SQLException e)
		{
			logger.error(e.getMessage());
		}	
		
		
	}

	@Override
	public double finalBillfromDB(int guestid) {      //To generate final bill of the guest  from DB after checkOut
		connection=ConnectionDAO.openConnection();
		String query="select room.price,accomodation.duration from accomodation JOIN room ON room.roomno=accomodation.roomno where id=? ";
		PreparedStatement pst=null;
		int price=0,duration=0;
		double total=0;
		try
		{
			pst=connection.prepareStatement(query);
			pst.setInt(1, guestid);
			ResultSet rs=pst.executeQuery();
			if(rs.next()){
				price=rs.getInt(1);
			    duration=rs.getInt(2);
			}
			 total=price*duration;	
		
     	}
		catch(SQLException e)
		{
			logger.error(e.getMessage());
		}	
		return total;

}
}
