package com.twu.biblioteca;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sun.tools.tree.ByteExpression;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by alexa on 4/02/2019.
 */
public class LoginSystemTest {

    static LoginSystem login;
    ByteArrayOutputStream outputContent;
    PrintStream outputStream;

    @BeforeClass
    public static void setUp() {
        login = new LoginSystem();

        Customer cust = new Customer("123-4567", "name", "0410123456", "test@email.com", "pass");

        login.addUser(cust);
    }


    //unsuccessful login will loop till there is a right combination input
    @Test
    public void successfulLoginProcessShouldReturnTrue() {

        String data = "123-4567\npass\n";

        assertThat(login.loginProcess(new Scanner(data), new PrintStream(new ByteArrayOutputStream())),
                is(true));

    }

    @Test
    public void successfullyOutputTheRightMessageToPromptForInput() {
        outputContent = new ByteArrayOutputStream();
        outputStream = new PrintStream(outputContent);

        String data = "123-4567\npass\n";

        String expected = "Enter library number (xxx-xxxx): \nEnter password: \n";

        login.loginProcess(new Scanner(data), outputStream);

        assertThat(outputContent.toString(), is(expected));

    }

    @Test
    public void successfullyOutputErrorMessageAndPromptsAgainWhenUserPutInWrongDetails() {
        outputContent = new ByteArrayOutputStream();
        outputStream = new PrintStream(outputContent);

        String data = "123-4567\nwrongpassword\n123-4567\npass\n";

        String expected = "Enter library number (xxx-xxxx): \nEnter password: \n" +
                "Wrong library number and/or password inputted, please try again!\n" +
                "Enter library number (xxx-xxxx): \nEnter password: \n";

        login.loginProcess(new Scanner(data), outputStream);

        assertThat(outputContent.toString(), is(expected));



    }


}
