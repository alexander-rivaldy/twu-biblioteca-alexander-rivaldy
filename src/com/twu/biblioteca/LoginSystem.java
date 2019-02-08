package com.twu.biblioteca;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by alexa on 4/02/2019.
 */
public class LoginSystem {

    ArrayList<Customer> customers;
    Customer currentUser;

    public LoginSystem() {
        customers = new ArrayList<Customer>();
        currentUser = null;
    }

    public boolean loginProcess(Scanner reader, PrintStream output) {
        String libNum;
        String pass;
        do {
            output.println("Enter library number (xxx-xxxx): ");
            libNum = reader.nextLine();
            output.println("Enter password: ");
            pass = reader.nextLine();

            currentUser = checkCredentials(libNum, pass);

            if(currentUser == null)
                output.println("Wrong library number and/or password inputted, please try again!");
        }
        while (currentUser == null);

        return true;

    }

    public void addUser(Customer customer) {
        customers.add(customer);
    }

    public Customer getActiveUser() {
        return currentUser;
    }


    private Customer checkCredentials(String libraryNumber, String password) {

        for (Customer cust : customers) {
            if (cust.getLibraryNumber().equals(libraryNumber))
                if (cust.getPassword().equals(password))
                    return cust;
        }

        return null;
    }


}
