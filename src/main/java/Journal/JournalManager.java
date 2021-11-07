package Journal;

import java.io.File;
import java.time.LocalDate;
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

     */
    public void createEntry(String title, String content, LocalDate date, String tags) {
        // asks interface to create the file with the given info
        File fileCreated = accessFiles.addFile(title, content, date, tags);
        journal.addEntry(title, fileCreated);

        if (fileCreated == null) { // means file was not created
            // need to get a new name for journal entry if journal entry with that name exist
        }
    }
    /**
     * Finds the journal entry file with the given title by calling getEntry in
     * Journal. Then calls accessFiles passing it the journal entry file to delete.
     *  @param title The title of the entry to delete.
     */
        public void deleteEntry(String title)  {
        File fileToDelete = journal.getEntryFile(title);
        accessFiles.deleteFile(fileToDelete);
        journal.deleteEntryFile(title);
        }
    /**
     * Finds the journal entry file with the given title by calling get Entry in Journal.
     * Calls accessFiles  passing it the journal entry file to get a string array
     * of the form {title, tags, content}.
     *  @param title The title of the entry to get.
     */
        public String[] getEntry(String title){
        File fileWithInfo = journal.getEntryFile(title);
            return accessFiles.getInfo(fileWithInfo);
        }

    /**
     * Calls journal to get the title of all the users' entries
     * @return A set of the titles of the users' entries
     */

        public Set<String> GetAllEntries(){
            return journal.getAllEntryTitles();
        }



}
