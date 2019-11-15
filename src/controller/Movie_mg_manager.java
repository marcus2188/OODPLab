package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entity.Movie;
import entity.MovieReview;


public class Movie_mg_manager extends Movie_both_manager implements Movie_mg_inf {
	
	public void searchMovie() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter movie title: ");
		CharSequence movieName = sc.nextLine().toLowerCase();
		int returnCount = 0;
		ArrayList<Movie> m1 = this.getM();
		ArrayList<Movie> m2 = new ArrayList<Movie>();
		for (int i=0;i<m1.size();i++) {
			String mTitle = m1.get(i).getTitle();
			if (mTitle.toLowerCase().contains(movieName)) {
				returnCount++;
				m2.add(m1.get(i));
				System.out.println(returnCount+ ". " + mTitle);
			}
		}
		if (returnCount==0) {
			System.out.println("No movies found.");
			return;
		}
		else System.out.println(returnCount + " movies returned from search.");
		
		int choice, mchoice=0;
		do {
			System.out.println("Would you like to do anything else?");
			System.out.println("0. No, let's return\n1. See details for a movie. \n2. Add review for a movie. \n3. Print past reviews of a movie");
			choice = sc.nextInt();
			sc.nextLine();
			if (choice == 0) break;
			else if (choice==1||choice==2||choice==3){
				//choose movie d
				do {
					System.out.println("Select movie from the above list.");
					mchoice = sc.nextInt();
					sc.nextLine();
					if (mchoice<1||mchoice>returnCount) System.out.println("Invalid selection!\n");
					else break;
				} while (true);
				
				//get movie d
				Movie d = m2.get(mchoice-1);
				
				//do something to movie d
				actOnMovie(m1,d,choice);
				System.out.println();
			}
		} while (true);
		
		return;
	}
	
	public void listAllMovie() {
		if (this.getM().size()==0) {
			System.out.println("No movies are being shown now.");
			return;
		}
		this.printMovieList();
		Scanner sc = new Scanner(System.in);
		int choice, mchoice=0;
		do {
			//choose what to do
			System.out.println("Would you like to do anything else?");
			System.out.println("0. No, let's return\n1. See details for a movie. \n2. Add review for a movie. \n3. Print past reviews of a movie");
			choice = sc.nextInt();
			sc.nextLine();
			
			if (choice == 0) break;
			else if (choice==1||choice==2||choice==3){
				//get movielist and reprint movielist if list above is too far away
				ArrayList<Movie> m1 = this.getM();
				if (mchoice!=0) this.printMovieList();
				
				
				//choose movie d
				do {
					System.out.println("Select movie from the above list.");
					mchoice = sc.nextInt();
					sc.nextLine();
					int msize = m1.size();
					if (mchoice<1||mchoice>msize) System.out.println("Invalid selection!\n");
					else break;
				} while (true);
				
				//get movie d
				Movie d = m1.get(mchoice-1);
				
				//do something to movie d
				actOnMovie(m1,d,choice);
				System.out.println();
			}
			else System.out.println("Invalid selection!\n");
		} while (true);
		return;
	}
	
	public void actOnMovie(ArrayList<Movie> m1, Movie d, int choice) {
		if (choice == 1) {
			//viewMovieDetails();
			d.printMovie();
		}
		else if (choice == 2) {
			//addMovieReview();
			Scanner sc = new Scanner(System.in);
			int rating;
			System.out.println("Rate this movie: 1 2 3 4 5");
			do {
				rating = sc.nextInt();
				sc.nextLine();
			} while (rating<1 || rating>5);
			
			System.out.println("Comments: ");
			String comments = sc.nextLine();
			d.appendMovieReviewList(comments, rating);
			this.exportData(m1);
			System.out.println("Review successfully added.");
		}
		else if (choice == 3) {
			//printPastReviews();
			/*List<MovieReview> mrlist = d.getReview_list();
			int size = mrlist.size();
			for (int i=0;i<size;i++) {
				System.out.println(i);
				System.out.println("Rating: " + mrlist.get(i).getRating() + "\nComments: " + mrlist.get(i).getComments());
			}
			*/
			d.printPastReviews();			
		}
		return;
	}
}
