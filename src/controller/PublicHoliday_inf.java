package controller;

import java.util.Date;

public interface PublicHoliday_inf {
	public void printMenu();
	public void addHoliday(int day, int month, int year);
	public void deleteHoliday(int day, int month, int year);
	public boolean checkHoliday (Date d);
	
}
