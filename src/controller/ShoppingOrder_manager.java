package controller;
import entity.MovieGoer;
import entity.MovieTicket;
import entity.Movie;
import entity.ShoppingOrder;
import utils.ScannerErrorHandler;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import java.util.Scanner;
import utils.SerializeDB;


public class ShoppingOrder_manager implements ShoppingOrder_inf{
	Scanner sc = new Scanner(System.in);
	ArrayList<ShoppingOrder> BookingHistory = new ArrayList<ShoppingOrder>();           // BELONGS TO THIS INSTANCE OF SHOPORD MGR?
	ArrayList<ShoppingOrder> PaymentHistory = new ArrayList<ShoppingOrder>();  
	ArrayList<MovieGoer> people = new ArrayList<MovieGoer>();
	
	public ShoppingOrder_manager(){
		try {
			this.importData();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	// READ IN PAST PAYMENTS FROM TXT FILE

	public void importdata() throws FileNotFoundException, IOException, ClassNotFoundException{
		/* FileInputStream f = new FileInputStream(new File("payments.txt"));
		ObjectInputStream o = new ObjectInputStream(f);
		while((ShoppingOrder)o.readObject() != null) {
			this.PaymentHistory.add((ShoppingOrder)o.readObject());                    // IS THIS HOW YOU DO SERIALIZABLE??
		}
		o.close();

		FileInputStream b = new FileInputStream(new File("peoplenames.txt"));
		ObjectInputStream p = new ObjectInputStream(b);
		while((MovieGoer)p.readObject() != null) {
			this.people.add((MovieGoer)p.readObject());
		}
		p.close();

		*/
		// BENG IMPORT
		this.PaymentHistory = (ArrayList)SerializeDB.readSerializedObject("payments.dat");
		this.people = (ArrayList)SerializeDB.readSerializedObject("peoplenames.dat");
	}
	// UPDATES THE NEW SHOPPING ORDER THAT HAS BEEN PAID
	public void updatedata() throws FileNotFoundException, IOException, ClassNotFoundException{
		/*FileOutputStream fo = new FileOutputStream(new File("payments.txt"));
		ObjectOutputStream oo = new ObjectOutputStream(fo);
		oo.writeObject(this.PaymentHistory.get(this.PaymentHistory.size() - 1));              // DOES THIS OVERWRITE THE EXISTING TEXT FILE?
		oo.close();
		
		FileOutputStream bo = new FileOutputStream(new File("peoplenames.txt"));
		ObjectOutputStream po = new ObjectOutputStream(bo);
		po.writeObject(this.people.get(this.people.size() - 1));              // DOES THIS OVERWRITE THE EXISTING TEXT FILE?
		po.close();
		*/
		// BENG EXPORT
		SerializeDB.writeSerializedObject("payments.dat", this.PaymentHistory);
		SerializeDB.writeSerializedObject("peoplenames.dat", this.people);
	}
	
	// CREATE NEW SHOPPING ORDER AND ADD IT INTO BOOKING HISTORY, USUALLY DONE AT THE START
	public void createShoppingOrder() {
		ArrayList<MovieTicket> tixlist = new ArrayList<MovieTicket>();
		Date newdate = null;
		ShoppingOrder neword = new ShoppingOrder(tixlist, newdate);
		this.BookingHistory.add(neword);
	}
	// WHEN USER WANTS TO VIEW ALL THE TICKETS INSIDE CURRENT SHOPPING ORDER ( IE BOOKING)
	public void viewShoppingCart() {
		this.BookingHistory.get(this.BookingHistory.size() - 1).printalltickets();
	}
	
	// USER CLEAR BOOKING HISTORY
	public void clearBookingHistory() {
		this.BookingHistory.clear();
		System.out.println("Your booking history has been cleared! ");
	}
	
	// USER CLEAR PAYMENT HISTORY
	public void clearPaymentHistory() {
		this.PaymentHistory.clear();
		System.out.println("Your payment history has been cleared! ");
	}
	
	// DELETE CURRENT SHOPPING ORDER 
	public void deleteShoppingOrder() {
		this.BookingHistory.remove(this.BookingHistory.size() - 1);
	}
	
	// ADD A NEW TICKET INTO THE CURRENT SHOPPING CART
	public void bookTicket() throws ParseException, IOException {
		MovieTicketManager m = new MovieTicketManager();                // import nigel's class atop
		this.BookingHistory.get(this.BookingHistory.size() - 1).addtix(m.checkPrice());
		
		System.out.println("Please ");
		// Get only available screentime object from beng
		// Get ticket price, TID, agegroup, Screenformat, from nigel
		// Print available seats(occupied and non occupied) : Get available seats from nigel,
		// Upon payment, mark seats as occupied using function by nigel
		
		// What I need to print:
		// Movie name, Cineplex, cinema, seat number, date, time, price, TID, Screenformat, agegroup
		
	}
	
	// USER WANTS TO CHECKOUT THE CURRENT SHOPPING ORDER
	public void makePurchase() {
		ScannerErrorHandler sc = new ScannerErrorHandler();
		// FINDS TODAY'S DATE AND SET THE SHOPPING ORDER DATEOFPURCHASE TO TODAY , TIME NEEDED?
		Date todaydate = new Date();
		this.BookingHistory.get(this.BookingHistory.size() - 1).setpaymentDate(todaydate);
		
		// TRANSFER SHOPPING ORDER FROM BOOKING HISTORY TO PAYMENT HISTORY, AND REMOVE FROM BOOKING HISTORY
		this.PaymentHistory.add(this.BookingHistory.get(this.BookingHistory.size() - 1));
		this.BookingHistory.clear();
		
		
		// COLLECT MOVIEGOER INFORMATION, CREATE NEW MOVIEGOER OBJECT AND STORE INTO PEOPLE
		System.out.println("Please enter your name here : ");
		String person_name = sc.nextLine();
		System.out.println("Please enter your email here : ");
		String person_email = sc.nextLine();
		System.out.println("Please enter your mobile number here : ");
		double person_no = sc.nextDouble();
		MovieGoer g = new MovieGoer(person_name, person_no, person_email);
		this.people.add(g);
		
		// PAYMENT SUCCESSFUL MESSAGE AND PRINT ALL TICKETS INSIDE SHOPPING ORDER
		System.out.println("Thank you! you have successfully made payment");
		System.out.println("Here is a summary of the tickets you purchased : ");
		System.out.println("Name : " + g.getName() + " Email : " + g.getEmail() + " Number : " + g.getMobileNumber());
		this.PaymentHistory.get(this.PaymentHistory.size() - 1).printalltickets();
		
		// UPDATE BOTH PEOPLE DATA AND PAYMENTHISTORY DATA IN THE TXT FILES
		try {
			this.updateData();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// USER WANTS TO DELETE TICKET FROM CURRENT SHOPPING ORDER
	public void deleteTicket() {
		// SHOULD WE HAVE SUCH A FUNCTION ??
	}
	
	// TO VIEW ENTIRE PAYMENT HISTORY OF PAID TICKETS
	public void viewPaymentHistory() {
		for(int i = 0; i< this.PaymentHistory.size(); i++) {
			System.out.println("Name : " + this.people.get(i).getName() + " Email : " + this.people.get(i).getEmail() + " Number : " + this.people.get(i).getMobileNumber());
			this.PaymentHistory.get(i).printalltickets();
		}
	}
	
}
