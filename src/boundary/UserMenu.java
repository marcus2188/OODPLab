package boundary;

import Main.MOBLIMA;
import controller.Movie_mg_inf;
import controller.Movie_mg_manager;
import controller.ShoppingOrder_inf;
import controller.ShoppingOrder_manager;
import utils.ScannerErrorHandler;

import java.io.IOException;
import java.text.ParseException;

public class UserMenu {
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
                MOBLIMA.setAppState(STATE.MOVIE_GOER_MOVIE_LIST);
                break;
            case 2:
                MOBLIMA.setAppState(STATE.MOVIE_GOER_MOVIE_SEARCH);
                break;
            case 3:
                MOBLIMA.setAppState(STATE.MOVIE_GOER_LIST_TOP_5);
                break;
            case 4:
                MOBLIMA.setAppState(STATE.MOVIE_GOER_VIEW_SHOPPING_CART);
                break;
            case 5:
                MOBLIMA.setAppState(STATE.MOVIE_GOER_VIEW_TRANSACTIONS);
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

    public static void userViewShoppingCart() {
        ShoppingOrder_inf shoppingOrderManager = new ShoppingOrder_manager();
        System.out.println("=== My Shopping Cart ===");
        shoppingOrderManager.viewcurrentSO();
        MOBLIMA.setAppState(STATE.MOVIE_GOER_MENU);
    }

    public static void userViewTransactions() {
        ShoppingOrder_inf shoppingOrderManager = new ShoppingOrder_manager();
        System.out.println("=== My Transactions ===");
        shoppingOrderManager.viewallpaidtix();
        MOBLIMA.setAppState(STATE.MOVIE_GOER_MENU);
    }

    public static void userMovieBooking() {
        // try {
            ShoppingOrder_inf shoppingOrderManager = new ShoppingOrder_manager();
            System.out.println(("=== Movie Booking ==="));
            // shoppingOrderManager.bookTicket();
            MOBLIMA.setAppState(STATE.MOVIE_GOER_MENU);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }

}
