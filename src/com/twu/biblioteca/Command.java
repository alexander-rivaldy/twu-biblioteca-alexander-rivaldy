package com.twu.biblioteca;

public class Command {

    String menuName;
    int menuNumber;
    LoginSystem login;
    Library library;

    public Command() {
    }


    public Command(String menuName, int menuNumber) {
        this.menuName = menuName;
        this.menuNumber = menuNumber;
    }

    public Command(String menuName, int menuNumber, LoginSystem login) {
        this.menuName = menuName;
        this.menuNumber = menuNumber;
        this.login = login;
    }

    public Command(String menuName, int menuNumber, Library library) {
        this.menuName = menuName;
        this.menuNumber = menuNumber;
        this.library = library;
    }

    public String execute() {
        return "";
    }

    public class ExitCommand extends Command {

        public ExitCommand(String menuName, int menuNumber) {
            super(menuName, menuNumber);
        }

        public String execute() {
            System.exit(1);
            return menuName;
        }

    }

    public class ShowInfoCommand extends Command {

        public ShowInfoCommand(String menuName, int menuNumber, LoginSystem loginSystem) {
            super(menuName, menuNumber, loginSystem);
        }

        public String execute() {
            return login.getActiveUser().printDetails();
        }

    }

    public class ListBooksCommand extends Command {

        public ListBooksCommand(String menuName, int menuNumber, Library library) {
            super(menuName, menuNumber, library);
        }

        public String execute() {
            return library.getAllBookDetailsWithColumn();
        }

    }

}
