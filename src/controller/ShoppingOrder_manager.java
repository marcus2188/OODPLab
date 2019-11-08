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
	
	ShoppingOrder_manager(){
		// import all past booking history and payment history and people
	}
	public void bookTicket() throws IOException, ParseException {
		MovieTicketManager m = new MovieTicketManager();
		
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
		}
		MovieTicket mt = m.checkPrice();
		
	}
	
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
	
		// SET THE TID OF ALL TICKETS BEFORE MOVING INTO PAYMENT HIST
		String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		for(int i = 0; i < this.neword.getbacktix().size();i++) {
			neword.getbacktix().get(i).setTID(/* TBC: String of ticket date + string of cinema code - YYYYMMDDXXX*/);
		}
		
		// MOVE ALL TICKETS FROM SHOPPING ORDER INTO PAYMENT HISTORY ARRAYLIST
		for(int i = 0; i < this.neword.getbacktix().size();i++) {
			PaymentHist.add(neword.getbacktix().get(i));
		}
		this.neword.resetcart();
		
		
   }
	
}
