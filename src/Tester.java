import entity.Cinema;
import entity.Cineplex;
import entity.Movie;
import entity.MovieReview;
import entity.MovieScreening;
import entity.Seat;
import utils.SerializeDB;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tester {
    public static void main(String[] args) {
        /*
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
        */
    	
        MovieReview r1 = new MovieReview("5.0 like our GPA", 5);
        MovieReview r2 = new MovieReview("Pull up ur socks man",4);
        ArrayList<MovieReview> r = new ArrayList<MovieReview>(Arrays.asList(r1,r2));
        SerializeDB.writeSerializedObject("MovieReview.dat", r);
        MovieScreening m1 = new MovieScreening();
        MovieScreening m2 = new MovieScreening();
        ArrayList<MovieScreening> ms = new ArrayList<MovieScreening>(Arrays.asList(m1,m2));
        
        ArrayList<String> cast = new ArrayList<String>(Arrays.asList("Nigel","Yuan Neng", "Marcus", "Beng", "Jozua"));
        
        Movie movie1 = new Movie("Hello world", "Now Showing", (LocalDate) null, "World says hello to all", "Owen", cast, (float) 5.0, true, r, 9999999);
        movie1.printMovie();
        Movie movie2 = new Movie("Hello world", "Now Showing", (LocalDate) null, "World says hello to all", "Noel", cast, (float) 5.0, true, r, 9999999);
        movie2.printMovie();
        
        ArrayList<Movie> m = new ArrayList<Movie>(Arrays.asList(movie1,movie2));
        SerializeDB.writeSerializedObject("Movie.dat", m);
        /*
    	ArrayList<Movie> m = (ArrayList<Movie>) SerializeDB.readSerializedObject("Movie.dat");
    	for (int i=0;i<m.size();i++) {
    		m.get(i).printMovie();
    	}
    	*/
    }
}
