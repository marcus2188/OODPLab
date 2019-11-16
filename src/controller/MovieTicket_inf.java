package controller;

import entity.MovieTicket;

import java.io.IOException;
import java.text.ParseException;

/**
 An interface for movie ticket manager
 @author SS3_Group4
 @version 1.0
 @since 2019-11-15
*/

public interface MovieTicket_inf {
    /** 
    * Update price table 
    */
    void updatePriceTable() throws IOException;
    /** 
    * Print menu to update price table
    */
    void printMenu();
    /** 
    * Import data
    */
    void importData() throws IOException;
    /** 
    * Takes in details and returns a movie ticket with appropriate price
    */
    MovieTicket checkPrice() throws ParseException;
    /** 
    * Delete data
    */
    void deleteData();
    /** 
    * Print price table
    */
    void printPriceTable();
}
