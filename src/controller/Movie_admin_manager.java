package controller;

import java.util.List;

import entity.Movie;
import entity.MovieReview;
import entity.MovieScreening;

public class Movie_admin_manager extends Movie_both_manager implements Movie_admin_inf, MovieTop5_inf {
	
	public void createMovie(String title, String showingStatus, String synopsis, String director, List<String> cast, double avgRating, boolean isBlockBuster, List<MovieReview> review_list, int ticketSales, List<MovieScreening> ms) {
		Movie newm = new Movie(title, showingStatus, synopsis, director, cast, avgRating, isBlockBuster, review_list, ticketSales, ms);

		Movie[] m1 = this.getM();
		int length = m1.length;
		Movie[] m2 = new Movie[length+1];
		for (int i=0;i<length;i++) {
			m2[i] = m1[i];
		}
		m2[length] = newm;
		exportData(m2);
	}
	
	public void updateMovie(String movieName, int attr, Object value) {
		Movie u = this.findMovie(movieName);
		if (attr==1) u.setTitle((String) value);
		else if (attr==2) u.setShowingStatus((String) value);
		else if (attr==3) u.setSynopsis((String) value);
		else if (attr==4) u.setDirector((String) value);
		else if (attr==5) u.setCast((List<String>) value);
		else if (attr==6) u.setAvgRating((double) value);
		else if (attr==7) u.setBlockBuster((boolean) value);
		else if (attr==8) u.setReview_list((List<MovieReview>) value);
		else if (attr==9) u.setTicketSales((int) value);
		else {
			System.out.println("Attribute error. No updates done.");
		}
	}
	
	public void removeMovie(String movieName) {
		Movie r = this.findMovie(movieName);
		
		Movie[] m1 = this.getM();
		int length = m1.length;
		Movie[] m2 = new Movie[length-1];
		for (int i=0;i<length;i++) {
			if (!m1[i].equals(r)) m2[i] = m1[i];
		}
		exportData(m2);
	}

	
}
