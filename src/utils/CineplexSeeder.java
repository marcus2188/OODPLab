//package utils;
//
//import entity.Cinema;
//import entity.Cineplex;
//import entity.Seat;
//
//import java.util.ArrayList;
//
//public class CineplexSeeder {
//    private static ArrayList cineplexlist;
//    public static void main(String[] args) {
//        //cineplexlist = (ArrayList) SerializeDB.readSerializedObject("cineplex3.dat");
//        //System.out.println(cineplexlist.size());
//        ArrayList seats = new ArrayList();
//        for (int i = 0; i < 20; i++) {
//            for (char a = 'A'; a < 'J'; a++) {
//                Seat newSeat = new Seat(a, i);
//                seats.add(newSeat);
//            }
//        }
//
//        ArrayList occupiedSeats = new ArrayList();
//        Seat seat1 = new Seat('A', 1);
//        Seat seat2 = new Seat('A', 2);
//        Seat seat3 = new Seat('A', 3);
//        occupiedSeats.add(seat1);
//        occupiedSeats.add(seat2);
//        occupiedSeats.add(seat3);
//
//        ArrayList cinemas = new ArrayList();
//        for (int i = 0; i < 3; i++) {
//            Cinema newCinema = new Cinema(seats, occupiedSeats, 5, Integer.toString(i+1), "00" + Integer.toString(i+1));
//            cinemas.add(newCinema);
//        }
//
//
//        ArrayList cineplexes = new ArrayList();
//        Cineplex newCineplex1 = new Cineplex(cinemas, "Golden Village");
//        cineplexes.add(newCineplex1);
//        Cineplex newCineplex2 = new Cineplex(cinemas, "Silver Village");
//        cineplexes.add(newCineplex2);
//        Cineplex newCineplex3 = new Cineplex(cinemas, "Bronze Village");
//        cineplexes.add(newCineplex3);
//
//        SerializeDB.writeSerializedObject("cineplex6.dat", cineplexes);
//
//
//    }
//}
