package Journal;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Scanner;

public class JournalUI {
    public JournalController controller;

    public JournalUI(){
        MakeDir dir = new MakeDir(FileSystemView.getFileSystemView()
                        .getHomeDirectory()
                        .getAbsolutePath() + "/" +"Documents" + "/"  + "Journal Entries");
        this.controller = new JournalController(dir);
    }

    public void addEntry(){
        String[] newEntry = this.addEntryPopUp();
        String title = newEntry[0];
        String content = newEntry[2];
        String tags = newEntry[1];
        LocalDate today = LocalDate.now();
        this.controller.callCreateEntry(title, content, today, tags );
    }

    public void deleteEntry(){
        Scanner read = new Scanner(System.in);
        System.out.println("Title of entry you would like to delete:");
        String title = read.nextLine();
        this.controller.callDeleteEntry(title );

    }

    public void viewEntry(){
        Scanner read = new Scanner(System.in);
        System.out.println("Title of entry you would like to view:");
        String titleOfEntryToView = read.nextLine();
        String[] entryInfo = this.controller.callGetEntry(titleOfEntryToView);
        String[] modifiedJournalEntry = viewEntryPopUp(titleOfEntryToView, entryInfo);
        this.controller.callDeleteEntry(titleOfEntryToView);
        this.controller.callCreateEntry(modifiedJournalEntry[0], modifiedJournalEntry[2], LocalDate.parse(entryInfo[0]), modifiedJournalEntry[1]);

    }

    public String[] viewEntryPopUp(String titleOfEntryToView, String[] entryInfo){
        JTextField title = new JTextField();
        JTextField tags = new JTextField();
        JTextField entry = new JTextField();

        title.setText(titleOfEntryToView);
        tags.setText(entryInfo[1]);
        entry.setText(entryInfo[2]);
        Object[] message = {
                "Title:", title,
                "Tags:", tags,
                "Entry:", entry,};
        int option = JOptionPane.showConfirmDialog(null, message, entryInfo[0], JOptionPane.OK_CANCEL_OPTION);

        String titleModified = title.getText();
        String tagsModified = tags.getText();
        String entryModified = entry.getText();
        String[] modifiedJournalEntry = {titleModified, tagsModified, entryModified};
        return modifiedJournalEntry;
    }

    public String[] addEntryPopUp(){
        JTextField titleEntered = new JTextField();
        JTextField tagsEntered = new JTextField();
        JTextField entryEntered = new JTextField();

        Object[] message = {
                "Title:", titleEntered,
                "Tags:", tagsEntered,
                "Entry:", entryEntered,};
        int option = JOptionPane.showConfirmDialog(null, message, "Journal", JOptionPane.OK_CANCEL_OPTION);

        String title = titleEntered.getText();
        String tags = tagsEntered.getText();
        String entry = entryEntered.getText();
        String[] JournalEntry = {title, tags, entry};
        return JournalEntry;

    }

    public static void main(String[] args) {
        JournalUI UI = new JournalUI();
        // implement popup so user can choose what they want to do in journal
        UI.addEntry();
        UI.deleteEntry();
        UI.viewEntry();


    }
}


