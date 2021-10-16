package codestuff.Journal;

import java.time.LocalDate;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class JournalUI {
    // object we will call to add and view journal entry
    public JournalController controller;

    public JournalUI(){
        controller = new JournalController();
    }

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        JournalUI journalView = new JournalUI();

        // Asks user if they want to journal to exit the journal program
        System.out.println("Type exit to leave the program or continue to keep journaling");
        // Journal program keeps running until the user types exit
        while(!Objects.equals(read.nextLine(), "exit")){




// User want to add a journal entry


            System.out.println("Title of entry:");
            String title = read.nextLine();
            System.out.println("Entry:");
            String content = read.nextLine();
            System.out.println("Enter each tag you would like to add seperated by a comma:");
            String[] ArrayOfTags = read.nextLine().split(",");
            // Convert the string array of tags to a string array list
            ArrayList<String> tags = new ArrayList<>();
            Collections.addAll(tags, ArrayOfTags);
// get the date of when the user is writing the journal entry
            LocalDate today = LocalDate.now();
            // creat journal entry that user wants and store it and returns the string representation of the
            // journal entry
            System.out.println(journalView.controller.callCreateEntry(title, content, today, tags));
            System.out.println("Type exit to leave the program or continue to keep journaling");
    }

}

}
