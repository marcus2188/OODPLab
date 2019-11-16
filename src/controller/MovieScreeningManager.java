package controller;

import entity.*;
import utils.ScannerErrorHandler;
import utils.SerializeDB;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 A manager to handle admin request for movie screening.
 @author SS3_Group4
 @version 1.0
 @since 2019-11-15
*/


public class MovieScreeningManager implements MovieScreening_inf {
    /** 
    * The list of movie screening from moviescreening.dat
    */
    private ArrayList<MovieScreening> movieScreeningList = new ArrayList<>();
    /** 
    * The list of cineplex from cineplex.dat
    */
    private ArrayList<Cineplex> cineplexList;
    /** 
    * The list of movie from Movie.dat
    */
    private ArrayList<Movie> movieList;
    /** 
    * A formarter to format dd/MM/yyyy into date
    */
    private DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

    /** 
    * Creates a movie screening manager and import data
    */
    public MovieScreeningManager(){
        this.importData();
    }

    /** 
    * Allows movie goer to view all movie screening list
    * @return The array list of user selected movie screening
    */
    public ArrayList<MovieScreening> viewAllListing(){
        ArrayList<MovieScreening> sortedList = sortMovieScreening(movieScreeningList);
        ArrayList<MovieScreening> userSelection = new ArrayList<>();
        ArrayList<String> cineplexToPrint = cineplexToPrint2();
        ArrayList<String> distinctDate =sortDateList();
        ArrayList<String> distinctMovie = distinctMovie();
        int userIndex =1;
        int timeslot=0;

        String movieFlag = " ";
        String dateFlag = " ";
        for(int i=0;i<cineplexToPrint.size();i++){
            System.out.println();
            System.out.println("============");
            System.out.println("Cineplex:" +cineplexToPrint.get(i));
            for(int j=0; j<sortedList.size();j++){

                    for(int l=0; l<distinctMovie.size();l++){

                        if(cineplexList.get(i).getName().equals(sortedList.get(j).getCineplex().getName())&&distinctMovie.get(l).equals(sortedList.get(j).getMovie().getTitle())){
                            if(!movieFlag.equals(distinctMovie.get(l))){
                                System.out.println();
                                System.out.println("Movie Title:"+distinctMovie.get(l));
                                movieFlag = distinctMovie.get(l);
                                dateFlag = " ";
                            }

                            for(int k =0; k<distinctDate.size();k++){
                                if(cineplexList.get(i).getName().equals(sortedList.get(j).getCineplex().getName())&&dateFormatter.format(sortedList.get(j).getShowDate()).equals(distinctDate.get(k))){

                                    if(!dateFlag.equals(distinctDate.get(k))){
                                        System.out.println();
                                        System.out.println("Date: "+distinctDate.get(k));
                                        dateFlag = distinctDate.get(k);
                                    }

                                }

                            }

                            System.out.print(String.format("%04d",sortedList.get(j).getShowTime())+"["+userIndex+"] ");
                            userSelection.add(sortedList.get(j));
                            userIndex++;


                        }

                    }
            }
            movieFlag = " ";
        }

        return userSelection;
    }

    /**
    * View movie screening based on movie title
    * @param movieTitle The movie title to filter by
    * @return The array list of user selected movie screening 
    */
    public ArrayList<MovieScreening> mgMovieprinting(String movieTitle){
        ArrayList<MovieScreening> userSelection = new ArrayList<>();
        ArrayList<String> distinctDate =sortDateList();
        int userIndex =1;
        int timeIndex =0;
        String dateFlag = " ";
        movieScreeningList = sortByDateTime(movieScreeningList);
        ArrayList<String> cineplexToPrint = cineplexToPrint(movieTitle);
        ArrayList<MovieScreening> filteredList =  filterMovieScreening(movieTitle);
        for(int i =0; i<cineplexToPrint.size();i++){
            System.out.println("Cineplex:"+cineplexToPrint.get(i));
            System.out.println("Movie Title:"+movieTitle);
            for(int j=0; j<filteredList.size();j++){
                for(int k =0; k<distinctDate.size();k++){
                    if(cineplexList.get(i).getName().equals(filteredList.get(j).getCineplex().getName())&&dateFormatter.format(filteredList.get(j).getShowDate()).equals(distinctDate.get(k))){

                        if(!dateFlag.equals(distinctDate.get(k))){
                            System.out.println();
                            System.out.println("Date:"+distinctDate.get(k));
                            dateFlag = distinctDate.get(k);
                        }
                        System.out.print(String.format("%04d",filteredList.get(j).getShowTime())+"["+userIndex+"] ");
                        userSelection.add(filteredList.get(j));
                        userIndex++;

                    }
                }

            }
            dateFlag = " ";
            System.out.println();
            System.out.println("=================");
        }

        return userSelection;
    }
    
    /** 
    * Create a movie screening
    */
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
        Cinema cinema = cineplex.getCinemas().get(cinemaChoice-1);
        Movie movie = movieList.get(movieIndex-1);


        MovieScreening newScreening = new MovieScreening(cinema, movie,date,timeSlot);
        this.movieScreeningList.add(newScreening);
        SerializeDB.writeSerializedObject("moviescreening.dat", this.movieScreeningList);
        System.out.println("Movie Screening Added!");

    }

    /** 
    * Print list of movie screening
    */
    public void printScreeningList() {
        System.out.println("===The Current Screening Value===");
        for (int i = 0; i < this.movieScreeningList.size(); i++) {
            System.out.print("["+(i+1)+"]");
            movieScreeningList.get(i).printMovieScreening();
        }
    }

    /** 
    * Update movie screening
    */
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


    /** 
    * Delete movie screening
    */
   public void deleteMovieScreening() {
       printScreeningList();
       System.out.println("===Please select the index to remove===");
       ScannerErrorHandler scan = new ScannerErrorHandler();
       int choice = scan.nextInt();
       movieScreeningList.remove(choice-1);
       SerializeDB.writeSerializedObject("moviescreening.dat", this.movieScreeningList);
       System.out.println("Have been successfully removed");
   }

    /** 
    * Print all the cineplexes
    */
    private void printCineplex(){
        for(int i = 0; i < this.cineplexList.size(); i++){
            String CineplexName = this.cineplexList.get(i).getName();
            System.out.println((i+1) + ". " + CineplexName);
        }
    }

    /**
    * Print all the cinemas in the given cineplex
    * @param cineplex The cineplex choice 
    */
    private void printCinema(Cineplex cineplex){
        for( int i = 0; i < cineplex.getCinemas().size(); i++){
            String cinemaName = cineplex.getCinemas().get(i).getName();
            System.out.println((i+1) + ". " + cinemaName);
        }
    }

    /** 
    * Print all movies
    */
    private void printMovie(){
        for( int i = 0; i < this.movieList.size(); i++){
            String movieTitle = this.movieList.get(i).getTitle();
            System.out.println((i+1) + ". " + movieTitle);
        }
    }

    /** 
    * Sort the array list of movie screening by date and time
    * @param movieScreeningList The array list of movie screening
    * @return The sorted array list of movie screening
    */
    private ArrayList<MovieScreening> sortByDateTime(ArrayList<MovieScreening> movieScreeningList){
        movieScreeningList.sort(Comparator.comparing(o->o.getShowTime()));
        movieScreeningList.sort(Comparator.comparing(o->o.getShowDate()));
        return movieScreeningList;
    }

    /** 
    * Sort the date array list
    * @return The sorted date array list
    */
    private ArrayList<String> sortDateList(){
        ArrayList<String> dateList = new ArrayList<>();
        for(int i =0; i < movieScreeningList.size();i++){
            String movieScreeningDate = dateFormatter.format(movieScreeningList.get(i).getShowDate());
            if(!dateList.contains(movieScreeningDate)){
                dateList.add(movieScreeningDate);
            }
        }
        // sort the date in order
        Collections.sort(dateList);
        return dateList;
    }

    /** 
    * Filters movie screening list by movie title and return an array list of cineplex names to print
    * @param movieTitle The movie title to filter by
    * @return The array list of cineplex names 
    */
    private ArrayList<String> cineplexToPrint(String movieTitle){
        ArrayList<String> cineplexToShow = new ArrayList<>();
        for(int i =0; i < movieScreeningList.size();i++){
            String movie = movieScreeningList.get(i).getMovie().getTitle();
            String cineplex = movieScreeningList.get(i).getCineplex().getName();
            if(movie.equals(movieTitle)){
                if(!cineplexToShow.contains(cineplex)){
                    cineplexToShow.add(cineplex);
                }
            }

        }
        return cineplexToShow;
    }

    /** 
    * Return an array list of cineplex names to print
    * @return The array list of cineplex names 
    */
    public ArrayList<String> cineplexToPrint2(){
        ArrayList<String> cineplexToShow = new ArrayList<>();
        for(int i =0; i < movieScreeningList.size();i++){
            String cineplex = movieScreeningList.get(i).getCineplex().getName();
            if(movieScreeningList.get(i).getCineplex().getName().equals(cineplex)){
                if(!cineplexToShow.contains(cineplex)){
                    cineplexToShow.add(cineplex);
                }
            }

        }
        return cineplexToShow;
    }

     /** 
    * Filters movie screening list by movie title
    * @param movieTitle The movie title to filter by
    * @return The array list of filtered movie screening
    */
    private ArrayList<MovieScreening> filterMovieScreening(String movieTitle){
        ArrayList<MovieScreening> filtedList = new ArrayList<>();
        for(int i =0; i < movieScreeningList.size();i++){
            String movie = movieScreeningList.get(i).getMovie().getTitle();
            if(movie.equals(movieTitle)){
                if(!filtedList.contains(movie)){
                    filtedList.add(movieScreeningList.get(i));
                }
            }

        }
        filtedList = sortMovieScreening(filtedList);
        return filtedList;
    }

    /** 
    * Sort an array list of movie screening
    * @return The sorted list of movie screening
    */
    private ArrayList<MovieScreening> sortMovieScreening(ArrayList<MovieScreening> movieScreeningList){
        movieScreeningList.sort(Comparator.comparing(o1->o1.getCineplex().getName()));
        movieScreeningList.sort(Comparator.comparing(o3->o3.getShowDate()));
        movieScreeningList.sort(Comparator.comparing(o2->o2.getMovie().getTitle()));
        return movieScreeningList;
    }

    /** 
    * Gets movie screenings with distinct movie
    * @return The array list of movie screening with distinct movie
    */
    private ArrayList<String> distinctMovie(){
        ArrayList<String> distinctMovie = new ArrayList<>();
        for(int i =0; i < movieScreeningList.size();i++){
            String movieTitle =movieScreeningList.get(i).getMovie().getTitle();
            if(!distinctMovie.contains(movieTitle)){
                distinctMovie.add(movieTitle);
            }
        }
        // sort the movie in order
        Collections.sort(distinctMovie);
        return distinctMovie;
    }

    /** 
    * Read data from moviescreening.dat, cineplex.dat and Movie.dat
    */
    public void importData() {
        this.movieScreeningList = (ArrayList) SerializeDB.readSerializedObject("moviescreening.dat");
        this.cineplexList = (ArrayList) SerializeDB.readSerializedObject("cineplex.dat");
        this.movieList = getBookableMovies((ArrayList) SerializeDB.readSerializedObject("Movie.dat"));
    }
    
    /** 
    * Filter out the array list of movie with status end of showing
    * @param m The array list of movie to be filtered
    * @return The filtered array list of movie
    */
    public ArrayList<Movie> getBookableMovies (ArrayList<Movie> m) {
    	for (int i=0;i<m.size();i++) {
    		if (m.get(i).getShowingStatus().equalsIgnoreCase("End of Showing")) {
    			m.remove(i);
    			i--;
    		}
    	}
    	return m;
    }


}
