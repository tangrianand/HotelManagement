package com.hotelmgmt.client;

import java.util.ArrayList;
import java.util.Scanner;
import com.hotelmgmt.bean.Guest;
import com.hotelmgmt.exceptions.GuestIdNotFoundException;
import com.hotelmgmt.service.GuestServices;
import com.hotelmgmt.service.RoomServices;
import com.hotelmgmt.service.implementation.GuestServicesImplementation;
import com.hotelmgmt.service.implementation.RoomServicesImplementation;

public class Admin {

	public static void main(String[] args) throws GuestIdNotFoundException {
		Scanner scanner= new Scanner(System.in);
		int choiceForExit=0;
		ArrayList<Guest> guestList=null;  //list of all the guests
		ArrayList<String[]> guests=null;  //list of guests according to accomodation
		RoomServices roomser= new RoomServicesImplementation();   
		GuestServices guestser=new GuestServicesImplementation();
		    while(choiceForExit!=6){
				System.out.println("Enter the choice number");
				System.out.println("1. check availability of a room");
				System.out.println("2. update availability of a room");
				System.out.println("3. insert guest details");
				System.out.println("4. accomodate guest");
				System.out.println("5. update guest details");
				System.out.println("6. delete a guest");
				System.out.println("7. get a list of all the guests");
				System.out.println("8. get a list of guests by room category");
				System.out.println("9. Calculate Bill for a guest");
				System.out.println("10. exit ");
				int choice= scanner.nextInt();
				
				switch(choice){
				case 1:
					ArrayList<Integer> avlblRooms=new ArrayList<Integer>();  //List of Available rooms
					avlblRooms=roomser.checkAvailability();
					System.out.println("Available Rooms are: ");
					for(Integer available:avlblRooms){
				    	 System.out.println(available);
				     }
				    break;
				case 2:
					System.out.println("Enter the room number: ");
					int num=scanner.nextInt();
					System.out.println("Enter the status: ");
					String status=scanner.next();
					roomser.updateAvailability(num, status);
					break;
				case 3:
					System.out.println("Please Enter the guest details");
					System.out.println("Enter the guest name");
					String name=scanner.next();
					System.out.println("Enter the guest address");
					String address=scanner.next();
					System.out.println("Enter the guest contact");
					long contact=scanner.nextLong();
					Guest new_guest=new Guest();              // Create a new guest object
					new_guest.setName(name);
					new_guest.setAddress(address);
					new_guest.setContact(contact);
					guestser.addGuest(new_guest);             // Add guest to the database
					break;
				case 4:
					System.out.println("Enter the room number");
					int roomNumber=scanner.nextInt();
					System.out.println("Enter the guest id");
					int guestId=scanner.nextInt();
					System.out.println("Enter the check-in date");
					String checkInDate=scanner.next();
					System.out.println("Enter the duration of stay");
					int duration=scanner.nextInt();
					roomser.updateAvailability(roomNumber, "n");      // automatically update availability to "no"
					guestser.guestCheckin(roomNumber,guestId, checkInDate, duration);
					break;
				case 5:
					System.out.println("Please Enter the guest details");
					System.out.println("Enter the guest id");
					int id=scanner.nextInt();
					System.out.println("Enter the guest name");
					String update_name=scanner.next();
					System.out.println("Enter the guest address");
					String update_address=scanner.next();
					System.out.println("Enter the guest contact");
					long update_contact=scanner.nextLong();
					Guest update_guest=new Guest();
					update_guest.setName(update_name);
					update_guest.setAddress(update_address);
					update_guest.setContact(update_contact);
					guestser.updateGuest(update_guest,id);        // Automatically update guest list
					break;
				case 6:
					System.out.println("Enter the guest id");
					int delete_id=scanner.nextInt();
					guestser.deleteGuest(delete_id) ;      //Automatically Delete any guest from list
					break;
				case 7:
					guestList=guestser.getAllGuests();
				     for(Guest guest1:guestList){
				    	 System.out.println(guest1);     
				     }
				     break;
				case 8:
					guests=guestser.getGuestsByAccomodation();
				     for(String[] i:guests){
				    	 System.out.println("Name: "+i[0]+", Address: "+i[1]+", Room Category: "+i[2]);
				    	 
				    	 
				     }
				     break;
				case 9:
					System.out.println("Enter the guest id");
					int bill_id=scanner.nextInt();
					double bill=guestser.finalBill(bill_id) ;
					System.out.println("The total bill is: "+bill);
					break;
					
					
					
				default:
					System.out.println("wrong choice");
					break;
				}
				
					
				
		    }
		    scanner.close();

}
}
