package Journal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Set;

public class PopUp { // presenter class
    ItemListener listener;
    JournalController controller;
    public PopUp(MakeDir dir){
        this.controller = new JournalController(dir);
    }


    public String[] viewAndAddEntryPopUp(String[] defaultEntryInput, String titleOfPopUp){
        JTextField title = new JTextField();
        JTextField tags = new JTextField();
        JTextField entry = new JTextField();
        title.setText(defaultEntryInput[0]);
        tags.setText(defaultEntryInput[1]);
        entry.setText(defaultEntryInput[2]);
        Object[] message = {
                "Title:", title,
                "Tags:", tags,
                "Entry:", entry,};
        int option = JOptionPane.showConfirmDialog(null, message, titleOfPopUp, JOptionPane.OK_CANCEL_OPTION);
        String[] modifiedEntryInfo = {title.getText(), tags.getText(), entry.getText()};
        return modifiedEntryInfo;
    }
    public String[] editedEntry(String[]  defaultEntryInput, String titleOfPopUp){
        String[] modifiedEntryInfo = viewAndAddEntryPopUp(defaultEntryInput, titleOfPopUp);
        String titleModified = modifiedEntryInfo[0];
        String tagsModified = modifiedEntryInfo[0];
        String entryModified = modifiedEntryInfo[0];
        String[] modifiedJournalEntry = {titleModified, tagsModified, entryModified};
        return modifiedJournalEntry;
    }

    public String[] addEntryPopUp(){
        String[] noDefaultInput = new String[3];
        String[] userInput = viewAndAddEntryPopUp(noDefaultInput, "Journal");

        String title =userInput[0];
        String tags = userInput[1];
        String entry = userInput[2];
        String[] JournalEntry = {title, tags, entry};
        return JournalEntry;

    }

    public String deleteEntryPopUp(){ // going to be in presenter class
        Choice possibleEntriesToDelete = new Choice();
        Frame JournalEntries = new Frame("Choose an entry to delete");
        Set<String> allJournalEntries = controller.callGetAllEntries();
        for (String entryTitle : allJournalEntries){
            possibleEntriesToDelete.add(entryTitle);
        }
        possibleEntriesToDelete.addItemListener(listener);
        String entryToDelete = possibleEntriesToDelete.getSelectedItem();
        return entryToDelete;

    }

}
