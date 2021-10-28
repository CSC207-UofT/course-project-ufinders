package Journal;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

public class PopUp { // presenter class

    JournalController controller;

    public PopUp( JournalController controller) {

        this.controller = controller;
    }

    public String[] viewAndAddEntryPopUp(String[] defaultEntryInput) {
        JTextField title = new JTextField();
        JTextField tags = new JTextField();
        JTextField entry = new JTextField();
        title.setText(defaultEntryInput[1]);
        tags.setText(defaultEntryInput[2]);
        entry.setText(defaultEntryInput[3]);
        Object[] message = {
                "Title:", title,
                "Tags:", tags,
                "Entry:", entry,};
        int option = JOptionPane.showConfirmDialog(null, message, defaultEntryInput[0], JOptionPane.OK_CANCEL_OPTION);
        String[] modifiedEntryInfo = {title.getText(), tags.getText(), entry.getText()};
        return modifiedEntryInfo;
    }



    public String[] addEntryPopUp() {
        String[] noDefaultInput = new String[4];
        noDefaultInput[0] = "Journal";
        String[] userInput = viewAndAddEntryPopUp(noDefaultInput);

        String title = userInput[0];
        String tags = userInput[1];
        String entry = userInput[2];
        String[] JournalEntry = {title, tags, entry};
        return JournalEntry;

    }

    public String deleteEntryPopUp() { // going to be in presenter class

        Set<String> allJournalEntries = controller.callGetAllEntries();
        Object[] possibleEntriesToDelete = new String[allJournalEntries.size()];
        int i = 0;
        for (String entryTitle : allJournalEntries) {
            possibleEntriesToDelete[i] = entryTitle;
            i += 1;
        }
       return (String)JOptionPane.showInputDialog(null, "choose an entry to delete",
               "Delete a journal entry", JOptionPane.QUESTION_MESSAGE, null, possibleEntriesToDelete,
               null);
    }

    public String viewEntriesPopUp() {
        Set<String> allJournalEntries = controller.callGetAllEntries();
        Object[] possibleEntriesToView = new String[allJournalEntries.size()];
        int i = 0;
        for (String entryTitle : allJournalEntries) {
            possibleEntriesToView[i] = entryTitle;
            i += 1;
        }
        return (String)JOptionPane.showInputDialog(null, "choose an entry to View",
                "Delete a journal entry", JOptionPane.QUESTION_MESSAGE, null, possibleEntriesToView,
                null);
    }

    public String viewUserOptions() {

        Object[] userOptions = {"add an entry", "view/edit entries", "delete an entry", "exit journal"};

        return (String)JOptionPane.showInputDialog(null, "What would you like to do",
                "Choose what you would like to do in journal", JOptionPane.QUESTION_MESSAGE, null, userOptions,
                null);
    }
}
