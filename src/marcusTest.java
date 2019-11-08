import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import entity.AgeGroup;
import entity.Cinema;
import entity.Cineplex;
import entity.MovieTicket;
import entity.ScreeningFormat;
import entity.Seat;
import entity.ShoppingOrder;
import utils.SerializeDB;
import entity.MovieGoer;

public class marcusTest {
	public static void main(String[] args) {
		ArrayList<MovieTicket> PaymentHist = new ArrayList<MovieTicket>();
		MovieTicket mt = new MovieTicket(AgeGroup.REGULAR, false, false, ScreeningFormat.BLOCKBUSTER, 6, (float)10.00);
		PaymentHist.add(mt);
		SerializeDB.writeSerializedObject("paid.dat", PaymentHist);
		System.out.println("hi");
    }
}
