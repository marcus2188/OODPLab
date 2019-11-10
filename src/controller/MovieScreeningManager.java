package controller;

import entity.Cinema;
import entity.Cineplex;
import entity.MovieScreening;
import utils.ScannerErrorHandler;
import utils.SerializeDB;
import entity.Movie;
import utils.Print;
import utils.Filter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;

public class MovieScreeningManager implements MovieScreening_inf {
    private ArrayList<MovieScreening> movieScreeningList;
    private ArrayList<Cineplex> cineplexes;
    private ArrayList<Movie> movies;

    public MovieScreeningManager(){
        this.importData();
    }

    public void createMovieScreening() {
        ScannerErrorHandler sc = new ScannerErrorHandler();
        Cineplex cineplex;
        Cinema cinema;
        Movie movie;
        Timestamp time;
		
		System.out.println("Choose a cineplex: ");
		Print.printCineplexes(cineplexes);
		int choice;
		do {
			choice = sc.nextInt();
		} while (choice<1 || choice>cineplexes.size());
		cineplex = cineplexes.get(choice-1);
		
        System.out.println("Choose a cinema: ");
		cineplex.printCinemas();
		do {
			choice = sc.nextInt();
		} while (choice<1 || choice>cineplex.getCinemas().size());
		cinema = cineplex.getCinemas().get(choice-1);

        System.out.println("Choose a movie: ");
		Print.printMovies(movies);
		do {
			choice = sc.nextInt();
		} while (choice<1 || choice>movies.size());
		movie = movies.get(choice-1);

        time = sc.nextTime();

        MovieScreening newScreening = new MovieScreening(cinema, movie, time);
        this.movieScreeningList.add(newScreening);
        SerializeDB.writeSerializedObject("moviescreening.dat", this.movieScreeningList);
        System.out.println("Movie Screening Added!");
    }


    public void printScreeningList() {
        int choice, choice2;
        Movie movie;
        Cineplex cineplex;
        ScannerErrorHandler sc = new ScannerErrorHandler();

        System.out.println("Press 1 to list movie screening by movie.");
        System.out.println("Press 2 to list movie screening by cineplex.");
        choice = sc.nextInt();
        switch(choice){
            case 1:
                System.out.println("Choose a movie: ");
                Print.printMovies(movies);
                do {
                    choice2 = sc.nextInt();
                } while (choice2<1 || choice2>movies.size());
                movie = movies.get(choice2-1);
                Print.printMovieScreenings(Filter.filterByMovie(movieScreeningList, movie));
                break;
            case 2:
                System.out.println("Choose a cineplex: ");
                Print.printCineplexes(cineplexes);
                do {
                    choice2 = sc.nextInt();
                } while (choice2<1 || choice2>cineplexes.size());
                cineplex = cineplexes.get(choice2-1);
                Print.printMovieScreenings(Filter.filterByCineplex(movieScreeningList, cineplex));
                break;
            default:
                System.out.println("Invalid value!");
                break;
        } 

    }

   public void updateMovieScreening() {
        int choice, choice2;
        MovieScreening movieScreening;
        Cinema cinema;
        Cineplex cineplex;
        ScannerErrorHandler sc = new ScannerErrorHandler();

        System.out.println("Update movie screening");
        Print.printMovieScreenings(movieScreeningList);
        System.out.println("Select the movie screening you want to update:");
        do {
            choice = sc.nextInt();
        } while (choice<1 || choice>movieScreeningList.size());
        movieScreening = movieScreeningList.get(choice);

        System.out.println("Press 1 to change Cineplex:");
        System.out.println("Press 2 to change Cinema:");
        System.out.println("Press 3 to change screen time:");
        choice = sc.nextInt();
        switch(choice){
            case 1:
                System.out.println("Choose a cineplex: ");
                Print.printCineplexes(cineplexes);
                do {
                    choice2 = sc.nextInt();
                } while (choice2<1 || choice2>cineplexes.size());
                cineplex = cineplexes.get(choice2-1);
                
                System.out.println("Choose a cinema: ");
                cineplex.printCinemas();
                do {
                    choice2 = sc.nextInt();
                } while (choice2<1 || choice2>cineplex.getCinemas().size());
                cinema = cineplex.getCinemas().get(choice2-1);

                movieScreening.setCineplex(cinema);
                break;

            case 2:
                cineplex = movieScreening.getCineplex();
                System.out.println("Choose a cinema: ");
                cineplex.printCinemas();
                do {
                    choice2 = sc.nextInt();
                } while (choice2<1 || choice2>cineplex.getCinemas().size());
                cinema = cineplex.getCinemas().get(choice2-1);

                movieScreening.setCinema(cinema);
                break;
            case 3:
                Timestamp time = sc.nextTime();
                movieScreening.setShowTime(time);
                break;
            default:
                System.out.println("Invalid value!");
                break;
        }

        SerializeDB.writeSerializedObject("moviescreening.dat", this.movieScreeningList);
        System.out.println("Movie Screening Updated!");
   }

   public void deleteMovieScreening() {
        int choice;
        ScannerErrorHandler sc = new ScannerErrorHandler();

        System.out.println("Delete movie screening");
        Print.printMovieScreenings(movieScreeningList);
        System.out.println("Select the movie screening you want to delete:");
        do {
            choice = sc.nextInt();
        } while (choice<1 || choice>movieScreeningList.size());
        movieScreeningList.remove(choice);
        SerializeDB.writeSerializedObject("moviescreening.dat", this.movieScreeningList);
        System.out.println("Movie Screening Removed!");
   }

    public void importData() {
        this.movieScreeningList = (ArrayList) SerializeDB.readSerializedObject("moviescreening.dat");
        this.cineplexes = (ArrayList) SerializeDB.readSerializedObject("cineplex.dat");
        this.movies = (ArrayList) SerializeDB.readSerializedObject("Movie.dat");
    }

}
