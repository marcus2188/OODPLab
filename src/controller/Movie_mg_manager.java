package controller;

import java.util.List;

import entity.Movie;
import entity.MovieReview;


public class Movie_mg_manager extends Movie_both_manager implements Movie_mg_inf, MovieTop5_inf{
	
	public void searchMovie(String movieName) {
		Movie s = this.findMovie(movieName);
		if (s!=null) System.out.println(s.getTitle());
	}
	
	public void listAllMovie() {
		for (int i=0;i<this.getM().size();i++) {
			this.getM().get(i).printMovie();
		}
	}
	
	public void viewMovieDetails(String movieName) {
		Movie d = this.findMovie(movieName);
		if (d!=null) d.printMovie();
	}
	
	public void addMovieReview(String movieName, String comments, int rating) {
		Movie a = this.findMovie(movieName);
		if (a!=null) {
			a.appendMovieReview(comments, rating);
			System.out.println("Review successfully added.");
		}
	}
	
	public void printPastReviews(String movieName) {
		Movie r = this.findMovie(movieName);
		List<MovieReview> mrlist = r.getReview_list();
		int size = mrlist.size();
		for (int i=0;i<size;i++) {
			System.out.println("Rating: " + mrlist.get(i).getRating() + "\nComments: " + mrlist.get(i).getComments());
		}
	}

	public void listTop5(Boolean byTicketSales) {
		System.out.println("=== List top 5 movies ===");
	}
}
