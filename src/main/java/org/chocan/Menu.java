package org.chocan;

import org.chocan.entities.*;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Menu {
    /*
        menuIndx:
            0: Main Menu
            1: Manager Login Menu
            2: Provider Login Menu
            3: Manager Menu
            4: Service Provider Menu
            5: Manager Database Interaction Menu
            6: Report Generator Menu
     */
    public int menuIndx = 0;
    private Scanner sc = new Scanner(System.in);
    private Manager mManager;
    private Provider mProvider;

    public static void ClearScreen() {
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

        boolean onMenu = true;
        while (onMenu == true) {
            DisplayMenu(0);
            System.out.print("Your choice: ");
            int option = GetInputIndx(-1, 2);

            switch (option) {
                case -1:
                    //Quit
                    menuIndx = -1;
                    return;
                case 0:
                    //Clear screen
                    ClearScreen();
                    break;
                case 1:
                    DisplayMenu(0);       //Print the menu again
                    break;
                case 2:
                    //Log in as manager
                    menuIndx = 1;
                    return;
                case 3:
                    //Log in as provider
                    menuIndx = 2;
                    return;
            }
        }
    }
    public void ManagerLoginMenu(){
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
        System.out.println("****************Manager Login Menu*****************");

        boolean onMenu = true;
        while(onMenu == true) {
            DisplayMenu(1);
            System.out.print("Your choice: ");
            int option = GetInputIndx(-1, 2);

            switch (option) {
                case -1:
                    //Quit
                    menuIndx = -1;
                    onMenu = false;
                    Logout();
                    break;
                case 0:
                    //Clear screen and redisplay menu
                    ClearScreen();
                    DisplayMenu(1);
                    break;
                case 1:
                    //Go back to main menu
                    menuIndx = 1;
                    onMenu = false;
                    Logout();
                    break;
                case 2:
                    //Log in
                    String userName, password;
                    System.out.println("Manager Login Menu");
                    System.out.print("Username: ");
                    userName = sc.nextLine();

                    Optional<Manager> provider = Database.MANAGERS.get(userName);
                    if (provider.isPresent() == true) {
                        System.out.print("Password: ");
                        password = sc.nextLine();
                        if (provider.get().isValidPass(password) == true){
                            mManager = provider.get();
                            menuIndx = 3;
                            onMenu = false;
                        } else {
                            System.out.println("Wrong password!");
                        }
                    } else {
                        System.out.println("Wrong username!");
                    }
                    break;
            }
        }
    }
    public void ProviderLoginMenu(){
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
        System.out.println("***************Provider Login Menu****************");

        boolean onMenu = true;
        while(onMenu == true) {
            DisplayMenu(1);
            System.out.print("Your choice: ");
            int option = GetInputIndx(-1, 2);

            switch (option) {
                case -1:
                    //Quit
                    menuIndx = -1;
                    onMenu = false;
                    break;
                case 0:
                    //Clear and redisplay menu
                    ClearScreen();
                    DisplayMenu(1);
                    break;
                case 1:
                    //Go back to main menu
                    menuIndx = 1;
                    onMenu = false;
                    Logout();
                    break;
                case 2:
                    //Log in
                    String userName, password;
                    System.out.println("Provider Login Menu");
                    System.out.print("Username: ");
                    userName = sc.nextLine();

                    Optional<Provider> provider = Database.PROVIDERS.getByName(userName);
                    if (provider.isPresent() == true) {
                        System.out.print("Password: ");
                        password = sc.nextLine();
                        if (provider.get().isValidPass(password) == true){
                            mProvider = provider.get();
                            menuIndx = 4;
                            onMenu = false;
                        } else {
                            System.out.println("Wrong password!");
                        }
                    } else {
                        System.out.println("Wrong username!");
                    }
                    break;
            }
        }
    }

    public void ManagerAccountMenu(){
        System.out.println();
        System.out.println();
        System.out.println("********************Manager Menu*********************");

        boolean onMenu = true;
        while (onMenu == true) {
            DisplayMenu(3);
            System.out.print("Your choice: ");
            int option = GetInputIndx(-1, 5);

            switch (option) {
                case 0:
                    //Clear and redisplay menu
                    ClearScreen();
                    DisplayMenu(3);
                    break;
                case 1:
                    //Log out
                    menuIndx = 1;
                    onMenu = false;
                    Logout();
                    break;
                case 2:
                    //Go to manager database interaction menu
                    menuIndx = 5;
                    onMenu = false;
                    break;
                case 3:
                    //Report menu
                    menuIndx = 6;
                    onMenu = false;
                    break;
                default:
                    menuIndx = -1;
                    onMenu = false;
                    Logout();
                    break;
            }
        }
    }
    public void ProviderAccountMenu(){
        System.out.println();
        System.out.println();
        System.out.println("********************Provider Menu*********************");

        boolean onMenu = true;
        while (onMenu == true) {
            DisplayMenu(4);
            System.out.print("Your choice: ");
            int option = GetInputIndx(-1, 5);

            switch (option) {
                case 0:
                    ClearScreen();
                    DisplayMenu(3);
                    break;
                case 1:
                    menuIndx = 1;
                    onMenu = false;
                    break;
                case 2:
                    //Request and view reports
                    break;
                case 3:
                    //Report Menu
                    menuIndx = 6;
                    onMenu = false;
                    break;
                default:
                    menuIndx = -1;
                    onMenu = false;
                    break;
            }
        }
    }

    public void ManagerDatabaseInteraction(){
        System.out.println();
        System.out.println();
        System.out.println("********************Manager Database Interaction Menu*********************");
        if (mManager == null){
            System.out.println("Error on loading manager!");
            menuIndx = 0;
            return;
        }
        DisplayMenu(5);

        //Display all the names only based on the current manager
        List<Provider> providers = mManager.getProviders();
        System.out.println("Here is the list of all providers belong to this manager: ");
        for(int indx = 0; indx < providers.size(); indx++){
            System.out.println((indx + 1) + ": " + providers.get(indx).getName());
        }

        boolean onMenu = true;
        while (onMenu == true){
            DisplayMenu(5);
            System.out.print("Your choice: ");
            int option = GetInputIndx(-1, 6);
            int indxToInteract;

            switch (option){
                case 0:
                    //Clear screen and redisplay
                    ClearScreen();
                    break;
                case 1:
                    //Log out
                    menuIndx = 0;
                    onMenu = false;
                    Logout();
                    break;
                case 2:
                    //Display all the providers
                    System.out.println("Here is the list of all providers belong to this manager: ");
                    for(int indx = 0; indx < providers.size(); indx++){
                        System.out.println(indx + ": " + providers.get(indx).getName());
                    }
                    break;
                case 3:
                    //Add new provider
                    System.out.println("Adding new provider...");
                    System.out.print("Please enter a new name: ");
                    String name = sc.nextLine();
                    System.out.print("Please enter a new weekly consultations: ");
                    short weeklyConsultations = sc.nextShort();
                    System.out.print("Please enter a new weekly fees: ");
                    float weeklyFees = sc.nextFloat();
                    int serviceCode = Database.PROVIDERS.getNextId();
                    System.out.print("Please enter a new password: ");
                    String password = sc.nextLine();
                    System.out.print("Please enter provider' street address: ");
                    String street = sc.nextLine();
                    System.out.print("Please enter provider' city: ");
                    String city = sc.nextLine();
                    System.out.print("Please enter provider' state: ");
                    String state = sc.nextLine();
                    System.out.print("Please enter provider' zip code: ");
                    int zipCode = sc.nextInt();

                    System.out.println("Are you sure you want to create a new provider with: ");
                    System.out.println("\tName: " + name);
                    System.out.println("\tWeekly Consultations: " + weeklyConsultations);
                    System.out.println("\tWeekly Weekly Fees: " + weeklyFees);
                    System.out.println("\tService Code: " + serviceCode);
                    System.out.println("\tPassword: " + password);
                    System.out.println("\tStreet Address: " + street);
                    System.out.println("\tCity: " + city);
                    System.out.println("\tState: " + state);
                    System.out.println("\tZip code: " + zipCode);

                    System.out.print("Your choice (0/1): ");
                    if (GetInputIndx(0, 1) == 1){
                        Coordinate coord = new Coordinate(street, city, state, zipCode);
                        Provider newProvider = new Provider(name, serviceCode, coord, password);
                        Database.PROVIDERS.add(newProvider);
                        System.out.println("New provider created!");
                    } else {
                        System.out.println("Input info discard...");
                    }
                    break;
                case 4:
                    //Remove provider
                    System.out.println("Please choose a provider index to remove (-1 to go back): ");
                    indxToInteract = GetInputIndx(0, providers.size() - 1);
                    System.out.println("Are you sure you want to delete that (0/1): ");
                    if (GetInputIndx(0, 1) == 1){
                        //Delete the manager
                        Database.PROVIDERS.delete(providers.get(indxToInteract));
                    }
                    break;
                case 5:
                    //Update provider info
                    //TODO: Update provider info
                    System.out.print("Please choose a member index to view (-1 to go back): ");
                    indxToInteract = GetInputIndx(0, providers.size() - 1);

                    break;
                case 6:
                    //Go back to manager menu
                    menuIndx = 3;
                    onMenu = false;
                    break;
                default:
                    //Quit
                    menuIndx = -1;
                    onMenu = false;
                    Logout();
                    break;
            }
        }
    }
    public void ReportGeneratorMenu() throws IOException {
        ReportGenerator mRG = new ReportGenerator();
        System.out.println();
        System.out.println();
        System.out.println("********************Report Menu*********************");

        List<Member> members = Database.MEMBERS.getAll();
        List<Provider> providers = mManager == null ? null : Database.PROVIDERS.getAll();

        boolean onMenu = true;
        while(onMenu == true) {
            DisplayMenu(6);
            System.out.print("Your choice: ");
            int choice = 0;

            switch(GetInputIndx(-1, mManager == null ? 4 : 5)){
                case -1:
                    onMenu = false;
                    menuIndx = -1;
                    break;
                case 0:
                    //Clear screen and redisplay menu
                    ClearScreen();
                    break;
                case 1:
                    //Logout
                    Logout();
                    menuIndx = 0;
                    onMenu = false;
                    break;
                case 2:
                    //Go back to previous menu
                    if (mManager == null){
                        //This means we were from provider menu
                        menuIndx = 4;
                    } else {
                        menuIndx = 3;
                    }
                    onMenu = false;
                    break;
                case 3:
                    System.out.println("Here is the list of all members' names");
                    for(int indx = 0; indx < members.size(); indx++){
                        System.out.println(indx + ": " + members.get(indx).getName());
                    }
                    System.out.print("Choose a member's index to generate report (-1 to return): ");
                    choice = GetInputIndx(-1, members.size() - 1);
                    if (choice != -1){
                        System.out.println("Here is the report: ");
                        mRG.generateMemberReport(members.get(choice));
                        System.out.println();
                    }
                    break;
                case 4:
                    mRG.generateEFTReport();
                    break;
                case 5:
                    System.out.println("Here is the list of all providers' names");
                    for(int indx = 0; indx < providers.size(); indx++){
                        System.out.println(indx + ": " + providers.get(indx).getName());
                    }
                    System.out.print("Choose a provider's index to generate report (-1 to return): ");
                    choice = GetInputIndx(-1, providers.size() - 1);
                    if (choice != -1){
                        System.out.println("Here is the report: ");
                        mRG.generateProviderReport(providers.get(choice));
                        System.out.println();
                    }
                    break;
            }
        }
    }

    protected void DisplayMenu(int indx){
        switch (indx){
            case 0: //Main Menu
                System.out.println("Please choose an option: ");
                System.out.println("\t-1: Quit app");
                System.out.println("\t 0: Clear Screen");
                System.out.println("\t 1: Redisplay Menu");
                System.out.println("\t 2: Go to Manager Screen");
                System.out.println("\t 3: Go to Provider Screen");
                break;
            case 1: //Login Menu
                System.out.println("Please choose an option: ");
                System.out.println("\t-1: Quit app");
                System.out.println("\t 0: Clear Screen and Re-display Menu");
                System.out.println("\t 1: Return to Main Menu");
                System.out.println("\t 2: Log in");
                break;
            case 3: //Manager Menu
                System.out.println("Please choose an option: ");
                System.out.println("\t-1: Quit app");
                System.out.println("\t 0: Clear Screen and Re-display menu");
                System.out.println("\t 1: Log Out");
                System.out.println("\t 2: Add/Remove/Update Provider's Information");
                System.out.println("\t 3: View Reports");
                break;
            case 4: //Provider Menu
                System.out.println("Please choose an option: ");
                System.out.println("\t-1: Quit app");
                System.out.println("\t 0: Clear Screen");
                System.out.println("\t 1: Log Out");
                System.out.println("\t 2: Request and send reports");
                System.out.println("\t 3: Bill services provided");
                break;
            case 5: //Manager Database Interaction Menu
                System.out.println("Please choose an option: ");
                System.out.println("\t-1: Quit app");
                System.out.println("\t 0: Clear Screen");
                System.out.println("\t 1: Log Out");
                System.out.println("\t 2: Re-display all the providers' names");
                System.out.println("\t 3: Add Provider");
                System.out.println("\t 4: Remove Provider");
                System.out.println("\t 5: More interaction with specific Provider");
                System.out.println("\t 6: Back to Previous Menu");
                break;
            case 6: //Report generator menu
                System.out.println("Please choose an option: ");
                System.out.println("\t-1: Quit app");
                System.out.println("\t 0: Clear Screen");
                System.out.println("\t 1: Log Out");
                System.out.println("\t 2: Go back to previous menu");
                System.out.println("\t 3: Generate Member Report");
                System.out.println("\t 4: Generate ETF Report");
                if (mManager != null){
                    //This means we are from the manager menu
                    System.out.println("\t 5: Generate Provider Report");
                }
                break;

        }
    }
    protected void Logout(){
        mProvider = null;
        mManager = null;
    }
    protected int GetInputIndx(int min, int max){
        int returnVal = sc.nextInt();
        //Bad input ----- Make sure to check to upper bound
        while (returnVal < min || returnVal > max){
            System.out.print("Can't choose that... ");
            System.out.print("Your choice: ");
            returnVal = sc.nextInt();
        }

        return returnVal;
    }
}
