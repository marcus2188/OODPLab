package entity;

import java.io.Serializable;

public class Seat implements Serializable {
    boolean taken;

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
}
