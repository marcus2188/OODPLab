package controller;

import utils.ScannerErrorHandler;
import utils.SerializeDB;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 A manager to handle admin request for public holiday updates.
 @author SS3_Group4
 @version 1.0
 @since 2019-11-15
*/

public class PublicHolidayManager implements PublicHoliday_inf {
    /** 
    * An array list representing holidays
    */
    private ArrayList holidays;

    /** 
    * Creates a public holiday manager and import data
    */
    public PublicHolidayManager() {
        this.importData();
    }

    /** 
	* Print menu for public holiday updates
	*/
    public void printMenu() {
        System.out.println("Current Public Holidays:");
        for (int i = 0; i < this.holidays.size(); i++) {
            Date holiday = (Date) this.holidays.get(i);
            System.out.println(holiday);
        }
    }

    /** 
	* Add a public holiday
	* @param day The day chosen
	* @param month The month chosen
	* @param year The year chosen
	*/
    public void addHoliday(int day, int month, int year) {
        // Scanner scan = new Scanner(System.in);

        Date newDate = new GregorianCalendar(year, month -1, day).getTime();

        this.holidays.add(newDate);

        SerializeDB.writeSerializedObject("dates.dat", this.holidays);
        System.out.println("Holiday Added!");
    }

    /** 
	* Delete a public holiday
	* @param day The day chosen
	* @param month The month chosen
	* @param year The year chosen
	*/
    public void deleteHoliday(int day, int month, int year) {
        // Scanner scan = new Scanner(System.in);

        Date newDate = new GregorianCalendar(year, month -1, day).getTime();

        for (int i= 0; i < this.holidays.size(); i++) {
            Date eachDate = (Date) this.holidays.get(i);
            if(eachDate.equals(newDate)) {
                this.holidays.remove(i);
                System.out.println("Successfully removed: " + newDate);
                this.exportData();
                return;
            }
        }
        System.out.println("No such date found, could not delete.");
        return;
    }

    /** 
	* Check a public holiday
	* @param d The date to check for
    * @return The determinant to see if a date is a holiday
	*/
    public boolean checkHoliday (Date d) {
        for (int i = 0; i < this.holidays.size(); i++) {
            Date holiday = (Date) this.holidays.get(i);
            if (d.compareTo(holiday) == 0) {
                return true;
            }
        }
        return false;
    }

    /** 
    * Read data from dates.dat
    */
    public void importData() {
        this.holidays = (ArrayList) SerializeDB.readSerializedObject("dates.dat");
    }

    /**
    * Write data to dates.dat 
    */
    public void exportData() {
        SerializeDB.writeSerializedObject("dates.dat", this.holidays);
    }
}
