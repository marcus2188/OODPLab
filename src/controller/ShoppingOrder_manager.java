package controller;
import entity.AgeGroup;
import entity.MovieGoer;
import entity.MovieScreening;
import entity.MovieTicket;
import entity.ScreeningFormat;
import entity.ShoppingOrder;
import entity.Cineplex;
import entity.Movie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.util.Scanner;

import utils.ScannerErrorHandler;
import utils.SerializeDB;
import utils.Print;
import utils.Filter;

public class ShoppingOrder_manager implements ShoppingOrder_inf{
	private ArrayList<MovieTicket> PaymentHist; 
	private ArrayList<MovieGoer> people;
	private ArrayList<Cineplex> cineplexes;
	private ArrayList<Movie> movies;
	private ArrayList<MovieScreening> movieScreeningList;
	private ArrayList<MovieTicket> tixlist;
	Date newdate;
	ShoppingOrder neword;
	
	// CONSTRUCTOR RUNS WHEN MAIN CREATES MY MANAGER INSTANCE
	public ShoppingOrder_manager(){
		this.importdata();
		newdate = null;
		neword = new ShoppingOrder(this.tixlist, newdate);
	}
	
	// TO FETCH ALL PAST PAID TICKETS INTO THE PAYMENTHIST
	public void importdata() {
		this.PaymentHist = (ArrayList) SerializeDB.readSerializedObject("paymentHistory.dat");
		this.people = (ArrayList) SerializeDB.readSerializedObject("peoplenames.dat");
		this.movieScreeningList = (ArrayList) SerializeDB.readSerializedObject("moviescreening.dat");
		this.cineplexes = (ArrayList) SerializeDB.readSerializedObject("cineplex.dat");
		this.movies = (ArrayList) SerializeDB.readSerializedObject("Movie.dat");
		this.tixlist = (ArrayList) SerializeDB.readSerializedObject("bookingtickets.dat");
	}
	// TO UPDATE PAID TICKETS FROM PAYMENT HIST TO PAID.dat
	public void updatedata() {
		SerializeDB.writeSerializedObject("paymentHistory.dat", this.PaymentHist);
		SerializeDB.writeSerializedObject("peoplenames.dat", this.people);
		SerializeDB.writeSerializedObject("moviescreening.dat", this.movieScreeningList);
		SerializeDB.writeSerializedObject("Movie.dat", this.movies);
		SerializeDB.writeSerializedObject("bookingtickets.dat", this.tixlist);
	}
	
	// MAIN CALLS THIS FUNCTION FOR BOOKING TICKETS
	public void bookTicket() throws ParseException {
		int choice, choice2;
        Movie movie;
		Cineplex cineplex;
		MovieScreening movieScreening;
		ArrayList<MovieScreening> temp;
        ScannerErrorHandler sc = new ScannerErrorHandler();

        System.out.println("Press 1 to list movie screening by movie.");
        System.out.println("Press 2 to list movie screening by cineplex.");
        choice = sc.nextInt();
        switch(choice){
            case 1:
                System.out.println("Choose a movie: ");
                Print.printMovies(movies);
                do {
                    choice2 = sc.nextInt();
                } while (choice2<1 || choice2>movies.size());
                movie = movies.get(choice2-1);
				temp = Filter.filterByMovie(movieScreeningList, movie);
                Print.printMovieScreenings(temp);
				System.out.println("Choose a movie screening: ");
				do {
                    choice2 = sc.nextInt();
                } while (choice2<1 || choice2>temp.size());
				movieScreening = temp.get(choice2-1);
                break;

            case 2:
                System.out.println("Choose a cineplex: ");
                Print.printCineplexes(cineplexes);
                do {
                    choice2 = sc.nextInt();
                } while (choice2<1 || choice2>cineplexes.size());
                cineplex = cineplexes.get(choice2-1);

                temp = Filter.filterByCineplex(movieScreeningList, cineplex);
                Print.printMovieScreenings(temp);
				System.out.println("Choose a movie screening: ");
				do {
                    choice2 = sc.nextInt();
                } while (choice2<1 || choice2>temp.size());
				movieScreening = temp.get(choice2-1);
                break;
            default:
                System.out.println("Invalid value!");
                return;
		}

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
        choice = scan.nextInt();
        switch (choice) {
            case 1:
                ageGroup = AgeGroup.SENIORCITIZEN;
            case 2:
                ageGroup = AgeGroup.STUDENT;
            case 3:
                ageGroup = AgeGroup.REGULAR;
            default:
                ageGroup = AgeGroup.REGULAR;
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
            case 2:
                screeningFormat = ScreeningFormat.REGULAR;
            case 3:
                screeningFormat = ScreeningFormat.BLOCKBUSTER;
            default:
                screeningFormat = ScreeningFormat.REGULAR;
        }

        // GET DAY int
        int day;
        day = (int) c1.get(Calendar.DAY_OF_WEEK);

		// ASK TO CHOOSE SEAT
		System.out.println("Please choose a seat(O means available):");
		movieScreening.getCinema().updateSeats(movieScreening.getSeatStatus());
		movieScreening.getCinema().printSeatAvailability();

		char row;
		int col;
		int index;

		System.out.println("Please enter the row: ");
		row = sc.next().charAt(0);
		System.out.println("Please enter the column: ");
		col = sc.nextInt();
		try{
			index = movieScreening.bookSeat(row, col);
			MovieTicket mt = new MovieTicket(ageGroup, weekday, before6, screeningFormat, day, (float)0.00);
			// SET TICKET PRICE PRICE float
			mt.setPriceBasedOnAttributes();
			
			// SET THE REST OF THE TICKET ATTRIBUTES
			mt.setMovieScreening(movieScreening);
			
			mt.setSeat(movieScreening.getCinema().getSeatList().get(index));

			this.tixlist.add(mt);

			updatedata();

		}catch(Exception e){
			System.out.println("Please enter the right format!");
		}
        
        

        // SET THE SEAT NUMBER ONLY AFTER I CAN ACCESS THE CINEPLEX AND CINEMA OBJECTS
        //String seatno = Character.toString(row) + Integer.toString(col);
        //mt.setSeat(seatno);
        
        // SET THE TID OF TICKET   //TBC		
        //mt.setTID("this.obj.getShowDate()" + this.obj.getCinema());
        //this.neword.addticket(mt);
	}
	
	// MAIN CALLS THIS FUNCTION TO ACTUALLY PURCHASE THE TICKETS INSIDE SHOPPING ORDER
	public void makePurchase() {
		ScannerErrorHandler se = new ScannerErrorHandler();
		
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
	
		// SET THE TID OF ALL TICKETS BEFORE MOVING INTO PAYMENT HIST
		String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		for(int i = 0; i < this.neword.returnticketarray().size();i++) {
			neword.returnticketarray().get(i).setTID(neword.returnticketarray().get(i).getMovieScreening().getCinema().getCinemaID() + timeStamp);
		}
		
		// MOVE ALL TICKETS FROM SHOPPING ORDER INTO PAYMENT HISTORY ARRAYLIST AND ADD SALES TO CORRESPONDING MOVIE
		for(int i = 0; i < this.neword.returnticketarray().size();i++) {
			MovieTicket ticket = neword.returnticketarray().get(i);
			ticket.getMovieScreening().getMovie().addTicketSales(ticket.getPrice());
			PaymentHist.add(ticket);
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
