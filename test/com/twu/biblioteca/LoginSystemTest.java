package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by alexa on 4/02/2019.
 */
public class LoginSystemTest {

    LoginSystem login;

    @Rule
    public final ExpectedException failure = ExpectedException.none();

    @Before
    public void setUp(){
        login = new LoginSystem();
    }

    @Test
    public void successfulCheckCredentials() throws Exception{
        Customer cust = new Customer("123-4567","name", "0410123456", "test@email.com", "pass");

        login.addUser(cust);

        assertThat(login.checkCredentials("123-4567", "pass"), is(cust));


    }

    @Test
    public void unsuccessfulCheckCredentialsShouldThrowWrongUserDetailsException() throws Exception{
        failure.expect(WrongUserDetailsException.class);

        login.checkCredentials("","");
    }


    //unsuccessful login will loop till there is a right combination input
    @Test
    public void successfulLoginProcessShouldReturnTrue(){
        Customer cust = new Customer("123-4567","name", "0410123456", "test@email.com", "pass");

        login.addUser(cust);

        String data = "123-4567\npass\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        assertThat(login.loginProcess(new Scanner(System.in)), is(true));

    }



}
