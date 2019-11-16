package entity;

import utils.TextTicketDB;

import java.util.ArrayList;

 /**
 Represents the price table for our pricing scheme
 A price table is the aggregation of price table ticket
 @author SS3_Group4
 @version 1.0
 @since 2019-11-15
*/

public class PriceTable {
    /** 
    * The array list of price table ticket in this price table
    */
    private static ArrayList<PriceTableTicket> priceTable;
 
    /** 
    * Creates a price table
    */
    public PriceTable() {
        this.importData();
    }


    /** 
    * Import date from prices.txt
    */
    private void importData() {
        priceTable = TextTicketDB.readPrices("prices.txt");
    }

    /** 
    * Check price from price table
    * @param ageGroup The age group of purchaser
    * @param isWeekDay The determinant for weekday during the time of boooking
    * @param isBefore6 The determinant for before 6 during the time of booking
    * @param format The screening format of the movie screening
    * @param day The day during the time of booking
    * @param isPlatinum The determinant for platinum with respect to the cinema 
    */
    public float checkPrice(
            AgeGroup ageGroup,
            boolean isWeekDay,
            boolean isBefore6,
            ScreeningFormat format,
            int day,
            boolean isPlatinum
    ) {
        for (int i =0; i < priceTable.size(); i++){
            PriceTableTicket sampleTicket = priceTable.get(i);

            if(
             sampleTicket.getAgeGroup().equals(ageGroup)
             && sampleTicket.isWeekday() == isWeekDay
              && sampleTicket.isBefore6() == isBefore6
             && sampleTicket.getFormat().equals(format)
             && sampleTicket.getDay() == day
            ) {
                if (isPlatinum) {
                    return sampleTicket.getPrice() + 10;
                } else {
                    return sampleTicket.getPrice();
                }
            }
         }
        if (format.equals(ScreeningFormat.THREEDIMENSION)) {

            for (int i = 0; i < priceTable.size(); i++) {
                PriceTableTicket ticket = priceTable.get(i);
                if (ticket.getFormat().equals(ScreeningFormat.THREEDIMENSION)
                && ticket.getAgeGroup().equals(AgeGroup.REGULAR)
                && ticket.isBefore6() == isBefore6
                        && ticket.getDay() == day
                        && ticket.isWeekday() == isWeekDay
            ) {
                    System.out.println("No concession prices found. Regular price: " + ticket.getPrice());
                    if (isPlatinum) {
                        return ticket.getPrice() + 10;
                    } else {
                        return ticket.getPrice();
                    }
                }
            }

        } else if (format.equals(ScreeningFormat.REGULAR)){
            for (int i = 0; i < priceTable.size(); i++) {
                PriceTableTicket ticket = priceTable.get(i);
                if (ticket.getFormat().equals(ScreeningFormat.REGULAR)
                        && ticket.getAgeGroup().equals(AgeGroup.REGULAR)
                        && ticket.isBefore6() == isBefore6
                        && ticket.getDay() == day
                        && ticket.isWeekday() == isWeekDay
                ) {
                    System.out.println("No concession prices found. Regular price: " + ticket.getPrice());
                    if (isPlatinum) {
                        return ticket.getPrice() + 10;
                    } else {
                        return ticket.getPrice();
                    }
                }
            }
        }
        return 0;
    }
}
