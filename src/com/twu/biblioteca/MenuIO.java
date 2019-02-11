package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuIO {

    PrintStream output;
    Scanner input;

    public MenuIO(PrintStream output, Scanner input) {
        this.output = output;
        this.input = input;
    }

    public void printMessage(String message) {
        output.print(message);
    }

    //
    public void printMenuOptions(HashMap<Integer, String> menuItems) {
        for (Map.Entry<Integer, String> option : menuItems.entrySet()) {
            output.print(option.getKey() + ". " + option.getValue() + "\n");
        }
    }

    public int askOptionInput(HashMap<Integer, String> menuItems) {
        boolean askForInput = true;
        String input;
        int option = -1;

        while (askForInput) {
            output.print("\nEnter your desired option: ");
            try {
                input = this.input.nextLine();
                option = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                output.println("Value was not a number, please enter again\n");
                continue;
            }

            if ((askForInput = !menuItems.containsKey(option)))
                output.println("Value not within options, please enter again\n");
        }

        return option;
    }

}
