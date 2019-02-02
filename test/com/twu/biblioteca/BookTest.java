package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by alexa on 2/02/2019.
 */
public class BookTest {

    Book book;


    @Test
    public void shouldPrintAllDetailsOfABook(){
        book = new Book("Title 1", "Author 1", "2001");
        assertThat(book.getFullDetail(),
                is(" Title 1              | Author 1        | 2001 |"));
    }

    @Test
    public void shouldPrintErrorMessageWhenBookHasNoDetailsNull(){
        book = new Book(null,null,null);
        assertThat(book.getFullDetail(), is("Error!!! Book has no details"));
    }

    @Test
    public void shouldPrintErrorMessageWhenBookHasNoDetailsEmpty(){
        book = new Book("","","");
        assertThat(book.getFullDetail(), is("Error!!! Book has no details"));
    }

    @Test
    @Ignore
    public void shouldPrintUnknownWhenDetailIsNotComplete(){
        book = new Book("Title 2", null, "2000");
        assertThat(book.getFullDetail(),
                is(" Title 2              | Unknown         | 2000 |"));
    }

    @Test
    @Ignore
    public void successCheckForYear(){

    }

    @Test
    @Ignore
    public void failedCheckForYear(){

    }


}
