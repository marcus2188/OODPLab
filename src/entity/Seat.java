package entity;

public class Seat {
    private String row;
    private String col;

    public Seat(String row, String col) {
        this.row = row;
        this.col = col;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }
}
