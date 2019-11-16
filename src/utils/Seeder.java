package utils;

import entity.*;
import java.time.LocalDate;
import java.util.Date;
import java. sql. Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 A utility class to initialize data
 @author SS3_Group4
 @version 1.0
 @since 2019-11-15
*/

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
        // Joker   
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

        ArrayList<String> cast = new ArrayList<String>(Arrays.asList("Nigel","Yuan Neng", "Marcus", "Beng", "Jozua"));
        
        // Avenger
        Movie movie1 = new Movie("Avenger", "Now Showing", (LocalDate) null, "Assemble!", "Oxley", cast, true, 9999999);
        movie1.printMovie();
        movie1.appendMovieReviewList("5.0 like our GPA", 5);
        movie1.appendMovieReviewList("Pull up ur socks man",4);       
       
        
        Movie movie2 = new Movie("Ironman", "Now Showing", (LocalDate) null, "And I.. am Iron Man", "Noel", cast, true, 1230);
        movie2.printMovie();
        movie2.appendMovieReviewList(
        		"Director Jon Favreau keeps the film light and bright; the special effects are impressively crafted, and the setup for another film is handled gently and well.", 
        		5);
        movie2.appendMovieReviewList(
        		"Iron man is a fun and enjoyable but a flawed film", 3);
        
        
        
        Movie movie3 = new Movie("Batman bin supaman", "End of Showing", (LocalDate) LocalDate.now(), "Because I'm Batman", "Bruce", cast, true, 999);
        movie3.printMovie();
        movie3.appendMovieReviewList(
        		"funny!", 4);
        movie3.appendMovieReviewList(
        		"Batman is a fun and enjoyable but a flawed film", 3);
        
        Movie movie4 = new Movie("Sonic", "End of Showing", (LocalDate) LocalDate.now(), "World says hello to all", "Owen Who", cast, true, 99329);
        movie4.printMovie();
        movie4.appendMovieReviewList(
        		"Animation was so bad!", 1);
        movie4.appendMovieReviewList(
        		"Ok movie", 3);
        
        
        Movie movie5 = new Movie("Powerpuff girls", "Preview", (LocalDate) null, "MOJO", "Dr E", cast, true, 12321);
        movie5.printMovie();
        movie5.appendMovieReviewList(
        		"used to think it was a girly, sweet show, ...untill I watched it.", 3);
        movie5.appendMovieReviewList(
        		"Do not listen to that review,the powerpuff girls were GREAT i loved them age 9-12 and i was never violent.", 5);
        
        Movie movie6 = new Movie("Frozen", "Now Showing", (LocalDate) null, "Elsa vs Anna", "Owen", cast, true, 0);
        movie6.printMovie();
        movie6.appendMovieReviewList(
        		"Make room for two more wonderful princesses in this perfect combination of the best Disney has to offer.",5);
        movie6.appendMovieReviewList(
        		"Totally for kids.",1);
        
        Movie movie7 = new Movie("Frozen 2", "Coming Soon", (LocalDate) null, "Elsa kills Anna", "Owen", cast, true, 0);
        movie7.printMovie();
        movie7.appendMovieReviewList(
        		"Make room for two more wonderful princesses in this perfect combination of the best Disney has to offer.",5);
        
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

        Cinema cinema1 = cineplexes.get(0).getCinemas().get(0);
        Date date1 = new Date();
        MovieScreening ms1 = new MovieScreening(cinema1, movie, date1, 1700);
        movieScreenings.add(ms1);

        Cinema cinema2 = cineplexes.get(0).getCinemas().get(2);
        Date date2 = new Date();
        MovieScreening ms2 = new MovieScreening(cinema2, movie1, date2, 1200);
        movieScreenings.add(ms2);

        Cinema cinema3 = cineplexes.get(1).getCinemas().get(1);
        Date date3 = parseDate("2019-11-09");
        MovieScreening ms3 = new MovieScreening(cinema3, movie5, date3, 1130);
        movieScreenings.add(ms3);

        Cinema cinema4 = cineplexes.get(2).getCinemas().get(2);
        Date date4 = new Date();
        MovieScreening ms4 = new MovieScreening(cinema4, movie5, date4, 2030);
        movieScreenings.add(ms4);

        Cinema cinema7 = cineplexes.get(2).getCinemas().get(1);
        MovieScreening ms7 = new MovieScreening(cinema7, movie5, date4, 2030);
        movieScreenings.add(ms7);

        Cinema cinema5 = cineplexes.get(1).getCinemas().get(1);
        Date date5 = new Date();
        MovieScreening ms5 = new MovieScreening(cinema5, movie5, date5, 1330);
        movieScreenings.add(ms5);

        Cinema cinema6 = cineplexes.get(0).getCinemas().get(1);
        Date date6 = new Date();
        MovieScreening ms6 = new MovieScreening(cinema6, movie6, date6, 1330);
        movieScreenings.add(ms6);

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
        MovieTicket ticket = new MovieTicket(ageGroup, weekday, before6,format, day, 0, false);
        ticket.setPriceBasedOnAttributes();
        ticket.setMovieScreening(movieScreenings.get(0));
        ticket.setSeat(movieScreenings.get(0).getCinema().getSeatList().get(0));
        ticket.setTID("GVS1202011151200");
        tickets.add(ticket);
        SerializeDB.writeSerializedObject("paymentHistory.dat", tickets);
    }
    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (Exception e) {
            return null;
        }
     }
}
