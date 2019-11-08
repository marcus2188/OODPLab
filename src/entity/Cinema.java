package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Cinema implements Serializable {
    private ArrayList<Seat> seats;
    private ArrayList<Seat> occupiedSeats;
    private String name;
    private int aisleLocation;
    private String cinemaID;

    public Cinema(ArrayList<Seat> seats, ArrayList<Seat> occupiedSeats, int aisleLocation, String name, String cinemaID) {
        this.occupiedSeats = occupiedSeats;
        this.seats = seats;
        this.aisleLocation = aisleLocation;
        this.name = name;
        this.cinemaID = cinemaID;
    }

    public String getCinemaID() {
        return cinemaID;
    }

    public void setCinemaID(String cinemaID) {
        this.cinemaID = cinemaID;
    }

    public void printSeats() { // has to be printed in order
        char c = 'A';
        int i ,j, k, counter =1;
        System.out.print("\t");
        for (j = 0; j < this.getMaxCol(); j++) {
            if (j == this.aisleLocation) {
                System.out.print("\t" );
            } else {
                if (j < 10) {
                    System.out.print(" " + counter+ "\t" );
                } else {
                    System.out.print(counter + "\t");
                }
                counter++;
            }
        }
        System.out.print("\n");

        for (i =0; i < ((int)this.getMaxRow() -64); i++){
            j = 1;
            for (k = 0; k < this.getMaxCol(); k++) {
                if (k == 0) {
                    System.out.print(c);
                    c++;
                }
                if (k == this.aisleLocation) {
                    System.out.print("\t| |");
                } else {
                    if (this.checkSeatOccupied( (char)(i+65), j)) {
                        System.out.print("\t[X]");
                    } else {
                        System.out.print("\t[ ]");
                    }
                    j++;
                }

            }
             System.out.print("\n");
        }
    }

    public String getName() {
        return this.name;
    }

    private int getMaxCol(){
        int max = 0;
        int i;
        for (i = 0; i < this.seats.size(); i++) {
            if (max < this.seats.get(i).getCol()) {
                max = this.seats.get(i).getCol();
            }
        }
        if (this.aisleLocation == '\u0000') {
            return max ;
        } else {
            return max + 1;
        }

    }

    private char getMaxRow() {
        char max = 'A';
        int i;
        for (i = 0; i < this.seats.size(); i++) {
            if (max < this.seats.get(i).getRow()) {
                max = this.seats.get(i).getRow();
            }
        }
        return max;
    }

    private boolean checkSeatOccupied(char row, int col) {
        for (int i = 0; i < this.occupiedSeats.size(); i++) {
            Seat s = (Seat)this.occupiedSeats.get(i);
            if (s.getRow() == row && s.getCol() == col) {
                return true;
            }
        }
        return false;
    }


}
