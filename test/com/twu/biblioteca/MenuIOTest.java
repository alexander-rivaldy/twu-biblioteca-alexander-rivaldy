package com.twu.biblioteca;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MenuIOTest {

    static HashMap<Integer, String> list;

    static MenuIO menuIO;

    static ByteArrayOutputStream outputContent;
    static PrintStream outputStream;

    @BeforeClass
    public static void setUp() {
        outputContent = new ByteArrayOutputStream();
        outputStream = new PrintStream(outputContent);

        menuIO = new MenuIO(outputStream, null);
        list = new HashMap<>();
    }

    @After
    public void cleanUp() {
        list.clear();
        outputContent.reset();
    }

    @Test
    public void successfulListPrintWithOneItem() {
        list.put(1, "Item 1");

        menuIO.printMenuOptions(list);

        String expected = "1. Item 1\n";

        assertThat(outputContent.toString(), is(expected));

    }

    @Test
    public void successfulListPrintWithTwoItems() {
        list.put(1, "Item 1");
        list.put(2, "Item 2");

        menuIO.printMenuOptions(list);

        String expected = "1. Item 1\n2. Item 2\n";

        assertThat(outputContent.toString(), is(expected));
    }

    @Test
    public void successfulPrintMessage() {
        menuIO.printMessage("printing a test message");

        assertThat(outputContent.toString(), is("printing a test message"));
    }

    @Test
    public void successfulUserOptionInput() {
        list.put(1, "Item 1");
        list.put(2, "Item 2");

        String data = "1\n";

        MenuIO menuIO = new MenuIO(outputStream, new Scanner(data));

        assertThat(menuIO.askOptionInput(list), is(1));
    }

    @Test
    public void askOptionInputPromptsUserInput() {
        list.put(1, "Item 1");

        String data = "1\n";

        MenuIO menuIO = new MenuIO(outputStream, new Scanner(data));
        menuIO.askOptionInput(list);

        String expected = "\nEnter your desired option: ";

        assertThat(outputContent.toString(), is(expected));
    }

    @Test
    public void askOptionInputOutputsErrorMessageWhenUserEntersNonIntegerValue() {
        list.put(1, "Item 1");

        String data = "a\n1\n";

        MenuIO menuIO = new MenuIO(outputStream, new Scanner(data));
        menuIO.askOptionInput(list);

        String expected = "\nEnter your desired option: " +
                "Value was not a number, please enter again\n\n" +
                "\nEnter your desired option: ";

        assertThat(outputContent.toString(), is(expected));
    }

    @Test
    public void askOptionInputOutputsErrorMessageWhenUserEntersValueNotInList() {
        list.put(1, "Item 1");

        String data = "2\n1\n";

        MenuIO menuIO = new MenuIO(outputStream, new Scanner(data));
        menuIO.askOptionInput(list);

        String expected = "\nEnter your desired option: " +
                "Value not within options, please enter again\n\n" +
                "\nEnter your desired option: ";

        assertThat(outputContent.toString(), is(expected));
    }
}
