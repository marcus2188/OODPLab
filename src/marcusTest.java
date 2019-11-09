import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import controller.ShoppingOrder_manager;
import entity.AgeGroup;
import entity.Cinema;
import entity.Cineplex;
import entity.MovieTicket;
import entity.ScreeningFormat;
import entity.Seat;
import entity.ShoppingOrder;
import utils.SerializeDB;
import entity.MovieGoer;
import java.util.Scanner;
public class marcusTest {
	public static void main(String[] args) throws IOException, ParseException {
		
		Scanner sc = new Scanner(System.in);
		ShoppingOrder_manager ms = new ShoppingOrder_manager();
		System.out.println("WELCOME TO THE BOOKING MENU");
		System.out.println("-------------------------------------");
		System.out.println("1. book ticket");
		System.out.println("2. view current shopping cart");
		System.out.println("3. make your purchase");
		System.out.println("4. see purchase history");
		System.out.println("5. see all past purchasers");      // TESTED WORKING
		System.out.println("6. clear current shopping cart");   
		System.out.println("-------------------------------------");
		System.out.println("What do you wanna do? : ");
		
		int choice = sc.nextInt();
		switch(choice) {
		case 1: ms.bookTicket(); break;
		case 2: ms.viewcurrentSO(); break;
		case 3: ms.makePurchase(); break;
		case 4: ms.viewallpaidtix(); break;
		case 5: ms.seepeople(); break;
		case 6: ms.dumpcurrentSO(); break;
		
		}
		sc.close();
    }
}
