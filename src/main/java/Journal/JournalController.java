package Journal;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

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
    /**
     * Calls journal manager to create entry with the given information.
     *
     * @param title The title of the entry.
     * @param content The content of the entry.
     * @param date The date the entry was written on.
     * @param tags The tags we want to give the entry.

     */
    public void callCreateEntry(String title, String content, LocalDate date, String[] tags) {
        journalmanager.createEntry(title, content, date, tags);
    }

    public void callDeleteEntry(String title) {
        journalmanager.deleteEntry(title);
    }
}
