package java.Journal;


import java.time.LocalDate;
import java.util.ArrayList;



public class JournalEntry {

    // The title of the entry
    private final String title;
    // the date the entry was written on
    private final LocalDate date;
    // the tags associated with the entry
    private final ArrayList<String> tags;
    // the content within the entry
    private final String content;

    /**
     * Creates a new journal entry with the given
     * entry, tags and the date the entry was written.
     *
     * @param entry        the content of the journal entry.
     * @param tags          tags associated with journal entry.
     * @param date the date the journal entry was written.
     */


    public JournalEntry(String title, String entry, ArrayList<String> tags, LocalDate date){
        this.title = title;
        this.content = entry;
        this.tags = tags;
        this.date = date;
    }
    /**
     * Reports the title of this entry.
     *
     * @return the title of this entry.
     */
    public String titleGetter(){
        return this.title;
    }

    /**
     * Reports the content of this entry.
     *
     * @return the content of this entry.
     */
    public String contentGetter(){
        return this.content;
    }
    /**
     * Reports the tags associated with this entry.
     *
     * @return the tags of this entry.
     */
    public ArrayList<String> tagsGetter(){
        return this.tags;
    }
    /**
     * Reports the date of this entry.
     *
     * @return the date of this entry.
     */
    public LocalDate dateGetter(){
        return this.date;
    }

}
