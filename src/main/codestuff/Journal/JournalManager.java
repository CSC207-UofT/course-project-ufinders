package codestuff.Journal;

import java.time.LocalDate;
import java.util.ArrayList;


public class JournalManager {
    // Where journal entries are stored
    private final Journal journal;

    public JournalManager(){
        journal = new Journal();
    }
    /**
     * Create a journal entry with the given information and stores it in the journal.
     *
     * @param title The title of the entry.
     * @param content The content of the entry.
     * @param date The date the entry was written on.
     * @param tags The tags we want to give the entry.

     */
    public String createEntry(String title, String content, LocalDate date, ArrayList<String> tags){
        JournalEntry entry = new JournalEntry(title, content, tags, date);
        journal.addEntry(title, entry);
        return this.entryGetter(title);
    }
    /**
     * Retrieves the journal entry from Journal with the given title.
     *
     * @param title The title of the entry that we want to retrieve.
     * @return The string representation of the entry with the given title.
     */
    public String entryGetter(String title){

        JournalEntry maybeEntry = journal.getEntry(title);
        if (maybeEntry == null){
            return "Entry with given title does not exist!";
        }
        else{

            ArrayList<String> tags = maybeEntry.tagsGetter();
            StringBuilder tagOfEntry = new StringBuilder();
            for (int i = 0; i < tags.toArray().length - 1; i += 1){
                tagOfEntry.append(tags.get(i)).append(",");
            }
            tagOfEntry.append(tags.get(tags.toArray().length - 1));


            return "date: " + maybeEntry.dateGetter() +  '\n' +  "title: " + maybeEntry.titleGetter()  + '\n'
                    + "tags: "  + tagOfEntry +  '\n' + '\n' +  maybeEntry.contentGetter();
        }
    }
}
