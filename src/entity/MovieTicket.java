package entity;

import java.io.Serializable;

// TODO: bring in moviescreening entity as an attribute
public class MovieTicket implements Serializable{
	private float price;
    private String TID;

    // features
    private AgeGroup ageGroup;
    private boolean weekday;
    private boolean before6;
    private ScreeningFormat format;
    private int day;
    private String seat;
    private MovieScreening movieScreening;

    public MovieTicket(AgeGroup ageGroup, boolean weekday, boolean before6, ScreeningFormat format, int day, float price) {
        this.ageGroup = ageGroup;
        this.weekday = weekday;
        this.before6 = before6;
        this.format = format;
        this.day = day;
        this.price = price;
        this.movieScreening = null;
        this.seat = null;
    }

    // default constructor
    public MovieTicket() {
        this.ageGroup = null;
        this.weekday = false;
        this.before6 = false;
        this.format = null;
        this.day = 0;
        this.price = 0;
        this.movieScreening = null;
        this.seat = null;
    }

    public void setPriceBasedOnAttributes() {
        this.price = PriceTable.checkPrice(
                this.ageGroup,
                this.weekday,
                this.before6,
                this.format,
                this.day);
    }

    public void setMovieScreening (MovieScreening ms) {
        this.movieScreening = ms;
    }

    public MovieScreening getMovieScreening() {
    	return this.movieScreening;
    }

    public void printTicketDetails() {
        System.out.println("Ticket details:");
        System.out.println("Age Group: " + this.getAgeGroup());
        System.out.println("Day: " + this.getDay());
        System.out.println((this.isBefore6() ? "Before 6" : "After 6"));
        System.out.println("Screening format: " + this.getFormat());
        System.out.println("Price: " + this.getPrice());
        return;
    }


    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getTID() {
        return TID;
    }

    public void setTID(String TID) {
        this.TID = TID;
    }
    public boolean isWeekday() {
        return weekday;
    }

    public boolean isBefore6() {
        return before6;
    }

    public ScreeningFormat getFormat() {
        return format;
    }

    public int getDay() {
        return day;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public void printDetails() {
        System.out.println("===============Movie Ticket Details=====================");
        System.out.println("Cineplex: " + this.movieScreening.getCineplex());
        System.out.println("Cinema: " + this.movieScreening.getCinema());
        System.out.println("Movie: " + this.movieScreening.getMovieTitle());
        System.out.println("Date: " + this.movieScreening.getShowDate());
        System.out.println("Time: " + this.movieScreening.getShowTime());
        System.out.println("Ticket ID: " + this.TID);
        System.out.println("Price: " + this.price);
        System.out.println("Seat: " + this.seat);
    }
}
