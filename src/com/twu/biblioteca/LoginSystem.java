package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by alexa on 4/02/2019.
 */
public class LoginSystem {

    ArrayList<Customer> customers;
    Customer currentUser;

    public LoginSystem(){
        customers = new ArrayList<Customer>();
        currentUser = null;
    }

    public void addUser(Customer customer){
        customers.add(customer);
    }

    public void setActiveUser(Customer user){
        currentUser = user;
    }

    public Customer checkCredentials(String libraryNumber, String password){

        for(Customer cust : customers){
            if(cust.getLibraryNumber().equals(libraryNumber))
                if(cust.getPassword().equals(password))
                    return cust;
        }

        return null;
    }


}
