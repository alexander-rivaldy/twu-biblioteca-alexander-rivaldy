package com.twu.biblioteca;

/**
 * Created by alexa on 2/02/2019.
 */
public class Book {

    String title;
    String author;
    String year;
    boolean available;

    public Book(String title, String author, String year){
        this.title = title;
        this.author = author;
        this.year = year;
        available = true;
    }

    public Book(String title, String author, String year, boolean available){
        this.title = title;
        this.author = author;
        this.year = year;
        this.available = available;
    }

    public String getFullDetail(){
        if((title == null && author == null && year == null) ||
                (title.isEmpty() && author.isEmpty() && year.isEmpty()))
            return "Error!!! Book has no details";

        String detail = String.format(" %-20s | %-15s | %-4s |",
                                        title, author, year);
        return detail;
    }

    public void borrow() throws BookCannotBeCheckedOutException {
        if(available)
            available = false;
        else
            throw new BookCannotBeCheckedOutException("Sorry, that book is not available");
    }

    public String getTitle(){ return title; }

    public boolean isAvailable(){
        return available;
    }
}
