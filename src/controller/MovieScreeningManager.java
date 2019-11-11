package controller;

import entity.*;
import utils.ScannerErrorHandler;
import utils.SerializeDB;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class MovieScreeningManager implements MovieScreening_inf {
    private ArrayList<MovieScreening> movieScreeningList = new ArrayList<MovieScreening>();
    private ArrayList<Cineplex> cineplexList;
    private ArrayList<Movie> movieList;
    DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

    public MovieScreeningManager(){
        this.importData();
    }

    public void createMovieScreening() {
        ScannerErrorHandler scan = new ScannerErrorHandler();
        int cineplexchoice;
        int cinemaChoice;

        System.out.println("===Create Movie Screening===");
        System.out.println("Please Select Cineplex ");
        printCineplex();
        cineplexchoice = scan.nextInt();
        Cineplex cineplex = cineplexList.get(cineplexchoice-1);

        System.out.println("Please Select Cinema ");
        printCinema(cineplex);
        cinemaChoice = scan.nextInt();


        System.out.println("Enter Movie: ");
        printMovie();
        int movieIndex = scan.nextInt();

        System.out.println("Enter date: (dd/MM/yyyy) ");

        String dateInStr = scan.nextLine();
        Date date = new Date();
        try {
            date = dateFormatter.parse(dateInStr);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Wrong Format!");
        }

        System.out.println("Enter time: (HHMM)");
        int timeSlot = scan.nextInt();

        /*
        ArrayList<Seat> seats= ((Cineplex)cineplexList.get(cineplexchoice-1)).getCinemas().get(cinemaChoice-1).getSeats();
        ArrayList<Seat> occupySeats= ((Cineplex)cineplexList.get(cineplexchoice-1)).getCinemas().get(cinemaChoice-1).getOccupiedSeats();
        int aisleLocation = ((Cineplex)cineplexList.get(cineplexchoice-1)).getCinemas().get(cinemaChoice-1).getAisleLocation();
        String name = ((Cineplex)cineplexList.get(cineplexchoice-1)).getCinemas().get(cinemaChoice-1).getName();
        String cinemaID = ((Cineplex)cineplexList.get(cineplexchoice-1)).getCinemas().get(cinemaChoice-1).getCinemaID();
        String cineplexName2 = ((Cineplex)cineplexList.get(cineplexchoice-1)).getName();
        */
        Cinema cinema = cineplex.getCinemas().get(cinemaChoice-1);
        Movie movie = movieList.get(movieIndex-1);
        /*
        ArrayList<Cinema> cinemaArrayList = new ArrayList<Cinema>();
        cinemaArrayList.add(cinema);
        ArrayList<Movie> movie = new ArrayList<Movie>();
        movie.add(movieList.get(movieIndex-1));
        */
        /*
        Cineplex cineplex = new Cineplex(cinemaArrayList,cineplexName2);
        ArrayList<Cineplex> cineplexList = new ArrayList<Cineplex>();
        cineplexList.add(cineplex);
        */

        MovieScreening newScreening = new MovieScreening(cinema, movie,date,timeSlot);
        this.movieScreeningList.add(newScreening);
        SerializeDB.writeSerializedObject("moviescreening.dat", this.movieScreeningList);
        System.out.println("Movie Screening Added!");

    }

    public void printScreeningList() {
        System.out.println("===The Current Screening Value===");
        for (int i = 0; i < this.movieScreeningList.size(); i++) {
            System.out.print("["+(i+1)+"]");
            movieScreeningList.get(i).printMovieScreening();
        }
    }

   public void updateMovieScreening() { 
       int cineplexchoice;
       Cineplex cineplex;
       int cinemachoice;
       MovieScreening movieScreening;
       Cinema cinema;
       Movie movie;


       printScreeningList();
       System.out.println("===Please select the index to Update===");
       ScannerErrorHandler scan = new ScannerErrorHandler();
       int choice = scan.nextInt();
       movieScreening = movieScreeningList.get(choice-1);

       int attr=0;
       do {
           System.out.println("What attribute would you like to update?");
           System.out.println("1. Cineplex");
           System.out.println("2. Cinema");
           System.out.println("3. Movie");
           System.out.println("4. Date");
           System.out.println("5. Time");
           attr = scan.nextInt();
       } while (attr <0 && attr>5);

       if (attr==1) {
           System.out.println("Current Cineplex: "+ movieScreening.getCineplex().getName());
           System.out.println("Select New Cineplex Index: ");
           printCineplex();
           cineplexchoice  = scan.nextInt();
           cineplex = cineplexList.get(cineplexchoice-1);
           System.out.println("Select New Cinema Index: ");
           printCinema(cineplex);
           cinemachoice = scan.nextInt();
           cinema = cineplex.getCinemas().get(cinemachoice-1);
           movieScreening.setCineplex(cinema);
           SerializeDB.writeSerializedObject("moviescreening.dat", this.movieScreeningList);
           System.out.println("Successfully Updated!");
       }

       else if (attr==2) {
           System.out.println("Current Cinema: "+ movieScreening.getCinema().getName());
           System.out.println("Select New Cinema Index: ");
           cineplex = movieScreening.getCinema().getCineplex();
           printCinema(cineplex);
           cinemachoice = scan.nextInt();
           cinema = cineplex.getCinemas().get(cinemachoice-1);
           movieScreening.setCinema(cinema);
           SerializeDB.writeSerializedObject("moviescreening.dat", this.movieScreeningList);
           System.out.println("Successfully Updated!");
       }

       else if (attr==3) {
           System.out.println("Current Movie: "+movieScreening.getMovie().getTitle());
           System.out.println("Select New Movie Index: ");
           printMovie();
           int movieChoice = scan.nextInt();
           movie = movieList.get(movieChoice-1);
           movieScreening.setMovie(movie);
           SerializeDB.writeSerializedObject("moviescreening.dat", this.movieScreeningList);
           System.out.println("Successfully Updated!");
       }

       else if (attr==4) {
           System.out.println("Current Date: "+movieScreening.getShowDate());
           System.out.println("Enter New Date:(dd/MM/yyyy)");

           String dateInStr = scan.nextLine();
           Date date = new Date();
           try {
               date = dateFormatter.parse(dateInStr);
           } catch (ParseException e) {
               e.printStackTrace();
               System.out.println("Wrong Format!");
           }
           movieScreening.setShowDate(date);
           SerializeDB.writeSerializedObject("moviescreening.dat", this.movieScreeningList);
           System.out.println("Successfully Updated!");
       }

       else if (attr==5) {
           System.out.println("Current Time: "+movieScreening.getShowTime());
           System.out.println("Enter New Time: ");
           int showTime = scan.nextInt();
           movieScreening.setShowTime(showTime);
           SerializeDB.writeSerializedObject("moviescreening.dat", this.movieScreeningList);
           System.out.println("Successfully Updated!");
       }
   }



   public void deleteMovieScreening() {
       printScreeningList();
       System.out.println("===Please select the index to remove===");
       ScannerErrorHandler scan = new ScannerErrorHandler();
       int choice = scan.nextInt();
       movieScreeningList.remove(choice-1);
       SerializeDB.writeSerializedObject("moviescreening.dat", this.movieScreeningList);
       System.out.println("Have been successfully removed");
   }

    private void printCineplex(){
        for(int i = 0; i < this.cineplexList.size(); i++){
            String CineplexName = this.cineplexList.get(i).getName();
            System.out.println((i+1) + ". " + CineplexName);
        }
    }

    private void printCinema(Cineplex cineplex){
        for( int i = 0; i < cineplex.getCinemas().size(); i++){
            String cinemaName = cineplex.getCinemas().get(i).getName();
            System.out.println((i+1) + ". " + cinemaName);
        }
    }

    private void printMovie(){
        for( int i = 0; i < this.movieList.size(); i++){
            String movieTitle = this.movieList.get(i).getTitle();
            System.out.println((i+1) + ". " + movieTitle);
        }
    }


    public void importData() {
        this.movieScreeningList = (ArrayList) SerializeDB.readSerializedObject("moviescreening.dat");
        this.cineplexList = (ArrayList) SerializeDB.readSerializedObject("cineplex.dat");
        this.movieList = (ArrayList) SerializeDB.readSerializedObject("Movie.dat");
    }
}
