package controller;

import boundary.MovieGoer_UI;
import entity.Movie;
import utils.SerializeDB;

import java.util.ArrayList;

public class Movie_both_manager implements MovieGoer_UI {
	
	private ArrayList<Movie> m;

	// constructor
	public Movie_both_manager() {
		this.m = this.loadData();
	}
	
	public void listTop5(boolean ticketSales) {
		Movie tmp;
		if (ticketSales) {	
			//sort by ticketSales
			System.out.println("Top 5 Movies by ticket sales: ");
			for (int i=0;i<m.size();i++) {
				for (int j=i;j>0;j--) {
					if (m.get(i).getTicketSales()<m.get(j).getTicketSales()) {
						tmp = m.get(i);
						m.set(i, m.get(j));
						m.set(j, tmp);
					}
				}
			}
		}
		else {
			//sort by avgRating
			System.out.println("Top 5 Movies by average rating: ");
			for (int i=0;i<m.size();i++) {
				for (int j=i;j>0;j--) {
					if (m.get(i).getAvgRating()<m.get(j).getAvgRating()) {
						tmp = m.get(i);
						m.set(i, m.get(j));
						m.set(j, tmp);
					}
				}
			}
		}
		for (int i=0;i<m.size();i++) {
			System.out.println(m.get(i).getTitle());
		}
	}
	
	public ArrayList<Movie> getM() {
		return m;
	}

	public void setM(ArrayList<Movie> m) {
		this.m = m;
	}
	
	public ArrayList<Movie> loadData() {
		// Movie[] m = null;
		ArrayList<Movie> movies = (ArrayList<Movie>)SerializeDB.readSerializedObject("Movie.dat");
		return movies;
	}
	
	public void exportData(ArrayList<Movie> m) {
		//save movie data
		this.setM(m);
		SerializeDB.writeSerializedObject("Movie.dat", m);
	}
	
	public Movie findMovie(String movieName) {	//helper method for use movie managers.
		for (int i=0;i<m.size();i++) {
			if (m.get(i).getTitle().equalsIgnoreCase(movieName)) {
				return m.get(i);
			}
		}
		return null;
	}
	
	public void printMovieList() {
		for (int i=0;i<m.size();i++) {
			System.out.println((i+1)+ ". "+m.get(i).getTitle() );
		}
	}
	
	public ArrayList<Movie> getBookableMovies() {
		int size = this.getM().size();
		ArrayList<Movie> bookables = this.getM();
		for (int i=0;i<size;i++) {
			if (bookables.get(i).getShowingStatus().equals("End of Showing")) {
				bookables.remove(i);
			}
		}
		return bookables;
	}

}
