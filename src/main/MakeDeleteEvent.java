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
        events = new ArrayList<>();
    }

    /**
     * Adds an event to the calendar.
     *
     * @param date The date of the event.
     * @param time The time the event takes place at.
     * @param title The name of the event.
     * @param url The URL of the event if it's from the UofT website.
     * @param user The username of the user using this program
     * @param print Whether to print the event upon adding it.
     */
    public void addEvent(Date date, String time, String title, String URL, String user, Boolean print) {
        Event event = new Event(date, time, title, URL);
        events.add(event);
        if (print) {
            this.printEvent(event);
        }
    }

    /**
     * Prints the event.
     *
     * @param event The event itself.
     */
    public void printEvent(Event event) {
        System.out.println(event.getEventTitle());
        System.out.println(event.getEventDate());
        System.out.println(event.getEventTime());
        if (!Objects.equals(event.getEventURL(), "")) {
            System.out.println(event.getEventURL());
        }
    }

    /**
     * Prints all events this object holds.
     */
    public void displayEvents() {
        for(Event e: events) {
            this.printEvent(e);
        }
    }

}
