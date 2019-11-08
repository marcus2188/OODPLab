package controller;


import entity.Cineplex;
import utils.SerializeDB;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import entity.MovieScreening;

public class MovieScreeningManager implements MovieScreening_inf {
    private ArrayList<MovieScreening> movieScreeningList = new ArrayList<MovieScreening>();
    private ArrayList<Cineplex> cineplexList;
    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

    public MovieScreeningManager(){
        this.importData();
    }

    public void createMovieScreening() {
        Scanner scan = new Scanner(System.in);

        int cineplexchoice;
        int cinemaChoice;
        System.out.println("===Create Movie Screening===");
        System.out.println("Please Select Cineplex ");
        printCineplex();
        cineplexchoice = scan.nextInt();
        String cineplexName = this.cineplexList.get(cineplexchoice-1).getName();

        System.out.println("Please Select Cinema ");
        printCinema(cineplexchoice-1);
        cinemaChoice = scan.nextInt();
        String cinemaName =this.cineplexList.get(cineplexchoice-1).getCinemas().get(cinemaChoice-1).getName();

        System.out.println("Enter Movie: ");
        scan.nextLine();
        String movieName = scan.nextLine();

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

        MovieScreening newScreening = new MovieScreening(cineplexName,cinemaName,movieName,date,timeSlot);
        this.movieScreeningList.add(newScreening);
        SerializeDB.writeSerializedObject("moviescreening.dat", this.movieScreeningList);
        System.out.println("Movie Screening Added!");
    }

    public void printScreeningList() {
        System.out.println("===The Current Screening Value===");
        for (int i = 0; i < this.movieScreeningList.size(); i++) {
            MovieScreening perScreening = this.movieScreeningList.get(i);
            System.out.print((i+1) + ". ");
            perScreening.printMovieScreening();
        }
    }
    //public void updateMovieScreening(){}

    public void updateMovieScreening(){
            printScreeningList();
            System.out.println("===Please select the index to Update===");
            Scanner scan = new Scanner(System.in);
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
            System.out.println("Current Cineplex: "+this.movieScreeningList.get(choice-1).getCineplex());
            System.out.println("Select New Cineplex Index: ");
            printCineplex();
            int cineplexchoice;
            cineplexchoice  = scan.nextInt();
            this.movieScreeningList.get(choice-1).setCineplex(this.cineplexList.get(cineplexchoice-1).getName());
            SerializeDB.writeSerializedObject("moviescreening.dat", this.movieScreeningList);
            System.out.println("Successfully Updated!");
        }

        else if (attr==2) {
            System.out.println("Current Cinema: "+this.movieScreeningList.get(choice-1).getCinema());
            System.out.println("Select New Cinema Index: ");
            int cineplexchoice =0;
            String currentCineplex = this.movieScreeningList.get(choice-1).getCineplex();
            // get cineplex index
            for(int i =0; i < this.cineplexList.size();i++){
                if (currentCineplex == this.cineplexList.get(i).getName()){
                    cineplexchoice = i;
                    break;
                }
            }
            printCinema(cineplexchoice);
            int cinemaChoice = scan.nextInt();
            this.movieScreeningList.get(choice-1).setCinema(this.cineplexList.get(cineplexchoice).getCinemas().get(cinemaChoice-1).getName());
            SerializeDB.writeSerializedObject("moviescreening.dat", this.movieScreeningList);
            System.out.println("Successfully Updated!");
        }

        else if (attr==3) {
            System.out.println("Current Movie: "+this.movieScreeningList.get(choice-1).getMovieTitle());
            // TO ADD YN PART
            System.out.println("Enter New Movie: ");
            scan.nextLine();
            String movieName = scan.nextLine();
            this.movieScreeningList.get(choice-1).setMovieTitle(movieName);
            SerializeDB.writeSerializedObject("moviescreening.dat", this.movieScreeningList);
            System.out.println("Successfully Updated!");
        }

        else if (attr==4) {
            System.out.println("Current Date: "+this.movieScreeningList.get(choice-1).getShowDate());
            System.out.println("Enter New Date:(dd/MM/yyyy)");
            scan.nextLine();
            String dateInStr = scan.nextLine();
            Date date = new Date();
            try {
                date = dateFormatter.parse(dateInStr);
            } catch (ParseException e) {
                e.printStackTrace();
                System.out.println("Problematic la: ");
            }
            this.movieScreeningList.get(choice-1).setShowDate(date);
            SerializeDB.writeSerializedObject("moviescreening.dat", this.movieScreeningList);
            System.out.println("Successfully Updated!");
        }

        else if (attr==5) {
            System.out.println("Current Time: "+this.movieScreeningList.get(choice-1).getShowTime());
            System.out.println("Enter New Time: ");
            int showTime = scan.nextInt();
            this.movieScreeningList.get(choice-1).setShowTime(showTime);
            SerializeDB.writeSerializedObject("moviescreening.dat", this.movieScreeningList);
            System.out.println("Successfully Updated!");
        }


    }
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
    public void printMgScreeninglist(String cineplex){
        System.out.println("===Movie Screening===");
            //Date date = new Date();
            String date = dateFormatter.format(this.movieScreeningList.get(0).getShowDate());
            System.out.println("The Date:"+date);
            ArrayList<MovieScreening> filterMovieScreening = new ArrayList<MovieScreening>();

            // Store array list of screening that have same date and cineplex
            for(int i = 0; i < this.movieScreeningList.size();i++){
                String listDate = dateFormatter.format(this.movieScreeningList.get(i).getShowDate());
                if(date.equals(listDate)&& this.movieScreeningList.get(i).getCineplex().equals(cineplex)){
                    filterMovieScreening.add(movieScreeningList.get(i));
                }
            }

            // filter same movie and print out
            String movieTitle = filterMovieScreening.get(0).getMovieTitle();
            System.out.println("The Movie:"+movieTitle);
            System.out.print("The Timings:");
            for(int i =0; i< filterMovieScreening.size();i++){
                if(filterMovieScreening.get(i).getMovieTitle().equals(movieTitle)){
                    int showTime = filterMovieScreening.get(i).getShowTime();
                    System.out.print(showTime +" ");
                }
            }

    }
    public void mgMovieprinting(String movieTitle, String showDate){




    }

    public void mgDateList(){
        ArrayList<String> dateList = new ArrayList<String>();

        for(int i =0; i < movieScreeningList.size();i++){
            String movieScreeningDate = dateFormatter.format(movieScreeningList.get(i).getShowDate());
            if(!dateList.contains(movieScreeningDate)){
                dateList.add(movieScreeningDate);
            }
        }
        // sort the date in order

    }
    public void deleteMovieScreening(){
        printScreeningList();
        System.out.println("===Please select the index to remove===");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        movieScreeningList.remove(choice-1);
        SerializeDB.writeSerializedObject("moviescreening.dat", this.movieScreeningList);
        System.out.println("Have been successfully removed");

    }


    private void printCineplex(){
        for(int i = 0; i < this.cineplexList.size(); i++){
            String CineplexName = this.cineplexList.get(0).getName();
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

    }

    public void importData() {
        this.movieScreeningList = (ArrayList) SerializeDB.readSerializedObject("moviescreening.dat");
        this.cineplexList = (ArrayList) SerializeDB.readSerializedObject("cineplex.dat");

    }




}
