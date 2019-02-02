package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by alexa on 1/02/2019.
 */
public class Library {

    static final int MAX_COLUMN = 57;

    ArrayList<Book> books;

    public Library(){
        books = new ArrayList<Book>();
    }

    public void addBook(Book book){
        books.add(book);
    }

    public String getAvailableBooks(){
        String allBooks = "";
        String format = "";
        int counter = 1;
        for(Book book : books){
            if (!book.isAvailable())
                continue;
            format = String.format(" | %-2s |%-48s\n",
                    ""+ counter++,book.getFullDetail());
            allBooks += format;
        }

        return allBooks;

    }

    public String getAllBookDetailsWithColumn(){
        String allBooksWithColumn = String.format(" | %-2s | %-20s | %-15s | %4s |\n",
                "No", "Title", "Author", "Year");
        for(int i=0; i<MAX_COLUMN; i++){
            allBooksWithColumn += "-";
        }
        return allBooksWithColumn + "\n" + getAvailableBooks() ;
    }

    public String borrowProcess(){
        String title = askForTitle();
        try{
            findBook(title).borrow();
        }
        catch(BookNotFoundException e){
            return e.getMessage();
        }
        return "\nThe book " + title + " has been successfully checked out!\n";
    }

    public String askForTitle(){
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();
        reader.close();
        return input;
    }

    public void borrowBook(Book book){
        book.borrow();
    }

    public Book findBook(String title) throws BookNotFoundException{
        for(Book book : books){
            if(book.getTitle().equals(title))
                return book;
        }
        throw new BookNotFoundException("Book is not in the Library!");
    }








}
