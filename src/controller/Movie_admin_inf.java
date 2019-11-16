package controller;

/**
 An interface for movie admin manager.
 @author SS3_Group4
 @version 1.0
 @since 2019-11-15
*/


public interface Movie_admin_inf extends Movie_both_inf{
	/** 
	* Create a movie
	*/
	public void createMovie();
	/** 
	* Update a movie
	*/
	public void updateMovie();
	/** 
	* Remove a movie
	*/
	public void removeMovie();
}
