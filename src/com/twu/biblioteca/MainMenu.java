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
        System.out.println();
        System.out.println(getMenuOptions());
        int input = -1;
        try {
            input = askForOptionInput();
            checkOption(input);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getMenuOptions(){
        String menu = "";
        for(Map.Entry<Integer,String> option : validOptions.entrySet()){
            menu += option.getKey() + ". " + option.getValue() + "\n";
        }
        return menu;
    }

    public boolean checkOption(int option) throws Exception{
        if (validOptions.containsKey(option))
            return true;
        else
            throw new WrongMenuOptionException("Please select a valid option!");
    }

    public int askForOptionInput(){
        System.out.print("\nEnter your desired option: ");
        Scanner reader = new Scanner(System.in);
        int input = reader.nextInt();
        reader.close();
        return input;
    }

    public String welcomeMessage(){
        return "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    }



}
