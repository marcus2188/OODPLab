package controller;

import entity.Movie;
import entity.MovieReview;
import entity.MovieScreening;
import utils.ScannerErrorHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entity.Movie;
import entity.MovieReview;

public class Movie_admin_manager extends Movie_both_manager implements Movie_admin_inf {

	public ArrayList importData() {
		System.out.println("Import data");
		ArrayList temp = new ArrayList();
		return temp;
	}
	public void createMovie() {
		int choice;
		// Scanner sc = new Scanner(System.in);
		ScannerErrorHandler sc = new ScannerErrorHandler();

		System.out.println("Movie title: ");
		String title = sc.nextLine();
		
		
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
			sc.nextLine();
			
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
				int day, month, year;
				do {
					System.out.println("Year: ");
					year = sc.nextInt();
					sc.nextLine();
					
					System.out.println("Month: ");
					month = sc.nextInt();
					sc.nextLine();
					if (month<1 || month>12) {
						System.out.println("Invalid month!\n");
						continue;
					}
					
					System.out.println("Day: ");
					day = sc.nextInt();
					sc.nextLine();
					if (day<1 || day>31) {
						System.out.println("Invalid day!\n");
						continue;
					}
					
					break;
				} while (true);
				
				endOfShowingDate = LocalDate.of(year,month,day);
			}
			else System.out.println("Invalid choice!\n");
		} while (choice<1 && choice>4);

		
		//synopsis
		System.out.println("Synopsis: ");
		String synopsis;
		synopsis= sc.nextLine();
		
		
		//director
		System.out.println("Director: ");
		String director;
		director = sc.nextLine();
		
		
		//cast
		choice=0;
		System.out.println("Cast: ");
		List<String> cast = new ArrayList<String>();
		do {
			System.out.println("1. Insert cast");
			System.out.println("2. Done");
			choice=sc.nextInt();
			sc.nextLine();
			
			if (choice==1) {
				System.out.println("Name of cast:");
				cast.add(sc.nextLine());
			}
			
		} while (choice==1);
		
		
		//System.out.println("Average Rating: ");
		//default to -1
		float avgRating =-1;
		
		
		//blockbuster
		char charchoice;
		boolean isBlockBuster=false;
		do {
			System.out.println("Is movie a blockbuster? (Y/N)");
			
			charchoice = sc.nextLine().charAt(0);
			if (charchoice=='y' || charchoice=='Y') isBlockBuster = true;
			else if ((charchoice=='n')||(charchoice=='N')) isBlockBuster = false;
			else {
				System.out.println("Invalid choice!\n");
				continue;
			}
			break;
		} while (true);
		
		//System.out.println("Review list: ");
		//default to empty list;
		List<MovieReview> review_list = new ArrayList<MovieReview>();
		if (review_list.size()==0) System.out.println("Empty review list");
		
		//System.out.println("Ticket sales: ");
		//deafult to 0;
		int ticketSales = 0;
		
		//make new movie using given and default parameters
		Movie newm = new Movie(title, showingStatus, endOfShowingDate, synopsis, director, cast, avgRating, isBlockBuster, review_list, ticketSales);
		
		//add new movie to current movie list
		ArrayList<Movie> m1 = this.getM();
		m1.add(newm);
		
		//export to serialisable file
		exportData(m1);
	}
	
	public void updateMovie() {
		int choice=0;
		//Scanner sc = new Scanner(System.in);
		ScannerErrorHandler sc = new ScannerErrorHandler();

		System.out.println("Select the movie you want: ");
		this.printMovieList();
		
		do {
			System.out.println("Select the movie you want: (Input 0 to quit)");
			this.printMovieList();
			ind = sc.nextInt();
			sc.nextLine();
			if (ind==0) return; //quit
			else if (ind>=1 && ind<=m1.size()) {
				break;
			} else {
				System.out.println("Invalid choice!\n");
			}
		} while (true);
		
		Movie u = m1.get(ind-1);
		
		int attr=0;
		do {
			System.out.println("What attribute would you like to update?");
			System.out.println("1. Title");
			System.out.println("2. Showing status");
			System.out.println("3. End of Showing date");
			System.out.println("4. Synopsis");
			System.out.println("5. Director");
			System.out.println("6. Cast");
			System.out.println("7. Average rating");
			System.out.println("8. Blockbuster or not");
			System.out.println("9. Movie Review list");
			System.out.println("10. Ticket sales");
			attr = sc.nextInt();
			sc.nextLine();
		} while (attr<1 || attr>10);
		
		
		if (attr==1) {
			System.out.println("Current title: "+u.getTitle());
			System.out.println("Enter new title: ");
			String value = sc.nextLine();
			u.setTitle(value);
		}
		else if (attr==2) { //showingstatus and endofshowingdate
			System.out.println("Current showing status: "+u.getShowingStatus());
			String value = null;
			LocalDate endOfShowingDate = null;
			do {
				System.out.println("Enter new showing status: ");
				System.out.println("1. Coming Soon");
				System.out.println("2. Preview");
				System.out.println("3. Now Showing");
				System.out.println("4. End of Showing");
				choice = sc.nextInt();
				sc.nextLine();
				int day, month, year;
				if (choice==1) value = "Coming Soon";
				else if (choice==2) value = "Preview";
				else if (choice==3) value = "Now Showing";
				else if (choice==4) {
					value = "End of Showing";
					do {
						System.out.println("Input end-of-showing date: ");
						System.out.print("Year: ");
						year = sc.nextInt();
						sc.nextLine();
						System.out.print("Month: ");
						month = sc.nextInt();
						sc.nextLine();
						if (month<1 || month>12) {
							System.out.println("Invalid month!\n");
							continue;
						}
						System.out.print("Day: ");
						day = sc.nextInt();
						sc.nextLine();
						if (day<1 || day>31) {
							System.out.println("Invalid day!\n");
							continue;
						}
						break;
					} while (true);
					endOfShowingDate = LocalDate.of(year,month,day);
				}
				else System.out.println("Invalid choice!\n");
			} while (choice<1 || choice>4);
			u.setShowingStatus(value);
			u.setEndOfShowingDate(endOfShowingDate);
		}
		
		else if (attr==3) {
			if (u.getEndOfShowingDate()==null) {
				System.out.println("Movie showing status is not End of Showing! Update failed.");
				return;
			}
			System.out.println("Current endOfShowingDate: "+u.getEndOfShowingDate());
			int day,month,year;
			do {
				System.out.println("Year: ");
				year = sc.nextInt();
				
				System.out.println("Month: ");
				month = sc.nextInt();
				if (month<1||month>12) {
					System.out.println("Invalid month!\n");
					continue;
				}
				
				System.out.println("Day: ");
				day = sc.nextInt();
				if (day<1||day>31) {
					System.out.println("Invalid day!\n");
					continue;
				}
				break;
			} while(true);
			u.setEndOfShowingDate(LocalDate.of(year, month, day));
			
		}
		
		else if (attr==4) {
			System.out.println("Current synopsis: "+u.getSynopsis());
			System.out.println("Enter new synopsis: ");
			String value = sc.nextLine();
			u.setSynopsis(value);
		}
		else if (attr==5) {
			System.out.println("Current director: "+u.getDirector());
			System.out.println("Enter new director: ");
			String value = sc.nextLine();
			u.setDirector(value);
		}
		else if (attr==6) {
			System.out.println("What do you want to do? \n1. Add cast \n2. Update cast details \n3. Delete cast");
			do {
				choice = sc.nextInt();
				sc.nextLine();
				if (choice>=1 && choice<=3) break;
				else System.out.println("Invalid choice!\n");
			} while (true);
			
			
			if (choice == 1) {
				System.out.println("Current cast: ");
				u.printCast();
				//add cast
				List<String> value = u.getCast();
				String in;
				do {
					System.out.println("Enter new cast: (Input 0 to quit)");
					in = sc.nextLine();
					if (in.equals("0")) break;
					value.add(in);
				} while (true);
				u.setCast(value);
			}
			else if (choice==2) {
				System.out.println("Current cast: ");
				if (u.getCast().size() == 0) {
					System.out.println("No cast available for update!");
					return;
				}
				u.printCast();
				//update cast
				int num=0;
				do {
					System.out.println("Enter cast number to update: (Input 0 to quit)");
					num = sc.nextInt();
					sc.nextLine();
					if (num==0) break;
					List<String> value = new ArrayList<String>();
					value = u.getCast();
					System.out.println("Enter new data for selected cast: ");
					String in = sc.nextLine();
					value.set(num-1, in);
				} while (true);
			}
			else if (choice==3) {
				System.out.println("Current cast: ");
				if (u.getCast().size() == 0) {
					System.out.println("No cast available for delete!");
					return;
				}
				u.printCast();
				//delete cast
				int num=0;
				do {
					System.out.println("Enter cast number to delete: (Input 0 to quit)");
					num = sc.nextInt();
					sc.nextLine();
					if (num==0) break;
					List<String> value = new ArrayList<String>();
					value = u.getCast();
					value.remove(num-1);
					u.setCast(value);
				} while (true);
			}	
		}
		else if (attr==7) {
			System.out.println("Current average rating: "+u.getAvgRating());
			u.setAvgRating(u.getReview_list());
			System.out.println("New average rating: "+u.getAvgRating());
		}
		else if (attr==8) {
			u.setBlockBuster(!u.isBlockBuster());
			if (u.isBlockBuster()) System.out.println("Movie is now a blockbuster.");
			else System.out.println("Movie is now a non-blockbuster.");
		}
		else if (attr==9) { 
			System.out.println("Current reviews: ");
			if (u.getReview_list().size() == 0) {
				System.out.println("No review available for delete!");
				return;
			}
			u.printPastReviews();
			//delete review
			int num=0;
			do {
				System.out.println("Enter review number to delete: (Input 0 to quit)");
				num = sc.nextInt();
				sc.nextLine();
				if (num==0) break;
				List<MovieReview> value = new ArrayList<MovieReview>();
				value = u.getReview_list();
				value.remove(num-1);
				u.setReview_list(value);
			} while (true);
		}
		else if (attr==10) {
			System.out.println("Current ticket sales: "+u.getTicketSales());
			System.out.println("Enter new ticket sale numbers: ");
			int value = sc.nextInt();
			sc.nextLine();
			u.setTicketSales(value);
		}
		else {
			System.out.println("Attribute error. No updates done.");
			return;
		}
		
		//after updating then need to update .dat
		m1.set(ind-1, u);
		this.exportData(m1);
		
	}
	
	public void removeMovie() {
		// Scanner sc = new Scanner(System.in);
		ScannerErrorHandler sc = new ScannerErrorHandler();

		Movie r=null;
		do {
			System.out.println("Enter movie name to remove: ");
			String movieName= sc.next();
			r = this.findMovie(movieName);
			System.out.println("Movie not found. Please try again.\n");
		} while (r==null);	
		
		ArrayList<Movie> m1 = this.getM();
		if (m1.size()==0) {
			System.out.println("Nothing to delete here!");
			return;
		}
		
		do {
			System.out.println("Select the movie you want: (Input 0 to quit)");
			this.printMovieList();
			
			int choice = sc.nextInt();
			sc.nextLine();
			
			if (choice==0) break;
			else if (choice>m1.size()) System.out.println("Invalid choice!\n"); 
			else m1.remove(choice-1);
		} while (true);	
		
		exportData(m1);
	}
}
