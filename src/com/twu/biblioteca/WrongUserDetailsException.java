package com.twu.biblioteca;

/**
 * Created by alexa on 4/02/2019.
 */
public class WrongUserDetailsException extends Exception {
    public WrongUserDetailsException (String message) {
        super(message);
    }
}
