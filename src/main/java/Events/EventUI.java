package Events;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class EventUI { //The user interface for the events section

    //Events.EventManager Controller that controls how events work
    private EventManager em;

    /**
     * Constructor that initializes the Events.EventUI object and its Events.EventManager attribute.
     * @param userID the utorID of the student
     */
    public EventUI(String userID) {
        File userInfo = new File("/course-project-ufinders/src/main/java/userData/" + userID + ".txt");
        em = new EventManager(userID);
    }

    /**
     * Constructs an event on a calendar.
     *
     * @param date The date of the event.
     * @param time The time the event takes place at.
     * @param title The name of the event.
     * @param URL The URL of the event if it's from the UofT website.
     */
    public Event AddEvent(String date, String time, String title, String URL) throws IOException {
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
    public void RemoveEvent(String date, String time, String title, String URL) throws IOException {
        em.removeSingleEvent(date, time, title, URL);
    }

    /**
     * Removes all events from this student's calendar.
     */
    public void RemoveAllEvents() throws FileNotFoundException {
        em.removeAllEvents();
    }

    /**
     * Retrieves an event from this student's database.
     *
     * @param date The date of the event.
     * @param time The time the event takes place at.
     * @param title The name of the event.
     * @param URL The URL of the event if it's from the UofT website.
     */
    public Event RetrieveEvent(String date, String time, String title, String URL) {
        return em.retrieveEvent(date, time, title, URL);
    }

    /**
     * Retrieves all events on this student's calendar.
     */
    public List<Event> RetrieveAllEvents() {
        return em.retrieveAllEvents();
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
    public void SetAlarm(String date, String time, String title, String URL, String AlarmDate, String AlarmTime) {
        em.setAlarm(date, time, title, URL, AlarmDate, AlarmTime);
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
        em.removeAlarm(date, time, title, URL);
    }

    /**
     * Loads this user's calendar from their database.
     */
    public void LoadEvents() throws IOException {
        em.loadEvents();
    }

}
