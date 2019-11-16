package controller;

import java.util.Date;

/**
 An interface for public holiday manager
 @author SS3_Group4
 @version 1.0
 @since 2019-11-15
*/

public interface PublicHoliday_inf {
	/** 
	* Print menu for public holiday updates
	*/
	public void printMenu();
	/** 
	* Add a public holiday
	* @param day The day chosen
	* @param month The month chosen
	* @param year The year chosen
	*/
	public void addHoliday(int day, int month, int year);
	/** 
	* Delete a public holiday
	* @param day The day chosen
	* @param month The month chosen
	* @param year The year chosen
	*/
	public void deleteHoliday(int day, int month, int year);
	/** 
	* Check a public holiday
	* @param date The date to check for
	*/
	public boolean checkHoliday (Date d);
	
}
