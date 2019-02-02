package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by alexa on 1/02/2019.
 */
public class LibraryTest {

    Library library;

    @Rule
    public final ExpectedException failure = ExpectedException.none();

    @Before
    public void setUp(){
        library = new Library();
    }

    @Test
    public void shouldPrintOneBookDetailsWhenThereIsOneBook(){
        library.addBook(new Book("Title 1", "Author 1", "2000"));
        assertThat(library.getAvailableBooks(),
                is(" | 1  | Title 1              | Author 1        | 2000 |\n"));
    }
    @Test
    public void shouldPrintBothBookDetailsWhenThereAreTwoBooks(){
        library.addBook(new Book("Title 1", "Author 1", "2000"));
        library.addBook(new Book("Title 2", "Author 2", "2002"));
        assertThat(library.getAvailableBooks(),
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

    @Test
    public void shouldPrintOnlyAvailableBooks(){
        library.addBook(new Book("Title 2", "Author 2", "2000", false)) ;
        library.addBook(new Book("Title 3", "Author 3", "2001", true)) ;
        library.addBook(new Book("Title 4", "Author 4", "2002", true)) ;

        String expected =
                " | 1  | Title 3              | Author 3        | 2001 |\n" +
                " | 2  | Title 4              | Author 4        | 2002 |\n";

        assertThat(library.getAvailableBooks(), is(expected));
    }

    @Test
    public void shouldReturnTheCorrectBookAccordingToTitle() throws Exception{
        library.addBook(new Book("Title 5", "Author 5", "2019"));
        library.addBook(new Book("Title 6", "Author 6", "2019"));
        library.addBook(new Book("Title 7", "Author 7", "2019"));

        assertThat(library.findBook("Title 6").getTitle(), is("Title 6"));
    }

    @Test
    public void shouldThrowBookNotFoundExceptionWhenThereIsNoBookWithDesiredTitle() throws Exception{
        failure.expect(BookNotFoundException.class);
        library.findBook("Title");
    }

    @Test
    public void shouldFindDesiredBookAndMakeItNotAvailable(){
        Book book = new Book("Title 8", "Author 8", "2019");
        library.borrowBook(book);
        assertThat(book.isAvailable(), is(false));
    }

    @Test
    public void shouldGetUserInputAndCheckOutABookCorrectly(){
        Book wantToBorrow = new Book("Title 11", "Author 11", "2019");
        library.addBook(new Book("Title 9", "Author 9", "2019"));
        library.addBook(new Book("Title 10", "Author 10", "2019"));
        library.addBook(wantToBorrow);

        String data = "Title 11";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        library.borrowProcess(new Scanner(System.in));

        assertThat(wantToBorrow.isAvailable(), is(false));

    }



}
