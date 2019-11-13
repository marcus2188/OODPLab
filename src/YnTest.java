import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import controller.MovieScreeningManager;
import controller.MovieScreening_inf;
import entity.Movie;
import entity.MovieReview;
import utils.SerializeDB;

public class YnTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

        MovieReview r1 = new MovieReview("5.0 like our GPA", 5);
        MovieReview r2 = new MovieReview("Pull up ur socks man",4);
        ArrayList<MovieReview> r = new ArrayList<MovieReview>(Arrays.asList(r1));
        SerializeDB.writeSerializedObject("MovieReview.dat", r);
        
        ArrayList<String> cast = new ArrayList<String>(Arrays.asList("Nigel","Yuan Neng", "Marcus", "Beng", "Jozua"));
        
        Movie movie1 = new Movie("Avenger", "Now Showing", (LocalDate) null, "World says hello to all", "Owen", cast, true, 9999999);
        movie1.printMovie();
        Movie movie2 = new Movie("Ironman", "Now Showing", (LocalDate) null, "World says hello to all", "Noel", cast, true, 9999999);
        movie2.printMovie();
        Movie movie3 = new Movie("Batman bin supaman", "End of Showing", (LocalDate) null, "World says hello to all", "Owen", cast, true, 9999999);
        movie3.printMovie();
        Movie movie4 = new Movie("Sonic", "End of Showing", (LocalDate) null, "World says hello to all", "Owen", cast, true, 9999999);
        movie4.printMovie();
        Movie movie5 = new Movie("Powerpuff girls", "Preview", (LocalDate) null, "World says hello to all", "Owen", cast, true, 9999999);
        movie5.printMovie();
        Movie movie6 = new Movie("Frozen", "Coming Soon", (LocalDate) null, "Elsa vs Anna", "Owen", cast, true, 9999999);
        movie6.printMovie();
        Movie movie7 = new Movie("Frozen 2", "Coming Soon", (LocalDate) null, "Elsa kills Anna", "Owen", cast, true, 9999999);
        movie7.printMovie();
        
        ArrayList<Movie> m = new ArrayList<Movie>(Arrays.asList(movie1,movie2));
        SerializeDB.writeSerializedObject("Movie.dat", m);
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
