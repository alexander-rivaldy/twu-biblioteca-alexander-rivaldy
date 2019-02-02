package com.twu.biblioteca;

/**
 * Created by alexa on 2/02/2019.
 */
public class BookCannotBeCheckedOutException extends Exception {
    public BookCannotBeCheckedOutException(String errorMessage){
        super(errorMessage);
    }}
