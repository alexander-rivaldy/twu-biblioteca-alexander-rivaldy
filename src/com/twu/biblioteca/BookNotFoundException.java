package com.twu.biblioteca;

/**
 * Created by alexa on 2/02/2019.
 */
public class BookNotFoundException extends Exception {
    public BookNotFoundException(String errorMessage){
        super(errorMessage);
    }

}
