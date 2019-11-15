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
