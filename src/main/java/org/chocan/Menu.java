package org.chocan;

import java.util.Scanner;

public class Menu {
    /*
        menuIndx:
            0: Main Menu
            1: Login Screen
            2: Manager Menu
            3: Operator Menu
            4: Service Provider Menu
     */
    public int menuIndx = 0;
    private Scanner sc = new Scanner(System.in);

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void MainMenu(){
        System.out.println();
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
        System.out.println();
        System.out.println();
        System.out.println("***************************************************");
        System.out.println("               `-+shdmNMMMMNmdhs+-`               ");
        System.out.println("           `:smMmyo/:.``  ``.:/oymMms:`           ");
        System.out.println("         :yNms:`                  `:smNy:         ");
        System.out.println("       /mNy-                          -yNm/       ");
        System.out.println("     :mNo`          .+syhhys+.          `oNm:     ");
        System.out.println("   `yMh.         `omMMMMMMMMMMmo`         .hMs`   ");
        System.out.println("  `dM+          :NMMMMMMMMMMMMMMN:          +Md`  ");
        System.out.println(" `dM/          :MMMMMMMMMMMMMMMMMM:          /Md` ");
        System.out.println(" yMo           dMMMMMMMMMMMMMMMMMMd           oMy ");
        System.out.println(":Mm            NMMMMMMMMMMMMMMMMMMN            mM:");
        System.out.println("yM+            sMMMMMMMMMMMMMMMMMMs            +My");
        System.out.println("NM.            `dMMMMMMMMMMMMMMMMd`            .MN");
        System.out.println("MM`              oNMMMMMMMMMMMMMs`             `MM");
        System.out.println("NM.               `+hMMMMMMMMh+`               .MN");
        System.out.println("yM+             `/ymMMMMMMMMMMms/`             +My");
        System.out.println(":Mm           -hMMMMMMMMMMMMMMMMMNy.           mM:");
        System.out.println(" yMo        .hMMMMMMMMMMMMMMMMMMMMMMy`        oMy ");
        System.out.println(" `dM+      :NMMMMMMMMMMMMMMMMMMMMMMMMN-      +Md` ");
        System.out.println("  `dMo    .NMMMMMMMMMMMMMMMMMMMMMMMMMMN.    oMd`  ");
        System.out.println("    sMd.  hMMMMMMMMMMMMMMMMMMMMMMMMMMMMh  .dMs    ");
        System.out.println("     :mMy.MMMMMMMMMMMMMMMMMMMMMMMMMMMMMM.yMm:     ");
        System.out.println("       /mMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMm/       ");
        System.out.println("         :yNMMMMMMMMMMMMMMMMMMMMMMMMMMNy:         ");
        System.out.println("            :smMMMMMMMMMMMMMMMMMMMMms:            ");
        System.out.println("               `-+shdmNMMMMNmdhs+-`               ");
        System.out.println();
        System.out.println("********************Login Menu*********************");
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
                //If login succesfully then go to right account menu, if not, let the user input again or quit
                break;
        }
    }

    public void ManagerAccountMenu(){
        System.out.println();
        System.out.println();
        System.out.println("********************Manager Menu*********************");
        System.out.println("Please choose an option: ");
        System.out.println("\t-1: Quit app");
        System.out.println("\t 0: Clear Screen");
        System.out.println("\t 1: Log Out");
        System.out.println("\t 2: Run Weekly Reports");

        System.out.print("Your choice: ");
        int option = sc.nextInt();

        //Bad input ----- Make sure to check to upper bound
        while (option < -1 || option > 2){
            System.out.print("Can't choose that...");
            System.out.print("Your choice: ");
            option = sc.nextInt();
        }
    }

    public void OperatorAccountMenu(){
        System.out.println();
        System.out.println();
        System.out.println("********************Operator Menu*********************");
        System.out.println("Please choose an option: ");
        System.out.println("\t-1: Quit app");
        System.out.println("\t 0: Clear Screen");
        System.out.println("\t 1: Log Out");
        System.out.println("\t 2: Add member");
        System.out.println("\t 3: Update member");
        System.out.println("\t 4: Remove member");
        System.out.println("\t 5: Add provider");
        System.out.println("\t 6: Update provider");
        System.out.println("\t 7: Remove provider");

        System.out.print("Your choice: ");
        int option = sc.nextInt();

        //Bad input ----- Make sure to check to upper bound
        while (option < -1 || option > 7){
            System.out.print("Can't choose that...");
            System.out.print("Your choice: ");
            option = sc.nextInt();
        }
    }

    public void ProviderAccountMenu(){
        System.out.println();
        System.out.println();
        System.out.println("********************Report Menu*********************");

        System.out.println("Please choose an option: ");
        System.out.println("\t-1: Quit app");
        System.out.println("\t 0: Clear Screen");
        System.out.println("\t 1: Log Out");
        System.out.println("\t 2: Enter Service Provided into System");
        System.out.println("\t 3: View Provider Directory");
        System.out.println("\t 4: View and Send Billing Info");
        System.out.println("\t 5: View Subscription");

        System.out.print("Your choice: ");
        int option = sc.nextInt();

        //Bad input ----- Make sure to check to upper bound
        while (option < -1 || option > 5){
            System.out.print("Can't choose that...");
            System.out.print("Your choice: ");
            option = sc.nextInt();
        }
    }
}
