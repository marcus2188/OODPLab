package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Cineplex implements Serializable {
    private ArrayList<Cinema> cinemas;
    private String name;

    public Cineplex(ArrayList<Cinema> cinemas, String name) {

        this.cinemas = cinemas;
        this.name = name;
    }

    public ArrayList<Cinema> getCinemas() {
        return cinemas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printCinemas(){
        for (int i = 0; i < this.cinemas.size(); i++) {
            Cinema cinema = this.cinemas.get(i);
            String cinemaName = cinema.getName();
            System.out.println((i+1) + ". " + cinemaName);
        }

    }
}
