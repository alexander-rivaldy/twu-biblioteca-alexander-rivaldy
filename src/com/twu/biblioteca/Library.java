package com.twu.biblioteca;

import java.util.ArrayList;

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

    public String getAllBookDetails(){
        String allBooks = "";
        String format = "";
        for(Book book : books){
            format = String.format(" | %-2s |%-48s\n",
                    ""+(books.indexOf(book)+1),book.getFullDetail());
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
        return allBooksWithColumn + "\n" + getAllBookDetails() ;
    }


}
