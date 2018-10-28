package org.chocan;

public class Main {

    public static void main(String[] args) {
        Menu mMenu = new Menu();

        System.out.println("Welcome to our ChocAn App!");
        System.out.println("Connecting to database...");
        System.out.println("Connection established :D");

        while (mMenu.menuIndx != -1){
            //Keep looping until the user choose to quit
            switch(mMenu.menuIndx) {
                case 0:
                    mMenu.MainMenu();
                    break;
                case 1:
                    mMenu.LoginMenu();
                    break;

            }
        }
        System.out.println("Thank you for using our app!");
        System.out.println("Quiting...");

    }

}
