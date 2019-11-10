package entity;

import java.io.Serializable;
import java.sql.Array;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import utils.Converter;
import entity.Movie;

public class MovieScreening implements Serializable {
    private Cinema cinema;
    private Movie movie;
    private Timestamp time;
    private ArrayList<Integer> seats;

    public MovieScreening(Cinema cinema, Movie movie, Timestamp time) {
        this.cinema = cinema;
        this.movie = movie;
        this.time = time;
        seats = new ArrayList<Integer>();
        for(int i = 0; i < cinema.maxSize; i++){
            seats.add(0);
        }   
    }

    public void printMovieScreening() {
        Date date = time;
        System.out.println("Movie Screening details:");
        System.out.println("Cineplex: " + cinema.getCineplex().getName());
        System.out.println("Cinema: " + cinema.getName());
        System.out.println("Movie: " + movie.getTitle());
        System.out.println("Screen Time: " + time);
    }

    // get setter
    public Cineplex getCineplex() {
        return cinema.getCineplex();
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

    public Cinema getCinema(){
        return this.cinema;
    }

    public Movie getMovie() {
        return this.movie;
    }

    public Timestamp getShowTime() {
        return this.time;
    }

    public ArrayList<Integer> getSeatStatus(){
        return seats;
    }

    public void setShowTime(Timestamp time) {
        this.time = time;
    }

    public void bookSeat(char row, int col){
        int r = Converter.charToInt(row);
        int index = r*cinema.maxCol + col;
        if(seats.get(index) == 1){
            System.out.println("Seat is not available.");
        }else{
            seats.set(index, 1);
            System.out.println("Seat is successfully booked.");
        }
    }

    public void unbookSeat(char row, int col){
        int r = Converter.charToInt(row);
        int index = r*cinema.maxCol + col;
        if(seats.get(index) == 0){
            System.out.println("Seat is already available.");
        }else{
            seats.set(index, 0);
            System.out.println("Seat is successfully unbooked.");
        }
    }

}
