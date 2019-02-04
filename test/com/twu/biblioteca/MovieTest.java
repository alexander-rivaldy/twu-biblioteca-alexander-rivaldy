package com.twu.biblioteca;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by alexa on 4/02/2019.
 */
public class MovieTest {

    Movie movie;

    @Rule
    public final ExpectedException failure = ExpectedException.none();

    @Test
    public void shouldPrintAllDetailsOfAMovie(){
        movie = new Movie("Title 1", "2001", "Director 1", 10);
        assertThat(movie.getFullDetail(),
                is(" Title 1              | 2001 | Director 1      | 10      |"));
    }

    @Test
    public void shouldPrintUnratedWhenMovieHasntBeenRated(){
        movie = new Movie("Title 1", "2001", "Director 1");
        assertThat(movie.getFullDetail(),
                is(" Title 1              | 2001 | Director 1      | Unrated |"));
    }

    @Test
    public void shouldPrintErrorMessageWhenMovieHasNoDetailsNull(){
        movie = new Movie(null,null,null);
        assertThat(movie.getFullDetail(), is("Error!!! Movie has no details"));
    }

    @Test
    public void shouldPrintErrorMessageWhenMovieHasNoDetailsEmpty(){
        movie = new Movie("","","");
        assertThat(movie.getFullDetail(), is("Error!!! Movie has no details"));
    }





}
