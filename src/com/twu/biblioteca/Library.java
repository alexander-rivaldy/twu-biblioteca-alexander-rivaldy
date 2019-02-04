package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by alexa on 1/02/2019.
 */
public class Library {

    static final int MAX_COLUMN_BOOK = 57;
    static final int MAX_COLUMN_MOVIE = 65;

    ArrayList<Book> books;
    ArrayList<Movie> movies;

    public Library(){
        books = new ArrayList<Book>();
        movies = new ArrayList<Movie>();
    }

    public void addBook(Book book){
        books.add(book);
    }
    public void addMovie(Movie movie) { movies.add(movie); }

    /**
     * Constructs a string containing information of all available books
     * @return has the format of
     *       | 1  | Title 3              | Author 3        | 2001 |
     *       | 2  | Title 4              | Author 4        | 2002 |
     */
    public String getAvailableBooks(){
        String allBooks = "";
        String format = "";
        int counter = 1;
        for(Book book : books){
            if (!book.isAvailable())
                continue;
            format = String.format(" | %-2s |%-48s\n",
                    ""+ counter++,book.getFullDetail());
            allBooks += format;
        }

        return allBooks;

    }

    public String getAvailableMovies(){
        String allMovies = "";
        String format = "";
        int counter = 1;
        for(Movie movie : movies){
            if (!movie.isAvailable())
                continue;
            format = String.format(" | %-2s |%-48s\n",
                    ""+ counter++,movie.getFullDetail());
            allMovies += format;
        }

        return allMovies;
    }


    /**
     * Adds a column on top of list of books
     * @return has the format of
     *    | No | Title                | Author          | Year |
     *   ---------------------------------------------------------
     *       followed by the list of books
     */
    public String getAllBookDetailsWithColumn(){
        String allBooksWithColumn = String.format(" | %-2s | %-20s | %-15s | %4s |\n",
                "No", "Title", "Author", "Year");
        for(int i=0; i<MAX_COLUMN_BOOK; i++){
            allBooksWithColumn += "-";
        }
        return allBooksWithColumn + "\n" + getAvailableBooks() ;
    }

    public String getAllMovieDetailsWithColumn(){
        String allMoviesWithColumn = String.format(" | %-2s | %-20s | %-4s | %-15s | %-7s |\n",
                "No", "Title", "Year", "Director", "Rating");
        for(int i=0; i<MAX_COLUMN_MOVIE; i++){
            allMoviesWithColumn += "-";
        }
        return allMoviesWithColumn + "\n" + getAvailableMovies() ;
    }


    /**
     * function that handles all borrowing logic
      * @param reader to make sure that the app only uses 1 Scanner
     * @return status of the checkout
     */
    public String borrowBookProcess(Scanner reader){
        String title = askForTitle(reader);
        try{
            findBook(title).borrow();
        }
        //Exception could be ItemCannotBeCheckedOutException or ItemNotFoundException
        catch(Exception e){
            return "\nSorry, that book is not available\n";
        }

        return "\nThank you! Enjoy the book\n";
    }

    public String borrowMovieProcess(Scanner reader){
        String title = askForTitle(reader);
        try{
            findMovie(title).borrow();
        }
        //Exception could be ItemCannotBeCheckedOutException or ItemNotFoundException
        catch(Exception e){
            return "\nSorry, that movie is not available\n";
        }

        return "\nThank you! Enjoy the book\n";
    }

    /**
     * function that handles all returning logic
     * @param reader to make sure that the app only uses 1 Scanner
     * @return status of the return
     */
    public String returnProcess(Scanner reader){
        String title = askForTitle(reader);
        try{
            findBook(title).returnBook();
        }
        //Exception could be BookNotValidForReturnException or ItemNotFoundException
        catch(Exception e){
            return "\nThat is not a valid book to return\n";
        }
        return "\nThank you for returning the book\n";
    }

    /**
     * Function to get user input on book title
     * @param reader to make sure that the app only uses 1 Scanner
     * @return the user input
     */
    public String askForTitle(Scanner reader){
        System.out.print("Enter title of book: ");
        String input = reader.nextLine();
        return input;
    }

    /**
     * Function to iterate through the Book ArrayList and find a book with
     * certain title. If it is not found, it will throw an Exception
     * @param title title of the book to be found
     * @return the Book object
     * @throws ItemNotFoundException when book is not found
     */
    public Book findBook(String title) throws ItemNotFoundException {
        for(Book book : books){
            if(book.getTitle().equals(title))
                return book;
        }
        throw new ItemNotFoundException("Sorry, that book is not available");
    }

    public Movie findMovie(String title) throws ItemNotFoundException {
        for(Movie movie : movies){
            if(movie.getTitle().equals(title))
                return movie;
        }
        throw new ItemNotFoundException("Sorry, that movie is not available");
    }








}
