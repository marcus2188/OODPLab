package controller;

import entity.Movie;
import utils.SerializeDB;
import utils.ScannerErrorHandler;

import java.util.ArrayList;
import java.util.List;

/**
 A manager to handle requst to list top 5 movies.
 @author SS3_Group4
 @version 1.0
 @since 2019-11-15
*/

public class Movie_both_manager {
	
	/** 
	* The array list of movies from Movie.dat
	*/
	private ArrayList<Movie> m;

	// constructor
	/** 
	* Creates the movie both manager and load data
	*/
	public Movie_both_manager() {
		this.m = this.loadData();
	}
	
	/** 
	* List top 5 movies
	*/
	public void listTop5() {
		ScannerErrorHandler sc = new ScannerErrorHandler();
		
		System.out.println("Press 1 for top 5 movies ranked by ticket sales.");
        System.out.println("Press 2 for top 5 movies ranked by ratings");
        int choice = sc.nextInt();
        while (choice<1 || choice>2) {
            System.out.println("Invalid choice, please try again: ");
            choice = sc.nextInt();
        }
        
		Movie tmp;
		if (choice==1) {	
			//sort by ticketSales
			ArrayList<Movie> tmplist = new ArrayList<Movie>(this.getM().size());
			for (Movie x:this.getM()) {
				tmplist.add(x);
			}
			System.out.println("Top 5 Movies by ticket sales: ");
			if (tmplist.size()==0) {
				System.out.println("No movies in the database have a ticket sale.\n");
				return;
			}
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
				if(i < 0)
					break;
				if (i<0) break;
				System.out.println(tmplist.get(i).getTitle() + ", " + tmplist.get(i).getTicketSales());
			}
			System.out.println();
		}
		else if (choice==2) {
			//sort by avgRating
			ArrayList<Movie> tmplist = new ArrayList<Movie>();
			System.out.println("Top 5 Movies by average rating: ");
			
			//filter for nonNA ratings away first
			for (int i=0;i<m.size();i++) {
				if (m.get(i).getAvgRating()!=-1) {
					tmplist.add(m.get(i));
				}
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
			
			if (tmplist.size()==0) {
				System.out.println("No movies in the database have a rating.\n");
				return;
			}

			//print nonNA ratings
			for (int i=tmplist.size()-1;i>=tmplist.size()-5;i--) {
				if (i<0) break;
				System.out.print(tmplist.get(i).getTitle() + ", ");
				tmplist.get(i).printReviewRating();
				System.out.println();
			}
			System.out.println();
		}
	}
	
	/** 
	* Gets the array list of movies from Movie.dat
	* @return The array list of movies from Movie.dat
	*/
	public ArrayList<Movie> getM() {
		return m;
	}

	/** 
	* Change the array list of movies to save into Movie.dat
	* @param m The new array list of movies
	*/
	public void setM(ArrayList<Movie> m) {
		this.m = m;
	}
	
	/** 
	* Read data from Movie.dat
	* @return The array list of movie loaded
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<Movie> loadData() {
		List movies;
		movies = SerializeDB.readSerializedObject("Movie.dat");
		return (ArrayList<Movie>) movies;
	}
	
	/** 
	* Write data to Movie.dat
	* @param m The data to be written in
	*/
	public void exportData(ArrayList<Movie> m) {
		//save movie data
		this.setM(m); //setM so that can update the ArrayList m without reimporting.
		SerializeDB.writeSerializedObject("Movie.dat", m);
	}
	
	/** 
	* Print the list of movies
	*/
	public void printMovieList() {
		for (int i=0;i<m.size();i++) {
			System.out.println((i+1)+ ". "+m.get(i).getTitle() );
		}
		System.out.println();
	}
}
