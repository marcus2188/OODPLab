package utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateSeeder {
    //public static void main(String[] arg) {
        // read dates
//        ArrayList dates = readDates();
//        for (int i = 0; i < dates.size(); i++) {
//            System.out.println(dates.get(i));
//        }

        // write dates
//        ArrayList dates = new ArrayList();
//        Date d1 = new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime();
//        Date d2 = new GregorianCalendar(2019, Calendar.FEBRUARY, 5).getTime();
//        Date d3 = new GregorianCalendar(2019, Calendar.FEBRUARY, 6).getTime();
//        Date d4 = new GregorianCalendar(2019, Calendar.APRIL, 19).getTime();
//        Date d5 = new GregorianCalendar(2019, Calendar.MAY, 1).getTime();
//        Date d6 = new GregorianCalendar(2019, Calendar.MAY, 19).getTime();
//        Date d7 = new GregorianCalendar(2019, Calendar.MAY, 20).getTime();
//        Date d8 = new GregorianCalendar(2019, Calendar.JUNE, 4).getTime();
//        Date d9 = new GregorianCalendar(2019, Calendar.JUNE, 5).getTime();
//        Date d10 = new GregorianCalendar(2019, Calendar.AUGUST, 9).getTime();
//        Date d11 = new GregorianCalendar(2019, Calendar.AUGUST, 11).getTime();
//        Date d12 = new GregorianCalendar(2019, Calendar.AUGUST, 12).getTime();
//        Date d13 = new GregorianCalendar(2019, Calendar.OCTOBER, 28).getTime();
//        Date d14 = new GregorianCalendar(2019, Calendar.DECEMBER, 25).getTime();
//        dates.add(d1);
//        dates.add(d2);
//        dates.add(d3);
//        dates.add(d4);
//        dates.add(d5);
//        dates.add(d6);
//        dates.add(d7);
//        dates.add(d8);
//        dates.add(d9);
//        dates.add(d10);
//        dates.add(d11);
//        dates.add(d12);
//        dates.add(d13);
//        dates.add(d14);
//
//         writeSerializedObject("dates.dat", dates);
//    }

    public static ArrayList readDates() {
        ArrayList dates = (ArrayList) SerializeDB.readSerializedObject("dates.dat");
        return dates;
    }

    public static void main(String[] args) {
        ArrayList dates = readDates();
        for (int i = 0; i < dates.size() ; i++) {
            System.out.println(dates.get(i));
        }
    }
}
