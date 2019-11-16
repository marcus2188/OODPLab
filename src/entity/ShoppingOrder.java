package entity;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

 /**
 Represents the shopping order of the purchaser
 The shopping order is an aggregation of movie tickets
 @author SS3_Group4
 @version 1.0
 @since 2019-11-15
*/

public class ShoppingOrder implements Serializable{
	/** 
	* The array list of movie tickets
	*/
	ArrayList<MovieTicket> tickets;
	/** 
	* The date which the purchaser make his/her purchase
	*/
	Date dateofPurchase;
	
	/** 
	* Creates a shopping order with the given list of tickets and date
	* @param tix The array list of movie tickets
	* @param dop The date which the purchaser make his/her purchase
	*/
	public ShoppingOrder(ArrayList<MovieTicket> tix, Date dop){
		this.tickets = tix;
		this.dateofPurchase = dop;
	}
	
    /** 
	* Add a movie ticket into the shopping order
	* @param mt The new movie ticket to be added
	*/
    public void addticket(MovieTicket mt) throws ParseException {
    	this.tickets.add(mt);
    }

	/** 
	* Print all movie ticket in the shopping order
	*/
    public void printalltickets() {
    	for(int i = 0; i < this.tickets.size(); i++) {
    		this.tickets.get(i).printTicketDetails();
    	}
    }
	
	/** 
	* Change the date of purchase of this shopping order
	* @param newdate The new date of purchase for this shopping order
	*/
    public void setpaymentDate(Date newdate) {
    	this.dateofPurchase = newdate;
    }

	/** 
	* Gets the array list of movie tickets in this shopping order
	* @return The array list of movie tickets in this shopping order
	*/
    public ArrayList<MovieTicket> returnticketarray(){
    	return this.tickets;
    }

	/** 
	* Remove all movie tickets in this shopping order
	*/
    public void resetcart() {
    	this.tickets.clear();
    }
}
