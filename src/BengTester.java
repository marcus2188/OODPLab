

import controller.MovieScreeningManager;
import entity.Cineplex;
import utils.SerializeDB;

import java.util.ArrayList;


public class BengTester {
    public static void main(String[] args) {

        MovieScreeningManager screeningManager = new MovieScreeningManager();
        //screeningManager.printScreeningList();
        //screeningManager.printMgScreeninglist("Golden City Cineplexes");
        //screeningManager.createMovieScreening();
        //screeningManager.manualInsertData();
        //screeningManager.updateMovieScreening();
        //screeningManager.deleteMovieScreening();
        screeningManager.mgMovieprinting("Ironman");
        //screeningManager.printScreeningList();



    }




}
