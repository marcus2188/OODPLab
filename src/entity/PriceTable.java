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
                return sampleTicket.getPrice();
            }
         }
        System.out.println("Ticket price not found");
        return 0;


    }
}
