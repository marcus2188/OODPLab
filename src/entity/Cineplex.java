package entity;

import controller.SystemSettings_inf;

import java.io.Serializable;
import java.util.ArrayList;

public class Cineplex implements Serializable {
    private ArrayList<Cinema> cinemas;
    private String name;

    public Cineplex(ArrayList<Cinema> cinemas) {

        this.cinemas = cinemas;
        this.name = "Golden City Cineplexes";
    }

    public ArrayList<Cinema> getCinemas() {
        return cinemas;
    }

    public String getName() {
        return name;
    }

    public void printCinemas(){
        for (int i = 0; i < this.cinemas.size(); i++) {
            Cinema cinema = this.cinemas.get(i);
            String cinemaName = cinema.getName();
            System.out.println((i+1) + ". " + cinemaName);
        }

    }
}
