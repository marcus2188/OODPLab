package utils;

import java.util.Scanner;

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
                continue;
            }
        }
    }
}