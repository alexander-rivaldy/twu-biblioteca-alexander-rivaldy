package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by alexa on 1/02/2019.
 */
public class MainMenu {

    HashMap<Integer, String> validOptions;

    public MainMenu(){
        initialMenu();
    }

    public void initialMenu(){
        validOptions = new HashMap<Integer, String>();
        validOptions.put(1, "List of Books");
    }

    public void run(){
        System.out.println(welcomeMessage());
        System.out.println();
        System.out.println(getMenuOptions());
        try{
            chooseOption(askForOptionInput());
        }
        catch(Exception e){
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

    public boolean chooseOption(Object option) throws Exception{
        if (validOptions.containsKey(option))
            return true;
        else throw new WrongMenuOptionException("Please select a valid option!");
    }

    public Object askForOptionInput(){
        System.out.print("\nEnter your desired option: ");
        Scanner reader = new Scanner(System.in);
        Object input = reader.next();
        reader.close();
        return input;
    }

    public String welcomeMessage(){
        return "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    }



}
