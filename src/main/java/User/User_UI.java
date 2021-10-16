package java.User;

import java.Journal.JournalUI;
import java.util.Scanner;
import java.util.Objects;


public class User_UI {

    public static void main(String[] args){
        Scanner read = new Scanner(System.in);
        User_UI userview = new User_UI();

        // Asks user if they want to exit the UserUI
        System.out.println("Type 'exit' to leave the program or sign in");
        // Program keeps the user on the login section until they exit
        String id = null;
        String name = null;
        while(!Objects.equals(read.nextLine(), "exit") && (id == null && name == null) ){
            System.out.println("UTorID:");
            id = read.nextLine();
            System.out.println("Name:");
            name = read.nextLine();
        }
        if(id != null && name != null){
            User newuser = Create_User.makeUserObject(id, name);
            while(!Objects.equals(read.nextLine(), "exit")) {
                System.out.println("Would you like to access the journal or the events tab?");
                System.out.println("Type 'journal' or 'events'");
                String choice = read.nextLine();
                if (Objects.equals(choice, "journal")){
                    new JournalUI();}
                else if (Objects.equals(choice, "events")){
                    EventUI.displayEvent();
                }
            }
        }
    }

}
