package Journal;

import User.MakeDir;

import javax.swing.filechooser.FileSystemView;
import java.time.LocalDate;
import java.util.Objects;

public class JournalUI {
    // object the JournalUI calls on to do the work its given
    public JournalController controller;
    // objects the JournalUI calls to create a pop-up window and get information that is inputted
    public JournalWindow popUpWindow;

    // number of entries with no title
    public int titleLessEntries;




    public JournalUI(){
        MakeDir dir = new MakeDir(FileSystemView.getFileSystemView()
                .getHomeDirectory()
                .getAbsolutePath() + "/" +"Documents" + "/"  + "Journal Entries");
        this.controller = new JournalController(new JournalFileGateway(dir.getPath()));
        this.popUpWindow = new JournalWindow();
        this.titleLessEntries += 0;

    }

    /**
     * Calls popUpWindow to create a pop-up window prompting user to add an entry. Gets input from pop-window and
     * passes it to controller to create entry with the given information and store it in dir.
     */

    public void addEntry() {

        String[] newEntry = this.popUpWindow.addEntryPopUp();
        if (newEntry != null) {
            for (int i = 0; i < newEntry.length; i += 1) {
                if (newEntry[i] == null) {
                    newEntry[i] = "";

                }
            }
            newEntry[0] = checkEntryHasTitle(newEntry[0]);
            LocalDate today = LocalDate.now();
            this.controller.callCreateEntry(newEntry[0], newEntry[2], today, newEntry[1]);
        }
    }

    /**
     * check whether title is an empty string, if it is  "Untitled"+ " " + titleLessEntries is returned and
     * if not title is returned
     * @param title string that is checked if it is empty string
     * @return  "Untitled"+ " " + titleLessEntries if title is empty string and title if title is not an empty string
     */

    public String checkEntryHasTitle(String title){
        if (Objects.equals(title, "")){
            String newTitle = "Untitled"+ " " + titleLessEntries;
            titleLessEntries += 1;
            return newTitle;
        }
        else{
            return title;
        }
    }


    /**
     * Calls popUpWindow to create a pop-up window prompting user to  choose from a list of entries to delete. Gets
     * the entry the user wants to delete and calls controller to delete that entry.
     */

    public void deleteEntry(){
        String entryToDelete = this.popUpWindow.deleteEntryPopUp(controller.callGetAllEntries());
        if (entryToDelete != null){
        controller.callDeleteEntry(entryToDelete);}
    }


    /**
     * Calls controller to get information about the entry the user wants to view. Then, calls popUpWindow tp create a
     * pop-up with the information. Gets the modified journal entry information and calls controller with the modified
     * journal entry information to edit the entry

     */
    public void viewEntry(String titleOfEntryToView){
        String[] entryInfo = this.controller.callGetEntry(titleOfEntryToView);
        String[] modifiedJournalEntry = this.popUpWindow.viewAndAddEntryPopUp(entryInfo);
        modifiedJournalEntry[0] = checkEntryHasTitle(modifiedJournalEntry[0]);

        this.controller.callEditEntry(titleOfEntryToView, modifiedJournalEntry[0], modifiedJournalEntry[1],
                LocalDate.parse(entryInfo[0]), modifiedJournalEntry[2]);
    }
    /**
     * Calls controller to get title of all entries. Calls a popUpWindow  to create a pop-up window with these titles,
     * prompting user to choose an entry to view. Then calls viewEntry passing it the title of the entry the user
     *  wants to view
     */
    public void viewAllEntry(){
        String entryToView = this.popUpWindow.viewEntriesPopUp(controller.callGetAllEntries());
        if (entryToView != null){
        viewEntry(entryToView);}
    }

    /**
     * Calls popUpWindow to  create a pop-up window with a list of actions that the user wants to do. Gets the action
     * the user wants to do and call addEntry,deleteEntry or viewAllEntry.
     */
    public static void main(String[] args) { // what if user presses cancel
        JournalUI UI = new JournalUI();
        String userCommand = UI.popUpWindow.viewUserOptionsPopUP();
        while(!Objects.equals(userCommand, "exit journal")){

            if (Objects.equals(userCommand, "add an entry")){
                UI.addEntry();
            }

            else if(Objects.equals(userCommand, "view/edit entries")){
                UI.viewAllEntry();
            }

            else {
                UI.deleteEntry();
            }
            userCommand = UI.popUpWindow.viewUserOptionsPopUP();

        }
    }


}

