package entity;

import controller.SystemSettings_inf;

import java.io.Serializable;
import java.util.ArrayList;

public class Cinema implements Serializable {
    private ArrayList<Seat> seats;
    private String name;
    private char aisleLocation;

    public Cinema(ArrayList<Seat> seats, char aisleLocation,String name) {

        this.seats = seats;
        this.aisleLocation = aisleLocation;
        this.name = name;
    }

    public void printSeats() { // has to be printed in order
        char c = 'A';
        int i ,j, k, counter =1;
        System.out.print("\t");
        for (j = 0; j < this.getMaxCol(); j++) {
            if ((char)(j + 65) == this.aisleLocation) {
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
        for (i =0; i < ((int)this.getMaxRow() -63); i++){
            for (k = 0; k < this.getMaxCol(); k++) {
                if (k == 0) {
                    System.out.print(c);
                    c++;
                }
                if ((char)(k + 65) == this.aisleLocation) {
                    System.out.print("\t| |");
                } else {
                    System.out.print("\t[ ]");
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
