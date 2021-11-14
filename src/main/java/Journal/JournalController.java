package Journal;

import java.time.LocalDate;
import java.util.Set;

public class JournalController {
    // object the controller calls on to do the work its given
    private final JournalManager journalmanager;



    /**
     * Creates a controller with the given directory, dir.
     *
     *
     * @param journalFile  the gateway which is used to interact and create files on users' computer
     */



    public JournalController(JournalFileGateway journalFile){
        this.journalmanager = new JournalManager(journalFile);
    }


    /**
     * Calls journal manager to create entry with the given information and store it in dir.
     * @param title The title of the entry.
     * @param content The content of the entry.
     * @param date The date the entry was written on.
     * @param tags The tags we want to give the entry.

     */
    public void callCreateEntry(String title, String content, LocalDate date, String tags) {
        journalmanager.createEntry(title, content, date, tags);
    }

    /**
     * Calls journal manager to delete the entry with the given title in dir.
     * @param title The title of the entry to delete.
     */
    public void callDeleteEntry(String title) {
        journalmanager.deleteEntry(title);
    }

    /**
     * Calls journal manager to get the entry with the given title
     * @param title The title of the entry to get.
     * @return An array of the title, tags and contents of the entry
     */

    public String[] callGetEntry(String title) {
        return journalmanager.getEntry(title);
    }


    /**
     * Calls callDeleteEntry to delete the journal entry with title, titleOfEntryToDelete.
     * Also it calls callCreateEntry to create a new entry with the given title and store it in dir.
     * @param titleOfEntryToDelete The title of the entry user wants to delete.
     * @param title The possibly modified title of the entry.
     * @param tags The  possibly modified tags of the entry.
     * @param today The  date the entry was orginaly written on.
     * @param entry The possibly modified tags of  the entry.

     */
    public void callEditEntry(String titleOfEntryToDelete, String title, String tags, LocalDate today, String entry) {
        if (callEntryExist(titleOfEntryToDelete)){
        this.callDeleteEntry(titleOfEntryToDelete);
        this.callCreateEntry(title, entry, today, tags);}
    }

    /**
     * Calls journal manager to get the title of all the users' entries
     * @return A set of the titles of the users' entries
     */
    public Set<String> callGetAllEntries(){
        return journalmanager.GetAllEntries();
    }

    public boolean callEntryExist(String title){
        return journalmanager.checkEntryExist(title);
    }
}
