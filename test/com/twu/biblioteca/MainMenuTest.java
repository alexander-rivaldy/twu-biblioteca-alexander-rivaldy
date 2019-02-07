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
        method = MainMenu.class.getDeclaredMethod("checkOption", Integer.class);
        method.setAccessible(true);

    }

    /*
        ExpectedSystemExit was initially going to be used to test whether
        the input 0 would close the program, but the current JUnit implemented
        in this app does not support it
     */
//    @Rule
//    public final ExpectedSystemExit exit = ExpectedSystemExit.none();


    @Test
    public void correctOption() throws Exception{
        actualValue = method.invoke(menu, 1);

        //1 is List of Books, an option that will always be in the menu
        assertThat(actualValue.toString(), is("true"));
    }

    @Test
    public void wrongOptionShouldThrowWrongMenuOptionException() throws Exception{
        try {
            method.invoke(menu, -1);
            fail("should have thrown an exception");
        } catch (InvocationTargetException e) {
            assertThat(e.getCause(), instanceOf(WrongMenuOptionException.class));
        }
    }

    @Test
    public void wrongOptionShouldShowAMessageToCustomer() throws Exception{
        try {
            method.invoke(menu, -1);
            fail("should have thrown an exception");
        } catch (InvocationTargetException e) {
            assertThat(e.getCause().getMessage(), is("Please select a valid option!"));
        }
    }






}
