package entity;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class ShoppingOrder implements Serializable{
	public ShoppingOrder(ArrayList<MovieTicket> tix, Date dop){
		this.tickets = tix;
		this.dateofPurchase = dop;
	}
	ArrayList<MovieTicket> tickets;
	Date dateofPurchase;
    
    public void addtix(MovieTicket ticket) throws ParseException {
    	this.tickets.add(ticket);
    }
    public void printalltickets() {
    	for(int i = 0; i < this.tickets.size(); i++) {
    		this.tickets.get(i).printTicketDetails();
    	}
    }
    public void setpaymentDate(Date newdate) {
    	this.dateofPurchase = newdate;
    }
}
