package entity;

import java.io.Serializable;
import java.util.ArrayList;
import utils.Converter;


 /**
 Represents a cinema that exist in a cineplex
 A cinema can only exist in a cineplex
 A cinema is composed of many seats
 @author SS3_Group4
 @version 1.0
 @since 2019-11-15
*/

public class Cinema implements Serializable {
    /** 
    * The maximum seat capacity
    */
    public int maxSize = 100;
    /** 
    * The maximum row for seats
    */
    public int maxRow = 10;
    /** 
    * The maximum col for seats
    */
    public int maxCol = 10;

    /**  
    * An arraylist of seats
    */
    protected ArrayList<Seat> seats;
    /** 
    * The cinema nam
    */
    private String name;
    /** 
    * The cinema ID. Cineplex ID + Hall no.
    */
    private String cinemaID;
    /** 
    * The cineplex it exists in
    */
    private Cineplex cineplex;
    /** 
    * Determinant for platinum hall
    */
    private boolean isPlatinum;

    /** 
    * Creates a new cinema with the given name, cinemaID, and cineplex
    * @param name The cinema's name
    * @param cinemaID The cinema's ID
    * @param cineplex The cineplex it exist in
    */
    public Cinema(String name, String cinemaID, Cineplex cineplex) {
        this.name = name;
        this.cinemaID = cinemaID;
        this.cineplex = cineplex;
        this.isPlatinum = false;
        seats = new ArrayList<Seat>();
        for(int i = 0; i < maxSize; i++){
            Seat s = new Seat();
            s.setSeatID(i, maxCol);
            seats.add(s);
            
        }   
    }

    /** 
    * Change the determinant of platinum for this cinema
    * @param p The new determinant value
    */
    public void setPlatinum(boolean p){
        this.isPlatinum = p;
    }


    /** 
    * Gets the determinant of platinum for this cinema
    * @return the determinant
    */
    public boolean getPlatinum(){
        return isPlatinum;
    }

    /** 
    * Gets the ID of this cinema
    * @return this Cinema ID
    */
    public String getCinemaID() {
        return cinemaID;
    }

    /** 
    * Change the ID of this cinema
    * @param cinemaID This Cinema's ID
    */
    public void setCinemaID(String cinemaID) {
        this.cinemaID = cinemaID;
    }

    /** 
    * Gets the name of this cinema
    * @return this Cinema's name
    */
    public String getName() {
        return this.name;
    }

    /** 
    * Change the name of this cinema
    * @param name the new name for this cinema
    */
    public void setName(String name) {
        this.name = name;
    }

    /** 
    * Gets the list of seats in this cinema
    * @return list of seats in this cinema
    */
    public ArrayList<Seat> getSeatList(){
        return seats;
    }

    /** 
    * Gets the cineplex this cinema is in
    * @return the cineplex*/
    public Cineplex getCineplex(){
        return cineplex;
    }

    /** 
    * Prints the available seats in this cinema
    */
    public void printSeatAvailability(){
        int index;
        char col;
        int row;
        for(int i = 0; i < maxCol; i++){
            for(int j = 0; j < maxRow; j++){
                index = i*maxCol + j;
                if(seats.get(index).isTaken()){
                    if(j == 1){
                        System.out.print("X   ");
                    }else if(j == 7){
                        System.out.print("X   ");
                    }else if(j == 0){
                        System.out.print(Converter.intToChar(i)+" X ");
                    }
                    else{
                        System.out.print("X ");
                    }
                }else{
                    if(j == 1){
                        System.out.print("O   ");
                    }else if(j == 7){
                        System.out.print("O   ");
                    }else if(j == 0){
                        System.out.print(Converter.intToChar(i)+" O ");
                    }
                    else{
                        System.out.print("O ");
                    }
                }
            }
            System.out.println();
        }
        for(int i = 0; i < maxCol; i++){
            if(i == 1){
                System.out.print(i+ "   ");
            }else if(i == 7){
                System.out.print(i+ "   ");
            }else if(i == 0){
                System.out.print("  " + i + " ");
            }else{
                System.out.print(i+ " ");
            }
        }
        System.out.println();
    }

    /** 
    * Update the status of the seats in this cinema
    * @param s the status of the list of seats
    */
    public void updateSeats(ArrayList<Integer> s){
        for(int i = 0; i < s.size(); i++){
            if(s.get(i) == 0){
                seats.get(i).unbookSeat();
            }else if(s.get(i) == 1){
                seats.get(i).bookSeat();
            }
        }
    }


}
