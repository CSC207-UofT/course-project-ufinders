package Journal;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;


public class JournalManager {
    // Where journal entries are stored
    private final Journal journal;

    public JournalManager() {
        journal = new Journal();
    }

    /**
     * Create a journal entry with the given information and stores it in the journal.
     *
     * @param title   The title of the entry.
     * @param content The content of the entry.
     * @param date    The date the entry was written on.
     * @param tags    The tags we want to give the entry.
     */
    public void createEntry(String title, String content, LocalDate date, String[] tags) throws IOException {
        // When create use ask them where they want to store the journal entries and create directory for it
        String path = "/Users/thakshamangalam/Documents/JournalEntries";
        //new File(path).mkdir();


        File journalEntry = new File(path + "/" + title + ".txt");
        journalEntry.createNewFile();
        // Open the file.
        PrintWriter writingEntry = new PrintWriter(path + "/" + title + ".txt"); // Step 2


        writingEntry.println(date);
        writingEntry.println(title + "\n");

        StringBuilder tagsOfEntry = new StringBuilder();
        for (String tag : tags) {
            tagsOfEntry.append(tag + ", ");
        }
        tagsOfEntry.delete(tagsOfEntry.length() - 2, tagsOfEntry.length());
        writingEntry.println("Tags:" + tagsOfEntry + "\n");
        writingEntry.println(content);


        // Close the file.
        writingEntry.close();


        /**
         * Retrieves the journal entry from Journal with the given title.
         *
         * @param title The title of the entry that we want to retrieve.
         * @return The string representation of the entry with the given title.
         */
//    public String entryGetter(String title){
//
//        JournalEntry maybeEntry = journal.getEntry(title);
//        if (maybeEntry == null){
//            return "Entry with given title does not exist!";
//        }
//        else{
//
//            String[] tags = maybeEntry.tagsGetter();
//            StringBuilder tagOfEntry = new StringBuilder();
//            for (int i = 0; i < tags.toArray().length - 1; i += 1){
//                tagOfEntry.append(tags.get(i)).append(",");
//            }
//            tagOfEntry.append(tags.get(tags.toArray().length - 1));
//
//
//            return "date: " + maybeEntry.dateGetter() +  '\n' +  "title: " + maybeEntry.titleGetter()  + '\n'
//                    + "tags: "  + tagOfEntry +  '\n' + '\n' +  maybeEntry.contentGetter();
//        }
//    }
    }
}