package Journal;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class JournalController {

    // the object that the controller instructs
    private final JournalManager journalmanager;

    public JournalController(){
        this.journalmanager = new JournalManager();
    }
    /**
     * Calls journal manager to create entry with the given information.
     *
     * @param title The title of the entry.
     * @param content The content of the entry.
     * @param date The date the entry was written on.
     * @param tags The tags we want to give the entry.

     */
    public void callCreateEntry(String title, String content, LocalDate date, String[] tags) throws IOException {
        journalmanager.createEntry(title, content, date, tags);
    }

}
