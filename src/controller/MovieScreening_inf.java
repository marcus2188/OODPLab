package controller;

import entity.MovieScreening;
import java.util.ArrayList;

/**
 An interface for movie screening manager
 @author SS3_Group4
 @version 1.0
 @since 2019-11-15
*/

public interface MovieScreening_inf {
     /** 
     Read data from Movie.dat, cineplex.dat, moviescreening.dat
     */
     void importData();
     /** 
     * Print a list of movie screening
     */
     void printScreeningList();
     /**
     * View all movie screening listing 
     */
     ArrayList<MovieScreening> viewAllListing();
     /** 
     * Create a movie screening
     */
     void createMovieScreening();
     /** 
     * Update a movie screening
     */
     void updateMovieScreening();
     /** 
     * Delete a movie screening
     */
     void deleteMovieScreening();
     // void updateHoliday();
}
