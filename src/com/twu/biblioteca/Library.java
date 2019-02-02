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

    public String borrowProcess(Scanner reader){
        String title = askForTitle(reader);
        try{
            findBook(title).borrow();
        }
        //Exception could be BookCannotBeCheckedOutException or BookNotFoundException
        catch(Exception e){
            return e.getMessage();
        }

        return "\nThank you! Enjoy the book\n";
    }

    public String askForTitle(Scanner reader){
        String input = reader.nextLine();
        return input;
    }

    public Book findBook(String title) throws BookNotFoundException{
        for(Book book : books){
            if(book.getTitle().equals(title))
                return book;
        }
        throw new BookNotFoundException("Book is not in the Library!");
    }








}
