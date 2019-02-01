package com.twu.biblioteca;

/**
 * Created by alexa on 1/02/2019.
 */
public class WrongMenuOptionException extends Exception {
    public WrongMenuOptionException(String errorMessage){
        super(errorMessage);
    }
}
