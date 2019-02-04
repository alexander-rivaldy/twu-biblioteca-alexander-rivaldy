package com.twu.biblioteca;

/**
 * Created by alexa on 2/02/2019.
 */
public class ItemNotValidForReturnException extends Exception {
    public ItemNotValidForReturnException(String errorMessage){
        super(errorMessage);
    }
}
