package controller;
import entity.MovieGoer;
import entity.MovieTicket;
import entity.ShoppingOrder;

import java.text.ParseException;
import java.util.ArrayList;
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
	public ShoppingOrder_manager(){
		this.importdata();
	}
	
	// TO FETCH ALL PAST PAID TICKETS INTO THE PAYMENTHIST
	public void importdata() {
		// TODO: create new dat file after nigel's movieTicket is done
		this.PaymentHist = (ArrayList) SerializeDB.readSerializedObject("paid.dat");
		this.people = (ArrayList) SerializeDB.readSerializedObject("peoplenames.dat");
	}
	// TO UPDATE PAID TICKETS FROM PAYMENT HIST TO PAID.dat
	public void updatedata() {
		SerializeDB.writeSerializedObject("paid.dat", this.PaymentHist);
		SerializeDB.writeSerializedObject("peoplenames.dat", this.people);
	}
	
	// MAIN CALLS THIS FUNCTION FOR BOOKING TICKETS
	public void bookTicket() throws ParseException {
		neword.addticket(); 
	}
	
	// MAIN CALLS THIS FUNCTION TO ACTUALLY PURCHASE THE TICKETS INSIDE SHOPPING ORDER
	public void makePurchase() {
		// PAYMENT CONFIRMATION
		System.out.println("Are you sure you want to pay for your shopping order? ");
		System.out.println("1. YES ");
		System.out.println("2. NO ");
		int c = se.nextInt();
		if(c != 1) {
			System.out.println("Alright then, payment rejected ");
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
	
		
		// MOVE ALL TICKETS FROM SHOPPING ORDER INTO PAYMENT HISTORY ARRAYLIST
		for(int i = 0; i < this.neword.returnticketarray().size();i++) {
			PaymentHist.add(neword.returnticketarray().get(i));
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
	
	// USER WANTS TO VIEW ALL TICKETS INSIDE CURRENT SHOPPING ORDER
	public void viewcurrentSO() {
		if(this.neword.returnticketarray().isEmpty()) {
			System.out.println("The current shopping order is empty");
			return;
		}
		else {
			for(int k = 0; k < this.neword.returnticketarray().size(); k++) {
				this.neword.returnticketarray().get(k).printDetails();
			}
		}
	}
	
	// USER WANTS TO EMPTY CURRENT SHOPPING ORDER
	public void dumpcurrentSO(){
		if(this.neword.returnticketarray().isEmpty()) {
			System.out.println("ERROR");
			System.out.println("The current shopping order is already empty");
			return;
		}
		else {
			this.neword.resetcart();
		}
	}
	
	// USER WANTS TO VIEW ENTIRE PAYMENT HISTORY
	public void viewallpaidtix() {
		System.out.println("Your entire payment history is as follows : ");
		for(int i = 0; i< this.PaymentHist.size(); i++) {
			this.PaymentHist.get(i).printDetails();
			
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
