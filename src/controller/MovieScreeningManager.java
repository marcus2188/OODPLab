package controller;

import entity.Cinema;
import entity.Cineplex;
import utils.SerializeDB;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import boundary.Admin_UI;
import entity.MovieScreening;

public class MovieScreeningManager implements MovieScreening_inf {
    private ArrayList<MovieScreening> movieScreeningList = new ArrayList<MovieScreening>();
    private ArrayList<Cinema> cinemaList;

    public MovieScreeningManager(){
        this.importData();
    }

    public void createMovieScreening() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Cineplex: ");
        String cineplexName = scan.nextLine();

        System.out.println("Enter Cinema: ");
        String cinemaName = scan.nextLine();

        System.out.println("Enter Movie: ");
        String movieName = scan.nextLine();

        System.out.println("Enter date: ");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        String dateInStr = scan.nextLine();
        Date date = new Date();
        try {
            date = dateFormatter.parse(dateInStr);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Problematic la: ");
        }


        System.out.println("Enter time: ");
        int timeSlot = scan.nextInt();

        MovieScreening newScreening = new MovieScreening(cineplexName,cinemaName,movieName,dateInStr,timeSlot);
        this.movieScreeningList.add(newScreening);
        SerializeDB.writeSerializedObject("moviescreening.dat", this.movieScreeningList);
        System.out.println("Movie Screening Added!");
    }

    public void printScreeningList() {
        //System.out.println("===The current Screening Value===");
        //for (int i = 0; i <= this.movieScreeningList.size(); i++) {
          //  MovieScreening perScreening = (MovieScreening) this.movieScreeningList.get(i);
         //   System.out.print(i + ". ");
        //    perScreening.printMovieScreening();
      //  }

        System.out.println("===Cinema Listing===");
        Cineplex perCinema = new Cineplex(cinemaList);
        //for(int i = 0; i <= this.cinemaList.size(); i++){
                //Cinema perCinema = this.cinemaList.get(i);
            //ArrayList<Cinema>  cinemaName = perCinema.getCinemas();
            //String NAMENAMENAME = cinemaName.get(0).getName();
                String cinemaName = perCinema.getCinemas().get(0).getName();
                System.out.println((1) + ". " + cinemaName);
           // }

       // Cineplex cineplex = new Cineplex(cinemaList);
       // cineplex.printCinemas();
    }
   // public void updateMovieScreening()
   // public void removeMovieScreening()




    public void importData() {
        this.movieScreeningList = (ArrayList) SerializeDB.readSerializedObject("moviescreening.dat");
        this.cinemaList = (ArrayList) SerializeDB.readSerializedObject("cineplex.dat");
    }

    // getMovieScreening(List<MovieScreening> screeningList)


}
