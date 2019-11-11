package entity;

import java.io.Serializable;
import java.sql.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import utils.Converter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MovieScreening implements Serializable {



    private Date showDate;
    private int showTime;
    private ArrayList<Integer> seats;
    private Movie movie;
    private Cinema cinema;

    public MovieScreening(Cinema cinema, Movie movie, Date showDate, int showTime ) {
        this.showDate = showDate;
        this.showTime = showTime;
        this.movie = movie;
        this.cinema = cinema;
        seats = new ArrayList<Integer>();
        for(int i = 0; i < cinema.maxSize; i++){
            seats.add(0);
        }  
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setCineplex(Cinema cinema){
        this.cinema = cinema;
        // When change cinema, reconfigure seats
        seats = new ArrayList<Integer>();
        for(int i = 0; i < cinema.maxSize; i++){
            seats.add(0);
        }  
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
        // When change cinema, reconfigure seats
        seats = new ArrayList<Integer>();
        for(int i = 0; i < cinema.maxSize; i++){
            seats.add(0);
        }  
    }

    public ArrayList<Integer> getSeatStatus(){
        return seats;
    }

    public Movie getMovie() {
        return movie;
    }

    public Cineplex getCineplex() {
         return cinema.getCineplex();
    }

    public Cinema getCinema(){
        return cinema;
    }

    
    public int bookSeat(char row, int col){
        int r = Converter.charToInt(row);
        int index = r*cinema.maxCol + col;
        if(seats.get(index) == 1){
            System.out.println("Seat is not available.");
            return -1;
        }else{
            seats.set(index, 1);
            System.out.println("Seat is successfully booked.");
            return index;
        }
    }

    public int unbookSeat(char row, int col){
        int r = Converter.charToInt(row);
        int index = r*cinema.maxCol + col;
        if(seats.get(index) == 0){
            System.out.println("Seat is already available.");
            return index;
        }else{
            seats.set(index, 0);
            System.out.println("Seat is successfully unbooked.");
            return index;
        }
    }

    public Date getShowDate() {
        return showDate;
    }

    public void setShowDate(Date showDate) {
        this.showDate = showDate;
    }

    public int getShowTime() {
        return showTime;
    }
    public void setShowTime(int showTime) {
        this.showTime = showTime;
    }

    public void printMovieScreening() {
        DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        
        System.out.println("Cineplex: "+ getCineplex().getName());
        System.out.println("Cinema:"+ cinema.getName());
        System.out.println( "Movie:"+ movie.getTitle());
        int theTiming = showTime;
        if(theTiming < 1000){
            String leftpadTime = String.format("%04d",theTiming);
        }
        Date newdate = showDate;
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
