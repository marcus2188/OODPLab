package entity;

import java.io.Serializable;

public class MovieReview implements Serializable {
    private int rating;
    private String comments;
    
    public MovieReview(String comments, int rating) {
        this.rating = rating;
        this.comments = comments;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    
    public void printReview() {
    	System.out.println("Rating: " + getRating());
    	System.out.println("Comments: " + getComments());
    }
}
