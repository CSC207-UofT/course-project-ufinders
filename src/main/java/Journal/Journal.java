package Journal;



import java.io.File;
import java.util.HashMap;


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
     * Store the provided journal entry's' title to the provided journal entry
     *
     * @param title The title of the entry that we want to store.
     * @param entry The journal entry we want to store
     */
    public void addEntry(String title, File entry) {
        entries.put(title, entry);
    }

    /**
     * Find the journal entry with the given title and return it.
     *
     * @param title The title of the entry that we want to retrieve.
     * @return The entry with the given title.
     */
    public File getEntry(String title) {
            return entries.get(title);
    }
}
