package controller;

import java.util.List;

import entity.MovieReview;

public interface Movie_admin_inf {
	public void createMovie(String title, String showingStatus, String synopsis, String director, List<String> cast, double avgRating, boolean isBlockBuster, List<MovieReview> review_list);
	public void updateMovie(String movieName, int attr, Object value);
	public void removeMovie(String movieName);
}
