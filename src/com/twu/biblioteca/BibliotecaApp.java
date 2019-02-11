package com.twu.biblioteca;

import java.util.Scanner;

public class BibliotecaApp {

    /**
     * Main function to run the whole application
     *
     * @param args
     */
    public static void main(String[] args) {
        NewMainMenu menu = new NewMainMenu(System.out, new Scanner(System.in));
        menu.run();
    }
}
