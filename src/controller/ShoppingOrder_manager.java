package controller;
import entity.AgeGroup;
import entity.MovieGoer;
import entity.MovieScreening;
import entity.MovieTicket;
import entity.ScreeningFormat;
import entity.ShoppingOrder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.util.Scanner;

import utils.ScannerErrorHandler;
import utils.SerializeDB;


public class ShoppingOrder_manager implements ShoppingOrder_inf{
	Scanner se = new Scanner(System.in);
	ArrayList<MovieTicket> PaymentHist = new ArrayList<MovieTicket>(); 
	ArrayList<MovieGoer> people = new ArrayList<MovieGoer>(); 
	MovieScreening obj;
	
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
		this.PaymentHist = (ArrayList) SerializeDB.readSerializedObject("paymentHistory.dat");
		this.people = (ArrayList) SerializeDB.readSerializedObject("peoplenames.dat");
	}
	// TO UPDATE PAID TICKETS FROM PAYMENT HIST TO PAID.dat
	public void updatedata() {
		SerializeDB.writeSerializedObject("paymentHistory.dat", this.PaymentHist);
		SerializeDB.writeSerializedObject("peoplenames.dat", this.people);
	}
	
	// MAIN CALLS THIS FUNCTION FOR BOOKING TICKETS
	public void bookTicket(MovieScreening obj, char row, int col) throws ParseException {
		this.obj = obj;
		// GET BEFORE6 boolean
        boolean before6;
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        dateFormat.format(date);
        if (dateFormat.parse(dateFormat.format(date)).after(dateFormat.parse("18:00"))) {
            before6 = false;
        } else {
            before6 = true;
        }

        // GET AGEGROUP agegroup
        AgeGroup ageGroup;
        System.out.println("What is your age group?");
        System.out.println("1. Senior Citizen");
        System.out.println("2. Student");
        System.out.println("3. Regular");
        // Scanner scan = new Scanner(System.in);
        ScannerErrorHandler scan = new ScannerErrorHandler();
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                ageGroup = AgeGroup.SENIORCITIZEN;
                break;
            case 2:
                ageGroup = AgeGroup.STUDENT;
                break;
            case 3:
                ageGroup = AgeGroup.REGULAR;
                break;
            default:
                ageGroup = AgeGroup.REGULAR;
                break;
        }

        // GET WEEKDAY boolean
        boolean weekday;
        Calendar c1 = Calendar.getInstance();
        if ((c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) ||
                (c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)) {
            weekday = false;
        } else {
            weekday = true;
        }
        // System.out.println("weekday is" + weekday);

        // GET SCREENINGFORMAT screeningformat
        ScreeningFormat screeningFormat;
        System.out.println("What screening format?");
        System.out.println("1. 3D");
        System.out.println("2. Regular");
        System.out.println("3. Blockbuster");
        choice = scan.nextInt();
        switch (choice) {
            case 1:
                screeningFormat = ScreeningFormat.THREEDIMENSION;
                break;
            case 2:
                screeningFormat = ScreeningFormat.REGULAR;
                break;
            case 3:
                screeningFormat = ScreeningFormat.BLOCKBUSTER;
                break;
            default:
                screeningFormat = ScreeningFormat.REGULAR;
                break;
        }

        // GET DAY int
        int day;
        day = (int) c1.get(Calendar.DAY_OF_WEEK);
        // System.out.println("Day is" + day);
        
        MovieTicket mt = new MovieTicket(ageGroup, weekday, before6, screeningFormat, day, (float)0.00);
        
        // SET TICKET PRICE PRICE float
        mt.setPriceBasedOnAttributes();
        
        // SET THE REST OF THE TICKET ATTRIBUTES
        mt.setMovieScreening(this.obj);
        
        // SET THE SEAT NUMBER ONLY AFTER I CAN ACCESS THE CINEPLEX AND CINEMA OBJECTS
        String seatno = Character.toString(row) + Integer.toString(col);
        mt.setSeat(seatno);
        
        // SET THE TID OF TICKET   //TBC
        mt.setTID(this.obj.getShowDate() + this.obj.getCinema());
        this.neword.addticket(mt);
        
        // TELLS USER TICKET IS SUCCESSFULLY BOOKED
        System.out.println("Ticket has been booked");
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
		String person_no = se.nextLine();
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
