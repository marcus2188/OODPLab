package entity;

import utils.TextTicketDB;

import java.util.ArrayList;

public class PriceTable {
    private static ArrayList<PriceTableTicket> priceTable;

     public PriceTable() {
        this.importData();
    }


    private void importData() {
        priceTable = TextTicketDB.readPrices("prices.txt");
    }

    public float checkPrice(
            AgeGroup ageGroup,
            boolean isWeekDay,
            boolean isBefore6,
            ScreeningFormat format,
            int day
    ) {
        for (int i =0; i < priceTable.size(); i++){
            PriceTableTicket sampleTicket = priceTable.get(i);
            if(
             sampleTicket.getAgeGroup() == ageGroup
             && sampleTicket.isWeekday() == isWeekDay
              && sampleTicket.isBefore6() == isBefore6
             && sampleTicket.getFormat() == format
             && sampleTicket.getDay() == day
            ) {
                System.out.println("Age Group: " + ageGroup);
                System.out.println("Before 6: "+ isBefore6);
                System.out.println("Weekday: " + isWeekDay);
                System.out.println("Screening Format: " + format);
                System.out.println("Ticket price: " + sampleTicket.getPrice());
                return sampleTicket.getPrice();
            }
         }
        if (format == ScreeningFormat.THREEDIMENSION) {

            for (int i = 0; i < priceTable.size(); i++) {
                PriceTableTicket ticket = priceTable.get(i);
                if (ticket.getFormat().equals(ScreeningFormat.THREEDIMENSION)
                && ticket.getAgeGroup().equals(AgeGroup.REGULAR)
                && ticket.isBefore6() == isBefore6
                        && ticket.getDay() == day
                        && ticket.isWeekday() == isWeekDay
            ) {
                    System.out.println("No concession prices found. Regular price: " + ticket.getPrice());
                    return ticket.getPrice();
                }
            }

        } else if (format == ScreeningFormat.REGULAR) {
            for (int i = 0; i < priceTable.size(); i++) {
                PriceTableTicket ticket = priceTable.get(i);
                if (ticket.getFormat().equals(ScreeningFormat.REGULAR)
                        && ticket.getAgeGroup().equals(AgeGroup.REGULAR)
                        && ticket.isBefore6() == isBefore6
                        && ticket.getDay() == day
                        && ticket.isWeekday() == isWeekDay
                ) {
                    System.out.println("No concession prices found. Regular price: " + ticket.getPrice());
                    return ticket.getPrice();
                }
            }
        }
        return 0;


    }
}
