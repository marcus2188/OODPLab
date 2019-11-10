package controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import utils.SerializeDB;
import entity.Movie;
import entity.MovieReview;


public class Movie_mg_manager extends Movie_both_manager implements Movie_mg_inf {
	private ArrayList<MovieReview> movieReviews;
	private ArrayList<Movie> movies;

	public void searchMovie() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter movie title: ");
		String movieName = sc.nextLine();
		
		Movie s = this.findMovie(movieName);
		if (s!=null) s.printMovie();
		else System.out.println("Movie not found!");
	}
	
	public void listAllMovie() {
		this.printMovieList();
	}
	
	public void viewMovieDetails() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Choose movie: ");
		this.printMovieList();
		int choice;
		do {
			choice = sc.nextInt();
			sc.nextLine();
		} while (choice<1 || choice>this.getM().size());
		Movie d = this.getM().get(choice-1);
		
		d.printMovie();
	}
	
	public void addMovieReview() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Choose movie: ");
		this.printMovieList();
		int choice;
		do {
			choice = sc.nextInt();
			sc.nextLine();
		} while (choice<1 || choice>this.getM().size());
		Movie d = this.getM().get(choice-1);
		
		int rating;
		System.out.println("Rate this movie: 1 2 3 4 5");
		do {
			rating = sc.nextInt();
			sc.nextLine();
		} while (rating<1 || rating>5);
		
		String comments = sc.nextLine();
		
		d.appendMovieReview(comments, rating);
		super.exportData(getM());
		System.out.println("Review successfully added.");
	}
	
	public void printPastReviews() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Choose movie: ");
		this.printMovieList();
		int choice;
		do {
			choice = sc.nextInt();
			sc.nextLine();
		} while (choice<1 || choice>this.getM().size());
		Movie d = this.getM().get(choice-1);
		
		List<MovieReview> mrlist = d.getReview_list();
		int size = mrlist.size();
		for (int i=0;i<size;i++) {
			System.out.println("Rating: " + mrlist.get(i).getRating() + "\nComments: " + mrlist.get(i).getComments());
		}
	}

	public Movie findMovie(String title){
		int i;
		int size = this.getM().size();
		for(i = 0; i < size; i++){
			if(this.getM().get(i).getTitle().equals(title)){
				return this.getM().get(i);
			}
		}
		return null;
	}
}
