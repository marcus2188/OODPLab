package entity;

import java.io.Serializable;
import java.sql.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class MovieScreening implements Serializable {



    private Date showDate;
    private int showTime;
    private ArrayList<Seat> occupiedSeats;
    private  ArrayList<Movie> movies;
    private ArrayList<Cineplex> cineplexes;

    public void setOccupiedSeats(ArrayList<Seat> occupiedSeats) {
        this.occupiedSeats = occupiedSeats;
    }

    public void setMovies(String movies) {
        this.movies.get(0).setTitle(movies);
    }

    public void setCineplexes(String cineplexesName) {
        this.cineplexes.get(0).setName(cineplexesName);
    }

    public void setCinema(String cinemaName) {
        this.cineplexes.get(0).getCinemas().get(0).setName(cinemaName);
    }

    public ArrayList<Seat> getOccupiedSeats() {
        return occupiedSeats;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public ArrayList<Cineplex> getCineplexes() {
        return cineplexes;
    }



    public MovieScreening(ArrayList<Cineplex> cineplexes,ArrayList<Movie> movies,Date showDate, int showTime ) {
        this.showDate = showDate;
        this.showTime = showTime;
        this.movies = movies;
        this.cineplexes = cineplexes;
    }




    public boolean reserveSeat(char row, int col) {
        for (int i = 0; i < this.occupiedSeats.size(); i++) {
            Seat currentSeat = (Seat) this.occupiedSeats.get(i);
            if (currentSeat.getCol() == col && currentSeat.getRow() == row) {
                System.out.println("Seat already taken!");
                return false; // exits loop and tells controller that seat is already taken
            }
        }

        Seat reservedSeat = new Seat(row, col);
        this.occupiedSeats.add(reservedSeat);
        System.out.println("Seat Reserved!");
        return true;
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
