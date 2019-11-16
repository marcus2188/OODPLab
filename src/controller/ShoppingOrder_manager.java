package controller;
import entity.AgeGroup;
import entity.MovieGoer;
import entity.MovieScreening;
import entity.MovieTicket;
import entity.ScreeningFormat;
import entity.ShoppingOrder;
import entity.Seat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.util.Scanner;

import utils.ScannerErrorHandler;
import utils.SerializeDB;


/**
 A manager to handle movie goer request to book/purchase tickets.
 @author SS3_Group4
 @version 1.0
 @since 2019-11-15
*/

public class ShoppingOrder_manager implements ShoppingOrder_inf{
	/** 
	* The scanner that handles error
	*/
	private ScannerErrorHandler se = new ScannerErrorHandler();
	/** 
	* An array list of movie ticket that represents payment history
	*/
	private ArrayList<MovieTicket> PaymentHist = new ArrayList<MovieTicket>(); 
	/** 
	* An array list of movie goer
	*/
	private ArrayList<MovieGoer> people = new ArrayList<MovieGoer>(); 
	/** 
	* An array list of dates that represents holidays
	*/
	private ArrayList<Date> holidays = new ArrayList<Date>();
	
	/** 
	* An array list of tickets in shopping order
	*/
	private ArrayList<MovieTicket> tixlist = new ArrayList<MovieTicket>();
	/** 
	* Purchase date
	*/
	private Date newdate = null;
	/** 
	* A shopping order
	*/
	private ShoppingOrder neword = new ShoppingOrder(tixlist, newdate);
	
	/** 
	* Creates a shopping order manager and import data
	*/
	public ShoppingOrder_manager(){
		this.importdata();
	}
	
	/** 
	* Read data from paymentHistory.dat, peoplenames.dat and dates.dat
	*/
	public void importdata() {
		this.PaymentHist = (ArrayList) SerializeDB.readSerializedObject("paymentHistory.dat");
		this.people = (ArrayList) SerializeDB.readSerializedObject("peoplenames.dat");
		this.holidays = (ArrayList) SerializeDB.readSerializedObject("dates.dat");
	}

	/** 
	* Write data to paymentHistory.dat and peoplenames.date
	*/
	public void updatedata() {
		SerializeDB.writeSerializedObject("paymentHistory.dat", this.PaymentHist);
		SerializeDB.writeSerializedObject("peoplenames.dat", this.people);
	}
	
	/** 
     * Book a ticket
     * @param obj The movie screening chosen
     * @param seat The seat chosen
     */
	public void bookTicket(MovieScreening obj, Seat seat) throws ParseException {
		System.out.println("Please enter ");
		// GET BEFORE6 boolean
        boolean before6;
        Date date = obj.getShowDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyyMMdd");

		Calendar c = Calendar.getInstance();
		int timing = obj.getShowTime();
		//int minute = obj.getShowTime() % 100;
		if(timing < 1800)
			before6 = true;
		else
			before6 = false;
//		c.set(Calendar.HOUR, hour);
//		c.set(Calendar.MINUTE, minute);
//		Date d = c.getTime();
//
//        // System.out.println("time "+ dateFormat.format(d));
//        if (dateFormat.parse(dateFormat.format(d)).after(dateFormat.parse("18:00"))) {
//            before6 = false;
//        } else {
//            before6 = true;
//        }

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
        c1.setTime(date);
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
        choice = scan.nextInt();
        switch (choice) {
            case 1:
                screeningFormat = ScreeningFormat.THREEDIMENSION;
                break;
            case 2:
                screeningFormat = ScreeningFormat.REGULAR;
                break;
            default:
                screeningFormat = ScreeningFormat.REGULAR;
                break;
        }

		/*
        System.out.println("Would you like to upgrade to platinum suite for $10? (y/n)");
        boolean isPlatinum;
        choice = scan.next().charAt(0);

        while (choice != 'Y'
		 && choice != 'y'
		&& choice != 'n'
		&& choice != 'N') {
        	System.out.println("Invalid choice, please try again.");
        	choice = scan.next().charAt(0);
		}
        if (choice == 'y' || choice == 'Y') {
        	isPlatinum = true;
		} else if (choice == 'n' || choice == 'N') {
        	isPlatinum = false;
		} else {
        	isPlatinum = false;
		}*/
		boolean isPlatinum = obj.getCinema().getPlatinum();


        // GET DAY int
        int day;
        day = (int) c1.get(Calendar.DAY_OF_WEEK);
        // System.out.println("Day is" + day);
        // System.out.println("1");
        MovieTicket mt = new MovieTicket(ageGroup, weekday, before6, screeningFormat, day, (float)-1.00, isPlatinum);
        
        // SET TICKET PRICE PRICE float
        mt.setPriceBasedOnAttributes();
        
        //price
        // SET THE REST OF THE TICKET ATTRIBUTES
        mt.setMovieScreening(obj);
        
        for (int i = 0; i < this.holidays.size(); i++) {
            Date holiday = (Date) this.holidays.get(i);
            if (obj.getShowDate().equals(holiday)) {
            	mt.setHolidayRate();
            }
        }
        // SET THE SEAT NUMBER ONLY AFTER I CAN ACCESS THE CINEPLEX AND CINEMA OBJECTS
        //String seatno = Character.toString(row) + Integer.toString(col);
        mt.setSeat(seat);
        // SET THE TID OF TICKETDate date = Calendar.getInstance().getTime();   
		String id = obj.getCinema().getCinemaID() + dateFormat2.format(obj.getShowDate()) + String.valueOf(obj.getShowTime());
		mt.setTID(id);
        //mt.setTID(this.obj.getShowDate() + this.obj.getCinema());
        this.neword.addticket(mt);
        
        mt.printPrice();
        // TELLS USER TICKET IS SUCCESSFULLY BOOKED
        System.out.println("Ticket has been added to your shopping cart");
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
		System.out.println("We need to collect your credentials");
		System.out.println("");
		System.out.println("Please enter your full name : ");
		String person_name = se.nextLine();
		System.out.println("Please enter your email : ");
		String person_email = se.nextLine();
		System.out.println("Please enter your mobile number : ");
		String person_no = se.nextLine();
		/*if(!person_no.matches("[0-9]{8}") || !person_name.matches("[a-zA-Z]+") || !person_email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$") || !(person_name == person_email) ) {
			System.out.println("There is an error in one of your information entered");
			System.out.println("Payment denied");
			System.out.println("heading back.... ");
			System.out.println(":(");
			return;
		}*/
		MovieGoer g = new MovieGoer(person_name, person_no, person_email);
		this.people.add(g);
	
		
		// MOVE ALL TICKETS FROM SHOPPING ORDER INTO PAYMENT HISTORY ARRAYLIST AND UPDATE TICKETSALES
		for(int i = 0; i < this.neword.returnticketarray().size();i++) {
			PaymentHist.add(neword.returnticketarray().get(i));
			neword.returnticketarray().get(i).getMovieScreening().getMovie().addTicketSales();
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
	
	/** 
     * View current shopping order
     */
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
	
	/** 
     * Reset current shopping order
     */
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
	
	/** 
     * View all paid tickets
     */
	public void viewallpaidtix() {
		System.out.println("Your entire payment history is as follows : ");
		for(int i = 0; i< this.PaymentHist.size(); i++) {
			this.PaymentHist.get(i).printDetails();
			
		}
		System.out.println("Thank you for using MOBLIMA!! : ");
	}
	
	/** 
     * See all past purchasers
     */
	public void seepeople() {
		System.out.println("These are all the people who purchased tickets : ");
		for(int i = 0; i< this.people.size(); i++) {
			System.out.println("Name : " + this.people.get(i).getName() + " | Email : " + this.people.get(i).getEmail() + " | Number : " + this.people.get(i).getMobileNumber())
			;
		}
	}
}
