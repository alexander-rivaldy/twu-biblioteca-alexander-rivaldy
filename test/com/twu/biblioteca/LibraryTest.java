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
    public void shouldPrintOneBookDetailsWhenThereIsOneBook(){
        library.addBook(new Book("Title 1", "Author 1", "2000"));
        assertThat(library.getAllBookDetails(),
                is(" | 1  | Title 1              | Author 1        | 2000 |\n"));
    }
    @Test
    public void shouldPrintBothBookDetailsWhenThereAreTwoBooks(){
        library.addBook(new Book("Title 1", "Author 1", "2000"));
        library.addBook(new Book("Title 2", "Author 2", "2002"));
        assertThat(library.getAllBookDetails(),
                is(" | 1  | Title 1              | Author 1        | 2000 |\n" +
                   " | 2  | Title 2              | Author 2        | 2002 |\n"));
    }

    @Test
    public void shouldPrintBookDetailsWithColumns(){
        library.addBook(new Book("Title 3", "Author 3", "2019"));
        //60
        String expected =
                " | No | Title                | Author          | Year |\n" +
                "---------------------------------------------------------\n" +
                " | 1  | Title 3              | Author 3        | 2019 |\n";
        assertThat(library.getAllBookDetailsWithColumn(), is(expected));
    }

}
