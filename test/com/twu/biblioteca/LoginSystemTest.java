package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by alexa on 4/02/2019.
 */
public class LoginSystemTest {

    LoginSystem login;

    @Before
    public void setUp(){
        login = new LoginSystem();
    }

    @Test
    public void successfulPairingLibNumberAndPassword(){
        Customer cust = new Customer("123-4567","name", "0410123456", "test@email.com", "pass");

        login.addUser(cust);

        assertThat(login.checkCredentials("123-4567", "pass"), is(cust));


    }


}
