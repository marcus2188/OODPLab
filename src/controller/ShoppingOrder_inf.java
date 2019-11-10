package controller;

import java.io.IOException;
import java.text.ParseException;

import entity.MovieScreening;

public interface ShoppingOrder_inf  {
     void bookTicket() throws ParseException, IOException;
     void makePurchase();
     void importdata();
     void updatedata();
     void viewcurrentSO();
     void dumpcurrentSO();
     void viewallpaidtix();
     void seepeople();
     
}
