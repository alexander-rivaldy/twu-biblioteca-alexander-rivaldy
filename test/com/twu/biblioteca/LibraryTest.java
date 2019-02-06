package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static com.twu.biblioteca.Library.*;
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
        assertThat(library.getLibraryItems(BOOKS),
                is(" | 1  | Title 1              | Author 1        | 2000 |\n"));
    }
    @Test
    public void shouldPrintBothBookDetailsWhenThereAreTwoBooks(){
        library.addBook(new Book("Title 1", "Author 1", "2000"));
        library.addBook(new Book("Title 2", "Author 2", "2002"));
        assertThat(library.getLibraryItems(BOOKS),
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

        assertThat(library.getLibraryItems(BOOKS), is(expected));
    }

    @Test
    public void shouldReturnTheCorrectBookAccordingToTitle() throws Exception{
        Book book = new Book("Title 6", "Author 6", "2019");

        library.addBook(new Book("Title 5", "Author 5", "2019"));
        library.addBook(book);
        library.addBook(new Book("Title 7", "Author 7", "2019"));

        assertThat(library.findItem("Title 6", BOOKS).getFullDetail(),
                is(book.getFullDetail()));
    }

    @Test
    public void shouldThrowItemNotFoundExceptionWhenThereIsNoBookWithDesiredTitle() throws Exception{
        failure.expect(ItemNotFoundException.class);
        library.findBook("Title");
    }

    @Test
    public void shouldGetUserInputAndCheckOutABookCorrectly() throws Exception{
        Book wantToBorrow = new Book("Title 9", "Author 9", "2019");
        library.addBook(wantToBorrow);

        String data = "Title 9";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        library.borrowProcess(new Scanner(System.in), null, BOOKS);

        assertThat(wantToBorrow.isAvailable(), is(false));

    }

    @Test
    public void shouldGetUserInputAndReturnABookCorrectly() throws Exception{
        Book wantToBorrow = new Book("Title 10", "Author 9", "2019", false);
        library.addBook(wantToBorrow);

        String data = "Title 10";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        library.borrowProcess(new Scanner(System.in), null, BOOKS);

        assertThat(wantToBorrow.isAvailable(), is(false));

    }

    @Test
    public void shouldShowBorrowedBookWithLibraryNumber(){
        Book book =  new Book("Title 1", "Author 1", "2019", false);
        Customer customer = new Customer("123-4567","name name", "phone", "email", "pass");
        book.setBorrowedBy(customer);

        Book book2 =  new Book("Title 2", "Author 2", "2019", false);
        Customer customer2 = new Customer("123-4568","name name", "phone", "email", "pass");
        book2.setBorrowedBy(customer2);

        library.addBook(book);
        library.addBook(book2);

        String expected =
                " | 1  | 123-4567 | Title 1              | Author 1        | 2019 |\n" +
                " | 2  | 123-4568 | Title 2              | Author 2        | 2019 |\n";

        assertThat(library.printBorrowedBooks(), is(expected));

    }

    /**
     * The tests below are for a list of movies
     */

    @Test
    public void shouldPrintOneMovieDetailsWhenThereIsOneBook(){
        library.addMovie(new Movie("Title 1", "2000", "Director 1", 10));
        assertThat(library.getLibraryItems(MOVIES),
                is(" | 1  | Title 1              | 2000 | Director 1      | 10      |\n"));
    }

    @Test
    public void shouldPrintBothMovieDetailsWhenThereAreTwoBooks(){
        library.addMovie(new Movie("Title 1", "2000", "Director 1", 1));
        library.addMovie(new Movie("Title 2", "2002", "Director 2", 5));
        assertThat(library.getLibraryItems(MOVIES),
                is(" | 1  | Title 1              | 2000 | Director 1      | 1       |\n" +
                   " | 2  | Title 2              | 2002 | Director 2      | 5       |\n"));
    }

    @Test
    public void shouldPrintOnlyAvailableMovies(){
        library.addMovie(new Movie("Title 2", "2000", "Director 2", false));
        library.addMovie(new Movie("Title 3", "2000", "Director 3", true));
        library.addMovie(new Movie("Title 4", "2000", "Director 4", true));

        String expected =
                " | 1  | Title 3              | 2000 | Director 3      | Unrated |\n" +
                " | 2  | Title 4              | 2000 | Director 4      | Unrated |\n";

        assertThat(library.getLibraryItems(MOVIES), is(expected));
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

    @Test
    public void shouldReturnTheCorrectMovieAccordingToTitle() throws Exception{
        Movie movie = new Movie("Title 2", "2019", "Director 2");

        library.addMovie(new Movie("Title 1", "2019", "Director 1"));
        library.addMovie(movie);
        library.addMovie(new Movie("Title 3", "2019", "Director 3"));

        assertThat(library.findItem("Title 2", MOVIES).getFullDetail(),
                is(movie.getFullDetail()));
    }

    @Test
    public void shouldThrowItemNotFoundExceptionWhenThereIsNoMovieWithDesiredTitle() throws Exception{
        failure.expect(ItemNotFoundException.class);
        library.findMovie("Title");
    }

    @Test
    public void shouldGetUserInputAndCheckOutAMovieCorrectly() throws Exception{
        Movie wantToBorrow = new Movie("Title 1", "2000", "Director 1");
        library.addMovie(wantToBorrow);

        String data = "Title 1";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        library.borrowProcess(new Scanner(System.in), null, MOVIES);

        assertThat(wantToBorrow.isAvailable(), is(false));

    }

    @Test
    public void shouldReturnOnlyBooks(){
        library.addBook(new Book("Title 1", "Author 1", "2000"));
        library.addMovie(new Movie("Title 3", "2003", "Director 3", 7));
        assertThat(library.getLibraryItems(BOOKS),
                is(" | 1  | Title 1              | Author 1        | 2000 |\n"));

    }



}
