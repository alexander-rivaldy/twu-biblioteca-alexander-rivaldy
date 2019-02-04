package com.twu.biblioteca;

/**
 * Created by alexa on 2/02/2019.
 */
public class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String errorMessage){
        super(errorMessage);
    }

}
