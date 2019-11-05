import boundary.Admin_UI;
import controller.MovieTicketManager;
import java.io.IOException;
import java.util.Scanner;
import controller.*;
import boundary.*;

public class Main {

    /* Global variables */
    private static boolean admin = false;
    private static boolean running = true;
    private static final String PASSWORD = "root";

    /*
    Acts as "routes" like in a url that renders pages based on the appState.
    appState can be set from different methods in order to render the pages required.
     */
    enum STATE {
        LOGIN,

        // Movie Goer Pages
        MOVIE_GOER_MENU,
        MOVIE_GOER_MOVIE_DETAILS,
        MOVIE_GOER_MOVIE_LIST,
        MOVIE_GOER_LIST_TOP_5,
        MOVIE_GOER_MOVIE_SEARCH,
        MOVIE_GOER_BOOKING,
        MOVIE_GOER_VIEW_SHOPPING_CART,
        MOVIE_GOER_VIEW_TRANSACTIONS,
        MOVIE_GOER_ADD_REVIEW,

        // Admin pages
        ADMIN_MENU,
        ADMIN_CREATE_MOVIE,
        ADMIN_UPDATE_MOVIE,
        ADMIN_DELETE_MOVIE,
        ADMIN_CREATE_SCREENING,
        ADMIN_UPDATE_SCREENING,
        ADMIN_DELETE_SCREENING,
        ADMIN_UPDATE_PRICE,
        ADMIN_UPDATE_PH
    }

    /* Initialize app state */
    private static STATE appState = STATE.LOGIN;

    public static void main(String[] args) throws IOException {
        /*
        List all routes, render routes based on state
         */
         while(running) {
             switch(appState) {
                 case LOGIN:
                     printMenu();
                     break;

                 // user routes
                 case MOVIE_GOER_MENU:
                     userMenu();
                     break;
                 case MOVIE_GOER_MOVIE_LIST:
                     userListMovies();
                     break;
                 case MOVIE_GOER_MOVIE_SEARCH:
                     userSearchMovies();
                     break;
                 case MOVIE_GOER_LIST_TOP_5:
                     userListTop5Movies();
                     break;
                 case MOVIE_GOER_VIEW_SHOPPING_CART:
                     userViewShoppingCart();
                     break;
                 case MOVIE_GOER_VIEW_TRANSACTIONS:
                     userViewTransactions();
                     break;
                 case MOVIE_GOER_BOOKING:
                     userMovieBooking();
                     break;
                 case MOVIE_GOER_MOVIE_DETAILS:
                     userMovieDetails();
                     break;
                 case MOVIE_GOER_ADD_REVIEW:
                     userAddReview();
                     break;

                 // admin routes
                 case ADMIN_MENU:
                     adminMenu();
                     break;
                 case ADMIN_CREATE_MOVIE:
                     adminCreateMovie();
                     break;
                 case ADMIN_UPDATE_MOVIE:
                     adminUpdateMovie();
                     break;
                 case ADMIN_DELETE_MOVIE:
                     adminDeleteMovie();
                     break;
                 case ADMIN_CREATE_SCREENING:
                     adminCreateScreening();
                     break;
                 case ADMIN_UPDATE_SCREENING:
                     adminUpdateScreening();
                     break;
                 case ADMIN_DELETE_SCREENING:
                     adminDeleteScreening();
                     break;
                 case ADMIN_UPDATE_PRICE:
                     adminUpdatePrice();
                     break;
                 case ADMIN_UPDATE_PH:
                     adminUpdatePH();
                     break;
             }
         }
    }

    /*
    Top level Main Menu
     */
    private static void printMenu() {
        int choice = 0;
        String password; // password that user will input

        Scanner scan = new Scanner(System.in);
      
        System.out.println("Welcome to MOBLIMA App!");
        System.out.println("Press 1 for Movie Goer");
        System.out.println("Press 2 for admin");
        System.out.println("=======================");

        choice = scan.nextInt();
        while (choice != 1 && choice != 2) {
            System.out.println("Invalid choice, try again:");
            choice = scan.nextInt();
        }
        switch (choice) {
            // navigate to movie goer page
            case 1:
                System.out.println("Loading Movie Goer page...");
                appState = STATE.MOVIE_GOER_MENU;
                break;
            // navigate to admin page
            case 2:
                System.out.println("Loading Admin page...");
                System.out.println("Please enter password: ");
                password= scan.next();

                if (password.equals(PASSWORD)) {
                    System.out.println("Loading admin page...");
                    appState = STATE.ADMIN_MENU;
                    admin = true;
                    break;
                } else {
                    System.out.println("Invalid Password!");
                    System.out.println("Redirecting...");
                    appState = STATE.LOGIN;
                    break;
                }

            default:
                System.out.println("Invalid choice, try again...");
                appState = STATE.LOGIN;
                break;
        }
    }


    /*
    All user menus
    TODO: Implement controllers in each case
     */
    private static void userMenu() {
        int choice = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome, Movie Goer!");
        System.out.println("Press 1 to list all movies");
        System.out.println("Press 2 to search a movie");
        System.out.println("Press 3 to list top 5 movies");
        System.out.println("Press 4 to view shopping cart");
        System.out.println("Press 5 to view transaction history");
        System.out.println("Press 0 to go to Main Menu");

        choice = scan.nextInt();
        while (choice < 0 || choice > 5) {
            System.out.println("Invalid choice, please try again:");
            choice = scan.nextInt();
        }

        switch (choice) {
            case 1:
                appState = STATE.MOVIE_GOER_MOVIE_LIST;
                break;
            case 2:
                appState = STATE.MOVIE_GOER_MOVIE_SEARCH;
                break;
            case 3:
                appState = STATE.MOVIE_GOER_LIST_TOP_5;
                break;
            case 4:
                appState = STATE.MOVIE_GOER_VIEW_SHOPPING_CART;
                break;
            case 5:
                appState = STATE.MOVIE_GOER_VIEW_TRANSACTIONS;
                break;
            case 0:
                System.out.println("Redirecting to main menu...");
                appState = STATE.LOGIN;
                break;
            default:
                break;
        }

    }

    private static void userListMovies() {
        MovieGoer_UI movieMgManager = new Movie_mg_manager();
        System.out.println("=== Movie List ===");
        ((Movie_mg_manager) movieMgManager).listAllMovie();
        appState = STATE.MOVIE_GOER_MENU;   // Redirect back to main menu
    }

    private static void userSearchMovies() {
        MovieGoer_UI movieMgManager = new Movie_mg_manager();
        String movieName;
        Scanner scan = new Scanner(System.in);

        System.out.println("=== Movie search ===");
        System.out.println("Please enter the title: ");
        movieName = scan.next();
        ((Movie_mg_manager) movieMgManager).searchMovie(movieName);
        appState = STATE.MOVIE_GOER_MENU; // Redirect back to main menu
    }


    private static void userListTop5Movies() {
        MovieGoer_UI movieMgManager = new Movie_mg_manager();
        boolean byTicketSales;
        int choice;
        Scanner scan = new Scanner(System.in);
        System.out.println("=== Top 5 Movies ===");
        System.out.println("Press 1 for top 5 movies ranked by ticket sales.");
        System.out.println("Press 2 for top 5 movies ranked by ratings");
        choice = scan.nextInt();
        while (choice != 2 && choice != 1) {
            System.out.println("Invalid choice, please try again: ");
            choice = scan.nextInt();
        }
        if(choice == 1){
            byTicketSales = true;

        }else if(choice == 2){
            byTicketSales = false;
        } else {
            byTicketSales = false;
        }
        ((Movie_mg_manager) movieMgManager).listTop5(byTicketSales);
        appState = STATE.MOVIE_GOER_MENU;
    }

    private static void userViewShoppingCart() {System.out.println("=== My Shopping Cart ===");}

    private static void userViewTransactions() {System.out.println("=== My Transactions ===");}

    private static void userMovieBooking() { System.out.println(("=== Movie Booking ==="));}

    private static void userMovieDetails() {
        MovieGoer_UI movieMgManager = new Movie_mg_manager();
        String movieName;
        Scanner scan = new Scanner(System.in);
        System.out.println("=== Movie Details ===");
        System.out.println("Please enter the movie to view details: ");
        movieName = scan.next();
        ((Movie_mg_manager) movieMgManager).viewMovieDetails(movieName);
        appState = STATE.MOVIE_GOER_MENU;
    }

    private static void userAddReview() {
        MovieGoer_UI movieMgManager = new Movie_mg_manager();
        int rating;
        String comments, movieName;
        Scanner scan = new Scanner(System.in);
        
        System.out.println("=== Add Review ===");
        System.out.println("Please enter the movie to review: ");
        movieName = scan.next();
        System.out.println("Please enter your review: ");
        comments = scan.next();
        System.out.println("Please enter your rating: ");
        rating = scan.nextInt();
        ((Movie_mg_manager) movieMgManager).addMovieReview(movieName, comments, rating);
        appState = STATE.MOVIE_GOER_MENU;
    }

    /*
    Admin pages
   */
    private static void adminMenu() {
        int choice = 0;
        int id;
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome, admin!");
        System.out.println("Press 1 to create a movie in movie listings");
        System.out.println("Press 2 to update a movie in movie listings");
        System.out.println("Press 3 to delete a movie from movie listings");
        System.out.println("Press 4 to create a new movie screening");
        System.out.println("Press 5 to update an existing movie screening");
        System.out.println("Press 6 to delete an existing movie screening");
        System.out.println("Press 7 to update ticket prices");
        System.out.println("Press 8 to update public holidays");
        System.out.println("Press 0 to go to main menu");

        choice = scan.nextInt();
        while (choice < 0 || choice > 8) {
            System.out.println("Invalid choice, please try again: ");
            choice = scan.nextInt();
        }

        switch(choice) {
            case 1:
                appState = STATE.ADMIN_CREATE_MOVIE;
                break;
            case 2:
                appState = STATE.ADMIN_UPDATE_MOVIE;
                break;
            case 3:
                appState = STATE.ADMIN_DELETE_MOVIE;
                break;
            case 4:
                appState = STATE.ADMIN_CREATE_SCREENING;
                break;
            case 5:
                appState = STATE.ADMIN_UPDATE_SCREENING;
                break;
            case 6:
                appState = STATE.ADMIN_DELETE_SCREENING;
                break;
            case 7:
                appState = STATE.ADMIN_UPDATE_PRICE;
                break;
            case 8:
                appState = STATE.ADMIN_UPDATE_PH;
                break;
            case 0:
                System.out.println("Logging out, redirecting to main page...");
                appState = STATE.LOGIN;
                break;
            default:
                break;
        }
    }


    private static void adminCreateMovie() {
        System.out.println("=== Create Movie ===");

    }

    private static void adminUpdateMovie() {System.out.println("=== Update Movie ===");}

    private static void adminDeleteMovie() {System.out.println("=== Delete Movie === ");}

    private static void adminCreateScreening() {System.out.println("=== Create Screening ===");}

    private static void adminUpdateScreening() {System.out.println("=== Update Screening ===");}

    private static void adminDeleteScreening() {System.out.println("=== Delete Screening ===");}

    private static void adminUpdatePrice() throws IOException {
        System.out.println("=== Update Price ===");
        Admin_UI adminManager = new MovieTicketManager();
        ((MovieTicketManager) adminManager).updatePriceTable();
        appState = STATE.ADMIN_MENU;

    }

    private static void adminUpdatePH() {
        System.out.println("=== Update Public Holidays ===");
        Admin_UI holidayManager = new PublicHolidayManager();
        holidayManager.printMenu();
        System.out.println("Press 1 to add a new public holiday");
        System.out.println("Press 2 to delete a public holiday");
        System.out.println("Press 0 to go back to menu");

        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        // TODO: add error checking

        switch(choice) {
            case 1:
                ((PublicHolidayManager) holidayManager).addHoliday();
                System.out.println("Redirecting to Menu...");
                appState = STATE.ADMIN_MENU;
            case 2:
                ((PublicHolidayManager) holidayManager).deleteHoliday();
                System.out.println("Redirecting to Menu...");
                appState = STATE.ADMIN_MENU;
            case 0:
                appState = STATE.ADMIN_MENU;
        }
    }
}
