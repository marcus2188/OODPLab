package entity;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class ShoppingOrder implements Serializable{
	ArrayList<MovieTicket> tickets = new ArrayList<MovieTicket>();
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
