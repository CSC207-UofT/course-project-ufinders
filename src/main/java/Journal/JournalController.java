package Journal;

import java.time.LocalDate;
import java.util.Set;

public class JournalController {
    // object the controller calls on to do the work its given
    private final JournalManager journalmanager;
    // the directory that the journal entries are stored in
    private final MakeDir dir;


    /**
     * Creates a controller with the given directory, dir.
     *
     *
     * @param dir         the directory the controller modifies
     */



    public JournalController(MakeDir dir){
        this.dir = dir;

        this.journalmanager = new JournalManager(new JournalFileGateway(dir.getPath())); // not sure if controller
        // creates an instance
        // of a gateway
        // controller needs its own journal manager to manage
    }
    public JournalManager getJournalManager(){
        return this.journalmanager;
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
     * Calls journal manager to get the entry with the given title in dir
     * @param title The title of the entry to get.
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
        this.callDeleteEntry(titleOfEntryToDelete);
        this.callCreateEntry(title, entry, today, tags);
    }

    public Set<String> callGetAllEntries(){
        return journalmanager.GetAllEntries();
    }
}
