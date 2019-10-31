package entity;

import java.io.Serializable;

public class Seat implements Serializable {
    private char row;
    private int col;

    public Seat(char row, int col) {
        this.row = row;
        this.col = col;
    }

    public char getRow() {
        return row;
    }

    public void setRow(char row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
