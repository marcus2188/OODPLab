package entity;

import java.io.Serializable;
import utils.Converter;

public class Seat implements Serializable {
    boolean taken;
    String ID;

    public Seat() {
        taken = false;
    }

    public boolean isTaken(){
        return taken;
    }

    public void bookSeat(){
        taken = true;
    }

    public void unbookSeat(){
        taken = false;
    }

    public void setSeatID(int index, int maxCol){
        int row, col;
        col = index % maxCol;
        row = (int)Math.floor(index / maxCol);
        ID = Converter.intToChar(row) + String.valueOf(col);
    }

    public String getSeatID(){
        return ID;
    }
}