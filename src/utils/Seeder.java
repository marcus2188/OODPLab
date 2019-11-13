package utils;

import entity.*;
import java.time.LocalDate;
import java.util.Date;
import java. sql. Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

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
        ArrayList<String> cast2 = new ArrayList<String>();
        cast2.add("Joaquin Phoenix");
        cast2.add("Robert De Niro");
        cast2.add("Zazie Beetz");
        cast2.add("Frances Conroy");
        float avgRating = 4;
        boolean isBlockBuster = true;
        ArrayList<MovieReview> review_list = new ArrayList<MovieReview>();

        int ticketSales = 0;

        MovieReview mr = new MovieReview("A grim, shallow, distractingly derivative homage to 1970s movies at their grittiest, Joker continues the dubious darker-is-deeper tradition.", 4);
        review_list.add(mr);
        Movie m2 = new Movie(title, showingStatus, endOfShowingDate, synopsis, director, cast2, isBlockBuster, ticketSales);
        ArrayList<Movie> movies = new ArrayList<Movie>();
        movies.add(m2);
        
        MovieReview r1 = new MovieReview("5.0 like our GPA", 5);
        MovieReview r2 = new MovieReview("Pull up ur socks man",4);
        ArrayList<MovieReview> r = new ArrayList<MovieReview>(Arrays.asList(r1));
        SerializeDB.writeSerializedObject("MovieReview.dat", r);
        
        ArrayList<String> cast = new ArrayList<String>(Arrays.asList("Nigel","Yuan Neng", "Marcus", "Beng", "Jozua"));
        
        Movie movie1 = new Movie("Avenger", "Now Showing", (LocalDate) null, "World says hello to all", "Owen", cast, true, 9999999);
        movie1.printMovie();
        Movie movie2 = new Movie("Ironman", "Now Showing", (LocalDate) null, "World says hello to all", "Noel", cast, true, 9999999);
        movie2.printMovie();
        Movie movie3 = new Movie("Batman bin supaman", "End of Showing", (LocalDate) null, "World says hello to all", "Owen", cast, true, 9999999);
        movie3.printMovie();
        Movie movie4 = new Movie("Sonic", "End of Showing", (LocalDate) null, "World says hello to all", "Owen", cast, true, 9999999);
        movie4.printMovie();
        Movie movie5 = new Movie("Powerpuff girls", "Preview", (LocalDate) null, "World says hello to all", "Owen", cast, true, 9999999);
        movie5.printMovie();
        Movie movie6 = new Movie("Frozen", "Coming Soon", (LocalDate) null, "Elsa vs Anna", "Owen", cast, true, 9999999);
        movie6.printMovie();
        Movie movie7 = new Movie("Frozen 2", "Coming Soon", (LocalDate) null, "Elsa kills Anna", "Owen", cast, true, 9999999);
        movie7.printMovie();
        
        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);
        movies.add(movie5);
        movies.add(movie6);
        movies.add(movie7);
        
        SerializeDB.writeSerializedObject("MovieReview.dat", review_list);
        SerializeDB.writeSerializedObject("Movie.dat", movies);

        // Initialize Data for MovieScreening
        Cinema cinema = cineplexes.get(0).getCinemas().get(0);
        Movie movie = movies.get(0);
        Date date= new Date();
        long time = date.getTime();
        Timestamp timeStamp = new Timestamp(time);
        ArrayList<MovieScreening> movieScreenings = new ArrayList<MovieScreening>();
        MovieScreening ms = new MovieScreening(cinema, movie, date, 1400);
        movieScreenings.add(ms);
        SerializeDB.writeSerializedObject("moviescreening.dat", movieScreenings);

        // Initialize Data for MovieGoer
        ArrayList<MovieGoer> people = new ArrayList<MovieGoer>();
        people.add(new MovieGoer("Adam", "91828282", "adam@outlook.com"));
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
    }
}
