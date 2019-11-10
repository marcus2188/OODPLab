package utils;

import entity.Cineplex;
import entity.Movie;
import entity.MovieScreening;
import java.util.ArrayList;

public class Filter {
    public static ArrayList<MovieScreening> filterByMovie(ArrayList<MovieScreening> l, Movie m){
        ArrayList<MovieScreening> temp = new ArrayList<MovieScreening>();
        for(int i = 0; i < l.size(); i++){
            if(l.get(i).getMovie().getTitle().equals(m.getTitle())){
                temp.add(l.get(i));
            }
        }
        return temp;
    }

     public static ArrayList<MovieScreening> filterByCineplex(ArrayList<MovieScreening> l, Cineplex c){
        ArrayList<MovieScreening> temp = new ArrayList<MovieScreening>();
        for(int i = 0; i < l.size(); i++){
            if(l.get(i).getCineplex().getName().equals(c.getName())){
                temp.add(l.get(i));
            }
        }
        return temp;
    }
}