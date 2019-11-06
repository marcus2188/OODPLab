import entity.Cinema;
import entity.Cineplex;
import entity.Seat;
import utils.SerializeDB;

import java.util.ArrayList;

public class Tester {
    public static void main(String[] args) {
        Seat seat1 = new Seat('A', 1);
        Seat seat2 = new Seat('A', 2);
        ArrayList<Seat> seatlist = new ArrayList<Seat>();
        seatlist.add(seat1);
        seatlist.add(seat2);
        Cinema cinema = new Cinema(seatlist, (ArrayList)seatlist,'c', "name");
        ArrayList<Cinema> cinemas= new ArrayList<Cinema>();
        cinemas.add(cinema);
        Cineplex cineplex1 = new Cineplex(cinemas);
        cineplex1.printCinemas();

        ArrayList cineplexes = new ArrayList();
        cineplexes.add(cineplex1);
        SerializeDB.writeSerializedObject("cineplex.dat", cineplexes);
    }
}
