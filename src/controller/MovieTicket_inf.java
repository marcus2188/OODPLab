package controller;

import entity.MovieTicket;

import java.io.IOException;
import java.text.ParseException;

public interface MovieTicket_inf {
    void updatePriceTable() throws IOException;
    void printMenu();
    void importData() throws IOException;
    MovieTicket checkPrice() throws ParseException;
    void deleteData();
    void printPriceTable();
    void updatePriceOfTicket(MovieTicket ticket);
}
