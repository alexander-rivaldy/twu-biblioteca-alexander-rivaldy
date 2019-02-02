package com.twu.biblioteca;

/**
 * Created by alexa on 2/02/2019.
 */
public class BookNotValidForReturnException extends Exception {
    public BookNotValidForReturnException(String errorMessage){
        super(errorMessage);
    }
}
