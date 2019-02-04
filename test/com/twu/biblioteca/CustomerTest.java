package com.twu.biblioteca;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by alexa on 4/02/2019.
 */
public class CustomerTest {

    Customer cust;

    @Rule
    public final ExpectedException failure = ExpectedException.none();


    @Test
    public void shouldPrintCustomerDetailsCorrectly(){
        cust = new Customer("123-1234","name","0410123456", "test@email.com", "");
        String expected =
                "\n" +
                "Library Number: 123-1234\n" +
                "Name: name\n" +
                "Phone: 0410123456\n" +
                "Email: test@email.com\n" +
                "\n";
        assertThat(cust.printDetails(), is(expected));

    }

    @Test
    public void shouldThrowWrongUserDetailsExceptionWithLibNumberError() throws Exception{
        failure.expect(WrongUserDetailsException.class);
        failure.expectMessage("Wrong library number format.");
        cust = new Customer();

        cust.checkLibNumberPattern("123-123");

    }

}
