package entity;

import utils.Converter;
import java.io.Serializable;
import java.util.ArrayList;

public class PlatinumCinema extends Cinema implements Serializable {
    public int maxSize = 25;
    public int maxRow = 5;
    public int maxCol = 5;

    public PlatinumCinema(String name, String cinemaID, Cineplex cineplex) {
        super(name, cinemaID, cineplex);
        super.setPlatinum(true); 
    }

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
