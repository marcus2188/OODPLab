package controller;

import entity.Movie;
import entity.MovieReview;
import entity.MovieScreening;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Movie_admin_manager extends Movie_both_manager implements Movie_admin_inf, MovieTop5_inf {

	public ArrayList importData() {
		System.out.println("Import data");
		ArrayList temp = new ArrayList();
		return temp;
	}
	public void createMovie() {
		int choice;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Movie title: ");
		String title = sc.next();
		
		//showing status + endOfShowingDate
		String showingStatus = "";
		LocalDate endOfShowingDate = null;
		choice=0;
		do {
			System.out.println("Showing Status: ");
			System.out.println("1. Coming Soon");
			System.out.println("2. Preview");
			System.out.println("3. Now Showing");
			System.out.println("4. End of Showing");
			choice = sc.nextInt();
			if (choice==1) {
				showingStatus = "Coming Soon";
				endOfShowingDate = null;
			}
			else if (choice==2) {
				showingStatus = "Preview";
				endOfShowingDate = null;
			}
			else if (choice==3) {
				showingStatus = "Now Showing";
				endOfShowingDate = null;
			}
			else if (choice==4) {
				showingStatus = "End of Showing";
				System.out.println("Input end of showing date: ");
				System.out.println("Year: ");
				int year = sc.nextInt();
				System.out.println("Month: ");
				int month = sc.nextInt();
				System.out.println("Day: ");
				int day = sc.nextInt();
				endOfShowingDate = LocalDate.of(year,month,day);
			}
			else System.out.println("Invalid choice!\n");
		} while (choice>=1 && choice<=4);

		
		System.out.println("Synopsis: ");
		String synopsis = sc.next();
		
		System.out.println("Director: ");
		String director = sc.next();
		
		//cast
		choice=0;
		System.out.println("Cast: ");
		List<String> cast = new ArrayList<String>();
		do {
			System.out.println("Menu to insert cast");
			System.out.println("1. Insert cast");
			System.out.println("2. Done");
			choice=sc.nextInt();
			if (choice==1) {
				System.out.println("Name of cast:");
				cast.add(sc.next());
			}
		} while (choice==1);
		
		//System.out.println("Average Rating: ");
		//default to -1
		double avgRating =-1;
		
		char charchoice;
		boolean isBlockBuster=false;
		int looper;
		do {
			looper=0;
			System.out.println("Is a blockbuster (Y/N): ");
			
			charchoice = sc.next().charAt(0);
			if (charchoice=='y' || charchoice=='Y') isBlockBuster = true;
			else if ((charchoice=='n')||(charchoice=='N')) isBlockBuster = false;
			else {
				System.out.println("Invalid choice!\n");
				looper++;
			}
		} while (looper!=0);
		
		//System.out.println("Review list: ");
		//default to empty list;
		List<MovieReview> review_list = new ArrayList<MovieReview>();
		if (review_list.size()==0) System.out.println("Empty review list");
		
		//System.out.println("Ticket sales: ");
		//deafult to 0;
		int ticketSales = 0;
		
		//System.out.println("Movie screenings: ");
		//default to empty list;
		List<MovieScreening> ms = new ArrayList<MovieScreening>();
		
		//make new movie using given and default parameters
		Movie newm = new Movie(title, showingStatus, endOfShowingDate, synopsis, director, cast, avgRating, isBlockBuster, review_list, ticketSales, ms);
		
		//add new movie to current movie list
		ArrayList<Movie> m1 = this.getM();
		m1.add(newm);
		
		//export to serialisable file
		exportData(m1);
	}
	
	public void updateMovie() {
		int choice=0;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Select the movie you want: ");
		this.printMovieList();
		
		do {
			choice = sc.nextInt();
			if (choice>=1 && choice<=this.getM().size()) {
				break;
			} else {
				System.out.println("Invalid choice!\n");
			}
		} while (true);
		
		String movieName = this.getM().get(choice-1).getTitle();
		Movie u = this.findMovie(movieName);
		
		int attr=0;
		do {
			System.out.println("What attribute would you like to update?");
			System.out.println("1. Title");
			System.out.println("2. Showing status");
			System.out.println("3. Synopsis");
			System.out.println("4. Director");
			System.out.println("5. Cast");
			System.out.println("6. Average rating");
			System.out.println("7. Blockbuster or not");
			System.out.println("8. Movie Review list");
			System.out.println("9. Ticket sales");
			attr = sc.nextInt();
		} while (attr>=1 && attr<=9);
		
		
		if (attr==1) {
			System.out.println("Current title: "+u.getTitle());
			System.out.println("Enter new title: ");
			String value = sc.next();
			u.setTitle(value);
		}
		else if (attr==2) {
			System.out.println("Current showing status: "+u.getShowingStatus());
			System.out.println("Enter new showing status: ");
			String value = sc.next();
			u.setShowingStatus(value);
			do {
				System.out.println("Showing Status: ");
				System.out.println("1. Coming Soon");
				System.out.println("2. Preview");
				System.out.println("3. Now Showing");
				System.out.println("4. End of Showing");
				choice = sc.nextInt();
				if (choice==1) value = "Coming Soon";
				else if (choice==2) value = "Preview";
				else if (choice==3) value = "Now Showing";
				else if (choice==4) {
					value = "End of Showing";
					
				}
				else System.out.println("Invalid choice!\n");
			} while (choice>=1 && choice<=3);
		}
		else if (attr==3) {
			System.out.println("Current synopsis: "+u.getSynopsis());
			System.out.println("Enter new synopsis: ");
			String value = sc.next();
			u.setSynopsis(value);
		}
		else if (attr==4) {
			System.out.println("Current director: "+u.getDirector());
			System.out.println("Enter new director: ");
			String value = sc.next();
			u.setDirector(value);
		}
		else if (attr==5) {
			System.out.println("What do you want to do? \n1. Add cast \n2. Update cast details \n3. Delete cast");
			do {
				choice = sc.nextInt();
				if (choice>=1 && choice<=3) break;
				else System.out.println("Invalid choice!\n");
			} while (true);
			
			
			if (choice == 1) {
				System.out.println("Current cast: ");
				u.printCast();
				//add cast
				List<String> value = new ArrayList<String>();
				String in;
				do {
					System.out.println("Enter new cast: (Input 0 to stop)");
					in = sc.next();
					if (in.equals('0')) break;
					value.add(in);
				} while (true);
				u.setCast(value);
			}
			else if (choice==2) {
				System.out.println("Current cast: ");
				u.printCast();
				//update cast
				int num=0;
				do {
					System.out.println("Enter cast number to update: (Input 0 to stop)");
					num = sc.nextInt();
					if (num==0) break;
					List<String> value = new ArrayList<String>();
					value = u.getCast();
					System.out.println("Enter new data for selected cast: ");
					String in = sc.next();
					value.set(num-1, in);
				} while (true);
			}
			else if (choice==3) {
				System.out.println("Current cast: ");
				u.printCast();
				//delete cast
				int num=0;
				do {
					System.out.println("Enter cast number to delete: (Input 0 to stop)");
					num = sc.nextInt();
					if (num==0) break;
					List<String> value = new ArrayList<String>();
					value = u.getCast();
					value.remove(num-1);
					u.setCast(value);
				} while (true);
			}	
		}
		else if (attr==6) {
			System.out.println("Current average rating: "+u.getAvgRating());
			u.setAvgRating(u.getReview_list());
			System.out.println("New average rating: "+u.getAvgRating());
		}
		else if (attr==7) {
			u.setBlockBuster(!u.isBlockBuster());
		}
		else if (attr==8) { 
			System.out.println("Current reviews: ");
			u.printPastReviews();
			//delete review
			int num=0;
			do {
				System.out.println("Enter review number to delete: (Input 0 to stop)");
				num = sc.nextInt();
				if (num==0) break;
				List<MovieReview> value = new ArrayList<MovieReview>();
				value = u.getReview_list();
				value.remove(num-1);
				u.setReview_list(value);
			} while (true);
		}
		else if (attr==9) {
			System.out.println("Current ticket sales: "+u.getTicketSales());
			int value = sc.nextInt();
			u.setTicketSales(value);
		}
		else {
			System.out.println("Attribute error. No updates done.");
		}
	}
	
	public void removeMovie() {
		Scanner sc = new Scanner(System.in);
		
		Movie r=null;
		do {
			System.out.println("Enter movie name to remove: ");
			String movieName= sc.next();
			r = this.findMovie(movieName);
			System.out.println("Movie not found. Please try again.\n");
		} while (r==null);	
		
		ArrayList<Movie> m1 = this.getM();
		int length = m1.size();
		for (int i=0;i<length;i++) {
			if (m1.get(i).equals(r)) m1.remove(i);
		}
		exportData(m1);
	}

	
}
