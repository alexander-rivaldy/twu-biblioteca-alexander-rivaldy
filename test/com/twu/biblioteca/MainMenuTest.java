package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by alexa on 1/02/2019.
 */
public class MainMenuTest {

    MainMenu menu;
    Method method;
    Object actualValue;

    @Before
    public void setUp() throws Exception{

        menu = new MainMenu();

    }

    /*
        ExpectedSystemExit was initially going to be used to test whether
        the input 0 would close the program, but the current JUnit implemented
        in this app does not support it
     */
//    @Rule
//    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void correctMenuOptionsPrinted() throws Exception{
        method = MainMenu.class.getDeclaredMethod("getMenuOptions");
        method.setAccessible(true);
        actualValue = method.invoke(menu);

        String expected = "" +
                "0. Quit\n" +
                "1. Show User Info\n" +
                "2. List of Books\n" +
                "3. Checkout Book\n" +
                "4. Return Book\n" +
                "5. List of Movies\n" +
                "6. Checkout Movie\n" +
                "7. List of Borrowed Books\n";

        assertThat(actualValue.toString(), is(expected));
    }







}
