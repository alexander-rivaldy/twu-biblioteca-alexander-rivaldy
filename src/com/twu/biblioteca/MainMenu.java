package com.twu.biblioteca;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;



/**
 * Created by alexa on 1/02/2019.
 */
public class MainMenu {

    public static final int EXIT = 0;
    public static final int BOOK_LIST = 1;
    public static final int CHECKOUT_BOOK = 2;
    public static final int RETURN_BOOK = 3;

    HashMap<Integer, String> validOptions;

    Library library;

    Scanner reader;

    public MainMenu(){
        initialMenu();
    }

    /**
     * Function to initialize all menu items as well as the library contents
     */
    public void initialMenu(){
        validOptions = new HashMap<Integer, String>();
        validOptions.put(EXIT, "Quit");
        validOptions.put(BOOK_LIST, "List of Books");
        validOptions.put(CHECKOUT_BOOK, "Checkout Book");
        validOptions.put(RETURN_BOOK, "Return Book");

        library = new Library();
        library.addBook(new Book("Test Driven Development", "Kent Beck", "2003"));
        library.addBook(new Book("The People Vs Tech", "Jamie Bartlett", "2018"));
        library.addBook(new Book("The Alchemist", "Paulo Coelho", "1988"));
    }

    public void run(){
        System.out.println(welcomeMessage());
        reader = new Scanner(System.in);
        while(true){
            int input = -1;
            printMenu();
            try {
                input = askForOptionInput(reader);
                checkOption(input);
                executeOption(input);
            }
            catch (InputMismatchException e){
                System.out.println("Please select a valid option!");
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Function to print the menu options
     */
    public void printMenu(){
        System.out.println("\nChoose an option from the list below: \n");
        System.out.println(getMenuOptions());
    }

    /**
     * Construct a String of all available menu options
     * @return has the format of
     *  0. Quit
     *  1. List of Books
     * and so on
     */
    public String getMenuOptions(){
        String menu = "";
        for(Map.Entry<Integer,String> option : validOptions.entrySet()){
            menu += option.getKey() + ". " + option.getValue() + "\n";
        }
        return menu;
    }

    /**
     * Function to check whether or not the user input of option is valid
     * @param option user input
     * @return boolean of true
     * @throws Exception will throw WrongMenuOptionException, telling the calling
     *    function that user has inputted a wrong value
     */
    public boolean checkOption(int option) throws Exception{
        if (validOptions.containsKey(option))
            return true;
        else
            throw new WrongMenuOptionException("Please select a valid option!");
    }

    /**
     * Function to execute the option entered by the user accordingly
     * @param option user input of option
     */
    public void executeOption(int option) {
        switch(option){
            case 0:
                reader.close();
                System.exit(1);
                break;
            case 1:
                System.out.println(library.getAllBookDetailsWithColumn());
                break;
            case 2:
                System.out.println(library.borrowProcess(reader));
                break;
            case 3:
                System.out.println(library.returnProcess(reader));
                break;
        }
    }

    /**
     * Prompt user to input which option they want to execute
     * @param reader to make sure that the app only uses 1 Scanner
     * @return
     *     -1 if the value entered is not valid
     *     otherwise, it will pass on the user input
     */
    public int askForOptionInput(Scanner reader){
        System.out.print("\nEnter your desired option: ");
        String input = reader.nextLine();
        int option = -1;
        try{
            option = Integer.parseInt(input);
        }
        catch(Exception e){
            //entered value is not an integer and will be handled
            //in calling function
        }
        return option;
    }

    public String welcomeMessage(){
        return "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    }



}
