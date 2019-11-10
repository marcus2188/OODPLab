package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class PlatinumCinema extends Cinema implements Serializable {
    public int maxSize = 25;
    public int maxRow = 5;
    public int maxCol = 5;

    public PlatinumCinema(String name, String cinemaID, Cineplex cineplex) {
        super(name, cinemaID, cineplex);
    }

    public void printSeatAvailability(){
        int index;
        char col;
        int row;
        for(int i = 0; i < maxCol; i++){
            for(int j = 0; j < maxRow; i++){
                index = i*maxCol + j;
                if(seats.get(index).isTaken()){
                    if(j == 2){
                        System.out.print("X   ");
                    }else if(j == 7){
                        System.out.print("X   ");
                    }else{
                        System.out.print("O");
                    }
                }else{
                    if(j == 2){
                        System.out.print("O   ");
                    }else if(j == 7){
                        System.out.print("O   ");
                    }else{
                        System.out.print("O ");
                    }
                }
            }
        }
    }

}
