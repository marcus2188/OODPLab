package controller;
import entity.MovieGoer;
import entity.MovieTicket;
import entity.Movie;
import entity.ShoppingOrder;
import utils.ScannerErrorHandler;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.util.Scanner;
import utils.SerializeDB;


public class ShoppingOrder_manager implements ShoppingOrder_inf{
	Scanner se = new Scanner(System.in);
	ArrayList<MovieTicket> PaymentHist = new ArrayList<MovieTicket>(); 
	ArrayList<MovieGoer> people = new ArrayList<MovieGoer>(); 
	
	// AUTO CREATE NEW SHOPPING ORDER ON CALLING MANAGER
	ArrayList<MovieTicket> tixlist = new ArrayList<MovieTicket>();
	Date newdate = null;
	ShoppingOrder neword = new ShoppingOrder(tixlist, newdate);
	
	// CONSTRUCTOR RUNS WHEN MAIN CREATES MY MANAGER INSTANCE
	ShoppingOrder_manager(){
		this.importdata();
	}
	
	// TO FETCH ALL PAST PAID TICKETS INTO THE PAYMENTHIST
	public void importdata() {
		this.PaymentHist = (ArrayList) SerializeDB.readSerializedObject("paid.dat");
		this.people = (ArrayList) SerializeDB.readSerializedObject("peoplenames.dat");
	}
	// TO UPDATE PAID TICKETS FROM PAYMENT HIST TO PAID.dat
	public void updatedata() {
		SerializeDB.writeSerializedObject("paid.dat", this.PaymentHist);
		SerializeDB.writeSerializedObject("peoplenames.dat", this.people);
	}
	
	// MAIN CALLS THIS FUNCTION FOR BOOKING TICKETS
	public void bookTicket() throws IOException, ParseException {
		// ASK USER WHAT MOVIE NAME THEY WANNA BOOK
		System.out.println("Please enter your movie name here :");
		String movname = se.nextLine();
		
		// PRINT OUT ALL THE AVAILABLE MOVIE SCREENING DATES AND TIMES
		System.out.println("Here are all the available movie slots for booking");
		System.out.println("---------------------------------------------------");
		// get from beng this part
		System.out.println("---------------------------------------------------");
		System.out.println("Please choose a slot here (1-X) : ");
		int choice = se.nextInt();
		// LET USER CHOOSE THE NUMBERS ACCORDINGLY :
		if(choice == 1) {
			// Take the relevant object and get its date, time, etc.
		}
		else if(choice == 2) {
			// store the selected moviescreening object's data into suitable variables
			// date = obj.date 
			// time = obj.time 
			// cineplex = obj.cineplex
			// cinema = obj.cinema
			// cinemaid = obj.cinema.cinemaID
		}
		MovieTicketManager m = new MovieTicketManager();
		MovieTicket mt = m.checkPrice();
		// mt.setCinema = cinema  - STRING
		// mt.setCineplex = cineplex - STRING
		// mt.setDate = date   - STRING
		// mt.setTime = time   - INT
		// mt.price is already there 
		// mt.agegroup already there
		// mt.setTID = "datestr" + "cinemaID str"
		// mt.setMOVNAME = movietitle
		
		// PRINT OUT THE ENTIRE SEAT LAYOUT AVAILABLE IN THAT CINEMA HALL RETURNED BY BENG'S OBJ
		// obj.cinema.printSeats()
		System.out.println("Please select the row and column of your desired seat :");
		System.out.println("ROW (------>) :");
		char row = se.next().charAt(0);
		System.out.println("COLUMN (^) :");
		char col = se.next().charAt(0);
		// use an if statement here to verify if seat is indeed unoccupied using obj.cinema.checkOccupied()
		// BOOK/MARK THE SEAT AS OCCUPIED
		// obj.cinema.reserveSeat(char row, char col)
		
		// mt.setSeat = new Seat(row, col)
		neword.addtix(mt);
	}
	
	// MAIN CALLS THIS FUNCTION TO ACTUALLY PURCHASE THE TICKETS INSIDE SHOPPING ORDER
	public void makePurchase() {
		// PAYMENT CONFIRMATION
		System.out.println("Are you sure you want to pay for your shopping order? ");
		System.out.println("1. YES ");
		System.out.println("2. NO ");
		int c = se.nextInt();
		if(c != 1) {
			System.out.println("Alright then ");
			return;
		}
		// COLLECT MOVIEGOER INFORMATION, CREATE NEW MOVIEGOER OBJECT AND STORE INTO PEOPLE
		System.out.println("Please enter your name here : ");
		String person_name = se.nextLine();
		System.out.println("Please enter your email here : ");
		String person_email = se.nextLine();
		System.out.println("Please enter your mobile number here : ");
		double person_no = se.nextDouble();
		MovieGoer g = new MovieGoer(person_name, person_no, person_email);
		this.people.add(g);
	
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		
		// MOVE ALL TICKETS FROM SHOPPING ORDER INTO PAYMENT HISTORY ARRAYLIST
		for(int i = 0; i < this.neword.getbacktix().size();i++) {
			PaymentHist.add(neword.getbacktix().get(i));
		}
		
		// PRINT PAYMENT SUMMARY
		System.out.println("Congratulations! Your payment is successful !");
		System.out.println("Payment Summary ");
		System.out.println("--------------------------");
		this.viewcurrentSO();
		System.out.println("--------------------------");
		this.neword.resetcart();
		this.updatedata();
   }
	
	// SO NIGEL DID YOU UPDATE?
	// USER WANTS TO VIEW ALL TICKETS INSIDE CURRENT SHOPPING ORDER
	public void viewcurrentSO() {
		for(int k = 0; k < this.neword.getbacktix().size(); k++) {
			this.neword.getbacktix().get(k).printDetails();
			// THIS FUNCTION PRINT WILL BE UPDATED BY NIGEL
		}
	}
	
	// USER WANTS TO EMPTY CURRENT SHOPPING ORDER
	public void dumpcurrentSO(){
		this.neword.resetcart();
	}
	
	// USER WANTS TO VIEW ENTIRE PAYMENT HISTORY
	public void viewallpaidtix() {
		this.importdata();
		System.out.println("Your entire payment history is as follows : ");
		for(int i = 0; i< this.PaymentHist.size(); i++) {
			this.PaymentHist.get(i).printDetails();
			// NIGEL UPDATE PRINT FUNCTION
		}
		System.out.println("Thank you for using MOBLIMA!! : ");
	}
	
	// USER WANTS TO GET ALL MOVIEGOER DETAILS
	public void seepeople() {
		System.out.println("These are all the people who purchased tickets : ");
		for(int i = 0; i< this.people.size(); i++) {
			System.out.println("Name : " + this.people.get(i).getName() + " | Email : " + this.people.get(i).getEmail() + " | Number : " + this.people.get(i).getMobileNumber())
			;
		}
	}
}
