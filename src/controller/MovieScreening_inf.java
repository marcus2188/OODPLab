package controller;

import entity.MovieScreening;
import java.util.ArrayList;

public interface MovieScreening_inf {
     void importData();
     void printScreeningList();
     ArrayList<MovieScreening> viewAllListing();
     void createMovieScreening();
     void updateMovieScreening();
     void deleteMovieScreening();
     // void updateHoliday();
}
