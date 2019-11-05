package controller;

import entity.Movie;
import utils.SerializeDB;

import java.util.ArrayList;

public class Movie_both_manager {
	
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

	public void setM(ArrayList m) {
		this.m = m;
	}
	
	public ArrayList loadData() {
		// Movie[] m = null;
		ArrayList movies = (ArrayList)SerializeDB.readSerializedObject("Movie.dat");
		return movies;
	}
	
	public void exportData(ArrayList m) {
		//save movie?
		SerializeDB.writeSerializedObject("Movie.dat", m);
	}
	
	public Movie findMovie(String movieName) {	//helper method for use movie managers.
		for (int i=0;i<m.size();i++) {
			if (m.get(i).getTitle().equalsIgnoreCase(movieName)) {
				return m.get(i);
			}
		}
		System.out.println("Movie not found.");
		return null;
	}

}
