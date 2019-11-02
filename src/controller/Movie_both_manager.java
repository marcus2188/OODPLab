package controller;

import entity.Movie;

public class Movie_both_manager implements MovieTop5_inf {
	
	private Movie[] m;
	
	public Movie_both_manager() {
		m=loadData();
	}
	
	public void listTop5(boolean ticketSales) {
		Movie tmp;
		if (ticketSales) {						//sort by ticketSales
			for (int i=0;i<m.length;i++) {
				for (int j=i;j>0;j--) {
					if (m[i].getTicketSales()>m[j].getTicketSales()) {
						tmp = m[i];
						m[i] = m[j];
						m[j] = tmp;
					}
				}
			}
		}
	}
	
	public Movie[] getM() {
		return m;
	}

	public void setM(Movie[] m) {
		this.m = m;
	}
	
	public Movie[] loadData() { //NEEDS IMPLEMENTATION, LOADS ALL MOVIE
		Movie[] m = null;
		return m;
	}
	
	public void exportData(Movie[] m) { //NEEDS IMPLEMENTATION, LOADS ALL MOVIE
		//read to file
	}
	
	public Movie findMovie(String movieName) {	//helper method for use movie managers.
		for (int i=0;i<m.length;i++) {
			if (m[i].getTitle().equalsIgnoreCase(movieName)) {
				return m[i];
			}
		}
		System.out.println("Movie not found.");
		return null;
	}

	
}
