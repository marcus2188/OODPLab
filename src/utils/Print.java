package utils;

import entity.Cineplex;
import entity.Movie;
import entity.MovieScreening;
import java.util.ArrayList;

public class Print {
    public static void printCineplexes(ArrayList<Cineplex> l){
        int i;
        for(i = 0; i < l.size(); i++){
            System.out.println((i+1) + ". " + l.get(i).getName());
        }
    }

    public static void printMovies(ArrayList<Movie> l){
        int i;
        for(i = 0; i < l.size(); i++){
            System.out.println((i+1) + ". " + l.get(i).getTitle());
        }
    }

    public static void printMovieScreenings(ArrayList<MovieScreening> l){
        int i;
        for(i = 0; i < l.size(); i++){
            System.out.println("====================");
            System.out.print((i+1) + ". ");
            l.get(i).printMovieScreening();
        }
    }
}