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
import entity.MovieScreening;

import java.util.Scanner;
public class marcusTest {
	public static void main(String[] args) throws IOException, ParseException {
		
		MovieScreening mj = new MovieScreening("Golden Village", "009", "Transformers 4", "20170712", 1855);
		Scanner sc = new Scanner(System.in);
		ShoppingOrder_manager ms = new ShoppingOrder_manager();
		System.out.println("WELCOME TO THE BOOKING MENU");
		System.out.println("-------------------------------------");
		System.out.println("1. book ticket");
		System.out.println("2. view current shopping cart");  // TESTED WORKING
		System.out.println("3. make your purchase");
		System.out.println("4. see purchase history"); // TESTED WORKING
		System.out.println("5. see all past purchasers");      // TESTED WORKING 
		System.out.println("6. clear current shopping cart");   // TESTED WORKING
		System.out.println("7. quit now");    
		System.out.println("-------------------------------------");
		System.out.println("What do you wanna do? : ");
		
		
		int choice = sc.nextInt();
		while(choice != 7) {
		switch(choice) {
			case 1: ms.bookTicket(mj, 'R', 8); break;
			case 2: ms.viewcurrentSO(); break;
			case 3: ms.makePurchase(); break;
			case 4: ms.viewallpaidtix(); break;
			case 5: ms.seepeople(); break;
			case 6: ms.dumpcurrentSO(); break;
			case 7: System.exit(0); 
		}
		System.out.println("");
		System.out.println("");
		System.out.println("-------------------------------------");
		System.out.println("1. book ticket");
		System.out.println("2. view current shopping cart");
		System.out.println("3. make your purchase");
		System.out.println("4. see purchase history"); // TESTED WORKING
		System.out.println("5. see all past purchasers");      // TESTED WORKING
		System.out.println("6. clear current shopping cart");   
		System.out.println("7. quit now");   
		System.out.println("-------------------------------------");
		System.out.println("What do you wanna do? : ");
		choice = sc.nextInt();
		}
		sc.close(); 
		
		
		/*      // TO SEED THE paymentHistory.dat file
		ArrayList<MovieTicket> PaymentHist = new ArrayList<MovieTicket>(); 
		MovieTicket mt = new MovieTicket(AgeGroup.REGULAR, false, true, ScreeningFormat.BLOCKBUSTER, 3, (float)9.00);
		MovieScreening mj = new MovieScreening("Golden Village", "019", "Godzilla", "20160716", 1155);
		mt.setMovieScreening(mj);
		mt.setTID("20190617019");
		mt.setSeat("D4");
		PaymentHist.add(mt);
		SerializeDB.writeSerializedObject("paymentHistory.dat", PaymentHist);
		System.out.println("hi");
		*/
		
		/*             // TO SEED the peoplenames.dat
		ArrayList<MovieGoer> people = new ArrayList<MovieGoer>(); 
		MovieGoer mr = new MovieGoer("Douglas Sin", "93758563", "douglas86@hotmail.com");
		people.add(mr);
		SerializeDB.writeSerializedObject("peoplenames.dat", people);
		System.out.println("hi");
		*/
    }
}
