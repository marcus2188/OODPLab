package Main;

import boundary.AdminMenu;
import boundary.STATE;
import boundary.UserMenu;
import utils.ScannerErrorHandler;

import java.io.IOException;
import java.text.ParseException;

public class MOBLIMA {

    /* Global variables */
    private static boolean admin = false;
    private static boolean running = true;
    private static final String PASSWORD = "root";



    /* Initialize app state */
    private static STATE appState = STATE.LOGIN;

    public static STATE getAppState() {
        return appState;
    }

    public static void setAppState(STATE appState) {
        MOBLIMA.appState = appState;
    }

    public static void main(String[] args) throws IOException, ParseException {
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
                     UserMenu.userMenu();
                     break;
                 case MOVIE_GOER_MOVIE_LIST:
                     UserMenu.userListMovies();
                     break;
                 case MOVIE_GOER_MOVIE_SEARCH:
                     UserMenu.userSearchMovies();
                     break;
                 case MOVIE_GOER_LIST_TOP_5:
                     UserMenu.userListTop5Movies();
                     break;
                 case MOVIE_GOER_VIEW_SHOPPING_CART:
                     UserMenu.userViewShoppingCart();
                     break;
                 case MOVIE_GOER_VIEW_TRANSACTIONS:
                     UserMenu.userViewTransactions();
                     break;
                 case MOVIE_GOER_BOOKING:
                     UserMenu.userMovieBooking();
                     break;
                 case MOVIE_GOER_MOVIE_DETAILS:
                     UserMenu.userMovieDetails();
                     break;
                 case MOVIE_GOER_ADD_REVIEW:
                     UserMenu.userAddReview();
                     break;

                 // admin routes
                 case ADMIN_MENU:
                     AdminMenu.adminMenu();
                     break;
                 case ADMIN_CREATE_MOVIE:
                     AdminMenu.adminCreateMovie();
                     break;
                 case ADMIN_UPDATE_MOVIE:
                     AdminMenu.adminUpdateMovie();
                     break;
                 case ADMIN_DELETE_MOVIE:
                     AdminMenu.adminDeleteMovie();
                     break;
                 case ADMIN_CREATE_SCREENING:
                     AdminMenu.adminCreateScreening();
                     break;
                 case ADMIN_UPDATE_SCREENING:
                     AdminMenu.adminUpdateScreening();
                     break;
                 case ADMIN_DELETE_SCREENING:
                     AdminMenu.adminDeleteScreening();
                     break;
                 case ADMIN_UPDATE_PRICE:
                     AdminMenu.adminUpdatePrice();
                     break;
                 case ADMIN_UPDATE_PH:
                     AdminMenu.adminUpdatePH();
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

        //Scanner scan = new Scanner(System.in);
        ScannerErrorHandler scan = new ScannerErrorHandler();
      
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
                MOBLIMA.setAppState(STATE.MOVIE_GOER_MENU);
                break;
            // navigate to admin page
            case 2:
                System.out.println("Loading Admin page...");
                System.out.println("Please enter password: ");
                password= scan.next();

                if (password.equals(PASSWORD)) {
                    System.out.println("Loading admin page...");
                    setAppState(STATE.ADMIN_MENU);
                    admin = true;
                    break;
                } else {
                    System.out.println("Invalid Password!");
                    System.out.println("Redirecting...");
                    setAppState(STATE.LOGIN);
                    break;
                }

            default:
                System.out.println("Invalid choice, try again...");
                setAppState(STATE.LOGIN);
                break;
        }
    }
}
