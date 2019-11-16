package entity;

import java.io.Serializable;

 /**
 Represents a seat in a cinema
 @author SS3_Group4
 @version 1.0
 @since 2019-11-15
*/

public class Seat implements Serializable {
    /** 
    * The determinant for an occupied seat
    */
    boolean taken;
    /** 
    * The ID of the seat
    */
    String ID;

    /**
    * Creates an unoccupied seat
    */
    public Seat() {
        taken = false;
    }

    /** 
    * Check to see if this seat is occupied
    * @return The determinant for an occupied seat
    */
    public boolean isTaken(){
        return taken;
    }

    /** 
    * Occupy this seat
    */
    public void bookSeat(){
        taken = true;
    }

    /** 
    * Unoccupy this seat
    */
    public void unbookSeat(){
        taken = false;
    }

    /** 
    * Set this seat ID based on index of this seat in an array list of seats
    * @param index The index of this seat in an array list of seats
    * @param maxCol The maximum number of column in the cinema of this seat
    */
    public void setSeatID(int index, int maxCol){
        int row, col;
        col = index % maxCol;
        row = (int)Math.floor(index / maxCol);
        ID = (char)(65+row) + String.valueOf(col); //assuming row starts from 1.
    }

    /** 
    * Gets the seat ID of this seat
    * @return The seat ID of this seat
    */
    public String getSeatID(){
        return ID;
    }
}