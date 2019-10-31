import entity.Cinema;
import entity.Cineplex;
import entity.Seat;
import utils.SerializeDB;

import java.util.ArrayList;
import java.util.List;

/* A seeder script to seed dat files for easy use */
public class Seeder {
    public static void main(String[] args) {
        List list;
        Seat s1= new Seat('A', 1);
        Seat s2 = new Seat('A', 2);

        List seats = new ArrayList();
        seats.add(s1);
        seats.add(s2);

        Cinema cine1 = new Cinema((ArrayList)seats);

        List cinemas = new ArrayList();
        cinemas.add(cine1);

        Cineplex c1 = new Cineplex((ArrayList)cinemas);

        ArrayList<Cineplex> list1 = new ArrayList();
        list1 = (ArrayList) SerializeDB.readSerializedObject("cineplex.dat");
        for (int i = 0; i < list1.size(); i++) {
            Cineplex c2 = (Cineplex)list1.get(i);
            System.out.println(c2.getCinemas().get(0).getName());
        }

        list1.add(c1);
        SerializeDB.writeSerializedObject("cineplex.dat", list1 );


    }
}
