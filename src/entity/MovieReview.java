package entity;

import java.io.Serializable;

 /**
 Represents a movie review
 @author SS3_Group4
 @version 1.0
 @since 2019-11-15
*/

public class MovieReview implements Serializable {
    /** 
    * The rating in this movie review
    */
    private int rating;
    /** 
    * The comment in this movie review
    */
    private String comments;
    
    /** 
    * Creates a movie review with the given comment and rating
    * @param comments The comment in this movie review
    * @param rating The rating in this movie review
    */
    public MovieReview(String comments, int rating) {
        this.rating = rating;
        this.comments = comments;
    }

    /** 
    * Gets the rating in this movie review
    * @return The rating in this movie review
    */
    public int getRating() {
        return rating;
    }

    /** 
    * Change the rating in this movie review
    * @param rating The new rating in this movie review
    */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
    * Gets the comment in this movie review
    * @return The comment in this movie review
    */
    public String getComments() {
        return comments;
    }

    /** 
    * Change the comment in this movie review
    * @param comments The new comment in this movie review
    */
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    /** 
    * Print this movie review
    */
    public void printReview() {
    	System.out.println("Rating: " + getRating());
    	System.out.println("Comments: " + getComments());
    }
}
