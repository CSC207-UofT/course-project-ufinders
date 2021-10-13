package Journal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class JournalManager {
    private Journal journal;

    public JournalManager(){
        journal = new Journal();
    }
    public void createEntry(String title, String content, LocalDate date, ArrayList<String> tags){
        JournalEntry entry = new JournalEntry(title, content, tags, date);
        journal.addEntry(title, entry);


    }

    public String entryGetter(String title){
        return journal.getEntry(title);
    }
}
