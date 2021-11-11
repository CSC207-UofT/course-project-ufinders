package Events;

import Events.Event;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.List;

public class MakeDeleteEvent { //The Use Case that contains a list of events for this user and manages them
    //List of events this object stores
    private List<Event> events;


    /**
     * Constructs a Events.MakeDeleteEvent object.
     */
    public MakeDeleteEvent() {
        events = new ArrayList<Event>();
    }

    /**
     * Adds an event to the calendar.
     *
     * @param date The date of the event.
     * @param time The time the event takes place at.
     * @param title The name of the event.
     * @param URL The URL of the event if it's from the UofT website.
     */
    public Event addEvent(String date, String time, String title, String URL) {
        Event event = new Event(date, time, title, URL);
        events.add(event);
        return event;
    }

    /**
     * Retrieves an event from this student's calendar.
     *
     * @param date The date of the event.
     * @param time The time the event takes place at.
     * @param title The name of the event.
     * @param URL The URL of the event if it's from the UofT website.
     */
    public Event retrieveEvent(String date, String time, String title, String URL) {
        for (Event event : events) {
            if (!Objects.equals(event.getEventDate(), date)) {
                continue;
            }
            if (!Objects.equals(event.getEventTime(), time)) {
                continue;
            }
            if (!Objects.equals(event.getEventTitle(), title)) {
                continue;
            }
            if (!Objects.equals(event.getEventURL(), URL)) {
                continue;
            }
            return event;
        }
        return null;
    }

    /**
     * Removes all events from this student's calendar.
     */
    public void removeAllEvents() {
        events = new ArrayList<>();
    }

    /**
     * Removes a specified event from this student's calendar.
     *
     * @param date The date of the event.
     * @param time The time the event takes place at.
     * @param title The name of the event.
     * @param URL The URL of the event if it's from the UofT website.
     */
    public void removeEvent(String date, String time, String title, String URL) {
        Event result = this.retrieveEvent(date, time, title, URL);
        if (result != null) {
            events.remove(result);
        }
    }

    /**
     * Returns all events on this user's calendar holds.
     */
    public List<Event> retrieveAllEvents() {
        return events;
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

    }

}
