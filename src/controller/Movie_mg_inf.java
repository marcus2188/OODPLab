package controller;

/**
 An interface for movie movie-goer manager
 @author SS3_Group4
 @version 1.0
 @since 2019-11-15
*/

public interface Movie_mg_inf extends Movie_both_inf {
	/** 
	* Search a movie
	*/
	public void searchMovie();
	/** 
	* List all movies
	*/
	public void listAllMovie();
}
