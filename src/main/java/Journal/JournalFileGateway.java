package Journal;

import java.io.*;
import java.time.LocalDate;

public class JournalFileGateway implements FileGatewayInterface { //interacts with dir

    // the  path of the file to which JournalFileGateway can add, delete and edit text files from.
    private String path;


    /**
     * Creates a JournalFileGateway with the given path
     * @param   path      The path of a directory
     */

    public JournalFileGateway(String path){
        this.path = path;

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
    public File addFile(String title, String content, LocalDate date, String tags) { // method should be in journal manager.
        // only method in here should be edit file ? maybe

            String pathOfEntry = path + "/" + title + ".txt";
            File journalEntry = new File(pathOfEntry);
        try {
            journalEntry.createNewFile();
            writeToFile(title, content, date, tags, pathOfEntry); // write to file, getting to this line means file was created
            // should be done through an interface
            return journalEntry;}

        catch (Exception IOException){// file was not created
            return null;
        }

    }

    @Override
    public String[] getInfo(File fileWithInfo) {
        String[] info = new String[4];
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileWithInfo));
            String line = reader.readLine();
            info[0] = line;
            reader.readLine().strip(); // title of entry
            int i = 1;

            while(line != null){
                line = reader.readLine().strip();
                info[i] = line;
                i += 1;
            }
            info[1] = info[1].substring(info[1].indexOf(":"));
            info[2] = info[3];
            return info;

        }
        catch(Exception IOException){return info;}



    }

    @Override
    public void deleteFile(File fileToDelete) {
        fileToDelete.delete();

    }

    /**
     * Writes to a text file the title, content, date and tags at the given directory, journalEntry.
     *  @param title The title of the entry.
     * @param content The content of the entry.
     * @param date The date the entry was written on.
     * @param tags The tags we want to give the entry.
     * @param journalEntry The file to which will be written.

     */
    public void writeToFile(String title, String content, LocalDate date, String tags, String journalEntry) {
        try {
            PrintWriter writingEntry = new PrintWriter(journalEntry);
            writingEntry.println(date);
            writingEntry.println(title + "\n");

            writingEntry.println("Tags:" + tags + "\n");
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
    public StringBuilder getStringTags(String[] tags) { // can delete method

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


}
