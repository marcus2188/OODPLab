package utils;

import entity.Cineplex;
import entity.Movie;
import entity.MovieScreening;
import java.util.ArrayList;

/**
 A utility class to print an arraylist
 @author SS3_Group4
 @version 1.0
 @since 2019-11-15
*/

public class Print {
    /** Print an array list of cineplex 
    * @param l The cineplex chosen
    */
    public static void printCineplexes(ArrayList<Cineplex> l){
        int i;
        for(i = 0; i < l.size(); i++){
            System.out.println((i+1) + ". " + l.get(i).getName());
        }
    }

    /** Print an array list of movie
    * @param l The movie chosen
    */
    public static void printMovies(ArrayList<Movie> l){
        int i;
        for(i = 0; i < l.size(); i++){
            System.out.println((i+1) + ". " + l.get(i).getTitle());
        }
    }

    /** Print an array list of movie screening
    * @param l The movie screening chosen
    */
    public static void printMovieScreenings(ArrayList<MovieScreening> l){
        int i;
        for(i = 0; i < l.size(); i++){
            System.out.print("["+(i+1)+"]");
            l.get(i).printMovieScreening();
        }
    }
}