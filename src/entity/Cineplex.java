package entity;

import controller.SystemSettings_inf;

import java.util.ArrayList;

public class Cineplex {
    private ArrayList<Cinema> cinemas;

    public Cineplex(ArrayList<Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    public ArrayList<Cinema> getCinemas() {
        return cinemas;
    }

    public void setCinemas(ArrayList<Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    public void printCinemas(){
        for (int i = 0; i < this.cinemas.size(); i++) {
            Cinema cinema = this.cinemas.get(i);
            String cinemaName = cinema.getName();
            System.out.println(i + ". " + cinemaName);
        }

    }
}
