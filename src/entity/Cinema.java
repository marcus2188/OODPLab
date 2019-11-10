package entity;

import java.io.Serializable;
import java.util.ArrayList;
import utils.Converter;

public class Cinema implements Serializable {
    public int maxSize = 100;
    public int maxRow = 10;
    public int maxCol = 10;

    protected ArrayList<Seat> seats;
    private String name;
    private String cinemaID;
    private Cineplex cineplex;

    public Cinema(String name, String cinemaID, Cineplex cineplex) {
        this.name = name;
        this.cinemaID = cinemaID;
        this.cineplex = cineplex;
        seats = new ArrayList<Seat>();
        for(int i = 0; i < maxSize; i++){
            seats.add(new Seat());
        }   
    }

    public String getCinemaID() {
        return cinemaID;
    }

    public void setCinemaID(String cinemaID) {
        this.cinemaID = cinemaID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Seat> getSeatList(){
        return seats;
    }

    public Cineplex getCineplex(){
        return cineplex;
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
