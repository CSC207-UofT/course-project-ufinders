package Journal;


import java.io.*;
import java.time.LocalDate;


public class JournalFileGateway implements FileGateway { //interacts with dir

    // the  path of the file to which JournalFileGateway can add, delete and edit text files from.
    private final String journalFilePath;


    /**
     * Creates a JournalFileGateway with the given path
     * @param   journalFilePath      The path of a directory
     */

    public JournalFileGateway(String journalFilePath){
        this.journalFilePath = journalFilePath;
    }


    /**
     * Make a journal entry(text file) with the given information only if the file with the given title does not exist.
     *
     * @param title The title of the entry.
     * @param content The content of the entry.
     * @param date The date the entry was written on.
     * @param tags The tags we want to give the entry.
     * @return return true iff a file was created  with the given information and false if not file was created.

     */
    @Override
    public File createFile(String title, String content, LocalDate date, String tags) { // method should be in journal manager.


            String pathOfEntry = journalFilePath + "/" + title + ".txt";
            File journalEntry = new File(pathOfEntry);
        try {
            journalEntry.createNewFile();
            writeToFile(title, content, date, tags, pathOfEntry);
            return journalEntry;}

        catch (Exception IOException){// file was not created
            return null;
        }

    }

    /**
     * Reads fileWithInfo, storing tags, title and content within the file to a string array that will be returned.
     * @param fileWithInfo The file that we want to get information from.
     * @return return a string array containing the information in fileWithInfo in the format
     * [date, title, tags, content]
     */

    @Override
    public String[] getEntryInfo(File fileWithInfo) {
        String[] info = new String[4];
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileWithInfo));
            String line = reader.readLine(); // date of entry
            info[0] = line;
            line = reader.readLine().strip(); // title of entry
            info[1] = line;
            reader.readLine(); // space after title of entry
            line = reader.readLine();
            info[2] = line.substring(line.indexOf(":") + 2); // tags
            reader.readLine();// space after tags
            line = reader.readLine().strip();
            info[3] = line;// entry
            reader.close();
            return info;
        }
        catch(Exception IOException){
           return info;
            }
    }



    /**
     * Delete the fileToDelete from path that it is stored in.
     * @param fileToDelete The file that we want to delete.
     */
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

            writingEntry.println("Tags: " + tags + "\n");
            writingEntry.println(content);
            writingEntry.close();
        } catch (Exception ignored) {
        }
    }


}



