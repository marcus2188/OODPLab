package entity;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

 /**
 Represents a movie
 Each movie is associated to one or many movie reviews.
 @author SS3_Group4
 @version 1.0
 @since 2019-11-15
*/

public class Movie implements Serializable{

    /** 
	* The title of this movie
	*/
	private String title;

	/** 
	* The showing status of this movie
	*/
    private String showingStatus;

	/** 
	* The date where this movie stops showing
	*/
    private LocalDate endOfShowingDate;

	/** 
	* The sypnosis of this movie
	*/
    private String synopsis;
	/** 
	* The director of this movie
	*/
    private String director;
	/** 
	* The casts of this movie
	*/
    private List<String> cast;
	/** 
	* The average rating of this movie
	*/
    private float avgRating;
	/** 
	* Determinant for blockbuster movie
	*/
    private boolean isBlockBuster;
	/** 
	* The reviews for this movie
	*/
    private List<MovieReview> review_list;
	/** 
	* The number of ticket sales for this movie
	*/
    private int ticketSales;

    /** 
    * Creates a movie with the given title, showing status, end of showing date, sypnosis, director, casts and the determinant for blockbuster.
    * @param title This movie's title
    * @param showingStatus This movie's showing status
	* @param endOfShowingDate This movie's end of showing date
	* @param sypnosis This movie's sypnosis
	* @param director This movie's director
	* @param casts This movie's casts
	* @param isBlockBuster This movie's determinant for blockbuster
	* @param ticketSales This movie's ticket sales

    */
	public Movie(String title, String showingStatus, LocalDate endOfShowingDate, String synopsis, String director, List<String> cast, boolean isBlockBuster, int ticketSales) {
		this.title = title;
		this.showingStatus = showingStatus;
		this.endOfShowingDate=endOfShowingDate;
		this.synopsis = synopsis;
		this.director = director;
		this.cast = cast;
		this.isBlockBuster = isBlockBuster;
		this.review_list = new ArrayList<MovieReview>();
		this.setAvgRating(review_list);
		this.ticketSales = ticketSales;
	}

	/**
	* Gets the title of this movie
	* @return title of this movie
	*/
	public String getTitle() {
		return title;
	}
	
	/** 
	* Change title for this movie
	* @param title The new title for this movie
	*/
	public void setTitle(String title) {
		this.title = title;
	}
	
	/** 
	* Gets the showing status of this movie
	* @return the showing status of this movie
	*/
	public String getShowingStatus() {
		return showingStatus;
	}
	
	/** 
	* Change the showing statys for this movie
	* @param showStatus The new status for this movie
	*/
	public void setShowingStatus(String showingStatus) {
		this.showingStatus = showingStatus;
	}
	
	/** 
	* Gets the end of showing date for this movie
	* @return The end of showing date for this movie
	*/
	public LocalDate getEndOfShowingDate() {
		return endOfShowingDate;
	}

	/** 
	* Change the end of showing date for this movie
	* @param endOfShowingDate The enew nd of showing date 
	*/
	public void setEndOfShowingDate(LocalDate endOfShowingDate) {
		this.endOfShowingDate = endOfShowingDate;
	}

	/** 
	* Gets the sypnosis for this movie
	* @return The sypnosis for this movie
	*/
	public String getSynopsis() {
		return synopsis;
	}
	
	/** 
	* Change the sypnosis for this movie
	* @param synpnosis The new sypnosis of this movie
	*/
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	
	/** 
	* Gets the director for this movie
	* @return The director for this movie
	*/
	public String getDirector() {
		return director;
	}
	
	/** 
	* Change the director for this movie
	* @param director The director for this movie
	*/
	public void setDirector(String director) {
		this.director = director;
	}

	/** 
	* Gets the list of casts for this movie
	* @return The list of casts for this movie
	*/
	public List<String> getCast() {
		return cast;
	}
	
	/** 
	* Change the list of casts for this movie
	* @param cast The list of cast for this movie
	*/
	public void setCast(List<String> cast) {
		this.cast = cast;
	}
	
	/** 
	* Gets the average rating for this movie
	* @return The average rating for this movie
	*/
	public double getAvgRating() {
		return avgRating;
	}
	
	/**
	* Change the average rating for this movie 
	* @param avgRating The average rating for this movie
	*/
	public void setAvgRating(float avgRating) {
		this.avgRating = avgRating;
	}
	
	/** 
	* Change the average rating for this movie 
	* @param avgRating The reviews of this movie
	*/
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
	
	/** 
	* Gets the determinant for blockbuster
	* @return The dterminant for blockbuster
	*/
	public boolean isBlockBuster() {
		return isBlockBuster;
	}
	
	/** 
	* Change the determinant for this movie
	* @param isBlockBuster The determinant for this movie to be a blockbuster
	*/
	public void setBlockBuster(boolean isBlockBuster) {
		this.isBlockBuster = isBlockBuster;
	}
    
	/** 
	* Gets the reviews for this movie
	* @return The list of reviews for this movie
	*/
	public List<MovieReview> getReview_list() {
		return review_list;
	}

	/** 
	* Change the reviews for this movie
	* @param review_list The new list of reviews 
	*/
	public void setReview_list(List<MovieReview> review_list) {
		this.review_list = review_list;
		this.setAvgRating(review_list);
	}
	
	/** 
	* Gets the number of ticket sales for this movie
	* @return The number of ticket sales for this movie
	*/
	public int getTicketSales() {
		return ticketSales;
	}

	/** 
	* Change the number of ticket sales for this movie
	* @param ticketSales The new number of ticket sales
	*/
	public void setTicketSales(int ticketSales) {
		this.ticketSales = ticketSales;
	}

	/** 
	* Increment the number of ticket sakes
	*/
	public void addTicketSales(){
		this.ticketSales++;
	}
	
	/** 
	* Add a new movie review for this movie
	* @param comments The comment for the new movie review for this movie
	* @param raing The rating for the new movie review for this movie
	*/
	public void appendMovieReviewList(String comments, int rating) {	//added function
		MovieReview mr = new MovieReview(comments,rating);
		this.getReview_list().add(mr);
		this.setAvgRating(this.getReview_list());
		//System.out.println("Rating new: " + getAvgRating());
	}
	
	/** 
	* Print the casts for this movie
	*/
	public void printCast() {
		int size_cast = getCast().size();
		if (size_cast==0) System.out.println("No cast found.");
		for (int i=0;i<size_cast;i++ ) {
			System.out.println(getCast().get(i));
		}
	}
	
	/** 
	* Print the past reviews for this movie
	*/
	public void printPastReviews() {
		int size_review = getReview_list().size();
		if (size_review==0) System.out.println("No review found.");
		for (int i=0;i<size_review;i++) {
			System.out.println(i+1);
			this.getReview_list().get(i).printReview();
		}
	}
	
	/** 
	* Print the specific movie rewiew for this movie
	*/
	public void printReviewRating() {
		List<MovieReview> rlist = this.getReview_list();
		if (rlist.size()<=1 || this.getAvgRating()==-1) {
			System.out.print("NA");
			return;
		}
		System.out.printf("%.1f",this.getAvgRating());
		return;
	}
	
	/** 
	* Print this movie
	*/
	public void printMovie() {
		System.out.println("Title: "+getTitle());
		System.out.println();
		
		System.out.println("Showing Status: "+getShowingStatus());
		if (getShowingStatus().equalsIgnoreCase("End of Showing")) System.out.println("\nEnd of showing date: "+getEndOfShowingDate());
		System.out.println();
		
		System.out.println("Synopsis: "+getSynopsis());
		System.out.println();
		
		System.out.println("Director: "+getDirector());
		System.out.println();
		
		System.out.println("Cast: ");
		printCast();
		System.out.println();
		
		System.out.print("Average Rating: ");
		this.printReviewRating();
		System.out.println('\n');
		
		System.out.println("Past Reviews: ");
		printPastReviews();
		System.out.println();
	}
    
}
