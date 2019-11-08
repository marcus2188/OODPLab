package entity;

import java.io.Serializable;
import java.text.Format;
import java.util.Date;
import java.text.SimpleDateFormat;



public class MovieScreening implements Serializable {
    private String cineplex;
    private String cinema;
    private String movieTitle;
    private Date showDate;
    private int showTime;
    public void printMovieScreening() {
        System.out.println("Movie Screening details:");
        System.out.println("Cineplex: " + this.getCineplex());
        System.out.println("Cinema: " + this.getCinema());
        System.out.println("Movie: " + this.getMovieTitle());
        Format f = new SimpleDateFormat("dd/MM/yyyy");
        //f.format(this.getShowDate())
        System.out.println("Date: " + f.format(this.getShowDate()));
        int theTiming = this.getShowTime();
        if(theTiming < 1000){
            String leftpadTime = String.format("%04d",theTiming);
            System.out.println("Time: " + leftpadTime);
        }
        else{
            System.out.println("Time: " + theTiming);
        }


    }

    public MovieScreening(String cineplex, String cinema, String movieTitle, Date showDate, int showTime) {

        this.cineplex = cineplex;
        this.cinema = cinema;
        this.movieTitle = movieTitle;
        this.showDate = showDate;
        this.showTime = showTime;
    }
    // get setter
    public String getCineplex() {
        return cineplex;
    }

    public void setCineplex(String cineplex) {
        this.cineplex = cineplex;
    }

    public String getCinema() {
        return cinema;
    }

    public void setCinema(String cinema) {
        this.cinema = cinema;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
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

}
