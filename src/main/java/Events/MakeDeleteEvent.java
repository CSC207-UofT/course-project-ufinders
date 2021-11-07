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

    public void removeAllEvents() {
        events = new ArrayList<>();
    }

    public void removeEvent(String date, String time, String title, String URL) {
        Event result = this.retrieveEvent(date, time, title, URL);
        if (result != null) {
            events.remove(result);
        }
    }

    /**
     * Prints all events this object holds.
     */
    public List<Event> retrieveAllEvents() {
        return events;
    }

    public void setAlarm(String date, String time, String title, String URL, String AlarmDate, String AlarmTime) {

    }

    public void removeAlarm(String date, String time, String title, String URL) {

    }

}
