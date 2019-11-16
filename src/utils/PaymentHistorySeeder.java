package utils;

import entity.AgeGroup;
import entity.MovieScreening;
import entity.MovieTicket;
import entity.ScreeningFormat;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 A utility class to initialize data for paymentHistory.dat
 @author SS3_Group4
 @version 1.0
 @since 2019-11-15
*/


public class PaymentHistorySeeder {
    public static void main(String[] args) {
       //  MovieScreening screening = new MovieScreening()
    MovieTicket tix = new MovieTicket(AgeGroup.REGULAR,
            true,
            true,
            ScreeningFormat.REGULAR,
            6,
            10,
            true
            );
        ArrayList history = new ArrayList();
        history.add(tix);
        SerializeDB.writeSerializedObject("paymentHistory.dat", history);
    }

}
