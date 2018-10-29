package org.chocan;

import java.util.Scanner;

public class Menu {
    /*
        menuIndx:
            0: Main Menu
            1: Login Screen

     */
    public int menuIndx = 0;
    private Scanner sc = new Scanner(System.in);

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void MainMenu(){
        System.out.println();
        System.out.println("***************************************************");
        System.out.println("  ___  ___  ___  ___  ___.---------------.");
        System.out.println(".'\\__\\'\\__\\'\\__\\'\\__\\'\\__,`   .  ____ ___ \\ ");
        System.out.println("|\\/ __\\/ __\\/ __\\/ __\\/ _:\\   |`.  \\  \\___ \\ ");
        System.out.println(" \\\\'\\__\\'\\__\\'\\__\\'\\__\\'\\_`.__|\"\"`. \\  \\___ \\ ");
        System.out.println("  \\\\/ __\\/ __\\/ __\\/ __\\/ __:                \\ ");
        System.out.println("   \\\\'\\__\\'\\__\\'\\__\\ \\__\\'\\_;-----------------`");
        System.out.println("    \\\\/   \\/   \\/   \\/   \\/ :                 |");
        System.out.println("     \\|______________________;________________|");
        System.out.println();

        System.out.println("*********************Main Menu*********************");
        System.out.println("Please choose an option: ");
        System.out.println("\t-1: Quit app");
        System.out.println("\t 0: Clear Screen");
        System.out.println("\t 1: Go to Login Screen");

        System.out.print("Your choice: ");
        int option = sc.nextInt();

        //Bad input ----- Make sure to check to upper bound
        while (option < -1 || option > 1){
            System.out.print("Can't choose that...");
            System.out.print("Your choice: ");
            option = sc.nextInt();
        }

        switch (option){
            case -1:
                menuIndx = -1;
                return;
            case 0:
                clearScreen();
                menuIndx = 0;       //Print the menu again
                break;
            case 1:
                menuIndx = 1;
                return;
        }
    }
    public void LoginMenu(){
        System.out.println("----------------------Login Menu----------------------");
        System.out.println("Please choose an option: ");
        System.out.println("\t-1: Quit app");
        System.out.println("\t 0: Clear Screen");
        System.out.println("\t 1: Return to Main Menu");
        System.out.println("\t 2: Log in");

        System.out.print("Your choice: ");
        int option = sc.nextInt();

        //Bad input ----- Make sure to check to upper bound
        while (option < -1 || option > 5){
            System.out.print("Can't choose that...");
            System.out.print("Your choice: ");
            option = sc.nextInt();
        }

        switch(option){
            case -1:
                menuIndx = -1;
                return;
            case 0:
                clearScreen();
                menuIndx = 1;
                break;
            case 1:
                menuIndx = 1;
                return;
            case 2:
                //TODO: Handle login here
                //If login succesfully then go to account option, if not, let the user input again or quit
                break;
        }
    }

    public void AccountMenu(){

    }
    public void PrintReportMenu(){
        System.out.println("\n----------------------Report Menu----------------------");
    }
}
