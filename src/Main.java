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
                    printAdminMenu();
                    break;
                case 2:
                    System.out.println("Loading admin page...");
                    printMovieGoerMenu();
                    break;
                default:
                    System.out.println("Invalid choice, try again...");
                    break;
            }
        }
    }

    private static void printAdminMenu() {
        System.out.println("Welcome, admin!");
    }

    private static void printMovieGoerMenu() {
        System.out.println("Welcome, movie goer!");
    }
}
