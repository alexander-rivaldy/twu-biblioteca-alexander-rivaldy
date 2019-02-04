package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by alexa on 1/02/2019.
 */
public class MainMenuTest {

    MainMenu menu;

    @Before
    public void setUp(){
        menu = new MainMenu();
    }

    @Rule
    public final ExpectedException failure = ExpectedException.none();

    /*
        ExpectedSystemExit was initially going to be used to test whether
        the input 0 would close the program, but the current JUnit implemented
        in this app does not support it
     */
//    @Rule
//    public final ExpectedSystemExit exit = ExpectedSystemExit.none();


    @Test
    public void printingMenu() {
        assertThat(menu.getMenuOptions(),
                is("0. Quit\n1. List of Books\n2. Checkout Book\n3. Return Book\n4. List of Movies\n"));
    }

    @Test
    public void correctOption() throws Exception{
        //1 is List of Books, an option that will always be in the menu
        assertThat(menu.checkOption(1), is(true));
    }

    @Test
    public void wrongOptionShouldThrowWrongMenuOptionException() throws Exception{
        failure.expect(WrongMenuOptionException.class);
        menu.checkOption(-1);
    }

    @Test
    public void wrongOptionShouldShowAMessageToCustomer() throws Exception{
        failure.expect(WrongMenuOptionException.class);
        failure.expectMessage("Please select a valid option!");
        menu.checkOption(-1);
    }






}
