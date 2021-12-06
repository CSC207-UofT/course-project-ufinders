package Journal;


import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.time.LocalDate;
import java.util.Objects;
import User.MakeDir;

public class JournalUI {
    // object the JournalUI calls on to do the work its given
    public JournalController controller;
    // objects the JournalUI calls to create a pop-up window and get information that is inputted
    public JournalWindow popUpWindow;
    // the number of entries that were not given a title by the user
    public int numOfTitleLessEntries;



    /**
     * A  constructor that controller, popUpWindow and numOfTitleLessEntries
     */


    public JournalUI(){
        MakeDir dir = new MakeDir(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() +
                File.separator  + "Journal Entries");
        this.controller = new JournalController(new JournalFileGateway(dir.getPath()));
        this.popUpWindow = new JournalWindow();
        this.numOfTitleLessEntries = 0;

    }

    /**
     * Calls popUpWindow to create a pop-up window prompting user to add an entry. Entry is checked whether it has a
     * title and if not it is title "Untitled" + " " + titleLessEntries. If user inputs a title
     * that is the same title as an entry that already exist user is prompted to enter a new title until
     * they enter a title of an entry that does not exist. Gets input from pop-window and passes it to controller to
     * create entry with the given information and store it in dir.
     */

    public void addEntry(){

        String[] newEntry = this.popUpWindow.addEntryPopUp();
        if (newEntry != null) {
            newEntry = replaceNullWithEmptyStrings(newEntry);
            newEntry[0] = checkEntryHasTitle(newEntry[0]);
            LocalDate today = LocalDate.now();

            boolean entryCreated = this.controller.callCreateEntry(newEntry[0], newEntry[2], today, newEntry[1]);
            while (!entryCreated) {
                newEntry = addEntryWithNewTitle(today, newEntry);
                entryCreated = this.controller.callCreateEntry(newEntry[0],
                        newEntry[1], today, newEntry[2]);
            }
        }
        }

    /**
     * Replace elements in arrayToBeFixed that are null with empty strings
     * @param arrayToBeFixed the array whose null elements will be replaced
     * @return  arrayToBeFixed with all null elements replaced with empty strings
     */

    public String[] replaceNullWithEmptyStrings(String[]  arrayToBeFixed){
        for (int i = 0; i < arrayToBeFixed.length; i += 1) {
            if (arrayToBeFixed[i] == null) {
                arrayToBeFixed[i] = "";
            }}
        return arrayToBeFixed;
    }



    /**
     * check whether title is an empty string, if it is  "Untitled"+ " " + titleLessEntries is returned and
     * if not title is returned
     * @param title string that is checked if it is empty string
     * @return  "Untitled"+ " " + titleLessEntries if title is empty string and title if title is not an empty string
     */

    public String checkEntryHasTitle(String title){
        if (Objects.equals(title, "")){
            String newTitle = "Untitled"+ " " + numOfTitleLessEntries;
            numOfTitleLessEntries += 1;
            return newTitle;}

        else{return title;}
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

    public String[] addEntryWithNewTitle(LocalDate today, String[] newEntry){
        this.popUpWindow.entryWithTitleAlreadyExistsWarning();
        String[] tempEntry = {String.valueOf(today), newEntry[0], newEntry[2], newEntry[1]};
        newEntry = this.popUpWindow.viewAndAddEntryPopUp(tempEntry);
        return newEntry;
    }

    /**
     * Calls popUpWindow to create a pop-up window prompting user to  choose from a list of entries to delete.  If user
     * presses cancel pop-up window is closed. If not Gets the entry the user wants to delete and calls controller to
     * delete that entry.
     */

    public void deleteEntry(){

        String entryToDelete = this.popUpWindow.deleteEntryPopUp(controller.callGetAllEntries());
        if (entryToDelete != null){
        controller.callDeleteEntry(entryToDelete);}
    }


    /**
     * Calls controller to get information about the entry the user wants to view. Then, calls popUpWindow tp create a
     * pop-up with the information. Gets the modified journal entry information. Checks whether user has made the entry
     * have no title and if so give it the title "Untitled"+ " " + titleLessEntries.  If user modifies the title
     *  that is the same title as an entry that already exist user is prompted to enter a new title until
     *  they enter a title of an entry that does not exist. Calls controller with the modified journal entry information
     *  to edit the entry
     *  @param titleOfEntryToView title of the entry the user wants to view

     */
    public void viewEntry(String titleOfEntryToView){
        String[] entryInfo = this.controller.callGetEntry(titleOfEntryToView);
        String[] modifiedJournalEntry = this.popUpWindow.viewAndAddEntryPopUp(entryInfo);

        if (modifiedJournalEntry != null){
        modifiedJournalEntry[0] = checkEntryHasTitle(modifiedJournalEntry[0]);
        boolean noEntryWithSameTitle = this.controller.callGetAllEntries().contains(modifiedJournalEntry[0]);

        if (noEntryWithSameTitle){
            this.controller.callEditEntry(titleOfEntryToView, modifiedJournalEntry[0],
                    modifiedJournalEntry[1], LocalDate.parse(entryInfo[0]), modifiedJournalEntry[2]);
        }
        else{addEntryWithUniqueTitle(entryInfo[0], modifiedJournalEntry, titleOfEntryToView);}}}

    /**

     * Keeps prompting user to use a title that is not a title of an entry that already exists for the entry they
     * are creating. Creates a pop-up window with information from entryInfo.
     * @param date the date the journal entry was written
     * @param modifiedJournalEntry journal entry information before modified by user
     *@param titleOfEntryToView title of the journal entry
     */

    public void addEntryWithUniqueTitle(String date, String[]
            modifiedJournalEntry, String titleOfEntryToView){
        boolean EntryWithSameTitle = false;
        while (!EntryWithSameTitle){
            modifiedJournalEntry = addEntryWithNewTitle( LocalDate.parse(date), modifiedJournalEntry);
            EntryWithSameTitle = this.controller.callEditEntry(titleOfEntryToView, modifiedJournalEntry[0],
                    modifiedJournalEntry[1], LocalDate.parse(date), modifiedJournalEntry[2]);}
    }


/**
    * Calls controller to get title of all entries. Calls a popUpWindow  to create a pop-up window with these titles,
    * prompting user to choose an entry to view. if the user presses cancel, the pop-up window is closed. Then calls
    * viewEntry passing it the title of the entry the user wants to view.
 */
    public void viewAllEntry(){
        String entryToView = this.popUpWindow.viewEntriesPopUp(controller.callGetAllEntries());
        if (entryToView != null){
        viewEntry(entryToView);
    }}

    /**
     * Calls popUpWindow to  create a pop-up window with a list of actions that the user wants to do. Gets the action
     * the user wants to do and call addEntry,deleteEntry or viewAllEntry or closes pop-up window if the user
     * presses cancel.
     */
    public static void main(String[] args) { // what if user presses cancel
        JournalUI UI = new JournalUI();
        String userCommand = UI.popUpWindow.viewUserOptionsPopUP();
        while(!(Objects.equals(userCommand, "exit journal") || Objects.equals(userCommand, null))){

            if (Objects.equals(userCommand, "add an entry")){UI.addEntry();}

            else if(Objects.equals(userCommand, "view/edit entries")){
                UI.viewAllEntry();}

            else {UI.deleteEntry();}
            userCommand = UI.popUpWindow.viewUserOptionsPopUP();
        }
    }

}


