package boundary;

import Main.MOBLIMA;
import controller.*;
import utils.ScannerErrorHandler;

import java.io.IOException;

public class AdminMenu {
    /*
    Admin pages
   */
    public static void adminMenu() {
        int choice = 0;
        int id;
        //Scanner scan = new Scanner(System.in);
        ScannerErrorHandler scan = new ScannerErrorHandler();
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
                MOBLIMA.setAppState( STATE.ADMIN_CREATE_MOVIE);
                break;
            case 2:
                MOBLIMA.setAppState( STATE.ADMIN_UPDATE_MOVIE);
                break;
            case 3:
                MOBLIMA.setAppState(STATE.ADMIN_DELETE_MOVIE);
                break;
            case 4:
                MOBLIMA.setAppState( STATE.ADMIN_CREATE_SCREENING);
                break;
            case 5:
                MOBLIMA.setAppState(STATE.ADMIN_UPDATE_SCREENING);
                break;
            case 6:
                MOBLIMA.setAppState(STATE.ADMIN_DELETE_SCREENING);
                break;
            case 7:
                MOBLIMA.setAppState(STATE.ADMIN_UPDATE_PRICE);
                break;
            case 8:
                MOBLIMA.setAppState(STATE.ADMIN_UPDATE_PH);
                break;
            case 0:
                System.out.println("Logging out, redirecting to main page...");
                MOBLIMA.setAppState(STATE.LOGIN);
                break;
            default:
                MOBLIMA.setAppState(STATE.LOGIN);
                break;
        }
    }


    public static void adminCreateMovie() {
    	//create manager
    	Movie_admin_inf adminManager = new Movie_admin_manager();
    	
    	System.out.println("=== Create Movie ===");
        adminManager.createMovie();
        MOBLIMA.setAppState(STATE.ADMIN_MENU);
    }

    public static void adminUpdateMovie() {
    	//create manager
    	Movie_admin_inf adminManager = new Movie_admin_manager();
    	
    	System.out.println("=== Update Movie ===");
    	adminManager.updateMovie();
    	MOBLIMA.setAppState(STATE.ADMIN_MENU);
    }

    public static void adminDeleteMovie() {
    	//create manager
    	Movie_admin_inf adminManager = new Movie_admin_manager();
    	
    	System.out.println("=== Delete Movie === ");
    	adminManager.removeMovie();
    	MOBLIMA.setAppState(STATE.ADMIN_MENU);
    }

    public static void adminCreateScreening() {
        //create manager
        MovieScreening_inf movieScreeningManager = new MovieScreeningManager();

        System.out.println("=== Create Screening ===");
        ((MovieScreeningManager) movieScreeningManager).createMovieScreening();
        MOBLIMA.setAppState(STATE.ADMIN_MENU);
    }

    public static void adminUpdateScreening() {
        //create manager
        MovieScreening_inf movieScreeningManager = new MovieScreeningManager();

        System.out.println("=== Update Screening ===");
        ((MovieScreeningManager) movieScreeningManager).updateMovieScreening();
        MOBLIMA.setAppState(STATE.ADMIN_MENU);
    }

    public static void adminDeleteScreening() {
        //create manager
        MovieScreening_inf movieScreeningManager = new MovieScreeningManager();

        System.out.println("=== Delete Screening ===");
        ((MovieScreeningManager) movieScreeningManager).deleteMovieScreening();
        MOBLIMA.setAppState(STATE.ADMIN_MENU);
    }

    public static void adminUpdatePrice() throws IOException {
        System.out.println("=== Update Price ===");
        MovieTicket_inf adminManager = new MovieTicketManager();
        ((MovieTicketManager) adminManager).updatePriceTable();
        MOBLIMA.setAppState(STATE.ADMIN_MENU);

    }

    public static void adminUpdatePH() {
        System.out.println("=== Update Public Holidays ===");
        PublicHoliday_inf holidayManager = new PublicHolidayManager();
        ((PublicHolidayManager) holidayManager).printMenu();
        System.out.println("Press 1 to add a new public holiday");
        System.out.println("Press 2 to delete a public holiday");
        System.out.println("Press 0 to go back to menu");

        //Scanner scan = new Scanner(System.in);
        ScannerErrorHandler scan = new ScannerErrorHandler();
        int choice = scan.nextInt();
        // TODO: add error checking

        switch(choice) {
            case 1:
                ((PublicHolidayManager) holidayManager).addHoliday();
                System.out.println("Redirecting to Menu...");
                MOBLIMA.setAppState( STATE.ADMIN_MENU);
            case 2:
                ((PublicHolidayManager) holidayManager).deleteHoliday();
                System.out.println("Redirecting to Menu...");
                MOBLIMA.setAppState(STATE.ADMIN_MENU);
            case 0:
                MOBLIMA.setAppState( STATE.ADMIN_MENU);
        }
    }
}
