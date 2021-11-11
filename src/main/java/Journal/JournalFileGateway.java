package Journal;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.time.LocalDate;

public class JournalFileGateway implements FileGatewayInterface { //interacts with dir

    // the  path of the file to which JournalFileGateway can add, delete and edit text files from.
    private String path;
    private Journal journal;

    /**
     * Creates a JournalFileGateway with the given path
     * @param   path      The path of a directory
     */

    public JournalFileGateway(String path){
        this.path = path;
        this.journal = new Journal();
    }


    /**
     * Make a journal entry(text file) with the given information only if the file with the given title does not exsist.
     *
     * @param title The title of the entry.
     * @param content The content of the entry.
     * @param date The date the entry was written on.
     * @param tags The tags we want to give the entry.
     * @return return true iff a file was created  with the given information and false if not file was created.

     */
    @Override
    public boolean addFile(String title,  String content, LocalDate date, String[] tags) { // method should be in journal manager.
        // only method in here should be edit file ? maybe

            String pathOfEntry = path + "/" + title + ".txt";
            File journalEntry = new File(pathOfEntry);
        try {
            journalEntry.createNewFile();
            writeToFile(title, content, date, tags, pathOfEntry); // write to file, getting to this line means file was created
            journal.addEntry(title, journalEntry); // should be done through an interface
            return true;}

        catch (Exception IOException){// file was not created
            return false;
        }

    }

    /**
     * Writes to a text file the title, content, date and tags at the given directory, journalEntry.
     *
     * @param title The title of the entry.
     * @param content The content of the entry.
     * @param date The date the entry was written on.
     * @param tags The tags we want to give the entry.
     * @param journalEntry The file to which will be written.

     */
    public void writeToFile(String title, String content, LocalDate date, String[] tags, String journalEntry) {
        try {
            PrintWriter writingEntry = new PrintWriter(journalEntry);
            writingEntry.println(date);
            writingEntry.println(title + "\n");
            StringBuilder tagsOfEntry = getStringTags(tags);
            writingEntry.println("Tags:" + tagsOfEntry + "\n");
            writingEntry.println(content);
            writingEntry.close();
        } catch (Exception ignored) {
        }
    }

    /**
     * Gives the StringBuilder representation of tags.
     * @param tags The tags we want a StringBuilder representation of.
     * @return each elements of tags seperated by commas in as a string builder.

     */
    public StringBuilder getStringTags(String[] tags) {

        StringBuilder tagsOfEntry = new StringBuilder();
        if (tags.length == 1){
            tagsOfEntry.append(tags[0]);
        }
        else if (tags.length > 1){
        for (String tag : tags) {
            tagsOfEntry.append(tag).append(", ");
        }
        tagsOfEntry.delete(tagsOfEntry.length() - 2, tagsOfEntry.length());
    }
        return tagsOfEntry;
}
    public void deleteFile(String title){
        files.get(entryToDelete).delete();

    }

}
