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
		ArrayList<MovieGoer> people = new ArrayList<MovieGoer>();
		MovieGoer person = new MovieGoer("lol", 8.00, "lol@outlook.com");
		people.add(person);
		SerializeDB.writeSerializedObject("peoplenames.dat", people);
		System.out.println("hi");
    }
}
