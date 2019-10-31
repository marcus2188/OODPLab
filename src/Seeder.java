import entity.Cinema;
import entity.Cineplex;
import entity.Seat;
import utils.SerializeDB;

import java.util.ArrayList;
import java.util.List;

/* A seeder script to seed dat files for easy use */
public class Seeder {
    public static void main(String[] args) {
        // create seats list from A1 to Z20
        List seats = new ArrayList();
        for (int i = 1; i <= 20; i++) {
            for (char c = 'A'; c < 'Z'; c++) {
                Seat s1 = new Seat(c,i);
                seats.add(s1);
            }
        }

        // create 3 cinemas
        Cinema cine1 = new Cinema((ArrayList) seats, 'A', "Golden Cinema");
        Cinema cine2 = new Cinema((ArrayList) seats, 'B', "Silver Cinema");
        Cinema cine3 = new Cinema((ArrayList) seats, 'C', "Bronze Cinema");

        List cinemas = new ArrayList();
        cinemas.add(cine1);
        cinemas.add(cine2);
        cinemas.add(cine3);

        Cineplex c1 = new Cineplex((ArrayList)cinemas);

        ArrayList<Cineplex> list1 = new ArrayList();
        list1 = (ArrayList) SerializeDB.readSerializedObject("cineplex.dat");
        for (int i = 0; i < list1.size(); i++) {
            Cineplex c2 = (Cineplex)list1.get(i);
            for (int j = 0; j < c2.getCinemas().size(); j++) {
                System.out.println(c2.getCinemas().get(j).getName());
                c2.getCinemas().get(j).printSeats();
            }
        }

//        list1.add(c1);
//        SerializeDB.writeSerializedObject("cineplex.dat", list1 );


    }
}
