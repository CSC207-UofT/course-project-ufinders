package Journal;

import javax.swing.*;
import java.util.Set;

public class JournalWindow { // presenter class




    /**
     * Creates a pop-up window with the title, tags, entry and date in defaultEntryInput for the user to view and edit
     * @param defaultEntryInput Contains information about entry to display to user in the format
     *                          [date of entry, title, tags, entry]
     * @return an array in the format [date of entry, title, tags, entry] which may contain modifies title, entry
     * and/or tags

     */
    public String[] viewAndAddEntryPopUp(String[] defaultEntryInput) {
        JTextField title = new JTextField(15);
        JTextField tags = new JTextField(15);
        JTextArea entry = new JTextArea(7, 35);

        title.setText(defaultEntryInput[1]);
        tags.setText(defaultEntryInput[2]);
        entry.setText(defaultEntryInput[3]);
        Object[] message = {
                "Title:", title,
                "Tags:", tags,
                "Entry:", entry,};
        ImageIcon icon = new ImageIcon("journaling.png");
        int option = JOptionPane.showConfirmDialog(null, message, defaultEntryInput[0],
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, icon);
        if (option == JOptionPane.CANCEL_OPTION) {
            return null;
        } else {
            return new String[]{title.getText(), tags.getText(), entry.getText()};
        }
    }


    /**
     * Calls viewAndAddEntryPopUP to create a window prompting user to create a new entry asking for its title, tags
     * and content
     * @return an array in the format [date of entry, title, tags, entry]  that the user inputted into the pop-up window

     */
    public String[] addEntryPopUp() {
        String[] noDefaultInput = new String[4];
        noDefaultInput[0] = "Journal";
        return viewAndAddEntryPopUp(noDefaultInput);
    }

    /**
     * Creates a pop-up window with list of user's entries which they can choose from to delete one or more entries
     * @param allEntries A set of the title of all the user's journal entries
     * @return the title of the journal entry the user choose to delete
     */
    public String deleteEntryPopUp(Set<String> allEntries) { // going to be in presenter class

        Object[] possibleEntriesToDelete = new String[allEntries.size()];
        int i = 0;
        for (String entryTitle : allEntries) {
            possibleEntriesToDelete[i] = entryTitle;
            i += 1;
        }
       return (String)JOptionPane.showInputDialog(null, "choose an entry to delete",
               "Delete a journal entry", JOptionPane.QUESTION_MESSAGE, null, possibleEntriesToDelete,
               null);
    }
    /**
     * Creates a pop-up window with list of entries that user wants to view. The user can choose one entry to view.
     * @return the title of the entry the user wants to view
     */

    public String viewEntriesPopUp(Set<String> allEntries) {
        Object[] possibleEntriesToView = new String[allEntries.size()];
        int i = 0;
        for (String entryTitle : allEntries) {
            possibleEntriesToView[i] = entryTitle;
            i += 1;
        }
        return (String)JOptionPane.showInputDialog(null, "choose an entry to View",
                "View a journal entry", JOptionPane.QUESTION_MESSAGE, null, possibleEntriesToView,
                null);
    }

    /**
     * Creates a pop-up window with list of things that user wants to do in journal such as add, view/edit and delete
     * entry
     * @return the action the user want to do
     */
    public String viewUserOptions() {

        Object[] userOptions = {"add an entry", "view/edit entries", "delete an entry", "exit journal"};

        return (String)JOptionPane.showInputDialog(null, "What would you like to do",
                "Choose what you would like to do in journal", JOptionPane.QUESTION_MESSAGE, null, userOptions,
                null);
    }

    /**
     * Creates a pop-up window with a warning that an entry with given title already exists
     */

    public void entryWithTitleAlreadyExists(){
        ImageIcon icon = new ImageIcon("warning.png");
        JOptionPane.showMessageDialog(null, "Journal entry with this title already exists. " +
                "Please choose another title!", "warning", JOptionPane.ERROR_MESSAGE, icon);
    }


}
