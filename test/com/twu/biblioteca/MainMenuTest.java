package com.twu.biblioteca;

import org.junit.Before;
import org.junit.BeforeClass;
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
    public ExpectedException failure = ExpectedException.none();

    @Test
    public void printingMenu() {
        assertThat(menu.getMenuOptions(),
                is("1. List of Books\n"));
    }

    @Test
    public void correctOption() throws Exception{
        //1 is List of Books, an option that will always be in the menu
        assertThat(menu.chooseOption(1), is(true));
    }

    @Test
    public void wrongOptionShouldThrowWrongMenuOptionException() throws Exception{
        failure.expect(WrongMenuOptionException.class);
        menu.chooseOption(2);
    }

    @Test
    public void wrongOptionShouldShowAMessageToCustomer() throws Exception{
        failure.expect(WrongMenuOptionException.class);
        failure.expectMessage("Please select a valid option!");
        menu.chooseOption(2);
    }

    @Test
    public void wrongInputTypeShouldThrowWrongMenuOptionException() throws Exception{
        failure.expect(WrongMenuOptionException.class);
        menu.chooseOption("a");
    }





}
