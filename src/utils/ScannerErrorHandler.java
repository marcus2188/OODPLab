package utils;

import java.util.Scanner;

/**
 A utility class to handle scanner and its errors
 @author SS3_Group4
 @version 1.0
 @since 2019-11-15
*/

public class ScannerErrorHandler {

    /** 
    * Handle scanner next int
    * Only can scan in int
    * @return The next int
    */
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

    
    /** 
    * Handle scanner next line
    * Only can scan in line
    * @return The next line
    */
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

    
    /** 
    * Handle scanner next float
    * Only can scan in float
    * @return The next float
    */
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

    
    /** 
    * Handle scanner next double
    * Only can scan in double
    * @return The next double
    */
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

    
    /** 
    * Handle scanner next line
    * Only can scan in line
    * @return The next line
    */
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
}