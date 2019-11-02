import boundary.Admin_UI;
import controller.MovieTicketManager;

import java.util.Scanner;

public class Main {
    private boolean admin;
    private boolean running = true;
    private static final String PASSWORD = "root";
    enum STATE {LOGIN, MOVIE_GOER_MENU, ADMIN_MENU, MOVIE_GOER_MOVIE_DETAILS, MOVIE_GOER_MOVIE_LIST, MOVIE_GOER_BOOKING, 
                ;}
    private STATE state = STATE.LOGIN;

    // Initialize controllers
    private MovieGoer_UI = new movieMGManager;
    private MovieGoer_UI = new movieScreeningManager;
    private Admin_UI = new movieAdminManager;
    private Admin_UI = new movieScreeningManagerAdmin;

    public static void main(String[] args) {
        while(running){
            switch(state){
                case STATE.LOGIN:
                    printMenu();
                    break;
                case STATE.MOVIE_GOER_MENU:
                    mgMenu(movieMGManager);
                    break;
                case STATE.ADMIN_MENU:
                    adminMenu(movieAdminManager, movieScreeningManagerAdmin);
                    break;
                case STATE.MOVIE_GOER_MOVIE_LIST:
                    movieListMenu(movieMGManager);
                    break;
                case STATE.MOVIE_GOER_MOVIE_DETAILS:
                    movieDetailsMenu(movieMGManager, movieScreeningManager);
                    break;
                case STATE.MOVIE_GOER_BOOKING:
                    bookingMenu();
                    break;
                case STATE.ADMIN_MENU:
                    adminMenu();
                    break;


                default:

            }
        }
    }

    private static void printMenu() {
        int choice = 0;
        String password;

        System.out.println("Welcome to MOBLIMA App!");
        System.out.println("Press 1 for movie goer");
        System.out.println("Press 2 for admin");
        System.out.println("========================");

        Scanner scan = new Scanner(System.in);
        choice = scan.nextInt();
        switch (choice) {

            case 1:
                System.out.println("Loading movie goer page...");
                state = STATE.MOVIE_GOER_MENU;
                break;

            case 2:
                System.out.println("Please enter password: ");
                password = scan.next();
                if(password.equals(PASSWORD)){
                    System.out.println("Loading admin page...");
                    state = STATE.ADMIN_MENU;
                    break;
                }else{
                    System.out.println("Invalid Password!");
                    System.out.println("========================");
                    state = STATE.LOGIN;
                    break;
                }

            default:
                System.out.println("Invalid choice, try again...");
                state = STATE.LOGIN;
                break;
        }
    }

    // Admin menus
    private static void adminMenu(Admin_UI movieAdminManager, Admin_UI movieScreeningManagerAdmin) {
        int choice = 0;
        int id;
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome, admin!");
        System.out.println("Press 1 to create a movie in movie listing.");
        System.out.println("Press 2 to update a movie in movie listing.");
        System.out.println("Press 3 to delete a movie in movie listing.");
        System.out.println("Press 4 to create a show time for a movie.");
        System.out.println("Press 5 to update a show time for a movie.");
        System.out.println("Press 6 to delete a show time for a movie.");
        System.out.println("Press 7 to update price");
        System.out.println("Press 8 to update public holidays.");
        System.out.println("Press 9 to exit to main menu.");
        System.out.println("===========================================");

        
        Scanner scan = new Scanner(System.in);
        choice = scan.nextInt();
        switch(choice){
            case 1:
                createMovieRequest(movieAdminManager);
                break;
            case 2:
                updateMovieRequest(movieAdminManager);
                break;
            case 3:
                removeMovieRequest(movieAdminManager);
                break;
            case 4:
                createMovieScreeningRequest(movieScreeningManagerAdmin);
                break;
            case 5:
                updateMovieScreeningRequest(movieScreeningManagerAdmin);
                break;
            case 6:
                removeMovieScreeningRequest(movieScreeningManagerAdmin);
                break;
            case 7:
                updateTicketPriceRequest();
                break;
            case 8:
                updatePublicHolidayRequest();
            case 9:
                state = STATE.LOGIN;
            default:
                state = STATE.ADMIN_MENU;
    }


    // Movie goer menus
    private static void mgMenu(MovieGoer_UI movieMGManager, MovieGoer_UI shoppingOrderManager) {
        int choice = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome, Movie Goer!");
        System.out.println("Press 1 to list all movies.");
        System.out.println("Press 2 to search a movie.");
        System.out.println("Press 3 to list top 5 movies.");
        System.out.println("Press 4 to view shopping cart");
        System.out.println("Press 5 to view transaction history");
        System.out.println("===================================");
        choice = scan.nextInt();

        switch(choice){
            case 1:
                listAllMoviesRequest(movieMGManager);
                state = STATE.MOVIE_GOER_MOVIE_LIST;
                break;
            case 2:
                searchMovieRequest(movieMGManager);
                state = STATE.MOVIE_GOER_MOVIE_LIST;
                break;
            case 3:
                listTop5MoviesRequest(movieMGManager);
                state = STATE.MOVIE_GOER_MOVIE_LIST;
                break;
            case 4:
                showShoppingOrderRequest(shoppingOrderManager);
                shoppingMenu();
                break;
            case 5:
                showTransactionHistoryRequest();
                break;

            default:
                state = STATE.MOVIE_GOER_MENU;
                break;
        }
    }

    public static void shoppingMenu(MovieGoer_UI shoppingOrderManager){
        int choice = 0;
        Scanner scan = new Scanner(System.in);
        if(!shoppingOrderManager.isEmpty()){
            System.out.println("Press 1 to checkout.");
            System.out.println("Press 2 to return to menu.");
            choice = scan.nextInt();
            switch(choice){
                case 1:
                    checkoutMenu(shoppingOrderManager);
                    break;
                case 2:
                    state= STATE.MOVIE_GOER_MENU;
                    break;
                default:
                    break;
            }
        }
    }

    public static checkoutMenu(MovieGoer_UI shoppingOrderManager){
        String name;
        String mobileNumber;
        String email;
        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter your name: ");
        name = scan.next();
        System.out.println("Please enter your mobile number: ");
        mobileNumber = scan.next();
        System.out.println("Please enter your email: ");
        email = scan.next();
        shoppingOrderManager.makePurchase();
        showShoppingOrder.printReceipt();
    }

    public static void movieListMenu(MovieGoer_UI movieMGManager){
        int id;
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter movie id for more details: ");
        id = scan.nextInt();
        viewMovieDetailsRequest(movieAdminManager, id);
        state = STATE.MOVIE_GOER_MOVIE_DETAILS;
    }

    public static void movieDetailsMenu(MovieGoer_UI movieMGManager, MovieGoer_UI movieScreeningManager){
        int choice = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("===================================");
        System.out.println("Press 1 to add a review or rating.");
        System.out.println("Press 2 to view show time.");
        System.out.println("Press 3 to view pass reviews.");
        System.out.println("Press 4 to make booking.");
        System.out.println("===================================");
        choice = scan.nextInt();

        switch(choice){
            case 1:
                addReviewRequest(movieMGManager)
                break;
            case 2:
                listShowtimeRequest(movieScreeningManager);
                break;
            case 3:
                printPassReviewsRequest(movieMGManager);
                break;
            case 4:
                state = STATE.MOVIE_GOER_BOOKING;
                break;
            default:
                state = STATE.MOVIE_GOER_MENU;
                break;
        }
    }

    public static void bookingMenu(MovieGoer_UI seatManager){
        int id = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("===================================");
        System.out.println("Please enter the showtime id: ");
        seatManager.seatAvailability();
        System.out.println("Please enter the seats you want to book.");
        seatManager.seatSelection();
        state = STATE.MOVIE_GOER_MENU;
    }

    public static void showShoppingOrderRequest(MovieGoer_UI shoppingOrderManager){
        shoppingOrderManager.showShoppingOrder();
    }

    public static void listShowtimeRequest(MovieGoer_UI movieScreeningManager, Movie m){
        movieScreeningManager.listMovieScreening(m);
    }

    public static void printPassReviewsRequest(MovieGoer_UI movieMGManager, Movie m){
        movieMGManager.printPassReviews(m);
    }

    public static void addReviewRequest(MovieGoer_UI movieMGManager){
        Scanner scan = new Scanner(System.in);
        String comments;
        int rating;

        System.out.println("Please enter your review: ");
        comments = scan.next();
        System.out.println("Please enter your rating(1-5): ");
        rating = scan.nextInt();
        movieMGManager.addMovieReview(comments, rating);

    }

    public static void viewMovieDetailsRequest(MovieGoer_UI movieMGManager, int id){
        movieMGManager.viewMovieDetails(id);
    }

    public static void listAllMoviesRequest(MovieGoer_UI movieMGManager){
        movieMGManager.listAllMovie();
    }

    public static void searchMovieRequest(MovieGoer_UI movieMGManager){
        String title;
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the Movie Title: ");
        title = scan.next();
        movieMGManager.searchMovie(movieTitle);
    }

    public static void listTop5MoviesRequest(MovieGoer_UI movieAdminManager){
        int choice = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("Press 1 to view top 5 movies ranked by ticket sales.");
        System.out.println("Press 2 to view top 5 movies ranked by overall rating.");
        choice = scan.nextInt();
        switch(choice){
            case 1:
                movieAdminManager.listTop5(true);
                break;
            case 2:
                movieAdminManager.listTop5(false);
                break;
            default:
        }
    }

    public static void showTransactionHistoryRequest(){
        
    }

    public static void createMovieRequest(Admin_UI movieAdminManager){
        String title, showingStatus, sypnosis, director, castName, blockBuster;
        int noOfCast, i;
        boolean isBlockbuster;
        String[] cast;
        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter the movie title: ");
        title = scan.next();
        System.out.println("Please enter showing status: ");
        showingStatus = scan.next();
        System.out.println("Please enter the sypnosis: ");
        sypnosis = scan.next();
        System.out.println("Please enter the director: ");
        director = scan.next();
        System.out.println("How many cast are there in this movie?")
        noOfCast = scan.nextInt();
        cast = new String[noOfCast];
        for(i = 0; i < noOfCast; i++)}{
            System.out.println("Please enter cast name: ");
            castName = scan.next();
            cast[i] = castName;
        }
        System.out.println("Is it a blockbuster?(Y/N)");
        blockBuster = scan.next();
        if(blockBuster.equals("Y")){
            isBlockbuster = true;
        }else if(blockbuster.equals("N")){
            isBlockbuster = false;
        }

        movieAdminManager.createMovie(title, showingStatus, sypnosis, director, cast, isBlockbuster);
    }

    public static void updateMovieRequest(Admin_UI movieAdminManager){
        String title, showingStatus, sypnosis, director, castName, blockBuster;
        int noOfCast, i, choice, id;
        boolean isBlockbuster;
        String[] cast;
        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter the movie id: ");
        id = scan.nextInt();
        System.out.println("Press 1 to update title.");
        System.out.println("Press 2 to update showing status.");
        System.out.println("Press 3 to update sypnosis.");
        System.out.println("Press 4 to update director.");
        System.out.println("Press 5 to update cast.");
        System.out.println("Press 6 to update blockbuster status.");
        System.out.println("=================================");

        choice = scan.nextInt();
        switch(choice){
            case 1:
                System.out.println("Please enter new movie title: ");
                title = scan.next();
                movieAdminManager.updateMovieTitle(title);
                break;
            case 2:
                System.out.println("Please enter new showing status: ");
                showingStatus = scan.next();
                movieAdminManager.updateMovieShowingStatus(showingStatus);
                break;
            case 3:
                System.out.println("Please enter the sypnosis: ");
                sypnosis = scan.next();
                movieAdminManager.updateMovieSypnosis(sypnosis);
                break;
            case 4:
                System.out.println("Please enter new director: ");
                director = scan.next();
                movieAdminManager.updateMovieDirector(director);
                break;
            case 5:
                System.out.println("How many cast are there in this movie?")
                noOfCast = scan.nextInt();
                cast = new String[noOfCast];
                for(i = 0; i < noOfCast; i++)}{
                    System.out.println("Please enter cast name: ");
                    castName = scan.next();
                    cast[i] = castName;
                }
                movieAdminManager.updateMovieCast(cast);
                break;
            case 6:
                System.out.println("Is it a blockbuster?(Y/N)");
                blockBuster = scan.next();
                if(blockBuster.equals("Y")){
                    isBlockbuster = true;
                }else if(blockbuster.equals("N")){
                    isBlockbuster = false;
                }
                movieAdminManager.updateMovieBlockBuster(isBlockbuster);
                break;
            default:
                break;
        }
    }

    public static void removeMovieRequest(Admin_UI movieAdminManager){
        Scanner scan = new Scanner(System.in);
        int id;

        System.out.println("Please enter the movie id: ");
        id = scan.nextInt();
        movieAdminManager.removeMovie(id);
    }

    public static void createMovieScreeningRequest(Admin_UI movieScreeningManager){
        String screeningFormat, publicHoliday;
        int id;
        boolean isPH;
        Date time, date;
        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter the screening id: ");
        id = scan.nextInt();
        System.out.println("Please enter the screening format: ");
        screeningFormat = scan.next();
        System.out.println("Please enter the date of screening: ");
        // KIV
        date = scan.nextDate();
        System.out.println("Please enter the time of screening: ");
        // KIV
        time = scan.nextTime();

       movieScreeningManager.createMovieScreening(id, screeningFormat, date, time);
    }

    public static void updateMovieScreeningRequest(Admin_UI movieScreeningManager){
        String screeningFormat;
        int id, choice;
        Date time, date;
        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter the movie id: ");
        id = scan.nextInt();

        System.out.println("Press 1 to update date.");
        System.out.println("Press 2 to update time.");
        System.out.println("Press 3 to update screening format.");
        System.out.println("Press 4 to update public holiday status.");
        System.out.println("=================================");

        choice = scan.nextInt();
        switch(choice){
            case 1:
                System.out.println("Please enter new date: ");
                date = scan.nextDate();
                movieScreeningManager.updateMovieScreeningDate(date);
                break;
            case 2:
                System.out.println("Please enter new time: ");
                time = scan.nextTime();
                movieScreeningManager.updateMovieScreeningTime(time);
                break;
            case 3:
                System.out.println("Please enter the screening format: ");
                screeningFormat = scan.next();
                movieScreeningManager.updateMovieScreeningFormat(screeningFormat);
                break;
            case 4:
                System.out.println("Is it a public holiday?(Y/N)");
                publicHoliday = scan.next();
                if(publicHoliday.equals("Y")){
                    isPh = true;
                }else if(blockbuster.equals("N")){
                    isPH = false;
                }
                movieScreeningManager.updateMovieScreeningPH(isPH);
            default:
                break;
        }
    }

    public static void removeMovieScreeningRequest(Admin_UI movieScreeningManager){
        Scanner scan = new Scanner(System.in);
        int id;

        System.out.println("Please enter the screening id: ");
        id = scan.nextInt();
        movieScreeningManager.removeMovieScreening(id);
    }

    public static void updateTicketPriceRequest(Admin_UI movieTicketManger){
        movieTicketManger.updatePriceTable();
    }

    public static void updatePublicHolidayRequest(){
        
    }
}
