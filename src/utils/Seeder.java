package utils;

import entity.*;
import java.time.LocalDate;
import java.util.Date;
import java. sql. Timestamp;
import java.util.ArrayList;

public class Seeder {
    public static void main(String[] args) {
        // Initialize Data for Cineplex
        ArrayList<Cineplex> cineplexes = new ArrayList<Cineplex>();
        Cineplex newCineplex1 = new Cineplex("Golden Village", "GVS");
        cineplexes.add(newCineplex1);
        Cineplex newCineplex2 = new Cineplex("Silver Village", "SVS");
        cineplexes.add(newCineplex2);
        Cineplex newCineplex3 = new Cineplex("Bronze Village", "BVS");
        cineplexes.add(newCineplex3);

        SerializeDB.writeSerializedObject("cineplex.dat", cineplexes);

        // Initialize Data for Movie
        String title = "Joker";
        String showingStatus = "Now Showing";
        LocalDate endOfShowingDate = null;
        String synopsis = "Forever alone in a crowd, failed comedian Arthur Fleck seeks connection as he walks the streets of Gotham City. Arthur wears two masks -- the one he paints for his day job as a clown, and the guise he projects in a futile attempt to feel like he's part of the world around him. Isolated, bullied and disregarded by society, Fleck begins a slow descent into madness as he transforms into the criminal mastermind known as the Joker.";
        String director = "Todd Philips";
        ArrayList<String> cast = new ArrayList<String>();
        cast.add("Joaquin Phoenix");
        cast.add("Robert De Niro");
        cast.add("Zazie Beetz");
        cast.add("Frances Conroy");
        float avgRating = 4;
        boolean isBlockBuster = true;
        ArrayList<MovieReview> review_list = new ArrayList<MovieReview>();

        int ticketSales = 0;

        MovieReview mr = new MovieReview("A grim, shallow, distractingly derivative homage to 1970s movies at their grittiest, Joker continues the dubious darker-is-deeper tradition.", 4);
        review_list.add(mr);
        Movie m = new Movie(title, showingStatus, endOfShowingDate, synopsis, director, cast, avgRating, isBlockBuster, review_list, ticketSales);
        ArrayList<Movie> movies = new ArrayList<Movie>();
        movies.add(m);
        SerializeDB.writeSerializedObject("MovieReview.dat", review_list);
        SerializeDB.writeSerializedObject("Movie.dat", movies);

        // Initialize Data for MovieScreening
        Cinema cinema = cineplexes.get(0).getCinemas().get(0);
        Movie movie = movies.get(0);
        Date date= new Date();
        long time = date.getTime();
        Timestamp timeStamp = new Timestamp(time);
        ArrayList<MovieScreening> movieScreenings = new ArrayList<MovieScreening>();
        MovieScreening ms = new MovieScreening(cinema, movie, timeStamp);
        movieScreenings.add(ms);
        SerializeDB.writeSerializedObject("moviescreening.dat", movieScreenings);

        // Initialize Data for MovieGoer
        ArrayList<MovieGoer> people = new ArrayList<MovieGoer>();
        people.add(new MovieGoer("Adam", 91828282, "adam@outlook.com"));
        SerializeDB.writeSerializedObject("peoplenames.dat", people);

        // Initialize Data for Booking History
        ArrayList<MovieTicket> tickets = new ArrayList<MovieTicket>();
        AgeGroup ageGroup = AgeGroup.STUDENT;
        boolean weekday = true;
        boolean before6 = true;
        ScreeningFormat format = ScreeningFormat.REGULAR;
        int day = 3;
        MovieTicket ticket = new MovieTicket(ageGroup, weekday, before6,format, day, 0);
        ticket.setPriceBasedOnAttributes();
        ticket.setMovieScreening(movieScreenings.get(0));
        ticket.setSeat(movieScreenings.get(0).getCinema().getSeatList().get(0));
        tickets.add(ticket);
        SerializeDB.writeSerializedObject("paymentHistory.dat", tickets);
        SerializeDB.writeSerializedObject("bookingtickets.dat", tickets);
    }
}
