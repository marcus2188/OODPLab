package controller;

import entity.Cinema;
import entity.Cineplex;
import utils.SerializeDB;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import boundary.Admin_UI;
import entity.MovieScreening;

public class MovieScreeningManager  {
    private ArrayList<MovieScreening> movieScreeningList = new ArrayList<>();
    private ArrayList<Cineplex> cineplexList;


    public MovieScreeningManager(){
        this.importData();
    }

    public void createMovieScreening() {
        Scanner scan = new Scanner(System.in);

        int cineplexchoice;
        int cinemaChoice;
        System.out.println("Please Select Cineplex ");
        for(int i = 0; i < this.cineplexList.size(); i++){
            String CineplexName = this.cineplexList.get(0).getName();
            System.out.println((i+1) + ". " + CineplexName);
        }

        cineplexchoice = scan.nextInt();
        String cineplexName = this.cineplexList.get(cineplexchoice-1).getName();

        System.out.println("Please Select Cinema ");
        for( int i = 0; i < this.cineplexList.get(cineplexchoice-1).getCinemas().size(); i++){
            String cinemaName = this.cineplexList.get(cineplexchoice-1).getCinemas().get(i).getName();
            System.out.println((i+1) + ". " + cinemaName);
        }
        cinemaChoice = scan.nextInt();
        String cinemaName =this.cineplexList.get(cineplexchoice-1).getCinemas().get(cinemaChoice-1).getName();

        System.out.println("Enter Movie: ");
        scan.nextLine();
        String movieName = scan.nextLine();

        System.out.println("Enter date: (dd/MM/yyyy) ");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
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

        MovieScreening newScreening = new MovieScreening(cineplexName,cinemaName,movieName,date,timeSlot);
        this.movieScreeningList.add(newScreening);
        SerializeDB.writeSerializedObject("moviescreening.dat", this.movieScreeningList);
        System.out.println("Movie Screening Added!");
    }

    public void printScreeningList() {
        System.out.println("===The current Screening Value===");
        for (int i = 0; i < this.movieScreeningList.size(); i++) {
            MovieScreening perScreening = this.movieScreeningList.get(i);
            System.out.print((i+1) + ". ");
            perScreening.printMovieScreening();
        }
    }
    /*public void updateMovieScreening(){
            printScreeningList();
            System.out.println("===Please select the index to Update===");
            Scanner scan = new Scanner(System.in);
            int choice = scan.nextInt();
            System.out.println("===Current Cineplex===");
            String cinemaName =this.cineplexList.get(choice-1).getCinemas().get(cinemaChoice-1).getName();
    }*/
    /*public void manualInsertData() {

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateInStr = "24/05/2015";
        Date date = new Date();
        try {
            date = dateFormatter.parse(dateInStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //int thetime = 0800;
        //System.out.println("the time is "+justaname);
       // MovieScreening movieScreening = new MovieScreening("Punggol","hall 3","Far from Home",date,justaname);
       // this.movieScreeningList.add(movieScreening);
        //SerializeDB.writeSerializedObject("moviescreening.dat", this.movieScreeningList);
    }*/

    public void removeMovieScreening(){
        printScreeningList();
        System.out.println("===Please select the index to remove===");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        movieScreeningList.remove(choice-1);
        SerializeDB.writeSerializedObject("moviescreening.dat", this.movieScreeningList);
        System.out.println("Have been successfully removed");

    }



    private void importData() {
        this.movieScreeningList = (ArrayList) SerializeDB.readSerializedObject("moviescreening.dat");
        this.cineplexList = (ArrayList) SerializeDB.readSerializedObject("cineplex.dat");

    }

    // getMovieScreening(List<MovieScreening> screeningList)


}
