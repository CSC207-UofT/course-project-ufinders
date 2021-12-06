package Events;

import User.MakeDir;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.nio.*;

public class EventManager { //The controller for how all events work

    //Events.MakeDeleteEvent Use Case that contains all events for this user
    private final MakeDeleteEvent mde;
    private final String utorID;

    /**
     * Constructor that initializes Events.EventManager and its Events.MakeDeleteEvent attribute.
     * @param userID The utorID of this user.
     */
    public EventManager(String userID) {
        mde = new MakeDeleteEvent();
        utorID = userID;

    }

    /**
     * Adds an event to the calendar.
     *
     * @param date The date of the event.
     * @param time The time the event takes place at.
     * @param title The name of the event.
     * @param URL The URL of the event if it's from the UofT website.
     * @return The newly created Event object.
     */
    public Event addEvent(String date, String time, String title, String URL) throws IOException {
        MakeDir dir = new MakeDir(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() +
                File.separator  + "userDataEvents");

        FileWriter userWriter = new FileWriter(dir.getPath() + utorID + ".txt");
        String newData = MessageFormat.format("{0},{1},{2},{3}", date, time, title, URL);
        userWriter.write(newData + "\n");
        userWriter.close();
        return mde.addEvent(date, time, title, URL);
    }

    /**
     * Reads events matching given keywords from the UofT website
     * @param keywords The string containing the keywords given by the user for the purposes of searching for events.
     * @return The list of events matching the keyword search.
     */
    public List<Event> searchEvent(String keywords) throws ResponseException, NotFound {
        return GetEvent.findEvents(keywords);
    }

    /**
     * Removes all events from this student's calendar.
     */
    public void removeAllEvents() throws FileNotFoundException {
        mde.removeAllEvents();
        MakeDir dir = new MakeDir(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() +
                File.separator  + "userDataEvents");
        PrintWriter userPrint = new PrintWriter(dir.getPath() + utorID + ".txt");
        userPrint.print("");
        userPrint.close();
    }

    /**
     * Removes an event from this student's calendar.
     *
     * @param date The date of the event.
     * @param time The time the event takes place at.
     * @param title The name of the event.
     * @param URL The URL of the event if it's from the UofT website.
     */
    public void removeSingleEvent(String date, String time, String title, String URL) throws IOException {
        //In MakeDeleteEvent, removes event from eventList, then in this class it removes it from the user file
        mde.removeEvent(date, time, title, URL);
        MakeDir dir = new MakeDir(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() +
                File.separator  + "userDataEvents");

        File inputFile = new File(dir.getPath() + utorID + ".txt");
        File tempFile = new File("tempfile.txt");

        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

        String lineToRemove = MessageFormat.format("{0},{1},{2},{3}", date, time, title, URL);
        String currentLine;

        while((currentLine = br.readLine()) != null) {
            String trimmedLine = currentLine.trim();
            if(trimmedLine.contains(lineToRemove)) continue;
            bw.write(currentLine + System.getProperty("line.separator")); //Warning, this may cause an error later, duplicate newlines?
        }
        bw.close();
        br.close();
        inputFile.delete();
        tempFile.renameTo(inputFile);
    }


    /**
     * Retrieves an event from this student's database.
     *
     * @param date The date of the event.
     * @param time The time the event takes place at.
     * @param title The name of the event.
     * @param URL The URL of the event if it's from the UofT website.
     * @return The desired Event object.
     */
    public Event retrieveEvent(String date, String time, String title, String URL) {
        return mde.retrieveEvent(date, time, title, URL);
    }

    /**
     * Retrieves all events on this student's calendar.
     * @return A list of all event objects under this student.
     */
    public List<Event> retrieveAllEvents() {
        return mde.retrieveAllEvents();
    }


    /**
     * Edits an event in this student's database.
     *
     * @param date The date of the event.
     * @param time The time the event takes place at.
     * @param title The name of the event.
     * @param URL The URL of the event if it's from the UofT website.
     * @param newDate The new date of the event.
     * @param newTime The new time the event takes place at.
     * @param newTitle The new name of the event.
     * @param newURL The new URL of the event.
     * @return The newly edited event object.
     */
    public Event editEvent(String date, String time, String title, String URL, String newDate, String newTime, String newTitle, String newURL) throws IOException {
        this.removeSingleEvent(date, time, title, URL);
        return this.addEvent(newDate, newTime, newTitle, newURL);
    }

    /**
     * Loads this user's calendar from their database.
     */
    public void loadEvents() throws IOException { //Make sure the titles don't have commas
        MakeDir dir = new MakeDir(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() +
                File.separator  + "userDataEvents");
        File userFile = new File(dir.getPath() + utorID + ".txt");
        if (userFile.length() == 0) {
            return;
        }
        BufferedReader br = new BufferedReader(new FileReader(userFile));
        String currentLine;
        List<String> userList;
        String URL;
        while((currentLine = br.readLine()) != null) {
            String userData = currentLine.trim();
            if (!userData.isEmpty()) {
                userList = Arrays.asList(userData.split(","));
                if (userList.isEmpty()) {
                    continue;
                }
                String date = userList.get(0);
                String time = userList.get(1);
                String title = userList.get(2);
                if (userList.size() == 3) {
                    URL = "";
                }
                else {
                    URL = userList.get(3);
                }
                this.addEvent(date, time, title, URL);
            }
        }
        br.close();
    }

}