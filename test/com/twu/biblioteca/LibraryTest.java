package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by alexa on 1/02/2019.
 */
public class LibraryTest {

    Library library;

    @Before
    public void setUp(){
        library = new Library();
    }

    @Test
    public void shouldPrintOneBookTitleWhenThereIsOneBook(){
        library.addBook("Title1");
        assertThat(library.getAllBookTitle(), is("1. Title1\n"));
    }

    @Test
    public void shouldPrintBothBookTitleWhenThereAreTwoBooks(){
        library.addBook("Title1");
        library.addBook("Title2");
        assertThat(library.getAllBookTitle(), is("1. Title1\n2. Title2\n"));
    }

    @Test
    public void shouldGiveMessageWhenThereIsNoBook(){
        assertThat(library.getAllBookTitle(), is("There are no books!"));
    }

}
