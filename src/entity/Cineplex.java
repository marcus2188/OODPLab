package entity;

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
}
