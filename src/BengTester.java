

import controller.MovieScreeningManager;
import java.util.ArrayList;


public class BengTester {
    public static void main(String[] args) {
        ArrayList movieScreeningList = new ArrayList();
        //createscreening();
        retrieveScreening();
    }
    public static void createscreening(){
        System.out.println("=== Create Screening ===");
        MovieScreeningManager screeningManager = new MovieScreeningManager();
        screeningManager.createMovieScreening();

    }

    public static void retrieveScreening(){

        System.out.println("=== Retrieve Screening ===");
        MovieScreeningManager screeningManager = new MovieScreeningManager();
        screeningManager.printScreeningList();
    }
}
