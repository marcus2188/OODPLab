import boundary.Admin_UI;
import controller.MovieTicketManager;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        welcomeMenu();
    }

    private static void welcomeMenu() {
        int choice = 0;
        System.out.println("Welcome to MOBLIMA App!");
        System.out.println("Press 1 for movie goer");
        System.out.println("Press 2 for admin");
        while(choice != 1 && choice != 2) {
            Scanner scan = new Scanner(System.in);
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Loading movie goer page...");
                    printMovieGoerMenu();

                    break;
                case 2:
                    System.out.println("Loading admin page...");

                    try {
                        printAdminMenu();
                    } catch(IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("Invalid choice, try again...");
                    break;
            }
        }
    }

    private static void printAdminMenu() throws IOException {
        int choice = 0;
        System.out.println("Welcome, admin!");
        System.out.println("Press 1 to update movie ticket prices");
        try {
            while (choice != 9) {
                Scanner scan = new Scanner(System.in);
                choice = scan.nextInt();
                switch (choice) {
                    case 1:
                        Admin_UI adminUI = new MovieTicketManager();
                        adminUI.importData();
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static void printMovieGoerMenu() {
        int choice = 0;
        System.out.println("Welcome, movie goer!");

    }
}
