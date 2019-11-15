 package controller;

import entity.*;
import utils.ScannerErrorHandler;
import utils.TextTicketDB;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MovieTicketManager implements MovieTicket_inf {
    ArrayList priceTable;

    // constructor
    public MovieTicketManager() throws IOException {
        this.importData();
    }

    // updates the entries in the current price table
    public void updatePriceTable() throws IOException {
        // System.out.println("===Update ticket prices===");
        System.out.println("Enter details of ticket you want to edit:");

        // age group to edit
        AgeGroup ageGroup;
        System.out.println("Which age group do you want to edit?");
        System.out.println("1. Senior citizen");
        System.out.println("2. Student");
        System.out.println("3. Regular");
        // Scanner scan = new Scanner(System.in);
        ScannerErrorHandler scan = new ScannerErrorHandler();
        int choice = scan.nextInt();
        while( 0 > choice || choice  > 3) {
            System.out.println("Invalid choice, try again");
            choice = scan.nextInt();
        }
        switch(choice) {
            case 1:
                ageGroup = AgeGroup.SENIORCITIZEN;
                break;
            case 2:
                ageGroup = AgeGroup.STUDENT;
                break;
            case 3:
                ageGroup = AgeGroup.REGULAR;
                break;
            default:
                ageGroup = AgeGroup.REGULAR;
                System.out.println("Invalid choice");
                break;
        }

        // day to edit
        System.out.println("Enter day of ticket (1-Mon...7-Sun)");
        choice = scan.nextInt();
        while(choice < 0 || choice > 7) {
            System.out.println("Invalid choice");
            choice = scan.nextInt();
        }
        int day = choice;
        boolean weekday;
        if (1 <= choice && choice <=5) {
            weekday = true;
        } else {
            weekday = false;
        }

        // before 6
        System.out.println("Choice time of ticket:");
        System.out.println("1. Before 6");
        System.out.println("2. After 6");
        choice = scan.nextInt();
        boolean before6;
        if (choice == 1) {
            before6 = true;
        } else {
            before6 = false;
        }

        // screen format
        ScreeningFormat screeningFormat;
        System.out.println("Choose the screening format");
        System.out.println("1. 3D");
        System.out.println("2. Regular");
        System.out.println("3. Blockbuster");
        choice = scan.nextInt();
        while(0 > choice || choice > 3) {
            System.out.println("Invalid choice");
            choice = scan.nextInt();
        }
        switch (choice) {
            case 1:
                screeningFormat = ScreeningFormat.THREEDIMENSION;
                break;
            case 2:
                screeningFormat = ScreeningFormat.REGULAR;
                break;
            case 3:
                screeningFormat = ScreeningFormat.BLOCKBUSTER;
                break;
            default:
                screeningFormat = ScreeningFormat.REGULAR;
                break;
        }

        // check if ticket already exists
        boolean found = false;
        for (int i = 0; i < this.priceTable.size(); i++) {
            if (this.checkEqualTicket(ageGroup, weekday, before6, screeningFormat, day, i)) {
                found = true;
                System.out.println("What price do you want to set?");
                float price = (float)scan.nextFloat();
                PriceTableTicket ticket = (PriceTableTicket)this.priceTable.get(i);
                ticket.setPrice(price);
                priceTable.remove(i);
                priceTable.add(ticket);
                TextTicketDB.savePrices("prices.txt", this.priceTable);
            }
        }

        if (found == false) {
            System.out.println("Price not in database, would you like to create a new entry? (Y/n)");
            char choice2 = scan.next().charAt(0);
            System.out.println("What price do you want to set?");
            float price = scan.nextFloat();
            if (choice2 == 'y' || choice2 == 'Y') {
                MovieTicket newTicket = new MovieTicket(ageGroup, weekday, before6, screeningFormat, day, price, false);
                this.priceTable.add(newTicket);
                TextTicketDB.savePrices("prices.txt", this.priceTable);
                System.out.println("Ticket added!");
            } else {
                System.out.println("Operation cancelled");
            }
        }
        return;
    }

    public void printMenu() {
        System.out.println("Press 1 to list cineplexes");
    }

    public void importData() {
        this.priceTable = TextTicketDB.readPrices("prices.txt");
        return;
    }


    // takes in details and returns a movie ticket with appropriate price
    public MovieTicket checkPrice() throws ParseException {
        // check before 6
        boolean before6;
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        dateFormat.format(date);
        if (dateFormat.parse(dateFormat.format(date)).after(dateFormat.parse("18:00"))) {
            before6 = false;
        } else {
            before6 = true;
        }

        // check age group
        // control classes shouldnt access console?
        AgeGroup ageGroup;
        System.out.println("What is your age group?");
        System.out.println("1. Senior Citizen");
        System.out.println("2. Student");
        System.out.println("3. Regular");
        // Scanner scan = new Scanner(System.in);
        ScannerErrorHandler scan = new ScannerErrorHandler();
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                ageGroup = AgeGroup.SENIORCITIZEN;
            case 2:
                ageGroup = AgeGroup.STUDENT;
            case 3:
                ageGroup = AgeGroup.REGULAR;
            default:
                ageGroup = AgeGroup.REGULAR;
        }

        // check weekday
        boolean weekday;
        Calendar c1 = Calendar.getInstance();
        if ((c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) ||
                (c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)) {
            weekday = false;
        } else {
            weekday = true;
        }

        // check screening format
        ScreeningFormat screeningFormat;
        System.out.println("What screening format?");
        System.out.println("1. 3D");
        System.out.println("2. Regular");
        System.out.println("3. Blockbuster");
        choice = scan.nextInt();
        switch (choice) {
            case 1:
                screeningFormat = ScreeningFormat.THREEDIMENSION;
            case 2:
                screeningFormat = ScreeningFormat.REGULAR;
            case 3:
                screeningFormat = ScreeningFormat.BLOCKBUSTER;
            default:
                screeningFormat = ScreeningFormat.REGULAR;
        }

        System.out.println("Would you like to upgrade to platinum suite for $10? (y/n)");
        boolean isPlatinum;
        choice = scan.next().charAt(0);

        while (choice != 'Y'
                && choice != 'y'
                && choice != 'n'
                && choice != 'N') {
            System.out.println("Invalid choice, please try again.");
            choice = scan.next().charAt(0);
        }
        if (choice == 'y' || choice == 'Y') {
            isPlatinum = true;
        } else if (choice == 'n' || choice == 'N') {
            isPlatinum = false;
        } else {
            isPlatinum = false;
        }

        // set the day
        int day;
        day = (int) c1.get(Calendar.DAY_OF_WEEK);

        // check price
        float price = 0;
        for (int i = 0; i < this.priceTable.size(); i++) {
            if(this.checkEqualTicket(ageGroup, weekday, before6, screeningFormat, day, i) == true) {
                price = ((MovieTicket)this.priceTable.get(i)).getPrice();
            }
        }

        MovieTicket ticket = new MovieTicket(ageGroup, weekday, before6, screeningFormat, day, price, isPlatinum);
        return ticket;
    }

    private boolean checkEqualTicket(AgeGroup ageGroup, boolean weekday, boolean before6, ScreeningFormat screeningFormat, int day, int i) {
        PriceTableTicket ticket = (PriceTableTicket)this.priceTable.get(i);
        if (ticket.getAgeGroup() == ageGroup
        && ticket.isWeekday() == weekday
        && ticket.isBefore6() == before6
        && ticket.getFormat() == screeningFormat
        && ticket.getDay() == day) {
            return true;
        } else {
            return false;
        }
    }

    public void deleteData(){
        this.printPriceTable(); // print entire table first
        System.out.println("Enter ticket number to delete:");
        int choice;
        // Scanner scan = new Scanner(System.in);
        ScannerErrorHandler scan = new ScannerErrorHandler();
        choice = scan.nextInt();
        this.priceTable.remove(choice -1);
        System.out.println("Ticket price removed!");
        return;
    }

    public void printPriceTable(){
        System.out.println("===The current ticket table===");
        for (int i= 1; i <= this.priceTable.size(); i++ ) {
            PriceTableTicket ticket = (PriceTableTicket)this.priceTable.get(i);
            System.out.print(i + ". ");
            ticket.printTicketDetails();
        }
    }

    // TODO: implement
    // updates a price of an indivdual ticket
    public void updatePriceOfTicket(MovieTicket ticket) {
        return;
    }


}
