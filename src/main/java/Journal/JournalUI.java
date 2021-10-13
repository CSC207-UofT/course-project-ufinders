package Journal;

import java.time.LocalDate;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class JournalUI {
    public JournalController controller;
    public JournalUI(){
        controller = new JournalController();


    }
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        JournalUI journalView = new JournalUI();

            System.out.println("Type exit to leave the program or continue to keep journaling");
        while(!Objects.equals(read.nextLine(), "exit")){

        System.out.println("Type add if you want to add an entry or type view if you want to view an entry:");
        String userWants = read.nextLine();


        if (Objects.equals(userWants, "add")) {

            System.out.println("Title of entry:");
            String title = read.nextLine();
            System.out.println("Entry:");
            String content = read.nextLine();
            System.out.println("Enter each tag you would like to add seperated by a comma:");
            String[] ArrayOfTags = read.nextLine().split(",");
            ArrayList<String> tags = new ArrayList<>();
            Collections.addAll(tags, ArrayOfTags);

            LocalDate today = LocalDate.now();
            journalView.controller.callCreateEntry(title, content, today, tags);


        }
        else{
            System.out.println("Title of entry you want to view:");
            String title = read.nextLine();
            System.out.println(journalView.controller.viewEntry(title));

        }
            System.out.println("Type exit to leave the program or continue to keep journaling");
    }

}

}
