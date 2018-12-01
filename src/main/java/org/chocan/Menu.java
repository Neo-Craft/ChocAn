package org.chocan;

import org.chocan.entities.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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
    private Scanner numScanner = new Scanner(System.in);
    private Scanner strScanner = new Scanner(System.in);
    private Manager mManager;
    private Provider mProvider;

    private static void ClearScreen() {
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
            System.out.print("Your Menu choice: ");
            int option = GetNumberInputWithBoundCheck(-1, 3);

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
                    //DisplayMenu(0);       //Print the menu again
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
            System.out.print("Your Menu choice: ");
            int option = GetNumberInputWithBoundCheck(-1, 2);

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
                    //DisplayMenu(1);
                    break;
                case 1:
                    //Go back to main menu
                    menuIndx = 0;
                    onMenu = false;
                    Logout();
                    break;
                case 2:
                    //Log in
                    String userName, password;
                    System.out.println("Manager Login Menu");
                    System.out.print("Username: ");
                    userName = strScanner.nextLine();

                    Optional<Manager> provider = Database.MANAGERS.get(userName);
                    if (provider.isPresent() == true) {
                        System.out.print("Password: ");
                        password = strScanner.nextLine();
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
            System.out.print("Your Menu choice: ");
            int option = GetNumberInputWithBoundCheck(-1, 2);

            switch (option) {
                case -1:
                    //Quit
                    menuIndx = -1;
                    onMenu = false;
                    break;
                case 0:
                    //Clear and redisplay menu
                    ClearScreen();
                    //DisplayMenu(1);
                    break;
                case 1:
                    //Go back to main menu
                    menuIndx = 0;
                    onMenu = false;
                    Logout();
                    break;
                case 2:
                    //Log in
                    String userName, password;
                    System.out.println("Provider Login Menu");
                    System.out.print("Username: ");
                    userName = strScanner.nextLine();

                    Optional<Provider> provider = Database.PROVIDERS.getByName(userName);
                    if (provider.isPresent() == true) {
                        System.out.print("Password: ");
                        password = strScanner.nextLine();
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
            System.out.print("Your Menu choice: ");
            int option = GetNumberInputWithBoundCheck(-1, 5);

            switch (option) {
                case 0:
                    //Clear and redisplay menu
                    ClearScreen();
                    //DisplayMenu(3);
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
    public void ProviderAccountMenu() throws IOException {
        System.out.println();
        System.out.println();
        System.out.println("********************Provider Menu*********************");

        boolean onMenu = true;
        while (onMenu == true) {
            DisplayMenu(4);
            System.out.print("Your Menu choice: ");
            int option = GetNumberInputWithBoundCheck(-1, 3);

            switch (option) {
                case 0:
                    ClearScreen();
                    //DisplayMenu(3);
                    break;
                case 1:
                    menuIndx = 1;
                    onMenu = false;
                    break;
                case 2:
                    //Request Service Directory
                    DirectoryGenerator sDG = new DirectoryGenerator();
                    final String directoryPath =  sDG.generateProviderDirectory();
                    System.out.println("The directory has been generated in "+ directoryPath);
                    System.out.println();
                    break;
                case 3:
                    //Add service to bill
                    AddNewService(mProvider);
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

        //Current manager
        List<Provider> providers = mManager.getProviders();

        boolean onMenu = true;
        while (onMenu == true){
            DisplayMenu(5);
            System.out.print("Your Menu choice: ");
            int option = GetNumberInputWithBoundCheck(-1, 14);
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
                    //Display all the providers' names and IDs
                    System.out.println("Here is the list of all providers in the database: ");
                    for(Provider provider : Database.PROVIDERS.getAll()){
                        System.out.println(provider.getName() + ": " + provider.getNumber());
                    }
                    break;
                case 3:
                    AddNewProvider();
                    break;
                case 4:
                    //Remove provider
                    System.out.println("Please choose a provider index to remove (-1 to go back): ");
                    Provider curProvider;
                    indxToInteract = GetNumberInputWithBoundCheck(100000000, 999999999, true);
                    if (indxToInteract != -1){
                        curProvider = Database.PROVIDERS.get(indxToInteract).orElseGet(null);
                        while(curProvider == null) {
                            System.out.println("Can't find provider with id: " + indxToInteract + " on the database!");
                            System.out.print("Please reenter a new provider ID or -1 to abort: ");
                            indxToInteract = GetNumberInputWithBoundCheck(100000000, 999999999, true);
                            if(indxToInteract == -1){
                                break;
                            } else {
                                curProvider = Database.PROVIDERS.get(indxToInteract).orElseGet(null);
                            }
                        }
                        if(curProvider != null) {
                            System.out.println("Are you sure you want to delete that (0/1): ");
                            if (GetNumberInputWithBoundCheck(0, 1) == 1) {
                                //Delete the provider
                                Database.PROVIDERS.delete(curProvider);
                            }
                        }
                    }
                    break;
                case 5:
                    //Update provider info
                    System.out.print("Please choose a provider index to update (-1 to go back): ");
                    Provider curUpdateProvider;
                    indxToInteract = GetNumberInputWithBoundCheck(100000000, 999999999, true);
                    if (indxToInteract != -1){
                        curUpdateProvider = Database.PROVIDERS.get(indxToInteract).orElseGet(null);
                        while(curUpdateProvider == null) {
                            System.out.println("Can't find provider with id: " + indxToInteract + " on the database!");
                            System.out.print("Please reenter a new provider ID or -1 to abort: ");
                            indxToInteract = GetNumberInputWithBoundCheck(100000000, 999999999, true);
                            if(indxToInteract == -1){
                                break;
                            } else {
                                curUpdateProvider = Database.PROVIDERS.get(indxToInteract).orElseGet(null);
                            }
                        }
                        if(curUpdateProvider != null) {
                            UpdateProvider(curUpdateProvider);
                        }
                    }
                    break;
                case 6:
                    //Display members
                    System.out.println("Here is the list of all members in the database: ");
                    for(Member member : Database.MEMBERS.getAll()){
                        DisplayMemberInfo(member);
                    }
                    break;
                case 7:
                    AddNewMember();
                    break;
                case 8:
                    //Remove member
                    System.out.println("Please choose a member ID to remove (-1 to go back): ");
                    Member curMember;
                    indxToInteract = GetNumberInputWithBoundCheck(100000000, 999999999, true);
                    if (indxToInteract != -1){
                        curMember = Database.MEMBERS.get(indxToInteract).orElseGet(null);
                        while(curMember == null){
                            System.out.println("Can't find member with id: " + indxToInteract + " on the database!");
                            System.out.print("Please reenter a new member ID or -1 to abort: ");
                            indxToInteract = GetNumberInputWithBoundCheck(100000000, 999999999, true);
                            if(indxToInteract == -1){
                                break;
                            } else {
                                curMember = Database.MEMBERS.get(indxToInteract).orElseGet(null);
                            }
                        }
                        if (curMember != null){
                            System.out.println("Are you sure you want to delete that (0/1): ");
                            if (GetNumberInputWithBoundCheck(0, 1) == 1){
                                //Delete the member
                                Database.MEMBERS.delete(curMember);
                            }
                        }
                    }
                    break;
                case 9:
                    //Update member
                    System.out.println("Please choose a member ID to remove (-1 to go back): ");
                    Member curUpdateMember = null;
                    indxToInteract = GetNumberInputWithBoundCheck(100000000, 999999999, true);
                    if (indxToInteract != -1){
                        curUpdateMember = Database.MEMBERS.get(indxToInteract).orElseGet(null);
                        while(curUpdateMember == null){
                            System.out.println("Can't find member with id: " + indxToInteract + " on the database!");
                            System.out.print("Please reenter a new member ID or -1 to abort: ");
                            indxToInteract = GetNumberInputWithBoundCheck(100000000, 999999999, true);
                            if(indxToInteract == -1){
                                break;
                            } else {
                                curUpdateMember = Database.MEMBERS.get(indxToInteract).orElseGet(null);
                            }
                        }
                        if (curUpdateMember != null){
                            //Update the member
                            UpdateMember(curUpdateMember);
                        }
                    }
                    break;
                case 10:
                    //Display services
                    System.out.println("Please choose a provider index to view services (-1 to go back): ");
                    Provider curViewServiceProvider;
                    indxToInteract = GetNumberInputWithBoundCheck(100000000, 999999999, true);
                    if (indxToInteract != -1){
                        curViewServiceProvider = Database.PROVIDERS.get(indxToInteract).orElseGet(null);
                        while(curViewServiceProvider == null) {
                            System.out.println("Can't find provider with id: " + indxToInteract + " on the database!");
                            System.out.print("Please reenter a new provider ID or -1 to abort: ");
                            indxToInteract = GetNumberInputWithBoundCheck(100000000, 999999999, true);
                            if(indxToInteract == -1){
                                break;
                            } else {
                                curViewServiceProvider = Database.PROVIDERS.get(indxToInteract).orElseGet(null);
                            }
                        }
                        if(curViewServiceProvider != null) {
                            System.out.println("Is " + curViewServiceProvider.getName() + " correct? (0,1): ");
                            if (GetNumberInputWithBoundCheck(0, 1) == 1) {
                                for(ArrayList<Service> serviceList : curViewServiceProvider.getServices().values()){
                                    for(Service service : serviceList){
                                        DisplayServiceInfo(service);
                                    }
                                }
                            }
                        }
                    }
                    break;
                case 11:
                    //Add new service
                    System.out.println("Please choose a provider index to add service (-1 to go back): ");
                    Provider curAddServiceProvider;
                    indxToInteract = GetNumberInputWithBoundCheck(100000000, 999999999, true);
                    if (indxToInteract != -1){
                        curAddServiceProvider = Database.PROVIDERS.get(indxToInteract).orElseGet(null);
                        while(curAddServiceProvider == null) {
                            System.out.println("Can't find provider with id: " + indxToInteract + " on the database!");
                            System.out.print("Please reenter a new provider ID or -1 to abort: ");
                            indxToInteract = GetNumberInputWithBoundCheck(100000000, 999999999, true);
                            if(indxToInteract == -1){
                                break;
                            } else {
                                curAddServiceProvider = Database.PROVIDERS.get(indxToInteract).orElseGet(null);
                            }
                        }
                        if(curAddServiceProvider != null) {
                            System.out.println("Is " + curAddServiceProvider.getName() + " correct? (0,1): ");
                            if (GetNumberInputWithBoundCheck(0, 1) == 1) {
                                //Add new service for this provider
                                AddNewService(curAddServiceProvider);
                            }
                        }
                    }
                    break;
                case 12:
                    //Remove service
                    System.out.println("Please enter a service's ID to remove service (-1 to go back): ");
                    indxToInteract = GetNumberInputWithBoundCheck(100000000, 999999999, true);
                    Service delService = null;
                    //Find a service to delete
                    if (indxToInteract != -1){
                        for(Provider provider : Database.PROVIDERS.getAll()){
                            for(ArrayList<Service> serviceList : provider.getServices().values()){
                                for(Service service : serviceList){
                                    if (indxToInteract == service.getServiceId()){
                                        delService = service;
                                        break;
                                    }
                                }
                            }
                        }

                        while(delService == null){
                            System.out.println("Can't find service with ID: " + indxToInteract + " on your database!");
                            System.out.print("Please reenter the ID or -1 to abort: ");
                            indxToInteract = GetNumberInputWithBoundCheck(100000000, 999999999, true);
                            if (indxToInteract == -1){
                                break;
                            } else {
                                for(Provider provider : Database.PROVIDERS.getAll()){
                                    for(ArrayList<Service> serviceList : provider.getServices().values()){
                                        for(Service service : serviceList){
                                            if (indxToInteract == service.getServiceId()){
                                                delService = service;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        if (delService != null){
                            System.out.println("Are you sure you want to delete that (0/1): ");
                            if (GetNumberInputWithBoundCheck(0, 1) == 1){
                                //Delete the member
                                Database.SERVICES.delete(delService);
                            }
                        }
                    }
                    break;
                case 13:
                    //Update Service
                    System.out.println("Please enter a service's ID to Update (-1 to go back): ");
                    indxToInteract = GetNumberInputWithBoundCheck(100000000, 999999999, true);
                    Service updateService = null;
                    //Find a service to delete
                    if (indxToInteract != -1){
                        for(Provider provider : Database.PROVIDERS.getAll()){
                            for(ArrayList<Service> serviceList : provider.getServices().values()){
                                for(Service service : serviceList){
                                    if (indxToInteract == service.getServiceId()){
                                        updateService = service;
                                        break;
                                    }
                                }
                            }
                        }

                        while(updateService == null){
                            System.out.println("Can't find service with ID: " + indxToInteract + " on your database!");
                            System.out.print("Please reenter the ID or -1 to abort: ");
                            indxToInteract = GetNumberInputWithBoundCheck(100000000, 999999999, true);
                            if (indxToInteract == -1){
                                break;
                            } else {
                                for(Provider provider : Database.PROVIDERS.getAll()){
                                    for(ArrayList<Service> serviceList : provider.getServices().values()){
                                        for(Service service : serviceList){
                                            if (indxToInteract == service.getServiceId()){
                                                updateService = service;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        if (updateService != null){
                            UpdateService(updateService);
                        }
                    }
                    break;
                case 14:
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
            System.out.print("Your Menu choice: ");
            int choice = 0;

            switch(GetNumberInputWithBoundCheck(-1, mManager == null ? 4 : 5)){
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
                    for(int indx = 1; indx <= members.size(); indx++){
                        System.out.println(indx + ": " + members.get(indx - 1).getName());
                    }
                    System.out.print("Choose a member's index to generate report or 0 to print all (-1 to return): ");
                    choice = GetNumberInputWithBoundCheck(-1, members.size());
                    if (choice != -1){
                        if (choice == 0) {
                            String savePath = null;
                            for (Member member : members) {
                                savePath = mRG.generateMemberReport(member);
                            }
                            if (savePath != null) {
                                System.out.println("The reports have been saved in " + savePath);
                                System.out.println();
                            }
                        } else {
                            final String returnedPath = mRG.generateMemberReport(members.get(choice - 1));
                            System.out.println("The report has been saved in " + returnedPath);
                            System.out.println();
                        }
                    }
                    break;
                case 4:
                    final String reportPath =  mRG.generateEFTReport();
                    System.out.println("The EFT report has been generated in "+ reportPath);
                    System.out.println();
                    break;
                case 5:
                    System.out.println("Here is the list of all providers' names");
                    for(int indx = 1; indx <= providers.size(); indx++){
                        System.out.println(indx + ": " + providers.get(indx - 1).getName());
                    }
                    System.out.print("Choose a provider's index to generate report, or 0 to print all (-1 to return): ");
                    choice = GetNumberInputWithBoundCheck(-1, providers.size());
                    if (choice != -1){
                        if (choice == 0) {
                            String savePath = null;
                            for (Provider provider : providers) {
                                savePath = mRG.generateProviderReport(provider);
                            }
                            if (savePath != null) {
                                System.out.println("The reports have been saved in " + savePath);
                                System.out.println();
                            }
                        } else {
                            final String savePath = mRG.generateProviderReport(providers.get(choice - 1));
                            System.out.println("The report has been saved in " + savePath);
                            System.out.println();
                        }
                    }
                    break;
            }
        }
    }
    private void DisplayMenu(int option){
        int indx = -1;
        switch (option){
            case 0: //Main Menu: -1 -> 4
                System.out.println("Please choose an option: ");
                System.out.println("\t" + indx++ + ": Quit app");
                System.out.println("\t " + indx++ + ": Clear Screen");
                System.out.println("\t " + indx++ + ": Redisplay Menu");
                System.out.println("\t " + indx++ + ": Go to Manager Screen");
                System.out.println("\t " + indx++ + ": Go to Provider Screen");
                break;
            case 1: //Login Menu: -1 -> 3
                System.out.println("Please choose an option: ");
                System.out.println("\t" + indx++ + ": Quit app");
                System.out.println("\t " + indx++ + ": Clear Screen and Re-display Menu");
                System.out.println("\t " + indx++ + ": Return to Main Menu");
                System.out.println("\t " + indx++ + ": Log in");
                break;
            case 3: //Manager Menu: -1 -> 4
                System.out.println("Please choose an option: ");
                System.out.println("\t" + indx++ + ": Quit app");
                System.out.println("\t " + indx++ + ": Clear Screen and Re-display menu");
                System.out.println("\t " + indx++ + ": Log Out");
                System.out.println("\t " + indx++ + ": Add/Remove/Update Provider's Information");
                System.out.println("\t " + indx++ + ": View Reports");
                break;
            case 4: //Provider Menu
                System.out.println("Please choose an option: ");
                System.out.println("\t" + indx++ + ": Quit app");
                System.out.println("\t " + indx++ + ": Clear Screen");
                System.out.println("\t " + indx++ + ": Log Out");
                System.out.println("\t " + indx++ + ": Request Service Directory");
                System.out.println("\t " + indx++ + ": Bill services provided");
                break;
            case 5: //Manager Database Interaction Menu: -1 -> 14
                System.out.println("Please choose an option: ");
                System.out.println("\t" + indx++ + ": Quit app");
                System.out.println("\t " + indx++ + ": Clear Screen");
                System.out.println("\t " + indx++ + ": Log Out");

                System.out.println("\t " + indx++ + ": Display all the providers' names");
                System.out.println("\t " + indx++ + ": Add Provider");
                System.out.println("\t " + indx++ + ": Remove Provider");
                System.out.println("\t " + indx++ + ": Update specific Provider");

                System.out.println("\t " + indx++ + ": Display all the Members' names");
                System.out.println("\t " + indx++ + ": Add Member");
                System.out.println("\t " + indx++ + ": Remove Member");
                System.out.println("\t " + indx++ + ": Update specific Member");

                System.out.println("\t " + indx++ + ": Display Service info by Provider");
                System.out.println("\t " + indx++ + ": Add Service");
                System.out.println("\t " + indx++ + ": Remove Service");
                System.out.println("\t " + indx++ + ": Update specific Service");

                System.out.println("\t " + indx++ + ": Back to Previous Menu");
                break;
            case 6: //Report generator menu: -1 -> 4 (or 5)
                System.out.println("Please choose an option: ");
                System.out.println("\t" + indx++ + ": Quit app");
                System.out.println("\t " + indx++ + ": Clear Screen");
                System.out.println("\t " + indx++ + ": Log Out");
                System.out.println("\t " + indx++ + ": Go back to previous menu");
                System.out.println("\t " + indx++ + ": Generate Member Report");
                System.out.println("\t " + indx++ + ": Generate ETF Report");
                if (mManager != null){
                    //This means we are from the manager menu
                    System.out.println("\t " + indx++ + ": Generate Provider Report");
                }
                break;

        }
    }

    private void Logout(){
        mProvider = null;
        mManager = null;
    }
    private int GetNumberInputWithBoundCheck(int min, int max){
        int returnVal = -3;
        try {
             returnVal = numScanner.nextInt();
        }catch (InputMismatchException ex){
            System.out.println("Input mismatch! Check GetNumberInputWithBoundCheck(int, int)");
        }
        //Bad input ----- Make sure to check to upper bound
        while (returnVal < min || returnVal > max){
            System.out.print("Can't input that... ");
            System.out.print("Your input: ");
            try {
                returnVal = numScanner.nextInt();
            }catch (InputMismatchException ex){
                System.out.println("Input mismatch! Check GetNumberInputWithBoundCheck(int, int)");
            }
        }

        return returnVal;
    }       //Prompt for input until we get the correct input in range[min, max]
    private int GetNumberInputWithBoundCheck(int min, int max, boolean quitOption){
        int returnVal = -3;
        try {
            returnVal = numScanner.nextInt();
        }catch (InputMismatchException ex){
            System.out.println("Input mismatch! Check GetNumberInputWithBoundCheck(int, int)");
        }
        //User wants to quit instead of providing input
        if (quitOption && returnVal == -1) return returnVal;
        //Bad input ----- Make sure to check to upper bound
        while (returnVal < min || returnVal > max){
            System.out.print("Can't input that... ");
            System.out.print("Your input: ");
            try {
                returnVal = numScanner.nextInt();
            }catch (InputMismatchException ex){
                System.out.println("Input mismatch! Check GetNumberInputWithBoundCheck(int, int)");
            }
        }

        return returnVal;
    }       //Quits on -1 if quitOption is enabled
    private float GetNumberInputWithBoundCheck(float min, float max){
        float returnVal = -3;
        try {
            returnVal = numScanner.nextFloat();
        }catch (InputMismatchException ex){
            System.out.println("Input mismatch! Check GetNumberInputWithBoundCheck(float, float)");
        }
        //Bad input ----- Make sure to check to upper bound
        while (returnVal < min || returnVal > max){
            System.out.print("Can't input that... ");
            System.out.print("Your input: ");
            try {
                returnVal = numScanner.nextFloat();
            }catch (InputMismatchException ex){
                System.out.println("Input mismatch! Check GetNumberInputWithBoundCheck(float, float)");
            }
        }

        return returnVal;
    } //Prompt for input until we get the correct input in range[min, max]

    private Provider AddNewProvider(){
        //Add new provider
        System.out.println("Adding new provider...");
        System.out.print("Please enter a new name: ");
        String name = strScanner.nextLine();
        int providerID = Database.PROVIDERS.getNextId();
        System.out.print("Please enter a new password: ");
        String password = strScanner.nextLine();
        System.out.print("Please enter provider's street address: ");
        String street = strScanner.nextLine();
        System.out.print("Please enter provider's city: ");
        String city = strScanner.nextLine();
        System.out.print("Please enter provider's state: ");
        String state = strScanner.nextLine();
        System.out.print("Please enter provider's zip code (5 digits): ");
        int zipCode = GetNumberInputWithBoundCheck(10000, 99999);

        System.out.println("\nAre you sure you want to create a new provider with: ");
        System.out.println("\tName: " + name);
        System.out.println("\tID: " + providerID);
        System.out.println("\tPassword: " + password);
        System.out.println("\tStreet Address: " + street);
        System.out.println("\tCity: " + city);
        System.out.println("\tState: " + state);
        System.out.println("\tZip code: " + zipCode);

        System.out.print("Your choice (0/1): ");
        if (GetNumberInputWithBoundCheck(0, 1) == 1){
            Provider newProvider = new Provider(name, providerID,
                    new Coordinate(street, city, state, zipCode), password);
            Database.PROVIDERS.add(newProvider);
            System.out.println("New provider created!");
            return newProvider;
        } else {
            System.out.println("Input info discard...");
            return null;
        }
    }
    private Member AddNewMember(){
        System.out.println("Adding new member...");
        System.out.print("Please enter new name: ");
        String name = strScanner.nextLine();
        System.out.print("Is this member suspended (0/1): ");
        boolean isSuspended = GetNumberInputWithBoundCheck(0, 1) == 1;
        System.out.print("Please enter member's street address: ");
        String street = strScanner.nextLine();
        System.out.print("Please enter member's city: ");
        String city = strScanner.nextLine();
        System.out.print("Please enter member's state: ");
        String state = strScanner.nextLine();
        System.out.print("Please enter member's zip code: ");
        int zipCode = GetNumberInputWithBoundCheck(10000, 99999);
        int memberId = Database.MEMBERS.getNextId();

        System.out.println("\nAre you sure you want to create a new member with: ");
        System.out.println("\tName: " + name);
        System.out.println("\tSuspended: " + (isSuspended == true ? "Yes" : "No"));
        System.out.println("\tMember ID: " + memberId);
        System.out.println("\tStreet Address: " + street);
        System.out.println("\tCity: " + city);
        System.out.println("\tState: " + state);
        System.out.println("\tZip code: " + zipCode);

        System.out.print("Your choice (0/1): ");
        if (GetNumberInputWithBoundCheck(0, 1) == 1){
            Member newMember = new Member(name, memberId,
                    new Coordinate(street, city, state, zipCode), isSuspended);
            Database.MEMBERS.add(newMember);
            System.out.println("New member created!");
            return newMember;
        } else {
            System.out.println("Input info discard...");
            return null;
        }
    }
    private Service AddNewService(){
        try {
            System.out.println("Adding new service...");
            boolean validCode = false;
            int serviceCode = 0;
            while (!validCode) {
                System.out.print("Please enter a 6 digit service code: ");
                serviceCode = GetNumberInputWithBoundCheck(100000, 999999);
                if (Service.serviceDirectory.containsKey(serviceCode)) {
                    System.out.println("Service entered is " + Service.serviceDirectory.get(serviceCode));
                    System.out.println("Is this correct? (0/1): ");
                    if (GetNumberInputWithBoundCheck(0, 1) == 1) {
                        validCode = true;
                    }
                }
            }
            String serviceName = Service.serviceDirectory.get(serviceCode);
            System.out.print("Please enter date of service (MM-DD-YYYY): ");
            Date serviceDate  = new SimpleDateFormat("MM-dd-yyyy")
                    .parse(strScanner.nextLine());

            System.out.print("Please enter an associated provider ID: ");
            int providerID = GetNumberInputWithBoundCheck(100000000, 999999999);
            Provider curProvider;
            while((curProvider = Database.PROVIDERS.get(providerID).orElseGet(null)) == null){
                System.out.println("Provider with ID: " + providerID + " doesn't exist!");
                System.out.print("Please enter a new ID or -1 to quit: ");
                providerID = GetNumberInputWithBoundCheck(100000000, 999999999);

                if(providerID == -1){
                    System.out.println("Input info discard...");
                    return null;
                }
            }

            System.out.print("Please enter the fee to be paid (Up to $999.99): ");
            float paidFee = GetNumberInputWithBoundCheck(0.0f, 999.99f);

            System.out.print("Please enter a member's ID that the service is associated with: ");
            int memberID = GetNumberInputWithBoundCheck(100000000, 999999999, true);
            Member curMember;
            while((curMember = Database.MEMBERS.get(memberID).orElseGet(null)) == null){
                System.out.println("Can't find member with id: " + memberID + " on the system!");
                System.out.print("Please reenter a new ID or -1 to abort: ");
                memberID = GetNumberInputWithBoundCheck(100000000, 999999999, true);

                if (memberID == -1){
                    System.out.println("New info discard... Rolling back.");
                    return null;
                }
            }

            System.out.println("\nAre you sure you want to create a new service with: ");
            System.out.println("\tName: " + serviceName);
            System.out.println("\tService Date: " + serviceDate);
            System.out.println("\tProvider ID: " + providerID);
            System.out.println("\tService Code: " + serviceCode);
            System.out.println("\tFee to be paid: " + paidFee);
            System.out.println("\tAssociated member ID: " + memberID);

            System.out.print("Your choice (0/1): ");
            if (GetNumberInputWithBoundCheck(0, 1) == 1){
                int serviceId = Database.SERVICES.getNextId();
                Service newService = new Service(serviceDate, providerID, serviceId,
                        serviceCode, paidFee, serviceName);

                //Also add the service to the provider list
                curProvider.addService(curMember, newService);
                //and to the associated member's service list
                curMember.addService(newService);
                Database.SERVICES.add(newService);
                System.out.println("New member created!");
                return newService;
            } else {
                System.out.println("Input info discard...");
                return null;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    private Service AddNewService(Provider provider){
        try {
            System.out.println("Adding new service...");
            boolean validCode = false;
            int serviceCode = 0;
            while (!validCode) {
                System.out.print("Please enter a 6 digit service code: ");
                serviceCode = GetNumberInputWithBoundCheck(100000, 999999);
                if (Service.serviceDirectory.containsKey(serviceCode)) {
                    System.out.println("Service entered is " + Service.serviceDirectory.get(serviceCode));
                    System.out.println("Is this correct? (0/1): ");
                    if (GetNumberInputWithBoundCheck(0, 1) == 1) {
                        validCode = true;
                    }
                }
            }
            String serviceName = Service.serviceDirectory.get(serviceCode);
            System.out.print("Please enter date of service (MM-DD-YYYY): ");
            Date serviceDate  = new SimpleDateFormat("MM-dd-yyyy")
                    .parse(strScanner.nextLine());

            int providerID = provider.getNumber();

            System.out.print("Please enter the fee to be paid (Up to $999.99): ");
            float paidFee = GetNumberInputWithBoundCheck(0.0f, 999.99f);

            System.out.print("Please enter a member's ID that the service is associated with: ");
            int memberID = GetNumberInputWithBoundCheck(100000000, 999999999);;
            Member curMember;
            while((curMember = Database.MEMBERS.get(memberID).orElseGet(null)) == null){
                System.out.println("Can't find member with id: " + memberID + " on the system!");
                System.out.print("Please reenter a new ID or -1 to abort: ");
                memberID = GetNumberInputWithBoundCheck(100000000, 999999999, true);;

                if (memberID == -1){
                    System.out.println("New info discard... Rolling back.");
                    return null;
                }
            }

            System.out.println("\nAre you sure you want to create a new service with: ");
            System.out.println("\tName: " + serviceName);
            System.out.println("\tService Date: " + serviceDate);
            System.out.println("\tProvider ID: " + providerID);
            System.out.println("\tService Code: " + serviceCode);
            System.out.println("\tFee to be paid: " + paidFee);
            System.out.println("\tAssociated member ID: " + memberID);

            System.out.print("Your choice (0/1): ");
            if (GetNumberInputWithBoundCheck(0, 1) == 1){
                int serviceId = Database.SERVICES.getNextId();
                Service newService = new Service(serviceDate, providerID, serviceId,
                        serviceCode, paidFee, serviceName);

                //Also add the service to the provider list
                provider.addService(curMember, newService);
                //and to the associated member's service list
                curMember.addService(newService);
                Database.SERVICES.add(newService);
                System.out.println("New service created!");
                return newService;
            } else {
                System.out.println("Input info discard...");
                return null;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void UpdateProvider(Provider provider){
        if (provider == null){
            return;
        }
        DisplayProviderInfo(provider);

        boolean onMenu = true;
        while (onMenu == true){
            DisplaySubMenu(0);
            System.out.print("Your Menu choice: ");
            int choice = GetNumberInputWithBoundCheck(-1, 8);

            switch(choice){
                case -1:
                    System.out.println("Rolling back to previous menu...");
                    onMenu = false;
                    break;
                case 0:
                    ClearScreen();
                    break;
                case 1:
                    break;
                case 2:
                    DisplayProviderInfo(provider);
                    break;
                case 3:
                    System.out.print("Please enter a new provider's name: ");
                    String newName = strScanner.nextLine();
                    System.out.println("Name: " + provider.getName() + "\n  will be changed to " + newName);
                    System.out.print("Are you sure (0/1): ");
                    if (GetNumberInputWithBoundCheck(0, 1) == 1){
                        provider.setName(newName);
                        System.out.println("Name changed!");
                    } else {
                        System.out.println("New name discard!");
                    }
                    break;
                case 4:
                    System.out.print("Please enter a new provider's street address: ");
                    String street = strScanner.nextLine();
                    System.out.println("Street address: " + provider.getCoordinate().getStreetAddress() + "\n  will be changed to " + street);
                    System.out.print("Are you sure (0/1): ");
                    if (GetNumberInputWithBoundCheck(0, 1) == 1){
                        provider.getCoordinate().setStreetAddress(street);
                        System.out.println("Street address changed!");
                    } else {
                        System.out.println("New street address discard!");
                    }
                    break;
                case 5:
                    System.out.print("Please enter a new provider's city: ");
                    String city = strScanner.nextLine();
                    System.out.println("City: " + provider.getCoordinate().getCity() + "\n  will be changed to " + city);
                    System.out.print("Are you sure (0/1): ");
                    if (GetNumberInputWithBoundCheck(0, 1) == 1){
                        provider.getCoordinate().setCity(city);
                        System.out.println("City changed!");
                    } else {
                        System.out.println("New city discard!");
                    }
                    break;
                case 6:
                    System.out.print("Please enter new provider's state: ");
                    String state = strScanner.nextLine();
                    System.out.println("State: " + provider.getCoordinate().getState() + "\n  will be changed to " + state);
                    System.out.print("Are you sure (0/1): ");
                    if (GetNumberInputWithBoundCheck(0, 1) == 1){
                        provider.getCoordinate().setState(state);
                        System.out.println("State changed!");
                    } else {
                        System.out.println("New state discard!");
                    }
                    break;
                case 7:
                    System.out.print("Please enter new provider's zip code (5 digits): ");
                    int zipCode = GetNumberInputWithBoundCheck(10000, 99999);
                    System.out.println("Zip Code: " + provider.getCoordinate().getZipCode() + "\n  will be changed to " + zipCode);
                    System.out.print("Are you sure (0/1): ");
                    if (GetNumberInputWithBoundCheck(0, 1) == 1){
                        provider.getCoordinate().setZipCode(zipCode);
                        System.out.println("Zip Code changed!");
                    } else {
                        System.out.println("New zip code discard!");
                    }
                    break;
                case 8:
                    System.out.print("Please enter service code (6 digits): ");
                    int serviceCode = GetNumberInputWithBoundCheck(100000, 999999);
                    Service curService;
                    while(((curService = Database.SERVICES.get(serviceCode).orElseGet(null)) == null)             //Can't find service
                        || curService.getProviderId() != provider.getNumber()                           //Service doesn't belong to this provider
                    ){
                        System.out.println("Can't find that service for this provider!");
                        System.out.print("Please reenter the id or -1 to abort: ");
                        serviceCode = GetNumberInputWithBoundCheck(100000, 999999, true);;

                        if(serviceCode == -1){
                            break;
                        }
                    }

                    if(curService != null){
                        UpdateService(curService);
                    }
                    break;
            }
        }
    }
    private void DisplayProviderInfo(Provider provider){
        if (provider == null){
            return;
        }

        //Display all the info first
        Coordinate coord = provider.getCoordinate();
        System.out.println("Provider info: ");
        System.out.println("Name: " + provider.getName());
        System.out.println("Provider's ID: " + provider.getNumber());
        System.out.println("Provider's Address: ");
        System.out.println("\tStreet Address: " + coord.getStreetAddress());
        System.out.println("\tCity: " + coord.getCity());
        System.out.println("\tState: " + coord.getState());
        System.out.println("\tZip code: " + coord.getZipCode());
        System.out.println("Weekly fee: " + provider.totalWeeklyFees());
        System.out.println("Weekly Consultation: " + provider.totalWeeklyConsultations());
        System.out.println("Provider's services: ");

        for(ConcurrentHashMap.Entry<Member, ArrayList<Service>> entry : provider.getServices().entrySet()){
            System.out.println("\tMember's name: " + entry.getKey().getName());
            System.out.println("\tMember's ID: " + entry.getKey().getName());
            System.out.println("\tMember's services: ");
            for(Service service : entry.getValue()){
                System.out.println("\t\tService's name: " + service.getServiceName());
                System.out.println("\t\tService's code: " + service.getServiceCode());
                System.out.println("\t\tService's date: " + service.getServiceDate());
                System.out.println("\t\tDate into system: " +
                        new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(service.getReceiveDate()));
                System.out.println("\t\tFee to be paid: $" + service.getPaidFee());
                System.out.println("\t\t----------------------------------------");
            }
        }
    }
    private void UpdateMember(Member member){
        if(member == null){
            return;
        }
        DisplayMemberInfo(member);

        boolean onMenu = true;
        while(onMenu == true){
            DisplaySubMenu(1);
            System.out.print("Your Menu choice: ");
            int choice = GetNumberInputWithBoundCheck(-1, 9);

            switch(choice) {
                case -1:
                    System.out.println("Rolling back to previous menu...");
                    onMenu = false;
                    break;
                case 0:
                    ClearScreen();
                    break;
                case 1:
                    break;
                case 2:
                    DisplayMemberInfo(member);
                    break;
                case 3:
                    System.out.print("Please enter a new member's name: ");
                    String newName = strScanner.nextLine();
                    System.out.println("Name: " + member.getName() + "\n  will be changed to " + newName);
                    System.out.print("Are you sure (0/1): ");
                    if (GetNumberInputWithBoundCheck(0, 1) == 1) {
                        member.setName(newName);
                        System.out.println("Name changed!");
                    } else {
                        System.out.println("New name discard!");
                    }
                    break;
                case 4:
                    System.out.print("Please enter a new member's street address: ");
                    String street = strScanner.nextLine();
                    System.out.println("Street address: " + member.getCoordinate().getStreetAddress() + "\n  will be changed to " + street);
                    System.out.print("Are you sure (0/1): ");
                    if (GetNumberInputWithBoundCheck(0, 1) == 1) {
                        member.getCoordinate().setStreetAddress(street);
                        System.out.println("Street address changed!");
                    } else {
                        System.out.println("New street address discard!");
                    }
                    break;
                case 5:
                    System.out.print("Please enter a new member's city: ");
                    String city = strScanner.nextLine();
                    System.out.println("City: " + member.getCoordinate().getCity() + "\n  will be changed to " + city);
                    System.out.print("Are you sure (0/1): ");
                    if (GetNumberInputWithBoundCheck(0, 1) == 1) {
                        member.getCoordinate().setCity(city);
                        System.out.println("City changed!");
                    } else {
                        System.out.println("New city discard!");
                    }
                    break;
                case 6:
                    System.out.print("Please enter a new member's state: ");
                    String state = strScanner.nextLine();
                    System.out.println("State: " + member.getCoordinate().getState() + "\n  will be changed to " + state);
                    System.out.print("Are you sure (0/1): ");
                    if (GetNumberInputWithBoundCheck(0, 1) == 1) {
                        member.getCoordinate().setState(state);
                        System.out.println("State changed!");
                    } else {
                        System.out.println("New state discard!");
                    }
                    break;
                case 7:
                    System.out.print("Please enter a new member's zip code (5 digits): ");
                    int zipCode = GetNumberInputWithBoundCheck(10000, 99999, true);
                    System.out.println("Zip Code: " + member.getCoordinate().getZipCode() + "\n  will be changed to " + zipCode);
                    System.out.print("Are you sure (0/1): ");
                    if (GetNumberInputWithBoundCheck(0, 1) == 1) {
                        member.getCoordinate().setZipCode(zipCode);
                        System.out.println("Zip Code changed!");
                    } else {
                        System.out.println("New zip code discard!");
                    }
                    break;
                case 8:
                    System.out.print("Please enter a new member's suspension status (0/1): ");
                    int isSuspended = GetNumberInputWithBoundCheck(0, 1);
                    System.out.println("Suspension status: " + (member.isSuspended() == true ? "Yes" : "No") + "\n  will be changed to " + (isSuspended == 1 ? "Yes" : "No"));
                    System.out.print("Are you sure (0/1): ");
                    if (GetNumberInputWithBoundCheck(0, 1) == 1) {
                        member.setSuspended(isSuspended == 1);
                        System.out.println("Suspension status changed!");
                    } else {
                        System.out.println("Suspension status unchanged!");
                    }
                    break;
                case 9:
                    System.out.print("Please enter service code (6 digits): ");
                    int serviceCode = GetNumberInputWithBoundCheck(100000, 999999);
                    Service curService = null;
                    for(Service service : member.getServices()){
                        if (service.getServiceCode() == serviceCode){
                            curService = service;
                            break;
                        }
                    }

                    while (curService == null){
                        System.out.println("Can't find that service for this member!");
                        System.out.print("Please reenter the code or -1 to abort: ");
                        serviceCode = GetNumberInputWithBoundCheck(100000, 999999, true);

                        if (serviceCode == -1){
                            break;
                        } else {
                            for(Service service : member.getServices()){
                                if (service.getServiceCode() == serviceCode){
                                    curService = service;
                                    break;
                                }
                            }
                        }
                    }
                    if (curService != null) {
                        UpdateService(curService);
                    }
                    break;
                }
            }
    }
    private void DisplayMemberInfo(Member member){
        if(member == null){
            return;
        }
        Coordinate coord = member.getCoordinate();
        System.out.println("Member's name: " + member.getName());
        System.out.println("Member's ID: " + member.getNumber());
        System.out.println("Member's Address: ");
        System.out.println("\tStreet Address: " + coord.getStreetAddress());
        System.out.println("\tCity: " + coord.getCity());
        System.out.println("\tState: " + coord.getState());
        System.out.println("\tZip code: " + coord.getZipCode());
        System.out.println("Member's services: ");
        for(Service service : member.getServices()){
            System.out.println("\tService's name: " + service.getServiceName());
            System.out.println("\tService's code: " + service.getServiceCode());
            System.out.println("\tService's date: " + service.getServiceDate());
            System.out.println("\tDate into system: " +
                    new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(service.getReceiveDate()));
            System.out.println("\tFee to be paid: $" + service.getPaidFee());
            System.out.println("\t----------------------------------------");
        }
    }
    private void UpdateService(Service service) {
        if (service == null) {
            return;
        }
        DisplayServiceInfo(service);

        boolean onMenu = true;

        while (onMenu == true) {
            DisplaySubMenu(2);
            System.out.print("Your Menu choice: ");
            int choice = GetNumberInputWithBoundCheck(-1, 5);

            switch (choice) {
                case -1:
                    System.out.println("Rolling back to previous menu...");
                    onMenu = false;
                    break;
                case 0:
                    ClearScreen();
                    break;
                case 1:
                    break;
                case 2:
                    DisplayServiceInfo(service);
                    break;
                case 3:
                    System.out.print("Please enter the new service code (6 digits): ");
                    int newCode = GetNumberInputWithBoundCheck(100000, 999999);
                    if (Service.serviceDirectory.containsKey(newCode)) {
                        System.out.println(service.getServiceName() + "`\nwill be changed to "
                                + Service.serviceDirectory.get(newCode));
                        System.out.print("Is this correct? (0/1): ");
                        if (GetNumberInputWithBoundCheck(0, 1) == 1) {
                            service.setServiceCode(newCode);
                            service.setServiceName(Service.serviceDirectory.get(newCode));
                            System.out.println("Service changed!");
                        } else {
                            System.out.println("Change discarded!");
                        }
                    } else {
                        System.out.println("Service not found.");
                    }
                    break;
                case 4:
                    System.out.print("Please enter a new service's date (MM-DD-YYYY): ");
                    try {
                        Date newDate = new SimpleDateFormat("MM-dd-yyyy").parse(strScanner.nextLine());
                        System.out.println("Name: " + new SimpleDateFormat("MM-dd-yyyy").format(service.getServiceDate())
                                + "\n  will be changed to " + new SimpleDateFormat("MM-dd-yyyy").format(newDate));
                        System.out.print("Are you sure (0/1): ");
                        if (GetNumberInputWithBoundCheck(0, 1) == 1) {
                            service.setServiceDate(newDate);
                            System.out.println("Date changed!");
                        } else {
                            System.out.println("New date discard!");
                        }

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    System.out.print("Please enter a new service's fee to be paid: ");
                    float newFee = numScanner.nextFloat();
                    System.out.println("Fee: " + service.getPaidFee() + "\n  will be changed to " + newFee);
                    System.out.print("Are you sure (0/1): ");
                    if (GetNumberInputWithBoundCheck(0, 1) == 1) {
                        service.setPaidFee(newFee);
                        System.out.println("Fee changed!");
                    } else {
                        System.out.println("New fee discard!");
                    }
                    break;
            }
        }
    }
    private void DisplayServiceInfo(Service service){
        if(service == null){
            return;
        }

        System.out.println("==== Service #" + service.getServiceId() + " ====");
        System.out.println("Service's Name: " + service.getServiceName());
        System.out.println("Service's Code: " + service.getServiceCode());
        System.out.println("Service's Date: " + new SimpleDateFormat("MM-dd-yyyy").format(service.getServiceDate()));
        System.out.println("Date service entered system (can't be change): " + new SimpleDateFormat("MM-dd-yyyy").format(service.getReceiveDate()));
        System.out.println("Fee to be paid: " + service.getPaidFee());
        System.out.println("Associated provider's ID (can't be change): " + service.getProviderId());
    }
    private void DisplaySubMenu(int option){
        int indx = -1;
        switch(option){
            case 0: //Update Provider sub-menu: -1 -> 10
                System.out.println("Please choose an option: ");
                System.out.println("\t" + indx++ +  ": Go back to previous menu");
                System.out.println("\t " + indx++ + ": Clear Screen");
                System.out.println("\t " + indx++ + ": Redisplay Menu");
                System.out.println("\t " + indx++ + ": Redisplay all Entries");
                System.out.println("\t " + indx++ + ": Change provider's name");
                System.out.println("\t " + indx++ + ": Change provider's street address");
                System.out.println("\t " + indx++ + ": Change provider's city");
                System.out.println("\t " + indx++ + ": Change provider's state");
                System.out.println("\t " + indx++ + ": Change provider's zip code");
                System.out.println("\t " + indx++ + ": Change provider's service");
                break;
            case 1: //Update Member sub-menu: -1 -> 9
                System.out.println("Please choose an option: ");
                System.out.println("\t" + indx++ +  ": Go back to previous menu");
                System.out.println("\t " + indx++ + ": Clear Screen");
                System.out.println("\t " + indx++ + ": Redisplay Menu");
                System.out.println("\t " + indx++ + ": Redisplay all Entries");
                System.out.println("\t " + indx++ + ": Change member's name");
                System.out.println("\t " + indx++ + ": Change member's street address");
                System.out.println("\t " + indx++ + ": Change member's city");
                System.out.println("\t " + indx++ + ": Change member's state");
                System.out.println("\t " + indx++ + ": Change member's zip code");
                System.out.println("\t " + indx++ + ": Change member's suspension status");
                System.out.println("\t " + indx++ + ": Change member's service");
                break;
            case 2: //Update Service sub-menu: -1 -> 6
                System.out.println("Please choose an option: ");
                System.out.println("\t" + indx++ +  ": Go back to previous menu");
                System.out.println("\t " + indx++ + ": Clear Screen");
                System.out.println("\t " + indx++ + ": Redisplay Menu");
                System.out.println("\t " + indx++ + ": Redisplay service's info");
                System.out.println("\t " + indx++ + ": Change service code");
                System.out.println("\t " + indx++ + ": Change service's date");
                System.out.println("\t " + indx++ + ": Change service's fee to be paid");
                break;
        }


    }
}

