package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

public class NewMainMenu {

    public static final int EXIT = 0;
    public static final int SHOW_INFO = 1;
    public static final int BOOK_LIST = 2;
    public static final int CHECKOUT_BOOK = 3;
    public static final int RETURN_BOOK = 4;
    public static final int MOVIE_LIST = 5;
    public static final int CHECKOUT_MOVIE = 6;
    public static final int BORROWED_BOOK = 7;

    static PrintStream output;
    static Scanner input;
    HashMap<Integer,String> validOptions;
    LoginSystem login;

    Library library;

    MenuIO menuIO;


    public NewMainMenu(PrintStream output, Scanner input) {
        this.output = output;
        this.input = input;

        initialData();
    }

    public void run(){

        welcomeMessage();

        login.loginProcess(input, output);


        int option = -1;
        do{
            printMenu();
            option = menuIO.askOptionInput(validOptions);
            executeOption(option);
        }
        while(option != EXIT);

    }

    private void executeOption(int option) {
        switch (option) {
            case EXIT:
                input.close();
                break;
            case SHOW_INFO:
                output.println(login.getActiveUser().printDetails());
                break;
            case BOOK_LIST:
                output.println(library.getAllBookDetailsWithColumn());
                break;
            case CHECKOUT_BOOK:
                output.println(library.borrowProcess(input, login.getActiveUser(), Library.BOOKS));
                break;
            case RETURN_BOOK:
                output.println(library.returnProcess(input));
                break;
            case MOVIE_LIST:
                output.println(library.getAllMovieDetailsWithColumn());
                break;
            case CHECKOUT_MOVIE:
                output.println(library.borrowProcess(input, null, library.MOVIES));
                break;
            case BORROWED_BOOK:
                output.println(library.getBorrowedBooksWithColumn());
                break;
        }
    }

    private void printMenu() {
        menuIO.printMessage("\nChoose an option from the list below: \n\n");
        menuIO.printMenuOptions(validOptions);
    }

    private void welcomeMessage() {
        menuIO.printMessage("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n");
    }

    private void initialData(){
        login = new LoginSystem();
        login.addUser(new Customer("123-4567", "Alexander Rivaldy",
                "0410123456", "alexanderrivaldy@gmail.com", "password"));

        menuIO = new MenuIO(output, input);

        library = new Library();
        library.addBook(new Book("Test Driven Development", "Kent Beck", "2003"));
        library.addBook(new Book("The People Vs Tech", "Jamie Bartlett", "2018"));
        library.addBook(new Book("The Alchemist", "Paulo Coelho", "1988"));

        library.addMovie(new Movie("In The Heart of The Sea", "2015", "Ron Howard", 7));
        library.addMovie(new Movie("Deadpool", "2016", "Tim Miller", 7));
        library.addMovie(new Movie("Solo: A Star Wars Story", "2018", "Ron Howard"));

        fillMenuOptions();

    }

    private void fillMenuOptions() {
        validOptions = new HashMap<>();
        validOptions.put(EXIT, "Quit");
        validOptions.put(SHOW_INFO, "Show User Info");
        validOptions.put(BOOK_LIST, "List of Books");
        validOptions.put(CHECKOUT_BOOK, "Checkout Book");
        validOptions.put(RETURN_BOOK, "Return Book");
        validOptions.put(MOVIE_LIST, "List of Movies");
        validOptions.put(CHECKOUT_MOVIE, "Checkout Movie");
        validOptions.put(BORROWED_BOOK, "List of Borrowed Books");
    }



}
