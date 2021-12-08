package Journal;

import java.io.File;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;


public class JournalManager {// file gateway does the work of journal manager
    // the  interface the journal manager calls on to add, delete and modify journal entry files.
    private final FileGateway accessFiles;
    // Where journal entry file is stored. A journal entry file can be accessed with the title of the entry file.
    private final Journal journal;



    /**
     * Creates a journal manager with the given FileGateway, fileGateway.
     *
     *
     * @param   fileGateway      The interface that can be used to add, delete and edit journal entries
     */

    public JournalManager(FileGateway fileGateway){
        this.accessFiles = fileGateway; // needs gateway to allow it to store entries
        this.journal = new Journal();
    }


    /**
     * Calls accessFiles to create entry with the given information and add the title and file created
     * to journal.
     *  @param title The title of the entry.
     * @param content The content of the entry.
     * @param date The date the entry was written on.
     * @param tags The tags we want to give the entry.
     * @return true iff entry was created

     */
    public boolean createEntry(String title, String content, LocalDate date, String tags) {
        if (doesEntryExist(title)){
            return false;
        }
        else {
            // asks interface to create the file with the given info
            File fileCreated = accessFiles.createFile(title, content, date, tags);
            journal.addEntry(title, fileCreated);
            return true;
        }
    }
    /**
     * Finds the journal entry file with the given title by calling getEntry in
     * Journal. Then calls accessFiles passing it the journal entry file to delete.
     *  @param title The title of the entry to delete.
     */
        public void deleteEntry(String title)  {
        File fileToDelete = journal.getEntryFile(title);
        if (fileToDelete != null){
        accessFiles.deleteFile(fileToDelete);
        journal.deleteEntryFile(title);}
        }

    /**
     * Finds the journal entry file with the given title by calling get Entry in Journal.
     * Calls accessFiles  passing it the journal entry file to get a string array
     * of the form {date, title, tags, content}.
     *  @param title The title of the entry to get.
     */
        public String[] getEntry(String title){
        File fileWithInfo = journal.getEntryFile(title);
            return accessFiles.getEntryInfo(fileWithInfo);
        }

    /**
     * Checks whether entry with given info exist. Check whether edited title of entry is not the same title as another
     * entry, if so returns true. Else, Calls callDeleteEntry to delete the journal entry with title, titleOfEntryToDelete.
     * Also, it calls callCreateEntry to create a new entry with the given title, store it in dir and return false.
     * @param titleOfEntryToDelete The title of the entry user wants to delete.
     * @param title The possibly modified title of the entry.
     * @param tags The  possibly modified tags of the entry.
     * @param today The  date the entry was originally written on.
     * @param entry The possibly modified tags of  the entry.
     * @return true iff entry was modified

     */

        public boolean editEntry(String titleOfEntryToDelete, String title, String tags, LocalDate today, String entry){
            if (doesEntryExist(titleOfEntryToDelete)){
                if ((!getAllEntries().contains(title))|| Objects.equals(titleOfEntryToDelete, title)){
                    this.deleteEntry(titleOfEntryToDelete);
                    this.createEntry(title, entry, today, tags);
                    return true;}
                else{return false;}
            }
            else{return false;}
        }

    /**
     * Calls journal to get the title of all the users' entries
     * @return A set of the titles of the users' entries
     */

        public Set<String> getAllEntries(){
            return journal.getAllEntryTitles();
        }


    /**
     * Calls journal to get the title of all the users' entries
     *  @param title The title of the entry we are checking exists.
     * @return true iff an entry with that title exists
     */
    public boolean doesEntryExist(String title) {
            return journal.EntryExist(title);
    }
}
