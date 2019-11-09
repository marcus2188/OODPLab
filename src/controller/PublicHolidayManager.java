package controller;

import utils.ScannerErrorHandler;
import utils.SerializeDB;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class PublicHolidayManager implements PublicHoliday_inf {
    private ArrayList holidays;

    public PublicHolidayManager() {
        this.importData();
    }

    public void printMenu() {
        System.out.println("Current Public Holidays:");
        for (int i = 0; i < this.holidays.size(); i++) {
            Date holiday = (Date) this.holidays.get(i);
            System.out.println(holiday);
        }
    }

    public void addHoliday() {
        // Scanner scan = new Scanner(System.in);
        ScannerErrorHandler scan = new ScannerErrorHandler();
        System.out.println("Enter Day (DD): ");
        int day = scan.nextInt();
        while (day < 0 || day > 31) {
            System.out.println("Invalid choice, please try again: ");
            day = scan.nextInt();
        }
        System.out.println("Enter Month (MM): ");
        int month = scan.nextInt();
        System.out.println("Enter Year (YYYY): ");
        int year = scan.nextInt();
        Date newDate = new GregorianCalendar(year, month -1, day).getTime();

        this.holidays.add(newDate);

        SerializeDB.writeSerializedObject("dates.dat", this.holidays);
        System.out.println("Holiday Added!");
    }

    public void deleteHoliday() {
        // Scanner scan = new Scanner(System.in);
        ScannerErrorHandler scan = new ScannerErrorHandler();
        System.out.println("Enter date to remove");
        System.out.println("Enter Day (DD): ");
        int day = scan.nextInt();
        while (day < 0 || day > 31) {
            System.out.println("Invalid choice, please try again: ");
            day = scan.nextInt();
        }
        System.out.println("Enter Month (MM): ");
        int month = scan.nextInt();
        System.out.println("Enter Year (YYYY): ");
        int year = scan.nextInt();
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

    public boolean checkHoliday (LocalDate d) {
        Date convertedD = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
        for (int i = 0; i < this.holidays.size(); i++) {
            Date holiday = (Date) this.holidays.get(i);
            if (convertedD.compareTo(holiday) == 0) {
                return true;
            }
        }
        return false;
    }

    public void importData() {
        this.holidays = (ArrayList) SerializeDB.readSerializedObject("dates.dat");
    }

    public void exportData() {
        SerializeDB.writeSerializedObject("dates.dat", this.holidays);
    }
}
