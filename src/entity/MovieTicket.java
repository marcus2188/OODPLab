package entity;

import java.io.Serializable;

/**
 Represents a movie ticket
 Each movie screening is associated with one movie screening and seat
 @author SS3_Group4
 @version 1.0
 @since 2019-11-15
*/

public class MovieTicket implements Serializable{
    /** 
    * The price of this movie ticket
    */
	private float price;
    /**
    * The transaction ID of this movie ticket 
    */
    private String TID;

    // features
    /** 
    * The age group of the purchaser
    */
    private AgeGroup ageGroup;
    /** 
    * The determinant for weekday during the time of boooking
    */
    private boolean weekday;
    /** 
    * The determinant for before 6 during the time of booking
    */
    private boolean before6;
    /** 
    * The determinant for platinum with respect to the cinema
    */
    private boolean platinum;

    /** 
    * The screening format of the movie screening
    */
    private ScreeningFormat format;
    /** 
    * The day during the time of booking
    */
    private int day;
    /** 
    * The seat chosen by the purchaser
    */
    private Seat seat;
    /** 
    * The movie screening this ticket is associated to
    */
    private MovieScreening movieScreening;

    /** 
    * Creates a movie ticket with the given age group, weekday determinant, before 6 determinant, screening format, day, price and platinum determinant
    * @param ageGroup The age group of purchaser
    * @param weekday The determinant for weekday during the time of boooking
    * @param before6 The determinant for before 6 during the time of booking
    * @param format The screening format of the movie screening
    * @param int The day during the time of booking
    * @param price The price of this movie ticket
    * @param platinum The determinant for platinum with respect to the cinema
    */
    public MovieTicket(AgeGroup ageGroup, boolean weekday, boolean before6, ScreeningFormat format, int day, float price, boolean platinum) {
        this.ageGroup = ageGroup;
        this.weekday = weekday;
        this.before6 = before6;
        this.platinum = platinum;
        this.format = format;
        this.day = day;
        this.price = price;
        this.movieScreening = null;
        this.seat = null;
    }

    /** 
    * Creates a default movie ticket with no attributes
    */
    public MovieTicket() {
        this.ageGroup = null;
        this.weekday = false;
        this.before6 = false;
        this.platinum = false;
        this.format = null;
        this.day = 0;
        this.price = 0;
        this.movieScreening = null;
        this.seat = null;
    }

    /** 
    Set this movie ticket price based on the determinants
    */
    public void setPriceBasedOnAttributes() {
        PriceTable tbl = new PriceTable();
        this.price = tbl
                .checkPrice(
                this.ageGroup,
                this.weekday,
                this.before6,
                this.format,
                this.day,
                this.platinum);
        
    }

    /** 
    * Increase the movie ticket price by the holiday rate
    */
    public void setHolidayRate() {
    	this.price = this.price * (float)1.5;
    	
    }

    /** 
    * Print the price of this ticket
    */
    public void printPrice() {
    	System.out.println("Ticket Price: " + this.price);
    }

    /**
    * Change the movie screening of this movie ticket
    * @param ms The new movie screening for this movie ticket
    */
    public void setMovieScreening (MovieScreening ms) {
        this.movieScreening = ms;
    }

    /** 
    * Gets the movie screening of this movie ticket
    * @return The movie screening of this movie ticket
    */
    public MovieScreening getMovieScreening() {
    	return this.movieScreening;
    }

    /**
    * Print the details of this movie ticket
    */
    public void printTicketDetails() {
        System.out.println("Ticket details:");
        System.out.println("Age Group: " + this.getAgeGroup());
        System.out.println("Day: " + this.getDay());
        System.out.println((this.isBefore6() ? "Before 6" : "After 6"));
        System.out.println("Screening format: " + this.getFormat());
        System.out.println("Price: " + this.getPrice());
        return;
    }

    /** 
    * Gets the platinum determinant of this movie ticket
    * @return The platinum determinant of this movie ticket
    */
    public boolean isPlatinum() {
        return platinum;
    }

    /** 
    * Change the platinum determinant for this movie ticket
    * @param platinum The new platinum determinant for this movie ticket
    */
    public void setPlatinum(boolean platinum) {
        this.platinum = platinum;
    }

    /** 
    * Change the weekday determinant for this movie ticket
    * @param bool The new weekday determinant for this movie ticket
    */
    public void setweekday(boolean bool) {
    	this.weekday = bool;
    }
    
    /** 
    * Gets the weekday determinant of this movie ticket
    * @return The weekday determinant of this movie ticket
    */
    public boolean getweekday() {
    	return this.weekday;
    }
    
    /** 
    * Change the before 6 determinant for this movie ticket
    * @param bool The new before 6 determinant for this movie ticket
    */
    public void setbefore6(boolean bool) {
    	this.before6 = bool;
    }
    
    /** 
    * Gets the before 6 determinant of this movie ticket
    * @return The before 6 determinant of this movie ticket
    */
    public boolean getbefore6() {
    	return this.before6;
    }
    
    /** 
    * Change the screening format for this movie ticket
    * @param format The new screening format for this movie ticket
    */
    public void setScreeningFormat(ScreeningFormat format) {
    	this.format= format;
    }
    
    /** 
    * Gets the screening format of this movie ticket
    * @return The screening format of this movie ticket
    */
    public ScreeningFormat getScreeningFormat() {
    	return this.format;
    }
    
    /** 
    * Change the day of booking for this movie ticket
    * @param day The new day pf booking for this movie ticket
    */
    public void setday(int day) {
    	this.day = day;
    }
    /**
    * Gets the day of booking for this movie ticket
    * @return The day of booking for this movie ticket
    */
    public int getday() {
    	return this.day;
    }

    /** 
    * Gets the price of this movie ticket
    * @return The price of this movie ticket
    */
    public float getPrice() {
        return price;
    }

    /** 
    * Change the price of this movie ticket
    * @param price The new price for this movie ticket
    */
    public void setPrice(float price) {
        this.price = price;
    }

    /** 
    * Gets the age group of the purchaser for this movie ticket
    * @return The age group of the purchaser for this movie ticket
    */
    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    /** 
    * Change the age group of the purchaser for this movie ticket 
    * @param ageGroup The new age group of the purchaser for this movie ticket
    */
    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

    /** 
    * Gets the transaction ID of this movie ticket
    * @return The transaction ID of this movie ticket
    */
    public String getTID() {
        return TID;
    }

    /** 
    * Change the transaction ID for this movie ticket
    * @param TID The new transaction ID for this movie ticket
    */
    public void setTID(String TID) {
        this.TID = TID;
    }

    /** 
    * Gets the weekday determinant of this movie ticket
    * @return The weekday determinant of this movie ticket
    */
    public boolean isWeekday() {
        return weekday;
    }

    /** 
    * Gets the before 6 determinant of this movie ticket
    * @return The before 6 determinant of this movie ticket
    */
    public boolean isBefore6() {
        return before6;
    }

    /** 
    * Gets the screening format of this movie ticket
    * @return The screening format of this movie ticket
    */
    public ScreeningFormat getFormat() {
        return format;
    }

    /**
    * Gets the day of booking for this movie ticket
    * @return The day of booking for this movie ticket
    */
    public int getDay() {
        return day;
    }

    /** Gets the seat associated to this movie ticket
    * @return The seat associated to this movie ticket 
    */
    public Seat getSeat() {
        return seat;
    }

    /** 
    * Change the seat associated to this movie ticket
    * @param seat The new seat associated to this movie ticket
    */
    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    /** 
    * Print movie ticket details
    */
    public void printDetails() {
        System.out.println("======================================================");
        System.out.printf("Price: $%.2f\n", this.price);
       //  System.out.println("Cineplex: " + this.movieScreening.getCineplex().getName());
        System.out.println("Cinema: " + this.movieScreening.getCinema().getName());
        System.out.println("Movie: " + this.movieScreening.getMovie().getTitle());
        System.out.println("Date: " + this.movieScreening.getShowDate());
        System.out.println("Time: " + this.movieScreening.getShowTime());
        System.out.println("Ticket ID: " + this.TID);
        System.out.println("Seat: " + this.seat.getSeatID());
        System.out.println("======================================================");
    }
}
