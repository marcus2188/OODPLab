package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Cineplex implements Serializable {
    private ArrayList<Cinema> cinemas;
    private String name;

    public Cineplex(String name, String ID) {
        this.name = name;
        cinemas = new ArrayList<Cinema>();
        cinemas.add(new Cinema("Hall 1", ID + "1", this));
        cinemas.add(new Cinema("Hall 2", ID + "2", this));
        cinemas.add(new PlatinumCinema("Hall 3", ID + "3", this));
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
