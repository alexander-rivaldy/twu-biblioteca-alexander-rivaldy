package com.twu.biblioteca;

/**
 * Created by alexa on 4/02/2019.
 */
public class Customer {

    String name;
    String phone;
    String email;

    public Customer(String name, String phone, String email){
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String printDetails(){
        String details =
                "\n" +
                "Name: " + name + "\n" +
                "Phone: " + phone + "\n" +
                "Email: " + email + "\n" +
                "\n" ;

        return details;
    }

}
