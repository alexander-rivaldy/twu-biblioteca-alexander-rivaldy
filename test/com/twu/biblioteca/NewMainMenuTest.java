package com.twu.biblioteca;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NewMainMenuTest {

    static NewMainMenu mainMenu;
    static ByteArrayOutputStream outputContent;
    static PrintStream outputStream;

    String welcomeMessage = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n";

    String loginMessage = "Enter library number (xxx-xxxx): \nEnter password: \n";

    String listItems =
            "\nChoose an option from the list below: \n\n" +
                    "0. Quit\n" +
                    "1. Show User Info\n" +
                    "2. List of Books\n" +
                    "3. Checkout Book\n" +
                    "4. Return Book\n" +
                    "5. List of Movies\n" +
                    "6. Checkout Movie\n" +
                    "7. List of Borrowed Books\n";

    String promptUser = "\nEnter your desired option: ";

    String userDetails = "" +
            "\n" +
            "Library Number: 123-4567\n" +
            "Name: Alexander Rivaldy\n" +
            "Phone: 0410123456\n" +
            "Email: alexanderrivaldy@gmail.com\n" +
            "\n\n";

    String listOfBooks = "" +
            " | No | Title                | Author          | Year |\n" +
            "---------------------------------------------------------\n" +
            " | 1  | Test Driven Developm | Kent Beck       | 2003 |\n" +
            " | 2  | The People Vs Tech   | Jamie Bartlett  | 2018 |\n" +
            " | 3  | The Alchemist        | Paulo Coelho    | 1988 |\n\n";

    String nonIntegerError = "Value was not a number, please enter again\n\n";

    String nonInListError = "Value not within options, please enter again\n\n";

    @BeforeClass
    public static void setUp() {
        outputContent = new ByteArrayOutputStream();
        outputStream = new PrintStream(outputContent);

    }

    @After
    public void cleanUp() {
        outputContent.reset();
    }


    /*
    These test cases are still not the most optimal, since the data is not provided by the test case itself
     */
    @Test
    public void successfulRunUserDetails() {

        mainMenu = new NewMainMenu(outputStream, new Scanner("123-4567\npassword\n1\n0\n"));
        mainMenu.run();

        String expected = welcomeMessage + loginMessage + listItems + promptUser + userDetails + listItems + promptUser;

        assertThat(outputContent.toString(), is(expected));
    }

    @Test
    public void successfulRunListOfBooks() {

        mainMenu = new NewMainMenu(outputStream, new Scanner("123-4567\npassword\n2\n0\n"));
        mainMenu.run();

        String expected = welcomeMessage + loginMessage + listItems + promptUser + listOfBooks + listItems + promptUser;

        assertThat(outputContent.toString(), is(expected));
    }

}
