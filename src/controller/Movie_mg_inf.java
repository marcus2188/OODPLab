package controller;


public interface Movie_mg_inf {
	public void searchMovie(String movieName);
	public void listAllMovie();
	public void viewMovieDetails(String movieName);
	public void addMovieReview(String movieName, String comments, int rating);
	public void printPastReviews(String movieName);
}
