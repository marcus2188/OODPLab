package controller;

import boundary.MovieGoer_UI;

import java.io.IOException;
import java.text.ParseException;

public interface ShoppingOrder_inf extends MovieGoer_UI {
     void bookTicket() throws ParseException, IOException;
     void makePurchase();
}
