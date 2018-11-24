package org.chocan;

import javax.xml.crypto.Data;

public class Main {
    /*
            menuIndx:
                0: Main Menu
                1: Login Screen
                2: Manager Menu
                3: Operator Menu
                4: Service Provider Menu
         */

    public static void main(String[] args) {
        Menu mMenu = new Menu();

        System.out.println("Welcome to our ChocAn App!");
        System.out.println("Connecting to database...");
        Database.load();
        Runtime.getRuntime().addShutdownHook(new StopHook());

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
                case 2:
                    mMenu.ManagerAccountMenu();
                    break;
                case 3:
                    mMenu.OperatorAccountMenu();
                    break;
                case 4:
                    mMenu.ProviderAccountMenu();
                    break;
            }
        }
        System.out.println("Thank you for using our app!");
        System.out.println("Quiting...");
    }

    private static class StopHook extends Thread {
        @Override
        public void run(){
            Database.save();
            System.out.println("ChocAn app is about to shutdown");
        }
    }
}
