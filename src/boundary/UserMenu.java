package boundary;

import Main.MOBLIMA;
import controller.Movie_mg_inf;
import controller.Movie_mg_manager;
import controller.MovieScreening_inf;
import controller.MovieScreeningManager;
import controller.ShoppingOrder_inf;
import controller.ShoppingOrder_manager;
import utils.ScannerErrorHandler;
import entity.Movie;
import entity.Cineplex;
import entity.MovieScreening;
import utils.Print;
import utils.Filter;
import utils.SerializeDB;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 A boundary class to interact with movie goer.
 @author SS3_Group4
 @version 1.0
 @since 2019-11-15
*/

public class UserMenu {
    /** 
    * An arraylist of moviescreenings from database(.dat file)
    */
    private ArrayList<MovieScreening> movieScreeningList;
    /** 
    * An arraylist of cineplexes from database(.dat file)
    */
	private	ArrayList<Cineplex> cineplexes;
    /** 
    * An arraylist of movies from database(.dat file)
    */
	private	ArrayList<Movie> movies;
    /*
All user menus
 */
    /** 
    * Show user menu
    */
    public static void userMenu() {
        int choice = 0;
        //Scanner scan = new Scanner(System.in);
        ScannerErrorHandler scan = new ScannerErrorHandler();
        System.out.println("Welcome, Movie Goer!");
        System.out.println("Press 1 to list all movies");
        System.out.println("Press 2 to search a movie");
        System.out.println("Press 3 to list top 5 movies");
        System.out.println("Press 4 to see all movie screening");
        System.out.println("Press 5 to enter Booking Menu");
        System.out.println("Press 6 to exit MOBLIMA");
        System.out.println("Press 0 to go to Main Menu");

        choice = scan.nextInt();
        while (choice < 0 || choice > 6) {
            System.out.println("Invalid choice, please try again:");
            choice = scan.nextInt();
        }

        switch (choice) {
            case 1:
                MOBLIMA.setAppState(STATE.MOVIE_GOER_MOVIE_LIST);
                break;
            case 2:
                MOBLIMA.setAppState(STATE.MOVIE_GOER_MOVIE_SEARCH);
                break;
            case 3:
                MOBLIMA.setAppState(STATE.MOVIE_GOER_LIST_TOP_5);
                break;
            case 4:
                MOBLIMA.setAppState(STATE.MOVIE_GOER_MOVIE_SCREENING_LIST);
                break;
            case 5:
                MOBLIMA.setAppState(STATE.MOVIE_GOER_BOOKING_MENU);
                break;
            case 6: 
            	System.out.println("You have closed the program, goodbye!!!! :))");
            	System.exit(0);
            case 0:
                System.out.println("Redirecting to main menu...");
                MOBLIMA.setAppState(STATE.LOGIN);
                break;
            default:
                break;
        }

    }

    /** 
    * Shows movie goer a list of movie screenings
    * Movie screenings are filtered
    * Uses movie screening interface
    */
    public static void userListScreening(){
        MovieScreening_inf  movieMgManager = new MovieScreeningManager();
        movieMgManager.viewAllListing();
        MOBLIMA.setAppState(STATE.MOVIE_GOER_MENU);
    }

    /** 
    * Shows movie goer a list of all movies
    * Uses movie movie goer interface
    */
    public static void userListMovies() {
        Movie_mg_inf movieMgManager = new Movie_mg_manager();
        System.out.println("=== Movie List ===");
        movieMgManager.listAllMovie();
        MOBLIMA.setAppState(STATE.MOVIE_GOER_MENU);   // Redirect back to main menu
    }

    
    /** 
    * Allows movie goer to search for movies
    * Uses movie movie goer interface
    */
    public static void userSearchMovies() {
    	Movie_mg_inf movieMgManager = new Movie_mg_manager();
        System.out.println("=== Movie search ===");
        movieMgManager.searchMovie();
        MOBLIMA.setAppState(STATE.MOVIE_GOER_MENU); // Redirect back to main menu
    }

    /** 
    * Allows movie goer to list top 5 movies by ticket sales or rating
    * Uses movie movie goer interface
    */
    public static void userListTop5Movies() {
    	Movie_mg_inf movieMgManager = new Movie_mg_manager();
        System.out.println("=== Top 5 Movies ===");        
        movieMgManager.listTop5();
        MOBLIMA.setAppState(STATE.MOVIE_GOER_MENU);
    }

    /** 
    * Allows movie goer to enter booking menu to make transactions
    * Uses shopping order interface
    */
    public void userBookingMenu() {
		ScannerErrorHandler sc = new ScannerErrorHandler();
		ShoppingOrder_manager ms = new ShoppingOrder_manager();
		ArrayList<Movie> temp2;
        int choice, choice2, movieChoice, screeningChoice, cineplexChoice;
        Movie movie;
		Cineplex cineplex;
		MovieScreening movieScreening;
		ArrayList<MovieScreening> temp;
        char row;
		int col;
		int index;
        boolean loop = true;

        while(loop){
            System.out.println("WELCOME TO THE BOOKING MENU");
            System.out.println("-------------------------------------");
            System.out.println("1. Book ticket");
            System.out.println("2. View current shopping cart");
            System.out.println("3. Make your purchase");
            System.out.println("4. See purchase history");
            System.out.println("5. See all past purchasers");      // TESTED WORKING
            System.out.println("6. Clear current shopping cart");
            System.out.println("7. Back to Movie Menu");
            System.out.println("-------------------------------------");
            System.out.println("What do you wanna do? : ");
            
            choice = sc.nextInt();
            
            switch(choice) {
                case 1:
                    importdata();
                    System.out.println("Press 1 to list movie screening by movie.");
                    System.out.println("Press 2 to list movie screening by cineplex.");
                    choice2 = sc.nextInt();
                    switch(choice2){
                        case 1:
                            System.out.println("Choose a movie: ");
                            temp2 = Filter.filterByShowStatus(movies);
                            Print.printMovies(temp2);
                        do {
                        	System.out.println("Please enter a valid item :");
                            movieChoice = sc.nextInt();
                        } while (movieChoice<1 || movieChoice>movies.size());
                            movie = temp2.get(movieChoice-1);
                            temp = Filter.filterByMovie(movieScreeningList, movie); 
                            if(temp.isEmpty()) {
                            	System.out.println("There are no movie screenings for this movie currently");
                            	System.out.println("We apologise for any inconvenience");
                            	System.out.println("V   V");
                            	System.out.println("  W  ");
                            	continue;
                            }
                            System.out.println("Choose a movie screening: ");
                            Print.printMovieScreenings(temp);
                        do {
                        	System.out.println("Please enter a valid item :");
                            screeningChoice = sc.nextInt();
                        } while (screeningChoice<1 || screeningChoice>temp.size());
                            movieScreening = temp.get(screeningChoice-1);
                            break;

                        case 2:
                            System.out.println("Choose a cineplex: ");
                            Print.printCineplexes(cineplexes);
                            do {
                            	System.out.println("Please enter a valid item :");
                                cineplexChoice = sc.nextInt();
                            } while (cineplexChoice<1 || cineplexChoice>cineplexes.size());
                            cineplex = cineplexes.get(cineplexChoice-1);

                            temp = Filter.filterByCineplex(movieScreeningList, cineplex);
                            System.out.println("Choose a movie screening: ");
                            Print.printMovieScreenings(temp);
                            if(temp.isEmpty()) {
                            	System.out.println("There are no movie screenings for this  currently");
                            	System.out.println("We apologise for any inconvenience");
                            	System.out.println("V   V");
                            	System.out.println("  W  ");
                            	continue;
                            }
                            do {
                            	System.out.println("Please enter a valid item :");
                                screeningChoice = sc.nextInt();
                            } while (screeningChoice<1 || screeningChoice>temp.size());
                            movieScreening = temp.get(screeningChoice-1);
                            break;
                        default:
                            System.out.println("You have entered an invalid input, please check");
                            movieScreening = null;
                            continue;
                    }
                    boolean correctseat = false;
                    while(!correctseat) {
	                    System.out.println("Please choose a seat(O means available):");
	                    movieScreening.getCinema().updateSeats(movieScreening.getSeatStatus());
	                    movieScreening.getCinema().printSeatAvailability();
	
	                    System.out.println("Please enter the row: ");
	                    String intake = sc.nextLine();
	                    if(!intake.matches("[a-zA-Z]")) {
	                    	System.out.println("Please type a correct row please");
	                    	System.out.println("");
	                    	continue;
	                    }
	                    //type 2
	                    row = Character.toUpperCase(intake.charAt(0));
	                    System.out.println("Please enter the column: ");
	                    col = sc.nextInt();
	                    try{
	                        index = movieScreening.bookSeat(row, col);
	                        if(index == -1){
	                            continue;
	                        }
	                        ms.bookTicket(movieScreening, movieScreening.getCinema().getSeatList().get(index));
	                        correctseat = true;
	                    }catch(IndexOutOfBoundsException e){
	                        System.out.println("You have entered an invalid seat row or column");
	                        System.out.println("Please try again");
	                        System.out.println("");
	                    }catch(ParseException f){
	                    	System.out.println("There is a parsing error");
	                    }
                    }
                    updatedata();
                    break;

                case 2: 
                    ms.viewcurrentSO(); 
                    break;
                case 3:
                    ms.makePurchase();
                    updatedata();
                    break;
                case 4: 
                    ms.viewallpaidtix(); 
                    break;
                case 5:
                    ms.seepeople(); 
                    break;
                case 6: 
                    ms.dumpcurrentSO(); 
                    break;
                case 7:
                	loop = false;
                    break;
                default:
                    System.out.println("Please enter a valid choice, thank you");
                	break;
            }
        }
        MOBLIMA.setAppState(STATE.MOVIE_GOER_MENU);
    }

    /** 
    * Update data
    */
    public void updatedata() {
		SerializeDB.writeSerializedObject("moviescreening.dat", this.movieScreeningList);
		SerializeDB.writeSerializedObject("Movie.dat", this.movies);
	}

    /** 
    * Import data
    */
    public void importdata() {
		this.movieScreeningList = (ArrayList) SerializeDB.readSerializedObject("moviescreening.dat");
		this.cineplexes = (ArrayList) SerializeDB.readSerializedObject("cineplex.dat");
		this.movies = (ArrayList) SerializeDB.readSerializedObject("Movie.dat");
	}

}
