package User;

import java.util.Scanner;
import java.util.Objects;
import Journal.JournalUI;
import Events.EventUI;


public class User_UI {

    public static void intro(){
        Scanner read = new Scanner(System.in);

        // Asks user if they want to exit the UserUI
        System.out.println("Type 'exit' to leave the program or 'sign in' to sign in");
        String id = null;
        String name = null;
        // Program keeps the user on the login section until they exit
        while(!Objects.equals(read.nextLine(), "exit") || (id == null && name == null) ){
            // Asks the user to enter their UTorID:
            System.out.println("UTorID:");
            id = read.nextLine();
            // Asks the user to enter their Name:
            System.out.println("Name:");
            name = read.nextLine();
        }
        System.out.println("Type 'exit' to leave the program or 'done' to sign in");
        // Checks that the user has entered a UTorID and Name
        if(id != null && name != null){
            while(!Objects.equals(read.nextLine(), "exit")) {
                // Asks the user where they want to proceed next
                System.out.println("Would you like to access the journal or the events tab?");
                System.out.println("Type 'journal' or 'events'");
                String choice = read.nextLine();
                // Lead the user to Journal if they chose Journal
                if (Objects.equals(choice, "journal")){
                    new JournalUI();}
                // Lead the user to Events if they chose the events tab
                else if (Objects.equals(choice, "events")){
                    new EventUI();
                }
            }
        }
        Main.MainController.menu()
    }

}
