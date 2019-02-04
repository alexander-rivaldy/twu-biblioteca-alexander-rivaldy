package com.twu.biblioteca;

/**
 * Created by alexa on 2/02/2019.
 */
public class Book implements LibraryItem{

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

    /**
     * Constructs a string containing details of the book
     * @return has the format of
     *  Title 1              | Author 1        | 2001 |
     */
    public String getFullDetail(){
        if((title == null && author == null && year == null) ||
                (title.isEmpty() && author.isEmpty() && year.isEmpty()))
            return "Error!!! Book has no details";

        String detail = String.format(" %-20.20s | %-15.15s | %-4s |",
                                        title, author, year);
        return detail;
    }

    /**
     * Function to borrowItem the book, changing the available boolean to false
     * or throwing an exception when it has been checked out already
     * @throws ItemCannotBeCheckedOutException when book has been checked out
     */
    public void borrowItem() throws ItemCannotBeCheckedOutException {
        if(available)
            available = false;
        else
            throw new ItemCannotBeCheckedOutException("Sorry, that book is not available");
    }

    /**
     * Function to return the book changing the available boolean to true
     * or throwing an exception when it is still available
     * @throws ItemNotValidForReturnException when book is still available
     */
    public void returnItem() throws ItemNotValidForReturnException {
        if(!available)
            available = true;
        else
            throw new ItemNotValidForReturnException("That is not a valid book to return");
    }

    public String getTitle(){ return title; }

    public boolean isAvailable(){
        return available;
    }
}
