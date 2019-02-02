package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by alexa on 1/02/2019.
 */
public class Library {

    static final int MAX_COLUMN = 57;

    ArrayList<Book> books;

    public Library(){
        books = new ArrayList<Book>();
    }

    public void addBook(Book book){
        books.add(book);
    }

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
        for(int i=0; i<MAX_COLUMN; i++){
            allBooksWithColumn += "-";
        }
        return allBooksWithColumn + "\n" + getAvailableBooks() ;
    }


    /**
     * function that handles all borrowing logic
      * @param reader to make sure that the app only uses 1 Scanner
     * @return status of the checkout
     */
    public String borrowProcess(Scanner reader){
        String title = askForTitle(reader);
        try{
            findBook(title).borrow();
        }
        //Exception could be BookCannotBeCheckedOutException or BookNotFoundException
        catch(Exception e){
            return "\nSorry, that book is not available\n";
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
        //Exception could be BookNotValidForReturnException or BookNotFoundException
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
     * @throws BookNotFoundException when book is not found
     */
    public Book findBook(String title) throws BookNotFoundException{
        for(Book book : books){
            if(book.getTitle().equals(title))
                return book;
        }
        throw new BookNotFoundException("Book is not in the Library!");
    }








}
