package boundary;

import Main.MOBLIMA;
import controller.Movie_mg_inf;
import controller.Movie_mg_manager;
import controller.MovieScreening_inf;
import controller.MovieScreeningManager;
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
        System.out.println("Press 4 to view movie details");
        System.out.println("Press 5 to add a review.");
        System.out.println("Press 6 to book tickets.");
        System.out.println("Press 7 to view shopping cart");
        System.out.println("Press 8 to view transaction history");
        System.out.println("Press 9 to make payment");
        System.out.println("Press 0 to go to Main Menu");

        choice = scan.nextInt();
        while (choice < 0 || choice > 9) {
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
                MOBLIMA.setAppState(STATE.MOVIE_GOER_MOVIE_DETAILS);
                break;
            case 5:
                MOBLIMA.setAppState(STATE.MOVIE_GOER_ADD_REVIEW);
                break;
            case 6:
                MOBLIMA.setAppState(STATE.MOVIE_GOER_BOOKING);
                break;
            case 7:
                MOBLIMA.setAppState(STATE.MOVIE_GOER_VIEW_SHOPPING_CART);
                break;
            case 8:
                MOBLIMA.setAppState(STATE.MOVIE_GOER_VIEW_TRANSACTIONS);
                break;
            case 9:
                MOBLIMA.setAppState(STATE.MOVIE_GOER_PAYMENT);
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
        /*
        String movieName;
        //Scanner scan = new Scanner(System.in);
        ScannerErrorHandler scan = new ScannerErrorHandler();*/

        System.out.println("=== Movie search ===");
        /*
        System.out.println("Please enter the title: ");
        movieName = scan.next();
        */
        movieMgManager.searchMovie();
        MOBLIMA.setAppState(STATE.MOVIE_GOER_MENU); // Redirect back to main menu
    }


    public static void userListTop5Movies() {
        Movie_mg_inf movieMgManager = new Movie_mg_manager();
        /*
        boolean byTicketSales = false;
        int choice;
        //Scanner scan = new Scanner(System.in);
        ScannerErrorHandler scan = new ScannerErrorHandler();
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
        }*/
        ((Movie_mg_manager) movieMgManager).listTop5();
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

    public static void userPayment() {
        ShoppingOrder_inf shoppingOrderManager = new ShoppingOrder_manager();
        System.out.println("=== My Payments ===");
        shoppingOrderManager.makePurchase();
        MOBLIMA.setAppState(STATE.MOVIE_GOER_MENU);
    }

    public static void userMovieBooking() {
        // try {
            ShoppingOrder_inf shoppingOrderManager = new ShoppingOrder_manager();
            System.out.println(("=== Movie Booking ==="));
            try{
                shoppingOrderManager.bookTicket();
            }catch (ParseException e){

            }catch(IOException e){
                
            }
            MOBLIMA.setAppState(STATE.MOVIE_GOER_MENU);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }

    public static void userMovieDetails() {
        Movie_mg_inf movieMgManager = new Movie_mg_manager();
        /*
        String movieName;
        //Scanner scan = new Scanner(System.in);
        ScannerErrorHandler scan = new ScannerErrorHandler();
        */
        System.out.println("=== Movie Details ===");
        /*
        System.out.println("Please enter the movie to view details: ");
        movieName = scan.next();
        */
        movieMgManager.viewMovieDetails();
        MOBLIMA.setAppState(STATE.MOVIE_GOER_MENU);
    }

    public static void userAddReview() {
        Movie_mg_inf movieMgManager = new Movie_mg_manager();
        /*
        int rating;
        String comments, movieName;
        //Scanner scan = new Scanner(System.in);
        ScannerErrorHandler scan = new ScannerErrorHandler();
        */
        System.out.println("=== Add Review ===");
        /*
        System.out.println("Please enter the movie to review: ");
        movieName = scan.next();
        System.out.println("Please enter your review: ");
        comments = scan.next();
        System.out.println("Please enter your rating: ");
        rating = scan.nextInt();
        */
        movieMgManager.addMovieReview();
        MOBLIMA.setAppState(STATE.MOVIE_GOER_MENU);
    }

    public static void userListMovieScreening(){
        MovieScreening_inf movieScreeningManager = new MovieScreeningManager();
        
    }
}
