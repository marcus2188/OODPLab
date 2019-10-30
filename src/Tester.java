import entity.Cinema;
import entity.Seat;

import java.util.ArrayList;

public class Tester {
    public static void main(String[] args) {
        Seat seat1 = new Seat('A', 1);
        Seat seat2 = new Seat('A', 2);
        ArrayList<Seat> seatlist = new ArrayList<Seat>();
        seatlist.add(seat1);
        seatlist.add(seat2);
        Cinema cinema = new Cinema(seatlist);
        cinema.printSeats();
    }
}
