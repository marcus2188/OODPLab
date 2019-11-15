

import controller.MovieScreeningManager;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class BengTester {
    public static void main(String[] args) {
        String data = null;
        try {
            data = readFileAsString("src/data/moblima.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(data);


        MovieScreeningManager screeningManager = new MovieScreeningManager();
       // MovieScreeningManager screeningManager = new MovieScreeningManager();
        //screeningManager.printScreeningList();
        //screeningManager.updateMovieScreening();
        //screeningManager.deleteMovieScreening();
        //screeningManager.createMovieScreening();
        //screeningManager.mgMovieprinting("Joker");
        //screeningManager.viewAllListing();
    }

    public static String readFileAsString(String fileName)throws Exception
    {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }
}
