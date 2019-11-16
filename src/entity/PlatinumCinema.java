package entity;

import utils.Converter;
import java.io.Serializable;
import java.util.ArrayList;

 /**
 Represents a platinum cinema that exist in a cineplex
 A platinum cinema can only exist in a cineplex
 A platinum cinema is composed of many seats
 A platinum cinema is a sub class of cinema
 @author SS3_Group4
 @version 1.0
 @since 2019-11-15
*/

public class PlatinumCinema extends Cinema implements Serializable {
    /** 
    * The maximum seat capacity
    */
    public int maxSize = 25;
    /** 
    * The maximum row for seats
    */
    public int maxRow = 5;
    /** 
    * The maximum col for seats
    */
    public int maxCol = 5;

    /** 
    * Creates a new platinum cinema with the given name, cinemaID, and cineplex
    * @param name The cinema's name
    * @param cinemaID The cinema's ID
    * @param cineplex The cineplex it exist in
    */
    public PlatinumCinema(String name, String cinemaID, Cineplex cineplex) {
        super(name, cinemaID, cineplex);
        super.setPlatinum(true); 
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
                    }else if(j == 0){
                        System.out.print(Converter.intToChar(i)+" X ");
                    }
                    else{
                        System.out.print("X ");
                    }
                }else{
                    if(j == 1){
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

}
