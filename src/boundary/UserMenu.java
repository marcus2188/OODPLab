package boundary;

import Main.MOBLIMA;
import controller.Movie_mg_inf;
import controller.Movie_mg_manager;
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

public class UserMenu {
    private ArrayList<MovieScreening> movieScreeningList;
	private	ArrayList<Cineplex> cineplexes;
	private	ArrayList<Movie> movies;
    /*
All user menus
 */
    public static void userMenu() {
        int choice = 0;
        //Scanner scan = new Scanner(System.in);
        ScannerErrorHandler scan = new ScannerErrorHandler();
        System.out.println("Welcome, Movie Goer!");
        System.out.println("Press 1 to list all movies");
        System.out.println("Press 2 to search a movie");
        System.out.println("Press 3 to list top 5 movies");
        System.out.println("Press 4 to enter Booking Menu");
        System.out.println("Press 0 to go to Main Menu");

        choice = scan.nextInt();
        while (choice < 0 || choice > 5) {
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
                MOBLIMA.setAppState(STATE.MOVIE_GOER_BOOKING_MENU);
                break;
            case 0:
                System.out.println("Redirecting to main menu...");
                MOBLIMA.setAppState(STATE.LOGIN);
                break;
            default:
                break;
        }

    }

    public static void userListMovies() {
        Movie_mg_inf movieMgManager = new Movie_mg_manager();
        System.out.println("=== Movie List ===");
        movieMgManager.listAllMovie();
        MOBLIMA.setAppState(STATE.MOVIE_GOER_MENU);   // Redirect back to main menu
    }

    public static void userSearchMovies() {
    	Movie_mg_inf movieMgManager = new Movie_mg_manager();
        System.out.println("=== Movie search ===");
        movieMgManager.searchMovie();
        MOBLIMA.setAppState(STATE.MOVIE_GOER_MENU); // Redirect back to main menu
    }


    public static void userListTop5Movies() {
    	Movie_mg_inf movieMgManager = new Movie_mg_manager();
        System.out.println("=== Top 5 Movies ===");        
        movieMgManager.listTop5();
        MOBLIMA.setAppState(STATE.MOVIE_GOER_MENU);
    }

    public void userBookingMenu() {
		ScannerErrorHandler sc = new ScannerErrorHandler();
		ShoppingOrder_manager ms = new ShoppingOrder_manager();
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
                            Print.printMovies(movies);
                        do {
                            movieChoice = sc.nextInt();
                        } while (movieChoice<1 || movieChoice>movies.size());
                            movie = movies.get(movieChoice-1);
                            temp = Filter.filterByMovie(movieScreeningList, movie);
                            System.out.println("Choose a movie screening: ");
                            Print.printMovieScreenings(temp);
                        do {
                            screeningChoice = sc.nextInt();
                        } while (screeningChoice<1 || screeningChoice>temp.size());
                            movieScreening = temp.get(screeningChoice-1);
                            break;

                        case 2:
                            System.out.println("Choose a cineplex: ");
                            Print.printCineplexes(cineplexes);
                            do {
                                cineplexChoice = sc.nextInt();
                            } while (cineplexChoice<1 || cineplexChoice>cineplexes.size());
                            cineplex = cineplexes.get(cineplexChoice-1);

                            temp = Filter.filterByCineplex(movieScreeningList, cineplex);
                            System.out.println("Choose a movie screening: ");
                            Print.printMovieScreenings(temp);
                            do {
                                screeningChoice = sc.nextInt();
                            } while (screeningChoice<1 || screeningChoice>temp.size());
                            movieScreening = temp.get(screeningChoice-1);
                            break;
                        default:
                            System.out.println("Invalid value!");
                            movieScreening = null;
                            break;
                    }

                    System.out.println("Please choose a seat(O means available):");
                    movieScreening.getCinema().updateSeats(movieScreening.getSeatStatus());
                    movieScreening.getCinema().printSeatAvailability();

                    System.out.println("Please enter the row: ");
                    row = sc.next().charAt(0);
                    System.out.println("Please enter the column: ");
                    col = sc.nextInt();
                    try{
                        index = movieScreening.bookSeat(row, col);
                        if(index == -1){
                            continue;
                        }
                        ms.bookTicket(movieScreening, movieScreening.getCinema().getSeatList().get(index));
                    }catch(java.lang.ArrayIndexOutOfBoundsException e){
                        System.out.println("Please enter the correct format for choosing the seat");
                    }catch(Exception e){
                        e.printStackTrace();
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
                default:
                    loop = false;
                    break;
            }
        }
        MOBLIMA.setAppState(STATE.MOVIE_GOER_MENU);
    }

    public void updatedata() {
		SerializeDB.writeSerializedObject("moviescreening.dat", this.movieScreeningList);
		SerializeDB.writeSerializedObject("Movie.dat", this.movies);
	}

    public void importdata() {
		this.movieScreeningList = (ArrayList) SerializeDB.readSerializedObject("moviescreening.dat");
		this.cineplexes = (ArrayList) SerializeDB.readSerializedObject("cineplex.dat");
		this.movies = (ArrayList) SerializeDB.readSerializedObject("Movie.dat");
	}

}
