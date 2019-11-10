package utils;

import java.util.Scanner;
import java.sql.Timestamp;  

public class ScannerErrorHandler {
    
    public int nextInt(){
        int i;
        Scanner scan = new Scanner(System.in);
        while(true){
            try{
                i = scan.nextInt();
                return i;
            }catch(java.util.InputMismatchException e){
                System.out.println("Please enter an integer!");
                scan.next();
                continue;
            }
        }
    }

    public String next(){
        String i;
        Scanner scan = new Scanner(System.in);
        while(true){
            try{
                i = scan.next();
                return i;
            }catch(java.util.InputMismatchException e){
                System.out.println("Please enter a string!");
                scan.next();
                continue;
            }
        }
    }

    public Float nextFloat() {
        Float f;
        Scanner scan = new Scanner(System.in);
        while(true) {
            try {
                f = scan.nextFloat();
                return f;
            } catch(java.util.InputMismatchException e) {
                System.out.println("Please enter a float!");
                scan.next();
                continue;
            }
        }
    }

    public Double nextDouble() {
        Double d;
        Scanner scan = new Scanner(System.in);
        while(true) {
            try {
                d = scan.nextDouble();
                return d;
            } catch ( java.util.InputMismatchException e) {
                System.out.println("Please enter a double!");
                scan.next();
                continue;
            }
        }
    }

    public String nextLine() {
        String s;
        Scanner scan = new Scanner(System.in);
        while (true) {
            try {
                s = scan.nextLine();
                return s;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Please enter a string");
                scan.next();
                continue;
            }
        }
    }

    public Timestamp nextTime(){
        String s;
        Scanner scan = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Please enter a showtime in this format (yyyy-mm-dd hh:mm:ss):");
                s = scan.nextLine();
                return Timestamp.valueOf(s);
            } catch (java.util.InputMismatchException e) {
                System.out.println("Please enter a string");
                scan.next();
                continue;
            } catch (java.lang.IllegalArgumentException e){
                System.out.println("Please enter the correct format");
                scan.next();
                continue;
            }
        }
    }
}