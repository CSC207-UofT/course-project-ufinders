package Journal;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Scanner;
import java.util.Set;

public class JournalUI {
    public JournalController controller;
    public PopUp popUpWindow;




    public JournalUI(){
        MakeDir dir = new MakeDir(FileSystemView.getFileSystemView()
                        .getHomeDirectory()
                        .getAbsolutePath() + "/" +"Documents" + "/"  + "Journal Entries");
        this.controller = new JournalController(dir);
        this.popUpWindow = new PopUp(dir);

    }

    public void addEntry(){

        String[] newEntry = this.popUpWindow.addEntryPopUp();
        for (int i = 0; i < newEntry.length; i += 1){
            if(newEntry[i] == null){
                newEntry[i] = "";

            }
        }
        LocalDate today = LocalDate.now();
        this.controller.callCreateEntry(newEntry[0], newEntry[2], today, newEntry[1] );
    }

    public void deleteEntry(){

        String entryToDelete = this.popUpWindow.deleteEntryPopUp();
        controller.callDeleteEntry(entryToDelete);

    }




    public void viewEntry(){
        Scanner read = new Scanner(System.in);
        System.out.println("Title of entry you would like to view:");
        String titleOfEntryToView = read.nextLine();
        String[] entryInfo = this.controller.callGetEntry(titleOfEntryToView);
        String[] modifiedJournalEntry = this.popUpWindow.viewAndAddEntryPopUp(entryInfo, titleOfEntryToView);
        this.controller.callEditEntry(titleOfEntryToView, modifiedJournalEntry[0], modifiedJournalEntry[2],
                LocalDate.parse(entryInfo[0]), modifiedJournalEntry[1]);
    }







    public static void main(String[] args) {
        JournalUI UI = new JournalUI();
        // implement popup so user can choose what they want to do in journal
        UI.addEntry();
        UI.deleteEntry();
        //UI.viewEntry();


    }
}


