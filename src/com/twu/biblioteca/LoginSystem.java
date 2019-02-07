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

    public boolean loginProcess(Scanner reader){
        String libNum;
        String pass;
        while(currentUser == null){
            try{

                System.out.println("Enter library number (xxx-xxxx): ");
                libNum = reader.nextLine();
                System.out.println("Enter password: ");
                pass = reader.nextLine();

                currentUser = checkCredentials(libNum,pass);
            }
            catch (WrongUserDetailsException e){
                System.out.println("\n" + e.getMessage() + "\n");
            }
        }
        return true;

    }

    public void addUser(Customer customer){
        customers.add(customer);
    }

    public Customer getActiveUser(){ return currentUser; }

    private Customer checkCredentials(String libraryNumber, String password) throws WrongUserDetailsException{

        for(Customer cust : customers){
            if(cust.getLibraryNumber().equals(libraryNumber))
                if(cust.getPassword().equals(password))
                    return cust;
        }

        throw new WrongUserDetailsException("Failed to login. Either the username or password is incorrect.");
    }


}
