package Journal;



import java.io.File;
import java.util.HashMap;
import java.util.Set;


public class Journal {
    //Stores title to Journal Entry
    private final HashMap<String, File> entries;

    /**
     * A  constructor that initializes entries
     * as an empty HashMap.
     */
    public Journal() {
        entries = new HashMap<>();
    }

    /**
     * Store the provided journal entry's' title to the provided journal entry file.
     *
     * @param title The title of the entry that we want to store.
     * @param entry The journal entry file we want to store
     */
    public void addEntry(String title, File entry) {
        entries.put(title, entry);
    }

    /**
     * Find the journal entry file to the title and return it.
     *
     * @param title The title of the entry that we want to retrieve.
     * @return The journal entry file with the given title.
     */
    public File getEntryFile(String title) {
        if (this.entries.containsKey(title)){
        return entries.get(title);}
        else{
            return null;
        }
    }


    /**
     * Delete the title key-value pair from entries
     *
     * @param title The key that we want to delete in entries
     */
    public void deleteEntryFile(String title) {
        if (this.entries.containsKey(title)) {
            this.entries.remove(title);
        }
    }


    /**
     * Returns all titles of entries stored as keys in entries
     *
     *
     *  @return A set of title of journal entries that the user wrote
     */
    public Set<String> getAllEntryTitles() {
        return this.entries.keySet();
    }

    public boolean EntryExist(String title) {
        if (this.entries.containsKey(title)){
            return true;
        }
        else{
            return false;
        }
    }
}
