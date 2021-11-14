package Events;

import com.jaunt.NotFound;
import com.jaunt.ResponseException;

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
     */
    public Event addEvent(String date, String time, String title, String URL) throws IOException {
        String path = "/course-project-ufinders/src/main/java/userData/";
        FileWriter userWriter = new FileWriter(path + utorID + ".txt");
        String newData = MessageFormat.format("{0},{1},{2},{3}", date, time, title, URL);
        userWriter.write(newData + "\n");
        userWriter.close();
        return mde.addEvent(date, time, title, URL);
    }

    /**
     * Reads events matching given keywords from the UofT website
     * @param keywords The string containing the keywords given by the user for the purposes of searching for events.
     */
    public List<Event> searchEvent(String keywords) throws ResponseException, NotFound {
        return GetEvent.findEvents(keywords);
    }

    /**
     * Removes all events from this student's calendar.
     */
    public void removeAllEvents() throws FileNotFoundException {
        mde.removeAllEvents();
        String path = "/course-project-ufinders/src/main/java/userData/";
        PrintWriter userPrint = new PrintWriter(path + utorID + ".txt");
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
        String path = "/course-project-ufinders/src/main/java/userData/";

        File inputFile = new File(path + utorID + ".txt");
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
     */
    public Event retrieveEvent(String date, String time, String title, String URL) {
        return mde.retrieveEvent(date, time, title, URL);
    }

    /**
     * Retrieves all events on this student's calendar.
     */
    public List<Event> retrieveAllEvents() {
        return mde.retrieveAllEvents();
    }

    /**
     * Sets an alarm for the specified event on this student's calendar.
     *
     * @param date The date of the event.
     * @param time The time the event takes place at.
     * @param title The name of the event.
     * @param URL The URL of the event if it's from the UofT website.
     * @param AlarmDate The date the alarm goes off.
     * @param AlarmTime The time the alarm goes off.
     */
    public void setAlarm(String date, String time, String title, String URL, String AlarmDate, String AlarmTime) {
        mde.setAlarm(date, time, title, URL, AlarmDate, AlarmTime);
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
     */
    public Event editEvent(String date, String time, String title, String URL, String newDate, String newTime, String newTitle, String newURL) throws IOException {
        this.removeSingleEvent(date, time, title, URL);
        return this.addEvent(newDate, newTitle, newTime, newURL);
    }

    /**
     * Removes the alarm from this event on the calendar.
     *
     * @param date The date of the event.
     * @param time The time the event takes place at.
     * @param title The name of the event.
     * @param URL The URL of the event if it's from the UofT website.
     */
    public void removeAlarm(String date, String time, String title, String URL) {
        mde.removeAlarm(date, time, title, URL);
    }

    /**
     * Loads this user's calendar from their database.
     */
    public void loadEvents() throws IOException { //Make sure the titles don't have commas
        String path = "/course-project-ufinders/src/main/java/userData/";
        File userFile = new File(path + utorID + ".txt");
        if (userFile.length() == 0) {
            return;
        }
        BufferedReader br = new BufferedReader(new FileReader(userFile));
        String currentLine;
        List<String> userList;
        while((currentLine = br.readLine()) != null) {
            String userData = currentLine.trim();
            if (!userData.isEmpty()) {
                userList = Arrays.asList(userData.split(","));
                String date = userList.get(0);
                String time = userList.get(1);
                String title = userList.get(2);
                String URL = userList.get(3);
                this.addEvent(date, time, title, URL);
            }
        }
    }

}
