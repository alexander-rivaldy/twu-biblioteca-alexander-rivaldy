package com.twu.biblioteca;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by alexa on 4/02/2019.
 */
public class CustomerTest {

    Customer cust;


    @Test
    public void shouldPrintCustomerDetailsCorrectly(){
        cust = new Customer("name","0410123456", "test@email.com");
        String expected =
                "\n" +
                "Name: name\n" +
                "Phone: 0410123456\n" +
                "Email: test@email.com\n" +
                "\n";
        assertThat(cust.printDetails(), is(expected));

    }
}
