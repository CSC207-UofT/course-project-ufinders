package Journal;


import java.util.ArrayList;
import java.util.HashMap;


public class Journal {
    private HashMap<String, JournalEntry> entries;

    public Journal() {
        entries = new HashMap<>();
    }

    public void addEntry(String title, JournalEntry entry) {
        entries.put(title, entry);
    }

    public String getEntry(String title) {
        if (entries.containsKey(title)) {
            JournalEntry entry = entries.get(title);
            ArrayList<String> tags = entry.tagsGetter();
            StringBuilder tagOfEntry = new StringBuilder();
            for (int i = 0; i < tags.toArray().length - 1; i += 1){
                tagOfEntry.append(tags.get(i)).append(", ");
            }
            tagOfEntry.append(tags.get(tags.toArray().length - 1));


            return "date: " + entry.dateGetter() +  '\n' +  "title: " + entry.titleGetter()  + '\n'  + "tags: "  + tagOfEntry +  '\n'  +  entry.contentGetter();
        } else {
            return "Entry with given title does not exist!";
        }
    }
}
