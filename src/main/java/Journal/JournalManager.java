package Journal;

import java.io.File;
import java.time.LocalDate;

public class JournalManager {// file gateway does the work of journal manager
    // the  interface the journal manager calls on to add, delete and modify journal entry files.
    private FileGatewayInterface accessFiles;
    private Journal journal;



    /**
     * Creates a journal manager with the given FileGatewayInterface, fileGateway.
     *
     *
     * @param   fileGateway      The interface that can be used to add, delete and edit journal entries
     */

    public JournalManager(FileGatewayInterface fileGateway){
        setFileGateway(fileGateway );
    }
    /**
     * Sets accessFiles  to the provided journalFileGateway
     *
     * @param journalFileGateway The interface that accessFiles is set to.

     */

    public void setFileGateway(FileGatewayInterface journalFileGateway){
        this.accessFiles = journalFileGateway; // needs gateway to allow it to store entries
        this.journal = new Journal();

    }
    /**
     * Calls accessFiles to create entry with the given information.
     *  @param title The title of the entry.
     * @param content The content of the entry.
     * @param date The date the entry was written on.
     * @param tags The tags we want to give the entry.

     */
    public void createEntry(String title, String content, LocalDate date, String tags) {
        // asks interface to create the file with the given info
        File fileCreated = accessFiles.addFile(title, content, date, tags);
        journal.addEntry(title, fileCreated);

        if (fileCreated == null) {
            // need to get a new name for journal entry if journal entry with that name exist
        }
    }
        public void deleteEntry(String title)  {
        File fileToDelete = journal.getEntry(title);
        accessFiles.deleteFile(fileToDelete);
        }

        public String[] getEntry(String title){
        File fileWithInfo = journal.getEntry(title);
            return accessFiles.getInfo(fileWithInfo);
        }



}
