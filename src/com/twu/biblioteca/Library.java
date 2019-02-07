package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by alexa on 1/02/2019.
 */
public class Library {

    private static final int MAX_COLUMN_BOOK = 57;
    private static final int MAX_COLUMN_MOVIE = 65;
    private static final int MAX_COLUMN_BORROWED_BOOK = 70;

    static final int BOOKS = 1;
    static final int MOVIES = 2;

    static final String BOOK = "Book";

    private ArrayList<Book> books;
    private ArrayList<Movie> movies;

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
     * @return
     *      if type is BOOKS, then the format will be
     *       | 1  | Title 3              | Author 3        | 2001 |
     *       | 2  | Title 4              | Author 4        | 2002 |
     *      if type is MOVIES, then the format will be
     *       | 1  | Title 1              | 2000 | Director 1      | 1       |
     *       | 2  | Title 2              | 2002 | Director 2      | 5       |
     */

    private String retrieveLibraryItemsInformation(ArrayList<? extends LibraryItem> items){

        String details = "";
        String format;
        int counter = 1;

        for(LibraryItem libraryItem : items){
            if (!libraryItem.isAvailable())
                continue;
            format = String.format(" | %-2s |%-48s\n",
                    ""+ counter++,libraryItem.getFullDetail());
            details += format;
        }

        return details;
    }

    public String getLibraryItems(int type){
        switch(type){
            case BOOKS:
                return retrieveLibraryItemsInformation(books);
            case MOVIES:
                return retrieveLibraryItemsInformation(movies);
        }
        return "";
    }


    public String printBorrowedBooks(){
        String borrowed = "";
        String format = "";
        int counter = 1;
        for(Book book : books){
            if (book.isAvailable())
                continue;
            format = String.format(" | %-2s |%-48s\n",
                    ""+ counter++,book.getFullDetail());
            borrowed += format;
        }

        return borrowed;
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
        return allBooksWithColumn + "\n" + getLibraryItems(BOOKS) ;
    }

    public String getAllMovieDetailsWithColumn(){
        String allMoviesWithColumn = String.format(" | %-2s | %-20s | %-4s | %-15s | %-7s |\n",
                "No", "Title", "Year", "Director", "Rating");
        for(int i=0; i<MAX_COLUMN_MOVIE; i++){
            allMoviesWithColumn += "-";
        }
        return allMoviesWithColumn + "\n" + getLibraryItems(MOVIES) ;
    }

    public String getBorrowedBooksWithColumn(){
        String borrowedBooksWithColumn = String.format(" | %-2s | %-8s | %-20s | %-15s | %4s |\n",
                "No", "Lib Num", "Title", "Author", "Year");
        for(int i=0; i<MAX_COLUMN_BORROWED_BOOK; i++){
            borrowedBooksWithColumn += "-";
        }
        return borrowedBooksWithColumn + "\n" + printBorrowedBooks() ;
    }
    

    /**
     * function that handles all borrowing logic
     * @param reader to make sure that the app only uses 1 Scanner
     * @param customer to record who borrowed the library item
     * @param type either BOOKS or MOVIES
     * @return status of checkout
     */
    public String borrowProcess(Scanner reader, Customer customer, int type){
        String title = askForTitle(reader);
        String itemType;

        if(type == BOOKS)
            itemType = "book";
        else
            itemType = "movie";


        try{
            findItem(title, type).borrowItem(customer);
        }
        //Exception could be ItemCannotBeCheckedOutException or ItemNotFoundException
        catch(Exception e){
            return "\nSorry, that "+ itemType + " is not available\n";
        }

        return "\nThank you! Enjoy the "+ itemType + "\n";


    }

    /**
     * function that handles all returning logic
     * @param reader to make sure that the app only uses 1 Scanner
     * @return status of the return
     */
    public String returnProcess(Scanner reader){
        String title = askForTitle(reader);
        try{
            findItem(title, BOOKS).returnItem();
        }
        //Exception could be ItemNotValidForReturnException or ItemNotFoundException
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
     * Function to find a specific library item with title.
     * It will iterate through either books or movies, depending on the
     * parameter type
     * @param title title of book or movie to be found
     * @param libraryItems which ArrayList wants to be iterated through
     * @return a LibraryItem or null if not found
     */
    public LibraryItem findItem(String title, ArrayList<? extends LibraryItem> libraryItems){
        for(LibraryItem libraryItem : libraryItems){
            if(libraryItem.getTitle().equals(title))
                return libraryItem;
        }
        return null;
    }

    public LibraryItem findItem(String title, int type){
        switch(type){
            case BOOKS:
                return findItem(title, books);
            case MOVIES:
                return findItem(title, movies);
        }
        return null;

    }









}
