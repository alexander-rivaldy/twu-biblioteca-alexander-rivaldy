package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by alexa on 4/02/2019.
 */
public class LoginSystemTest {

    LoginSystem login;
    Method method;
    Object actualValue;

    @Rule
    public final ExpectedException failure = ExpectedException.none();

    @Before
    public void setUp() throws Exception{
        login = new LoginSystem();
        method = LoginSystem.class.getDeclaredMethod("checkCredentials", String.class, String.class);
        method.setAccessible(true);
    }

    @Test
    public void successfulCheckCredentials() throws Exception{
        Customer cust = new Customer("123-4567","name", "0410123456", "test@email.com", "pass");

        login.addUser(cust);

        actualValue = method.invoke(login, "123-4567", "pass");

        assertThat(actualValue.toString(), is(cust.toString()));


    }

    @Test
    public void unsuccessfulCheckCredentialsShouldReturnNull() throws Exception {
        actualValue = method.invoke(login, "","");
        assertThat(actualValue, is(nullValue()));

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
