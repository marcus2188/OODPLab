package entity;

import java.io.Serializable;
import java.util.ArrayList;

 /**
 Represents a cineplex
 A cineplex is composed of 3 cinemas
 @author SS3_Group4
 @version 1.0
 @since 2019-11-15
*/

public class Cineplex implements Serializable {
    /** 
    * The arraylist of cinemas in this cineplex
    */
    private ArrayList<Cinema> cinemas;

    /** 
    * The name of this cineplex
    */
    private String name;

    /** 
    * Creates a cineplex with the given name and ID
    * 3 cinemas will be created in this cineplex
    * @param name the cineplex's name
    * @param ID the cineplex's ID
    */
    public Cineplex(String name, String ID) {
        this.name = name;
        cinemas = new ArrayList<Cinema>();
        cinemas.add(new Cinema("Hall 1", ID + "1", this));
        cinemas.add(new Cinema("Hall 2", ID + "2", this));
        cinemas.add(new PlatinumCinema("Hall 3", ID + "3", this));
    }


    /** 
    * Gets the arraylist of cinemas
    * @return the arraylist of cinemas in this cineplex
    */
    public ArrayList<Cinema> getCinemas() {
        return cinemas;
    }

    /** 
    * Gets the name of this cineplex
    * @return the name of this cineplex
    */
    public String getName() {
        return name;
    }

    /** 
    * Change the name of this cineplex
    * @param name the new name of this cineplex 
    */
    public void setName(String name) {
        this.name = name;
    }

    /** 
    * Print the arraylist of cinemas 
    */
    public void printCinemas(){
        for (int i = 0; i < this.cinemas.size(); i++) {
            Cinema cinema = this.cinemas.get(i);
            String cinemaName = cinema.getName();
            System.out.println((i+1) + ". " + cinemaName);
        }

    }
}
