package controller;

import entity.Movie;
import utils.SerializeDB;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Movie_both_manager {
	
	private ArrayList<Movie> m;

	// constructor
	public Movie_both_manager() {
		this.m = this.loadData();
	}
	
	public void listTop5() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Press 1 for top 5 movies ranked by ticket sales.");
        System.out.println("Press 2 for top 5 movies ranked by ratings");
        int choice = sc.nextInt();
        sc.nextLine();
        while (choice<1 || choice>2) {
            System.out.println("Invalid choice, please try again: ");
            choice = sc.nextInt();
            sc.nextLine();
        }
        
		Movie tmp;
		if (choice==1) {	
			//sort by ticketSales
			ArrayList<Movie> tmplist = this.getM();
			System.out.println("Top 5 Movies by ticket sales: ");
			for (int i=1;i<tmplist.size();i++) {
				for (int j=i;j>0;j--) {
					if (tmplist.get(j).getTicketSales()<tmplist.get(j-1).getTicketSales()) {
						tmp = tmplist.get(j);
						tmplist.set(j, tmplist.get(j-1));
						tmplist.set(j-1, tmp);
					}
				}
			}
			for (int i=tmplist.size()-1; i>=tmplist.size()-5; i--) {
				System.out.println(tmplist.get(i).getTitle() + ", " + tmplist.get(i).getTicketSales());
			}
		}
		else if (choice==2) {
			//sort by avgRating
			ArrayList<Movie> tmplist = new ArrayList<Movie>();
			System.out.println("Top 5 Movies by average rating: ");
			
			//transfer NA ratings away first
			ArrayList<Movie> nr = new ArrayList<Movie>();
			for (int i=0;i<m.size();i++) {
				if (m.get(i).getAvgRating()==-1) nr.add(m.get(i));
				else tmplist.add(m.get(i));
			}
			
			//sort nonNA ratings
			for (int i=1;i<tmplist.size();i++) {
				for (int j=i;j>0;j--) {
					if (tmplist.get(j).getAvgRating()<tmplist.get(j-1).getAvgRating()) {
						tmp = tmplist.get(j);
						tmplist.set(j, tmplist.get(j-1));
						tmplist.set(j-1, tmp);
					}
				}
			}
			
			//print nonNA ratings
			for (int i=0;i<tmplist.size();i++) {
				System.out.print(tmplist.get(i).getTitle() + ", ");
				tmplist.get(i).printReviewRating();
				System.out.println();
			}
			
			//print NA ratings
			System.out.println("\nMovies with rating NA:");
			for (int i=0;i<nr.size();i++) {
				System.out.println(nr.get(i).getTitle());
			}
			System.out.println();
		}
	}
	
	public ArrayList<Movie> getM() {
		return m;
	}

	public void setM(ArrayList<Movie> m) {
		this.m = m;
	}
	
	public ArrayList<Movie> loadData() {
		List movies;
		movies = SerializeDB.readSerializedObject("Movie.dat");
		return (ArrayList<Movie>) movies;
	}
	
	public void exportData(ArrayList<Movie> m) {
		//save movie data
		this.setM(m); //setM so that can update the ArrayList m without reimporting.
		SerializeDB.writeSerializedObject("Movie.dat", m);
	}
	
	public void printMovieList() {
		for (int i=0;i<m.size();i++) {
			System.out.println((i+1)+ ". "+m.get(i).getTitle() );
		}
		System.out.println();
	}
}
