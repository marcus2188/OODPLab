package entity;


import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable{
    
	private String title;
    private String showingStatus;
    private String synopsis;
    private String director;
    private List<String> cast;
    private double avgRating;
    private boolean isBlockBuster;
    private List<MovieReview> review_list;
    private int ticketSales;
    private List<MovieScreening> ms;
    
	public Movie(String title, String showingStatus, String synopsis, String director, List<String> cast,
			double avgRating, boolean isBlockBuster, List<MovieReview> review_list, int ticketSales, List<MovieScreening> ms) {
		super();
		this.title = title;
		this.showingStatus = showingStatus;
		this.synopsis = synopsis;
		this.director = director;
		this.cast = cast;
		this.avgRating = avgRating;
		this.isBlockBuster = isBlockBuster;
		this.review_list = review_list;
		this.ticketSales = ticketSales;
		this.ms = ms;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getShowingStatus() {
		return showingStatus;
	}
	
	public void setShowingStatus(String showingStatus) {
		this.showingStatus = showingStatus;
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
	
	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}
	
	public void setAvgRating(List<MovieReview> review_list) {	//added function
		int total=0;
		int size = review_list.size();
		for (int i=0;i<size;i++) {
			total += review_list.get(i).getRating();
		}
		this.avgRating = total/(double) size;
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
	}
	
	public int getTicketSales() {
		return ticketSales;
	}

	public void setTicketSales(int ticketSales) {
		this.ticketSales = ticketSales;
	}
	
	public List<MovieScreening> getMs() {
		return ms;
	}

	public void setMs(List<MovieScreening> ms) {
		this.ms = ms;
	}

	public void appendMovieReview(String comments, int rating) {	//added function
		MovieReview mr = new MovieReview(comments,rating);
		this.getReview_list().add(mr);
	}

	public void printMovie() {
		System.out.println("Title: "+getTitle());
		System.out.println("Showing Status: "+getShowingStatus());
		System.out.println("Synopsis: "+getSynopsis());
		System.out.println("Director: "+getDirector());
		
		System.out.println("Cast: ");
		int size_cast = getCast().size();
		for (int i=0;i<size_cast;i++ ) {
			System.out.println(getCast().get(i));
		}
		
		System.out.println("Average Rating: "+getAvgRating());
		
		System.out.println("Past Reviews: ");
		int size_review = getReview_list().size();
		for (int i=0;i<size_review;i++) {
			System.out.println(i+1);
			this.getReview_list().get(i).printReview();
		}
		
		System.out.println("Movie Screening: ");
		int size_ms = getMs().size();
		for (int i=0;i<size_ms;i++) {
			System.out.println(i+1);
			this.getMs().get(i).printMovieScreening();
		}
	}
    
}
