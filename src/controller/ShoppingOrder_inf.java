package controller;

import java.io.IOException;
import java.text.ParseException;

import entity.MovieScreening;
import entity.Seat;

/**
 An interface for shopping order manager
 @author SS3_Group4
 @version 1.0
 @since 2019-11-15
*/

public interface ShoppingOrder_inf  {
     /** 
     * Book a ticket
     * @param obj The movie screening chosen
     * @param seat The seat chosen
     */
     void bookTicket(MovieScreening obj, Seat seat) throws ParseException, IOException;
     /** 
     * Make a purchase
     */
     void makePurchase();
     /** 
     * Import data
     */
     void importdata();
     /** 
     * Update data
     */
     void updatedata();
     /** 
     * View current shopping order
     */
     void viewcurrentSO();
     /** 
     * Reset current shopping order
     */
     void dumpcurrentSO();
     /** 
     * View all paid tickets
     */
     void viewallpaidtix();
     /** 
     * See all past purchasers
     */
     void seepeople();
     
}
