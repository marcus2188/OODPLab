package entity;

import utils.TextTicketDB;

import java.util.ArrayList;

public class PriceTable {
    private static ArrayList priceTable;

    public PriceTable() {
        this.importData();
    }

    private void importData() {
        this.priceTable = TextTicketDB.readPrices("prices.txt");
    }

    public static float checkPrice(
            AgeGroup ageGroup,
            boolean isWeekDay,
            boolean isBefore6,
            ScreeningFormat format,
            int day
    ) {
        for (int i =0; i < priceTable.size(); i++){
            MovieTicket sampleTicket =(MovieTicket) priceTable.get(i);
            if(sampleTicket.getAgeGroup() == ageGroup
            && sampleTicket.isWeekday() == isWeekDay
            && sampleTicket.isBefore6() == isBefore6
            && sampleTicket.getFormat() == format
            && sampleTicket.getDay() == day
            ) {
                return sampleTicket.getPrice();
            } else {
                System.out.println("Ticket not found");
                return 0;
            }
        }
        return 0;
    }
}
