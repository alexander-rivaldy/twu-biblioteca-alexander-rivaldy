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

    /**
     * The tests below are for a list of books up until the next commenting
     */

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
    public void shouldGetUserInputAndCheckOutABookCorrectly() throws Exception{
        Book wantToBorrow = new Book("Title 9", "Author 9", "2019");
        library.addBook(wantToBorrow);

        String data = "Title 9";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        library.borrowProcess(new Scanner(System.in));

        assertThat(wantToBorrow.isAvailable(), is(false));

    }

    @Test
    public void shouldGetUserInputAndReturnABookCorrectly() throws Exception{
        Book wantToBorrow = new Book("Title 10", "Author 9", "2019", false);
        library.addBook(wantToBorrow);

        String data = "Title 10";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        library.borrowProcess(new Scanner(System.in));

        assertThat(wantToBorrow.isAvailable(), is(false));

    }

    /**
     * The tests below are for a list of movies
     */

    @Test
    public void shouldPrintOneMovieDetailsWhenThereIsOneBook(){
        library.addMovie(new Movie("Title 1", "2000", "Director 1", 10));
        assertThat(library.getAvailableMovies(),
                is(" | 1  | Title 1              | 2000 | Director 1      | 10      |\n"));
    }

    @Test
    public void shouldPrintBothMovieDetailsWhenThereAreTwoBooks(){
        library.addMovie(new Movie("Title 1", "2000", "Director 1", 1));
        library.addMovie(new Movie("Title 2", "2002", "Director 2", 5));
        assertThat(library.getAvailableMovies(),
                is(" | 1  | Title 1              | 2000 | Director 1      | 1       |\n" +
                        " | 2  | Title 2              | 2002 | Director 2      | 5       |\n"));
    }

    @Test
    public void shouldPrintMovieDetailsWithColumns(){
        library.addMovie(new Movie("Title 3", "2003", "Director 3", 7));
        //65
        String expected =
                " | No | Title                | Year | Director        | Rating  |\n" +
                "-----------------------------------------------------------------\n" +
                " | 1  | Title 3              | 2003 | Director 3      | 7       |\n";
        assertThat(library.getAllMovieDetailsWithColumn(), is(expected));
    }







}
