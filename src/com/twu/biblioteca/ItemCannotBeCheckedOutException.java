package com.twu.biblioteca;

/**
 * Created by alexa on 2/02/2019.
 */
public class ItemCannotBeCheckedOutException extends Exception {
    public ItemCannotBeCheckedOutException(String errorMessage){
        super(errorMessage);
    }}
