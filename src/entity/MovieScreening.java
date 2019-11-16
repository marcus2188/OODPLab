package entity;

import java.io.Serializable;
import java.sql.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import utils.Converter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

 /**
 Represents a movie screening
 Each movie screening is associated with one movie and cinema
 @author SS3_Group4
 @version 1.0
 @since 2019-11-15
*/


public class MovieScreening implements Serializable {

    /** 
    * The show date of this movie screening
    */
    private Date showDate;
    /** 
    * The show time of this movie screening
    */
    private int showTime;
    /** 
    * The seat status with respect to this movie screening
    */
    private ArrayList<Integer> seats;
    /** 
    * The movie of this movie screening
    */
    private Movie movie;
    /** 
    * The cinema of this movie screening
    */
    private Cinema cinema;

    /** 
    * Creates a movie screening with the given cinema, movie, show date and show time
    * @param cinema The cinema this movie screening is in
    * @param movie The movie this movie screening is showing
    * @param showDate The date this movie screening is showing
    * @param showTime The time this movie screening is showing
    */
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
    
    /** 
    * Change the movie for this movie screening
    * @param movie The new movie for this movie screening
    */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    /** 
    * Change the cineplex for this movie screening
    * @param cinema The new cinema of the new cineplex for this movie screening
    */
    public void setCineplex(Cinema cinema){
        this.cinema = cinema;
        // When change cinema, reconfigure seats
        seats = new ArrayList<Integer>();
        for(int i = 0; i < cinema.maxSize; i++){
            seats.add(0);
        }  
    }

    /** 
    * Change the cinema for this movie screening
    * @param cinema The new cinema of the same cineplex for this movie screening
    */
    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
        // When change cinema, reconfigure seats
        seats = new ArrayList<Integer>();
        for(int i = 0; i < cinema.maxSize; i++){
            seats.add(0);
        }  
    }

    /** 
    * Gets the seats status
    * @return The seats status
    */
    public ArrayList<Integer> getSeatStatus(){
        return seats;
    }

    /** 
    * Gets the movie of this movie screening
    * @return The movie of this movie screening
    */
    public Movie getMovie() {
        return movie;
    }

    /** 
    * Gets the cineplex of this movie screening
    * @return The cineplex of this movie screening
    */
    public Cineplex getCineplex() {
         return cinema.getCineplex();
    }

    /** 
    * Gets the cinema of this movie screening
    * @return The cinema of this movie screening
    */
    public Cinema getCinema(){
        return cinema;
    }

    /** 
    * Update seat status by setting a specific seat to book
    * @param row The row alphabet of the seat
    * @param col The col number of the seat
    * @return The index of the seat
    */
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

     /** 
    * Update seat status by setting a specific seat to unbook
    * @param row The row alphabet of the seat
    * @param col The col number of the seat
    * @return The index of the seat
    */
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

    /** 
    * Gets the show date of this movie screening
    * @return The show date of this movie screening
    */
    public Date getShowDate() {
        return showDate;
    }

    /** 
    * Change the show date of this movie screening
    * @param showDate The new show date for this movie screening
    */
    public void setShowDate(Date showDate) {
        this.showDate = showDate;
    }

    /** 
    * Gets the show time of this movie screening
    * @return The show time of this movie screening
    */
    public int getShowTime() {
        return showTime;
    }

    /** 
    * Change the show time of this movie screening
    * @param showTime The new show time for this movie screening
    */
    public void setShowTime(int showTime) {
        this.showTime = showTime;
    }

    /** 
    * Print this movie screening
    */
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
