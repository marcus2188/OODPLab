package entity;


import java.io.InvalidClassException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import utils.SerializeDB;

public class Movie implements Serializable{
    
	private String title;
    private String showingStatus;
    private LocalDate endOfShowingDate;
    private String synopsis;
    private String director;
    private List<String> cast;
    private float avgRating;
    private boolean isBlockBuster;
    private List<MovieReview> review_list;
    private int ticketSales;
    
    
	public Movie(String title, String showingStatus, LocalDate endOfShowingDate, String synopsis, String director, List<String> cast,
			float avgRating, boolean isBlockBuster, List<MovieReview> review_list, int ticketSales) {
		this.title = title;
		this.showingStatus = showingStatus;
		this.endOfShowingDate=endOfShowingDate;
		this.synopsis = synopsis;
		this.director = director;
		this.cast = cast;
		this.avgRating = avgRating;
		this.isBlockBuster = isBlockBuster;
		this.review_list = review_list;
		this.ticketSales = ticketSales;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getShowingStatus() {
		if (this.showingStatus=="EndofShowing") return showingStatus+" "+this.getEndOfShowingDate();
		return showingStatus;
	}
	
	public void setShowingStatus(String showingStatus) {
		this.showingStatus = showingStatus;
	}
	
	public LocalDate getEndOfShowingDate() {
		return endOfShowingDate;
	}

	public void setEndOfShowingDate(LocalDate endOfShowingDate) {
		this.endOfShowingDate = endOfShowingDate;
	}

	public String getSynopsis() {
		return synopsis;
	}
	
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	
	public String getDirector() {
		return director;
	}
	
	public void setDirector(String director) {
		this.director = director;
	}
	
	public List<String> getCast() {
		return cast;
	}
	
	public void setCast(List<String> cast) {
		this.cast = cast;
	}
	
	public double getAvgRating() {
		return avgRating;
	}
	
	public void setAvgRating(float avgRating) {
		this.avgRating = avgRating;
	}
	
	public void setAvgRating(List<MovieReview> review_list) {	//added function
		int total=0;
		int size = review_list.size();
		if (size<=1) {
			this.avgRating=-1;
			return;
		}
		for (int i=0;i<size;i++) {
			total += review_list.get(i).getRating();
		}
		this.avgRating = total/(float) size;
	}
	
	public boolean isBlockBuster() {
		return isBlockBuster;
	}
	
	public void setBlockBuster(boolean isBlockBuster) {
		this.isBlockBuster = isBlockBuster;
	}
    
	public List<MovieReview> getReview_list() {
		return review_list;
	}

	public void setReview_list(List<MovieReview> review_list) {
		this.review_list = review_list;
		this.setAvgRating(review_list);
	}
	
	public int getTicketSales() {
		return ticketSales;
	}

	public void setTicketSales(int ticketSales) {
		this.ticketSales = ticketSales;
	}
	
	public void appendMovieReviewList() {	//added function
		Scanner sc = new Scanner(System.in);
		int rating;
		System.out.println("Rate this movie: 1 2 3 4 5");
		do {
			rating = sc.nextInt();
			sc.nextLine();
		} while (rating<1 || rating>5);
		
		System.out.println("Comments: ");
		String comments = sc.nextLine();
		
		MovieReview mr = new MovieReview(comments,rating);
		this.getReview_list().add(mr);
		this.setAvgRating(this.getReview_list());
	}
	
	public void printCast() {
		int size_cast = getCast().size();
		for (int i=0;i<size_cast;i++ ) {
			System.out.println(getCast().get(i));
		}
	}
	
	public void printPastReviews() {
		int size_review = getReview_list().size();
		for (int i=0;i<size_review;i++) {
			System.out.println(i+1);
			this.getReview_list().get(i).printReview();
		}
	}
	
	public void printReviewRating() {
		List<MovieReview> rlist = this.getReview_list();
		if (rlist.size()<=1 || this.getAvgRating()==-1) {
			System.out.print("NA");
			return;
		}
		System.out.printf("%.2f",this.getAvgRating());
		return;
	}
	
	public void printMovie() {
		System.out.println("Title: "+getTitle());
		System.out.println("Showing Status: "+getShowingStatus());
		System.out.println("Synopsis: "+getSynopsis());
		System.out.println("Director: "+getDirector());
		
		System.out.println("Cast: ");
		printCast();
		
		System.out.print("Average Rating: ");
		this.printReviewRating();
		System.out.println();
		
		System.out.println("Past Reviews: ");
		printPastReviews();
	}
    
}
