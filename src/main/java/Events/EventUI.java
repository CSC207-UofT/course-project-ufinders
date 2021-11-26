package Events;

import com.jaunt.NotFound;
import com.jaunt.ResponseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;

public class EventUI { //The user interface for the events section

    //Events.EventManager Controller that controls how events work
    private EventManager em;

    /**
     * Constructor that initializes the Events.EventUI object and its Events.EventManager attribute.
     * @param userID the utorID of the student
     */
    public EventUI(String userID) throws IOException {
        em = new EventManager(userID);
        em.loadEvents();
    }

    /**
     * Constructs an event on a calendar.
     *
     * @param date The date of the event.
     * @param time The time the event takes place at.
     * @param title The name of the event.
     * @param URL The URL of the event if it's from the UofT website.
     * @return The newly created Event object.
     */
    public Event addEvent(String date, String time, String title, String URL) throws IOException {
        return em.addEvent(date, time, title, URL);
    }

    /**
     * Removes an event from this student's calendar.
     *
     * @param date The date of the event.
     * @param time The time the event takes place at.
     * @param title The name of the event.
     * @param URL The URL of the event if it's from the UofT website.
     */
    public void removeEvent(String date, String time, String title, String URL) throws IOException {
        em.removeSingleEvent(date, time, title, URL);
    }

    /**
     * Reads events matching given keywords from the UofT website
     * @param keywords The string containing the keywords given by the user for the purposes of searching for events.
     * @return The list of events matching the keyword search.
     */
    public List<Event> searchEvent(String keywords) throws ResponseException, NotFound {
        return em.searchEvent(keywords);
    }

    /**
     * Removes all events from this student's calendar.
     */
    public void removeAllEvents() throws FileNotFoundException {
        em.removeAllEvents();
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
        return em.retrieveEvent(date, time, title, URL);
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
     * @return The new Event object.
     */
    public Event editEvent(String date, String time, String title, String URL, String newDate, String newTime, String newTitle, String newURL) throws IOException {
        return em.editEvent(date, time, title, URL, newDate, newTime, newTitle, newURL);
    }

    /**
     * Retrieves all events on this student's calendar.
     * @return A list of all event objects on this student's calendar.
     */
    public List<Event> retrieveAllEvents() {
        return em.retrieveAllEvents();
    }


}
