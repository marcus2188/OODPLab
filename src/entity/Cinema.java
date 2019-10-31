package entity;

import controller.SystemSettings_inf;

import java.io.Serializable;
import java.util.ArrayList;

public class Cinema implements Serializable {
    private ArrayList<Seat> seats;
    private String name;
    private char aisleLocation;

    public Cinema(ArrayList<Seat> seats) {

        this.seats = seats;
        this.aisleLocation = 'C';
        this.name = "GoldenCinema";
    }

    public void printSeats() { // has to be printed in order
        char c = 'A';
        int i ,j, k;
        System.out.print("  ");
        for (j = 0; j < this.getMaxCol(); j++) {
            if ((char)(j + 65) == this.aisleLocation) {
                System.out.print("   ");
            } else {
            System.out.print(" " + c + " ");
            c++;
            }
        }
        System.out.print("\n");
        for (i =0; i < ((int)this.getMaxRow() -64); i++){
            for (k = 0; k < this.getMaxCol(); k++) {
                if (k == 0) {
                    System.out.print(i + " ");
                }
                if ((char)(k + 65) == this.aisleLocation) {
                    System.out.print("| |");
                } else {
                    System.out.print("[ ]");
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


}
