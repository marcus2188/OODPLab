package entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class MovieScreening implements Serializable {
    private String cineplex;
    private String cinema;
    private String movieTitle;
    private String showDate;
    private int showTime;
    public void printMovieScreening() {
        System.out.println("Movie Screening details:");
        System.out.println("Cineplex: " + this.getCineplex());
        System.out.println("Cinema: " + this.getCinema());
        System.out.println("Movie: " + this.getMovieTitle());
        System.out.println("Date: " + this.getShowDate());
        System.out.println("Time: " + this.getShowTime());

        return;
    }

    public MovieScreening(String cineplex, String cinema, String movieTitle, String showDate, int showTime) {

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

    public String getShowDate() {
        return showDate;
    }

    public void setShowDate(String showDate) {
        this.showDate = showDate;
    }

    public int getShowTime() {
        return showTime;
    }
    public void setShowTime(int showTime) {
        this.showTime = showTime;
    }

}
