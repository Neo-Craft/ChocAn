package org.chocan;

import javax.xml.crypto.Data;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Menu mMenu = new Menu();

        System.out.println("Welcome to our ChocAn App!");
        System.out.println("Connecting to database...");
        Database.load();
        Runtime.getRuntime().addShutdownHook(new StopHook());
        ReportGenerator reportEngine = new ReportGenerator();

        System.out.println("Connection established :D");

        while (mMenu.menuIndx != -1){
            //Keep looping until the user choose to quit
            switch(mMenu.menuIndx) {
                case 0:
                    mMenu.MainMenu();
                    break;
                case 1:
                    mMenu.ManagerLoginMenu();
                    break;
                case 2:
                    mMenu.ProviderLoginMenu();
                    break;
                case 3:
                    mMenu.ManagerAccountMenu();
                    break;
                case 4:
                    mMenu.ProviderAccountMenu();
                    break;
                case 5:
                    mMenu.ManagerDatabaseInteraction();
                    break;
                case 6:
                    mMenu.ReportGeneratorMenu();
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
