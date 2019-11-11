package controller;

import entity.*;
import utils.ScannerErrorHandler;
import utils.SerializeDB;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MovieScreeningManager implements MovieScreening_inf {
    private ArrayList<MovieScreening> movieScreeningList = new ArrayList<MovieScreening>();
    private ArrayList<Cineplex> cineplexList;
    private ArrayList<Movie> movieList;
    DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

    public MovieScreeningManager(){
        this.importData();
    }

    public ArrayList<MovieScreening> mgMovieprinting(String movieTitle){
        ArrayList<MovieScreening> userSelection = new ArrayList<MovieScreening>();
        int userIndex =1;
        movieScreeningList = sortByDate(movieScreeningList);
        ArrayList<String> cinplexToPrint = cineplexToPrint(movieTitle);
        ArrayList<MovieScreening> filteredList =  filterMovieScreening(movieTitle);
        for(int i =0; i<cinplexToPrint.size();i++){
            System.out.println("Cineplex:"+cinplexToPrint.get(i));
            System.out.println("Movie Title: "+movieTitle);
            for(int j=0; j<filteredList.size();j++){
                    if(j == 0){
                        if(filteredList.get(j).getCineplexes().get(0).getName().equals(cineplexList.get(i))){
                            System.out.println("Date:"+dateFormatter.format(filteredList.get(j).getShowDate()));
                        }

                    }

                    else if (!dateFormatter.format(filteredList.get(j).getShowDate()).equals(dateFormatter.format(filteredList.get(j-1).getShowDate()))&&filteredList.get(j).getCineplexes().get(0).getName().equals(cinplexToPrint.get(i))) {
                            System.out.println();
                            System.out.println("Date:" + dateFormatter.format(filteredList.get(j).getShowDate()));
                        }

                    if(cinplexToPrint.get(i).equals(filteredList.get(j).getCineplexes().get(0).getName())){
                        userSelection.add(filteredList.get(j));
                        System.out.print(String.format("%04d",filteredList.get(j).getShowTime()) + "["+userIndex+"]"+" ");
                        userIndex++;
                    }

            }
            System.out.println();
            System.out.println("=================");
        }



        return userSelection;
    }

    public void createMovieScreening() {
        ScannerErrorHandler scan = new ScannerErrorHandler();
        int cineplexchoice;
        int cinemaChoice;
        System.out.println("===Create Movie Screening===");
        System.out.println("Please Select Cineplex ");
        printCineplex();
        cineplexchoice = scan.nextInt();

        System.out.println("Please Select Cinema ");
        printCinema(cineplexchoice-1);
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
            System.out.println("Problematic la: ");
        }

        System.out.println("Enter time: (HHMM)");
        int timeSlot = scan.nextInt();

        ArrayList<Seat> seats= ((Cineplex)cineplexList.get(cineplexchoice-1)).getCinemas().get(cinemaChoice-1).getSeats();
        ArrayList<Seat> occupySeats= ((Cineplex)cineplexList.get(cineplexchoice-1)).getCinemas().get(cinemaChoice-1).getOccupiedSeats();
        int aisleLocation = ((Cineplex)cineplexList.get(cineplexchoice-1)).getCinemas().get(cinemaChoice-1).getAisleLocation();
        String name = ((Cineplex)cineplexList.get(cineplexchoice-1)).getCinemas().get(cinemaChoice-1).getName();
        String cinemaID = ((Cineplex)cineplexList.get(cineplexchoice-1)).getCinemas().get(cinemaChoice-1).getCinemaID();
        String cineplexName2 = ((Cineplex)cineplexList.get(cineplexchoice-1)).getName();
        Cinema cinema = new Cinema(seats,occupySeats,aisleLocation,name,cinemaID);
        ArrayList<Cinema> cinemaArrayList = new ArrayList<Cinema>();
        cinemaArrayList.add(cinema);
        ArrayList<Movie> movie = new ArrayList<Movie>();
        movie.add(movieList.get(movieIndex-1));

        Cineplex cineplex = new Cineplex(cinemaArrayList,cineplexName2);
        ArrayList<Cineplex> cineplexList = new ArrayList<Cineplex>();
        cineplexList.add(cineplex);


        MovieScreening newScreening = new MovieScreening(cineplexList,movie,date,timeSlot);
        this.movieScreeningList.add(newScreening);
        SerializeDB.writeSerializedObject("moviescreening4.dat", this.movieScreeningList);
        System.out.println("Movie Screening Added!");

    }

    public void printScreeningList() {
        System.out.println("===The Current Screening Value===");
        for (int i = 0; i < this.movieScreeningList.size(); i++) {
            System.out.println("["+(i+1)+"]"+"Cineplex: "+ movieScreeningList.get(i).getCineplexes().get(0).getName());
            System.out.println("Cinema:"+movieScreeningList.get(i).getCineplexes().get(0).getCinemas().get(0).getName());
            System.out.println( "Movie:"+ movieScreeningList.get(i).getMovies().get(0).getTitle());
            int theTiming = movieScreeningList.get(i).getShowTime();
            if(theTiming < 1000){
                String leftpadTime = String.format("%04d",theTiming);
            }
            Date newdate = movieScreeningList.get(i).getShowDate();
            String showdate = dateFormatter.format(newdate);

            System.out.println( "Show Date:"+ showdate);

            if(theTiming < 1000){
                String leftpadTime = String.format("%04d",theTiming);
                System.out.println("Show Time: " + leftpadTime);
            }
            else{
                System.out.println("Show Time: " + theTiming);
            }
         System.out.println("===============");
        }
    }
   public void updateMovieScreening() { // TODO: to be done by BENG

       printScreeningList();
       System.out.println("===Please select the index to Update===");
       ScannerErrorHandler scan = new ScannerErrorHandler();
       int choice = scan.nextInt();
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
           System.out.println("Current Cineplex: "+this.movieScreeningList.get(choice-1).getCineplexes().get(0).getName());
           System.out.println("Select New Cineplex Index: ");
           printCineplex();
           int cineplexchoice;
           cineplexchoice  = scan.nextInt();
           this.movieScreeningList.get(choice-1).setCineplexes(cineplexList.get(cineplexchoice-1).getName());
           SerializeDB.writeSerializedObject("moviescreening4.dat", this.movieScreeningList);
           System.out.println("Successfully Updated!");
       }

       else if (attr==2) {
           System.out.println("Current Cinema: "+this.movieScreeningList.get(choice-1).getCineplexes().get(0).getCinemas().get(0).getName());
           System.out.println("Select New Cinema Index: ");
           int cineplexchoice =0;
           String currentCineplex = this.movieScreeningList.get(choice-1).getCineplexes().get(0).getCinemas().get(0).getName();
           // get cineplex index
           for(int i =0; i < this.cineplexList.size();i++){
               if (currentCineplex.equals(this.cineplexList.get(i).getName())){
                   cineplexchoice = i;
                   break;
               }
           }
           printCinema(cineplexchoice);
           int cinemaChoice = scan.nextInt();
           this.movieScreeningList.get(choice-1).setCinema(cineplexList.get(cineplexchoice).getCinemas().get(cinemaChoice-1).getName());
           SerializeDB.writeSerializedObject("moviescreening4.dat", this.movieScreeningList);
           System.out.println("Successfully Updated!");
       }

       else if (attr==3) {
           System.out.println("Current Movie: "+this.movieScreeningList.get(choice-1).getMovies().get(0).getTitle());
           // TO ADD YN PART
           System.out.println("Select New Movie Index: ");
           printMovie();
           int movieChoice = scan.nextInt();
           this.movieScreeningList.get(choice-1).setMovies(movieList.get(movieChoice-1).getTitle());
           SerializeDB.writeSerializedObject("moviescreening4.dat", this.movieScreeningList);
           System.out.println("Successfully Updated!");
       }

       else if (attr==4) {
           System.out.print("Current Date: ");
           Date newdate = movieScreeningList.get(choice-1).getShowDate();
           String showdate = dateFormatter.format(newdate);
           System.out.println(showdate);
           System.out.println("Enter New Date:(dd/MM/yyyy)");

           String dateInStr = scan.nextLine();
           Date date = new Date();
           try {
               date = dateFormatter.parse(dateInStr);
           } catch (ParseException e) {
               e.printStackTrace();
               System.out.println("Problematic la: ");
           }
           this.movieScreeningList.get(choice-1).setShowDate(date);
           SerializeDB.writeSerializedObject("moviescreening4.dat", this.movieScreeningList);
           System.out.println("Successfully Updated!");
       }

       else if (attr==5) {
           System.out.println("Current Time: ");
           int theTiming =this.movieScreeningList.get(choice-1).getShowTime();
           if(theTiming < 1000){
               String leftpadTime = String.format("%04d",theTiming);
               System.out.println(leftpadTime);
           }
           else{
               System.out.println(theTiming);
           }
           System.out.println("Enter New Time: ");
           int showTime = scan.nextInt();
           this.movieScreeningList.get(choice-1).setShowTime(showTime);
           SerializeDB.writeSerializedObject("moviescreening4.dat", this.movieScreeningList);
           System.out.println("Successfully Updated!");
       }
   }



   public void deleteMovieScreening() { // TODO: to be done by BENG
       printScreeningList();
       System.out.println("===Please select the index to remove===");
       ScannerErrorHandler scan = new ScannerErrorHandler();
       int choice = scan.nextInt();
       movieScreeningList.remove(choice-1);
       SerializeDB.writeSerializedObject("moviescreening4.dat", this.movieScreeningList);
       System.out.println("Have been successfully removed");


   }

    private void printCineplex(){
        for(int i = 0; i < this.cineplexList.size(); i++){
            String CineplexName = this.cineplexList.get(i).getName();
            System.out.println((i+1) + ". " + CineplexName);
        }
    }

    private void printCinema(int cineplexchoice){
        for( int i = 0; i < this.cineplexList.get(cineplexchoice).getCinemas().size(); i++){
            String cinemaName = this.cineplexList.get(cineplexchoice).getCinemas().get(i).getName();
            System.out.println((i+1) + ". " + cinemaName);
        }
    }

    private void printMovie(){
        for( int i = 0; i < this.movieList.size(); i++){
            String movieTitle = this.movieList.get(i).getTitle();
            System.out.println((i+1) + ". " + movieTitle);
        }
    }

    public ArrayList<String> sortDateList(){
        ArrayList<String> dateList = new ArrayList<String>();
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

    public ArrayList<String> cineplexToPrint(String movieTitle){
        ArrayList<String> cineplexToShow = new ArrayList<String>();
        for(int i =0; i < movieScreeningList.size();i++){
            String movie = movieScreeningList.get(i).getMovies().get(0).getTitle();
            String cineplex = movieScreeningList.get(i).getCineplexes().get(0).getName();
            if(movie.equals(movieTitle)){
                if(!cineplexToShow.contains(cineplex)){
                    cineplexToShow.add(cineplex);
                }
            }

        }
        return cineplexToShow;
    }

    public ArrayList<MovieScreening> filterMovieScreening(String movieTitle){
        ArrayList<MovieScreening> filtedList = new ArrayList<MovieScreening>();
        for(int i =0; i < movieScreeningList.size();i++){
            String movie = movieScreeningList.get(i).getMovies().get(0).getTitle();
            if(movie.equals(movieTitle)){
                if(!filtedList.contains(movie)){
                    filtedList.add(movieScreeningList.get(i));
                }
            }

        }
        return filtedList;
    }

    public ArrayList<MovieScreening> sortByDate(ArrayList<MovieScreening> movieScreeningList){
        movieScreeningList.sort(Comparator.comparing(o->o.getShowTime()));
        movieScreeningList.sort(Comparator.comparing(o->o.getShowDate()));
        return movieScreeningList;
    }


    public void importData() {
        this.movieScreeningList = (ArrayList) SerializeDB.readSerializedObject("moviescreening4.dat");
        this.cineplexList = (ArrayList) SerializeDB.readSerializedObject("cineplex6.dat");
        this.movieList = (ArrayList) SerializeDB.readSerializedObject("Movie2.dat");
    }





}
