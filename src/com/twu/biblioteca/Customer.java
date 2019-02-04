package com.twu.biblioteca;

/**
 * Created by alexa on 4/02/2019.
 */
public class Customer {

    public final String LIBRARY_NUMBER_PATTERN = "\\d{3}-\\d{4}";

    String libraryNumber;
    String name;
    String phone;
    String email;
    String password;

    public Customer(){};

    public Customer(String libraryNumber, String name, String phone, String email, String password){

        this.libraryNumber = libraryNumber;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;

    }

    public boolean checkLibNumberPattern(String libraryNumber) throws WrongUserDetailsException{
        if(!libraryNumber.matches(LIBRARY_NUMBER_PATTERN))
            throw new WrongUserDetailsException("Wrong library number format.");
        return true;
    }

    public String printDetails(){
        String details =
                "\n" +
                "Library Number: " + libraryNumber + "\n" +
                "Name: " + name + "\n" +
                "Phone: " + phone + "\n" +
                "Email: " + email + "\n" +
                "\n" ;

        return details;
    }

    public String getLibraryNumber(){ return libraryNumber; }
    public String getPassword(){ return password; }

}
