package com.twu.biblioteca;

import java.util.ArrayList;

/**
 * Created by alexa on 1/02/2019.
 */
public class Library {

    ArrayList<String> books;

    public Library(){
        books = new ArrayList<String>();
    }

    public void addBook(String title){
        books.add(title);
    }

    public String getAllBookTitle(){
        if(books.isEmpty())
            return "There are no books!";

        String allBooks = "";
        for(String book : books){
            allBooks += (books.indexOf(book)+1) + ". " + book + "\n";
        }
        return allBooks;
    }
}
