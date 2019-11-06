package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public interface ShoppingOrder_inf  {
     void importData() throws FileNotFoundException, IOException, ClassNotFoundException;
     void updateData() throws FileNotFoundException, IOException, ClassNotFoundException;
     void createShoppingOrder();
     void viewShoppingCart();
     void clearBookingHistory();
     void clearPaymentHistory();
     void deleteShoppingOrder();
     void bookTicket() throws ParseException, IOException;
     void makePurchase();
     void deleteTicket();
     void viewPaymentHistory();
}
