package Journal;


import javax.swing.filechooser.FileSystemView;
import java.time.LocalDate;
import java.util.Objects;
import User.MakeDir;

public class JournalUI {
    // object the JournalUI calls on to do the work its given
    public JournalController controller;
    // objects the JournalUI calls to create a pop-up window and get information that is inputted
    public JournalWindow popUpWindow;




    /**
     * ???? add about dir after integrated with user
     */

    public JournalUI(){
        MakeDir dir = new MakeDir(FileSystemView.getFileSystemView()
                        .getHomeDirectory()
                        .getAbsolutePath() + "/" +"Documents" + "/"  + "Journal Entries");
        this.controller = new JournalController(new JournalFileGateway(dir.getPath()));
        this.popUpWindow = new JournalWindow();

    }

    /**
     * Calls popUpWindow to create a pop-up window prompting user to add an entry. If an entry with that title already
     * exist user is prompted to enter a new title until they enter a title of an entry that does not exist.
     * Gets input from pop-window and passes it to controller to create entry with the given information and store it
     * in dir.
     */

    public void addEntry(){

        String[] newEntry = this.popUpWindow.addEntryPopUp();
        for (int i = 0; i < newEntry.length; i += 1){
            if(newEntry[i] == null){
                newEntry[i] = "";
            }
        }
        LocalDate today = LocalDate.now();
        boolean entryCreated = this.controller.callCreateEntry(newEntry[0], newEntry[2], today, newEntry[1] );

        while (!entryCreated){
            entryCreated = addEntryWithNewTitle(today, newEntry);
        }
    }


    /**
     * Calls popUpWindow to create a pop-up window  warning user that entry with that title already
     * exist. User is prompted to enter a new title. Gets input from pop-window and
     * passes it to controller to create entry with the given information and store it in dir. Entry is created
     * if entry with given title does not exist.
     * @param today date the journal entry was created
     * @param newEntry entry input from user in form {title, tags, content}
     * @return true iff a journal entry was created
     */

    public boolean addEntryWithNewTitle(LocalDate today, String[] newEntry){
        this.popUpWindow.entryWithTitleAlreadyExists();
        String[] tempEntry = {String.valueOf(today), newEntry[0], newEntry[2], newEntry[1]};
        newEntry = this.popUpWindow.viewAndAddEntryPopUp(tempEntry);
        return this.controller.callCreateEntry(newEntry[0],
                newEntry[1], today, newEntry[2] );
    }

    /**
     * Calls popUpWindow to create a pop-up window prompting user to  choose from a list of entries to delete. Gets
     * the entry the user wants to delete and calls controller to delete that entry.
     */

    public void deleteEntry(){

        String entryToDelete = this.popUpWindow.deleteEntryPopUp(controller.callGetAllEntries());
        controller.callDeleteEntry(entryToDelete);
    }


    /**
     * Calls controller to get information about the entry the user wants to view. Then, calls popUpWindow tp create a
     * pop-up with the information. Gets the modified journal entry information and calls controller with the modified
     * journal entry information to edit the entry

     */
    public void viewEntry(String titleOfEntryToView){
        String[] entryInfo = this.controller.callGetEntry(titleOfEntryToView);
        String[] modifiedJournalEntry = this.popUpWindow.viewAndAddEntryPopUp(entryInfo);
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
        viewEntry(entryToView);
    }

    /**
     * Calls popUpWindow to  create a pop-up window with a list of actions that the user wants to do. Gets the action
     * the user wants to do and call addEntry,deleteEntry or viewAllEntry.
     */
    public static void main(String[] args) { // what if user presses cancel
        JournalUI UI = new JournalUI();
        String userCommand = UI.popUpWindow.viewUserOptions();
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
            userCommand = UI.popUpWindow.viewUserOptions();

        }
    }


}


