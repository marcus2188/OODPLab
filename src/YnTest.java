import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import entity.Movie;
import entity.MovieReview;
import utils.SerializeDB;

public class YnTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

        MovieReview r1 = new MovieReview("5.0 like our GPA", 5);
        MovieReview r2 = new MovieReview("Pull up ur socks man",4);
        ArrayList<MovieReview> r = new ArrayList<MovieReview>(Arrays.asList(r1,r2));
        SerializeDB.writeSerializedObject("MovieReview.dat", r);
        
        ArrayList<String> cast = new ArrayList<String>(Arrays.asList("Nigel","Yuan Neng", "Marcus", "Beng", "Jozua"));
        
        Movie movie1 = new Movie("Avenger", "Now Showing", (LocalDate) null, "World says hello to all", "Owen", cast, (float) 5.0, true, r, 9999999);
        movie1.printMovie();
        Movie movie2 = new Movie("Ironman", "Now Showing", (LocalDate) null, "World says hello to all", "Noel", cast, (float) 5.0, true, r, 9999999);
        movie2.printMovie();
        
        ArrayList<Movie> m = new ArrayList<Movie>(Arrays.asList(movie1,movie2));
        SerializeDB.writeSerializedObject("Movie2.dat", m);
        /*
    	ArrayList<Movie> m = (ArrayList<Movie>) SerializeDB.readSerializedObject("Movie.dat");
    	for (int i=0;i<m.size();i++) {
    		m.get(i).printMovie();
    	}
    	/*
    	Movie_mg_inf mgr = new Movie_mg_manager();
    	mgr.addMovieReview();
    	*/
	}

}
