package entity;

 /**
 Represents an entry in the price table
 @author SS3_Group4
 @version 1.0
 @since 2019-11-15
*/

public class PriceTableTicket {
    /** 
    * The price in this entry
    */
    private float price;

    /** 
    * The age group of this entry
    */
    private AgeGroup ageGroup;
    /** 
    * The weekday determinant of this entry
    */
    private boolean weekday;
    /** 
    * The before 6 determinant of this entry
    */
    private boolean before6;
    /** 
    * The screening format of this entry
    */
    private ScreeningFormat format;
    /** 
    * The day condition in this entry
    */
    private int day;

    /** 
    * Gets the price in this entry
    * @return The price in this entry
    */
    public float getPrice() {
        return price;
    }

    /** 
    * Change the price in this entry
    * @param price The new price of this entry
    */
    public void setPrice(float price) {
        this.price = price;
    }

    /** 
    * Gets the age group of this entry
    * @return The age group of this entry
    */
    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    /**
    * Change the age group for this entry
    * @param ageGroup The new age group for this entry
    */
    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

    /** 
    * Gets the weekday determinant of this entry
    * @return The weekday determinant of this entry
    */
    public boolean isWeekday() {
        return weekday;
    }

    /** 
    * Change the weekday determinant for this entry
    * @param weekday The new weekday determinant for this entry
    */
    public void setWeekday(boolean weekday) {
        this.weekday = weekday;
    }

    /** 
    * Gets the before 6 determinant of this entry
    * @return The before 6 determinant of this entry
    */
    public boolean isBefore6() {
        return before6;
    }

    /** 
    * Change the before 6 determinant for this entry
    * @param before6 The new before 6 determinant for this entry
    */
    public void setBefore6(boolean before6) {
        this.before6 = before6;
    }

    /** 
    * Gets the screening format of this entry
    * @return The screening format of this entry
    */
    public ScreeningFormat getFormat() {
        return format;
    }

    /** 
    * Change the screening format for this entry
    * @param format The new screening format for this entry
    */
    public void setFormat(ScreeningFormat format) {
        this.format = format;
    }

    /** 
    * Gets the day condition of this entry
    * @return The day condition of this entry
    */
    public int getDay() {
        return day;
    }

    /** 
    * Change the day condition for this entry
    * @param day The new day condition for this entry
    */
    public void setDay(int day) {
        this.day = day;
    }

    /** 
    * Creates a new entry in the price table with the given age group, weekday determinant, before 6 determinant, screening format, day condition and price
    * @param ageGroup The age group of this entry
    * @param weekday The weekday determinant of this entry
    * @param before6 The before 6 determinant of this entry
    * @param format The screening format of this entry
    * @param day The day condition in this entry
    * @param price The price in this entry
    */
    public PriceTableTicket(AgeGroup ageGroup, boolean weekday, boolean before6, ScreeningFormat format, int day, float price) {
        this.price = price;
        this.ageGroup = ageGroup;
        this.weekday = weekday;
        this.before6 = before6;
        this.format = format;
        this.day = day;
    }

    /** 
    * Print entry details
    */
    public void printTicketDetails() {
        System.out.println("======================================================");
        System.out.printf("Price: $%.2f\n", this.price);
        System.out.println("======================================================");
    }
}
