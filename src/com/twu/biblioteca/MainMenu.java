package com.twu.biblioteca;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



/**
 * Created by alexa on 1/02/2019.
 */
public class MainMenu {

    public static final int EXIT = 0;
    public static final int BOOK_LIST = 1;

    HashMap<Integer, String> validOptions;

    Scanner reader;

    public MainMenu(){
        initialMenu();
    }

    public void initialMenu(){
        validOptions = new HashMap<Integer, String>();
        validOptions.put(EXIT, "Quit");
        validOptions.put(BOOK_LIST, "List of Books");
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
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void printMenu(){
        System.out.println("\nChoose an option from the list below: \n");
        System.out.println(getMenuOptions());
    }

    public String getMenuOptions(){
        String menu = "";
        for(Map.Entry<Integer,String> option : validOptions.entrySet()){
            menu += option.getKey() + ". " + option.getValue() + "\n";
        }
        return menu;
    }

    public boolean checkOption(int option) throws Exception{
        if(option == 0) {
            reader.close();
            System.exit(1);
        }
        if (validOptions.containsKey(option))
            return true;
        else
            throw new WrongMenuOptionException("Please select a valid option!");
    }

    public int askForOptionInput(Scanner reader){
        System.out.print("\nEnter your desired option: ");
        int input = reader.nextInt();
        return input;
    }

    public String welcomeMessage(){
        return "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    }



}
